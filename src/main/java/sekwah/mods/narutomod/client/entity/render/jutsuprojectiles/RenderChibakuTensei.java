package sekwah.mods.narutomod.client.entity.render.jutsuprojectiles;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import sekwah.mods.narutomod.client.entity.model.jutsuprojectiles.ModelChibakuTensei;
import sekwah.mods.narutomod.common.entity.jutsuprojectiles.EntityChibakuTensei;

import static org.lwjgl.opengl.GL11.*;


public class RenderChibakuTensei extends Render {

    private final ModelChibakuTensei model;

    public RenderChibakuTensei() {
        model = new ModelChibakuTensei();
    }

    @Override
    public void doRender(Entity entity, double posX, double posY, double posZ, float p_76986_8_, float p_76986_9_) {
        this.doRenderChibaku((EntityChibakuTensei) entity, posX, posY, posZ, p_76986_8_, p_76986_9_);
    }

    private void doRenderChibaku(EntityChibakuTensei entity, double posX, double posY, double posZ, float p_76986_8_, float delta) {
        glPushMatrix();
        glTranslated(posX,posY,posZ);
        glTranslatef(0,entity.height / 2,0);
        float rotation = entity.aliveTicks + delta;
        model.renderBlackHole(rotation);
        glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return null;
    }


}
