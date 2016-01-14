package sekwah.mods.narutomod.client;

import net.minecraft.client.entity.EntityClientPlayerMP;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.serverbound.ServerSoundPacket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class SoundEffects {

	public static void play(int soundID, EntityClientPlayerMP playerMP, double posX, double posY, double posZ) {
		
		if(soundID == 1){
			playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.Seal_a", 0.5F, 1F, false);
		}
		else if(soundID == 2){
			playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.Seal_b", 0.5F, 1F, false);
		}
		else if(soundID == 3){
			playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.Seal_c", 0.5F, 1F, false);
		}
		else if(soundID == 4){
			playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.bunshin_seal", 0.5F, 1F, false);
		}
		else if(soundID == 5){ // Fireball sound
			playerMP.worldObj.playAuxSFXAtEntity(null, 1009, (int)posX, (int)posY, (int)posZ, 0);
		}
		else if(soundID == 6){
			playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.clone_poof", 0.5F, 1F, false);
		}
		else if(soundID == 7){
			playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.jump_air", 0.5F, 1F, false);
		}
		else if(soundID == 8){
			playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.leap", 0.5F, 1F, false);
		}
		else if(soundID == 20){
			playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:eventsounds.MissionComplete", 0.5F, 1F, false);
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
