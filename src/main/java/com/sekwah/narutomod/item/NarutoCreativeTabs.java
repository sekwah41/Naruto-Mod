package com.sekwah.narutomod.item;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.block.NarutoBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NarutoCreativeTabs {

    @SubscribeEvent
    public static void creativeTabRegistry(CreativeModeTabEvent.Register event) {
        NINJA_WEAPONS = event.registerCreativeModeTab(new ResourceLocation(NarutoMod.MOD_ID, "narutomod_weapons"), (builder -> {
            builder.icon(() -> NarutoItems.KUNAI.get().getDefaultInstance()).displayItems((features, output, hasPermissions) -> {
                output.accept(NarutoItems.KUNAI.get());
                output.accept(NarutoItems.SENBON.get());
                output.accept(NarutoItems.EXPLOSIVE_KUNAI.get());
                output.accept(NarutoItems.SHURIKEN.get());
                output.accept(NarutoBlocks.ITEM_PAPER_BOMB.get());
                output.accept(NarutoItems.KATANA.get());
            }).title(Component.translatable("narutomod_weapons"));
        }));

        NINJA_ARMOR = event.registerCreativeModeTab(new ResourceLocation(NarutoMod.MOD_ID, "narutomod_armor"), (builder -> {
            builder.icon(() -> NarutoItems.RED_ANBU_MASK.get().getDefaultInstance()).displayItems((features, output, hasPermissions) -> {
                output.accept(NarutoItems.RED_ANBU_MASK.get());
                output.accept(NarutoItems.YELLOW_ANBU_MASK.get());
                output.accept(NarutoItems.GREEN_ANBU_MASK.get());
                output.accept(NarutoItems.BLUE_ANBU_MASK.get());
                output.accept(NarutoItems.MIST_ANBU_MASK.get());
                output.accept(NarutoItems.FLAK_JACKET_NEW.get());
                output.accept(NarutoItems.FLAK_JACKET.get());
                output.accept(NarutoItems.ANBU_ARMOR.get());
                output.accept(NarutoItems.AKATSUKI_CLOAK.get());
            }).title(Component.translatable("narutomod_armor"));
        }));

        HEADBANDS = event.registerCreativeModeTab(new ResourceLocation(NarutoMod.MOD_ID, "narutomod_headbands"), (builder -> {
            builder.icon(() -> NarutoItems.HEADBAND_BLUE.get().getDefaultInstance()).displayItems((features, output, hasPermissions) -> {
                output.accept(NarutoItems.HEADBAND_BLUE.get());
                output.accept(NarutoItems.HEADBAND_BLACK.get());
                output.accept(NarutoItems.HEADBAND_RED.get());
                output.accept(NarutoItems.HEADBAND_CUSTARD.get());
                output.accept(NarutoItems.HEADBAND_LEAF.get());
                output.accept(NarutoItems.HEADBAND_LEAF_SCRATCHED.get());
                output.accept(NarutoItems.HEADBAND_LEAF_BLACK.get());
                output.accept(NarutoItems.HEADBAND_LEAF_BLACK_SCRATCHED.get());
                output.accept(NarutoItems.HEADBAND_ROCK.get());
                output.accept(NarutoItems.HEADBAND_ROCK_SCRATCHED.get());
                output.accept(NarutoItems.HEADBAND_SAND.get());
                output.accept(NarutoItems.HEADBAND_SAND_SCRATCHED.get());
                output.accept(NarutoItems.HEADBAND_SOUND.get());
                output.accept(NarutoItems.HEADBAND_SOUND_SCRATCHED.get());
                output.accept(NarutoItems.HEADBAND_MIST.get());
                output.accept(NarutoItems.HEADBAND_MIST_SCRATCHED.get());
                output.accept(NarutoItems.HEADBAND_WATERFALL.get());
                output.accept(NarutoItems.HEADBAND_WATERFALL_SCRATCHED.get());
                output.accept(NarutoItems.HEADBAND_CLOUD.get());
                output.accept(NarutoItems.HEADBAND_CLOUD_SCRATCHED.get());
                output.accept(NarutoItems.HEADBAND_RAIN.get());
                output.accept(NarutoItems.HEADBAND_RAIN_SCRATCHED.get());
                output.accept(NarutoItems.HEADBAND_GRASS.get());
                output.accept(NarutoItems.HEADBAND_GRASS_SCRATCHED.get());
                output.accept(NarutoItems.HEADBAND_PRIDE.get());
                output.accept(NarutoItems.HEADBAND_YOUTUBE.get());
                output.accept(NarutoItems.HEADBAND_LAVA.get());
            }).title(Component.translatable("narutomod_headbands"));
        }));

        NINJA_MATERIALS = event.registerCreativeModeTab(new ResourceLocation(NarutoMod.MOD_ID, "narutomod_materials"), (builder -> {
            builder.icon(() -> NarutoItems.FABRIC.get().getDefaultInstance()).displayItems((features, output, hasPermissions) -> {
                output.accept(NarutoItems.FABRIC.get());
                output.accept(NarutoItems.FABRIC_REINFORCED.get());
                output.accept(NarutoItems.FABRIC_REINFORCED_GREEN.get());
                output.accept(NarutoItems.FABRIC_REINFORCED_BLACK.get());
                output.accept(NarutoItems.ARMOR_PLATE.get());
                output.accept(NarutoItems.ARMOR_PLATE_GREEN.get());
            }).title(Component.translatable("narutomod_materials"));
        }));

    }


    @SubscribeEvent
    public static void creativeModeBuildContent(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(NarutoItems.LONELY_MARCH.get());
        } else if(event.getTab() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(NarutoBlocks.ITEM_BONSAI_TREE.get());
        }
    }

    public static CreativeModeTab NINJA_WEAPONS;
    public static CreativeModeTab NINJA_ARMOR;
    public static CreativeModeTab HEADBANDS;
    public static CreativeModeTab NINJA_MATERIALS;
}
