package com.sekwah.narutomod.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

public class SubstitutionLogModel extends Model {

    private final ModelPart main;

    public SubstitutionLogModel(ModelPart modelPart)
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
                        .addBox(-2F, 0F, -2F, 4, 8, 4)
                , PartPose.offsetAndRotation(0F, 5F, 0F, (float) Math.PI, 0F, 0F));

        main.addOrReplaceChild("Shape2",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(16, 0)
                        .addBox(-0.2666667F, 2F, -1.866667F, 4, 1, 1)
                , PartPose.offsetAndRotation(0F, 0F, 0F, 0.4461433F, 0F, 0F));

        main.addOrReplaceChild("Shape3",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(26, 0)
                        .addBox(2.066667F, 1.733333F, 0F, 2, 2, 0)
                , PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, -0.3717861F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer vertexConsumer, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        this.main.render(matrixStack, vertexConsumer, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
    }
}
