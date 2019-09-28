package sekwah.mods.narutomod.client.item.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.client.ClientProxy;
import sekwah.mods.narutomod.client.item.model.weapon.ModelScroll;


/**
 * Registered in {@link ClientProxy#registerCustomItems()}
 */
public class RenderItemScroll implements IItemRenderer {

    protected ModelScroll scrollModel;

    public RenderItemScroll() {
        scrollModel = new ModelScroll();
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
                // but will be usefull for future model

                GL11.glRotatef(-119F, 0F, 0F, 1F);

                GL11.glTranslatef(-0.55F, 0.35F, 0F);


                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("narutomod", "textures/items/models/Scroll.png"));

                scrollModel.render((Entity) data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);

                GL11.glPopMatrix();
            }
            case EQUIPPED_FIRST_PERSON: {
                GL11.glPushMatrix();

                float scale = 1F;

                GL11.glScalef(scale, scale, scale);

                // use these at some point, forgot to for samehada
                // but will be usefull for future model

                GL11.glRotatef(-119F, 0F, 0F, 1F);

                GL11.glTranslatef(-0.55F, 0.35F, 0F);

                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("narutomod", "textures/items/models/Scroll.png"));

                scrollModel.render((Entity) data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);

                GL11.glPopMatrix();
            }
            default:
                break;
        }

    }

}
