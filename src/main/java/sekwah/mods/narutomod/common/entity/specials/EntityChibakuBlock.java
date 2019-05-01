package sekwah.mods.narutomod.common.entity.specials;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import sekwah.mods.narutomod.common.entity.jutsuprojectiles.EntityChibakuTensei;
import sekwah.mods.narutomod.common.entity.jutsuprojectiles.TargetBlock;

public class EntityChibakuBlock extends Entity implements IEntityAdditionalSpawnData
{
    private EntityChibakuTensei entityChibaku;
    private Block block;
    public int metadata;
    public int field_145812_b;
    public boolean field_145813_c;
    private boolean field_145808_f;
    private boolean field_145809_g;
    private int field_145815_h;
    private float field_145816_i;
    public NBTTagCompound nbtTagCompound;

    public EntityChibakuBlock(World p_i1706_1_)
    {
        super(p_i1706_1_);
        this.field_145813_c = true;
        this.field_145815_h = 40;
        this.field_145816_i = 2.0F;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
    }

    public EntityChibakuBlock(World p_i45318_1_, double p_i45318_2_, double p_i45318_4_, double p_i45318_6_, EntityChibakuTensei entity, Block p_i45318_8_)
    {
        this(p_i45318_1_, p_i45318_2_, p_i45318_4_, p_i45318_6_, entity, p_i45318_8_, 0);
    }

    public EntityChibakuBlock(World p_i45319_1_, double x, double y, double z, EntityChibakuTensei entity, Block block, int p_i45319_9_)
    {
        this(p_i45319_1_);
        this.block = block;
        this.metadata = p_i45319_9_;
        this.yOffset = this.height / 2.0F;
        this.setPosition(x, y, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.entityChibaku = entity;
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected void entityInit() {
        this.noClip = true;
    }

    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    public void onUpdate()
    {
        if(this.block == null) {
            if(!this.worldObj.isRemote) {
                this.setDead();
            }
            else {
                return;
            }
        }
        if (this.block.getMaterial() == Material.air)
        {
            this.setDead();
        }
        else
        {
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            ++this.field_145812_b;
            if(this.entityChibaku != null && !this.entityChibaku.isDead) {
                Vec3 dir = Vec3.createVectorHelper(this.posX - this.entityChibaku.posX, this.posY - this.entityChibaku.posY, this.posZ - this.entityChibaku.posZ);
                if(dir.xCoord * dir.xCoord + dir.yCoord * dir.yCoord + dir.zCoord * dir.zCoord < this.entityChibaku.lastSqDist) {
                    if(!this.worldObj.isRemote) {
                        TargetBlock block = this.entityChibaku.placeBlock();
                        if(block != null) {
                            this.worldObj.setBlock(block.x, block.y, block.z, this.block, metadata, 2);
                            this.setDead();
                            return;
                        }
                    }
                }
                Vec3 normDir = dir.normalize();
                double force = 0.1;
                this.motionX -= normDir.xCoord * force;
                this.motionY -= normDir.yCoord * force;
                this.motionZ -= normDir.zCoord * force;
                this.motionX *= 0.9;
                this.motionY *= 0.9;
                this.motionZ *= 0.9;
                this.moveEntity(this.motionX, this.motionY, this.motionZ);
                return;
            }
            else if(this.noClip) {
                this.noClip = false;
            }
            this.motionY -= 0.03999999910593033D;
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.9800000190734863D;
            this.motionY *= 0.9800000190734863D;
            this.motionZ *= 0.9800000190734863D;

            if (!this.worldObj.isRemote)
            {
                int i = MathHelper.floor_double(this.posX);
                int j = MathHelper.floor_double(this.posY);
                int k = MathHelper.floor_double(this.posZ);

                /*if (this.field_145812_b == 1)
                {
                    if (this.worldObj.getBlock(i, j, k) != this.block)
                    {
                        this.setDead();
                        return;
                    }

                    this.worldObj.setBlockToAir(i, j, k);
                }*/

                if (this.onGround)
                {
                    this.motionX *= 0.699999988079071D;
                    this.motionZ *= 0.699999988079071D;
                    this.motionY *= -0.5D;

                    if (this.worldObj.getBlock(i, j, k) != Blocks.piston_extension)
                    {
                        this.setDead();

                        if (!this.field_145808_f && this.worldObj.canPlaceEntityOnSide(this.block, i, j, k, true, 1, (Entity)null, (ItemStack)null) && !BlockFalling.func_149831_e(this.worldObj, i, j - 1, k) && this.worldObj.setBlock(i, j, k, this.block, this.metadata, 3))
                        {
                            if (this.block instanceof BlockFalling)
                            {
                                ((BlockFalling)this.block).func_149828_a(this.worldObj, i, j, k, this.metadata);
                            }

                            if (this.nbtTagCompound != null && this.block instanceof ITileEntityProvider)
                            {
                                TileEntity tileentity = this.worldObj.getTileEntity(i, j, k);

                                if (tileentity != null)
                                {
                                    NBTTagCompound nbttagcompound = new NBTTagCompound();
                                    tileentity.writeToNBT(nbttagcompound);
                                    Iterator iterator = this.nbtTagCompound.func_150296_c().iterator();

                                    while (iterator.hasNext())
                                    {
                                        String s = (String)iterator.next();
                                        NBTBase nbtbase = this.nbtTagCompound.getTag(s);

                                        if (!s.equals("x") && !s.equals("y") && !s.equals("z"))
                                        {
                                            nbttagcompound.setTag(s, nbtbase.copy());
                                        }
                                    }

                                    tileentity.readFromNBT(nbttagcompound);
                                    tileentity.markDirty();
                                }
                            }
                        }
                        else if (this.field_145813_c && !this.field_145808_f)
                        {
                            this.entityDropItem(new ItemStack(this.block, 1, this.block.damageDropped(this.metadata)), 0.0F);
                        }
                    }
                }
                else if (this.field_145812_b > 100 && !this.worldObj.isRemote && (j < 1 || j > 256) || this.field_145812_b > 600)
                {
                    if (this.field_145813_c)
                    {
                        this.entityDropItem(new ItemStack(this.block, 1, this.block.damageDropped(this.metadata)), 0.0F);
                    }

                    this.setDead();
                }
            }
        }
    }

    protected void fall(float p_70069_1_)
    {
        if (this.field_145809_g)
        {
            int i = MathHelper.ceiling_float_int(p_70069_1_ - 1.0F);

            if (i > 0)
            {
                ArrayList arraylist = new ArrayList(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox));
                boolean flag = this.block == Blocks.anvil;
                DamageSource damagesource = flag ? DamageSource.anvil : DamageSource.fallingBlock;
                Iterator iterator = arraylist.iterator();

                while (iterator.hasNext())
                {
                    Entity entity = (Entity)iterator.next();
                    entity.attackEntityFrom(damagesource, (float)Math.min(MathHelper.floor_float((float)i * this.field_145816_i), this.field_145815_h));
                }

                if (flag && (double)this.rand.nextFloat() < 0.05000000074505806D + (double)i * 0.05D)
                {
                    int j = this.metadata >> 2;
                    int k = this.metadata & 3;
                    ++j;

                    if (j > 2)
                    {
                        this.field_145808_f = true;
                    }
                    else
                    {
                        this.metadata = k | j << 2;
                    }
                }
            }
        }
    }

    protected void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        p_70014_1_.setByte("Tile", (byte)Block.getIdFromBlock(this.block));
        p_70014_1_.setInteger("TileID", Block.getIdFromBlock(this.block));
        p_70014_1_.setByte("Data", (byte)this.metadata);
        p_70014_1_.setByte("Time", (byte)this.field_145812_b);
        p_70014_1_.setBoolean("DropItem", this.field_145813_c);
        p_70014_1_.setBoolean("HurtEntities", this.field_145809_g);
        p_70014_1_.setFloat("FallHurtAmount", this.field_145816_i);
        p_70014_1_.setInteger("FallHurtMax", this.field_145815_h);

        if (this.nbtTagCompound != null)
        {
            p_70014_1_.setTag("TileEntityData", this.nbtTagCompound);
        }
    }

    protected void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        if (p_70037_1_.hasKey("TileID", 99))
        {
            this.block = Block.getBlockById(p_70037_1_.getInteger("TileID"));
        }
        else
        {
            this.block = Block.getBlockById(p_70037_1_.getByte("Tile") & 255);
        }

        this.metadata = p_70037_1_.getByte("Data") & 255;
        this.field_145812_b = p_70037_1_.getByte("Time") & 255;

        if (p_70037_1_.hasKey("HurtEntities", 99))
        {
            this.field_145809_g = p_70037_1_.getBoolean("HurtEntities");
            this.field_145816_i = p_70037_1_.getFloat("FallHurtAmount");
            this.field_145815_h = p_70037_1_.getInteger("FallHurtMax");
        }
        else if (this.block == Blocks.anvil)
        {
            this.field_145809_g = true;
        }

        if (p_70037_1_.hasKey("DropItem", 99))
        {
            this.field_145813_c = p_70037_1_.getBoolean("DropItem");
        }

        if (p_70037_1_.hasKey("TileEntityData", 10))
        {
            this.nbtTagCompound = p_70037_1_.getCompoundTag("TileEntityData");
        }

        if (this.block.getMaterial() == Material.air)
        {
            this.block = Blocks.sand;
        }
    }

    public void func_145806_a(boolean p_145806_1_)
    {
        this.field_145809_g = p_145806_1_;
    }

    public void addEntityCrashInfo(CrashReportCategory p_85029_1_)
    {
        super.addEntityCrashInfo(p_85029_1_);
        p_85029_1_.addCrashSection("Immitating block ID", Integer.valueOf(Block.getIdFromBlock(this.block)));
        p_85029_1_.addCrashSection("Immitating block data", Integer.valueOf(this.metadata));
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    @SideOnly(Side.CLIENT)
    public World getWorld()
    {
        return this.worldObj;
    }

    /**
     * Return whether this entity should be rendered as on fire.
     */
    @SideOnly(Side.CLIENT)
    public boolean canRenderOnFire()
    {
        return false;
    }

    public Block getBlock()
    {
        return this.block;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(Block.getIdFromBlock(this.block));
        buffer.writeInt(this.metadata);
        if(this.entityChibaku != null) {
            buffer.writeInt(this.entityChibaku.getEntityId());
        }
        else {
            buffer.writeInt(-1);
        }
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        this.block = Block.getBlockById(additionalData.readInt());
        this.metadata = additionalData.readInt();
        int entityID = additionalData.readInt();
        if(entityID == -1) {
            return;
        }
        Entity entity = this.worldObj.getEntityByID(entityID);
        if(entity instanceof EntityChibakuTensei) {
            this.entityChibaku = (EntityChibakuTensei) entity;
        }
    }
}