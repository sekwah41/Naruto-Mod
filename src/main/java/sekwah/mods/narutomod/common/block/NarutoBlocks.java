package sekwah.mods.narutomod.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;

public class NarutoBlocks {

    public static Block PaperBombBlock;
    public static Block PaperBombEntityTexture;

    public static Block Summoning_CircleCenter;
    public static Block Summoning_CircleEdges;

    public static Block Sakura_Leaves;

    public static Block Sakura_Sapling;

    public static Block bonsaiTree;

    public static Block bonsaiSakuraTree;


    public static void addBlocks(Configuration config) {

        // new format of the normal block
        // example = (new Block(160, Material.ground)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("paperbombblock").setTextureName(par1Str)("paperbombblock");


        PaperBombBlock = (new BlockPaperBomb(8)).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setUnlocalizedName("paperbombblock").setTextureName("paperbombblock");

        Summoning_CircleCenter = (new BlockSummoningCircle(false, 0)).setHardness(-1F).setStepSound(Block.soundTypeGrass).setUnlocalizedName("Summoning_CircleCenter").setTextureName("Summoning_CircleCenter");
        Summoning_CircleEdges = (new BlockSummoningCircleEdge(false)).setHardness(-1F).setStepSound(Block.soundTypeGrass).setUnlocalizedName("Summoning_CircleSides").setTextureName("Summoning_CircleSides");

        Sakura_Leaves = (new BlockSakuraLeaves()).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setUnlocalizedName("sakuraleaves").setTextureName("sakuraleaves");

        Sakura_Sapling = (new BlockSakuraSapling(3104)).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setUnlocalizedName("sakurasapling").setTextureName("sakurasapling").setCreativeTab(CreativeTabs.tabDecorations);

        bonsaiTree = (new BlockBonsaiTree()).setHardness(3F).setStepSound(Block.soundTypeGrass).setUnlocalizedName("bonsaitree").setTextureName("bonsaitree").setCreativeTab(CreativeTabs.tabDecorations);

        bonsaiSakuraTree = (new BlockBonsaiSakuraTree()).setHardness(3F).setStepSound(Block.soundTypeGrass).setUnlocalizedName("bonsaisakuratree").setTextureName("bonsaisakuratree").setCreativeTab(CreativeTabs.tabDecorations);

        // if you find out what the rest of the registerBlock does then maybe that will allow more advanced blocks
        GameRegistry.registerBlock(PaperBombBlock, PaperBombBlock.getUnlocalizedName());

        GameRegistry.registerBlock(Summoning_CircleCenter, Summoning_CircleCenter.getUnlocalizedName());
        GameRegistry.registerBlock(Summoning_CircleEdges, Summoning_CircleEdges.getUnlocalizedName());

        GameRegistry.registerBlock(Sakura_Leaves, Sakura_Leaves.getUnlocalizedName());

        GameRegistry.registerBlock(Sakura_Sapling, Sakura_Sapling.getUnlocalizedName());

        GameRegistry.registerBlock(bonsaiTree, bonsaiTree.getUnlocalizedName());

        GameRegistry.registerBlock(bonsaiSakuraTree, bonsaiSakuraTree.getUnlocalizedName());

        // Bonsai
        GameRegistry.addShapelessRecipe(new ItemStack(bonsaiTree, 1), Items.flower_pot, Blocks.sapling);

        GameRegistry.addShapelessRecipe(new ItemStack(bonsaiSakuraTree, 1), Items.flower_pot, Sakura_Sapling);

    }

}
