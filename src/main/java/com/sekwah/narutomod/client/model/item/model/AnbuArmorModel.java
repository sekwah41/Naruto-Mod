package com.sekwah.narutomod.client.model.item.model;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.sekclib.util.ModelUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;

public class AnbuArmorModel<T extends LivingEntity> extends HumanoidModel<T>
{
    
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(NarutoMod.MOD_ID, "anbu_armor"), "main");

    public AnbuArmorModel(ModelPart modelPart) {
        super(modelPart);
    }

    // Grab the parts in the constructor if you need them
    
    public static LayerDefinition createLayer() {
        //MeshDefinition definition = new MeshDefinition();
        MeshDefinition definition = ModelUtils.createBlankHumanoidMesh();
        PartDefinition root = definition.getRoot();
        
        PartDefinition body = root.addOrReplaceChild("body",
            CubeListBuilder.create()
                .texOffs(16, 16)
                .addBox(-4, 0, -2, 8, 6, 4),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor2",
            CubeListBuilder.create()
                .texOffs(40, 50)
                .addBox(-4, 1.9F, -2.2F, 8, 4, 1),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor23",
            CubeListBuilder.create()
                .texOffs(100, 12)
                .addBox(-3.8F, -0.1F, -2.3F, 1, 1, 4),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor18",
            CubeListBuilder.create()
                .texOffs(90, 0)
                .addBox(-3.8F, 0, 1.3F, 1, 2, 1),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor17",
            CubeListBuilder.create()
                .texOffs(100, 0)
                .addBox(2.8F, 1.9F, 1.4F, 1, 1, 1),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor19",
            CubeListBuilder.create()
                .texOffs(100, 0)
                .addBox(-3.8F, 1.9F, 1.4F, 1, 1, 1),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor24",
            CubeListBuilder.create()
                .texOffs(99, 13)
                .addBox(2.8F, -0.1F, 1.3F, 1, 1, 1),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armorleft1",
            CubeListBuilder.create()
                .texOffs(105, 63)
                .addBox(3.1F, 2, -2, 1, 4, 4),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor10",
            CubeListBuilder.create()
                .texOffs(105, 63)
                .addBox(-4.1F, 2, -2, 1, 4, 4),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor22",
            CubeListBuilder.create()
                .texOffs(99, 13)
                .addBox(2.8F, -0.1F, -2.3F, 1, 1, 4),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor11",
            CubeListBuilder.create()
                .texOffs(90, 0)
                .addBox(2.8F, 0, -2.3F, 1, 2, 1),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor14",
            CubeListBuilder.create()
                .texOffs(100, 0)
                .addBox(-3.8F, 1.9F, -2.4F, 1, 1, 1),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor12",
            CubeListBuilder.create()
                .texOffs(90, 0)
                .addBox(-3.8F, 0, -2.3F, 1, 2, 1),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor13",
            CubeListBuilder.create()
                .texOffs(100, 0)
                .addBox(2.8F, 1.9F, -2.4F, 1, 1, 1),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor25",
            CubeListBuilder.create()
                .texOffs(100, 12)
                .addBox(-3.8F, -0.1F, 1.3F, 1, 1, 1),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor16",
            CubeListBuilder.create()
                .texOffs(90, 0)
                .addBox(2.8F, 0, 1.3F, 1, 2, 1),
            PartPose.ZERO);
        
        body.addOrReplaceChild("armor6",
            CubeListBuilder.create()
                .texOffs(60, 50)
                .addBox(-4, 1.9F, 1.2F, 8, 4, 1),
            PartPose.ZERO);
        
        PartDefinition lower_left_arm = root.getChild("left_arm").addOrReplaceChild("lower_left_arm",
            CubeListBuilder.create()
                .texOffs(40, 28)
                .addBox(-6, 2, -2, 4, 6, 4),
            PartPose.offsetAndRotation(5, 2, 0, 0, 0, 0));
        
        lower_left_arm.addOrReplaceChild("arm1_1",
            CubeListBuilder.create()
                .texOffs(0, 90)
                .addBox(-2.5F, 1.8F, -1, 1, 5, 2),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 0.024609142453120045F));
        
        lower_left_arm.addOrReplaceChild("arm3_1",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(-5.8F, 5.3F, 0.1F, 4, 1, 2),
            PartPose.ZERO);
        
        lower_left_arm.addOrReplaceChild("arm8_1",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(-6.1F, 5.3F, -2.09F, 1, 1, 4),
            PartPose.ZERO);
        
        lower_left_arm.addOrReplaceChild("arm5_1",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(-5.8F, 2.8F, 0.1F, 4, 1, 2),
            PartPose.ZERO);
        
        lower_left_arm.addOrReplaceChild("arm6_1",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(-6.1F, 2.8F, -2.09F, 1, 1, 4),
            PartPose.ZERO);
        
        lower_left_arm.addOrReplaceChild("arm7_1",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(-6.09F, 2.8F, 1.09F, 1, 1, 1),
            PartPose.ZERO);
        
        lower_left_arm.addOrReplaceChild("arm2_1",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(-5.8F, 2.8F, -2.1F, 4, 1, 2),
            PartPose.ZERO);
        
        lower_left_arm.addOrReplaceChild("arm4_1",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(-5.8F, 5.3F, -2.1F, 4, 1, 2),
            PartPose.ZERO);
        
        lower_left_arm.addOrReplaceChild("arm9_1",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(-6.09F, 5.3F, 1.09F, 1, 1, 1),
            PartPose.ZERO);
        
        PartDefinition lower_body = body.addOrReplaceChild("lower_body",
            CubeListBuilder.create()
                .texOffs(16, 28)
                .addBox(-4, 0, -2, 8, 6, 4),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 0));
        
        lower_body.addOrReplaceChild("armor4",
            CubeListBuilder.create()
                .texOffs(0, 50)
                .addBox(3.2F, 11, -2.3F, 1, 1, 4),
            PartPose.ZERO);
        
        lower_body.addOrReplaceChild("armor2_1",
            CubeListBuilder.create()
                .texOffs(40, 50)
                .addBox(-4, 5.9F, -2.2F, 8, 6, 1),
            PartPose.ZERO);
        
        lower_body.addOrReplaceChild("armor6_1",
            CubeListBuilder.create()
                .texOffs(60, 50)
                .addBox(-4, 5.9F, 1.2F, 8, 6, 1),
            PartPose.ZERO);
        
        lower_body.addOrReplaceChild("armor20",
            CubeListBuilder.create()
                .texOffs(70, 20)
                .addBox(3.6F, 5.1F, -2.25F, 1, 2, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 0.2617993877991494F));
        
        lower_body.addOrReplaceChild("armor10_1",
            CubeListBuilder.create()
                .texOffs(105, 63)
                .addBox(-4.1F, 5.9F, -2, 1, 6, 4),
            PartPose.ZERO);
        
        lower_body.addOrReplaceChild("armor3",
            CubeListBuilder.create()
                .texOffs(0, 50)
                .addBox(-3.8F, 11, 1.3F, 8, 1, 1),
            PartPose.ZERO);
        
        lower_body.addOrReplaceChild("armor1",
            CubeListBuilder.create()
                .texOffs(0, 50)
                .addBox(-4.2F, 11, -2.3F, 8, 1, 1),
            PartPose.ZERO);
        
        lower_body.addOrReplaceChild("armorleft2",
            CubeListBuilder.create()
                .texOffs(105, 63)
                .addBox(3.1F, 5.9F, -2, 1, 6, 4),
            PartPose.ZERO);
        
        lower_body.addOrReplaceChild("armor21",
            CubeListBuilder.create()
                .texOffs(70, 20)
                .addBox(-4.6F, 5.1F, -2.25F, 1, 2, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, -0.2617993877991494F));
        
        lower_body.addOrReplaceChild("armor5",
            CubeListBuilder.create()
                .texOffs(0, 50)
                .addBox(-4.2F, 11, -1.7F, 1, 1, 4),
            PartPose.ZERO);
        
        PartDefinition lower_right_arm = root.getChild("right_arm").addOrReplaceChild("lower_right_arm",
            CubeListBuilder.create()
                .texOffs(40, 28)
                .addBox(2, 2, -2, 4, 6, 4),
            PartPose.offsetAndRotation(-5, 2, 0, 0, 0, 0));
        
        lower_right_arm.addOrReplaceChild("arm2",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(1.8F, 2.8F, -2.1F, 4, 1, 2),
            PartPose.ZERO);
        
        lower_right_arm.addOrReplaceChild("arm8",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(5.1F, 5.3F, -2.09F, 1, 1, 4),
            PartPose.ZERO);
        
        lower_right_arm.addOrReplaceChild("arm3",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(1.8F, 5.3F, 0.1F, 4, 1, 2),
            PartPose.ZERO);
        
        lower_right_arm.addOrReplaceChild("arm9",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(5.09F, 5.3F, 1.09F, 1, 1, 1),
            PartPose.ZERO);
        
        lower_right_arm.addOrReplaceChild("arm6",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(5.1F, 2.8F, -2.09F, 1, 1, 4),
            PartPose.ZERO);
        
        lower_right_arm.addOrReplaceChild("arm4",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(1.8F, 5.3F, -2.1F, 4, 1, 2),
            PartPose.ZERO);
        
        lower_right_arm.addOrReplaceChild("arm1",
            CubeListBuilder.create()
                .texOffs(0, 90)
                .addBox(1.5F, 1.8F, -1, 1, 5, 2),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, -0.024609142453120045F));
        
        lower_right_arm.addOrReplaceChild("arm5",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(1.8F, 2.8F, 0.1F, 4, 1, 2),
            PartPose.ZERO);
        
        lower_right_arm.addOrReplaceChild("arm7",
            CubeListBuilder.create()
                .texOffs(0, 70)
                .addBox(5.09F, 2.8F, 1.09F, 1, 1, 1),
            PartPose.ZERO);
        
        return LayerDefinition.create(definition, 150, 100);
    }

    // May need to implement the rendering parts depending on what you are doing
}
