package com.imz.imz_example_mod.item;

import com.imz.imz_example_mod.CreativeTabs;
import com.imz.imz_example_mod.ExampleMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;


public class GreenAppleAxe extends ItemAxe {
    public static Item item = new GreenAppleAxe(GreenApple.materialTool,5f,-2f);
    public static final String REGISTRY_NAME = "green_apple_axe";

    protected GreenAppleAxe(ToolMaterial material, float damage, float speed) {
        super(material, damage, speed);
        super.setRegistryName(ExampleMod.QuickRegName(REGISTRY_NAME))
                .setCreativeTab(CreativeTabs.EXAMPLE_CREATIVE_TAB)
                .setTranslationKey(ExampleMod.QuickTranslationKey(REGISTRY_NAME));
    }
}


