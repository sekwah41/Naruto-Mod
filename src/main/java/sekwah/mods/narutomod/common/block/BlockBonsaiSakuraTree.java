package sekwah.mods.narutomod.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.common.block.tileentity.TileEntityBonzaiSakuraTree;

public class BlockBonsaiSakuraTree extends BlockContainer {
    @SideOnly(Side.CLIENT)
    private IIcon[] blockIcons;

    public BlockBonsaiSakuraTree() {
        super(Material.plants);
        this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(NarutoMod.modid + ":" + (this.getUnlocalizedName().substring(5)));
    }

    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return false;
    }

    /**
     * Change this depending on the size of the summoning animal
     * <p/>
     * note: This checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
        return par3 >= 255 ? false : World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4);
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        //int dir = MathHelper.floor_double((double)((par5EntityLiving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        int dir = MathHelper.floor_double((double) ((par5EntityLiving.rotationYaw * 8F) / 360F) + 0.5D);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, dir + 2, 0);
    }

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
        super.onNeighborBlockChange(par1World, par2, par3, par4, Block.getBlockById(par5));
        if (!this.canBlockStay(par1World, par2, par3, par4)) {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlock(par2, par3, par4, Blocks.air, 0, 2);
        }
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
        return par3 >= 255 ? false : World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World, int p_149915_2_) {
        return new TileEntityBonzaiSakuraTree();
    }

    //And this tell it that you can see through this block, and neighbor blocks should be rendered.
    public boolean isOpaqueCube() {
        return false;
    }


}