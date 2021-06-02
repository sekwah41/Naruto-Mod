package com.sekwah.narutomod.datagen;

import com.sekwah.narutomod.item.NarutoItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class RecipeGen extends RecipeProvider {
    public RecipeGen(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> recipeConsumer) {

        ShapedRecipeBuilder.shaped(NarutoItems.ANBU_MASK.get()).define('W', Items.WHITE_TERRACOTTA).define('R', Items.RED_DYE)
                .pattern("WRW")
                .pattern("WWW")
                .pattern(" W ")
                .unlockedBy("has_rose_red", has(Items.RED_DYE))
                .unlockedBy("has_iron_ingot", has(Items.WHITE_TERRACOTTA))
                .save(recipeConsumer);
    }
}
