package com.sekwah.narutomod;

import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import com.sekwah.narutomod.client.gui.NarutoInGameGUI;
import com.sekwah.narutomod.client.keybinds.NarutoKeyHandler;
import com.sekwah.narutomod.client.renderer.NarutoRenderers;
import com.sekwah.narutomod.commands.NarutoCommands;
import com.sekwah.narutomod.config.NarutoConfig;
import com.sekwah.narutomod.entity.NarutoDataSerialisers;
import com.sekwah.narutomod.entity.NarutoEntities;
import com.sekwah.narutomod.item.NarutoItemModels;
import com.sekwah.narutomod.item.NarutoItems;
import com.sekwah.narutomod.network.PacketHandler;
import com.sekwah.narutomod.registries.NarutoRegistries;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(NarutoMod.MOD_ID)
@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID)
public class NarutoMod {

    public static final String MOD_ID = "narutomod";

    public static final Logger LOGGER = LogManager.getLogger("Naruto Mod");

    public NarutoMod() {

        ModLoadingContext loadingContext = ModLoadingContext.get();
        loadingContext.registerConfig(ModConfig.Type.COMMON, NarutoConfig.MOD_CONFIG, "naruto-mod.toml");

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::newRegistryEvent);

        NarutoItems.register(eventBus);
        NarutoBlocks.register(eventBus);
        NarutoEntities.register(eventBus);
        NarutoDataSerialisers.register(eventBus);
        NarutoSounds.register(eventBus);

        DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> NarutoInGameGUI::new);
    }


    private void clientSetup(FMLClientSetupEvent event) {
        NarutoRenderers.registerEntity();
        NarutoRenderers.registerRenderTypes();
        NarutoItemModels.setItemModels();
        NarutoKeyHandler.registerKeyBinds();
    }

    private void setup(FMLCommonSetupEvent event) {
        PacketHandler.init();
        NinjaCapabilityHandler.register();
    }

    @SubscribeEvent
    public static void onServerStarting(RegisterCommandsEvent event) {
        NarutoCommands.register(event.getDispatcher());
    }

    private void newRegistryEvent(RegistryEvent.NewRegistry event) {
        NarutoRegistries.init();
    }

}
