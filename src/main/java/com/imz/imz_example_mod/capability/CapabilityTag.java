package com.imz.imz_example_mod.capability;

import com.imz.imz_example_mod.Tag;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class CapabilityTag {

    public static class Storage implements Capability.IStorage<ITag>{

        @Nullable
        @Override
        public NBTBase writeNBT(Capability<ITag> capability, ITag instance, EnumFacing side) {
            NBTTagList list = new NBTTagList();
            for (Tag tag : instance.getTags()){
                NBTTagCompound compound = new NBTTagCompound();
                if (tag != null){
                    compound.setString("tag",tag.getTagName());
                    compound.setString("ID",tag.getID());
                    compound.setString("rarity",tag.getRarity());
                }
                list.appendTag(compound);
            }
            return list;
        }

        @Override
        public void readNBT(Capability<ITag> capability, ITag instance, EnumFacing side, NBTBase nbt) {
            NBTTagList list = (NBTTagList) nbt;
            Tag[] tags = new Tag[list.tagCount()];
            for (int i = 0 ; i < tags.length ; ++i ){
                NBTTagCompound compound = list.getCompoundTagAt(i);
                if (!compound.isEmpty()){
                    tags[i] = new Tag(compound.getString("tag"),compound.getString("ID"),compound.getString("rarity"));
                }
            }
            instance.setTags(tags);
        }
    }

    public static class Implementation implements ITag{

        private Tag[] tags = new Tag[10];

        @Override
        public Tag[] getTags() {
            return tags.clone();
        }

        @Override
        public void setTags(Tag[] tags) {
            this.tags = tags.clone();
        }

        @Override
        public void addTag(Tag tag) {
            for (int i = 1 ; i<tags.length ; ++i){
                tags[i - 1] = tags[i];
            }
            tags[tags.length - 1] = tag;
        }
    }

    public static class ProviderPlayer implements ICapabilitySerializable<NBTTagCompound>{


        private ITag tags = new Implementation();
        private Capability.IStorage<ITag> storage = CapabilityLoader.tag.getStorage();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return CapabilityLoader.tag.equals(capability);
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {

            if (CapabilityLoader.tag.equals(capability)){
                @SuppressWarnings("unchecked")
                T result = (T)tags;
                return result;
            }
            return  null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setTag("tags",storage.writeNBT(CapabilityLoader.tag,tags,null));
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            NBTTagList list = (NBTTagList) nbt.getTag("tags");
            storage.readNBT(CapabilityLoader.tag,tags,null,list);

        }
    }


    public static class ProviderItemStack implements ICapabilitySerializable<NBTTagCompound>{


        private ITag tags = new Implementation();
        private Capability.IStorage<ITag> storage = CapabilityLoader.tag.getStorage();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return CapabilityLoader.tag.equals(capability);
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {

            if (CapabilityLoader.tag.equals(capability)){
                @SuppressWarnings("unchecked")
                T result = (T)tags;
                return result;
            }
            return  null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setTag("tags",storage.writeNBT(CapabilityLoader.tag,tags,null));
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            NBTTagList list = (NBTTagList) nbt.getTag("tags");
            storage.readNBT(CapabilityLoader.tag,tags,null,list);

        }
    }
}

