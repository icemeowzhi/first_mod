package com.imz.imz_example_mod;

import com.imz.imz_example_mod.item.GreenApple;
import net.minecraft.item.ItemStack;

public class CreativeTabs {
    public static final net.minecraft.creativetab.CreativeTabs EXAMPLE_CREATIVE_TAB = new net.minecraft.creativetab.CreativeTabs("example_tab") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(GreenApple.item);
        }

    };
}
