package com.imz.imz_example_mod.block;


import com.imz.imz_example_mod.CreativeTabs;
import com.imz.imz_example_mod.ExampleMod;
import com.imz.imz_example_mod.GUIHandler;
import com.imz.imz_example_mod.tileentity.GADynamicTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;


//一个有朝向的方块，右键改变方块材质
//it's a pain,a torture 2021/5/9
//but it success 2021/5/10
//新需求 使用 TileEntity 写一个GUI，在GUI切换材质,并且存9组物品
//done in 5.14


public class GADynamic extends BlockContainer {

    public static final String REGISTRY_NAME = "dynamic_machine";
    public static Block block = new GADynamic(Material.ROCK);
    public static Item item = new ItemBlock(block).setRegistryName(ExampleMod.QuickRegName(REGISTRY_NAME));

    public static PropertyEnum<EnumMachineType> TYPE;

    public static IProperty<EnumFacing> FACING;

    public GADynamic(Material materialIn) {
        super(materialIn);
        super.setRegistryName(ExampleMod.QuickRegName(REGISTRY_NAME))
                .setCreativeTab(CreativeTabs.EXAMPLE_CREATIVE_TAB)
                .setTranslationKey(ExampleMod.QuickTranslationKey(REGISTRY_NAME))
                .setHardness(2.5F)
                .setHarvestLevel("pickaxe", 2);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(FACING,EnumFacing.NORTH).withProperty(TYPE,EnumMachineType.M1));
    }



    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new GADynamicTileEntity();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        TYPE =PropertyEnum.create("type",EnumMachineType.class);
        FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
        return new BlockStateContainer(this, FACING,TYPE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing facing = EnumFacing.byHorizontalIndex(meta & 3);
        EnumMachineType type = EnumMachineType.values()[meta >> 2];
        return this.getDefaultState().withProperty(FACING, facing).withProperty(TYPE, type);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int facing = state.getValue(FACING).getHorizontalIndex();
        int material = state.getValue(TYPE).ordinal() << 2;
        return facing | material;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos,state.withProperty(FACING,placer.getHorizontalFacing().getOpposite()));

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //worldIn.setBlockState(pos, state.cycleProperty(TYPE));

        if (!worldIn.isRemote){
            playerIn.openGui(ExampleMod.instance, GUIHandler.GADynamic,worldIn,pos.getX(),pos.getY(),pos.getZ());
            return true;
        }


        return false;
    }


    public enum EnumMachineType implements IStringSerializable {
        M1("m1"),M2("m2"),M3("m3"),M4("m4");

        private String name;

        EnumMachineType(String type){
            this.name=type;
        }

        @Override
        public String getName() {
            return this.name;
        }
        @Override
        public String toString()
        {
            return this.name;
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        IItemHandler up = tileEntity.getCapability(itemHandlerCapability,EnumFacing.UP);
        IItemHandler down = tileEntity.getCapability(itemHandlerCapability,EnumFacing.DOWN);
        IItemHandler side = tileEntity.getCapability(itemHandlerCapability,EnumFacing.NORTH);
        Block.spawnAsEntity(worldIn,pos,up.getStackInSlot(0));
        Block.spawnAsEntity(worldIn,pos,up.getStackInSlot(1));
        Block.spawnAsEntity(worldIn,pos,up.getStackInSlot(2));
        Block.spawnAsEntity(worldIn,pos,side.getStackInSlot(0));
        Block.spawnAsEntity(worldIn,pos,side.getStackInSlot(1));
        Block.spawnAsEntity(worldIn,pos,side.getStackInSlot(2));
        Block.spawnAsEntity(worldIn,pos,down.getStackInSlot(0));
        Block.spawnAsEntity(worldIn,pos,down.getStackInSlot(1));
        Block.spawnAsEntity(worldIn,pos,down.getStackInSlot(2));

        super.breakBlock(worldIn,pos,state);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new GADynamicTileEntity();
    }

    /*

    @Override
    public BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING,TYPE);
    }



    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing facing = EnumFacing.byHorizontalIndex(meta & 3);
        EnumMachineType type = EnumMachineType.values()[meta >> 2];
        return this.getDefaultState().withProperty(FACING, facing).withProperty(TYPE, type);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int facing = state.getValue(FACING).getHorizontalIndex();
        int material = state.getValue(TYPE).ordinal() << 2;
        return facing | material;
    }



    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        worldIn.setBlockState(pos, state.cycleProperty(TYPE));
        return true;
    }



    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos,state.withProperty(FACING,EnumFacing.getFacingFromVector(pos.getX(), 0.0f,pos.getZ()).getOpposite()));

    }

     */




}


