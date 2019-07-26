package sekwah.mods.narutomod.animation;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.util.Vec3;

public class OffsetPositionTextureVertex extends PositionTextureVertex {


    public Vec3 originalVec;

    public OffsetPositionTextureVertex(float p_i1158_1_, float p_i1158_2_, float p_i1158_3_, float p_i1158_4_, float p_i1158_5_) {
        super(p_i1158_1_, p_i1158_2_, p_i1158_3_, p_i1158_4_, p_i1158_5_);
        this.originalVec = Vec3.createVectorHelper(this.vector3D.xCoord, this.vector3D.yCoord, this.vector3D.zCoord);
    }

    public void setOffsetVec(double x, double y, double z) {
        this.vector3D.xCoord = this.originalVec.xCoord + x;
        this.vector3D.yCoord = this.originalVec.yCoord + y;
        this.vector3D.zCoord = this.originalVec.zCoord + z;
    }

    public void setOffsetVecX(double offset) {
        this.vector3D.xCoord = this.originalVec.xCoord + offset;
    }

    public void setOffsetVecY(double offset) {
        System.out.println(offset);
        System.out.println("Before:" + this.vector3D.yCoord);
        this.vector3D.yCoord = this.originalVec.yCoord + offset;
        System.out.println("After:" + this.vector3D.yCoord);
    }

    public void setOffsetVecZ(double offset) {
        this.vector3D.zCoord = this.originalVec.zCoord + offset;
    }
}
