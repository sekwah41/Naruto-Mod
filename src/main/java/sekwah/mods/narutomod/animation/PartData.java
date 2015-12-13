package sekwah.mods.narutomod.animation;


public class PartData {

    public String partName;

    // LocationData
    public boolean[] hasPos = {false, false, false};
    public float rotationPointX = 0;
    public float rotationPointY = 0;
    public float rotationPointZ = 0;
    public boolean[] shouldRot = {false, false, false};
    public float rotateAngleX = 0;
    public float rotateAngleY = 0;
    public float rotateAngleZ = 0;

    public PartData(String partName) {
        this.partName = partName;
    }

    public PartData(String partName, boolean fullPos, boolean fullRot) {
        this.partName = partName;
        hasPos[0] = fullPos;
        hasPos[1] = fullPos;
        hasPos[2] = fullPos;

        shouldRot[0] = fullRot;
        shouldRot[1] = fullRot;
        shouldRot[2] = fullRot;
    }

    public PartData(String partName, boolean hasPosX, boolean hasPosY, boolean hasPosZ, boolean hasRotX, boolean hasRotY, boolean hasRotZ) {
        this.partName = partName;
        hasPos[0] = hasPosX;
        hasPos[1] = hasPosY;
        hasPos[2] = hasPosZ;

        shouldRot[0] = hasRotX;
        shouldRot[1] = hasRotY;
        shouldRot[2] = hasRotZ;
    }

    public void setRotationPoint(float x, float y, float z) {
        rotationPointX = x;
        rotationPointY = y;
        rotationPointZ = z;
    }
}
