package sekwah.mods.narutomod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.entitys.particles.EntitySakuraPetalFX;

import java.util.ArrayList;
import java.util.Random;

public class BlockSakuraLeaves extends BlockLeavesBase implements IShearable {

    int[] adjacentTreeBlocks;
    @SideOnly(Side.CLIENT)
    private IIcon[] blockIcons;
    @SideOnly(Side.CLIENT)
    /** 1 for fast graphic. 0 for fancy graphics. used in iconArray. */
    private int iconType;
    private IIcon[][] iconArray = new IIcon[2][];
    private boolean opaqueCube = true;

    protected BlockSakuraLeaves() {
        super(Material.leaves, false);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        //Blocks.fire.setFireInfo(this, 30, 60);
    }

    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
        byte b0 = 1;
        int j1 = b0 + 1;

        if (par1World.checkChunksExist(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1)) {
            for (int k1 = -b0; k1 <= b0; ++k1) {
                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        Block j2 = par1World.getBlock(par2 + k1, par3 + l1, par4 + i2);

                        if (j2 != null) {
                            j2.beginLeavesDecay(par1World, par2 + k1, par3 + l1, par4 + i2);
                        }
                    }
                }
            }
        }
        this.removeLeaves(par1World, par2, par3, par4);
    }


    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!par1World.isRemote) {
            int l = par1World.getBlockMetadata(par2, par3, par4);

            if ((l & 8) != 0 && (l & 4) == 0) {
                byte b0 = 4;
                int i1 = b0 + 1;
                byte b1 = 32;
                int j1 = b1 * b1;
                int k1 = b1 / 2;

                if (this.adjacentTreeBlocks == null) {
                    this.adjacentTreeBlocks = new int[b1 * b1 * b1];
                }

                int l1;

                if (par1World.checkChunksExist(par2 - i1, par3 - i1, par4 - i1, par2 + i1, par3 + i1, par4 + i1)) {
                    int i2;
                    int j2;
                    int k2;

                    for (l1 = -b0; l1 <= b0; ++l1) {
                        for (i2 = -b0; i2 <= b0; ++i2) {
                            for (j2 = -b0; j2 <= b0; ++j2) {
                                Block block = par1World.getBlock(par2 + l1, par3 + i2, par4 + j2);

                                if (block != null && block.canSustainLeaves(par1World, par2 + l1, par3 + i2, par4 + j2)) {
                                    this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = 0;
                                } else if (block != null && block.isLeaves(par1World, par2 + l1, par3 + i2, par4 + j2)) {
                                    this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -2;
                                } else {
                                    this.adjacentTreeBlocks[(l1 + k1) * j1 + (i2 + k1) * b1 + j2 + k1] = -1;
                                }
                            }
                        }
                    }

                    for (l1 = 1; l1 <= 4; ++l1) {
                        for (i2 = -b0; i2 <= b0; ++i2) {
                            for (j2 = -b0; j2 <= b0; ++j2) {
                                for (k2 = -b0; k2 <= b0; ++k2) {
                                    if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1] == l1 - 1) {
                                        if (this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2) {
                                            this.adjacentTreeBlocks[(i2 + k1 - 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] == -2) {
                                            this.adjacentTreeBlocks[(i2 + k1 + 1) * j1 + (j2 + k1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] == -2) {
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 - 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] == -2) {
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1 + 1) * b1 + k2 + k1] = l1;
                                        }

                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] == -2) {
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + (k2 + k1 - 1)] = l1;
                                        }

                                        if (this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] == -2) {
                                            this.adjacentTreeBlocks[(i2 + k1) * j1 + (j2 + k1) * b1 + k2 + k1 + 1] = l1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                l1 = this.adjacentTreeBlocks[k1 * j1 + k1 * b1 + k1];

                if (l1 >= 0) {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, l & -9, 4);
                } else {
                    this.removeLeaves(par1World, par2, par3, par4);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int x, int y, int z, Random par5Random) {
        if (par1World.canLightningStrikeAt(x, y + 1, z) && !World.doesBlockHaveSolidTopSurface(par1World, x, y - 1, z) && par5Random.nextInt(15) == 1) {
            double d0 = (double) ((float) x + par5Random.nextFloat());
            double d1 = (double) y - 0.05D;
            double d2 = (double) ((float) z + par5Random.nextFloat());
            par1World.spawnParticle("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }

        if (par5Random.nextInt(27) == 0) {
            if (super.canPlaceBlockAt(par1World, x, y - 1, z)) {
                Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySakuraPetalFX(par1World, x + par1World.rand.nextFloat(), y, z + par1World.rand.nextFloat()).setBreeze(0.02F, 0F, 0.02F).setMaxAge(20).setScale(0.4F).setGravity(0.1F));
            }
        }
    }

    private void removeLeaves(World par1World, int par2, int par3, int par4) {
        this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
        par1World.setBlockToAir(par2, par3, par4);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random) {
        return par1Random.nextInt(20) == 0 ? 1 : 0;
    }

    public Block getBlockDropped(int i, Random random, int j) {
        return NarutoBlocks.Sakura_Sapling;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
        if (!par1World.isRemote) {
            int j1 = 20;

            if ((par5 & 3) == 3) {
                j1 = 40;
            }

            if (par7 > 0) {
                j1 -= 2 << par7;

                if (j1 < 10) {
                    j1 = 10;
                }
            }

            if (par1World.rand.nextInt(j1) == 0) {
                this.dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(getBlockDropped(0, null, 0), 1, 0));
            }

            j1 = 200;

            if (par7 > 0) {
                j1 -= 10 << par7;

                if (j1 < 40) {
                    j1 = 40;
                }
            }

            if ((par5 & 3) == 0 && par1World.rand.nextInt(j1) == 0) {
                this.dropBlockAsItem(par1World, par2, par3, par4, new ItemStack(Items.apple, 1, 0));
            }
        }
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
        super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return opaqueCube;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        blockIcons = new IIcon[2];
        blockIcons[0] = par1IconRegister.registerIcon(NarutoMod.modid + ":" + "leaves_sakura");
        blockIcons[1] = par1IconRegister.registerIcon(NarutoMod.modid + ":" + "leaves_sakura_opaque");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        this.opaqueCube = !Minecraft.getMinecraft().gameSettings.fancyGraphics;
        if (!this.opaqueCube) {
            return blockIcons[0];
        } else {
            return blockIcons[1];
        }
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1) {
        return new ItemStack(this, 1, par1 & 3);
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
        return ret;
    }

    @Override
    public void beginLeavesDecay(World world, int x, int y, int z) {
        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
    }

    @Override
    public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        Block block = p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_);
        return true; //(!this.opaqueCube) || super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
    }
}
