package sekwah.mods.narutomod.blocks.itemrenderers;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.blocks.models.ModelBonsai;

public class ItemRendererBonsaiTree implements IItemRenderer {

    private ModelBonsai model;

    public ItemRendererBonsaiTree() {
        model = new ModelBonsai();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        GL11.glPushMatrix();
        //GL11.glTranslatef((float)par2 + 0.5F, (float)par4, (float)par6 + 0.5F);

        //int dir = par1TileEntityBonsai.getBlockMetadata();
        GL11.glRotatef(180, 1F, 0F, 0F);
        GL11.glScalef(1.2F, 1.2F, 1.2F);
        //GL11.glRotatef(dir * (90F), 0F, 1F, 0F);
        //GL11.glTranslatef(0.4F, 0.1F, -0.5F);
        //TileEntityRenderer.instance.renderEngine.bindTexture(new ResourceLocation("narutomod:textures/blocks/bonsaitree.png"));
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("narutomod:textures/blocks/bonsaitreeTexture.png"));
        if (type == ItemRenderType.ENTITY) {
            GL11.glTranslatef(0.0F, 0.2F, 0.0F);
        } else if (type == ItemRenderType.INVENTORY) {
            GL11.glTranslatef(0.0F, 0.45F, 0.0F);
        } else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.EQUIPPED) {
            GL11.glTranslatef(0.40F, -0.2F, -0.50F);
        }
        this.model.render();

        GL11.glPopMatrix();

    }

}
