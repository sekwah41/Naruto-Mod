package sekwah.mods.narutomod.client;

import net.minecraft.client.entity.EntityClientPlayerMP;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.serverbound.ServerSoundPacket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class SoundEffects {

	public static void play(int soundID, EntityClientPlayerMP playerMP, double posX, double posY, double posZ) {

		switch(soundID) {
			case 1:
				playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.Seal_a", 0.5F, 1F, false);
				break;
			case 2:
				playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.Seal_b", 0.5F, 1F, false);
				break;
			case 3:
				playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.Seal_c", 0.5F, 1F, false);
				break;
			case 4:
				playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.bunshin_seal", 0.5F, 1F, false);
				break;
			case 5: // Fireball sound
				playerMP.worldObj.playAuxSFXAtEntity(null, 1009, (int)posX, (int)posY, (int)posZ, 0);
				break;
			case 6:
				playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.clone_poof", 0.5F, 1F, false);
				break;
			case 7:
				playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.jump_air", 0.5F, 1F, false);
				break;
			case 8:
				playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.leap", 0.5F, 1F, false);
				break;
			case 9:
				playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.chidori", 0.5F, 1F, false);
				break;
		}
	}

	public static void sendSound(EntityClientPlayerMP playerEntity, int soundID){

		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(soundID);
			outputStream.writeDouble(playerEntity.posX);
			outputStream.writeDouble(playerEntity.posY);
			outputStream.writeDouble(playerEntity.posZ);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		PacketDispatcher.sendPacketToServer(new ServerSoundPacket(bos.toByteArray()));
	}

}
