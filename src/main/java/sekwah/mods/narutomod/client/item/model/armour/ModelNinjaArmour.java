package sekwah.mods.narutomod.client.item.model.armour;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import sekwah.mods.narutomod.client.player.models.ModelNinjaBiped;

public class ModelNinjaArmour extends ModelNinjaBiped implements IRenderFirstPerson {

    private final boolean shouldArmRender;

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


    public ModelNinjaArmour(boolean shouldArmRender, boolean herosFuckUpFix) {
        this.shouldArmRender = shouldArmRender;
        this.herosFuckUpFix = herosFuckUpFix;
    }

    /*
     Made for speed, make sure that every reference is removed so the garbage collector removes everything unneeded.
     also checks what should render
     */
    public void cleanupModel() {
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


        this.removeChild(this.UpperBody, this.LowerBody);

        if(this.herosFuckUpFix) {
            this.removeChild(this.UpperLeftArm, this.LowerLeftArm);
            this.removeChild(this.UpperRightArm, this.LowerRightArm);
            this.removeChild(this.UpperLeftLeg, this.LowerLeftLeg);
            this.removeChild(this.UpperRightLeg, this.LowerRightLeg);
        }
    }
    public void removeChild(ModelRenderer parent, ModelRenderer child) {
        if(parent != null && parent.childModels != null) parent.childModels.remove(child);
    }

    public boolean shouldRender(ModelRenderer part) {
        return part != null && part.childModels != null && part.childModels.size() > 0;
    }

    public void renderFull(float scale) {
        this.UpperLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.UpperRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.UpperLeftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.UpperRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);

        this.LowerBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        if(this.hasHead) this.renderTracked(this.Head, scale, this.Head);

        if(this.herosFuckUpFix) {
            if(this.hasUpperLeftArm) {
                this.renderTracked(this.UpperLeftArm, scale, this.bipedLeftArmUpper);
            }
            if(this.hasLowerLeftArm) {
                this.renderTracked(this.LowerLeftArm, scale, this.bipedLeftArmUpper, this.bipedLeftArmLower);
            }
            if(this.hasUpperRightArm) {
                this.renderTracked(this.UpperRightArm, scale, this.bipedRightArmUpper);
            }
            if(this.hasLowerRightArm) {
                this.renderTracked(this.LowerRightArm, scale, this.bipedRightArmUpper, this.bipedRightArmLower);
            }

            if(this.hasUpperLeftLeg) {
                this.renderTracked(this.UpperLeftLeg, scale, this.bipedLeftLegUpper);
            }
            if(this.hasLowerLeftLeg) {
                this.renderTracked(this.LowerLeftLeg, scale, this.bipedLeftLegUpper, this.bipedLeftLegLower);
            }
            if(this.hasUpperRightLeg) {
                this.renderTracked(this.UpperRightLeg, scale, this.bipedRightLegUpper);
            }
            if(this.hasLowerRightLeg) {
                this.renderTracked(this.LowerRightLeg, scale, this.bipedRightLegUpper, this.bipedRightLegLower);
            }
        }
        else {
            if(this.hasUpperLeftArm) {
                if(this.hasLowerLeftArm) this.trackToPart(this.LowerLeftArm, this.bipedLeftArmLower);
                this.renderTracked(this.UpperLeftArm, scale, this.bipedLeftArmUpper);
            }
            else if(this.hasLowerLeftArm) {
                this.renderTracked(this.LowerLeftArm, scale, this.bipedLeftArmUpper, this.bipedLeftArmLower);
            }
            if(this.hasUpperRightArm) {
                if(this.hasLowerRightArm) this.trackToPart(this.LowerRightArm, this.bipedRightArmLower);
                this.renderTracked(this.UpperRightArm, scale, this.bipedRightArmUpper);
            }
            else if(this.hasLowerRightArm) {
                this.renderTracked(this.LowerRightArm, scale, this.bipedRightArmUpper, this.bipedRightArmLower);
            }

            if(this.hasUpperLeftLeg) {
                if(this.hasLowerLeftLeg) this.trackToPart(this.LowerLeftLeg, this.bipedLeftLegLower);
                this.renderTracked(this.UpperLeftLeg, scale, this.bipedLeftLegUpper);
            }
            else if(this.hasLowerLeftLeg) {
                this.renderTracked(this.LowerLeftLeg, scale, this.bipedLeftLegUpper, this.bipedLeftLegLower);
            }
            if(this.hasUpperRightLeg) {
                if(this.hasLowerRightLeg) this.trackToPart(this.LowerRightLeg, this.bipedRightLegLower);
                this.renderTracked(this.UpperRightLeg, scale, this.bipedRightLegUpper);
            }
            else if(this.hasLowerRightLeg) {
                this.renderTracked(this.LowerRightLeg, scale, this.bipedRightLegUpper, this.bipedRightLegLower);
            }
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
            this.bipedRightArmLower.rotateAngleX = this.bipedRightArmLower.rotateAngleY = this.bipedRightArmUpper.rotateAngleZ = 0;
            this.renderTracked(this.UpperRightArm, scale, this.bipedRightArmUpper);
            this.renderTracked(this.LowerRightArm, scale, this.bipedRightArmUpper, this.bipedRightArmLower);
        }
    }
}
