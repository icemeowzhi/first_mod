package com.imz.imz_example_mod;

import com.imz.imz_example_mod.Commands;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandLoader {
    public CommandLoader(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new Commands());
    }
}
