package com.imz.imz_example_mod.gui;

import com.imz.imz_example_mod.ExampleMod;
import com.imz.imz_example_mod.block.GADynamic;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;


@SideOnly(Side.CLIENT)
public class GADynamicGUI extends GuiContainer {

    private  static  final ResourceLocation TEXTURE = new ResourceLocation(ExampleMod.MODID + ":textures/gui/container/dynamic_machine.png");
    private static final int BTN_1 = 0;
    private static final int BTN_2 = 1;
    private static final int BTN_3 = 2;
    private static final int BTN_4 = 3;
    private final BlockPos pos;
    private final World world;
    private final EntityPlayer player;

    public GADynamicGUI(Container inventorySlotsIn) {
        super(inventorySlotsIn);
        this.xSize = 176;
        this.ySize = 166;
        this.pos = ((GADynamicContainer) inventorySlotsIn).getPos();
        this.world = ((GADynamicContainer)inventorySlotsIn).getWorld();
        this.player = ((GADynamicContainer)inventorySlotsIn).getPlayer();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        super.drawScreen(mouseX,mouseY,partialTicks);
        super.renderHoveredToolTip(mouseX,mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        int left = (this.width - this.xSize) / 2;
        int top = (this.height - this.ySize) / 2;
        GlStateManager.color(1.0f,1.0f,1.0f,1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(left,top,0,0,this.xSize,this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String text = I18n.format("tile.dynamic_machine.gui.name");
        //int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        //this.drawCenteredString(this.fontRenderer,text,(this.xSize)/2,6,0x00404040);
        this.drawCenteredString(this.fontRenderer,text,118,35,0x00404040);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        IBlockState state = world.getBlockState(pos);
        switch (button.id){
            case BTN_1:
                world.setBlockState(pos, state.withProperty(GADynamic.TYPE, GADynamic.EnumMachineType.M1));
                player.sendMessage(new TextComponentString(I18n.format("texture_set1")));
                break;
            case BTN_2:
                world.setBlockState(pos, state.withProperty(GADynamic.TYPE, GADynamic.EnumMachineType.M2));
                player.sendMessage(new TextComponentString(I18n.format("texture_set2")));
                break;
            case BTN_3:
                world.setBlockState(pos, state.withProperty(GADynamic.TYPE, GADynamic.EnumMachineType.M3));
                player.sendMessage(new TextComponentString(I18n.format("texture_set3")));
                break;
            case BTN_4:
                world.setBlockState(pos, state.withProperty(GADynamic.TYPE, GADynamic.EnumMachineType.M4));
                player.sendMessage(new TextComponentString(I18n.format("texture_set4")));
                break;
            default:
                super.actionPerformed(button);
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.buttonList.add(new GuiButton(BTN_1, offsetX + 70, offsetY + 47, 22, 22, "")
        {

            @Override
            public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
                if (this.visible)
                {
                    GlStateManager.color(1.0F, 1.0F, 1.0F);

                    mc.getTextureManager().bindTexture(TEXTURE);
                    int x = mouseX - this.x, y = mouseY - this.y;

                    if (x >= 0 && y >= 0 && x < this.width && y < this.height)
                    {
                        this.drawTexturedModalRect(this.x, this.y, 44, 166, this.width, this.height);
                    }
                    else
                    {
                        this.drawTexturedModalRect(this.x, this.y, 0, 166, this.width, this.height);
                    }
                }
            }

        });

        this.buttonList.add(new GuiButton(BTN_2, offsetX + 95, offsetY + 47, 22, 22, "")
        {
            @Override
            public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
            {
                if (this.visible)
                {
                    GlStateManager.color(1.0F, 1.0F, 1.0F);

                    mc.getTextureManager().bindTexture(TEXTURE);
                    int x = mouseX - this.x, y = mouseY - this.y;

                    if (x >= 0 && y >= 0 && x < this.width && y < this.height)
                    {
                        this.drawTexturedModalRect(this.x, this.y, 44, 166, this.width, this.height);
                    }
                    else
                    {
                        this.drawTexturedModalRect(this.x, this.y, 0, 166, this.width, this.height);
                    }
                }
            }
        });

        this.buttonList.add(new GuiButton(BTN_3, offsetX + 120, offsetY + 47, 22, 22, "")
        {
            @Override
            public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
            {
                if (this.visible)
                {
                    GlStateManager.color(1.0F, 1.0F, 1.0F);

                    mc.getTextureManager().bindTexture(TEXTURE);
                    int x = mouseX - this.x, y = mouseY - this.y;

                    if (x >= 0 && y >= 0 && x < this.width && y < this.height)
                    {
                        this.drawTexturedModalRect(this.x, this.y, 44, 166, this.width, this.height);
                    }
                    else
                    {
                        this.drawTexturedModalRect(this.x, this.y, 0, 166, this.width, this.height);
                    }
                }
            }
        });

        this.buttonList.add(new GuiButton(BTN_4, offsetX + 145, offsetY + 47, 22, 22, "")
        {
            @Override
            public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
            {
                if (this.visible)
                {
                    GlStateManager.color(1.0F, 1.0F, 1.0F);

                    mc.getTextureManager().bindTexture(TEXTURE);
                    int x = mouseX - this.x, y = mouseY - this.y;

                    if (x >= 0 && y >= 0 && x < this.width && y < this.height)
                    {
                        this.drawTexturedModalRect(this.x, this.y, 44, 166, this.width, this.height);
                    }
                    else
                    {
                        this.drawTexturedModalRect(this.x, this.y, 0, 166, this.width, this.height);
                    }
                }
            }
        });


    }
}







