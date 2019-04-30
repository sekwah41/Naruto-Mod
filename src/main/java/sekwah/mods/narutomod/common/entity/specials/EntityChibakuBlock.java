package sekwah.mods.narutomod.common.entity.specials;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import sekwah.mods.narutomod.common.entity.jutsuprojectiles.EntityChibakuTensei;

import java.util.List;

public class EntityChibakuBlock extends Entity implements IEntityAdditionalSpawnData {

    public NBTTagCompound fallingBlockTileEntityData;

    private Block block;
    private byte data;

    private EntityChibakuTensei chibakuTensei;

    public EntityChibakuBlock(World world) {
        super(world);
    }

    public EntityChibakuBlock(World par1World, EntityChibakuTensei chibiTensei, int blockID, int blockMetaID, int health) {
        this(par1World);
        this.block = Block.getBlockById(blockID);
        this.data = (byte) blockMetaID;
        this.preventEntitySpawning = true;
    }

    @Override
    protected void entityInit() {
        this.ignoreFrustumCheck = true;
        this.noClip = true;
        this.setSize(1F, 1F);
    }

    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return this.boundingBox;
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

    public boolean canBeCollidedWith()
    {
        return false;
    }

    @Override
    public void onUpdate() {
        if(this.chibakuTensei != null) {
            Vec3 dir = Vec3.createVectorHelper(this.posX - this.chibakuTensei.posX, this.posY - this.chibakuTensei.posY, this.posX - this.chibakuTensei.posZ);
            Vec3 normDir = dir.normalize();
            double force = 0.1;
            this.motionX += normDir.xCoord * force;
            this.motionY += normDir.yCoord * force;
            this.motionZ += normDir.zCoord * force;
        }
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
    }

    public Block getBlock() {
        return this.block;
    }

    public byte getMetaData() {
        return this.data;
    }
}
