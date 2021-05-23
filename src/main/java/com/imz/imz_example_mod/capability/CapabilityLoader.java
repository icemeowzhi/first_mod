package com.imz.imz_example_mod.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CapabilityLoader {
    @CapabilityInject(ITag.class)
    public static Capability<ITag> tag;
    public CapabilityLoader(FMLPreInitializationEvent event){
        CapabilityManager.INSTANCE.register(ITag.class , new CapabilityTag.Storage() , CapabilityTag.Implementation.class);
    }
}
