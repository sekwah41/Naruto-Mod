package sekwah.mods.narutomod.client.item.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
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
public class RenderCustomItemModel implements IItemRenderer {

    private final ResourceLocation texture;
    private ModelBase customModel;

    public RenderCustomItemModel(ModelBase model, ResourceLocation texture) {
        this.customModel = model;
        this.texture = texture;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        switch (type) {
            case EQUIPPED:
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
            case EQUIPPED:
                this.renderModel(type, item, true, data);
                break;
            case EQUIPPED_FIRST_PERSON:
                this.renderModel(type, item, false, data);
                break;
            default:
                break;
        }

    }

    private void renderModel(ItemRenderType type, ItemStack item, boolean cullFace, Object... data) {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(cullFace ? GL11.GL_FRONT : GL11.GL_BACK);
        GL11.glPushMatrix();

        // use these at some point, forgot to for samehada
        // but will be usefull for future model

        GL11.glRotatef(-136F, 0F, 0F, 1F);

        GL11.glTranslatef(-0.55F, 0.35F, 0F);

        GL11.glRotatef(90F, 0F, 1F, 0F);

        GL11.glTranslatef(0.04f, -0.2F, -0.15F);

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);

        customModel.render((Entity) data[1], 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);

        GL11.glCullFace(GL11.GL_BACK);
        GL11.glPopMatrix();
    }

}
