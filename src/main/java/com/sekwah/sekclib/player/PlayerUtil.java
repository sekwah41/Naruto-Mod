package com.sekwah.sekclib.player;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class PlayerUtil {

    /**
     * Sets the player velocity and also sends it to the client.
     * @param player effected player
     * @param velocity vector to set
     */
    public static void setVelocity(Player player, Vec3 velocity) {
        player.setDeltaMovement(velocity);
        if(velocity.y > 0) {
            player.fallDistance = 0;
        }
        Vec3 ap = player.getDeltaMovement();
        if(player instanceof ServerPlayer serverPlayer) {
            serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(player.getId(), ap));
        }
    }

}
