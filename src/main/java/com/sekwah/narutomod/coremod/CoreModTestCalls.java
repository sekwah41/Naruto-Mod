package com.sekwah.narutomod.coremod;

import com.sekwah.narutomod.NarutoMod;

/**
 * These parts are just here to ensure that the ASM is working properly. Will be removed at a later date.
 * {@link net.minecraftforge.coremod.api.ASMAPI}
 */
public class CoreModTestCalls {

    public static void basicTestCall() {
        NarutoMod.LOGGER.debug("Main menu basic insert trigger ASM");
    }

    public static void classTestCall() {
        NarutoMod.LOGGER.debug("Main menu class insert trigger ASM");
    }

}
