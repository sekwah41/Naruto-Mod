package com.sekwah.sekclib.capabilitysync;

import com.sekwah.sekclib.capabilitysync.annotation.Sync;
import net.minecraftforge.common.capabilities.Capability;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Currently only designed for players though if there is demand we can add entity syncing too.
 */
public class CapabilitySyncRegistry {

    private static final List<Capability<?>> SYNC_PLAYER_CAPABILITIES = new ArrayList<>();

    /**
     * @param capability the capability to process
     * @param clazz so that the fields can be pre-grabbed.
     */
    public static void registerPlayerCap(Capability<?> capability, Class<?> clazz) {
        SYNC_PLAYER_CAPABILITIES.add(capability);
        Field[] values = Arrays.stream(clazz.getDeclaredFields())
                .filter(value -> value.isAnnotationPresent(Sync.class)).toArray(Field[]::new);
        for (Field field : values) {

        }
    }


    /**
     * @return list of capabilities for syncing later
     */
    @java.lang.SuppressWarnings("squid:S1452")
    public static List<Capability<?>> getPlayerCapabilities() {
        return SYNC_PLAYER_CAPABILITIES;
    }
}
