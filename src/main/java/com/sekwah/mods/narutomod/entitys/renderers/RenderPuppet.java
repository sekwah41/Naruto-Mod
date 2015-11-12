package com.sekwah.mods.narutomod.entitys.renderers;

import com.sekwah.mods.narutomod.entitys.models.ModelPuppet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderPuppet extends RenderLiving {
    private static final ResourceLocation Puppet_Texture = new ResourceLocation("narutomod:textures/entitys/puppet_crow.png");  //refers to:assets/yourmod/textures/entity/yourtexture.png

    public RenderPuppet() {
        super(new ModelPuppet(), 0.5F); // model class, shadow size
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return Puppet_Texture;
    }
}