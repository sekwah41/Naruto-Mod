package sekwah.mods.narutomod.entitys.renderers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.entitys.EntityNinjaVillager;

@SideOnly(Side.CLIENT)
public class RenderNinjaVillager extends RenderNinjaBiped {
    private static final ResourceLocation entityTexture1 = new ResourceLocation("narutomod:textures/entitys/Ninja_Villager_Green.png");
    private static final ResourceLocation entityTexture2 = new ResourceLocation("narutomod:textures/entitys/Ninja_Villager_Navy.png");
    private static final ResourceLocation entityTexture3 = new ResourceLocation("narutomod:textures/entitys/Ninja_Villager_Purple.png");

    public RenderNinjaVillager() {
        super(new ModelBiped(), 0.5F);
    }

    protected void func_82422_c() {
        GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
    }

    protected ResourceLocation getTexture(EntityNinjaVillager par1EntityNinjaVillager) {
        switch (par1EntityNinjaVillager.getProfession()) {
            case 0:
                return entityTexture1;

            case 1:
                return entityTexture2;

            case 2:
                return entityTexture3;

            default:
                return entityTexture1;
        }
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.getTexture((EntityNinjaVillager) par1Entity);
    }
}
