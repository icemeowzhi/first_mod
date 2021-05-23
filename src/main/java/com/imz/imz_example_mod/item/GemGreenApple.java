package com.imz.imz_example_mod.item;

import com.imz.imz_example_mod.CreativeTabs;
import com.imz.imz_example_mod.ExampleMod;
import net.minecraft.item.Item;

public class GemGreenApple extends Item {
    public static Item item = new GemGreenApple();
    public static final String REGISTRY_NAME = "green_apple_gem";
    public GemGreenApple(){
        super.setRegistryName(ExampleMod.QuickRegName(REGISTRY_NAME))
                .setCreativeTab(CreativeTabs.EXAMPLE_CREATIVE_TAB)
                .setTranslationKey(ExampleMod.QuickTranslationKey(REGISTRY_NAME));
    }
}
