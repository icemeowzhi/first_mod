package com.imz.imz_example_mod;

import com.imz.imz_example_mod.block.GADynamic;
import com.imz.imz_example_mod.block.GAEOven;
import com.imz.imz_example_mod.block.GreenAppleBlock;
import com.imz.imz_example_mod.item.*;
import com.imz.imz_example_mod.recipes.GAEOvenRecipe;
import com.imz.imz_example_mod.tileentity.GADynamicTileEntity;
import com.imz.imz_example_mod.tileentity.GAEOvenTileEntity;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;



@Mod.EventBusSubscriber(modid = "imz_example_mod")
public class Initializer {

    public static GAEOvenRecipe recipe = new GAEOvenRecipe();

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event){

        event.getRegistry().register(MyFirstItem.item);
        event.getRegistry().register(TrollItem.item);
        event.getRegistry().register(GreenApple.item);
        event.getRegistry().register(GreenAppleSword.item);
        event.getRegistry().register(GreenAppleAxe.item);
        event.getRegistry().register(GreenApplePickaxe.item);
        event.getRegistry().register(GreenAppleShovel.item);
        event.getRegistry().register(GreenAppleHelmet.item);
        event.getRegistry().register(GreenAppleLeggings.item);
        event.getRegistry().register(GreenAppleChestplate.item);
        event.getRegistry().register(GreenAppleBoots.item);
        event.getRegistry().register(GemGreenApple.item);



    }

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        // 和物品一样，每一个方块都有唯一一个注册名，不能使用大写字母。
        event.getRegistry().register(GreenAppleBlock.block);
        event.getRegistry().register(GADynamic.block);
        TileEntity.register(GADynamicTileEntity.ID,GADynamicTileEntity.class);
        event.getRegistry().register(GAEOven.block);
        TileEntity.register(GAEOvenTileEntity.ID,GAEOvenTileEntity.class);
        recipe.regRecipes(GreenApple.item, GemGreenApple.item,120,"1");
        recipe.regFuels(GreenApple.item,480);
        recipe.regFuels(Items.APPLE,120);

    }
    @SubscribeEvent
    public static void registerBlockItem(RegistryEvent.Register<Item> event) {
        // 注意这个 ItemBlock 使用了和它对应的方块一样的注册名。
        // 对于所有有物品形态的方块，其物品的注册名和它自己的注册名需要保持一致。
        event.getRegistry().register(GreenAppleBlock.item);
        event.getRegistry().register(GADynamic.item);
        event.getRegistry().register(GAEOven.item);
    }

}
