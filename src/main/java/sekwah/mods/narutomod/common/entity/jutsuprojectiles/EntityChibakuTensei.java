package sekwah.mods.narutomod.common.entity.jutsuprojectiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sekwah.mods.narutomod.common.entity.specials.EntityChibakuBlock;

import java.util.*;

public class EntityChibakuTensei extends Entity {

    public EntityChibakuTensei(World world) {
        super(world);
    }

    public final int maxLife = 40 * 20;

    private boolean hasLookedForBlocks = false;

    private int cX;
    private int cY;
    private int cZ;

    private LinkedList<TargetBlock> targetBlocks;

    @Override
    protected void entityInit() {
        this.setSize(2,2);
    }

    public void onUpdate() {
        int travelTime = 16 * 10;
        if(ticksExisted < travelTime) {
            double moveAmount = (travelTime - ticksExisted) * 0.005;
            this.posY += moveAmount;
            float sizeValue = 2.2f - (float) moveAmount * 10f;
            this.setSize(sizeValue, sizeValue);
        }
        if(!this.worldObj.isRemote) {
            if(ticksExisted == travelTime / 9) {
                cX = (int) this.posX;
                cY = (int) this.posY;
                cZ = (int) this.posZ;
            }
            if (ticksExisted == travelTime) {
                this.targetBlocks = this.grabBlocks();
            }
            if (this.targetBlocks != null) {
                for(int i = 0; i < 6; i++) {
                    TargetBlock targetBlock = this.targetBlocks.pollFirst();
                    if(targetBlock != null) {
                        this.worldObj.setBlock(targetBlock.x, targetBlock.y, targetBlock.z, Blocks.air);
                        EntityChibakuTensei entityChibakuTensei = new EntityChibakuTensei(this.worldObj);
                        this.worldObj.spawnEntityInWorld(entityChibakuTensei);
                    }
                }
            }
        }
        if(ticksExisted > maxLife) {
            setDead();
        }
    }

    final int blockRange = 40;
    final int blockRangeSq = blockRange * blockRange;

    public LinkedList<TargetBlock> grabBlocks() {
        LinkedList<TargetBlock> blocks = new LinkedList<TargetBlock>();
        for (int x = -blockRange; x < blockRange; x++) {
            for (int y = -blockRange; y < blockRange; y++) {
                for (int z = -blockRange; z < blockRange; z++) {
                    if(x * x + y * y + z * z < blockRangeSq) {
                        Block block = this.worldObj.getBlock(cX + x, cY + y, cZ + z);
                        if(block.getMaterial() != Material.air) {
                            blocks.add(new TargetBlock(cX + x, cY + y, cZ + z, this.posX, this.posY, this.posZ ,this.worldObj.getBlock(cX + x, cY + y, cZ + z)));
                        }
                    }
                }
            }
        }
        Collections.sort(blocks);
        return blocks;
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
