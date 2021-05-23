package com.imz.imz_example_mod.item;

import com.imz.imz_example_mod.CreativeTabs;
import com.imz.imz_example_mod.ExampleMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;


public class GreenAppleShovel extends ItemSpade {
    public static Item item = new GreenAppleShovel(GreenApple.materialTool);
    public static final String REGISTRY_NAME = "green_apple_shovel";

    public GreenAppleShovel(ToolMaterial material) {
        super(material);
        super.setRegistryName(ExampleMod.QuickRegName(REGISTRY_NAME))
                .setCreativeTab(CreativeTabs.EXAMPLE_CREATIVE_TAB)
                .setTranslationKey(ExampleMod.QuickTranslationKey(REGISTRY_NAME));
    }
}


