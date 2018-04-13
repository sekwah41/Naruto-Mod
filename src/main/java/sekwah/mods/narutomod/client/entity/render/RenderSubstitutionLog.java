package sekwah.mods.narutomod.client.entity.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.common.entity.EntitySubstitutionLog;
import sekwah.mods.narutomod.common.entity.models.jutsuprojectiles.ModelSubstitution;

@SideOnly(Side.CLIENT)
public class RenderSubstitutionLog extends RenderLiving {
    private static final ResourceLocation Log_Texture = new ResourceLocation("narutomod:textures/entitys/Substitution.png");  //refers to:assets/yourmod/textures/entity/yourtexture.png

    public RenderSubstitutionLog() {
        super(new ModelSubstitution(), 0.0F); // model class, shadow size
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return Log_Texture;
    }

    protected void rotateCorpse(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        EntitySubstitutionLog entity = (EntitySubstitutionLog) par1EntityLivingBase;
        GL11.glRotatef(180.0F - par3, 0.0F, 1.0F, 0.0F);

        if (entity.destuctionTime > 0) {
            float f3 = ((float) entity.destuctionTime + par4 - 1.0F) / 20.0F * 1.6F;
            f3 = MathHelper.sqrt_float(f3);

            if (f3 > 1.0F) {
                f3 = 1.0F;
            }

            GL11.glRotatef(f3 * this.getDeathMaxRotation(par1EntityLivingBase), 0.0F, 0.0F, 1.0F);
        }
    }
}
