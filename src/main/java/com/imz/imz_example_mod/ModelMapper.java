package com.imz.imz_example_mod;


import com.imz.imz_example_mod.block.GADynamic;
import com.imz.imz_example_mod.block.GAEOven;
import com.imz.imz_example_mod.block.GreenAppleBlock;
import com.imz.imz_example_mod.item.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = "imz_example_mod")
public final class ModelMapper {
    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(MyFirstItem.item, 0, new ModelResourceLocation(MyFirstItem.item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(GreenApple.item, 0, new ModelResourceLocation(GreenApple.item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(GreenAppleSword.item, 0, new ModelResourceLocation(GreenAppleSword.item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(GreenAppleAxe.item, 0, new ModelResourceLocation(GreenAppleAxe.item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(GreenApplePickaxe.item, 0, new ModelResourceLocation(GreenApplePickaxe.item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(GreenAppleShovel.item, 0, new ModelResourceLocation(GreenAppleShovel.item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(TrollItem.item, 0, new ModelResourceLocation(TrollItem.item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(GreenAppleHelmet.item, 0, new ModelResourceLocation(GreenAppleHelmet.item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(GreenAppleLeggings.item, 0, new ModelResourceLocation(GreenAppleLeggings.item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(GreenAppleChestplate.item, 0, new ModelResourceLocation(GreenAppleChestplate.item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(GreenAppleBoots.item, 0, new ModelResourceLocation(GreenAppleBoots.item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(GreenAppleBlock.item, 0, new ModelResourceLocation(GreenAppleBlock.item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(GADynamic.item,0,new ModelResourceLocation(GADynamic.item.getRegistryName(),"inventory"));
        ModelLoader.setCustomModelResourceLocation(GemGreenApple.item, 0, new ModelResourceLocation(GemGreenApple.item.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(GAEOven.item,0,new ModelResourceLocation(GAEOven.item.getRegistryName(),"inventory"));
    }
}
