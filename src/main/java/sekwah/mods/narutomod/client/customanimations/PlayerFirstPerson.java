package sekwah.mods.narutomod.client.customanimations;

import sekwah.mods.narutomod.client.player.RenderNinjaPlayer;
import sekwah.mods.narutomod.settings.NarutoSettings;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class PlayerFirstPerson {

    public static void render(Entity entity, float par3) {
        //if(NarutoMod.experimentalFirstPerson){
        // ItemRenderer
        Render renderer = RenderManager.instance.getEntityRenderObject(entity);
        if (renderer instanceof RenderNinjaPlayer) {
            GL11.glPushMatrix();
            RenderNinjaPlayer playerRender = (RenderNinjaPlayer) renderer;

            playerRender.firstPersonChanger();
            // GL11.glTranslatef(0F, 0F, 0.5F);
            //float xOffset = (float) (Math.cos(Math.toRadians(entity.rotationYaw - 90)) * (-entity.getLookVec().yCoord + 0.4) / 1.5);
            //float zOffset = (float) (Math.sin(Math.toRadians(entity.rotationYaw - 90)) * (-entity.getLookVec().yCoord + 0.4) / 1.5);
            //GL11.glTranslatef((float) (xOffset / 3.6), 0.05F, (float) (zOffset / 3.6));
            RenderManager.instance.renderEntitySimple(entity, par3);
            playerRender.firstPersonChangeBack();
            GL11.glPopMatrix();

            //if(playerRender.isModelBiped()){// remove code if it doesnt work with the morph mod
            //}
        }
        //}
    }

    /**
     * this is triggered whenever the camera is told to first render so could also be used for rotations in wall walk or sliding cameras
     */
    public static void cameraUpdate(float par1, int par2) {
        if (FMLClientHandler.instance().getClient().gameSettings.thirdPersonView == 0 && NarutoSettings.experimentalFirstPerson) { // used to make sure that the camera stuff doesnt mess up third person

            EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;

            DataWatcher dw = playerMP.getDataWatcher();

            Render renderer = RenderManager.instance.getEntityRenderObject(playerMP);

            RenderNinjaPlayer playerRender = (RenderNinjaPlayer) renderer;

            float CameraXoffset = (float) (Math.cos(Math.toRadians(playerMP.rotationYaw - 90)) * (-playerMP.getLookVec().yCoord + 0.1) / 1.2) / 3.6F;
            float CameraYoffset = 0;
            float CameraZoffset = (float) (Math.sin(Math.toRadians(playerMP.rotationYaw - 90)) * (-playerMP.getLookVec().yCoord + 0.1) / 1.2) / 3.6F;

            if (!dw.getWatchableObjectString(20).equals("default") || !dw.getWatchableObjectString(26).equals("default")) {
                // TODO if needed add an X offset so the head can move left and right
                CameraXoffset -= (float) (Math.cos(Math.toRadians(playerMP.rotationYaw - 90)) * playerRender.getHeadZOffset()) / 30;
                CameraZoffset -= (float) (Math.sin(Math.toRadians(playerMP.rotationYaw - 90)) * playerRender.getHeadZOffset()) / 30;
                CameraYoffset = playerRender.getHeadYOffset() / 30;
            }

            if (playerMP.isSprinting()) {
                CameraYoffset = 0.2F;

                CameraXoffset = (float) (Math.cos(Math.toRadians(playerMP.rotationYaw - 90)) / 2F);

                CameraZoffset = (float) (Math.sin(Math.toRadians(playerMP.rotationYaw - 90)) / 2F);

            }

            // offset to add to the camera
            GL11.glTranslatef(CameraXoffset, CameraYoffset, CameraZoffset);
        }
    }

}
