package sekwah.mods.narutomod.common.entity.specials;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeModContainer;

public class EntityMovingBlock extends Entity implements IEntityAdditionalSpawnData {

    private boolean canMove = true;

    /*public int blockID;
    public int metadata;*/
    /**
     * Animation data
     */
    public double fromPosX;
    public double fromPosY;
    public double fromPosZ;

    public double toPosX;
    public double toPosY;
    public double toPosZ;

    public double transitionLength;

    public int health;

    public NBTTagCompound fallingBlockTileEntityData;

    private Block block;
    private byte data;

    public EntityMovingBlock(World world) {
        super(world);
        this.commonSetup();
    }

    public EntityMovingBlock(World par1World, double x, double y, double z, int blockID, int blockMetaID, int health) {
        super(par1World);
        this.block = Block.getBlockById(blockID);
        this.data = (byte) blockMetaID;
        this.health = health;
        this.commonSetup();
        this.preventEntitySpawning = true;
        this.setPosition(x + 0.5f, y, z + 0.5f);
        this.canMove = false;
    }

    @Override
    public void setPosition(double p_70107_1_, double p_70107_3_, double p_70107_5_)
    {
        if(!this.canMove) {
            return;
        }
        this.posX = p_70107_1_;
        this.posY = p_70107_3_;
        this.posZ = p_70107_5_;
        float f = this.width / 2.0F;
        float f1 = this.height;
        this.boundingBox.setBounds(p_70107_1_ - (double)f, p_70107_3_ - (double)this.yOffset + (double)this.ySize, p_70107_5_ - (double)f, p_70107_1_ + (double)f, p_70107_3_ - (double)this.yOffset + (double)this.ySize + (double)f1, p_70107_5_ + (double)f);
    }

    @Override
    protected void entityInit() {
        this.dataWatcher.addObject(10, Byte.valueOf((byte)0));
    }

    public boolean getShaking() {
        return this.getRenderFlag((byte)1);
    }

    private boolean getRenderFlag(byte b) {
        return (this.dataWatcher.getWatchableObjectByte(10) & b) > 0;
    }

    public void setShaking(boolean shouldShake) {
        this.setRenderFlag((byte)1, shouldShake);
    }

    public void setRenderFlag(byte flag, boolean bool) {
        byte currentFlags = this.dataWatcher.getWatchableObjectByte(10);
        if(bool) {
            this.dataWatcher.updateObject(10, (byte) (currentFlags | flag));
        }
        else {
            this.dataWatcher.updateObject(10, (byte) (currentFlags & ~(flag)));
        }
    }

    private void commonSetup() {
        this.setSize(1F, 1F);
        //this.yOffset = this.height / 2.0F;
    }

    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return entity.boundingBox;
    }

    /**
     * returns the bounding box for this entity
     */
    public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }

    public boolean canBePushed()
    {
        return false;
    }

    @Override
    public void onUpdate() {
        //this.posY += 0.01;

        if(!this.worldObj.isRemote) {
            WorldServer worldserver = (WorldServer)this.worldObj;
            if(this.health == 30) {
                this.setShaking();
            }
            if(this.health-- < 0) {
                this.worldObj.playSoundAtEntity(this, this.block.stepSound.getBreakSound(), 1, 1);
                worldserver.func_147487_a("blockcrack_" + Block.getIdFromBlock(block) + "_" + this.data,
                        this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.boundingBox.minY + 0.1D,
                        this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width ,10,0,0,0,1);
                //worldserver.func_147487_a("blockcrack_" + Block.getIdFromBlock(block) + "_" + this.data, this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.boundingBox.minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, 0,0,0);
                this.setDead();
            }
        }
    }

    private void setShaking() {
        this.setRenderFlag((byte)1, true);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
        this.block = Block.getBlockById(nbtTagCompound.getInteger("BlockID"));
        this.data = nbtTagCompound.getByte("Data");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger("BlockID", Block.getIdFromBlock(this.block));
        nbtTagCompound.setByte("Data", this.data);
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(Block.getIdFromBlock(this.block));
        buffer.writeByte(this.data);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        this.block = Block.getBlockById(additionalData.readInt());
        this.data = additionalData.readByte();
        this.canMove = false;
    }

    public Block getBlock() {
        return this.block;
    }

    public byte getMetaData() {
        return this.data;
    }
}
