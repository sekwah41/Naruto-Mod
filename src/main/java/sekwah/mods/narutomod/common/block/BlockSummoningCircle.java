package sekwah.mods.narutomod.common.block;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.clientbound.ClientSoundPacket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

public class BlockSummoningCircle extends Block {
    /**
     * Boolean used to seperate different states of blocks
     */
    private boolean blockType;

    private int updatecount;
    private int summonentity;

    private EntityLivingBase PlayerPlace;

    protected BlockSummoningCircle(boolean par3, int par9) {
        super(Material.grass);
        this.summonentity = par9;
        this.blockType = par3;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.001F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(NarutoMod.modid + ":" + (this.getUnlocalizedName().substring(5)));
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        super.onBlockAdded(par1World, par2, par3, par4);
    }

    /**
     * Change this depending on the size of the summoning animal
     * <p/>
     * note: This checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
        return par3 >= 255 ? false : World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4) && super.canPlaceBlockAt(par1World, par2, par3, par4) && super.canPlaceBlockAt(par1World, par2 + 1, par3, par4) && super.canPlaceBlockAt(par1World, par2, par3, par4 + 1) && super.canPlaceBlockAt(par1World, par2 - 1, par3, par4) && super.canPlaceBlockAt(par1World, par2, par3, par4 - 1) && super.canPlaceBlockAt(par1World, par2 - 1, par3, par4 - 1) && super.canPlaceBlockAt(par1World, par2 + 1, par3, par4 - 1) && super.canPlaceBlockAt(par1World, par2 - 1, par3, par4 + 1) && super.canPlaceBlockAt(par1World, par2 + 1, par3, par4 + 1) && World.doesBlockHaveSolidTopSurface(par1World, par2 + 1, par3 - 1, par4) && World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4 + 1) && World.doesBlockHaveSolidTopSurface(par1World, par2 - 1, par3 - 1, par4) && World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4 - 1) && World.doesBlockHaveSolidTopSurface(par1World, par2 - 1, par3 - 1, par4 - 1) && World.doesBlockHaveSolidTopSurface(par1World, par2 + 1, par3 - 1, par4 - 1) && World.doesBlockHaveSolidTopSurface(par1World, par2 - 1, par3 - 1, par4 + 1) && World.doesBlockHaveSolidTopSurface(par1World, par2 + 1, par3 - 1, par4 + 1);
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        int var6 = MathHelper.floor_double((double) (par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        this.PlayerPlace = par5EntityLiving;
        updatecount = 1;
        par1World.scheduleBlockUpdate(par2, par3, par4, this, 4);
    }

    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        for (int random = 1; random < 10; random++) {
            par1World.spawnParticle("explode", par2 + par5Random.nextDouble(), par3 + (par5Random.nextDouble() * 1.5), par4 + par5Random.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (updatecount == 1) {
            par1World.setBlock(par2 + 1, par3, par4, NarutoBlocks.Summoning_CircleEdges, 1, 3);
            par1World.setBlock(par2, par3, par4 + 1, NarutoBlocks.Summoning_CircleEdges, 2, 3);
            par1World.setBlock(par2 + 1, par3, par4 + 1, NarutoBlocks.Summoning_CircleEdges, 5, 3);
            par1World.setBlock(par2 - 1, par3, par4 - 1, NarutoBlocks.Summoning_CircleEdges, 7, 3);
            par1World.setBlock(par2 + 1, par3, par4 - 1, NarutoBlocks.Summoning_CircleEdges, 4, 3);
            par1World.setBlock(par2 - 1, par3, par4 + 1, NarutoBlocks.Summoning_CircleEdges, 6, 3);
            par1World.setBlock(par2, par3, par4 - 1, NarutoBlocks.Summoning_CircleEdges, 0, 3);
            par1World.setBlock(par2 - 1, par3, par4, NarutoBlocks.Summoning_CircleEdges, 3, 3);
            par1World.notifyBlockChange(par2, par3, par4, NarutoBlocks.Summoning_CircleEdges);
            par1World.notifyBlockChange(par2 + 1, par3, par4, NarutoBlocks.Summoning_CircleEdges);
            par1World.notifyBlockChange(par2, par3, par4 + 1, NarutoBlocks.Summoning_CircleEdges);
            par1World.notifyBlockChange(par2 + 1, par3, par4 + 1, NarutoBlocks.Summoning_CircleEdges);
            par1World.notifyBlockChange(par2 - 1, par3, par4 - 1, NarutoBlocks.Summoning_CircleEdges);
            par1World.notifyBlockChange(par2 + 1, par3, par4 - 1, NarutoBlocks.Summoning_CircleEdges);
            par1World.notifyBlockChange(par2 - 1, par3, par4 + 1, NarutoBlocks.Summoning_CircleEdges);
            par1World.notifyBlockChange(par2, par3, par4 - 1, NarutoBlocks.Summoning_CircleEdges);
            par1World.notifyBlockChange(par2 - 1, par3, par4, NarutoBlocks.Summoning_CircleEdges);
            par1World.scheduleBlockUpdate(par2, par3, par4, this, 15);
            updatecount = 2;
        }
        if (updatecount == 2) {

            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
            DataOutputStream outputStream = new DataOutputStream(bos);
            try {
                outputStream.writeInt(4);
                outputStream.writeDouble(par2);
                outputStream.writeDouble(par3);
                outputStream.writeDouble(par4);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (!par1World.isRemote) {
                PacketDispatcher.sendPacketToAllAround(new ClientSoundPacket(bos.toByteArray()), new TargetPoint(par1World.getWorldInfo().getDimension(), par2, par3, par4, 64));
            }

            //par1World.playAuxSFX(par2, par3, par4, "narutomod:jutsusounds.bunshin_seal", 0.5F, 1F, false);
            par1World.scheduleBlockUpdate(par2, par3, par4, this, 5);
            updatecount = 3;
        } else {
            par1World.setBlock(par2, par3, par4, Blocks.air);
            par1World.setBlock(par2 + 1, par3, par4, Blocks.air);
            par1World.setBlock(par2, par3, par4 + 1, Blocks.air);
            par1World.setBlock(par2 + 1, par3, par4 + 1, Blocks.air);
            par1World.setBlock(par2 - 1, par3, par4 - 1, Blocks.air);
            par1World.setBlock(par2 + 1, par3, par4 - 1, Blocks.air);
            par1World.setBlock(par2 - 1, par3, par4 + 1, Blocks.air);
            par1World.setBlock(par2, par3, par4 - 1, Blocks.air);
            par1World.setBlock(par2 - 1, par3, par4, Blocks.air);
            par1World.notifyBlockChange(par2, par3, par4, Blocks.air);
            par1World.notifyBlockChange(par2 + 1, par3, par4, Blocks.air);
            par1World.notifyBlockChange(par2, par3, par4 + 1, Blocks.air);
            par1World.notifyBlockChange(par2 + 1, par3, par4 + 1, Blocks.air);
            par1World.notifyBlockChange(par2 - 1, par3, par4 - 1, Blocks.air);
            par1World.notifyBlockChange(par2 + 1, par3, par4 - 1, Blocks.air);
            par1World.notifyBlockChange(par2 - 1, par3, par4 + 1, Blocks.air);
            par1World.notifyBlockChange(par2, par3, par4 - 1, Blocks.air);
            par1World.notifyBlockChange(par2 - 1, par3, par4, Blocks.air);
            par1World.playSoundEffect(par2, par3, par4, "random.explode", 4.0F, (1.0F + (par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.2F) * 0.7F);

            if (this.summonentity == 0) {
                EntityWolf var7 = new EntityWolf(par1World);
                var7.setLocationAndAngles((double) par2 + 0.5, (double) par3 + 0D, (double) par4 + 0.5D, 0.0F, 0.0F);
                var7.setTamed(true);
                var7.func_152115_b(this.PlayerPlace.getUniqueID().toString());
                par1World.spawnEntityInWorld(var7);
            } else {
                EntityChicken var7 = new EntityChicken(par1World);
                var7.setLocationAndAngles((double) par2 + 0.5, (double) par3 + 0D, (double) par4 + 0.5D, 0.0F, 0.0F);
                par1World.spawnEntityInWorld(var7);
            }
        }
    }
}
