package sekwah.mods.narutomod.client.entity.model.jutsuprojectiles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

import static org.lwjgl.opengl.GL11.*;

public class ModelChibakuTensei extends ModelBase {

    ModelRenderer blackHole;

    public ModelChibakuTensei() {
        blackHole = new ModelRenderer(this, 0,0);
        int boxSize = 8;
        blackHole.addBox( -boxSize, -boxSize, -boxSize, boxSize*2, boxSize*2, boxSize*2);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    /**
     * @param age in ticks
     */
    public void setPosition(float age) {

    }

    public void renderBlackHole(float rotation) {
        glPushMatrix();
        glDisable(GL_LIGHTING);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
        float scale;
        if(rotation < 20 * 6) {
            scale = rotation / 90f;
        }
        else {
            scale = 20 * 6 / 90f;
        }
        scale += Math.cos(rotation / 8) * 0.07f + 0.14f;
        glScalef(scale, scale, scale);
        glPushMatrix();
        glScalef(1.45f,1.45f,1.45f);
        glColor4f(1,1,1,0.13f);

        glDepthMask(false);
        for(int i = 0; i < 5; i++) {
            glRotatef(-rotation * 3, 0f, 1f, 0f);
            glRotatef(60.0F, 0.7071F, 0.0F, 0.7071F);
            this.blackHole.render(0.0625f);
        }
        glDepthMask(true);
        glPopMatrix();

        int modValue = ((int)rotation) % 10;
        if(modValue == 1 || modValue == 3 || modValue == 7) {
            glPushMatrix();
            glScalef(1.1f,1.1f,1.1f);
            glColor4f(1,1,1,0.5f);

            glDepthMask(false);
            for(int i = 0; i < 5; i++) {
                glRotatef(rotation * 3, 0f, 1f, 0f);
                glRotatef(60.0F, 0.7071F, 0.0F, 0.7071F);
                this.blackHole.render(0.0625f);
            }
            glDepthMask(true);
            glPopMatrix();
        }


        glPushMatrix();
        glDepthMask(false);
        glColor4f(0,0,0,1f);

        for(int i = 0; i < 5; i++) {
            glRotatef(rotation * 3, 0f, 1f, 0f);
            glRotatef(60.0F, 0.7071F, 0.0F, 0.7071F);
            this.blackHole.render(0.0625f);
        }
        glDepthMask(true);
        glPopMatrix();
        glColor4f(1,1,1,1);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_LIGHTING);
        glPopMatrix();

    }
}
