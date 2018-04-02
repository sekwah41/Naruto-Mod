package sekwah.mods.narutomod.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;

/**
 * Created on 21/12/2015.
 *
 * Extends the normal gui and includes some useful rendering methods, removed the need for textures with powers of two.
 *
 * @author sekwah41
 */
public class NarutoGui extends Gui {

    protected final Minecraft mc;

    protected int textureWidth;

    protected int textureHeight;

    public NarutoGui(Minecraft mc){
        this.mc = mc;
    }

    public void bindTexture(NarutoResource resource){
        this.mc.renderEngine.bindTexture(resource);
        this.textureWidth = resource.defaultWidth;
        this.textureHeight = resource.defaultHeight;
    }

    // Draw whole texture and stretch over the whole object.
    public void drawTextureFull(double x, double y, double width, double height){
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + height, this.zLevel, 0,1);
        tessellator.addVertexWithUV(x + width, y + height, this.zLevel, 1, 1);
        tessellator.addVertexWithUV(x + width, y + 0, this.zLevel, 1, 0);
        tessellator.addVertexWithUV(x + 0, y + 0, this.zLevel, 0, 0);
        tessellator.draw();
    }

    // Doesn't work with flipping the texture as only 1 side is rendered and reversing it with negative width makes it face away i think :P
    public void drawTexture(int x, int y, double u, double v, double width, double height){
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + height, this.zLevel, u / (double) this.textureWidth,(v + height) / (double) this.textureHeight);
        tessellator.addVertexWithUV(x + width, y + height, this.zLevel, (u  + width) / (double) this.textureWidth,(v + height) / (double) this.textureHeight);
        tessellator.addVertexWithUV(x + width, y + 0, this.zLevel, (u + width) / (double) this.textureWidth,v / (double) this.textureHeight);
        tessellator.addVertexWithUV(x + 0, y + 0, this.zLevel, u / (double) this.textureWidth,v / (double) this.textureHeight);
        tessellator.draw();
    }

    public void drawFlippedTexture(int x, int y, double u, double v, double width, double height){
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + 0, y + height, this.zLevel, (u  + width) / (double) this.textureWidth,(v + height) / (double) this.textureHeight);
        tessellator.addVertexWithUV(x + width, y + height, this.zLevel, u / (double) this.textureWidth,(v + height) / (double) this.textureHeight);
        tessellator.addVertexWithUV(x + width, y + 0, this.zLevel, u / (double) this.textureWidth,v / (double) this.textureHeight);
        tessellator.addVertexWithUV(x + 0, y + 0, this.zLevel, (u  + width) / (double) this.textureWidth,v / (double) this.textureHeight);
        tessellator.draw();
    }

}
