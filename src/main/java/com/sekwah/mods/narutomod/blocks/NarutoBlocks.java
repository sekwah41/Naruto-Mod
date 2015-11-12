package com.sekwah.mods.narutomod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;

public class NarutoBlocks {

    public static Block PaperBombBlock;
    public static Block PaperBombEntityTexture;

    public static Block Summoning_CircleCenter;
    public static Block Summoning_CircleEdges; // change the edges to be one block with metadata at some point

    public static Block Sakura_Leaves;

    public static Block Sakura_Sapling;

    public static Block bonsaiTree; // needs the renderer to be finished


    public static void addBlocks(Configuration config) {

        // new format of the normal block
        // example = (new Block(160, Material.ground)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("paperbombblock").setTextureName(par1Str)("paperbombblock");


        PaperBombBlock = (new BlockPaperBomb(8)).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("paperbombblock").setBlockTextureName("paperbombblock");

        Summoning_CircleCenter = (new BlockSummoningCircle(false, 0)).setHardness(-1F).setStepSound(Block.soundTypeGrass).setBlockName("Summoning_CircleCenter").setBlockTextureName("Summoning_CircleCenter");
        Summoning_CircleEdges = (new BlockSummoningCircleEdge(false)).setHardness(-1F).setStepSound(Block.soundTypeGrass).setBlockName("Summoning_CircleSides").setBlockTextureName("Summoning_CircleSides");

        Sakura_Leaves = (new BlockSakuraLeaves()).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("sakuraleaves").setBlockTextureName("sakuraleaves");

        Sakura_Sapling = (new BlockSakuraSapling(3104)).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("sakurasapling").setBlockTextureName("sakurasapling").setCreativeTab(CreativeTabs.tabDecorations);

        bonsaiTree = (new BlockBonsaiTree()).setHardness(3F).setStepSound(Block.soundTypeGrass).setBlockName("bonsaitree").setBlockTextureName("bonsaitree").setCreativeTab(CreativeTabs.tabDecorations);

        // if you find out what the rest of the registerBlock does then maybe that will allow more advanced blocks
        GameRegistry.registerBlock(PaperBombBlock, PaperBombBlock.getUnlocalizedName());

        GameRegistry.registerBlock(Summoning_CircleCenter, Summoning_CircleCenter.getUnlocalizedName());
        GameRegistry.registerBlock(Summoning_CircleEdges, Summoning_CircleEdges.getUnlocalizedName());

        GameRegistry.registerBlock(Sakura_Leaves, Sakura_Leaves.getUnlocalizedName());

        GameRegistry.registerBlock(Sakura_Sapling, Sakura_Sapling.getUnlocalizedName());

        GameRegistry.registerBlock(bonsaiTree, bonsaiTree.getUnlocalizedName());

        //LanguageRegistry.addName(Sakura_Leaves, "Sakura Leaves");

        //LanguageRegistry.addName(Sakura_Sapling, "Sakura Sapling");

        //LanguageRegistry.addName(bonsaiTree, "Bonsai Tree");

    }

}
