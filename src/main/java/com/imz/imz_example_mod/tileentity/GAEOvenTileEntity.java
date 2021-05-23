package com.imz.imz_example_mod.tileentity;

import com.imz.imz_example_mod.ExampleMod;
import com.imz.imz_example_mod.Initializer;
import com.imz.imz_example_mod.recipes.GAEOvenRecipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class GAEOvenTileEntity extends TileEntity implements ITickable {
    public static final String ID = ExampleMod.MODID + ":gae_oven";

    public final ItemStackHandler up;
    private final ItemStackHandler down;
    private final ItemStackHandler side;
    private final GAEOvenRecipe recipe = Initializer.recipe;

    public int getBurnTime() {
        return burnTime;
    }

    private int burnTime = 0;
    private boolean isBurning = false;
    private ItemStack result;

    public int getTotalBurnTime() {
        return totalBurnTime;
    }

    private int totalBurnTime;

    public int getFuelTime() {
        return fuelTime;
    }

    private int fuelTime = 1;

    public int getTotalFuelTime() {
        return totalFuelTime;
    }

    private int totalFuelTime = 1;
    public GAEOvenTileEntity(){
        up = new ItemStackHandler(1){
            @Override
            protected void onContentsChanged(int slot) {
                GAEOvenTileEntity.this.markDirty();
            }
        };
        down = new ItemStackHandler(2){
            @Override
            protected void onContentsChanged(int slot) {
                GAEOvenTileEntity.this.markDirty();
            }
        };
        side = new ItemStackHandler(1){
            @Override
            protected void onContentsChanged(int slot) {
                GAEOvenTileEntity.this.markDirty();
            }
        };

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.down.deserializeNBT(compound.getCompoundTag("Down"));
        this.up.deserializeNBT(compound.getCompoundTag("Up"));
        this.side.deserializeNBT(compound.getCompoundTag("Side"));
        this.burnTime = compound.getInteger("BurnTime");
        this.fuelTime = compound.getInteger("FuelTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("Down",this.down.serializeNBT());
        compound.setTag("Up",this.up.serializeNBT());
        compound.setTag("Side",this.up.serializeNBT());
        compound.setInteger("BurnTime",this.burnTime);
        compound.setInteger("FuelTime",this.fuelTime);
        return super.writeToNBT(compound);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        if (itemHandlerCapability.equals(capability)){
            /*
            if (EnumFacing.UP.equals(facing)){return (T) this.up;}
            if (EnumFacing.DOWN.equals(facing)){return (T) this.down;}
            return (T) this.side;

             */
            if (EnumFacing.UP.equals(facing)){return itemHandlerCapability.cast(this.up);}
            if (EnumFacing.DOWN.equals(facing)){return itemHandlerCapability.cast(this.down);}
            return itemHandlerCapability.cast(this.side);

        }
        return super.getCapability(capability,facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        return itemHandlerCapability.equals(capability) || super.hasCapability(capability,facing);
    }

    @Override
    public void update() {
        if(!world.isRemote) {
            if(fuelTime > 0){fuelTime--;}
            if (isBurning && fuelTime == 0){
                boolean canExtractFuel = recipe.hasFuel(this.side.extractItem(0,1,true).getItem());
                Item fuel = this.side.extractItem(0,1,true).getItem();
                totalFuelTime = recipe.getFuelTimeByInput(fuel);
                if (canExtractFuel){
                    this.side.extractItem(0,1,false).getItem();
                    fuelTime += totalFuelTime;
                }else {
                    burnTime = 0;
                    isBurning = false;
                }
            }
            ItemStack input = this.up.extractItem(0,1,true);
            boolean canExtractInput = recipe.hasRecipe(input.getItem());
            this.result = recipe.getRecipeOutputByInput(input.getItem()).getDefaultInstance();
            boolean canInsertOutput = this.down.insertItem(0,result,true).isEmpty() || this.down.insertItem(1,result,true).isEmpty();
            //System.out.println(canExtractInput);
            if (canExtractInput && canInsertOutput){
                isBurning = true;
                burnTime++;
                totalBurnTime = recipe.getProgressByInput(input.getItem());
                //System.out.println(totalBurnTime);
                if (burnTime>totalBurnTime){
                    burnTime = 0;
                    this.up.extractItem(0,1,false);
                    insertOutput(result);
                    markDirty();
                }
                /*
                if (burnTime == 0){
                    isBurning = true;
                    burnTime++;
                    totalBurnTime = GAEOvenRecipe.getBurnTimeByInput(input.getItem());
                    System.out.println(totalBurnTime);
                    if (burnTime>totalBurnTime){
                        burnTime = 0;
                        this.up.extractItem(0,1,false);
                        insertOutput(result);
                        markDirty();
                    }
                }

                 */
            }else {
                isBurning = false;
                markDirty();
            }
        }
    }

    private void insertOutput(ItemStack output){
        if (this.down.insertItem(0,output,true).isEmpty()){
            this.down.insertItem(0,output,false);
        }else {
            this.down.insertItem(1,output,false);
        }
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }

    public int getFuelScaled(int scale){
        return (scale / totalFuelTime) * fuelTime;
    }
}
