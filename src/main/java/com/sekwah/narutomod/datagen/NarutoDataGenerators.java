package com.sekwah.narutomod.datagen;

import com.sekwah.narutomod.NarutoMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NarutoDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();

        gen.addProvider(event.includeServer(), new NarutoRecipeGen(gen));
        NarutoDataGenerator.addProviders(event.includeServer(), gen, gen.getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper());
        gen.addProvider(event.includeServer(), new NarutoGameEventTagGen(gen, event.getLookupProvider(), event.getExistingFileHelper()));
        gen.addProvider(event.includeClient(), new NarutoSpriteSourceGen(gen.getPackOutput(), event.getExistingFileHelper(), NarutoMod.MOD_ID));
    }
}
