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
import sekwah.mods.narutomod.common.NarutoEffects;
import sekwah.mods.narutomod.settings.NarutoSettings;

//
// GuiBuffBar implements a simple status bar at the top of the screen which 
// shows the current buffs/debuffs applied to the character.
//
public class GuiChakraBar extends Gui {
    private static final ResourceLocation NOCHAKRA = new ResourceLocation("narutomod", "textures/gui/nochakra.png");
    private static final ResourceLocation BLUECHAKRA = new ResourceLocation("narutomod", "textures/gui/bluechakra.png");
    private static final ResourceLocation REDCHAKRA = new ResourceLocation("narutomod", "textures/gui/redchakra.png");
    private static final int BUFF_ICON_SIZE = 18;
    private static final int BUFF_ICON_SPACING = BUFF_ICON_SIZE + 2; // 2 pixels between buff icons
    private static final int BUFF_ICON_BASE_U_OFFSET = 0;
    private static final int BUFF_ICON_BASE_V_OFFSET = 198;
    private static final int BUFF_ICONS_PER_ROW = 8;
    private Minecraft mc;
    public GuiChakraBar(Minecraft mc) {
        super();

        // We need this to invoke the render engine.
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
        this.mc.renderEngine.bindTexture(NOCHAKRA);

        GL11.glEnable(GL11.GL_ALPHA_TEST);

        //GL11.glEnable(GL11.GL_LIGHTING);

        int OffsetX = NarutoSettings.chakraBarOffsetX;
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
        }

        drawTexturedModalRect(OffsetX, OffsetY, 0, 0, 90, 100);

        int chakraHeight = (int) ((PlayerClientTickEvent.chakra / PlayerClientTickEvent.maxChakra) * 100);

        if (chakraHeight > 0) {
            // TODO re-add the chakra restore :D
            if (mc.thePlayer.isPotionActive(NarutoEffects.chakraRestore)) {
                this.mc.renderEngine.bindTexture(REDCHAKRA);
                drawTexturedModalRect(OffsetX, OffsetY + (-chakraHeight + 100), 0, (-chakraHeight + 100), 90, chakraHeight);
            } else {
                this.mc.renderEngine.bindTexture(BLUECHAKRA);
                drawTexturedModalRect(OffsetX, OffsetY + (-chakraHeight + 100), 0, (-chakraHeight + 100), 90, chakraHeight);
            }
        }

        // Potential new gui



        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glPopMatrix();
    }
}