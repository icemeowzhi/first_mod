package com.imz.imz_example_mod.block;

import com.imz.imz_example_mod.CreativeTabs;
import com.imz.imz_example_mod.ExampleMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import static com.imz.imz_example_mod.ExampleMod.MODID;


public class GreenAppleBlock extends Block {

    private static final String REGISTRY_NAME = "green_apple_block";

    public static Block block = new GreenAppleBlock(Material.ROCK);

    public static Item item = new ItemBlock(block).setRegistryName(MODID, REGISTRY_NAME);

    public GreenAppleBlock(Material materialIn) {
        super(materialIn);
        super.setRegistryName(ExampleMod.QuickRegName(REGISTRY_NAME))
        .setCreativeTab(CreativeTabs.EXAMPLE_CREATIVE_TAB)
                .setTranslationKey(ExampleMod.QuickTranslationKey(REGISTRY_NAME))
                .setHardness(2.5F)
                .setHarvestLevel("pickaxe", 2);
    }


}
