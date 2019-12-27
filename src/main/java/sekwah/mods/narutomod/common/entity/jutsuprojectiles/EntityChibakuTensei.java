package sekwah.mods.narutomod.common.entity.jutsuprojectiles;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import sekwah.mods.narutomod.common.entity.specials.EntityChibakuBlock;
import sekwah.mods.narutomod.settings.NarutoSettings;

import java.util.*;


// TODO keep the radius you are currently at (squared) and use the sort algorithm to sort positions to start building up a sphere
public class EntityChibakuTensei extends Entity implements IEntityAdditionalSpawnData {

    private EntityPlayer summoner;

    public EntityChibakuTensei(EntityPlayer player) {
        this(player.worldObj);
        this.summoner = player;
    }

    public EntityChibakuTensei(World world) {
        super(world);
    }

    public final int maxLife = 60 * 20;
    public final int noSpawny = 10 * 20;

    public double lastSqDist = 1;

    public int ticksMoved = 0;

    private int cX;
    private int cY;
    private int cZ;

    private LinkedList<TargetBlock> targetBlocks;
    private LinkedList<TargetBlock> placingLocations;

    private Random rand = new Random();

    @Override
    protected void entityInit() {
        this.noClip = true;
        this.setSize(2,2);

        this.getDataWatcher().addObject(16, 0f);
    }

    public float getGrabRadius() {
        return this.getDataWatcher().getWatchableObjectFloat(16);
    }

    public void setGrabRadius(float radius) {
        this.getDataWatcher().updateObject(16, radius);
    }

    public void onUpdate() {
        ticksMoved++;
        int travelTime = 16 * 10;
        if(ticksMoved < travelTime) {
            double moveAmount = (travelTime - ticksMoved) * 0.005;
            this.posY += moveAmount;
            float sizeValue = 3.5f - (float) moveAmount * 3f;
            this.setSize(sizeValue, sizeValue);
        }
        if(ticksMoved > travelTime) {
            float gravityRadius = 30;
            float gravityHeight = 70;
            List entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.setBounds(this.posX - gravityRadius, this.posY - gravityHeight, this.posZ - gravityRadius, this.posX + gravityRadius, this.posY + gravityHeight, this.posZ + gravityRadius));

            float grabDistance = this.getGrabRadius() + 2;
            for(Object entityObj : entities) {
                if(entityObj instanceof Entity && !(entityObj instanceof EntityChibakuBlock)) {
                    if(entityObj instanceof EntityPlayer) {
                        if(/*((EntityPlayer) entityObj).capabilities.isCreativeMode || */(summoner != null && ((EntityPlayer) entityObj).getEntityId() == summoner.getEntityId())) {
                            continue;
                        }
                    }
                    Entity entity = (Entity) entityObj;
                    Vec3 dir = Vec3.createVectorHelper(this.posX - entity.posX, this.posY - entity.posY, this.posZ - entity.posZ);
                    double length = dir.lengthVector();
                    if(length < grabDistance) {
                        Vec3 normDir = dir.normalize();
                        if(length <= 1) {
                            entity.setPosition(this.posX, this.posY, this.posZ);

                            setEntityVelocity(entity,0,0,0);

                            if(ticksMoved % 10 == 0) {
                                entity.attackEntityFrom(DamageSource.magic, 4);
                            }
                        }
                        else {
                            double divider = Math.min(Math.max(length * 3, 0.5d), 9);
                            entity.motionX += normDir.xCoord / divider;
                            entity.motionY += normDir.yCoord / divider;
                            entity.motionZ += normDir.zCoord / divider;
                            if(ticksMoved % 20 == 0) {
                                entity.attackEntityFrom(DamageSource.magic, 1);
                            }
                        }
                    }
                }
            }
        }
        if(!this.worldObj.isRemote) {
            if(ticksMoved == travelTime / 9) {
                cX = (int) this.posX;
                cY = this.worldObj.getHeightValue((int) this.posX, (int) this.posZ);
                cZ = (int) this.posZ;
            }
            if(!NarutoSettings.nonDestructiveMode) {
                if (ticksMoved == travelTime) {
                    this.targetBlocks = this.grabBlocks();
                    this.placingLocations = this.genPlaceList(this.targetBlocks.size());
                }
                if (this.targetBlocks != null && maxLife > ticksMoved + noSpawny) {
                    for(int i = 0; i < 10; i++) {
                        TargetBlock targetBlock = this.targetBlocks.pollFirst();
                        if(targetBlock != null) {
                            Block block = this.worldObj.getBlock(targetBlock.x, targetBlock.y, targetBlock.z);
                            if(block.getBlockHardness(this.worldObj, targetBlock.x, targetBlock.y, targetBlock.z) > -1) {
                                if(!(block instanceof BlockLeaves) && !(block instanceof BlockDynamicLiquid)) {
                                    if(block == Blocks.grass) {
                                        block = Blocks.dirt;
                                    }
                                    else if(block == Blocks.stone) {
                                        block = this.rand.nextFloat() > 0.5f ? Blocks.stone : Blocks.cobblestone;
                                    }
                                    EntityChibakuBlock blockEntity = new EntityChibakuBlock(this.worldObj, targetBlock.x - 0.5d, targetBlock.y - 0.5d, targetBlock.z - 0.5d, this, block,
                                            worldObj.getBlockMetadata(targetBlock.x, targetBlock.y, targetBlock.z));
                                    //block.motionY = 2;
                                    this.worldObj.spawnEntityInWorld(blockEntity);
                                }
                                this.worldObj.setBlock(targetBlock.x, targetBlock.y, targetBlock.z, Blocks.air);
                            }
                            if(i == 9) {
                                this.setGrabRadius((float) Vec3.createVectorHelper(this.posX - targetBlock.x, this.posY - targetBlock.y, this.posZ - targetBlock.z).lengthVector());
                            }
                        }
                    }
                }
            }

        }
        if(ticksMoved > maxLife) {
            setDead();
        }
    }

    public void setEntityVelocity(Entity entity, double x, double y, double z) {
        entity.motionX = x;
        entity.motionY = y;
        entity.motionZ = z;
    }

    public TargetBlock placeBlock() {
        TargetBlock block = this.placingLocations.pollFirst();
        if(block != null) {
            this.lastSqDist = block.sqDist > 1 ? block.sqDist : 1;
        }
        return block;
    }

    public LinkedList<TargetBlock> genPlaceList(int size) {
        LinkedList<TargetBlock> blocks = new LinkedList<TargetBlock>();
        int radius = (int) Math.ceil(Math.pow((3d * size)/ (4d * Math.PI), 1d/3d));
        float radSq = radius * radius;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    if(x * x + y * y + z * z < radSq) {
                        Block block = this.worldObj.getBlock((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z);
                        if(block.getMaterial() == Material.air) {
                            blocks.add(new TargetBlock((int) this.posX + x, (int) this.posY + y, (int) this.posZ + z, this.posX, this.posY, this.posZ));
                        }

                    }
                }
            }
        }
        Collections.sort(blocks);
        return blocks;
    }

    public LinkedList<TargetBlock> grabBlocks() {
        LinkedList<TargetBlock> blocks = new LinkedList<TargetBlock>();
        int blockRange = 40;
        for (int x = -blockRange; x < blockRange; x++) {
            for (int y = -blockRange; y < blockRange; y++) {
                for (int z = -blockRange; z < blockRange; z++) {
                    float blockRangeSq = (float) blockRange * blockRange;
                    if(x * x + y * y + z * z < blockRangeSq) {
                        Block block = this.worldObj.getBlock(cX + x, cY + y, cZ + z);
                        // TODO find if there is a way to set the block no to fall
                        if(block.getMaterial() != Material.air) {
                            blocks.add(new TargetBlock(cX + x, cY + y, cZ + z, this.posX, this.posY, this.posZ));
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
        if (nbtTagCompound.hasKey("TicksMoved"))
        {
            this.ticksMoved = nbtTagCompound.getInteger("TicksMoved");
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger("TicksMoved", this.ticksMoved);
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

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(this.ticksMoved);
        buffer.writeInt(this.summoner == null ? -1 : this.summoner.getEntityId());
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        this.ticksMoved = additionalData.readInt();
        int entityId = additionalData.readInt();
        if(entityId != 0) {
            Entity summoner = this.worldObj.getEntityByID(entityId);
            if(summoner instanceof EntityPlayer) {
                this.summoner = (EntityPlayer) summoner;
            }
        }
    }
}
