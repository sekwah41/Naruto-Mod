package sekwah.mods.narutomod.client.item.model.armour;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAkatsukiCloak extends ModelNinjaArmour {

    public ModelRenderer HeadNeck6;
    public ModelRenderer HeadNeck1;
    public ModelRenderer HeadNeck3;
    public ModelRenderer HeadNeck4;
    public ModelRenderer HeadNeck5;
    public ModelRenderer Cloak1;
    public ModelRenderer Cloak2;
    public ModelRenderer Cloak5;
    public ModelRenderer Cloak6;
    public ModelRenderer Cloak3;
    public ModelRenderer Cloak4;
    public ModelRenderer Cloak7;
    public ModelRenderer Cloak8;
    public ModelRenderer Cloak9;
    public ModelRenderer CloakArm1;
    public ModelRenderer CloakArm2;
    public ModelRenderer CloakArm3;
    public ModelRenderer CloakArm4;
    public ModelRenderer CloakArm9;
    public ModelRenderer CloakArm5;
    public ModelRenderer CloakArm6;
    public ModelRenderer CloakArm7;
    public ModelRenderer CloakArm8;
    public ModelRenderer CloakArm1_1;
    public ModelRenderer CloakArm3_1;
    public ModelRenderer CloakArm2_1;
    public ModelRenderer CloakArm4_1;
    public ModelRenderer CloakArm9_1;
    public ModelRenderer CloakArm5_1;
    public ModelRenderer CloakArm7_1;
    public ModelRenderer CloakArm6_1;
    public ModelRenderer CloakArm8_1;
    public ModelRenderer Leg1;
    public ModelRenderer Leg3;
    public ModelRenderer Leg4;
    public ModelRenderer Leg5;
    public ModelRenderer Leg7;
    public ModelRenderer Leg2;
    public ModelRenderer Leg6;
    public ModelRenderer Leg8;
    public ModelRenderer Leg1_1;
    public ModelRenderer Leg3_1;
    public ModelRenderer Leg4_1;
    public ModelRenderer Leg5_1;
    public ModelRenderer Leg7_1;
    public ModelRenderer Leg2_1;
    public ModelRenderer Leg6_1;
    public ModelRenderer Leg8_1;

    public ModelAkatsukiCloak() {
        super(true, true, true);
        this.textureWidth = 150;
        this.textureHeight = 100;
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.LowerRightLeg = new ModelRenderer(this, 0, 28);
        this.LowerRightLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LowerRightLeg.addBox(-8.0F, -6.0F, -2.0F, 4, 6, 4, 0.0F);
        this.CloakArm9_1 = new ModelRenderer(this, 66, 58);
        this.CloakArm9_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm9_1.addBox(-3.0F, -2.2F, -2.0F, 4, 1, 4, 0.0F);
        this.Leg2_1 = new ModelRenderer(this, 113, 70);
        this.Leg2_1.mirror = true;
        this.Leg2_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg2_1.addBox(-0.27F, -6.15F, -2.0F, 1, 4, 4, 0.0F);
        this.setRotateAngle(Leg2_1, 0.0F, 0.0F, -0.03490658503988659F);
        this.CloakArm8 = new ModelRenderer(this, 81, 70);
        this.CloakArm8.mirror = true;
        this.CloakArm8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm8.addBox(-6.12F, 2.0F, -2.0F, 1, 5, 4, 0.0F);
        this.HeadNeck5 = new ModelRenderer(this, 0, 81);
        this.HeadNeck5.mirror = true;
        this.HeadNeck5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HeadNeck5.addBox(-4.0F, -3.25F, 3.55F, 8, 3, 1, 0.0F);
        this.setRotateAngle(HeadNeck5, -0.08726646259971647F, 0.0F, 0.0F);
        this.CloakArm2 = new ModelRenderer(this, 43, 70);
        this.CloakArm2.mirror = true;
        this.CloakArm2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm2.addBox(-1.0F, -2.0F, 1.28F, 4, 6, 1, 0.0F);
        this.Leg4 = new ModelRenderer(this, 92, 49);
        this.Leg4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg4.addBox(-6.0F, 2.0F, -2.4F, 3, 4, 1, 0.0F);
        this.CloakArm2_1 = new ModelRenderer(this, 43, 70);
        this.CloakArm2_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm2_1.addBox(-3.0F, -2.0F, 1.28F, 4, 6, 1, 0.0F);
        this.HeadNeck1 = new ModelRenderer(this, 24, 73);
        this.HeadNeck1.mirror = true;
        this.HeadNeck1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HeadNeck1.addBox(3.45F, -3.25F, -4.0F, 1, 3, 8, 0.0F);
        this.setRotateAngle(HeadNeck1, 0.0F, 0.0F, 0.08726646259971647F);
        this.UpperLeftArm = new ModelRenderer(this, 40, 16);
        this.UpperLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.UpperLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 6, 4, 0.0F);
        this.HeadNeck3 = new ModelRenderer(this, 24, 73);
        this.HeadNeck3.mirror = true;
        this.HeadNeck3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HeadNeck3.addBox(-4.45F, -3.25F, -4.0F, 1, 3, 8, 0.0F);
        this.setRotateAngle(HeadNeck3, 0.0F, 0.0F, -0.08726646259971647F);
        this.CloakArm6 = new ModelRenderer(this, 70, 70);
        this.CloakArm6.mirror = true;
        this.CloakArm6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm6.addBox(-6.0F, 2.0F, 1.28F, 4, 5, 1, 0.0F);
        this.LowerBody = new ModelRenderer(this, 16, 28);
        this.LowerBody.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.LowerBody.addBox(-4.0F, 0.0F, -2.0F, 8, 6, 4, 0.0F);
        this.CloakArm5 = new ModelRenderer(this, 70, 70);
        this.CloakArm5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm5.addBox(-6.0F, 2.0F, -2.28F, 4, 5, 1, 0.0F);
        this.CloakArm5_1 = new ModelRenderer(this, 70, 70);
        this.CloakArm5_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm5_1.addBox(2.0F, 2.0F, -2.28F, 4, 5, 1, 0.0F);
        this.Cloak4 = new ModelRenderer(this, 20, 60);
        this.Cloak4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloak4.addBox(-4.0F, 0.0F, 1.5F, 8, 6, 1, 0.0F);
        this.setRotateAngle(Cloak4, 0.03490658503988659F, 0.0F, 0.0F);
        this.Cloak8 = new ModelRenderer(this, 55, 52);
        this.Cloak8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloak8.addBox(3.25F, 0.0F, -2.0F, 1, 6, 4, 0.0F);
        this.Leg1 = new ModelRenderer(this, 100, 80);
        this.Leg1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg1.addBox(-6.3F, 0.2F, -2.0F, 1, 6, 4, 0.0F);
        this.setRotateAngle(Leg1, 0.0F, 0.0F, 0.03490658503988659F);
        this.Leg5_1 = new ModelRenderer(this, 112, 60);
        this.Leg5_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg5_1.addBox(-2.4F, 1.4F, -2.42F, 1, 4, 1, 0.0F);
        this.setRotateAngle(Leg5_1, 0.0F, 0.0F, -0.24434609527920614F);
        this.Cloak6 = new ModelRenderer(this, 55, 40);
        this.Cloak6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloak6.addBox(3.2F, 0.0F, -2.0F, 1, 6, 4, 0.0F);
        this.Cloak2 = new ModelRenderer(this, 20, 50);
        this.Cloak2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloak2.addBox(-4.0F, 0.0F, 1.3F, 8, 6, 1, 0.0F);
        this.setRotateAngle(Cloak2, 0.03490658503988659F, 0.0F, 0.0F);
        this.Cloak9 = new ModelRenderer(this, 0, 92);
        this.Cloak9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloak9.addBox(-4.0F, -0.2F, -2.0F, 8, 1, 4, 0.0F);
        this.Leg3_1 = new ModelRenderer(this, 100, 60);
        this.Leg3_1.mirror = true;
        this.Leg3_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg3_1.addBox(-2.0F, 0.0F, -2.4F, 4, 2, 1, 0.0F);
        this.Leg6_1 = new ModelRenderer(this, 89, 60);
        this.Leg6_1.mirror = true;
        this.Leg6_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg6_1.addBox(-3.0F, -6.0F, -2.4F, 3, 4, 1, 0.0F);
        this.HeadNeck6 = new ModelRenderer(this, 0, 91);
        this.HeadNeck6.mirror = true;
        this.HeadNeck6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HeadNeck6.addBox(-4.0F, -0.7F, -4.0F, 8, 1, 8, 0.0F);
        this.Leg3 = new ModelRenderer(this, 100, 63);
        this.Leg3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg3.addBox(-6.0F, 0.0F, -2.4F, 4, 2, 1, 0.0F);
        this.CloakArm9 = new ModelRenderer(this, 66, 58);
        this.CloakArm9.mirror = true;
        this.CloakArm9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm9.addBox(-1.0F, -2.2F, -2.0F, 4, 1, 4, 0.0F);
        this.Leg5 = new ModelRenderer(this, 119, 60);
        this.Leg5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg5.addBox(-2.9F, 2.5F, -2.36F, 2, 4, 1, 0.0F);
        this.setRotateAngle(Leg5, 0.0F, 0.0F, 0.3490658503988659F);
        this.UpperBody = new ModelRenderer(this, 16, 16);
        this.UpperBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.UpperBody.addBox(-4.0F, 0.0F, -2.0F, 8, 6, 4, 0.0F);
        this.Leg1_1 = new ModelRenderer(this, 100, 70);
        this.Leg1_1.mirror = true;
        this.Leg1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg1_1.addBox(1.3F, 0.0F, -2.0F, 1, 6, 4, 0.0F);
        this.setRotateAngle(Leg1_1, 0.0F, 0.0F, -0.03490658503988659F);
        this.LowerLeftArm = new ModelRenderer(this, 40, 28);
        this.LowerLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.LowerLeftArm.addBox(-6.0F, 2.0F, -2.0F, 4, 6, 4, 0.0F);
        this.CloakArm4_1 = new ModelRenderer(this, 57, 70);
        this.CloakArm4_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm4_1.addBox(0.12F, -2.0F, -2.0F, 1, 6, 4, 0.0F);
        this.CloakArm1 = new ModelRenderer(this, 43, 70);
        this.CloakArm1.mirror = true;
        this.CloakArm1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm1.addBox(-1.0F, -2.0F, -2.28F, 4, 6, 1, 0.0F);
        this.Cloak3 = new ModelRenderer(this, 0, 60);
        this.Cloak3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloak3.addBox(-4.0F, 0.0F, -2.4F, 8, 6, 1, 0.0F);
        this.Leg7_1 = new ModelRenderer(this, 113, 49);
        this.Leg7_1.mirror = true;
        this.Leg7_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg7_1.addBox(-2.0F, 0.0F, 1.7F, 4, 6, 1, 0.0F);
        this.setRotateAngle(Leg7_1, 0.03490658503988659F, 0.0F, 0.0F);
        this.CloakArm6_1 = new ModelRenderer(this, 70, 70);
        this.CloakArm6_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm6_1.addBox(2.0F, 2.0F, 1.28F, 4, 5, 1, 0.0F);
        this.CloakArm3_1 = new ModelRenderer(this, 57, 70);
        this.CloakArm3_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm3_1.addBox(-3.2F, -2.0F, -2.0F, 1, 6, 4, 0.0F);
        this.LowerLeftLeg = new ModelRenderer(this, 0, 28);
        this.LowerLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LowerLeftLeg.addBox(-4.0F, -6.0F, -2.0F, 4, 6, 4, 0.0F);
        this.Leg8 = new ModelRenderer(this, 113, 49);
        this.Leg8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg8.addBox(-8.0F, -6.05F, 2.12F, 4, 4, 1, 0.0F);
        this.setRotateAngle(Leg8, 0.03490658503988659F, 0.0F, 0.0F);
        this.Cloak1 = new ModelRenderer(this, 0, 50);
        this.Cloak1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloak1.addBox(-4.0F, 0.0F, -2.4F, 8, 6, 1, 0.0F);
        this.Cloak7 = new ModelRenderer(this, 43, 51);
        this.Cloak7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloak7.addBox(-4.25F, 0.0F, -2.0F, 1, 6, 4, 0.0F);
        this.CloakArm8_1 = new ModelRenderer(this, 81, 70);
        this.CloakArm8_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm8_1.addBox(5.12F, 2.0F, -2.0F, 1, 5, 4, 0.0F);
        this.UpperLeftLeg = new ModelRenderer(this, 0, 16);
        this.UpperLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.UpperLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.Leg2 = new ModelRenderer(this, 113, 70);
        this.Leg2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg2.addBox(-8.73F, -5.85F, -2.0F, 1, 4, 4, 0.0F);
        this.setRotateAngle(Leg2, 0.0F, 0.0F, 0.03490658503988659F);
        this.Leg8_1 = new ModelRenderer(this, 113, 49);
        this.Leg8_1.mirror = true;
        this.Leg8_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg8_1.addBox(-4.0F, -6.05F, 2.12F, 4, 4, 1, 0.0F);
        this.setRotateAngle(Leg8_1, 0.03490658503988659F, 0.0F, 0.0F);
        this.UpperRightLeg = new ModelRenderer(this, 0, 16);
        this.UpperRightLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.UpperRightLeg.addBox(-6.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.HeadNeck4 = new ModelRenderer(this, 0, 73);
        this.HeadNeck4.mirror = true;
        this.HeadNeck4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HeadNeck4.addBox(-4.0F, -3.25F, -4.65F, 8, 3, 1, 0.0F);
        this.setRotateAngle(HeadNeck4, 0.08726646259971647F, 0.0F, 0.0F);
        this.CloakArm4 = new ModelRenderer(this, 57, 70);
        this.CloakArm4.mirror = true;
        this.CloakArm4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm4.addBox(-1.12F, -2.0F, -2.0F, 1, 6, 4, 0.0F);
        this.Cloak5 = new ModelRenderer(this, 43, 40);
        this.Cloak5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Cloak5.addBox(-4.2F, 0.0F, -2.0F, 1, 6, 4, 0.0F);
        this.CloakArm3 = new ModelRenderer(this, 57, 70);
        this.CloakArm3.mirror = true;
        this.CloakArm3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm3.addBox(2.2F, -2.0F, -2.0F, 1, 6, 4, 0.0F);
        this.Leg6 = new ModelRenderer(this, 89, 65);
        this.Leg6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg6.addBox(-8.0F, -6.0F, -2.4F, 3, 4, 1, 0.0F);
        this.CloakArm7 = new ModelRenderer(this, 81, 70);
        this.CloakArm7.mirror = true;
        this.CloakArm7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm7.addBox(-2.8F, 2.0F, -2.0F, 1, 5, 4, 0.0F);
        this.Leg4_1 = new ModelRenderer(this, 100, 49);
        this.Leg4_1.mirror = true;
        this.Leg4_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg4_1.addBox(-1.0F, 2.0F, -2.4F, 3, 4, 1, 0.0F);
        this.LowerRightArm = new ModelRenderer(this, 40, 28);
        this.LowerRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.LowerRightArm.addBox(2.0F, 2.0F, -2.0F, 4, 6, 4, 0.0F);
        this.CloakArm1_1 = new ModelRenderer(this, 43, 70);
        this.CloakArm1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm1_1.addBox(-3.0F, -2.0F, -2.28F, 4, 6, 1, 0.0F);
        this.Leg7 = new ModelRenderer(this, 113, 49);
        this.Leg7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Leg7.addBox(-6.0F, 0.0F, 1.7F, 4, 6, 1, 0.0F);
        this.setRotateAngle(Leg7, 0.03490658503988659F, 0.0F, 0.0F);
        this.UpperRightArm = new ModelRenderer(this, 40, 16);
        this.UpperRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.UpperRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 6, 4, 0.0F);
        this.CloakArm7_1 = new ModelRenderer(this, 81, 70);
        this.CloakArm7_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.CloakArm7_1.addBox(1.8F, 2.0F, -2.0F, 1, 5, 4, 0.0F);
        this.UpperRightLeg.addChild(this.LowerRightLeg);
        this.UpperRightArm.addChild(this.CloakArm9_1);
        this.LowerLeftLeg.addChild(this.Leg2_1);
        this.LowerLeftArm.addChild(this.CloakArm8);
        this.Head.addChild(this.HeadNeck5);
        this.UpperLeftArm.addChild(this.CloakArm2);
        this.UpperRightLeg.addChild(this.Leg4);
        this.UpperRightArm.addChild(this.CloakArm2_1);
        this.Head.addChild(this.HeadNeck1);
        this.Head.addChild(this.HeadNeck3);
        this.LowerLeftArm.addChild(this.CloakArm6);
        this.UpperBody.addChild(this.LowerBody);
        this.LowerLeftArm.addChild(this.CloakArm5);
        this.LowerRightArm.addChild(this.CloakArm5_1);
        this.LowerBody.addChild(this.Cloak4);
        this.LowerBody.addChild(this.Cloak8);
        this.UpperRightLeg.addChild(this.Leg1);
        this.UpperLeftLeg.addChild(this.Leg5_1);
        this.UpperBody.addChild(this.Cloak6);
        this.UpperBody.addChild(this.Cloak2);
        this.UpperBody.addChild(this.Cloak9);
        this.UpperLeftLeg.addChild(this.Leg3_1);
        this.LowerLeftLeg.addChild(this.Leg6_1);
        this.Head.addChild(this.HeadNeck6);
        this.UpperRightLeg.addChild(this.Leg3);
        this.UpperLeftArm.addChild(this.CloakArm9);
        this.UpperRightLeg.addChild(this.Leg5);
        this.UpperLeftLeg.addChild(this.Leg1_1);
        this.UpperLeftArm.addChild(this.LowerLeftArm);
        this.UpperRightArm.addChild(this.CloakArm4_1);
        this.UpperLeftArm.addChild(this.CloakArm1);
        this.LowerBody.addChild(this.Cloak3);
        this.UpperLeftLeg.addChild(this.Leg7_1);
        this.LowerRightArm.addChild(this.CloakArm6_1);
        this.UpperRightArm.addChild(this.CloakArm3_1);
        this.UpperLeftLeg.addChild(this.LowerLeftLeg);
        this.LowerRightLeg.addChild(this.Leg8);
        this.UpperBody.addChild(this.Cloak1);
        this.LowerBody.addChild(this.Cloak7);
        this.LowerRightArm.addChild(this.CloakArm8_1);
        this.LowerRightLeg.addChild(this.Leg2);
        this.LowerLeftLeg.addChild(this.Leg8_1);
        this.Head.addChild(this.HeadNeck4);
        this.UpperLeftArm.addChild(this.CloakArm4);
        this.UpperBody.addChild(this.Cloak5);
        this.UpperLeftArm.addChild(this.CloakArm3);
        this.LowerRightLeg.addChild(this.Leg6);
        this.LowerLeftArm.addChild(this.CloakArm7);
        this.UpperLeftLeg.addChild(this.Leg4_1);
        this.UpperRightArm.addChild(this.LowerRightArm);
        this.UpperRightArm.addChild(this.CloakArm1_1);
        this.UpperRightLeg.addChild(this.Leg7);
        this.LowerRightArm.addChild(this.CloakArm7_1);

        this.cleanupModel();
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        this.renderFull(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
