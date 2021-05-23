package com.imz.imz_example_mod.item;

import com.imz.imz_example_mod.CreativeTabs;
import com.imz.imz_example_mod.ExampleMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class GreenAppleSword extends ItemSword {
    public static Item item = new GreenAppleSword(GreenApple.materialTool);
    public static final String REGISTRY_NAME = "green_apple_sword";
    public GreenAppleSword(ToolMaterial material) {
        super(material);
        super.setRegistryName(ExampleMod.QuickRegName(REGISTRY_NAME))
                .setCreativeTab(CreativeTabs.EXAMPLE_CREATIVE_TAB)
                .setTranslationKey(ExampleMod.QuickTranslationKey(REGISTRY_NAME));
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        player.inventory.addItemStackToInventory(GreenApple.item.getDefaultInstance());
        player.getHeldItem(hand).damageItem(50,player);
        return new ActionResult<>(EnumActionResult.SUCCESS,player.getHeldItem(hand));
    }
}
