package sekwah.mods.narutomod.client.block.rendereres;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.client.block.models.ModelBonsai;

public class TileEntityBonsaiRenderer extends TileEntitySpecialRenderer {

    public final ModelBonsai model = new ModelBonsai();
    private final ResourceLocation texture;

    public TileEntityBonsaiRenderer(ResourceLocation texture) {
        this.texture = texture;
    }

    //This method is called when minecraft renders a tile entity
    public void renderTileEntityAt(TileEntity tileEntity, double d, double d1, double d2, float f) {
        this.renderBonsaiBlock(tileEntity, d, d1, d2, f);
    }

    // this method isnt called yet sadly, make sure its called before fixing this code
    public void renderBonsaiBlock(TileEntity par1TileEntityBonsai, double par2, double par4, double par6, float par8) {

        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2 + 0.5F, (float) par4, (float) par6 + 0.5F);
        int dir = par1TileEntityBonsai.getBlockMetadata();
        GL11.glRotatef(180, 1F, 0F, 0F);
        GL11.glRotatef(dir * (45F), 0F, 1F, 0F);

        this.bindTexture(texture);
        this.model.render();

        GL11.glPopMatrix();

    }
}