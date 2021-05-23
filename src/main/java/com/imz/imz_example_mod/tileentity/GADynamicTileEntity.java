package com.imz.imz_example_mod.tileentity;

import com.imz.imz_example_mod.ExampleMod;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class GADynamicTileEntity extends TileEntity {


    public static final String ID = ExampleMod.MODID + ":dynamic_machine";

    public final ItemStackHandler up;
    private final ItemStackHandler side;
    private final ItemStackHandler down;

    public GADynamicTileEntity(){
        up = new ItemStackHandler(3){
            @Override
            protected void onContentsChanged(int slot) {
                GADynamicTileEntity.this.markDirty();
            }
        };
        side = new ItemStackHandler(3){
            @Override
            protected void onContentsChanged(int slot) {
                GADynamicTileEntity.this.markDirty();
            }
        };
        down = new ItemStackHandler(3){
            @Override
            protected void onContentsChanged(int slot) {
                GADynamicTileEntity.this.markDirty();
            }
        };

    }




    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.down.deserializeNBT(compound.getCompoundTag("Down"));
        this.side.deserializeNBT(compound.getCompoundTag("Side"));
        this.up.deserializeNBT(compound.getCompoundTag("Up"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("Down",this.down.serializeNBT());
        compound.setTag("Side",this.side.serializeNBT());
        compound.setTag("Up",this.up.serializeNBT());
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
}
