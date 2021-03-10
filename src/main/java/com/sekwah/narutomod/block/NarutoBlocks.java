package com.sekwah.narutomod.block;

import com.sekwah.narutomod.block.weapons.PaperBombBlock;
import com.sekwah.narutomod.item.NarutoItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

public class NarutoBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Block> PAPER_BOMB = BLOCKS.register("paper_bomb",
            () -> new PaperBombBlock(AbstractBlock.Properties.of(Material.EXPLOSIVE).noCollission().strength(0.5F).sound(SoundType.CROP)));

    public static final RegistryObject<Item> ITEM_PAPER_BOMB = BLOCK_ITEMS.register("paper_bomb", ()
            -> new BlockItem(PAPER_BOMB.get(), new Item.Properties().tab(NarutoItems.NINJA_WEAPONS)));

    public static final RegistryObject<Block> BONSAI_TREE = BLOCKS.register("bonsai_tree",
            () -> new RotatableDecorativeBlock(AbstractBlock.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.CROP)
                    , Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D)));

    public static final RegistryObject<Item> ITEM_BONSAI_TREE = BLOCK_ITEMS.register("bonsai_tree", ()
            -> new BlockItem(BONSAI_TREE.get(), new Item.Properties().tab(NarutoItems.NINJA_WEAPONS)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }

    public static void setFlammableData() {
        FireBlock fireblock = (FireBlock)Blocks.FIRE;
        fireblock.setFlammable(PAPER_BOMB.get(), 15, 100);
    }
}
