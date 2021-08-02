package com.sekwah.narutomod.client.model.item.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sekwah.narutomod.util.ModelUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class HeadbandModel<T extends LivingEntity> extends HumanoidModel<T>
{
    private final ModelPart head;

    public HeadbandModel(ModelPart modelPart)
    {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    public static LayerDefinition createLayer() {

        float yOffset = 1.6F;

        MeshDefinition definition = ModelUtils.createBlankHumanoidMesh();
        PartDefinition root = definition.getRoot();
        PartDefinition partdefinition1 = root.addOrReplaceChild("head",
                CubeListBuilder.create()
                , PartPose.ZERO);

        partdefinition1.addOrReplaceChild("front",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(0F, 0F, 0F, 8, 2, 1)
                , PartPose.offset(-4F, -8F + yOffset, -4.233333F));

        partdefinition1.addOrReplaceChild("back",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(0F, 0F, 0F, 8, 2, 1)
                , PartPose.offset(-4F, -8F + yOffset, 3.2F));

        partdefinition1.addOrReplaceChild("right",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(0F, 0F, 0F, 8, 2, 1)
                , PartPose.offsetAndRotation(3.1F, -8F + yOffset, 4F, 0F, 1.570796F, 0F));

        partdefinition1.addOrReplaceChild("left",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(0F, 0F, 0F, 8, 2, 1)
                , PartPose.offsetAndRotation(-4.2F, -8F + yOffset, 4F, 0F, 1.570796F, 0F));
        partdefinition1.addOrReplaceChild("metal_plate",
                CubeListBuilder.create()
                        .texOffs(19, 0)
                        .addBox(0F, 0F, 0F, 3, 2, 1,
                                new CubeDeformation(0.01F))
                , PartPose.offset(-1.5F, -8F + yOffset, -4.533333F));

        partdefinition1.addOrReplaceChild("knot_right",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 3)
                        .addBox(-1F, 0F, 0F, 1, 3, 1)
                , PartPose.offsetAndRotation(0F, -6.333333F + yOffset, 3.6F,
                        0.4833219F, 1.226894F, 0F));

        partdefinition1.addOrReplaceChild("knot_left",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 3)
                        .addBox(0F, 0F, 0F, 1, 3, 1)
                , PartPose.offsetAndRotation(0F, -7.066667F + yOffset, 4.7F,
                        -0.5576792F, 1.953013F, 0F));

        partdefinition1.addOrReplaceChild("knot",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(0F, 0F, 0F, 1, 1, 1)
                , PartPose.offsetAndRotation(0.2666667F, -7.533333F + yOffset, 3.933333F,
                        0.4833219F, -0.3717861F, 0.8551081F));

        return LayerDefinition.create(definition, 64, 32);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer vertexConsumer, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        matrixStack.pushPose();
        matrixStack.translate((this.head.x / 16.0F), (this.head.y / 16.0F), (this.head.z / 16.0F));
        this.head.setPos(0,0,0);
        matrixStack.scale(1.1f, 1.1f, 1.1f);
        this.head.render(matrixStack, vertexConsumer, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
        matrixStack.popPose();
    }

}
