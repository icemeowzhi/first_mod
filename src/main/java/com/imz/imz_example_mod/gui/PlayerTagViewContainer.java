package com.imz.imz_example_mod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerTagViewContainer extends Container {
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
    public PlayerTagViewContainer(EntityPlayer player, World world, int x, int y, int z){
        this.world = world;
        this.pos = new BlockPos(x,y,z);
        this.player = player;
    }
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
