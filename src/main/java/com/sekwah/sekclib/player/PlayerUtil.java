package com.sekwah.sekclib.player;
import com.sekwah.sekclib.network.SekCPacketHandler;
import com.sekwah.sekclib.network.s2c.ClientVelocityPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class PlayerUtil {

    /**
     * Sets the player velocity and also sends it to the client.
     *
     * If the velocity is positive in the y it will also set on ground to false which avoid the ground reducing speed.
     *
     * @param player effected player
     * @param velocity vector to set
     * @param ignoreGround if the client should ignore the ground when the velocity is set avoiding initial drag
     */
    public static void setVelocity(Player player, Vec3 velocity, boolean ignoreGround) {
        player.setDeltaMovement(velocity);
        if(velocity.y > 0) {
            player.fallDistance = 0;
        }
        Vec3 ap = player.getDeltaMovement();
        if(player instanceof ServerPlayer serverPlayer) {
            if(ignoreGround) {
                SekCPacketHandler.sendToPlayer(new ClientVelocityPacket(ap), serverPlayer);
            } else {
                serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(player.getId(), ap));
            }
        }
    }

    /**
     * Another way to call {@link PlayerUtil#setVelocity(Player, Vec3, boolean)}
     *
     * @param player
     * @param x
     * @param y
     * @param z
     * @param ignoreGround
     */
    public static void setVelocity(Player player, double x, double y, double z, boolean ignoreGround) {
        setVelocity(player, new Vec3(x, y, z), ignoreGround);
    }
}
