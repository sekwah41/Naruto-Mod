package com.sekwah.narutomod.datagen;

import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.item.NarutoItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider {
    public RecipeGen(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeConsumer) {

        ShapedRecipeBuilder.shaped(NarutoItems.RED_ANBU_MASK.get())
                .define('W', Items.WHITE_TERRACOTTA)
                .define('R', Items.RED_DYE)
                .pattern("WRW")
                .pattern("WWW")
                .pattern(" W ")
                .unlockedBy("has_rose_red", has(Items.RED_DYE))
                .unlockedBy("has_white_terracotta", has(Items.WHITE_TERRACOTTA))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(NarutoItems.YELLOW_ANBU_MASK.get())
                .define('W', Items.WHITE_TERRACOTTA)
                .define('Y', Items.YELLOW_DYE)
                .pattern("WYW")
                .pattern("WWW")
                .pattern(" W ")
                .unlockedBy("has_yellow_dye", has(Items.YELLOW_DYE))
                .unlockedBy("has_white_terracotta", has(Items.WHITE_TERRACOTTA))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(NarutoItems.BLUE_ANBU_MASK.get())
                .define('W', Items.WHITE_TERRACOTTA)
                .define('B', Items.BLUE_DYE)
                .pattern("WBW")
                .pattern("WWW")
                .pattern(" W ")
                .unlockedBy("has_blue_dye", has(Items.BLUE_DYE))
                .unlockedBy("has_white_terracotta", has(Items.WHITE_TERRACOTTA))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(NarutoItems.GREEN_ANBU_MASK.get())
                .define('W', Items.WHITE_TERRACOTTA)
                .define('G', Items.GREEN_DYE)
                .pattern("WGW")
                .pattern("WWW")
                .pattern(" W ")
                .unlockedBy("has_blue_dye", has(Items.GREEN_DYE))
                .unlockedBy("has_white_terracotta", has(Items.WHITE_TERRACOTTA))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(NarutoItems.MIST_ANBU_MASK.get())
                .define('W', Items.WHITE_TERRACOTTA)
                .define('R', Items.RED_DYE)
                .pattern("WWW")
                .pattern("WRW")
                .pattern(" W ")
                .unlockedBy("has_rose_red", has(Items.RED_DYE))
                .unlockedBy("has_white_terracotta", has(Items.WHITE_TERRACOTTA))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(NarutoItems.KUNAI.get(), 8)
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .pattern(" I")
                .pattern("S ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(NarutoItems.SHURIKEN.get(), 4)
                .define('N', Items.IRON_NUGGET)
                .pattern(" N ")
                .pattern("N N")
                .pattern(" N ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(NarutoItems.SENBON.get(), 16)
                .define('N', Items.IRON_NUGGET)
                .pattern("  N")
                .pattern(" N ")
                .pattern("N  ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .save(recipeConsumer);

        ShapelessRecipeBuilder.shapeless(NarutoItems.EXPLOSIVE_KUNAI.get())
                .requires(NarutoItems.KUNAI.get())
                .requires(NarutoBlocks.ITEM_PAPER_BOMB.get())
                .unlockedBy("has_paper_bomb", has(NarutoBlocks.ITEM_PAPER_BOMB.get()))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(NarutoBlocks.ITEM_PAPER_BOMB.get())
                .define('G', Items.GUNPOWDER)
                .define('P', Items.PAPER)
                .pattern("GGG")
                .pattern("PPP")
                .pattern("GGG")
                .unlockedBy("has_gunpowder", has(Items.GUNPOWDER))
                .save(recipeConsumer);


    }
}
