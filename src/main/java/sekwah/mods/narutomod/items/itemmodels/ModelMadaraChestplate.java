package sekwah.mods.narutomod.items.itemmodels;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import sekwah.mods.narutomod.player.models.ModelNinjaBiped;

public class ModelMadaraChestplate extends ModelNinjaBiped {
    public ModelRenderer Underlayer_upper;
    public ModelRenderer Underlayer_lower;
    public ModelRenderer topshoulder_plate_1;
    public ModelRenderer topshoulder_plate_2;
    public ModelRenderer Neck_guard_1;
    public ModelRenderer Neck_guard_2;
    public ModelRenderer bottomshoulder_pad_1;
    public ModelRenderer Back_plate_bottom;
    public ModelRenderer Back_plate_top;
    public ModelRenderer Front_plate_top;
    public ModelRenderer Front_plate_bottom;
    public ModelRenderer bottomshoulder_pad_2;
    public ModelRenderer sleave_left;
    public ModelRenderer sleave_right;

    public ModelRenderer bodyLock;
    public ModelRenderer bodyLockLower;
    public ModelRenderer armLockRight;
    public ModelRenderer armLockLeft;

    public ModelMadaraChestplate() {
        textureWidth = 64;
        textureHeight = 64;

        Underlayer_upper = new ModelRenderer(this, 38, 34);
        Underlayer_upper.addBox(-4F, -0.01F, -2.5F, 8, 6, 5, 0.01F);
        Underlayer_upper.setRotationPoint(0F, 0F, 0F);
        Underlayer_upper.setTextureSize(64, 64);
        Underlayer_upper.mirror = true;
        setRotation(Underlayer_upper, 0F, 0F, 0F);
        Underlayer_lower = new ModelRenderer(this, 38, 0);
        Underlayer_lower.addBox(-4F, -0.01F, -2.5F, 8, 6, 5, 0.01F);
        Underlayer_lower.setRotationPoint(0F, 0F, 0F);
        Underlayer_lower.setTextureSize(64, 64);
        Underlayer_lower.mirror = true;
        setRotation(Underlayer_lower, 0F, 0F, 0F);
        topshoulder_plate_1 = new ModelRenderer(this, 0, 38);
        topshoulder_plate_1.addBox(-2F, -3.333333F, -3F, 4, 3, 6);
        topshoulder_plate_1.setRotationPoint(0F, 0F, 0F);
        topshoulder_plate_1.setTextureSize(64, 64);
        topshoulder_plate_1.mirror = true;
        setRotation(topshoulder_plate_1, 0F, 0F, -0.2268928F);
        Back_plate_bottom = new ModelRenderer(this, 0, 39);
        Back_plate_bottom.addBox(-3.5F, 0.666667F, 2F, 7, 5, 1);
        Back_plate_bottom.setRotationPoint(0F, 0F, 0F);
        Back_plate_bottom.setTextureSize(64, 64);
        Back_plate_bottom.mirror = true;
        setRotation(Back_plate_bottom, 0F, 0F, 0F);
        Back_plate_top = new ModelRenderer(this, 0, 40);
        Back_plate_top.addBox(-3F, 1.733333F, 2F, 6, 5, 1);
        Back_plate_top.setRotationPoint(0F, 0F, 0F);
        Back_plate_top.setTextureSize(64, 64);
        Back_plate_top.mirror = true;
        setRotation(Back_plate_top, 0F, 0F, 0F);
        Front_plate_top = new ModelRenderer(this, 0, 40);
        Front_plate_top.addBox(-3F, 1.7F, -3F, 6, 5, 1);
        Front_plate_top.setRotationPoint(0F, 0F, 0F);
        Front_plate_top.setTextureSize(64, 64);
        Front_plate_top.mirror = true;
        setRotation(Front_plate_top, 0F, 0F, 0F);
        Front_plate_bottom = new ModelRenderer(this, 0, 39);
        Front_plate_bottom.addBox(-3.5F, 0.7F, -3F, 7, 5, 1);
        Front_plate_bottom.setRotationPoint(0F, 0F, 0F);
        Front_plate_bottom.setTextureSize(64, 64);
        Front_plate_bottom.mirror = true;
        setRotation(Front_plate_bottom, 0F, 0F, 0F);
        bottomshoulder_pad_2 = new ModelRenderer(this, 0, 39);
        bottomshoulder_pad_2.addBox(-4F, -2.966667F, -2.5F, 3, 2, 5);
        bottomshoulder_pad_2.setRotationPoint(0F, 0F, 0F);
        bottomshoulder_pad_2.setTextureSize(64, 64);
        bottomshoulder_pad_2.mirror = true;
        setRotation(bottomshoulder_pad_2, 0F, 0F, -0.296706F);
        sleave_left = new ModelRenderer(this, 40, 0);
        sleave_left.addBox(-3F, -2F, -2F, 4, 7, 4, 0.01F);
        sleave_left.setRotationPoint(0F, 0F, 0F);
        sleave_left.setTextureSize(64, 64);
        sleave_left.mirror = true;
        setRotation(sleave_left, 0F, 0F, 0F);


        Neck_guard_1 = new ModelRenderer(this, 0, 38);
        Neck_guard_1.addBox(0F, -5F, -2.5F, 1, 2, 5);
        Neck_guard_1.setRotationPoint(0F, 0F, 0F);
        Neck_guard_1.setTextureSize(64, 64);
        Neck_guard_1.mirror = true;
        setRotation(Neck_guard_1, 0F, 0F, 0F);


        sleave_right = new ModelRenderer(this, 40, 0);
        sleave_right.addBox(-1F, -2F, -2F, 4, 7, 4, 0.01F);
        sleave_right.setRotationPoint(0F, 0F, 0F);
        sleave_right.setTextureSize(64, 64);
        sleave_right.mirror = true;
        setRotation(sleave_right, 0F, 0F, 0F);

        bottomshoulder_pad_1 = new ModelRenderer(this, 0, 39);
        bottomshoulder_pad_1.addBox(0.8666667F, -2.866667F, -2.5F, 3, 2, 5);
        bottomshoulder_pad_1.setRotationPoint(0F, 0F, 0F);
        bottomshoulder_pad_1.setTextureSize(64, 64);
        bottomshoulder_pad_1.mirror = true;
        setRotation(bottomshoulder_pad_1, 0F, 0F, 0.296706F);

        topshoulder_plate_2 = new ModelRenderer(this, 0, 38);
        topshoulder_plate_2.addBox(-2F, -3.3F, -3F, 4, 3, 6);
        topshoulder_plate_2.setRotationPoint(0F, 0F, 0F);
        topshoulder_plate_2.setTextureSize(64, 64);
        topshoulder_plate_2.mirror = true;
        setRotation(topshoulder_plate_2, 0F, 0F, 0.2268928F);

        Neck_guard_2 = new ModelRenderer(this, 0, 39);
        Neck_guard_2.addBox(-1F, -5F, -2.5F, 1, 2, 5);
        Neck_guard_2.setRotationPoint(0F, 0F, 0F);
        Neck_guard_2.setTextureSize(64, 64);
        Neck_guard_2.mirror = true;
        setRotation(Neck_guard_2, 0F, 0F, 0F);


        bodyLock = new ModelRenderer(this, 1, 1);
        bodyLock.addBox(0F, 0F, 0F, 0, 0, 0);
        bodyLock.setRotationPoint(0F, 0F, 0F);

        bodyLockLower = new ModelRenderer(this, 1, 1);
        bodyLockLower.addBox(0F, 0F, 0F, 0, 0, 0);
        bodyLockLower.setRotationPoint(0F, 0F, 0F);

        armLockLeft = new ModelRenderer(this, 1, 1);
        armLockLeft.addBox(0F, 0F, 0F, 0, 0, 0);
        armLockLeft.setRotationPoint(0F, 0F, 0F);

        armLockRight = new ModelRenderer(this, 1, 1);
        armLockRight.addBox(0F, 0F, 0F, 0, 0, 0);
        armLockRight.setRotationPoint(0F, 0F, 0F);

        bodyLock.addChild(Front_plate_top);
        bodyLock.addChild(Back_plate_top);
        bodyLock.addChild(Underlayer_upper);

        bodyLockLower.addChild(Front_plate_bottom);
        bodyLockLower.addChild(Back_plate_bottom);
        bodyLockLower.addChild(Underlayer_lower);

        armLockRight.addChild(sleave_left);
        armLockRight.addChild(bottomshoulder_pad_2);
        armLockRight.addChild(topshoulder_plate_1);
        armLockRight.addChild(Neck_guard_1);

        armLockLeft.addChild(sleave_right);
        armLockLeft.addChild(bottomshoulder_pad_1);
        armLockLeft.addChild(topshoulder_plate_2);
        armLockLeft.addChild(Neck_guard_2);

    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        renderWithLock(bipedBody, bodyLock, f5);

        renderWithLock(bipedLowerBody, bodyLockLower, f5);

        renderWithLock(this.bipedLeftArmUpper, armLockLeft, f5);

        renderWithLock(this.bipedRightArmUpper, armLockRight, f5);
    }

    private void renderWithLock(ModelRenderer bipedBody, ModelRenderer lockblock, float f5) {

        setRotation(lockblock, bipedBody.rotateAngleX, bipedBody.rotateAngleY, bipedBody.rotateAngleZ);

        lockblock.setRotationPoint(bipedBody.rotationPointX, bipedBody.rotationPointY, bipedBody.rotationPointZ);

        lockblock.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
