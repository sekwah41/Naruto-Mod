package com.sekwah.narutomod.client.model.jutsu;

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

public class WaterBulletModel extends Model
{
    private final ModelPart main;

    public WaterBulletModel(ModelPart modelPart)
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
                        .addBox(-2.533333F, -2.7F, -2.5F, 5, 5, 5)
                , PartPose.ZERO);

        main.addOrReplaceChild("Shape2",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-1.733333F, -1.733333F, 0F, 3, 3, 4)
                , PartPose.rotation(0F, 0F, 0.7853982F));

        main.addOrReplaceChild("Shape3",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-1.233333F, -1.233333F, 2.266667F, 2, 2, 4)
                , PartPose.rotation(0F, 0F, 0.7853982F));

        main.addOrReplaceChild("Shape4",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-0.7333333F, -0.7333333F, 4.333333F, 1, 1, 3)
                , PartPose.rotation(0F, 0F, 0.7853982F));

        main.addOrReplaceChild("Shape5",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-2F, -2.2F, -3F, 4, 4, 1)
                , PartPose.ZERO);

        main.addOrReplaceChild("Shape6",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-3F, -2.2F, -2F, 1, 4, 4)
                , PartPose.ZERO);

        main.addOrReplaceChild("Shape7",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(2F, -2.2F, -2F, 1, 4, 4)
                , PartPose.ZERO);

        main.addOrReplaceChild("Shape8",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-2F, -3F, -2F, 4, 1, 4)
                , PartPose.ZERO);

        main.addOrReplaceChild("Shape9",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-2F, 1.666667F, -2F, 4, 1, 4)
                , PartPose.ZERO);

        main.addOrReplaceChild("Shape10",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 0)
                        .addBox(-2F, -2.533333F, 1.266667F, 4, 4, 2)
                , PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer vertexConsumer, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        this.main.render(matrixStack, vertexConsumer, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
    }

}
