package sekwah.mods.narutomod.client.item.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
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

	public ResourceLocation[] shine = {new ResourceLocation("narutomod:textures/armour/headband_shine.png"),
			new ResourceLocation("narutomod:textures/armour/headband_shine2.png"),
			new ResourceLocation("narutomod:textures/armour/headband_shine3.png"),
			new ResourceLocation("narutomod:textures/armour/headband_shine4.png"),
			new ResourceLocation("narutomod:textures/armour/headband_shine5.png")};

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

		//int headbandShine = (int) ((entity.worldObj.getTotalWorldTime() / 4f) % 5);

		int i = MathHelper.floor_double(entity.posX);
		int j = MathHelper.floor_double(entity.posZ);

		float brightness = 0;

		float sunangle = entity.worldObj.getCelestialAngle(1.0f) * 360.0F; // 0 and 360 is at the top

		float sundirection = sunangle > 180 ? 270 : 90;

		float playerRotations = entity.rotationYaw % 360 + (entity.rotationYaw < 0 ? 360 : 0);

		float diff = playerRotations - sundirection;

		if (diff < -180) {
			diff += 360;
		}

		if (entity.worldObj.blockExists(i, 0, j))
		{
			double d0 = (entity.boundingBox.maxY - entity.boundingBox.minY) * 0.66D;
			int k = MathHelper.floor_double(entity.posY - (double)entity.yOffset + d0);
			brightness = entity.worldObj.getLightBrightness(i, k, j) * entity.worldObj.getSunBrightness(1.0f);
		}
		float fadeAngle = Math.abs(diff - (diff > 180 ? 360 : 0));
		if(fadeAngle > 90 && fadeAngle < 120) {
			brightness *= 1f - ((fadeAngle - 90f) / (120f - 90f));
		}
		else if(fadeAngle >= 120) {
			brightness = 0;
		}

		int headbandShine = MathHelper.clamp_int((int) ((diff - (diff > 180 ? 360 : 0)) / 30 + 2), 0, 4);//(int) Math.max(0, Math.min(diff / 30 + 2, 4));
		float headbandOpacity = 1;

		renderWithLock(this.bipedHead, headLock, f5, headbandShine, headbandOpacity * brightness);


	}

	private void renderWithLock(ModelRenderer bipedBody, ModelRenderer lockblock, float f5, int headBandShine, float headbandOpacity) {
		double scale = 1.1D;
		GL11.glPushMatrix();

		setRotation(lockblock, bipedBody.rotateAngleX, bipedBody.rotateAngleY, bipedBody.rotateAngleZ);

		lockblock.setRotationPoint(0,0,0);

		GL11.glTranslatef(bipedBody.rotationPointX / 16, bipedBody.rotationPointY / 16, bipedBody.rotationPointZ / 16);

		GL11.glScaled(scale, scale, scale);

		lockblock.render(f5);

		lockblock.postRender(f5);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glColor4f(1, 1, 1, headbandOpacity);

		Minecraft.getMinecraft().renderEngine.bindTexture(shine[headBandShine]);

		Shape1.render(f5 * 1.001f);

		GL11.glColor4f(1, 1, 1, 1);

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
