package com.sekwah.narutomod.util;

import net.minecraft.world.level.block.state.properties.AttachFace;

public class StateUtils {

    /**
     * Mostly for paperbombs but may be useful
     * @param face
     * @return
     */
    public static byte faceToByte(AttachFace face) {
        switch (face) {
            case WALL:
                return 1;
            case CEILING:
                return 2;
            default:
                return 0;
        }
    }

    /**
     * Mostly for paperbombs but may be useful
     * @param face
     * @return
     */
    public static AttachFace byteToFace(byte face) {
        switch (face) {
            case 1:
                return AttachFace.WALL;
            case 2:
                return AttachFace.CEILING;
            default:
                return AttachFace.FLOOR;
        }
    }

}
