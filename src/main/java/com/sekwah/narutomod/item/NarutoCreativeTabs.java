package com.sekwah.narutomod.item;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.block.NarutoBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NarutoCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static RegistryObject<CreativeModeTab> NINJA_WEAPONS = CREATIVE_MODE_TABS.register("narutomod_weapons", CreativeModeTab.builder()
            .icon(() -> NarutoItems.KUNAI.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(NarutoItems.KUNAI.get());
                output.accept(NarutoItems.SENBON.get());
                output.accept(NarutoItems.EXPLOSIVE_KUNAI.get());
                output.accept(NarutoItems.SHURIKEN.get());
                output.accept(NarutoBlocks.ITEM_PAPER_BOMB.get());
                output.accept(NarutoItems.KATANA.get());
            }).title(Component.translatable("narutomod_weapons"))::build);
    public static RegistryObject<CreativeModeTab> NINJA_ARMOR = CREATIVE_MODE_TABS.register("narutomod_armor", CreativeModeTab.builder()
            .icon(() -> NarutoItems.RED_ANBU_MASK.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(NarutoItems.RED_ANBU_MASK.get());
                output.accept(NarutoItems.YELLOW_ANBU_MASK.get());
                output.accept(NarutoItems.GREEN_ANBU_MASK.get());
                output.accept(NarutoItems.BLUE_ANBU_MASK.get());
                output.accept(NarutoItems.MIST_ANBU_MASK.get());
                output.accept(NarutoItems.FLAK_JACKET_NEW.get());
                output.accept(NarutoItems.FLAK_JACKET.get());
                output.accept(NarutoItems.ANBU_ARMOR.get());
                output.accept(NarutoItems.AKATSUKI_CLOAK.get());
            }).title(Component.translatable("narutomod_armor"))::build);
    public static RegistryObject<CreativeModeTab> HEADBANDS = CREATIVE_MODE_TABS.register("narutomod_headbands", CreativeModeTab.builder()
            .icon(() -> NarutoItems.HEADBAND_BLUE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
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
            }).title(Component.translatable("narutomod_headbands"))::build);
    public static RegistryObject<CreativeModeTab> NINJA_MATERIALS = CREATIVE_MODE_TABS.register("narutomod_materials", CreativeModeTab.builder()
            .icon(() -> NarutoItems.FABRIC.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(NarutoItems.FABRIC.get());
                output.accept(NarutoItems.FABRIC_REINFORCED.get());
                output.accept(NarutoItems.FABRIC_REINFORCED_GREEN.get());
                output.accept(NarutoItems.FABRIC_REINFORCED_BLACK.get());
                output.accept(NarutoItems.ARMOR_PLATE.get());
                output.accept(NarutoItems.ARMOR_PLATE_GREEN.get());
            }).title(Component.translatable("narutomod_materials"))::build);


    @SubscribeEvent
    public static void creativeModeBuildContent(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(NarutoItems.LONELY_MARCH.get());
        } else if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(NarutoBlocks.ITEM_BONSAI_TREE.get());
        }
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
