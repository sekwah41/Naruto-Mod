package com.sekwah.narutomod.events;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.item.NarutoCreativeTabs;
import com.sekwah.narutomod.item.NarutoItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CreativeTabEvents {

    @SubscribeEvent
    public static void creativeModeBuildContent(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(NarutoItems.LONELY_MARCH.get());
        } else if(event.getTab() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(NarutoBlocks.ITEM_BONSAI_TREE.get());
        }
    }

}
