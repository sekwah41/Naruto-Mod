package sekwah.mods.narutomod.client.item.model.armour;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import sekwah.mods.narutomod.client.player.models.ModelNinjaBiped;

import java.util.ArrayList;
import java.util.List;

public class ModelNinjaArmour extends ModelNinjaBiped implements IRenderFirstPerson {

    private final boolean shouldArmRender;
    private final boolean hasOnSkinParts;

    public ModelRenderer Head;

    public ModelRenderer UpperLeftArm;
    public ModelRenderer UpperRightArm;
    public ModelRenderer LowerLeftArm;
    public ModelRenderer LowerRightArm;

    public ModelRenderer UpperBody;
    public ModelRenderer LowerBody;

    public ModelRenderer UpperRightLeg;
    public ModelRenderer UpperLeftLeg;
    public ModelRenderer LowerRightLeg;
    public ModelRenderer LowerLeftLeg;

    private boolean hasHead;

    private boolean hasUpperBody;
    private boolean hasLowerBody;

    private boolean hasUpperLeftArm;
    private boolean hasLowerLeftArm;

    private boolean hasUpperRightArm;
    private boolean hasLowerRightArm;

    private boolean hasUpperLeftLeg;
    private boolean hasLowerLeftLeg;

    private boolean hasUpperRightLeg;
    private boolean hasLowerRightLeg;

    // Because hero screwed up the models.
    private boolean herosFuckUpFix;

    private ModelRenderer heroFixLowerLeftLeg;
    private ModelRenderer heroFixLowerRightLeg;
    private ModelRenderer heroFixLowerLeftArm;
    private ModelRenderer heroFixLowerRightArm;


    public ModelNinjaArmour(boolean shouldArmRender, boolean herosFuckUpFix, boolean hasOnSkinParts) {
        this.shouldArmRender = shouldArmRender;
        this.herosFuckUpFix = herosFuckUpFix;
        this.hasOnSkinParts = hasOnSkinParts;
    }

    /*
     Made for speed, make sure that every reference is removed so the garbage collector removes everything unneeded.
     also checks what should render
     */
    public void cleanupModel() {
        if(!this.hasOnSkinParts) {
            this.Head.cubeList.clear();

            this.UpperLeftArm.cubeList.clear();
            this.UpperRightArm.cubeList.clear();
            this.LowerLeftArm.cubeList.clear();
            this.LowerRightArm.cubeList.clear();

            this.UpperBody.cubeList.clear();
            this.LowerBody.cubeList.clear();

            this.UpperRightLeg.cubeList.clear();
            this.UpperLeftLeg.cubeList.clear();
            this.LowerRightLeg.cubeList.clear();
            this.LowerLeftLeg.cubeList.clear();
        }
        else {
            this.remakeSlightlyBigger(Head);

            this.remakeSlightlyBigger(UpperLeftArm);
            this.remakeSlightlyBigger(UpperRightArm);
            this.remakeSlightlyBigger(LowerLeftArm);
            this.remakeSlightlyBigger(LowerRightArm);

            this.remakeSlightlyBigger(UpperBody);
            this.remakeSlightlyBigger(LowerBody);

            this.remakeSlightlyBigger(UpperRightLeg);
            this.remakeSlightlyBigger(UpperLeftLeg);
            this.remakeSlightlyBigger(LowerRightLeg);
            this.remakeSlightlyBigger(LowerLeftLeg);
        }

        this.UpperLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.UpperRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.UpperLeftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.UpperRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);

        this.LowerBody.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.hasHead = this.shouldRender(this.Head);

        this.hasUpperLeftArm = this.shouldRender(this.UpperLeftArm);
        this.hasLowerLeftArm = this.shouldRender(this.LowerLeftArm);
        this.hasUpperRightArm = this.shouldRender(this.UpperRightArm);
        this.hasLowerRightArm = this.shouldRender(this.LowerRightArm);

        this.hasUpperLeftLeg = this.shouldRender(this.UpperLeftLeg);
        this.hasLowerLeftLeg = this.shouldRender(this.LowerLeftLeg);
        this.hasUpperRightLeg = this.shouldRender(this.UpperRightLeg);
        this.hasLowerRightLeg = this.shouldRender(this.LowerRightLeg);

        this.hasUpperBody = this.shouldRender(this.UpperBody);
        this.hasLowerBody = this.shouldRender(this.LowerBody);


        this.parentingFix(this.UpperBody, this.LowerBody, null, 0, 0, 0);

        if(this.herosFuckUpFix) {
            this.heroFixLowerLeftLeg = new ModelRenderer(this);
            this.heroFixLowerRightLeg = new ModelRenderer(this);
            this.heroFixLowerLeftArm = new ModelRenderer(this);
            this.heroFixLowerRightArm = new ModelRenderer(this);

            this.parentingFix(this.UpperLeftArm, this.LowerLeftArm, this.heroFixLowerLeftArm, 1.0F, 4F, 0.0F);
            this.parentingFix(this.UpperRightArm, this.LowerRightArm, this.heroFixLowerRightArm, -1.0F, 4F, 0.0F);
            this.parentingFix(this.UpperLeftLeg, this.LowerLeftLeg, this.heroFixLowerLeftLeg, 0, 6F, 0);
            this.parentingFix(this.UpperRightLeg, this.LowerRightLeg, this.heroFixLowerRightLeg, 0, 6F, 0);
        }
        else {
            this.heroFixLowerLeftLeg = this.LowerLeftLeg;
            this.heroFixLowerRightLeg = this.LowerRightLeg;
            this.heroFixLowerLeftArm = this.LowerLeftArm;
            this.heroFixLowerRightArm = this.LowerRightArm;
        }
    }

    public void remakeSlightlyBigger(ModelRenderer modelRenderer) {
        float expandValue = 0.005f;

        List oldList = modelRenderer.cubeList;
        if(oldList == null || oldList.size() == 0) return;

        List replacementList = new ArrayList();
        modelRenderer.cubeList = replacementList;
        for(Object oldObj : oldList) {
            if(oldObj instanceof ModelBox) {
                ModelBox oldBox = (ModelBox) oldObj;
                modelRenderer.addBox(oldBox.posX1, oldBox.posY1, oldBox.posZ1, Math.round(oldBox.posX2 - oldBox.posX1), Math.round(oldBox.posY2 - oldBox.posY1), Math.round(oldBox.posZ2 - oldBox.posZ1), expandValue);
            }
        }
    }

    /**
     *
     * @param originalParent
     * @param child
     * @param x offsetfix
     * @param y offsetfix
     * @param z offsetfix
     */
    public void parentingFix(ModelRenderer originalParent, ModelRenderer child, ModelRenderer fixParent, float x, float y, float z) {
        if(originalParent != null && originalParent.childModels != null) {
            originalParent.childModels.remove(child);
            if(fixParent != null) {
                fixParent.setRotationPoint(x,y,z);
                child.setRotationPoint(child.rotationPointX - x, child.rotationPointY- y, child.rotationPointZ - z);
                originalParent.addChild(fixParent);
                fixParent.addChild(child);
            }
        }
    }

    public boolean shouldRender(ModelRenderer part) {
        return part != null && ((part.childModels != null && part.childModels.size() > 0) || (part.cubeList != null && part.cubeList.size() > 0));
    }

    public void renderFull(float scale) {

        if(this.hasHead) this.renderTracked(this.Head, scale, this.Head);

        if(this.hasUpperLeftArm) {
            if(this.hasLowerLeftArm) this.trackToPart(this.heroFixLowerLeftArm, this.bipedLeftArmLower);
            this.renderTracked(this.UpperLeftArm, scale, this.bipedLeftArmUpper);
        }
        else if(this.hasLowerLeftArm) {
            this.renderTracked(this.LowerLeftArm, scale, this.bipedLeftArmUpper, this.bipedLeftArmLower);
        }
        if(this.hasUpperRightArm) {
            if(this.hasLowerRightArm) this.trackToPart(this.heroFixLowerRightArm, this.bipedRightArmLower);
            this.renderTracked(this.UpperRightArm, scale, this.bipedRightArmUpper);
        }
        else if(this.hasLowerRightArm) {
            this.renderTracked(this.LowerRightArm, scale, this.bipedRightArmUpper, this.bipedRightArmLower);
        }

        if(this.hasUpperLeftLeg) {
            if(this.hasLowerLeftLeg) this.trackToPart(this.heroFixLowerLeftLeg, this.bipedLeftLegLower);
            this.renderTracked(this.UpperLeftLeg, scale, this.bipedLeftLegUpper);
        }
        else if(this.hasLowerLeftLeg) {
            this.renderTracked(this.LowerLeftLeg, scale, this.bipedLeftLegUpper, this.bipedLeftLegLower);
        }
        if(this.hasUpperRightLeg) {
            if(this.hasLowerRightLeg) this.trackToPart(this.heroFixLowerRightLeg, this.bipedRightLegLower);
            this.renderTracked(this.UpperRightLeg, scale, this.bipedRightLegUpper);
        }
        else if(this.hasLowerRightLeg) {
            this.renderTracked(this.LowerRightLeg, scale, this.bipedRightLegUpper, this.bipedRightLegLower);
        }

        if(this.hasUpperBody) this.renderTracked(this.UpperBody, scale, this.bipedBody);
        if(this.hasLowerBody) this.renderTracked(this.LowerBody, scale, this.bipedLowerBody);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }


    @Override
    public void renderFirstPersonArm(float scale) {
        if(shouldArmRender) {
            this.bipedRightArmUpper.rotateAngleX = this.bipedRightArmUpper.rotateAngleY = this.bipedRightArmUpper.rotateAngleZ = 0;
            this.heroFixLowerRightArm.rotateAngleX = this.heroFixLowerRightArm.rotateAngleY = this.heroFixLowerRightArm.rotateAngleZ = 0;
            this.renderTracked(this.UpperRightArm, scale, this.bipedRightArmUpper);
            this.renderTracked(this.LowerRightArm, scale, this.bipedRightArmUpper, this.bipedRightArmLower);
        }
    }
}
