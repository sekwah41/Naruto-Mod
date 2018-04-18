package sekwah.mods.narutomod.common.entity.jutsuprojectiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityChibakuTensei extends Entity {

    public int aliveTicks = 0;

    public EntityChibakuTensei(World world) {
        super(world);
    }

    @Override
    protected void entityInit() {
        this.setSize(2,2);
    }

    public void onUpdate() {
        aliveTicks++;
        int travelTime = 20 * 6;
        if(aliveTicks < travelTime) {
            double moveAmount = (travelTime - aliveTicks) * 0.002;
            System.out.println(moveAmount);
            this.posY += moveAmount;
        }
        if(aliveTicks > 20 * 30) {
            setDead();
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {

    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float p_70013_1_)
    {
        return 1.0F;
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float p_70070_1_)
    {
        return 15728880;
    }
}
