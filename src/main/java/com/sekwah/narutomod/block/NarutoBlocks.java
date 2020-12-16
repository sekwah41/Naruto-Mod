package com.sekwah.narutomod.block;

import com.sekwah.narutomod.block.weapons.PaperBombBlock;
import com.sekwah.narutomod.item.NarutoItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.state.BooleanProperty;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

public class NarutoBlocks {



    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Block> PAPER_BOMB = BLOCKS.register("paper_bomb",
            () -> new PaperBombBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.PLANT)));

    public static final RegistryObject<Item> ITEM_PAPER_BOMB = BLOCK_ITEMS.register("paper_bomb", ()
            -> new BlockItem(PAPER_BOMB.get(), new Item.Properties().group(NarutoItems.NINJA_WEAPONS)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }
}
