package com.imz.imz_example_mod.gui;

import com.imz.imz_example_mod.ExampleMod;
import com.imz.imz_example_mod.Tag;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlayerTagViewGUI extends GuiContainer implements IGUIComponents{

    private final BlockPos pos;
    private final World world;
    private final EntityPlayer player;
    private  static  final ResourceLocation TEXTURE = new ResourceLocation(ExampleMod.MODID + ":textures/gui/player_tag_view.png");
    private int leftByGuiX;
    private int topByGuiY;

    public PlayerTagViewGUI(Container inventorySlotsIn) {
        super(inventorySlotsIn);
        this.xSize = 151;
        this.ySize = 154;
        this.pos = ((PlayerTagViewContainer) inventorySlotsIn).getPos();
        this.world = ((PlayerTagViewContainer)inventorySlotsIn).getWorld();
        this.player = ((PlayerTagViewContainer)inventorySlotsIn).getPlayer();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        leftByGuiX = (this.width - this.xSize) / 2;
        topByGuiY = (this.height - this.ySize) / 2;
        GlStateManager.color(1.0f,1.0f,1.0f,1.0f);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(leftByGuiX,topByGuiY,0,0,this.xSize,this.ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawDefaultBackground();
        super.drawScreen(mouseX,mouseY,partialTicks);
        super.renderHoveredToolTip(mouseX,mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        Tag[][] tags = new Tag[][]
        {
                new Tag[]{new Tag("apple", "1", "epic"),
                        new Tag("beef", "2", "epic"),
                        new Tag("salted_fish", "3", "epic"),
                        new Tag("brick", "4", "rare"),
                        new Tag("mutton", "5", "normal"),
                        new Tag("potato", "6", "normal")}
        };

        String[] titleName = new String[]{"food"};


        drawTagGUIFormatted(tags,titleName);
    }



    private void drawTagGUIFormatted(Tag[][] allTag, String[] allTitleName){

        //TODO:拉链
        int nextComponentX = firstTitleX;
        int nextComponentY = firstTitleY;
        int entryInGUIX = 1;
        int entryInGUIY = 1;
        int absoluteTitleX = 161;
        int absoluteTitleY = 0;
        int absoluteQMarkX = 151;
        int absoluteQMarkY = 0;
        int absoluteEpicTagX = 151;
        int absoluteEpicTagY = 10;
        int absoluteRareTagX = 151;
        int absoluteRareTagY = 21;
        int absoluteNormalTagX = 151;
        int absoluteNormalTagY = 32;
        int absoluteEntryX = 0;
        int absoluteEntryY = 154;
        String titleName;


        for (int i = 0; i < allTag.length; i++) {
            if (allTitleName.length >= i+1){
                titleName = allTitleName[i];
            }else {
                titleName = "Unknown";
            }

            this.drawTexturedModalRect(nextComponentX,nextComponentY,absoluteTitleX,absoluteTitleY,titleX,titleY); //title
            this.drawCenteredString(this.fontRenderer,titleName,getMidStringXByComponent(titleX,nextComponentX),getMidStringYByComponent(titleY,nextComponentY,-2),0x00c3e7f7);//titleString
            GlStateManager.color(1.0f,1.0f,1.0f,1.0f);
            this.mc.getTextureManager().bindTexture(TEXTURE);
            nextComponentX += titleToQMarkX;
            this.drawTexturedModalRect(nextComponentX,nextComponentY,absoluteQMarkX,absoluteQMarkY,qMarkX,qMarkY); //Qmark
            nextComponentX -= titleToQMarkX;
            nextComponentY += titleToEntryY;
            for (int j = 0; j < allTag[i].length; j++) {
                this.drawTexturedModalRect(nextComponentX,nextComponentY,absoluteEntryX,absoluteEntryY,entryX,entryY); //entry
                entryInGUIX++;
                if (entryInGUIX>2){
                    entryInGUIY ++;
                    entryInGUIX = 1;
                }
                switch (allTag[i][j].getRarity()){
                    case "normal":
                        this.drawTexturedModalRect(nextComponentX+2,nextComponentY+2,absoluteNormalTagX,absoluteNormalTagY,tagX,tagY); //tag
                        break;
                    case "rare":
                        this.drawTexturedModalRect(nextComponentX+2,nextComponentY+2,absoluteRareTagX,absoluteRareTagY,tagX,tagY); //tag
                        break;
                    case "epic":
                        this.drawTexturedModalRect(nextComponentX+2,nextComponentY+2,absoluteEpicTagX,absoluteEpicTagY,tagX,tagY); //tag
                        break;
                    default:
                        this.drawTexturedModalRect(nextComponentX+2,nextComponentY+2,absoluteNormalTagX,absoluteNormalTagY,tagX,tagY); //tag
                }
                this.drawCenteredString(this.fontRenderer,allTag[i][j].getTagName(),getMidStringXByComponent(entryX,nextComponentX,tagX+1),getMidStringYByComponent(entryY,nextComponentY),0x00c3e7f7);//tagString
                GlStateManager.color(1.0f,1.0f,1.0f,1.0f);
                this.mc.getTextureManager().bindTexture(TEXTURE);
                if (entryInGUIX - 1 == 1){
                    nextComponentX += entriesX;
                }else{
                    nextComponentX -= entriesX;
                    nextComponentY += entriesY;
                }

            }
            if (entryInGUIX - 1 == 1){
                nextComponentY += entryToDividerY;
            }else {
                nextComponentX -= entriesX;
                nextComponentY += entryToDividerY;
            }
            //drawTagViewGUILine(nextComponentX,nextComponentY);
            //TODO

            nextComponentY += dividerToNextTitleY;

        }
    }

    private static int getMidStringXByComponent(int componentX , int componentAbsoluteX , int offsetX){
        return Math.round(((float)(componentX - offsetX)/2) + offsetX + componentAbsoluteX);
    }
    private static int getMidStringYByComponent(int componentY , int componentAbsoluteY , int offsetY){
        return Math.round(((float)(componentY - offsetY)/4) + offsetY + componentAbsoluteY);
    }
    private static int getMidStringXByComponent(int componentX , int componentAbsoluteX){
        return Math.round(componentAbsoluteX + (float)(componentX / 2)) ;
    }
    private static int getMidStringYByComponent(int componentY , int componentAbsoluteY){
        return Math.round(componentAbsoluteY + (float)(componentY / 4)) ;
    }
}
