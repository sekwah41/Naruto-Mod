package com.sekwah.narutomod.client.model.item.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sekwah.narutomod.NarutoMod;
import com.sekwah.sekclib.util.ModelUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;

public class AkatsukiCloakModel<T extends LivingEntity> extends HumanoidModel<T>
{
    
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(NarutoMod.MOD_ID, "akatsuki_cloak"), "main");

    public AkatsukiCloakModel(ModelPart modelPart) {
        super(modelPart);
    }

    // Grab the parts in the constructor if you need them
    
    public static LayerDefinition createLayer() {
        //MeshDefinition definition = new MeshDefinition();
        MeshDefinition definition = ModelUtils.createBlankHumanoidMesh();
        PartDefinition root = definition.getRoot();
        
        PartDefinition head = root.addOrReplaceChild("head",
            CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-4, -8, -4, 8, 8, 8),
            PartPose.ZERO);
        
        head.addOrReplaceChild("head_neck5",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(0, 81)
                .addBox(-4, -3.25F, 3.55F, 8, 3, 1),
            PartPose.offsetAndRotation(0, 0, 0, -0.08726646259971647F, 0, 0));
        
        head.addOrReplaceChild("head_neck1",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(24, 73)
                .addBox(3.45F, -3.25F, -4, 1, 3, 8),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 0.08726646259971647F));
        
        head.addOrReplaceChild("head_neck3",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(24, 73)
                .addBox(-4.45F, -3.25F, -4, 1, 3, 8),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, -0.08726646259971647F));
        
        head.addOrReplaceChild("head_neck6",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(0, 91)
                .addBox(-4, -0.7F, -4, 8, 1, 8),
            PartPose.ZERO);
        
        head.addOrReplaceChild("head_neck4",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(0, 73)
                .addBox(-4, -3.25F, -4.65F, 8, 3, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0.08726646259971647F, 0, 0));
        
        PartDefinition left_arm = root.addOrReplaceChild("left_arm",
            CubeListBuilder.create()
                .texOffs(40, 16)
                .addBox(-1, -2, -2, 4, 6, 4),
            PartPose.offsetAndRotation(5, 2, 0, 0, 0, 0));
        
        left_arm.addOrReplaceChild("cloak_arm2",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(43, 70)
                .addBox(-1, -2, 1.28F, 4, 6, 1),
            PartPose.ZERO);
        
        left_arm.addOrReplaceChild("cloak_arm9",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(66, 58)
                .addBox(-1, -2.2F, -2, 4, 1, 4),
            PartPose.ZERO);
        
        PartDefinition lower_left_arm = left_arm.addOrReplaceChild("lower_left_arm",
            CubeListBuilder.create()
                .texOffs(40, 28)
                .addBox(-6, 2, -2, 4, 6, 4),
            PartPose.offsetAndRotation(5, 2, 0, 0, 0, 0));
        
        lower_left_arm.addOrReplaceChild("cloak_arm8",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(81, 70)
                .addBox(-6.12F, 2, -2, 1, 5, 4),
            PartPose.ZERO);
        
        lower_left_arm.addOrReplaceChild("cloak_arm6",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(70, 70)
                .addBox(-6, 2, 1.28F, 4, 5, 1),
            PartPose.ZERO);
        
        lower_left_arm.addOrReplaceChild("cloak_arm5",
            CubeListBuilder.create()
                .texOffs(70, 70)
                .addBox(-6, 2, -2.28F, 4, 5, 1),
            PartPose.ZERO);
        
        lower_left_arm.addOrReplaceChild("cloak_arm7",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(81, 70)
                .addBox(-2.8F, 2, -2, 1, 5, 4),
            PartPose.ZERO);
        
        left_arm.addOrReplaceChild("cloak_arm1",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(43, 70)
                .addBox(-1, -2, -2.28F, 4, 6, 1),
            PartPose.ZERO);
        
        left_arm.addOrReplaceChild("cloak_arm4",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(57, 70)
                .addBox(-1.12F, -2, -2, 1, 6, 4),
            PartPose.ZERO);
        
        left_arm.addOrReplaceChild("cloak_arm3",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(57, 70)
                .addBox(2.2F, -2, -2, 1, 6, 4),
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
        
        lower_body.addOrReplaceChild("cloak4",
            CubeListBuilder.create()
                .texOffs(20, 60)
                .addBox(-4, 0, 1.5F, 8, 6, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0.03490658503988659F, 0, 0));
        
        lower_body.addOrReplaceChild("cloak8",
            CubeListBuilder.create()
                .texOffs(55, 52)
                .addBox(3.25F, 0, -2, 1, 6, 4),
            PartPose.ZERO);
        
        lower_body.addOrReplaceChild("cloak3",
            CubeListBuilder.create()
                .texOffs(0, 60)
                .addBox(-4, 0, -2.4F, 8, 6, 1),
            PartPose.ZERO);
        
        lower_body.addOrReplaceChild("cloak7",
            CubeListBuilder.create()
                .texOffs(43, 51)
                .addBox(-4.25F, 0, -2, 1, 6, 4),
            PartPose.ZERO);
        
        body.addOrReplaceChild("cloak6",
            CubeListBuilder.create()
                .texOffs(55, 40)
                .addBox(3.2F, 0, -2, 1, 6, 4),
            PartPose.ZERO);
        
        body.addOrReplaceChild("cloak2",
            CubeListBuilder.create()
                .texOffs(20, 50)
                .addBox(-4, 0, 1.3F, 8, 6, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0.03490658503988659F, 0, 0));
        
        body.addOrReplaceChild("cloak9",
            CubeListBuilder.create()
                .texOffs(0, 92)
                .addBox(-4, -0.2F, -2, 8, 1, 4),
            PartPose.ZERO);
        
        body.addOrReplaceChild("cloak1",
            CubeListBuilder.create()
                .texOffs(0, 50)
                .addBox(-4, 0, -2.4F, 8, 6, 1),
            PartPose.ZERO);
        
        body.addOrReplaceChild("cloak5",
            CubeListBuilder.create()
                .texOffs(43, 40)
                .addBox(-4.2F, 0, -2, 1, 6, 4),
            PartPose.ZERO);

        PartDefinition left_leg = root.addOrReplaceChild("left_leg",
            CubeListBuilder.create()
                .texOffs(0, 16)
                .addBox(-2, 0, -2, 4, 6, 4),
            PartPose.offsetAndRotation(2, 12, 0, 0, 0, 0));
        
        left_leg.addOrReplaceChild("leg5_1",
            CubeListBuilder.create()
                .texOffs(112, 60)
                .addBox(-2.4F, 1.4F, -2.42F, 1, 4, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, -0.24434609527920614F));
        
        left_leg.addOrReplaceChild("leg3_1",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(100, 60)
                .addBox(-2, 0, -2.4F, 4, 2, 1),
            PartPose.ZERO);
        
        left_leg.addOrReplaceChild("leg1_1",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(100, 70)
                .addBox(1.3F, 0, -2, 1, 6, 4),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, -0.03490658503988659F));
        
        left_leg.addOrReplaceChild("leg7_1",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(113, 49)
                .addBox(-2, 0, 1.7F, 4, 6, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0.03490658503988659F, 0, 0));
        
        PartDefinition lower_left_leg = left_leg.addOrReplaceChild("lower_left_leg",
            CubeListBuilder.create()
                .texOffs(0, 28)
                .addBox(-4, -6, -2, 4, 6, 4),
            PartPose.offsetAndRotation(2, 12, 0, 0, 0, 0));
        
        lower_left_leg.addOrReplaceChild("leg2_1",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(113, 70)
                .addBox(-0.27F, -6.15F, -2, 1, 4, 4),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, -0.03490658503988659F));
        
        lower_left_leg.addOrReplaceChild("leg6_1",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(89, 60)
                .addBox(-3, -6, -2.4F, 3, 4, 1),
            PartPose.ZERO);
        
        lower_left_leg.addOrReplaceChild("leg8_1",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(113, 49)
                .addBox(-4, -6.05F, 2.12F, 4, 4, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0.03490658503988659F, 0, 0));
        
        left_leg.addOrReplaceChild("leg4_1",
            CubeListBuilder.create()
                .mirror(true)
                .texOffs(100, 49)
                .addBox(-1, 2, -2.4F, 3, 4, 1),
            PartPose.ZERO);

        PartDefinition right_leg = root.getChild("right_leg").addOrReplaceChild("right_leg_fixer",
            CubeListBuilder.create()
                .texOffs(0, 16)
                .addBox(-6, 0, -2, 4, 6, 4),
            PartPose.offsetAndRotation(4, 0, 0, 0, 0, 0));
        
        PartDefinition lower_right_leg = right_leg.addOrReplaceChild("lower_right_leg",
            CubeListBuilder.create()
                .texOffs(0, 28)
                .addBox(-8, -6, -2, 4, 6, 4),
            PartPose.offsetAndRotation(2, 12, 0, 0, 0, 0));
        
        lower_right_leg.addOrReplaceChild("leg8",
            CubeListBuilder.create()
                .texOffs(113, 49)
                .addBox(-8, -6.05F, 2.12F, 4, 4, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0.03490658503988659F, 0, 0));
        
        lower_right_leg.addOrReplaceChild("leg2",
            CubeListBuilder.create()
                .texOffs(113, 70)
                .addBox(-8.73F, -5.85F, -2, 1, 4, 4),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 0.03490658503988659F));
        
        lower_right_leg.addOrReplaceChild("leg6",
            CubeListBuilder.create()
                .texOffs(89, 65)
                .addBox(-8, -6, -2.4F, 3, 4, 1),
            PartPose.ZERO);
        
        right_leg.addOrReplaceChild("leg4",
            CubeListBuilder.create()
                .texOffs(92, 49)
                .addBox(-6, 2, -2.4F, 3, 4, 1),
            PartPose.ZERO);
        
        right_leg.addOrReplaceChild("leg1",
            CubeListBuilder.create()
                .texOffs(100, 80)
                .addBox(-6.3F, 0.2F, -2, 1, 6, 4),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 0.03490658503988659F));
        
        right_leg.addOrReplaceChild("leg3",
            CubeListBuilder.create()
                .texOffs(100, 63)
                .addBox(-6, 0, -2.4F, 4, 2, 1),
            PartPose.ZERO);
        
        right_leg.addOrReplaceChild("leg5",
            CubeListBuilder.create()
                .texOffs(119, 60)
                .addBox(-2.9F, 2.5F, -2.36F, 2, 4, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0, 0, 0.3490658503988659F));
        
        right_leg.addOrReplaceChild("leg7",
            CubeListBuilder.create()
                .texOffs(113, 49)
                .addBox(-6, 0, 1.7F, 4, 6, 1),
            PartPose.offsetAndRotation(0, 0, 0, 0.03490658503988659F, 0, 0));
        
        PartDefinition right_arm = root.addOrReplaceChild("right_arm",
            CubeListBuilder.create()
                .texOffs(40, 16)
                .addBox(-3, -2, -2, 4, 6, 4),
            PartPose.offsetAndRotation(-5, 2, 0, 0, 0, 0));
        
        right_arm.addOrReplaceChild("cloak_arm9_1",
            CubeListBuilder.create()
                .texOffs(66, 58)
                .addBox(-3, -2.2F, -2, 4, 1, 4),
            PartPose.ZERO);
        
        right_arm.addOrReplaceChild("cloak_arm2_1",
            CubeListBuilder.create()
                .texOffs(43, 70)
                .addBox(-3, -2, 1.28F, 4, 6, 1),
            PartPose.ZERO);
        
        right_arm.addOrReplaceChild("cloak_arm4_1",
            CubeListBuilder.create()
                .texOffs(57, 70)
                .addBox(0.12F, -2, -2, 1, 6, 4),
            PartPose.ZERO);
        
        right_arm.addOrReplaceChild("cloak_arm3_1",
            CubeListBuilder.create()
                .texOffs(57, 70)
                .addBox(-3.2F, -2, -2, 1, 6, 4),
            PartPose.ZERO);
        
        PartDefinition lower_right_arm = right_arm.addOrReplaceChild("lower_right_arm",
            CubeListBuilder.create()
                .texOffs(40, 28)
                .addBox(2, 2, -2, 4, 6, 4),
            PartPose.offsetAndRotation(-5, 2, 0, 0, 0, 0));
        
        lower_right_arm.addOrReplaceChild("cloak_arm5_1",
            CubeListBuilder.create()
                .texOffs(70, 70)
                .addBox(2, 2, -2.28F, 4, 5, 1),
            PartPose.ZERO);
        
        lower_right_arm.addOrReplaceChild("cloak_arm6_1",
            CubeListBuilder.create()
                .texOffs(70, 70)
                .addBox(2, 2, 1.28F, 4, 5, 1),
            PartPose.ZERO);
        
        lower_right_arm.addOrReplaceChild("cloak_arm8_1",
            CubeListBuilder.create()
                .texOffs(81, 70)
                .addBox(5.12F, 2, -2, 1, 5, 4),
            PartPose.ZERO);
        
        lower_right_arm.addOrReplaceChild("cloak_arm7_1",
            CubeListBuilder.create()
                .texOffs(81, 70)
                .addBox(1.8F, 2, -2, 1, 5, 4),
            PartPose.ZERO);
        
        right_arm.addOrReplaceChild("cloak_arm1_1",
            CubeListBuilder.create()
                .texOffs(43, 70)
                .addBox(-3, -2, -2.28F, 4, 6, 1),
            PartPose.ZERO);
        
        return LayerDefinition.create(definition, 150, 100);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer vertexConsumer, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        this.leftLeg.visible = true;
        this.rightLeg.visible = true;
        this.head.visible = true;
        super.renderToBuffer(matrixStack, vertexConsumer, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
    }

    // May need to implement the rendering parts depending on what you are doing
}
