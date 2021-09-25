package com.sekwah.sekclib;

import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterCapabilitySyncEvent;
import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterSyncTrackerTypeEvent;
import com.sekwah.sekclib.capabilitysync.capability.ISyncData;
import com.sekwah.sekclib.network.SekCPacketHandler;
import com.sekwah.sekclib.registries.SekCLibRegistries;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This will be seperated out into its own mod once it has more features.
 */
public class SekCLib {

    //public static final String MOD_ID = "sekclib";
    /**
     * Switch back to sekclib when split
     */
    public static final String MOD_ID = "narutomod";

    public static final Logger LOGGER = LogManager.getLogger("SekC Lib");

    public SekCLib() {

        //ModLoadingContext loadingContext = ModLoadingContext.get();
        //loadingContext.registerConfig(ModConfig.Type.COMMON, NarutoConfig.MOD_CONFIG, "naruto-mod.toml");

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::registerCapabilities);
        eventBus.addListener(this::setup);
        eventBus.addListener(SekCLibRegistries::registerRegistries);

    }

    private void setup(final FMLCommonSetupEvent event) {
        SekCPacketHandler.init();
        SekCLibRegistries.capabilityRegistry.unfreeze();
        ModLoader.get().postEvent(new RegisterSyncTrackerTypeEvent());
        ModLoader.get().postEvent(new RegisterCapabilitySyncEvent());
        SekCLibRegistries.capabilityRegistry.freeze();
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ISyncData.class);
    }

}
