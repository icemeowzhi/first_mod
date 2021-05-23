package com.imz.imz_example_mod.block;

import com.imz.imz_example_mod.CreativeTabs;
import com.imz.imz_example_mod.ExampleMod;
import com.imz.imz_example_mod.GUIHandler;
import com.imz.imz_example_mod.tileentity.GADynamicTileEntity;
import com.imz.imz_example_mod.tileentity.GAEOvenTileEntity;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

//一个苹果炉拿苹果作燃料，可以把苹果烧成绿钻石

public class GAEOven extends BlockContainer {

    public static final String REGISTRY_NAME = "gae_oven";

    public static Block block = new GAEOven(Material.ROCK);
    public static Item item = new ItemBlock(block).setRegistryName(ExampleMod.QuickRegName(REGISTRY_NAME));

    public static IProperty<EnumFacing> FACING;

    protected GAEOven(Material materialIn) {
        super(materialIn);
        super.setRegistryName(ExampleMod.QuickRegName(REGISTRY_NAME))
                .setCreativeTab(CreativeTabs.EXAMPLE_CREATIVE_TAB)
                .setTranslationKey(ExampleMod.QuickTranslationKey(REGISTRY_NAME))
                .setHardness(2.5F)
                .setHarvestLevel("pickaxe", 2);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(FACING,EnumFacing.NORTH));


    }


    @Override
    protected BlockStateContainer createBlockState() {
        FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing facing = EnumFacing.byHorizontalIndex(meta & 3);
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int facing = state.getValue(FACING).getHorizontalIndex();
        return facing;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos,state.withProperty(FACING,placer.getHorizontalFacing().getOpposite()));

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //worldIn.setBlockState(pos, state.cycleProperty(TYPE));

        if (!worldIn.isRemote){
            playerIn.openGui(ExampleMod.instance, GUIHandler.GAEOven,worldIn,pos.getX(),pos.getY(),pos.getZ());
            return true;
        }


        return false;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) { return new GAEOvenTileEntity(); }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new GAEOvenTileEntity();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        IItemHandler up = tileEntity.getCapability(itemHandlerCapability,EnumFacing.UP);
        IItemHandler down = tileEntity.getCapability(itemHandlerCapability,EnumFacing.NORTH);
        IItemHandler side = tileEntity.getCapability(itemHandlerCapability,EnumFacing.DOWN);
        Block.spawnAsEntity(worldIn,pos,up.getStackInSlot(0));
        Block.spawnAsEntity(worldIn,pos,side.getStackInSlot(0));
        Block.spawnAsEntity(worldIn,pos,down.getStackInSlot(0));
        Block.spawnAsEntity(worldIn,pos,down.getStackInSlot(1));
        super.breakBlock(worldIn,pos,state);
    }

}
