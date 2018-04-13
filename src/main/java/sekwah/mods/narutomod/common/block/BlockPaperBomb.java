package sekwah.mods.narutomod.common.block;

import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.items.NarutoItems;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import sekwah.mods.narutomod.common.entity.projectiles.EntityPaperBomb;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BlockPaperBomb extends Block {
    @SideOnly(Side.CLIENT)
    private IIcon[] blockIcons;

    private int rotation;

    public BlockPaperBomb(int par2) {
        super(Material.tnt);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        blockIcons = new IIcon[4];
        blockIcons[0] = par1IconRegister.registerIcon(NarutoMod.modid + ":" + (this.getUnlocalizedName().substring(5)));
        blockIcons[1] = par1IconRegister.registerIcon(NarutoMod.modid + ":" + (this.getUnlocalizedName().substring(5)) + 2);
        blockIcons[2] = par1IconRegister.registerIcon(NarutoMod.modid + ":" + (this.getUnlocalizedName().substring(5)) + "trans");
        blockIcons[3] = par1IconRegister.registerIcon(NarutoMod.modid + ":" + (this.getUnlocalizedName().substring(5)) + "trans" + 2);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        return blockIcons[par2];
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass() {
        return 1;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random) {
        return 1;
    }

    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
        return World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4) || par1World.getBlock(par2, par3 - 1, par4) == Blocks.glowstone;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
        if (!par1World.isRemote) {
            int var6 = par1World.getBlockMetadata(par2, par3, par4);
            boolean var7 = this.canPlaceBlockAt(par1World, par2, par3, par4);

            if (var7) {
            } else {
                this.dropBlockAsItem(par1World, par2, par3, par4, var6, 0);
                par1World.setBlock(par2, par3, par4, Blocks.air);
            }

            super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int x, int y, int z) {
        int damage = par1World.getBlockMetadata(x, y, z);
        if (damage == 0 || damage == 1) {
            par1World.scheduleBlockUpdate(x, y, z, this, 5 * 20);
        }
    }

    public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int dir = MathHelper.floor_double((double) ((par5EntityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        if (dir == 0 || dir == 2) {
            par1World.setBlockMetadataWithNotify(x, y, z, 1, 0);
        } else if (dir == 1 || dir == 3) {
            par1World.setBlockMetadataWithNotify(x, y, z, 0, 0);
        }
    }

    public void updateTick(World par1World, int x, int y, int z, Random par5Random) {
        int damage = par1World.getBlockMetadata(x, y, z);
        if (damage == 0 || damage == 1) {
            par1World.setBlock(x, y, z, NarutoBlocks.PaperBombBlock, damage + 2, 2);
        }
    }

    public Item getItemDropped(int i, Random random, int j) {
        return NarutoItems.PaperBombItem;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */

    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * Called upon block activation (right click on the block.)
     */

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        int damage = par1World.getBlockMetadata(par2, par3, par4);
        if (damage == 0 || damage == 2) {
            damage = 1;
        } else {
            damage = 0;
        }
        par1World.setBlock(par2, par3, par4, Blocks.air);
        EntityPaperBomb var6 = new EntityPaperBomb(par1World, (double) ((float) par2 + 0.5F), (double) ((float) par3 + 0.5F), (double) ((float) par4 + 0.5F), null);
        var6.setRotation(damage);
        var6.fuse = 60;
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
            par1World.spawnEntityInWorld(var6);
        }
        par1World.playSoundAtEntity(var6, "narutomod:effects.Sizzle", 1.0F, 1.0F);
        return true;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void fillWithRain(World par1World, int par2, int par3, int par4) {
        int var6 = par1World.getBlockMetadata(par2, par3, par4);
        boolean var7 = this.canPlaceBlockAt(par1World, par2, par3, par4);
        this.dropBlockAsItem(par1World, par2, par3, par4, var6, 0);
        par1World.setBlock(par2, par3, par4, Blocks.air);
    }

    protected AxisAlignedBB getSensitiveAABB(int par1, int par2, int par3) {
        float var4 = 0.125F;
        return AxisAlignedBB.getBoundingBox((double) ((float) par1 + var4), (double) par2, (double) ((float) par3 + var4), (double) ((float) (par1 + 1) - var4), (double) par2 + 0.25D, (double) ((float) (par3 + 1) - var4));
    }

    private void setStateIfMobInteractsWithPlate(World par1World, int par2, int par3, int par4) {
        boolean var5 = par1World.getBlockMetadata(par2, par3, par4) == 1;
        boolean var6 = false;
        float var7 = 0.125F;
        List var8 = null;
        var8 = par1World.getEntitiesWithinAABBExcludingEntity(null, this.getSensitiveAABB(par2, par3, par4));

        if (!var8.isEmpty()) {
            Iterator var9 = var8.iterator();

            while (var9.hasNext()) {
                Entity var10 = (Entity) var9.next();

                if (!var10.doesEntityNotTriggerPressurePlate()) {
                    var6 = true;
                    break;
                }
            }
        }

        if (var6 && !var5) {
            int damage = par1World.getBlockMetadata(par2, par3, par4);
            if (damage == 0 || damage == 2) {
                damage = 1;
            } else {
                damage = 0;
            }
            EntityPaperBomb bomb = new EntityPaperBomb(par1World, (double) ((float) par2 + 0.5F), (double) ((float) par3 + 0.5F), (double) ((float) par4 + 0.5F), null);
            bomb.fuse = 3;
            Side side = FMLCommonHandler.instance().getEffectiveSide();
            if (side == Side.SERVER) {
                par1World.spawnEntityInWorld(bomb);
            }
            par1World.playSoundAtEntity(bomb, "narutomod:ninjasounds.effects.Sizzle", 1.0F, 1.0F);

            par1World.setBlock(par2, par3, par4, Blocks.air);
        }

        if (!var6 && var5) {
            int damage = par1World.getBlockMetadata(par2, par3, par4);
            if (damage == 0 || damage == 2) {
                damage = 1;
            } else {
                damage = 0;
            }
            EntityPaperBomb bomb = new EntityPaperBomb(par1World, (double) ((float) par2 + 0.5F), (double) ((float) par3 + 0.5F), (double) ((float) par4 + 0.5F), null);
            bomb.fuse = 3;
            Side side = FMLCommonHandler.instance().getEffectiveSide();
            if (side == Side.SERVER) {
                par1World.spawnEntityInWorld(bomb);
            }
            par1World.playSoundAtEntity(bomb, "narutomod:ninjasounds.effects.Sizzle", 1.0F, 1.0F);

            par1World.setBlock(par2, par3, par4, Blocks.air);
        }
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion) {
        if (!par1World.isRemote) {
            int damage = par1World.getBlockMetadata(par2, par3, par4);
            if (damage == 0 || damage == 2) {
                damage = 1;
            } else {
                damage = 0;
            }
            EntityPaperBomb bomb = new EntityPaperBomb(par1World, (double) ((float) par2 + 0.5F), (double) ((float) par3 + 0.5F), (double) ((float) par4 + 0.5F), null);
            bomb.fuse = 3;
            Side side = FMLCommonHandler.instance().getEffectiveSide();
            if (side == Side.SERVER) {
                par1World.spawnEntityInWorld(bomb);
            }
            par1World.playSoundAtEntity(bomb, "narutomod:ninjasounds.effects.Sizzle", 1.0F, 1.0F);
        }
    }

    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
        if (!par1World.isRemote) {
            this.setStateIfMobInteractsWithPlate(par1World, par2, par3, par4);
        }
    }
}