package com.imz.imz_example_mod;

import com.imz.imz_example_mod.capability.CapabilityLoader;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION, useMetadata = true)
public class ExampleMod
{
    public static final String MODID = "imz_example_mod";
    public static final String NAME = "imz/'s Example Mod";
    public static final String VERSION = "0.0.0";

    @Mod.Instance(ExampleMod.MODID)
    public static ExampleMod instance;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        System.out.println("Hello,my first mod.");
        logger = event.getModLog();
        NetworkRegistryHandler.register();
        new CapabilityLoader(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code

    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        new CommandLoader(event);
    }

    public static String QuickTranslationKey(String name){return name;}
    public static String QuickRegName(String name){return MODID+ ":" + name;}
}
