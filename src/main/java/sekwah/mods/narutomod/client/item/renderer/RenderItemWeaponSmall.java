package sekwah.mods.narutomod.client.item.renderer;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.client.ClientProxy;

/**
 * Registered in {@link ClientProxy#registerCustomItems()}
 */
public class RenderItemWeaponSmall implements IItemRenderer {

    public RenderItemWeaponSmall() {
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        switch (type) {
            case EQUIPPED:
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

                GL11.glTranslatef(0F, 0.7F, 0F);

                GL11.glRotatef(-119F, 0F, 0F, 1F);

                GL11.glTranslatef(-0.15F, 0.35F, 0F);

                float scale = 0.8F;

                GL11.glScalef(scale, scale, scale);

                GL11.glTranslatef(-0.16f, -0.28f, 0F);

                GL11.glRotatef(25F, 0F, 0F, 1F);

                // use these at some point, forgot to for samehada
                // but will be usefull for future model

                IIcon icon = item.getIconIndex();
                ItemRenderer.renderItemIn2D(Tessellator.instance, icon.getMaxU(), icon.getMaxV(), icon.getMinU(), icon.getMinV(), 16,16, 0.0625f);
            }
            default:
                break;
        }

    }

}
