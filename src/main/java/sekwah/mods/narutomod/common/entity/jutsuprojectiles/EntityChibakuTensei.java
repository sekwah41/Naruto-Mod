package sekwah.mods.narutomod.common.entity.jutsuprojectiles;

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

    }

    public void onUpdate() {
        aliveTicks++;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {

    }
}
