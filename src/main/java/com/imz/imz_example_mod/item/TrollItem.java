package com.imz.imz_example_mod.item;

import com.imz.imz_example_mod.CreativeTabs;
import com.imz.imz_example_mod.ExampleMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class TrollItem extends Item {
    public static Item item = new TrollItem();
    public static final String REGISTRY_NAME = "troll";
/*
拿着它常驻力量II，中毒I
 */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_GHAST_SCREAM, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        return new ActionResult<>(EnumActionResult.SUCCESS,player.getHeldItem(hand));
    }

    public TrollItem(){
        super.setCreativeTab(CreativeTabs.EXAMPLE_CREATIVE_TAB)
                .setRegistryName(ExampleMod.QuickRegName(REGISTRY_NAME))
                .setTranslationKey(ExampleMod.QuickTranslationKey(REGISTRY_NAME));
    }
}
