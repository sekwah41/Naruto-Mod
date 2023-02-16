package com.sekwah.narutomod.datagen;

import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.item.NarutoItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider {
    public RecipeGen(DataGenerator gen) {
        super(gen.getPackOutput());
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> recipeConsumer) {

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NarutoItems.RED_ANBU_MASK.get())
                .define('W', Items.WHITE_TERRACOTTA)
                .define('R', Items.RED_DYE)
                .pattern("WRW")
                .pattern("WWW")
                .pattern(" W ")
                .unlockedBy("has_rose_red", has(Items.RED_DYE))
                .unlockedBy("has_white_terracotta", has(Items.WHITE_TERRACOTTA))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NarutoItems.YELLOW_ANBU_MASK.get())
                .define('W', Items.WHITE_TERRACOTTA)
                .define('Y', Items.YELLOW_DYE)
                .pattern("WYW")
                .pattern("WWW")
                .pattern(" W ")
                .unlockedBy("has_yellow_dye", has(Items.YELLOW_DYE))
                .unlockedBy("has_white_terracotta", has(Items.WHITE_TERRACOTTA))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NarutoItems.BLUE_ANBU_MASK.get())
                .define('W', Items.WHITE_TERRACOTTA)
                .define('B', Items.BLUE_DYE)
                .pattern("WBW")
                .pattern("WWW")
                .pattern(" W ")
                .unlockedBy("has_blue_dye", has(Items.BLUE_DYE))
                .unlockedBy("has_white_terracotta", has(Items.WHITE_TERRACOTTA))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NarutoItems.GREEN_ANBU_MASK.get())
                .define('W', Items.WHITE_TERRACOTTA)
                .define('G', Items.GREEN_DYE)
                .pattern("WGW")
                .pattern("WWW")
                .pattern(" W ")
                .unlockedBy("has_blue_dye", has(Items.GREEN_DYE))
                .unlockedBy("has_white_terracotta", has(Items.WHITE_TERRACOTTA))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NarutoItems.MIST_ANBU_MASK.get())
                .define('W', Items.WHITE_TERRACOTTA)
                .define('R', Items.RED_DYE)
                .pattern("WWW")
                .pattern("WRW")
                .pattern(" W ")
                .unlockedBy("has_rose_red", has(Items.RED_DYE))
                .unlockedBy("has_white_terracotta", has(Items.WHITE_TERRACOTTA))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NarutoItems.KUNAI.get(), 8)
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .pattern(" I")
                .pattern("S ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NarutoItems.SHURIKEN.get(), 4)
                .define('N', Items.IRON_NUGGET)
                .pattern(" N ")
                .pattern("N N")
                .pattern(" N ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NarutoItems.SENBON.get(), 16)
                .define('N', Items.IRON_NUGGET)
                .pattern("  N")
                .pattern(" N ")
                .pattern("N  ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .save(recipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, NarutoItems.EXPLOSIVE_KUNAI.get())
                .requires(NarutoItems.KUNAI.get())
                .requires(NarutoBlocks.ITEM_PAPER_BOMB.get())
                .unlockedBy("has_paper_bomb", has(NarutoBlocks.ITEM_PAPER_BOMB.get()))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NarutoBlocks.ITEM_PAPER_BOMB.get())
                .define('G', Items.GUNPOWDER)
                .define('P', Items.PAPER)
                .pattern("GGG")
                .pattern("PPP")
                .pattern("GGG")
                .unlockedBy("has_gunpowder", has(Items.GUNPOWDER))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NarutoItems.FABRIC.get())
                .define('W', ItemTags.WOOL)
                .pattern("WWW")
                .pattern("WWW")
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NarutoItems.FABRIC_REINFORCED.get(), 3)
                .define('F', NarutoItems.FABRIC.get())
                .define('I', Items.IRON_INGOT)
                .pattern("III")
                .pattern("FFF")
                .unlockedBy("has_fabric", has(NarutoItems.FABRIC.get()))
                .save(recipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, NarutoItems.FABRIC_REINFORCED_BLACK.get(), 1)
                .requires(NarutoItems.FABRIC_REINFORCED.get())
                .requires(Items.BLACK_DYE)
                .unlockedBy("has_fabric_reinforced", has(NarutoItems.FABRIC_REINFORCED.get()))
                .save(recipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, NarutoItems.FABRIC_REINFORCED_GREEN.get(), 1)
                .requires(NarutoItems.FABRIC_REINFORCED.get())
                .requires(Items.GREEN_DYE)
                .unlockedBy("has_fabric_reinforced", has(NarutoItems.FABRIC_REINFORCED.get()))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NarutoItems.ARMOR_PLATE.get(), 3)
                .define('F', NarutoItems.FABRIC_REINFORCED.get())
                .define('I', Items.IRON_INGOT)
                .pattern("III")
                .pattern("FFF")
                .unlockedBy("has_fabric_reinforced", has(NarutoItems.FABRIC_REINFORCED.get()))
                .save(recipeConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, NarutoItems.ARMOR_PLATE_GREEN.get(), 1)
                .requires(NarutoItems.ARMOR_PLATE.get())
                .requires(Items.GREEN_DYE)
                .unlockedBy("has_naruto_armor_plate", has(NarutoItems.ARMOR_PLATE.get()))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NarutoItems.ANBU_ARMOR.get(), 1)
                .define('B', NarutoItems.FABRIC_REINFORCED_BLACK.get())
                .define('A', NarutoItems.ARMOR_PLATE.get())
                .pattern("B B")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_naruto_armor_plate", has(NarutoItems.ARMOR_PLATE.get()))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NarutoItems.FLAK_JACKET.get(), 1)
                .define('A', NarutoItems.ARMOR_PLATE_GREEN.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .unlockedBy("has_naruto_armor_plate_green", has(NarutoItems.ARMOR_PLATE_GREEN.get()))
                .save(recipeConsumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, NarutoItems.FLAK_JACKET_NEW.get(), 1)
                .define('F', NarutoItems.FABRIC_REINFORCED_GREEN.get())
                .pattern("F F")
                .pattern("FFF")
                .pattern("FFF")
                .unlockedBy("has_fabric_reinforced_green", has(NarutoItems.FABRIC_REINFORCED_GREEN.get()))
                .save(recipeConsumer);

    }
}
