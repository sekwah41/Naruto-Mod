package sekwah.mods.narutomod.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.lwjgl.opengl.GL11;

import java.awt.*;

//
// GuiBuffBar implements a simple status bar at the top of the screen which 
// shows the current buffs/debuffs applied to the character.
//
public class GuiClanSelectionMenu extends GuiScreen {

    /**
     * The current mouse x coordinate
     */
    protected int mouseX;

    /**
     * The current mouse y coordinate
     */
    protected int mouseY;

    /**
     * Whether the Mouse Button is down or not
     */
    private int isMouseButtonDown;

    public GuiClanSelectionMenu() {

    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui() {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, this.width / 2 + 24, this.height / 2 + 74, 80, 20, I18n.format("naruto.gui.clan.select")));
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton) {
        if (par1GuiButton.id == 1) {
            this.mc.displayGuiScreen((GuiScreen) null);
            this.mc.setIngameFocus();
        }

        super.actionPerformed(par1GuiButton);
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char par1, int par2) {
        if (par2 == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
            this.mc.displayGuiScreen((GuiScreen) null);
            this.mc.setIngameFocus();
        } else {
            super.keyTyped(par1, par2);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int par1, int par2, float par3) {

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        //this.buttonList.add(new GuiButton(1, this.width / 2 + 24, this.height / 2 + 74, 80, 20, I18n.format("gui
        // .done")));


        this.drawDefaultBackground();

        super.drawScreen(par1, par2, par3);

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        this.drawTitle();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        // TODO add buttons for now(for testing) and then code the better menu with left and right and the player view
        // with clan info

    }

    private void drawTitle() {
        int i = (this.width - this.fontRendererObj.getStringWidth(I18n.format("naruto.gui.clan.title"))) / 2;
        int j = (this.height) / 2 - 101;
        this.fontRendererObj.drawString(I18n.format("naruto.gui.clan.title"), i, j, new Color(255, 255, 255).getRGB());
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen() {

    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame() {
        return true;
    }
}
