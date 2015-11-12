package com.sekwah.mods.narutomod.blocks.rendereres;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.sekwah.mods.narutomod.blocks.models.ModelBonsai;
import com.sekwah.mods.narutomod.blocks.tileentity.TileEntityBase;

public class TileEntityBonsaiRenderer extends TileEntitySpecialRenderer {

    public final ModelBonsai model = new ModelBonsai();

    //This method is called when minecraft renders a tile entity
    public void renderTileEntityAt(TileEntity tileEntity, double d, double d1, double d2, float f) {
        /**GL11.glPushMatrix();
         //This will move our renderer so that it will be on proper place in the world
         GL11.glTranslatef((float)d, (float)d1, (float)d2);
         TileEntityBonsai tileEntityYour = (TileEntityBonsai)tileEntity;
         GL11.glPopMatrix();*/

        this.renderBonsaiBlock((TileEntityBase) tileEntity, d, d1, d2, f);
    }

    // this method isnt called yet sadly, make sure its called before fixing this code
    public void renderBonsaiBlock(TileEntityBase par1TileEntityBonsai, double par2, double par4, double par6, float par8) {
        //System.out.println("renderTest");
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2 + 0.5F, (float) par4, (float) par6 + 0.5F);
        int dir = par1TileEntityBonsai.getBlockMetadata();
        GL11.glRotatef(180, 1F, 0F, 0F);
        GL11.glRotatef(dir * (45F), 0F, 1F, 0F);
        //GL11.glTranslatef(0.5F, -1.5F, -0.4F);

        this.bindTexture(new ResourceLocation("narutomod:textures/blocks/bonsaitreeTexture.png"));
        this.model.render();

        GL11.glPopMatrix();
    }
}