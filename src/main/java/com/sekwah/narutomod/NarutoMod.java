package com.sekwah.narutomod;

import com.sekwah.narutomod.abilities.NarutoAbilities;
import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.capabilities.INinjaData;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import com.sekwah.narutomod.capabilities.NinjaData;
import com.sekwah.narutomod.client.gui.NarutoInGameGUI;
import com.sekwah.narutomod.client.keybinds.NarutoKeyHandler;
import com.sekwah.narutomod.client.renderer.NarutoRenderEvents;
import com.sekwah.narutomod.config.NarutoConfig;
import com.sekwah.narutomod.commands.NarutoCommands;
import com.sekwah.narutomod.entity.NarutoDataSerialisers;
import com.sekwah.narutomod.entity.NarutoEntities;
import com.sekwah.narutomod.gameevents.NarutoGameEvents;
import com.sekwah.narutomod.item.NarutoDispenseItemBehavior;
import com.sekwah.narutomod.item.NarutoItems;
import com.sekwah.narutomod.network.PacketHandler;
import com.sekwah.narutomod.registries.NarutoRegistries;
import com.sekwah.narutomod.sounds.NarutoSounds;
import com.sekwah.sekclib.capabilitysync.capabilitysync.RegisterCapabilitySyncEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(NarutoMod.MOD_ID)
@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID)
public class NarutoMod {

    public static final String MOD_ID = "narutomod";

    // private static final Logger LOGGER = LogUtils.getLogger();

    public NarutoMod() {

        ModLoadingContext loadingContext = ModLoadingContext.get();
        loadingContext.registerConfig(ModConfig.Type.COMMON, NarutoConfig.MOD_CONFIG, "naruto-mod.toml");

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::registerCapabilities);
        eventBus.addListener(this::registerCapabilitySync);
        eventBus.addListener(NarutoRegistries::init);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            eventBus.addListener(NarutoKeyHandler::registerKeyBinds);
        }

        NarutoSounds.register(eventBus);
        NarutoDataSerialisers.register(eventBus);
        NarutoItems.register(eventBus);
        NarutoBlocks.register(eventBus);
        NarutoEntities.register(eventBus);
        NarutoAbilities.register(eventBus);
        NarutoGameEvents.register(eventBus);

        DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> NarutoInGameGUI::new);
    }


    private void clientSetup(FMLClientSetupEvent event) {
        NarutoRenderEvents.registerRenderTypes();
        //NarutoWorldRenderEvents.registerEvents();
    }

    private void setup(FMLCommonSetupEvent event) {
        PacketHandler.init();
        NarutoDispenseItemBehavior.register();
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(INinjaData.class);
    }

    @SubscribeEvent
    public static void onServerStarting(RegisterCommandsEvent event) {
        NarutoCommands.register(event.getDispatcher());
    }

    public void registerCapabilitySync(RegisterCapabilitySyncEvent event) {
        event.registerPlayerCap(new ResourceLocation(NarutoMod.MOD_ID, "ninja_data"), NinjaCapabilityHandler.NINJA_DATA, NinjaData.class);
    }
}
