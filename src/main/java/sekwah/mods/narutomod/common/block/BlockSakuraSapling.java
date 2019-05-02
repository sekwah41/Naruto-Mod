package sekwah.mods.narutomod.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.worldgeneration.WorldGenBigSakuraTree;
import sekwah.mods.narutomod.worldgeneration.WorldGenSakuraTrees;

import java.util.Random;

public class BlockSakuraSapling extends BlockBush implements IGrowable {

    // TODO fix because extending flower is breaking at the moment
    protected BlockSakuraSapling(int par1) {
        super(Material.plants);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return blockIcon;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!par1World.isRemote) {
            super.updateTick(par1World, par2, par3, par4, par5Random);

            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0) {
                this.markOrGrowMarked(par1World, par2, par3, par4, par5Random);
            }
        }
    }

    // Make it so it detects when bone marrow is used.

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!TerrainGen.saplingGrowTree(par1World, par5Random, par2, par3, par4)) return;

        int l = par1World.getBlockMetadata(par2, par3, par4) & 3;
        Object object = null;
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;

        if (object == null) {
            j1 = 0;
            i1 = 0;
            object = new WorldGenSakuraTrees(true, 4 + par5Random.nextInt(3), 3, 3, false);

            if (par5Random.nextInt(10) == 0) {
                object = new WorldGenBigSakuraTree(true);
            }
        }

        if (flag) {
            par1World.setBlock(par2 + i1, par3, par4 + j1, Blocks.air, 0, 4);
            par1World.setBlock(par2 + i1 + 1, par3, par4 + j1, Blocks.air, 0, 4);
            par1World.setBlock(par2 + i1, par3, par4 + j1 + 1, Blocks.air, 0, 4);
            par1World.setBlock(par2 + i1 + 1, par3, par4 + j1 + 1, Blocks.air, 0, 4);
        } else {
            par1World.setBlock(par2, par3, par4, Blocks.air, 0, 4);
        }

        if (!((WorldGenerator) object).generate(par1World, par5Random, par2 + i1, par3, par4 + j1)) {
            if (flag) {
                par1World.setBlock(par2 + i1, par3, par4 + j1, this, l, 4);
                par1World.setBlock(par2 + i1 + 1, par3, par4 + j1, this, l, 4);
                par1World.setBlock(par2 + i1, par3, par4 + j1 + 1, this, l, 4);
                par1World.setBlock(par2 + i1 + 1, par3, par4 + j1 + 1, this, l, 4);
            } else {
                par1World.setBlock(par2, par3, par4, this, l, 4);
            }
        }
    }

    /**
     * Determines if the same sapling is present at the given location.
     */
    public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5) {
        return par1World.getBlock(par2, par3, par4) == this && (par1World.getBlockMetadata(par2, par3, par4) & 3) == par5;
    }

    @SideOnly(Side.CLIENT)
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return CreativeTabs.tabDecorations;
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(NarutoMod.modid + ":" + "sapling_sakura");
    }

    public void markOrGrowMarked(World p_149879_1_, int p_149879_2_, int p_149879_3_, int p_149879_4_, Random p_149879_5_)
    {
        int l = p_149879_1_.getBlockMetadata(p_149879_2_, p_149879_3_, p_149879_4_);

        if ((l & 8) == 0)
        {
            p_149879_1_.setBlockMetadataWithNotify(p_149879_2_, p_149879_3_, p_149879_4_, l | 8, 4);
        }
        else
        {
            this.growTree(p_149879_1_, p_149879_2_, p_149879_3_, p_149879_4_, p_149879_5_);
        }
    }

    public boolean canFertilize(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return true;
    }

    public boolean shouldFertilize(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return (double)p_149852_1_.rand.nextFloat() < 0.45D;
    }

    public void fertilize(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_)
    {
        this.markOrGrowMarked(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
    }
}
