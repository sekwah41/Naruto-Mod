package sekwah.mods.narutomod.worldgeneration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import sekwah.mods.narutomod.common.block.NarutoBlocks;

import java.util.Random;

public class WorldGenSakuraTrees extends WorldGenerator {
    /**
     * The minimum height of a generated tree.
     */
    private final int minTreeHeight;

    /**
     * True if this tree should grow Vines.
     */
    private final boolean vinesGrow;

    /**
     * The metadata value of the wood to use in tree generation.
     */
    private final int metaWood;

    /**
     * The metadata value of the leaves to use in tree generation.
     */
    private final int metaLeaves;

    public WorldGenSakuraTrees(boolean par1) {
        this(par1, 4, 0, 0, false);
    }

    public WorldGenSakuraTrees(boolean par1, int par2, int par3, int par4, boolean par5) {
        super(par1);
        this.minTreeHeight = par2;
        this.metaWood = 0;
        this.metaLeaves = 0;
        this.vinesGrow = par5;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
        int l = par2Random.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        if (par4 >= 1 && par4 + l + 1 <= 256) {
            int i1;
            byte b0;
            int j1;
            int k1;

            Block block1;

            for (i1 = par4; i1 <= par4 + 1 + l; ++i1) {
                b0 = 1;

                if (i1 == par4) {
                    b0 = 0;
                }

                if (i1 >= par4 + 1 + l - 2) {
                    b0 = 2;
                }

                for (int l1 = par3 - b0; l1 <= par3 + b0 && flag; ++l1) {
                    for (j1 = par5 - b0; j1 <= par5 + b0 && flag; ++j1) {
                        if (i1 >= 0 && i1 < 256) {
                            block1 = par1World.getBlock(l1, i1, j1);

                            if (!par1World.isAirBlock(l1, i1, j1) &&
                                    !block1.isLeaves(par1World, l1, i1, j1) &&
                                    block1 != Blocks.grass &&
                                    block1 != Blocks.dirt &&
                                    !block1.isWood(par1World, l1, i1, j1)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                Block soil = par1World.getBlock(par3, par4 - 1, par5);
                boolean isSoil = (soil != null && soil.canSustainPlant(par1World, par3, par4 - 1, par5, ForgeDirection.UP, (BlockSapling) Blocks.sapling));

                if (isSoil && par4 < 256 - l - 1) {
                    soil.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
                    b0 = 3;
                    byte b1 = 0;
                    int i2;
                    int j2;
                    int k2;

                    for (j1 = 0; j1 < l; ++j1) {
                        Block block = par1World.getBlock(par3, par4 + j1, par5);

                        if (block == null || block.isAir(par1World, par3, par4 + j1, par5) || block.isLeaves(par1World, par3, par4 + j1, par5)) {
                            this.setBlockAndNotifyAdequately(par1World, par3, par4 + j1, par5, Blocks.log, this.metaWood);
                            par1World.notifyBlockChange(par3, par4 + j1, par5, Blocks.log);

                        }
                    }

                    for (j1 = par4 - b0 + l; j1 <= par4 + l; ++j1) {
                        k1 = j1 - (par4 + l);
                        i2 = b1 + 1 - k1 / 2;

                        for (j2 = par3 - i2; j2 <= par3 + i2; ++j2) {
                            k2 = j2 - par3;

                            for (int l2 = par5 - i2; l2 <= par5 + i2; ++l2) {
                                int i3 = l2 - par5;

                                if (Math.abs(k2) != i2 || Math.abs(i3) != i2 || par2Random.nextInt(2) != 0 && k1 != 0) {
                                    Block block = par1World.getBlock(j2, j1, l2);

                                    if (block == null || block.canBeReplacedByLeaves(par1World, j2, j1, l2)) {
                                        this.setBlockAndNotifyAdequately(par1World, j2, j1, l2, NarutoBlocks.Sakura_Leaves, this.metaLeaves);
                                        par1World.notifyBlockChange(j2, j1, l2, NarutoBlocks.Sakura_Leaves);
                                    }
                                }
                            }
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Grows vines downward from the given block for a given length. Args: World, x, starty, z, vine-length
     */
    private void growVines(World par1World, int par2, int par3, int par4, int par5) {
        this.setBlockAndNotifyAdequately(par1World, par2, par3, par4, Blocks.vine, par5);
        int i1 = 4;

        while (true) {
            --par3;

            if (!par1World.isAirBlock(par2, par3, par4) || i1 <= 0) {
                return;
            }

            this.setBlockAndNotifyAdequately(par1World, par2, par3, par4, Blocks.vine, par5);
            --i1;
        }
    }
}
