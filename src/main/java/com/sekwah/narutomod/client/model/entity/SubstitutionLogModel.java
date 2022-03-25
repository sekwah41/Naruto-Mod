package com.sekwah.narutomod.client.model.entity;
// Made with Blockbench 4.0.3
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sekwah.narutomod.NarutoMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class SubstitutionLogModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(NarutoMod.MOD_ID, "substitution_log"), "main");
	private final ModelPart main;

	public SubstitutionLogModel(ModelPart root) {
		this.main = root.getChild("main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(-5.1724F, -3.8587F, -2.55F, 5.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.6724F, 17.8587F, 0.05F));

		PartDefinition leaf_r1 = main.addOrReplaceChild("leaf_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.9449F, -1.2826F, 0.1F, 0.0F, 0.0F, -0.1745F));

		PartDefinition branch_r1 = main.addOrReplaceChild("branch_r1", CubeListBuilder.create().texOffs(0, 15).addBox(-0.1F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1724F, 0.1413F, -0.05F, 0.3491F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, buffer, packedLight, 655360);
	}
}