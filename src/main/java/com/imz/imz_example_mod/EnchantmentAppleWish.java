package com.imz.imz_example_mod;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "imz_example_mod")
public class EnchantmentAppleWish extends Enchantment{
    public EnchantmentAppleWish(Enchantment.Rarity rarity, EnumEnchantmentType type, EntityEquipmentSlot[] slots) {
        super(rarity, type, slots);
        // rarity 代表了这个附魔的稀有程度，可以是 COMMON、UNCOMMON、RARE 或 VERY_RARE。
        // type 代表了这个附魔可以加在什么工具/武器/装备上。
        // slots 代表了“这个附魔加在什么格子里装的工具/武器/装备上才有效果”，例如荆棘只在盔甲四件套上有效。
        // slots 会影响 getEnchantedItem（func_92099_a）的返回值，这个方法用于获取某个实体上有指定附魔的物品。
    }

    // func_77319_d，决定了附魔的最低可能等级
    @Override
    public int getMinLevel() {
        return 1;
    }

    // func_77325_b，决定了附魔的最高可能等级
    @Override
    public int getMaxLevel() {
        return 3;
    }

     static EntityEquipmentSlot[] entityEquipmentSlots = {EntityEquipmentSlot.CHEST};

    @SubscribeEvent
    public static void onEnchantmentRegistration(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(new EnchantmentAppleWish(Rarity.COMMON,EnumEnchantmentType.ALL,entityEquipmentSlots).setName("super_duper_enchantment").setRegistryName("super_duper_enchantment"));
        // setName（func_77322_b）用于组成附魔名称的本地化键，和注册名无关。本地化相关的内容请参考第 13 章。
    }
}
