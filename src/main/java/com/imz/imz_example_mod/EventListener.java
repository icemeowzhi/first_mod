package com.imz.imz_example_mod;

import com.imz.imz_example_mod.capability.CapabilityLoader;
import com.imz.imz_example_mod.capability.CapabilityTag;
import com.imz.imz_example_mod.capability.ITag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "imz_example_mod")
public class EventListener {
    //@SubscribeEvent
    public static void whenArrowNockEventHappen(ArrowNockEvent event){
        System.out.println("if I see this text,it(ArrowNockEvent) success."); //success
    }

    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof EntityPlayer)
        {
            ICapabilitySerializable<NBTTagCompound> provider = new CapabilityTag.ProviderPlayer();
            event.addCapability(new ResourceLocation(ExampleMod.MODID+":"+"player_tag"),provider);

        }
    }

    @SubscribeEvent
    public void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event)
    {
        Capability<ITag> capability = CapabilityLoader.tag;
        Capability.IStorage<ITag> storage = capability.getStorage();

        if (event.getOriginal().hasCapability(capability, null) && event.getEntityPlayer().hasCapability(capability, null))
        {
            NBTBase nbt = storage.writeNBT(capability, event.getOriginal().getCapability(capability, null), null);
            storage.readNBT(capability, event.getEntityPlayer().getCapability(capability, null), null, nbt);
        }
    }

}
