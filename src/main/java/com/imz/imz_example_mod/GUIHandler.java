package com.imz.imz_example_mod;

import com.imz.imz_example_mod.gui.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

public class GUIHandler implements IGuiHandler {

    public static final int GADynamic = 1;
    public static final int GAEOven = 2;
    public static final int PlayerTagView = 3;

    public GUIHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(ExampleMod.instance, this);
    }


    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID)
        {
            case GADynamic:
                return new GADynamicContainer(player,world,x,y,z);
            case GAEOven:
                return new GAEOvenContainer(player,world,x,y,z);
            case PlayerTagView:
                return new PlayerTagViewContainer(player,world,x,y,z);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID)
        {
            case GADynamic:
                return new GADynamicGUI(new GADynamicContainer(player,world,x,y,z));
            case GAEOven:
                return new GAEOvenGUI(new GAEOvenContainer(player, world, x, y, z));
            case PlayerTagView:
                return new PlayerTagViewGUI(new PlayerTagViewContainer(player,world,x,y,z));
            default:
                return null;
        }
    }
}

