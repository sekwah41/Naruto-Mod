package sekwah.mods.narutomod.common.items.itemrenderers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.common.items.itemmodels.ModelKubikiribocho;


public class RenderItemKubikiribocho implements IItemRenderer {

    protected ModelKubikiribocho weaponModel;

    public RenderItemKubikiribocho() {
        weaponModel = new ModelKubikiribocho();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        switch (type) {
            case EQUIPPED:
                return true;
            case EQUIPPED_FIRST_PERSON:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch (type) {
            case EQUIPPED: {
                GL11.glPushMatrix();

                float scale = 1F;

                GL11.glScalef(scale, scale, scale);

                // use these at some point, forgot to for samehada
                // but will be usefull for future models

                GL11.glRotatef(-119F, 0F, 0F, 1F);

                GL11.glTranslatef(-0.55F, 0.35F, 0F);


                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("narutomod", "textures/items/models/Kubi.png"));

                Entity entity = (Entity) data[1];

                if(entity != null && entity.isSprinting()){
                    GL11.glRotatef(-110F, 0F, 0F, 1F);
                    GL11.glTranslatef(-0.1F, -0.25F, 0);
                }

                weaponModel.render(entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);

                GL11.glPopMatrix();
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                GL11.glPushMatrix();

                float scale = 1F;

                GL11.glScalef(scale, scale, scale);

                // use these at some point, forgot to for samehada
                // but will be usefull for future models

                GL11.glRotatef(-119F, 0F, 0F, 1F);

                GL11.glTranslatef(-0.55F, 0.35F, 0F);

                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("narutomod", "textures/items/models/Kubi.png"));

                weaponModel.render((Entity) data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);

                GL11.glPopMatrix();
                break;
            }
            default:
                break;
        }

    }

}
