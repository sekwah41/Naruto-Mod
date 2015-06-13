package sekwah.mods.narutomod.client;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;

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
			playerMP.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, (int)posX, (int)posY, (int)posZ, 0);
		}
		else if(soundID == 6){
			playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.clone_poof", 0.5F, 1F, false);
		}
		else if(soundID == 7){
			playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:jutsusounds.whoosh", 0.5F, 1F, false);
		}
		else if(soundID == 20){
			playerMP.worldObj.playSound(posX, posY, posZ, "narutomod:eventsounds.MissionComplete", 0.5F, 1F, false);
		}
	}
	
}
