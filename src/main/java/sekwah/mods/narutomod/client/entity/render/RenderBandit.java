package sekwah.mods.narutomod.client.entity.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.common.entity.EntityBandit;
import sekwah.mods.narutomod.common.entity.models.ModelBandit;

@SideOnly(Side.CLIENT)
public class RenderBandit extends RenderNinjaBiped {

    public RenderBandit() {
        super(new ModelBandit(), 0.5F);
    }

    protected void func_82422_c() {
        GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
    }

    protected ResourceLocation getTexture(EntityBandit par1EntityBandit) {
        if (par1EntityBandit.getSkin() == 1) {
            return new ResourceLocation("narutomod:textures/entitys/bandit.png");
        } else if (par1EntityBandit.getSkin() == 2) {
            return new ResourceLocation("narutomod:textures/entitys/bandit2.png"); // narutomod:textures/entitys/bandit2.png
        } else {
            return new ResourceLocation("narutomod:textures/entitys/bandit.png");
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.getTexture((EntityBandit) par1Entity);
    }
}
