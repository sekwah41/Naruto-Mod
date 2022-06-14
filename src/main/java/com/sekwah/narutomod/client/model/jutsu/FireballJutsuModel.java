package com.sekwah.narutomod.client.model.jutsu;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sekwah.narutomod.NarutoMod;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class FireballJutsuModel extends Model
{
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(new ResourceLocation(NarutoMod.MOD_ID, "fireball"), "main");
    private final ModelPart main;

    public FireballJutsuModel(ModelPart modelPart)
    {
        super(RenderType::entitySolid);
        this.main = modelPart.getChild("main");
    }

    public static LayerDefinition createLayer() {

        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition main = partdefinition.addOrReplaceChild("main",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-9F, -9F, -11F, 18, 18, 22)
                , PartPose.ZERO);
        main.addOrReplaceChild("Shape2",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-11F, -9F, -9F, 22, 18, 18)
                , PartPose.ZERO);
        main.addOrReplaceChild("Shape3",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-9F, -11F, -9F, 18, 22, 18)
                , PartPose.ZERO);
        main.addOrReplaceChild("Shape4",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-13F, -5F, -5F, 26, 10, 10)
                , PartPose.ZERO);
        main.addOrReplaceChild("Shape5",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-5F, -13F, -5F, 10, 26, 10)
                , PartPose.ZERO);
        main.addOrReplaceChild("Shape6",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-5F, -5F, -13F, 10, 10, 26)
                , PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer vertexConsumer, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        matrixStack.pushPose();
        float scale = 2.4f;
        matrixStack.scale(scale, scale, scale);
        this.main.render(matrixStack, vertexConsumer, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
        matrixStack.popPose();
    }

}
