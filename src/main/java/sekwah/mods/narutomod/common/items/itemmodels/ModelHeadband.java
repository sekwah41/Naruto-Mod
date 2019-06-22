package sekwah.mods.narutomod.common.items.itemmodels;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.client.player.models.ModelNinjaBiped;

public class ModelHeadband extends ModelNinjaBiped
{
	public ModelRenderer Shape3;
	public ModelRenderer Shape4;
	public ModelRenderer Shape2;
	public ModelRenderer Shape5;
	public ModelRenderer Shape1;
	
	public ModelRenderer Shape8;
	public ModelRenderer Shape9;
	public ModelRenderer Shape10;

	public ModelRenderer headLock;

	public ModelHeadband()
	{
		textureWidth = 64;
		textureHeight = 32;
		
		float yOffset = 1.6F;

		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(0F, 0F, 0F, 8, 2, 1);
		Shape3.setRotationPoint(-4F, -8F + yOffset, -4.233333F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 0);
		Shape4.addBox(0F, 0F, 0F, 8, 2, 1);
		Shape4.setRotationPoint(-4F, -8F + yOffset, 3.2F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(0F, 0F, 0F, 8, 2, 1);
		Shape2.setRotationPoint(3.1F, -8F + yOffset, 4F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 1.570796F, 0F);
		Shape5 = new ModelRenderer(this, 0, 0);
		Shape5.addBox(0F, 0F, 0F, 8, 2, 1);
		Shape5.setRotationPoint(-4.2F, -8F + yOffset, 4F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 1.570796F, 0F);
		Shape1 = new ModelRenderer(this, 19, 0);
		Shape1.addBox(0F, 0F, 0F, 3, 2, 1, 0.01F);
		Shape1.setRotationPoint(-1.5F, -8F + yOffset, -4.533333F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);

		Shape8 = new ModelRenderer(this, 0, 3);
		Shape8.addBox(-1F, 0F, 0F, 1, 3, 1);
		Shape8.setRotationPoint(0F, -6.333333F + yOffset, 3.6F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0.4833219F, 1.226894F, 0F);
		Shape9 = new ModelRenderer(this, 0, 3);
		Shape9.addBox(0F, 0F, 0F, 1, 3, 1);
		Shape9.setRotationPoint(0F, -7.066667F + yOffset, 4.7F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, -0.5576792F, 1.953013F, 0F);
		Shape10 = new ModelRenderer(this, 0, 0);
		Shape10.addBox(0F, 0F, 0F, 1, 1, 1);
		Shape10.setRotationPoint(0.2666667F, -7.533333F + yOffset, 3.933333F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0.4833219F, -0.3717861F, 0.8551081F);

		headLock = new ModelRenderer(this, 1, 1);
		headLock.addBox(0F, 0F, 0F, 0, 0, 0);
		headLock.setRotationPoint(0F, 0F, 0F);

		headLock.addChild(Shape1);
		headLock.addChild(Shape2);
		headLock.addChild(Shape3);
		headLock.addChild(Shape4);
		headLock.addChild(Shape5);
		headLock.addChild(Shape8);
		headLock.addChild(Shape9);
		headLock.addChild(Shape10);
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
