package sekwah.mods.narutomod.client.gui;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.client.PlayerClientTickEvent;
import sekwah.mods.narutomod.generic.NarutoEffects;

//
// GuiBuffBar implements a simple status bar at the top of the screen which 
// shows the current buffs/debuffs applied to the character.
//
public class GuiChakraAndStaminaBar extends Gui {
    private static final ResourceLocation BARSTEXTURE = new ResourceLocation("narutomod", "textures/gui/newChakraBars.png");

    private static final ResourceLocation DEFAULTTEXTURE = new ResourceLocation("narutomod", "textures/gui/defaultBars.png");

    private Minecraft mc;
    public GuiChakraAndStaminaBar(Minecraft mc) {
        super();

        this.mc = mc;
    }

    //
    // This event is called by GuiIngameForge during each frame by
    // GuiIngameForge.pre() and GuiIngameForce.post().
    //


    @SubscribeEvent
    public void onRenderExperienceBar(RenderGameOverlayEvent event) {
        //
        // We draw after the ExperienceBar has drawn.  The event raised by GuiIngameForge.pre()
        // will return true from isCancelable.  If you call event.setCanceled(true) in
        // that case, the portion of rendering which this event represents will be canceled.
        // We want to draw *after* the experience bar is drawn, so we make sure isCancelable() returns
        // false and that the eventType represents the ExperienceBar event.
        if (event.isCancelable() || event.type != ElementType.EXPERIENCE) {
            return;
        }

        ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);

        // TODO add more styles of charka bar, the number value for below(possibly a list of active effects(maybe in a different gui)) and the number of substitutions left (and the cooldown)

        // Starting position for the buff bar - 2 pixels from the top left corner.
        //int xPos = 2;
        //int yPos = 2;
        GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        GL11.glEnable(GL11.GL_ALPHA_TEST);

        //GL11.glEnable(GL11.GL_LIGHTING);

        /*int OffsetX = NarutoSettings.chakraBarOffsetX;
        int OffsetY = NarutoSettings.chakraBarOffsetY;

        if (NarutoSettings.chakraGUICorner == 2) {
            OffsetX = scaledresolution.getScaledWidth() - NarutoSettings.chakraBarOffsetX - 90;
            OffsetY = NarutoSettings.chakraBarOffsetY;
        } else if (NarutoSettings.chakraGUICorner == 3) {
            OffsetX = NarutoSettings.chakraBarOffsetX;
            OffsetY = scaledresolution.getScaledHeight() - NarutoSettings.chakraBarOffsetY - 100;
        } else if (NarutoSettings.chakraGUICorner == 4) {
            OffsetX = scaledresolution.getScaledWidth() - NarutoSettings.chakraBarOffsetX - 90;
            OffsetY = scaledresolution.getScaledHeight() - NarutoSettings.chakraBarOffsetY - 100;
        }*/

        // Hiro base charka bar


        // need to add hue but yea here is sumulating blue.

        this.mc.renderEngine.bindTexture(DEFAULTTEXTURE);

        int chakraWidth = (int) ((1 - PlayerClientTickEvent.chakra / PlayerClientTickEvent.maxChakra) * 94);
        GL11.glColor3f(1F, 1F, 1F);
        drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 - 190, scaledresolution.getScaledHeight() - 12, 0, 246, 96, 7);

        if (mc.thePlayer.isPotionActive(NarutoEffects.chakraRestore)) {
            GL11.glColor3f(1F, 0.5F, 0.1F);
        }
        else{
            GL11.glColor3f(0.08F, 0.7F, 1F);
        }
        drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 - 189 + chakraWidth, scaledresolution.getScaledHeight() - 11, 1 + chakraWidth, 238, 94 - chakraWidth, 5);

        int staminaWidth = (int) ((PlayerClientTickEvent.stamina / PlayerClientTickEvent.maxStamina) * 94);
        GL11.glColor3f(1F, 1F, 1F);
        drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 + 94, scaledresolution.getScaledHeight() - 12, 159, 246, 97, 7);
        GL11.glColor3f(0F, 0.73F, 0F);
        drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 + 95, scaledresolution.getScaledHeight() - 11, 160, 238, staminaWidth, 5);


        // my old terrible texture

        //drawTexturedModalRect(0, scaledresolution.getScaledHeight() - 97, 0, 159, 12, 97);

        /*drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 - 189, scaledresolution.getScaledHeight() - 12, 0, 244, 97, 12);
        drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 - 169, scaledresolution.getScaledHeight() - 9, 20, 237, 74, 6);

        drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 + 92, scaledresolution.getScaledHeight() - 12, 159, 244, 97, 12);
        drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 + 95, scaledresolution.getScaledHeight() - 9, 162, 237, 74, 6);*/

        //drawTexturedModalRect(scaledresolution.getScaledWidth() - 12, scaledresolution.getScaledHeight() - 97, 244, 159, 12, 97);

        /*int chakraHeight = (int) ((PlayerClientTickEvent.chakra / PlayerClientTickEvent.maxChakra) * 100);

        if (chakraHeight > 0) {
            // TODO re-add the chakra restore :D
            if (mc.thePlayer.isPotionActive(NarutoEffects.chakraRestore)) {
                drawTexturedModalRect(OffsetX, OffsetY + (-chakraHeight + 100), 0, (-chakraHeight + 100), 90, chakraHeight);
            } else {
                drawTexturedModalRect(OffsetX, OffsetY + (-chakraHeight + 100), 0, (-chakraHeight + 100), 90, chakraHeight);
            }
        }*/

        // Potential new gui



        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glPopMatrix();
    }
}