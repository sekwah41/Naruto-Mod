package sekwah.mods.narutomod.entitys.specials;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityMovingBlock extends Entity implements IEntityAdditionalSpawnData {

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

    public int maxLife = 20 * 10;

    public int holdTime;

    public NBTTagCompound fallingBlockTileEntityData;

    private Block block;
    private int data;

    public EntityMovingBlock(World world) {
        super(world);
        this.commonSetup();
    }

    public EntityMovingBlock(World par1World, double x, double y, double z, int blockID, int blockMetaID) {
        super(par1World);
        this.holdTime = 20 * 4;
        this.block = Block.getBlockById(blockID);
        this.data = blockMetaID;
        this.commonSetup();
        this.preventEntitySpawning = true;
        this.setPosition(x + 0.5f, y, z + 0.5f);
    }

    @Override
    protected void entityInit() {
        /*this.dataWatcher.addObject(10, new Integer(0));
        this.dataWatcher.addObject(11, new Byte(0));*/
    }

    private void commonSetup() {

        this.setSize(1F, 1F);
        this.yOffset = this.height / 2.0F;
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
        if(this.maxLife-- < 0) {
            this.setDead();
        }
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
        this.block = Block.getBlockById(nbtTagCompound.getInteger("BlockID"));
        this.data = nbtTagCompound.getByte("Data");
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {

    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        System.out.printf("%s %s%n", this.block, this.data);
        buffer.writeInt(Block.getIdFromBlock(this.block));
        buffer.writeInt(this.data);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        this.block = Block.getBlockById(additionalData.readInt());
        this.data = additionalData.readInt();
    }
}
