package com.sekwah.narutomod.client.model.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.util.ModelUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;


public class AnbuMaskModel<T extends LivingEntity> extends HumanoidModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION_WITHOUT_EARS =
            new ModelLayerLocation(new ResourceLocation(NarutoMod.MOD_ID, "anbu_mask_without_ears"), "main");

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(new ResourceLocation(NarutoMod.MOD_ID, "anbu_mask"), "main");

    private final ModelPart head;

    public AnbuMaskModel(ModelPart modelPart)
    {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    public static LayerDefinition createLayer(boolean hasEars) {
        MeshDefinition definition = ModelUtils.createBlankHumanoidMesh();
        PartDefinition root = definition.getRoot();
        PartDefinition partdefinition1 = root.addOrReplaceChild("head",
                CubeListBuilder.create()
                        // comment, originX, originY, originZ, dimensionX, dimensionY, dimensionZ, xTexOffs, yTexOffs
                        // May need to add some box deformation (original additional value was 0.01f
                        .mirror(true)
                        .texOffs(32, 0)
                        .addBox("mask", -4F, -8.466666F, -4.266667F, 8, 9, 1,
                                new CubeDeformation(0.01F))
                        .texOffs(50, 3)
                        .addBox("eyebrow_left", 1F, -6F, -5F, 2, 1, 1)
                        .addBox("eyebrow_right", -3F, -6F, -5F, 2, 1, 1)
                        .addBox("nose",-1F, -5F, -5F, 2, 2, 1, 50, 5)
                , PartPose.ZERO);

        partdefinition1.addOrReplaceChild("mouth",
                CubeListBuilder.create()
                        .texOffs(50, 8)
                        .addBox(-2F, -5F, -5F, 4, 5, 1),
                PartPose.offsetAndRotation(0,0,0, -0.0743572F, 0F, 0F));

        partdefinition1.addOrReplaceChild("headband_left",
                CubeListBuilder.create()
                        .texOffs(60, 23)
                        .addBox(3.1F, -3.6F, 3F, 1, 8, 1),
                PartPose.offsetAndRotation(0,0,0, 1.570796F, 0F, 0F));

        partdefinition1.addOrReplaceChild("headband_right",
                CubeListBuilder.create()
                        .texOffs(60, 23)
                        .addBox(-4.1F, -3.733333F, 3F, 1, 8, 1),
                PartPose.offsetAndRotation(0,0,0, 1.570796F, 0F, 0F));

        partdefinition1.addOrReplaceChild("headband_back",
                CubeListBuilder.create()
                        .texOffs(60, 23)
                        .addBox(-4.4F, -4F, 3F, 1, 8, 1),
                PartPose.offsetAndRotation(0,0,0,  1.570796F, 1.570796F, 0F));

        if(hasEars) {
            partdefinition1.addOrReplaceChild("left_ear",
                    CubeListBuilder.create()
                            .texOffs(50, 0)
                            .addBox(-5F, -9F, -4.3F, 2, 2, 1),
                    PartPose.offsetAndRotation(-0.9333333F, 0F, 0F, 0F, 0F, 0.7853982F));
            partdefinition1.addOrReplaceChild("right_ear",
                    CubeListBuilder.create()
                            .texOffs(50, 0)
                            .addBox(-8.066667F, -6F, -4.3F, 2, 2, 1),
                    PartPose.offsetAndRotation(-0.35F, 0.04F, 0F, 0F, 0F, 0.7853982F));
        }

        return LayerDefinition.create(definition, 64, 32);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer vertexConsumer, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        matrixStack.pushPose();
        // Don't do this normally, its just the model needs to be modified to be slighty bigger :(
        // Fix this at some point if there are issues or write a script to scale everything up manually.
        matrixStack.translate((this.head.x / 16.0F), (this.head.y / 16.0F), (this.head.z / 16.0F));
        this.head.setPos(0,0,0);
        matrixStack.scale(1.1f, 1.1f, 1.1f);
        this.head.render(matrixStack, vertexConsumer, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
        matrixStack.popPose();
    }

}
