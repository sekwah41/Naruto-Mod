package com.sekwah.mods.narutomod.client.gui;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

//
// GuiBuffBar implements a simple status bar at the top of the screen which 
// shows the current buffs/debuffs applied to the character.
//
public class GuiNotificationUpdate extends Gui // make it look like the achievements that show up in the corner
{
    // change it so text is shown, not an achievement

    private static final ResourceLocation achievementTextures = new ResourceLocation("textures/gui/achievement/achievement_background.png");
    private static String achievementGetLocalText;
    private static String achievementStatName;
    private static long achievementTime;
    private static ItemStack itemIcon;
    /**
     * Holds the instance of the game (Minecraft)
     */
    private Minecraft mc;
    /**
     * Holds the latest width scaled to fit the game window.
     */
    private int achievementWindowWidth;
    /**
     * Holds the latest height scaled to fit the game window.
     */
    private int achievementWindowHeight;
    /**
     * Holds a instance of RenderItem, used to draw the achievement icons on screen (is based on ItemStack)
     */
    private RenderItem itemRender;
    private boolean haveAchiement;

    public GuiNotificationUpdate(Minecraft mc) {
        this.itemRender = new RenderItem();

        this.mc = mc;
    }

    /**
     * Queue a taken achievement to be displayed.
     */

    // Use this and also attach to packets with an item with damage ids for set images for server events and messages :D
    //  also attack it to certain events ( will most likely be server side controlled by plugins.

    // GuiNotificationUpdate.queueTakenAchievement("Mission Progress", "20/30 bandits killed!", new ItemStack(NarutoItems.Kunai,1,0));
    public static void queueTakenAchievement(String title, String name, ItemStack itemIcon) {
        GuiNotificationUpdate.achievementGetLocalText = title;
        GuiNotificationUpdate.achievementStatName = name;
        GuiNotificationUpdate.achievementTime = Minecraft.getSystemTime();
        GuiNotificationUpdate.itemIcon = itemIcon;
    }

    /**
     * Queue a information about a achievement to be displayed.
     */

    // possible use for displaying info for longer!
    public static void queueTakenAchievement(String title, String name, ItemStack itemIcon, int duration) {
        GuiNotificationUpdate.achievementGetLocalText = title;
        GuiNotificationUpdate.achievementStatName = name;
        GuiNotificationUpdate.achievementTime = Minecraft.getSystemTime() - duration;
        GuiNotificationUpdate.itemIcon = itemIcon;
    }

    /**
     * Update the display of the achievement window to match the game window.
     */
    private void updateAchievementWindowScale() {
        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        this.achievementWindowWidth = this.mc.displayWidth;
        this.achievementWindowHeight = this.mc.displayHeight;
        ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        this.achievementWindowWidth = scaledresolution.getScaledWidth();
        this.achievementWindowHeight = scaledresolution.getScaledHeight();
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, (double) this.achievementWindowWidth, (double) this.achievementWindowHeight, 0.0D, 1000.0D, 3000.0D);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
    }

    /**
     * Updates the small achievement tooltip window, showing a queued achievement if is needed.
     */
    //public void updateAchievementWindow() old starting
    @SubscribeEvent
    public void onRenderExperienceBar(RenderGameOverlayEvent event) {
        if (this.achievementGetLocalText != null && this.achievementTime != 0L) {
            double d0 = (double) (Minecraft.getSystemTime() - this.achievementTime) / 3000.0D;

            if (!this.haveAchiement && (d0 < 0.0D || d0 > 1.0D)) {
                this.achievementTime = 0L;
            } else {
                this.updateAchievementWindowScale();
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(false);
                double d1 = d0 * 2.0D;

                if (d1 > 1.0D) {
                    d1 = 2.0D - d1;
                }

                d1 *= 4.0D;
                d1 = 1.0D - d1;

                if (d1 < 0.0D) {
                    d1 = 0.0D;
                }

                d1 *= d1;
                d1 *= d1;
                int i = this.achievementWindowWidth - 160;
                int j = 0 - (int) (d1 * 36.0D);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                this.mc.renderEngine.bindTexture(achievementTextures);
                GL11.glDisable(GL11.GL_LIGHTING);
                this.drawTexturedModalRect(i, j, 96, 202, 160, 32);

                if (this.haveAchiement) {
                    this.mc.fontRenderer.drawSplitString(this.achievementStatName, i + 30, j + 7, 120, -1);
                } else {
                    this.mc.fontRenderer.drawString(this.achievementGetLocalText, i + 30, j + 7, -256);
                    this.mc.fontRenderer.drawString(this.achievementStatName, i + 30, j + 18, -1);
                }

                RenderHelper.enableGUIStandardItemLighting();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                GL11.glEnable(GL11.GL_COLOR_MATERIAL);
                GL11.glEnable(GL11.GL_LIGHTING);
                this.itemRender.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), this.itemIcon, i + 8, j + 8);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDepthMask(true);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
            }
        }
        this.mc.renderEngine.bindTexture(new ResourceLocation("textures/gui/icons.png"));
    }
}
