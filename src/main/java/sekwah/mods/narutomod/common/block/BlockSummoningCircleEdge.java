package sekwah.mods.narutomod.common.block;

import sekwah.mods.narutomod.NarutoMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSummoningCircleEdge extends Block {
    /**
     * Boolean used to seperate different states of blocks
     */
    private boolean blockType;

    private IIcon[] blockIcons;

    protected BlockSummoningCircleEdge(boolean par3) {
        super(Material.grass);
        this.setTickRandomly(true);
        this.blockType = par3;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.001F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        blockIcons = new IIcon[8];
        blockIcons[0] = par1IconRegister.registerIcon(NarutoMod.modid + ":Summoning_CircleSides");
        blockIcons[1] = par1IconRegister.registerIcon(NarutoMod.modid + ":Summoning_CircleSides2");
        blockIcons[2] = par1IconRegister.registerIcon(NarutoMod.modid + ":Summoning_CircleSides3");
        blockIcons[3] = par1IconRegister.registerIcon(NarutoMod.modid + ":Summoning_CircleSides4");
        blockIcons[4] = par1IconRegister.registerIcon(NarutoMod.modid + ":Summoning_CircleCorners");
        blockIcons[5] = par1IconRegister.registerIcon(NarutoMod.modid + ":Summoning_CircleCorners2");
        blockIcons[6] = par1IconRegister.registerIcon(NarutoMod.modid + ":Summoning_CircleCorners3");
        blockIcons[7] = par1IconRegister.registerIcon(NarutoMod.modid + ":Summoning_CircleCorners4");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        return blockIcons[par2];
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
}