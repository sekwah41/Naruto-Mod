package sekwah.mods.narutomod.packets;

import sekwah.mods.narutomod.NarutoMod;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayerMP;
import sekwah.mods.narutomod.client.PlayerClientTickEvent;
import sekwah.mods.narutomod.packets.serverbound.ServerAnimationPacket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class PacketAnimationUpdate {

    public static void animationUpdate(String animationID, EntityPlayerMP playerEntity) {

        if (NarutoMod.entityAnimator.getPose(animationID, NarutoMod.entityAnimator.playerPoses) == null) {
            NarutoMod.LOGGER.error("[NarutoMod] PoseData not found for: " + animationID);
            throw new NullPointerException("PoseData not found for: " + animationID + ". Either the data is missing or an there is something wrong.");
        }

        playerEntity.getDataWatcher().updateObject(20, animationID);

        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try {
            PlayerClientTickEvent.jutsuPoseID = animationID;
            outputStream.writeUTF(animationID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        PacketDispatcher.sendPacketToServer(new ServerAnimationPacket(bos.toByteArray()));

    }

    public static void animationUpdate(String animationID, EntityClientPlayerMP playerEntity) {
        if (NarutoMod.entityAnimator.getPose(animationID, NarutoMod.entityAnimator.playerPoses) == null) {
            NarutoMod.LOGGER.error("[NarutoMod] PoseData not found for: " + animationID);
            throw new NullPointerException("PoseData not found for: " + animationID + ". Either the data is missing or an there is something wrong.");
        }

        playerEntity.getDataWatcher().updateObject(20, animationID);

        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try {
            PlayerClientTickEvent.jutsuPoseID = animationID;
            outputStream.writeUTF(animationID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        PacketDispatcher.sendPacketToServer(new ServerAnimationPacket(bos.toByteArray()));

    }

}
