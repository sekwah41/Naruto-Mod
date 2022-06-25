package com.sekwah.narutomod.client.model.item.model;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.sekclib.util.ModelUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class FlakJacketModel<T extends LivingEntity> extends HumanoidModel<T>
{

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(NarutoMod.MOD_ID, "changeme"), "main");

    public FlakJacketModel(ModelPart modelPart) {
        super(modelPart);
    }

    // Grab the parts in the constructor if you need them

    public static LayerDefinition createLayer() {
        //MeshDefinition definition = new MeshDefinition();
        MeshDefinition definition = ModelUtils.createBlankHumanoidMesh();
        PartDefinition root = definition.getRoot();

        PartDefinition left_arm = root.addOrReplaceChild("left_arm",
                CubeListBuilder.create()
                        .texOffs(40, 16)
                        .addBox(-1, -2, -2, 4, 6, 4),
                PartPose.offsetAndRotation(5, 2, 0, 0, 0, 0));

        left_arm.addOrReplaceChild("armor_left2",
                CubeListBuilder.create()
                        .texOffs(24, 57)
                        .addBox(-0.99F, -2.19F, 1.27F, 3, 1, 1),
                PartPose.ZERO);

        left_arm.addOrReplaceChild("armor_left1",
                CubeListBuilder.create()
                        .texOffs(25, 55)
                        .addBox(-0.99F, -2.2F, -2.26F, 3, 1, 4),
                PartPose.ZERO);

        left_arm.addOrReplaceChild("lower_left_arm",
                CubeListBuilder.create()
                        .texOffs(40, 28)
                        .addBox(-6, 2, -2, 4, 6, 4),
                PartPose.offsetAndRotation(5, 2, 0, 0, 0, 0));

        PartDefinition right_arm = root.addOrReplaceChild("right_arm",
                CubeListBuilder.create()
                        .texOffs(40, 16)
                        .addBox(-3, -2, -2, 4, 6, 4),
                PartPose.offsetAndRotation(-5, 2, 0, 0, 0, 0));

        right_arm.addOrReplaceChild("armor_right1",
                CubeListBuilder.create()
                        .texOffs(18, 55)
                        .addBox(-1.99F, -2.2F, -2.26F, 3, 1, 4),
                PartPose.ZERO);

        right_arm.addOrReplaceChild("lower_right_arm",
                CubeListBuilder.create()
                        .texOffs(40, 28)
                        .addBox(2, 2, -2, 4, 6, 4),
                PartPose.offsetAndRotation(-5, 2, 0, 0, 0, 0));

        right_arm.addOrReplaceChild("armor_right2",
                CubeListBuilder.create()
                        .texOffs(25, 56)
                        .addBox(-1.99F, -2.19F, 1.27F, 3, 1, 1),
                PartPose.ZERO);

        PartDefinition body = root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(16, 16)
                        .addBox(-4, 0, -2, 8, 6, 4),
                PartPose.ZERO);

        PartDefinition lower_body = body.addOrReplaceChild("lower_body",
                CubeListBuilder.create()
                        .texOffs(16, 28)
                        .addBox(-4, 0, -2, 8, 6, 4),
                PartPose.offsetAndRotation(0, 6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor19",
                CubeListBuilder.create()
                        .texOffs(40, 68)
                        .addBox(-3.96F, 7.9F, 1.25F, 8, 1, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor8",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-3.7F, 10.73F, -2.2F, 7, 1, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor3",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-3.99F, 8.8F, -2.2F, 7, 1, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor18_1",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-4, 6, 1.2F, 8, 2, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor21",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-3.99F, 8.8F, 1.2F, 7, 1, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor27",
                CubeListBuilder.create()
                        .texOffs(24, 58)
                        .addBox(3.04F, 7.9F, -2.24F, 1, 1, 4),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor7",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-3, 9.8F, -2.2F, 6, 1, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor2",
                CubeListBuilder.create()
                        .texOffs(41, 68)
                        .addBox(-4.06F, 7.9F, -2.25F, 8, 1, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor20",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(2.99F, 8.8F, 1.2F, 1, 1, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor24",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-3, 9.8F, 1.2F, 6, 1, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor_bottom1",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-4, 6, -2.2F, 8, 2, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor28",
                CubeListBuilder.create()
                        .texOffs(23, 55)
                        .addBox(-4.07F, 7.9F, -1.75F, 1, 1, 4),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor5",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-5.31F, 9.1F, -2.2F, 1, 2, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, -0.13962634015954636F));

        lower_body.addOrReplaceChild("armor_side1",
                CubeListBuilder.create()
                        .texOffs(40, 68)
                        .addBox(3.03F, 6, -2, 1, 2, 4),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor26",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(2.7F, 10.73F, 1.2F, 1, 1, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor29_1",
                CubeListBuilder.create()
                        .texOffs(40, 68)
                        .addBox(-4.03F, 6, -2, 1, 2, 4),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor32",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(2.99F, 8.8F, -2.2F, 1, 1, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor23",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-5.31F, 9.1F, 1.2F, 1, 2, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, -0.13962634015954636F));

        lower_body.addOrReplaceChild("armor25",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-3.7F, 10.73F, 1.2F, 7, 1, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor6",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(4.31F, 9.1F, -2.19F, 1, 2, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0.13962634015954636F));

        lower_body.addOrReplaceChild("armor22",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(4.31F, 9.1F, 1.2F, 1, 2, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0.13962634015954636F));

        lower_body.addOrReplaceChild("armor9",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(2.7F, 10.73F, -2.2F, 1, 1, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor33",
                CubeListBuilder.create()
                        .texOffs(80, 24)
                        .addBox(0.66F, 10.7F, -1.46F, 1, 1, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0.8726646259971648F, 0));

        lower_body.addOrReplaceChild("black_line1",
                CubeListBuilder.create()
                        .texOffs(80, 24)
                        .addBox(0.66F, 6, -1.46F, 1, 5, 1),
                PartPose.offsetAndRotation(0, -6, 0, 0, 0.8726646259971648F, 0));

        body.addOrReplaceChild("head_neck2",
                CubeListBuilder.create()
                        .texOffs(24, 55)
                        .addBox(3.24F, -2.95F, -0.93F, 1, 3, 5),
                PartPose.ZERO);

        body.addOrReplaceChild("white3",
                CubeListBuilder.create()
                        .texOffs(13, 50)
                        .addBox(-2.95F, 0.62F, -0.92F, 1, 1, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0.8726646259971648F, -0.8726646259971648F, 0));

        body.addOrReplaceChild("armor14",
                CubeListBuilder.create()
                        .texOffs(24, 50)
                        .addBox(2.04F, 0.3F, -2.27F, 2, 1, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("white2",
                CubeListBuilder.create()
                        .texOffs(13, 50)
                        .addBox(2.64F, -0.88F, 1.23F, 1, 1, 1),
                PartPose.offsetAndRotation(0, 0, 0, -0.8726646259971648F, 0.8726646259971648F, 0));

        body.addOrReplaceChild("armor17",
                CubeListBuilder.create()
                        .texOffs(24, 50)
                        .addBox(1.44F, 0.3F, -2.27F, 1, 1, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor1",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-4, 1, -2.2F, 8, 5, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor29",
                CubeListBuilder.create()
                        .texOffs(40, 68)
                        .addBox(-4.03F, 1, -2, 1, 5, 4),
                PartPose.ZERO);

        body.addOrReplaceChild("head_neck3",
                CubeListBuilder.create()
                        .texOffs(22, 55)
                        .addBox(-4, -2.95F, 3.09F, 8, 3, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("white1",
                CubeListBuilder.create()
                        .texOffs(13, 50)
                        .addBox(1.94F, -0.22F, 0.73F, 1, 1, 1),
                PartPose.offsetAndRotation(0, 0, 0, -0.8726646259971648F, 0.8726646259971648F, 0));

        body.addOrReplaceChild("armor11",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(24, 50)
                        .addBox(-4.04F, -0.1F, -2.27F, 2, 1, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor10",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(24, 50)
                        .addBox(-4.04F, 0.3F, -2.27F, 2, 1, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor13",
                CubeListBuilder.create()
                        .texOffs(24, 50)
                        .addBox(-2.44F, -0.1F, -2.27F, 1, 1, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("head_neck4",
                CubeListBuilder.create()
                        .texOffs(21, 55)
                        .addBox(-4, -0.85F, 0.99F, 8, 1, 3),
                PartPose.ZERO);

        body.addOrReplaceChild("armor16",
                CubeListBuilder.create()
                        .texOffs(24, 50)
                        .addBox(1.44F, -0.1F, -2.27F, 1, 1, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor4",
                CubeListBuilder.create()
                        .texOffs(80, 24)
                        .addBox(0.66F, 1.01F, -1.46F, 1, 5, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0.8726646259971648F, 0));

        body.addOrReplaceChild("head_neck1",
                CubeListBuilder.create()
                        .texOffs(24, 55)
                        .addBox(-4.24F, -2.95F, -0.93F, 1, 3, 5),
                PartPose.ZERO);

        body.addOrReplaceChild("armor18",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-4, 1, 1.2F, 8, 5, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor12",
                CubeListBuilder.create()
                        .texOffs(24, 50)
                        .addBox(-2.44F, 0.3F, -2.27F, 1, 1, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor30",
                CubeListBuilder.create()
                        .texOffs(40, 68)
                        .addBox(3.03F, 1, -2, 1, 5, 4),
                PartPose.ZERO);

        body.addOrReplaceChild("armor15",
                CubeListBuilder.create()
                        .texOffs(24, 50)
                        .addBox(2.04F, -0.1F, -2.27F, 2, 1, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor31",
                CubeListBuilder.create()
                        .texOffs(24, 64)
                        .addBox(-4, -0.1F, 1.27F, 8, 3, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("white4",
                CubeListBuilder.create()
                        .texOffs(13, 50)
                        .addBox(-3.69F, 1.26F, -0.32F, 1, 1, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0.8726646259971648F, -0.8726646259971648F, 0));

        root.addOrReplaceChild("lower_right_leg",
                CubeListBuilder.create()
                        .texOffs(0, 28)
                        .addBox(-8, -6, -2, 4, 6, 4),
                PartPose.offsetAndRotation(2, 12, 0, 0, 0, 0));

        return LayerDefinition.create(definition, 150, 100);
    }

    // May need to implement the rendering parts depending on what you are doing
}
