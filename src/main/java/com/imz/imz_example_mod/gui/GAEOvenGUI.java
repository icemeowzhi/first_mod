package com.imz.imz_example_mod.gui;

import com.imz.imz_example_mod.ExampleMod;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GAEOvenGUI extends GuiContainer {

    private  static  final ResourceLocation TEXTURE = new ResourceLocation(ExampleMod.MODID + ":textures/gui/container/gae_oven.png");

    public GAEOvenGUI(Container inventorySlotsIn) {
        super(inventorySlotsIn);
        this.xSize = 176;
        this.ySize = 166;
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

        int barFuelHeight = 16;
        float percentFuel = (float)((GAEOvenContainer)this.inventorySlots).getFuelTime() / (float) ((GAEOvenContainer)this.inventorySlots).getTotalFuelTime();
        int barFuelWidth = Math.round(percentFuel * 16) ;


        //System.out.println(((GAEOvenContainer)this.inventorySlots).getFuelTime());
        //System.out.println(((GAEOvenContainer)this.inventorySlots).getTotalFuelTime());
        //System.out.println(((GAEOvenContainer)this.inventorySlots).getFuelTime());
        //System.out.println(percent);
        //System.out.println(barWidth);
        //int barWidth = Math.round(0.033f * ((GAEOvenContainer)this.inventorySlots).getFuelTime());

        this.drawTexturedModalRect(left + 36,top + 35,176,31,barFuelWidth,barFuelHeight);


        int barBurnTimeHeight = 17;
        float percentBurnTime = (float)((GAEOvenContainer)this.inventorySlots).getBurnTime() / (float) ((GAEOvenContainer)this.inventorySlots).getTotalBurnTime();
        int barBurnTimeWidth = Math.round(percentBurnTime * 24) ;

        this.drawTexturedModalRect(left + 59,top + 35,176,14,barBurnTimeWidth,barBurnTimeHeight);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String text = I18n.format("tile.gae_oven.gui.name");
        this.drawCenteredString(this.fontRenderer,text,(this.xSize)/2,6,0x00404040);
    }
}
