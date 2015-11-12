package com.sekwah.mods.narutomod.entitys.renderers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.sekwah.mods.narutomod.entitys.EntityRogueNinja;

@SideOnly(Side.CLIENT)
public class RenderRogueNinja extends RenderNinjaBiped {

    public RenderRogueNinja() {
        super(new ModelBiped(), 0.5F);
    }

    protected void func_82422_c() {
        GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
    }


    protected ResourceLocation getTexture(EntityRogueNinja par1EntityRogueNinja) {
        if (par1EntityRogueNinja.getMask() == 1) {
            return new ResourceLocation("narutomod:textures/entitys/rogue_ninja.png");
        } else {
            return new ResourceLocation("narutomod:textures/entitys/rogue_ninja1.png");
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.getTexture((EntityRogueNinja) par1Entity);
    }
}
