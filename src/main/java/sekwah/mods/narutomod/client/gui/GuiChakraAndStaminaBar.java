package sekwah.mods.narutomod.client.gui;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
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
public class GuiChakraAndStaminaBar extends /*Gui*/NarutoGui {
    //private static final ResourceLocation BARSTEXTURE = new ResourceLocation("narutomod", "textures/gui/newChakraBars.png");

    //private static final ResourceLocation DEFAULTTEXTURE = new ResourceLocation("narutomod", "textures/gui/defaultBars.png");

    /*private static final ResourceLocation DEFAULTTEXTURE = new ResourceLocation("narutomod", "textures/gui/chakrabars/DefaultBars.png");
    private static final ResourceLocation KATANATEXTURE = new ResourceLocation("narutomod", "textures/gui/chakrabars/Katana.png");
    private static final ResourceLocation KUBITEXTURE = new ResourceLocation("narutomod", "textures/gui/chakrabars/Kubi.png");
    private static final ResourceLocation KUNAITEXTURE = new ResourceLocation("narutomod", "textures/gui/chakrabars/Kunai.png");
    private static final ResourceLocation SCEPTERTEXTURE = new ResourceLocation("narutomod", "textures/gui/chakrabars/Scepter.png");
    private static final ResourceLocation SCROLLTEXTURE = new ResourceLocation("narutomod", "textures/gui/chakrabars/Scroll.png");
    private static final ResourceLocation UNNAMEDTEXTURE = new ResourceLocation("narutomod", "textures/gui/chakrabars/unnamedbar.png");*/

    private static final int barTextureWidth = 100;

    private static final int barTextureHeight = 44;

    private static final ResourceLocation DEFAULTTEXTUREOLD = new ResourceLocation("narutomod", "textures/gui/defaultBars.png");

    private static final NarutoResource DEFAULTTEXTURE = new NarutoResource("textures/gui/chakrabars/DefaultBar.png", barTextureWidth, barTextureHeight);
    private static final NarutoResource KATANATEXTURE = new NarutoResource("textures/gui/chakrabars/Katana.png", barTextureWidth, barTextureHeight);
    private static final NarutoResource KUBITEXTURE = new NarutoResource("textures/gui/chakrabars/Kubi.png", barTextureWidth, barTextureHeight);
    private static final NarutoResource KUNAITEXTURE = new NarutoResource("textures/gui/chakrabars/Kunai.png", barTextureWidth, barTextureHeight);
    private static final NarutoResource SCEPTERTEXTURE = new NarutoResource("textures/gui/chakrabars/Scepter.png", barTextureWidth, barTextureHeight);
    private static final NarutoResource SCROLLTEXTURE = new NarutoResource("textures/gui/chakrabars/Scroll.png", barTextureWidth, barTextureHeight);
    private static final NarutoResource UNNAMEDTEXTURE = new NarutoResource("textures/gui/chakrabars/unnamedbar.png", barTextureWidth, barTextureHeight);
    // Just to check where the full squares are
    /*private static final NarutoResource NULLTEXTURE = new NarutoResource("textures/gui/chakrabars/null.png", barTextureWidth, barTextureHeight);*/

    private static final NarutoResource[] barDesigns = {DEFAULTTEXTURE, KATANATEXTURE, KUBITEXTURE, KUNAITEXTURE, SCEPTERTEXTURE, SCROLLTEXTURE, UNNAMEDTEXTURE/*, NULLTEXTURE*/};

    // These are needed so that 0 and 100% chakra are at either end of the bar
    private static final int[] barWidths = {86,94,86,86,86,86,74};

    private static final int[] barXOffset = {6,5,3,5,5,6,3};

    //private final Minecraft mc;

    public GuiChakraAndStaminaBar(Minecraft mc) {
        super(mc);
        //this.mc = mc;
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

        // Get the texture 1 lower than the current bardesign value from the array

        // TODO make the chakra bar select the textures from a generic place so its just the bind texture that you are changing between the styles
        //this.mc.renderEngine.bindTexture(barDesigns[NarutoSettings.chakraBarDesign - 1]);
        bindTexture(barDesigns[NarutoSettings.chakraBarDesign - 1]);

        GL11.glColor3f(1F, 1F, 1F);
        //drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 - 190, scaledresolution.getScaledHeight() - 120, 0, 22, 100, 22);
        drawTexture(scaledresolution.getScaledWidth() / 2 - 192, scaledresolution.getScaledHeight()- 22, 0, 22, 100, 22);

        drawFlippedTexture(scaledresolution.getScaledWidth() / 2 + 92, scaledresolution.getScaledHeight() - 22, 0, 22, 100, 22);


        if (mc.thePlayer.isPotionActive(NarutoEffects.chakraRestore)) {
            GL11.glColor3f(1F, 0.5F, 0.1F);
        }
        else{
            //GL11.glColor3f(0.08F, 0.7F, 1F);
            // TODO change chakra to hue rather than individual sliders :)
            GL11.glColor3f(NarutoSettings.chakraRedFloat(), NarutoSettings.chakraGreenFloat(), NarutoSettings.chakraBlueFloat());
        }
        int chakraWidth = barXOffset[NarutoSettings.chakraBarDesign - 1] + (int) (barWidths[NarutoSettings.chakraBarDesign - 1] * (PlayerClientTickEvent.chakra / PlayerClientTickEvent.maxChakra));
        drawTexture(scaledresolution.getScaledWidth() / 2 - 192 + (100-chakraWidth), scaledresolution.getScaledHeight() - 22, (100-chakraWidth), 0, chakraWidth, 22);
        //drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 - 189 + chakraWidth, scaledresolution.getScaledHeight() - 11, 1 + chakraWidth, 238, 94 - chakraWidth, 5);

        GL11.glColor3f(0F, 0.73F, 0F);
        int staminaWidth = barXOffset[NarutoSettings.chakraBarDesign - 1] + (int) (barWidths[NarutoSettings.chakraBarDesign - 1] * (PlayerClientTickEvent.stamina / PlayerClientTickEvent.maxStamina));
        drawFlippedTexture(scaledresolution.getScaledWidth() / 2 + 92, scaledresolution.getScaledHeight() - 22, (100-staminaWidth), 0, staminaWidth, 22);
        //drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 + 95, scaledresolution.getScaledHeight() - 11, 160, 238, staminaWidth, 5);
        /*this.mc.renderEngine.bindTexture(DEFAULTTEXTUREOLD);

        // TODO make the chakra bar select the textures from a generic place so its just the bind texture that you are changing between the styles
        int chakraWidth = (int) ((1 - PlayerClientTickEvent.chakra / PlayerClientTickEvent.maxChakra) * 94);
        GL11.glColor3f(1F, 1F, 1F);
        drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 - 190, scaledresolution.getScaledHeight() - 12, 0, 246, 96, 7);

        int staminaWidth = (int) ((PlayerClientTickEvent.stamina / PlayerClientTickEvent.maxStamina) * 94);
        drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 + 94, scaledresolution.getScaledHeight() - 12, 159, 246, 97, 7);


        if (mc.thePlayer.isPotionActive(NarutoEffects.chakraRestore)) {
            GL11.glColor3f(1F, 0.5F, 0.1F);
        }
        else{
            //GL11.glColor3f(0.08F, 0.7F, 1F);
            GL11.glColor3f(NarutoSettings.chakraRedFloat(), NarutoSettings.chakraGreenFloat(), NarutoSettings.chakraBlueFloat());
        }
        drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 - 189 + chakraWidth, scaledresolution.getScaledHeight() - 11, 1 + chakraWidth, 238, 94 - chakraWidth, 5);

        GL11.glColor3f(0F, 0.73F, 0F);
        drawTexturedModalRect(scaledresolution.getScaledWidth() / 2 + 95, scaledresolution.getScaledHeight() - 11, 160, 238, staminaWidth, 5);
*/

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

    public static int getDesignCount(){
        return barDesigns.length;
    }
}