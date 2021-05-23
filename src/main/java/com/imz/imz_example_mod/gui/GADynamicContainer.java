package com.imz.imz_example_mod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class GADynamicContainer extends Container {
    public World getWorld() {
        return world;
    }

    private final World world;

    public BlockPos getPos() {
        return pos;
    }

    private final BlockPos pos;

    public EntityPlayer getPlayer() {
        return player;
    }

    private final EntityPlayer player;
    private final IItemHandler up;
    private final IItemHandler side;
    private final IItemHandler down;



    public GADynamicContainer(EntityPlayer player, World world,int x,int y, int z) {
        this.world = world;
        this.pos = new BlockPos(x,y,z);
        this.player = player;

        TileEntity tileEntity = world.getTileEntity(this.pos);
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        InventoryPlayer inventoryPlayer = player.inventory;


        assert tileEntity != null;
        this.up = tileEntity.getCapability(itemHandlerCapability, EnumFacing.UP);
        this.down = tileEntity.getCapability(itemHandlerCapability, EnumFacing.DOWN);
        this.side = tileEntity.getCapability(itemHandlerCapability, EnumFacing.NORTH);


        //this.addSlotToContainer(new SlotItemHandler(items,0,8,16));
        //this.addSlotToContainer(new SlotItemHandler(items,1,25,16));
        this.addSlotToContainer(new SlotItemHandler(this.up,0,8,16));
        this.addSlotToContainer(new SlotItemHandler(this.up,1,26,16));
        this.addSlotToContainer(new SlotItemHandler(this.up,2,44,16));

        this.addSlotToContainer(new SlotItemHandler(this.side,0,8,34));
        this.addSlotToContainer(new SlotItemHandler(this.side,1,26,34));
        this.addSlotToContainer(new SlotItemHandler(this.side,2,44,34));

        this.addSlotToContainer(new SlotItemHandler(this.down,0,8,52));
        this.addSlotToContainer(new SlotItemHandler(this.down,1,26,52));
        this.addSlotToContainer(new SlotItemHandler(this.down,2,44,52));

        int[] range = new int[]{0,1,2,3,4,5,6,7,8};
        for (int i : range){
            this.addSlotToContainer(new Slot(inventoryPlayer,i,i*18+8,142));
            this.addSlotToContainer(new Slot(inventoryPlayer,i+9,i*18+8,84));
            this.addSlotToContainer(new Slot(inventoryPlayer,i+18,i*18+8,102));
            this.addSlotToContainer(new Slot(inventoryPlayer,i+27,i*18+8,120));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }


}


