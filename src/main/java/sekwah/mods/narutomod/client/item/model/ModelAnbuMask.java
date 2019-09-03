package sekwah.mods.narutomod.client.item.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.client.player.models.ModelNinjaBiped;

public class ModelAnbuMask extends ModelNinjaBiped
{
  public ModelRenderer Mask;
  public ModelRenderer Mouth;
  public ModelRenderer EyebrowLeft;
  public ModelRenderer EyebrowRight;
  public ModelRenderer Nose;
  public ModelRenderer RightEar;
  public ModelRenderer LeftEar;
  public ModelRenderer HeadBandLeft;
  public ModelRenderer HeadbandRight;
  public ModelRenderer HeadbandBack;

  public ModelRenderer headLock;

  public ModelAnbuMask()
  {
    textureWidth = 64;
    textureHeight = 32;

    Mask = new ModelRenderer(this, 32, 0);
    Mask.addBox(-4F, -8.466666F, -4.266667F, 8, 9, 1, 0.01F);
    Mask.setRotationPoint(0F, 0F, 0F);
    Mask.setTextureSize(64, 32);
    Mask.mirror = true;
    setRotation(Mask, 0F, 0F, 0F);
    Mouth = new ModelRenderer(this, 50, 8);
    Mouth.addBox(-2F, -5F, -5F, 4, 5, 1);
    Mouth.setRotationPoint(0F, 0F, 0F);
    Mouth.setTextureSize(64, 32);
    Mouth.mirror = true;
    setRotation(Mouth, -0.0743572F, 0F, 0F);
    EyebrowLeft = new ModelRenderer(this, 50, 3);
    EyebrowLeft.addBox(1F, -6F, -5F, 2, 1, 1);
    EyebrowLeft.setRotationPoint(0F, 0F, 0F);
    EyebrowLeft.setTextureSize(64, 32);
    EyebrowLeft.mirror = true;
    setRotation(EyebrowLeft, 0F, 0F, 0F);
    EyebrowRight = new ModelRenderer(this, 50, 3);
    EyebrowRight.addBox(-3F, -6F, -5F, 2, 1, 1);
    EyebrowRight.setRotationPoint(0F, 0F, 0F);
    EyebrowRight.setTextureSize(64, 32);
    EyebrowRight.mirror = true;
    setRotation(EyebrowRight, 0F, 0F, 0F);
    Nose = new ModelRenderer(this, 50, 5);
    Nose.addBox(-1F, -5F, -5F, 2, 2, 1);
    Nose.setRotationPoint(0F, 0F, 0F);
    Nose.setTextureSize(64, 32);
    Nose.mirror = true;
    setRotation(Nose, 0F, 0F, 0F);
    RightEar = new ModelRenderer(this, 50, 0);
    RightEar.addBox(-8.066667F, -6F, -4.3F, 2, 2, 1);
    RightEar.setRotationPoint(-0.35F, 0.04F, 0F);
    RightEar.setTextureSize(64, 32);
    RightEar.mirror = true;
    setRotation(RightEar, 0F, 0F, 0.7853982F);
    LeftEar = new ModelRenderer(this, 50, 0);
    LeftEar.addBox(-5F, -9F, -4.3F, 2, 2, 1);
    LeftEar.setRotationPoint(-0.9333333F, 0F, 0F);
    LeftEar.setTextureSize(64, 32);
    LeftEar.mirror = true;
    setRotation(LeftEar, 0F, 0F, 0.7853982F);
    HeadBandLeft = new ModelRenderer(this, 60, 23);
    HeadBandLeft.addBox(3.1F, -3.6F, 3F, 1, 8, 1);
    HeadBandLeft.setRotationPoint(0F, 0F, 0F);
    HeadBandLeft.setTextureSize(64, 32);
    HeadBandLeft.mirror = true;
    setRotation(HeadBandLeft, 1.570796F, 0F, 0F);
    HeadbandRight = new ModelRenderer(this, 60, 23);
    HeadbandRight.addBox(-4.1F, -3.733333F, 3F, 1, 8, 1);
    HeadbandRight.setRotationPoint(0F, 0F, 0F);
    HeadbandRight.setTextureSize(64, 32);
    HeadbandRight.mirror = true;
    setRotation(HeadbandRight, 1.570796F, 0F, 0F);
    HeadbandBack = new ModelRenderer(this, 60, 23);
    HeadbandBack.addBox(-4.4F, -4F, 3F, 1, 8, 1);
    HeadbandBack.setRotationPoint(0F, 0F, 0F);
    HeadbandBack.setTextureSize(64, 32);
    HeadbandBack.mirror = true;
    setRotation(HeadbandBack, 1.570796F, 1.570796F, 0F);

    headLock = new ModelRenderer(this, 1, 1);
    headLock.addBox(0F, 0F, 0F, 0, 0, 0);
    headLock.setRotationPoint(0F, 0F, 0F);

    headLock.addChild(Mask);
    headLock.addChild(Mouth);
    headLock.addChild(EyebrowLeft);
    headLock.addChild(EyebrowRight);
    headLock.addChild(Nose);
    headLock.addChild(RightEar);
    headLock.addChild(LeftEar);
    headLock.addChild(HeadBandLeft);
    headLock.addChild(HeadbandRight);
    headLock.addChild(HeadbandBack);

  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);

    renderWithLock(this.bipedHead, headLock, f5);
  }

  private void renderWithLock(ModelRenderer bipedBody, ModelRenderer lockblock, float f5) {

    double scale = 1.1D;
    GL11.glPushMatrix();

    setRotation(lockblock, bipedBody.rotateAngleX, bipedBody.rotateAngleY, bipedBody.rotateAngleZ);

    lockblock.setRotationPoint(0,0,0);

    GL11.glTranslatef(bipedBody.rotationPointX / 16, bipedBody.rotationPointY / 16, bipedBody.rotationPointZ / 16);

    GL11.glScaled(scale, scale, scale);

    lockblock.render(f5);

    GL11.glPopMatrix();

    /*setRotation(lockblock, bipedBody.rotateAngleX, bipedBody.rotateAngleY, bipedBody.rotateAngleZ);

    lockblock.setRotationPoint(bipedBody.rotationPointX, bipedBody.rotationPointY, bipedBody.rotationPointZ);

    lockblock.render(f5);*/

  }

  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
