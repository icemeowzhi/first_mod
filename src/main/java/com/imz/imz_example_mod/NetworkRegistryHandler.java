package com.imz.imz_example_mod;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NetworkRegistryHandler{
    public static void register(){
        NetworkRegistry.INSTANCE.registerGuiHandler(ExampleMod.MODID,new GUIHandler());
    }


    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onClientCustomPacket(FMLNetworkEvent.ClientCustomPacketEvent event){

    }
}

