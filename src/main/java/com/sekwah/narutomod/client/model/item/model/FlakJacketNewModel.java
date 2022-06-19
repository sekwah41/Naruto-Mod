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

public class FlakJacketNewModel<T extends LivingEntity> extends HumanoidModel<T>
{

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(new ResourceLocation(NarutoMod.MOD_ID, "flack_jacket"), "main");

    public FlakJacketNewModel(ModelPart modelPart) {
        super(modelPart);
    }

    // Grab the parts in the constructor if you need them

    public static LayerDefinition createLayer() {
        //MeshDefinition definition = new MeshDefinition();
        MeshDefinition definition = ModelUtils.createBlankHumanoidMesh();
        PartDefinition root = definition.getRoot();

        root.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4, -8, -4, 8, 8, 8),
                PartPose.ZERO);

        PartDefinition body = root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(16, 16)
                        .addBox(-4, 0, -2, 8, 6, 4),
                PartPose.ZERO);

        body.addOrReplaceChild("armor15",
                CubeListBuilder.create()
                        .texOffs(24, 50)
                        .addBox(2.04F, -0.1F, -2.27F, 2, 2, 1),
                PartPose.ZERO);

        PartDefinition lower_body = body.addOrReplaceChild("lower_body",
                CubeListBuilder.create()
                        .texOffs(16, 28)
                        .addBox(-4, 0, -2, 8, 6, 4),
                PartPose.offsetAndRotation(0, 6, 0, 0, 0, 0));

        lower_body.addOrReplaceChild("armor_bottom2",
                CubeListBuilder.create()
                        .texOffs(40, 52)
                        .addBox(-4, 4.5F, -2.5F, 8, 1, 1),
                PartPose.ZERO);

        lower_body.addOrReplaceChild("body_crap13",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(0.02F, 0, -3.34F, 1, 1, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, -0.6981317007977318F, 0));

        lower_body.addOrReplaceChild("back_storage",
                CubeListBuilder.create()
                        .texOffs(0, 60)
                        .addBox(0.8F, 3.5F, 1.8F, 3, 3, 1),
                PartPose.ZERO);

        lower_body.addOrReplaceChild("armor_bottom3",
                CubeListBuilder.create()
                        .texOffs(40, 52)
                        .addBox(-4.3F, 4.5F, -2, 1, 1, 4),
                PartPose.ZERO);

        lower_body.addOrReplaceChild("armor29_1",
                CubeListBuilder.create()
                        .texOffs(40, 68)
                        .addBox(-4.03F, 0, -2, 1, 5, 4),
                PartPose.ZERO);

        lower_body.addOrReplaceChild("armor_side1",
                CubeListBuilder.create()
                        .texOffs(40, 68)
                        .addBox(3.03F, 0, -2, 1, 5, 4),
                PartPose.ZERO);

        lower_body.addOrReplaceChild("armor_bottom4",
                CubeListBuilder.create()
                        .texOffs(40, 52)
                        .addBox(3.3F, 4.5F, -2, 1, 1, 4),
                PartPose.ZERO);

        lower_body.addOrReplaceChild("armor_bottom5",
                CubeListBuilder.create()
                        .texOffs(40, 52)
                        .addBox(-4, 4.5F, 1.5F, 8, 1, 1),
                PartPose.ZERO);

        lower_body.addOrReplaceChild("body_crap12",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(-0.49F, 0, -2.9F, 1, 1, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, -0.6981317007977318F, 0));

        lower_body.addOrReplaceChild("body_crap6",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(-0.51F, 0, -2.9F, 1, 1, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0.6981317007977318F, 0));

        lower_body.addOrReplaceChild("armor18_1",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-4, 0, 1.2F, 8, 5, 1),
                PartPose.ZERO);

        lower_body.addOrReplaceChild("armor_bottom1",
                CubeListBuilder.create()
                        .texOffs(40, 52)
                        .addBox(-4, 0, -2.2F, 8, 5, 1),
                PartPose.ZERO);

        lower_body.addOrReplaceChild("body_crap5",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(-1.02F, 0, -3.34F, 1, 1, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0.6981317007977318F, 0));

        lower_body.addOrReplaceChild("body_crap7",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(-1.5F, 0, -3.73F, 1, 1, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0.6981317007977318F, 0));

        lower_body.addOrReplaceChild("black_line1",
                CubeListBuilder.create()
                        .texOffs(80, 24)
                        .addBox(0.77F, 0, -1.57F, 1, 5, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0.8726646259971648F, 0));

        lower_body.addOrReplaceChild("body_crap14",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(0.5F, 0, -3.73F, 1, 1, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, -0.6981317007977318F, 0));

        body.addOrReplaceChild("body_crap11",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(0.5F, 3.5F, -3.74F, 1, 2, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, -0.6981317007977318F, 0));

        body.addOrReplaceChild("head_neck2",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(24, 55)
                        .addBox(3.5F, -2.95F, -0.93F, 1, 3, 5),
                PartPose.ZERO);

        body.addOrReplaceChild("armor16",
                CubeListBuilder.create()
                        .texOffs(24, 50)
                        .addBox(1.44F, -0.1F, -2.27F, 1, 2, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("body_crap4",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(-0.51F, 3.5F, -2.9F, 1, 2, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0.6981317007977318F, 0));

        body.addOrReplaceChild("body_crap8",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(-0.49F, 3.5F, -2.9F, 1, 2, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, -0.6981317007977318F, 0));

        body.addOrReplaceChild("body_crap2",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(-3.2F, 5, -2.55F, 2, 1, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor18",
                CubeListBuilder.create()
                        .texOffs(75, 50)
                        .addBox(-4, 1, 1.2F, 8, 5, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor30",
                CubeListBuilder.create()
                        .texOffs(40, 68)
                        .addBox(3.03F, 1, -2, 1, 5, 4),
                PartPose.ZERO);

        body.addOrReplaceChild("armor11",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(24, 50)
                        .addBox(-4.04F, -0.1F, -2.27F, 2, 2, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor31",
                CubeListBuilder.create()
                        .texOffs(24, 64)
                        .addBox(-4, -0.1F, 1.38F, 8, 2, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("head_neck1",
                CubeListBuilder.create()
                        .texOffs(24, 55)
                        .addBox(-4.5F, -2.95F, -0.93F, 1, 3, 5),
                PartPose.ZERO);

        body.addOrReplaceChild("armor4",
                CubeListBuilder.create()
                        .texOffs(80, 24)
                        .addBox(0.77F, 1.01F, -1.57F, 1, 5, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0.8726646259971648F, 0));

        body.addOrReplaceChild("body_crap9",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(1.2F, 5, -2.55F, 2, 1, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("body_crap1",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(-1.5F, 3.5F, -3.74F, 1, 2, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0.6981317007977318F, 0));

        body.addOrReplaceChild("armor1",
                CubeListBuilder.create()
                        .texOffs(40, 50)
                        .addBox(-4, 1, -2.2F, 8, 5, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor32",
                CubeListBuilder.create()
                        .texOffs(24, 64)
                        .addBox(-4, 1.9F, 1.38F, 2, 1, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor33",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(24, 64)
                        .addBox(2, 1.9F, 1.38F, 2, 1, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("white2",
                CubeListBuilder.create()
                        .texOffs(13, 50)
                        .addBox(2.74F, -0.88F, 1.23F, 1, 1, 1),
                PartPose.offsetAndRotation(0, 0, 0, -0.8726646259971648F, 0.8726646259971648F, 0));

        body.addOrReplaceChild("white4",
                CubeListBuilder.create()
                        .texOffs(13, 50)
                        .addBox(-3.79F, 1.26F, -0.32F, 1, 1, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0.8726646259971648F, -0.8726646259971648F, 0));

        body.addOrReplaceChild("body_crap10",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(0.02F, 3.5F, -3.34F, 1, 2, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, -0.6981317007977318F, 0));

        body.addOrReplaceChild("armor13",
                CubeListBuilder.create()
                        .texOffs(24, 50)
                        .addBox(-2.44F, -0.1F, -2.27F, 1, 2, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("armor29",
                CubeListBuilder.create()
                        .texOffs(40, 68)
                        .addBox(-4.03F, 1, -2, 1, 5, 4),
                PartPose.ZERO);

        body.addOrReplaceChild("head_neck4",
                CubeListBuilder.create()
                        .texOffs(21, 55)
                        .addBox(-4, -0.75F, 0.99F, 8, 1, 3),
                PartPose.ZERO);

        body.addOrReplaceChild("head_neck3",
                CubeListBuilder.create()
                        .texOffs(22, 55)
                        .addBox(-4, -2.95F, 3.4F, 8, 3, 1),
                PartPose.ZERO);

        body.addOrReplaceChild("body_crap3",
                CubeListBuilder.create()
                        .texOffs(0, 51)
                        .addBox(-1.02F, 3.5F, -3.34F, 1, 2, 1),
                PartPose.offsetAndRotation(0, 0, 0, 0, 0.6981317007977318F, 0));

        PartDefinition right_arm = root.addOrReplaceChild("right_arm",
                CubeListBuilder.create()
                        .texOffs(40, 16)
                        .addBox(-3, -2, -2, 4, 6, 4),
                PartPose.offsetAndRotation(-5, 2, 0, 0, 0, 0));

        right_arm.addOrReplaceChild("armor_right1",
                CubeListBuilder.create()
                        .texOffs(18, 55)
                        .addBox(-1.99F, -2.2F, -2.26F, 3, 2, 4),
                PartPose.ZERO);

        right_arm.addOrReplaceChild("armor_right2",
                CubeListBuilder.create()
                        .texOffs(25, 56)
                        .addBox(-1.99F, -2.19F, 1.27F, 3, 2, 1),
                PartPose.ZERO);

        right_arm.addOrReplaceChild("lower_right_arm",
                CubeListBuilder.create()
                        .texOffs(40, 28)
                        .addBox(2, 2, -2, 4, 6, 4),
                PartPose.offsetAndRotation(-5, 2, 0, 0, 0, 0));

        PartDefinition left_arm = root.addOrReplaceChild("left_arm",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(40, 16)
                        .addBox(-1, -2, -2, 4, 6, 4),
                PartPose.offsetAndRotation(5, 2, 0, 0, 0, 0));

        left_arm.addOrReplaceChild("armor_left1",
                CubeListBuilder.create()
                        .texOffs(25, 55)
                        .addBox(-0.99F, -2.2F, -2.26F, 3, 2, 4),
                PartPose.ZERO);

        left_arm.addOrReplaceChild("lower_left_arm",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(40, 28)
                        .addBox(-6, 2, -2, 4, 6, 4),
                PartPose.offsetAndRotation(5, 2, 0, 0, 0, 0));

        left_arm.addOrReplaceChild("armor_left2",
                CubeListBuilder.create()
                        .texOffs(24, 57)
                        .addBox(-0.99F, -2.19F, 1.27F, 3, 2, 1),
                PartPose.ZERO);

        PartDefinition left_leg = root.addOrReplaceChild("left_leg",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 16)
                        .addBox(-2, 0, -2, 4, 6, 4),
                PartPose.offsetAndRotation(2, 12, 0, 0, 0, 0));

        left_leg.addOrReplaceChild("lower_left_leg",
                CubeListBuilder.create()
                        .mirror(true)
                        .texOffs(0, 28)
                        .addBox(-4, -6, -2, 4, 6, 4),
                PartPose.offsetAndRotation(2, 12, 0, 0, 0, 0));

        return LayerDefinition.create(definition, 150, 100);
    }
}
