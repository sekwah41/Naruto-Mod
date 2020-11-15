package com.sekwah.narutomod;

import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.capabilities.CapabilityHandler;
import com.sekwah.narutomod.client.renderer.entity.NarutoEntityRenderers;
import com.sekwah.narutomod.entity.NarutoEntities;
import com.sekwah.narutomod.item.NarutoItems;
import com.sekwah.narutomod.network.PacketHandler;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(NarutoMod.MOD_ID)
public class NarutoMod {

    public static final String MOD_ID = "narutomod";

    public static final Logger LOGGER = LogManager.getLogger("Naruto Mod");

    public NarutoMod() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::setup);

        NarutoItems.register(eventBus);
        NarutoBlocks.register(eventBus);
        NarutoEntities.register(eventBus);
        NarutoSounds.register(eventBus);
    }


    private void clientSetup(final FMLClientSetupEvent event) {
        NarutoEntityRenderers.register();
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.init();
        CapabilityHandler.register();
    }

    @SubscribeEvent
    public static void onServerStarting(RegisterCommandsEvent event) {

    }

}
