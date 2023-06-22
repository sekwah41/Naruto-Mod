package com.sekwah.narutomod.block;

import com.sekwah.narutomod.block.weapons.PaperBombBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

public class NarutoBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // TODO find a way to apply these to the paper bomb
    //public static final Material UNSTABLE_EXPLOSIVE = (new Material.Builder(MaterialColor.FIRE)).noCollider().flammable().notSolidBlocking().nonSolid().destroyOnPush().build();

    public static final RegistryObject<Block> PAPER_BOMB = BLOCKS.register("paper_bomb",
            () -> {
                BlockBehaviour.Properties blockBehavior = BlockBehaviour.Properties.of()
                        .noCollission()
                        .instabreak()
                        .strength(0.5F)
                        .sound(SoundType.CROP);
        return new PaperBombBlock(blockBehavior);
            });

    public static final RegistryObject<Item> ITEM_PAPER_BOMB = BLOCK_ITEMS.register("paper_bomb", ()
            -> new BlockItem(PAPER_BOMB.get(), new Item.Properties()));

    public static final RegistryObject<Block> BONSAI_TREE = BLOCKS.register("bonsai_tree",
            () -> new RotatableDecorativeBlock(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.CROP)
                    , Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D)));

    public static final RegistryObject<Item> ITEM_BONSAI_TREE = BLOCK_ITEMS.register("bonsai_tree", ()
            -> new BlockItem(BONSAI_TREE.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }
}
