package com.sekwah.sekclib;

import com.sekwah.sekclib.network.PacketHandler;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@Mod(SekCLib.MOD_ID)

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

        ModLoadingContext loadingContext = ModLoadingContext.get();

    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.init();
    }

}
