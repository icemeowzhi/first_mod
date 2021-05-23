package com.imz.imz_example_mod.item;

import com.imz.imz_example_mod.CreativeTabs;
import com.imz.imz_example_mod.ExampleMod;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;

public class GreenApple extends ItemFood {
    public static final String REGISTRY_NAME = "green_apple";
    public static Item item = new GreenApple(6,2.0f,false);
    public static Item.ToolMaterial materialTool = EnumHelper.addToolMaterial("GREENAPPLE",2,200,4f,5f,20);
    public static ItemArmor.ArmorMaterial materialArmor = EnumHelper.addArmorMaterial("GREENAPPLE","green_apple",15,new int[]{2,5,6,2},10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD,1.0f);
    public GreenApple(int hungerHeal, float saturation, boolean isWolfFood){
        super(hungerHeal, saturation, isWolfFood);
        super.setRegistryName(ExampleMod.QuickRegName(REGISTRY_NAME))
                .setCreativeTab(CreativeTabs.EXAMPLE_CREATIVE_TAB)
                .setTranslationKey(ExampleMod.QuickTranslationKey(REGISTRY_NAME));
        this.setAlwaysEdible();
    }
}
