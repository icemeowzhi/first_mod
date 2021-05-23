package com.imz.imz_example_mod.gui;

import com.imz.imz_example_mod.block.GreenAppleBlock;
import com.imz.imz_example_mod.item.GreenApple;
import com.imz.imz_example_mod.tileentity.GAEOvenTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class GAEOvenContainer extends Container {
    private final IItemHandler up;
    private final IItemHandler down;
    private final IItemHandler side;
    private final BlockPos pos;
    protected Slot fuel;

    private TileEntity tileEntity;
    private int burnTime;

    public int getTotalBurnTime() {
        return totalBurnTime;
    }


    public int getTotalFuelTime() {
        return totalFuelTime;
    }

    private int totalBurnTime;
    private int fuelTime;
    private int totalFuelTime;
    public int getBurnTime() {
        return burnTime;
    }

    public int getFuelTime() {
        return fuelTime;
    }

    public GAEOvenContainer(EntityPlayer player, World world, int x, int y, int z) {

        this.pos = new BlockPos(x,y,z);
        tileEntity = world.getTileEntity(this.pos);
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        InventoryPlayer inventoryPlayer = player.inventory;

        assert tileEntity != null;
        this.up = tileEntity.getCapability(itemHandlerCapability, EnumFacing.UP);
        this.down = tileEntity.getCapability(itemHandlerCapability, EnumFacing.DOWN);
        this.side = tileEntity.getCapability(itemHandlerCapability, EnumFacing.NORTH);


        int[] range = new int[]{0,1,2,3,4,5,6,7,8};
        for (int i : range){
            this.addSlotToContainer(new Slot(inventoryPlayer,i,i*18+8,142));
            this.addSlotToContainer(new Slot(inventoryPlayer,i+9,i*18+8,84));
            this.addSlotToContainer(new Slot(inventoryPlayer,i+18,i*18+8,102));
            this.addSlotToContainer(new Slot(inventoryPlayer,i+27,i*18+8,120));
        }

        this.addSlotToContainer(new SlotItemHandler(this.up,0,36,17));
        this.addSlotToContainer(this.fuel = new SlotItemHandler(this.side,0,36,53){
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return (stack.getItem() == GreenAppleBlock.item ||
                                stack.getItem() == GreenApple.item ||
                                stack.getItem() == Items.APPLE ) &&
                        super.isItemValid(stack);
            }
        });

        this.addSlotToContainer(new SlotItemHandler(this.down,0,96,35));
        this.addSlotToContainer(new SlotItemHandler(this.down,1,125,35));

    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        if (tileEntity instanceof GAEOvenTileEntity){
            int burnTime = ((GAEOvenTileEntity)tileEntity).getBurnTime();
            int totalBurnTime = ((GAEOvenTileEntity)tileEntity).getTotalBurnTime();
            int fuelTime = ((GAEOvenTileEntity)tileEntity).getFuelTime();
            int totalFuelTime = ((GAEOvenTileEntity)tileEntity).getTotalFuelTime();
            if (this.burnTime != burnTime){
                this.burnTime = burnTime;
                for (IContainerListener listener:this.listeners){
                    listener.sendWindowProperty(this,0,burnTime);
                }
            }
            if (this.fuelTime != fuelTime){
                this.fuelTime = fuelTime;
                for (IContainerListener listener:this.listeners){
                    listener.sendWindowProperty(this,1,fuelTime);
                }

            }
            if (this.totalBurnTime != totalBurnTime){
                this.totalBurnTime = totalBurnTime;
                for (IContainerListener listener:this.listeners){
                    listener.sendWindowProperty(this,2,totalBurnTime);
                }

            }
            if (this.totalFuelTime != totalFuelTime){
                System.out.println("updated " + totalFuelTime);
                this.totalFuelTime = totalFuelTime;
                for (IContainerListener listener:this.listeners){
                    listener.sendWindowProperty(this,3,totalFuelTime);
                }

            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        if (id == 0){
            this.burnTime = data;
        }
        if (id == 1){
            this.fuelTime = data;
        }
        if (id == 2){
            this.totalBurnTime = data;
        }
        if (id == 3){
            this.totalFuelTime = data;
        }
    }

}
