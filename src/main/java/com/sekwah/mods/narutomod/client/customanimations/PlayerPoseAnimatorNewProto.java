package com.sekwah.mods.narutomod.client.customanimations;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.DataWatcher;
import net.minecraft.nbt.NBTTagCompound;
import com.sekwah.mods.narutomod.client.PlayerRenderTickEvent;
import com.sekwah.mods.narutomod.json.JSONObject;
import com.sekwah.mods.narutomod.packets.PacketAnimationUpdate;
import com.sekwah.mods.narutomod.packets.PacketDispatcher;
import com.sekwah.mods.narutomod.packets.serverbound.ServerAnimationPacket;
import com.sekwah.mods.narutomod.player.models.ModelNinjaBiped;

import java.io.*;

public class PlayerPoseAnimatorNewProto {

    public static JSONObject poses;
    private static JSONObject poseData;
    private boolean jsonLoaded = false;

    public PlayerPoseAnimatorNewProto() { // make this loaded on startup
        // TODO add some code to fetch the pose data from dropbox if possible so players don't cheat
        InputStream filestreamJson = PlayerPoseAnimatorNewProto.class.getResourceAsStream("/assets/narutomod/mod/poseData.json");

        try {
            String json = readJSONFileStream(filestreamJson);
            poseData = new JSONObject(json);
            poses = poseData.getJSONObject("poses");
            this.jsonLoaded = true;
        } catch (IOException e) {
            jsonLoaded = false;
            System.out.println("Error loading poseData");
            e.printStackTrace();
        }
    }

    public static void animate(ModelNinjaBiped modelBiped, String animationID, String animationlastID, int animationTick) {

        /**if(animationID != "default"){
         if(poses.has(animationID)){
         JSONObject lastPoseInfo = poses.getJSONObject(animationID);
         JSONObject locData = lastPoseInfo.getJSONObject("locData");

         if(locData.has("rightArmUpper")){lastPosePart(modelBiped.bipedRightArm, locData.getJSONObject("rightArm"));}

         if(locData.has("leftArmUpper")){lastPosePart(modelBiped.bipedLeftArm, locData.getJSONObject("leftArm"));}

         }
         }*/

        //System.out.println("anim:" + animationID + " lastAnim:" + animationlastID + " animationTick:" + animationTick);

        if (animationID.equals("default") && !animationlastID.equals("default")) { // add animation to default too!
            if (poses.has(animationlastID)) {
                JSONObject poseInfo = poses.getJSONObject(animationlastID);
                JSONObject locData = poseInfo.getJSONObject("locData");
                int animLength = poseInfo.getInt("animLength");

                if (animLength > animationTick) {
                    if (locData.has("rightArmUpper")) {
                        animPosePart(modelBiped.bipedRightArmUpper, locData.getJSONObject("rightArmUpper"), animLength - animationTick, animLength);
                    }

                    if (locData.has("leftArmUpper")) {
                        animPosePart(modelBiped.bipedLeftArmUpper, locData.getJSONObject("leftArmUpper"), animLength - animationTick, animLength);
                    }

                    if (locData.has("rightArmLower")) {
                        animPosePart(modelBiped.bipedRightArmLower, locData.getJSONObject("rightArmLower"), animLength - animationTick, animLength);
                    }

                    if (locData.has("leftArmLower")) {
                        animPosePart(modelBiped.bipedLeftArmLower, locData.getJSONObject("leftArmLower"), animLength - animationTick, animLength);
                    }

                    if (locData.has("rightLeg")) {
                        animPosePart(modelBiped.bipedRightLeg, locData.getJSONObject("rightLeg"), animLength - animationTick, animLength);
                    }

                    if (locData.has("leftLeg")) {
                        animPosePart(modelBiped.bipedLeftLeg, locData.getJSONObject("leftLeg"), animLength - animationTick, animLength);
                    }

                    if (locData.has("upperBody")) {
                        animPosePart(modelBiped.bipedBody, locData.getJSONObject("upperBody"), animLength - animationTick, animLength);
                    }

                    if (locData.has("lowerBody")) {
                        animPosePart(modelBiped.bipedLowerBody, locData.getJSONObject("lowerBody"), animLength - animationTick, animLength);
                    }

                    if (locData.has("head")) {
                        animPosePart(modelBiped.bipedHead, locData.getJSONObject("head"), animLength - animationTick, animLength);
                    }

                    if (locData.has("head")) {
                        animPosePart(modelBiped.bipedHeadwear, locData.getJSONObject("head"), animLength - animationTick, animLength);
                    }

                    if (locData.has("head")) {
                        animPosePart(modelBiped.bipedMask, locData.getJSONObject("head"), animLength - animationTick, animLength);
                    }

                    if (locData.has("head")) {
                        animPosePart(modelBiped.bipedMaskmed, locData.getJSONObject("head"), animLength - animationTick, animLength);
                    }

                    if (locData.has("head")) {
                        animPosePart(modelBiped.bipedMasksmall, locData.getJSONObject("head"), animLength - animationTick, animLength);
                    }
                }
            } else {
                System.out.println("[NarutoMod] PoseData not found for: " + animationID);
                throw new NullPointerException("PoseData not found for: " + animationID + ". Either the data is missing or an there is something wrong.");
            }
        } else if (!animationlastID.equals("default")) {
            if (poses.has(animationlastID)) {
                JSONObject lastPoseInfo = poses.getJSONObject(animationlastID);
                JSONObject locData = lastPoseInfo.getJSONObject("locData");

                if (locData.has("rightArmUpper")) {
                    setPosePart(modelBiped.bipedRightArmUpper, locData.getJSONObject("rightArmUpper"));
                }

                if (locData.has("leftArmUpper")) {
                    setPosePart(modelBiped.bipedLeftArmUpper, locData.getJSONObject("leftArmUpper"));
                }

                if (locData.has("rightArmLower")) {
                    setPosePart(modelBiped.bipedRightArmLower, locData.getJSONObject("rightArmLower"));
                }

                if (locData.has("leftArmLower")) {
                    setPosePart(modelBiped.bipedLeftArmLower, locData.getJSONObject("leftArmLower"));
                }

                if (locData.has("rightLeg")) {
                    setPosePart(modelBiped.bipedRightLeg, locData.getJSONObject("rightLeg"));
                }

                if (locData.has("leftLeg")) {
                    setPosePart(modelBiped.bipedLeftLeg, locData.getJSONObject("leftLeg"));
                }

                if (locData.has("upperBody")) {
                    setPosePart(modelBiped.bipedBody, locData.getJSONObject("upperBody"));
                }

                if (locData.has("lowerBody")) {
                    setPosePart(modelBiped.bipedLowerBody, locData.getJSONObject("lowerBody"));
                }

                if (locData.has("head")) {
                    setPosePart(modelBiped.bipedHead, locData.getJSONObject("head"));
                }

                if (locData.has("head")) {
                    setPosePart(modelBiped.bipedHeadwear, locData.getJSONObject("head"));
                }

                if (locData.has("head")) {
                    setPosePart(modelBiped.bipedMask, locData.getJSONObject("head"));
                }

                if (locData.has("head")) {
                    setPosePart(modelBiped.bipedMaskmed, locData.getJSONObject("head"));
                }

                if (locData.has("head")) {
                    setPosePart(modelBiped.bipedMasksmall, locData.getJSONObject("head"));
                }
            } else {
                System.out.println("[NarutoMod] PoseData not found for: " + animationID);
                throw new NullPointerException("PoseData not found for: " + animationID + ". Either the data is missing or an there is something wrong.");
            }
        }

        if (!animationID.equals("default")) { // add animation to default too!
            if (poses.has(animationID)) {
                JSONObject poseInfo = poses.getJSONObject(animationID);
                JSONObject locData = poseInfo.getJSONObject("locData");
                int animLength = poseInfo.getInt("animLength");

                if (animLength > animationTick) {
                    if (locData.has("rightArmUpper")) {
                        animPosePart(modelBiped.bipedRightArmUpper, locData.getJSONObject("rightArmUpper"), animationTick, animLength);
                    }

                    if (locData.has("leftArmUpper")) {
                        animPosePart(modelBiped.bipedLeftArmUpper, locData.getJSONObject("leftArmUpper"), animationTick, animLength);
                    }

                    if (locData.has("rightArmLower")) {
                        animPosePart(modelBiped.bipedRightArmLower, locData.getJSONObject("rightArmLower"), animationTick, animLength);
                    }

                    if (locData.has("leftArmLower")) {
                        animPosePart(modelBiped.bipedLeftArmLower, locData.getJSONObject("leftArmLower"), animationTick, animLength);
                    }

                    if (locData.has("rightLeg")) {
                        animPosePart(modelBiped.bipedRightLeg, locData.getJSONObject("rightLeg"), animationTick, animLength);
                    }

                    if (locData.has("leftLeg")) {
                        animPosePart(modelBiped.bipedLeftLeg, locData.getJSONObject("leftLeg"), animationTick, animLength);
                    }

                    if (locData.has("upperBody")) {
                        animPosePart(modelBiped.bipedBody, locData.getJSONObject("upperBody"), animationTick, animLength);
                    }

                    if (locData.has("lowerBody")) {
                        animPosePart(modelBiped.bipedLowerBody, locData.getJSONObject("lowerBody"), animationTick, animLength);
                    }

                    if (locData.has("head")) {
                        animPosePart(modelBiped.bipedHead, locData.getJSONObject("head"), animationTick, animLength);
                    }

                    if (locData.has("head")) {
                        animPosePart(modelBiped.bipedHeadwear, locData.getJSONObject("head"), animationTick, animLength);
                    }

                    if (locData.has("head")) {
                        animPosePart(modelBiped.bipedMask, locData.getJSONObject("head"), animationTick, animLength);
                    }

                    if (locData.has("head")) {
                        animPosePart(modelBiped.bipedMasksmall, locData.getJSONObject("head"), animationTick, animLength);
                    }

                    if (locData.has("head")) {
                        animPosePart(modelBiped.bipedMaskmed, locData.getJSONObject("head"), animationTick, animLength);
                    }
                } else {
                    if (locData.has("rightArmUpper")) {
                        setPosePart(modelBiped.bipedRightArmUpper, locData.getJSONObject("rightArmUpper"));
                    }

                    if (locData.has("leftArmUpper")) {
                        setPosePart(modelBiped.bipedLeftArmUpper, locData.getJSONObject("leftArmUpper"));
                    }

                    if (locData.has("rightArmLower")) {
                        setPosePart(modelBiped.bipedRightArmLower, locData.getJSONObject("rightArmLower"));
                    }

                    if (locData.has("leftArmLower")) {
                        setPosePart(modelBiped.bipedLeftArmLower, locData.getJSONObject("leftArmLower"));
                    }

                    if (locData.has("rightLeg")) {
                        setPosePart(modelBiped.bipedRightLeg, locData.getJSONObject("rightLeg"));
                    }

                    if (locData.has("leftLeg")) {
                        setPosePart(modelBiped.bipedLeftLeg, locData.getJSONObject("leftLeg"));
                    }

                    if (locData.has("upperBody")) {
                        setPosePart(modelBiped.bipedBody, locData.getJSONObject("upperBody"));
                    }

                    if (locData.has("lowerBody")) {
                        setPosePart(modelBiped.bipedLowerBody, locData.getJSONObject("lowerBody"));
                    }

                    if (locData.has("head")) {
                        setPosePart(modelBiped.bipedHead, locData.getJSONObject("head"));
                    }

                    if (locData.has("head")) {
                        setPosePart(modelBiped.bipedHeadwear, locData.getJSONObject("head"));
                    }

                    if (locData.has("head")) {
                        setPosePart(modelBiped.bipedMask, locData.getJSONObject("head"));
                    }

                    if (locData.has("head")) {
                        setPosePart(modelBiped.bipedMaskmed, locData.getJSONObject("head"));
                    }

                    if (locData.has("head")) {
                        setPosePart(modelBiped.bipedMasksmall, locData.getJSONObject("head"));
                    }
                }
            } else {
                System.out.println("[NarutoMod] PoseData not found for: " + animationID);
                throw new NullPointerException("PoseData not found for: " + animationID + ". Either the data is missing or an there is something wrong.");
            }
        }

    }

    private static void animPosePart(ModelRenderer bodyPart, JSONObject poseInfo, int animationTick, int animLength) {

        if (poseInfo.has("rotX")) {
            bodyPart.rotateAngleX -= ((bodyPart.rotateAngleX - poseInfo.getFloat("rotX")) / animLength) * animationTick;
        }
        if (poseInfo.has("rotY")) {
            bodyPart.rotateAngleY -= ((bodyPart.rotateAngleY - poseInfo.getFloat("rotY")) / animLength) * animationTick;
        }
        if (poseInfo.has("rotZ")) {
            bodyPart.rotateAngleZ -= ((bodyPart.rotateAngleZ - poseInfo.getFloat("rotZ")) / animLength) * animationTick;
        }

        if (poseInfo.has("posX")) {
            bodyPart.rotationPointX -= ((bodyPart.rotationPointX - poseInfo.getFloat("posX")) / animLength) * animationTick;
        }
        if (poseInfo.has("posY")) {
            bodyPart.rotationPointY -= ((bodyPart.rotationPointY - poseInfo.getFloat("posY")) / animLength) * animationTick;
        }
        if (poseInfo.has("posZ")) {
            bodyPart.rotationPointZ -= ((bodyPart.rotationPointZ - poseInfo.getFloat("posZ")) / animLength) * animationTick;
        }

    }

    private static void setPosePart(ModelRenderer bodyPart, JSONObject poseInfo) {
        if (poseInfo.has("rotX")) {
            bodyPart.rotateAngleX = poseInfo.getFloat("rotX");
        }
        if (poseInfo.has("rotY")) {
            bodyPart.rotateAngleY = poseInfo.getFloat("rotY");
        }
        if (poseInfo.has("rotZ")) {
            bodyPart.rotateAngleZ = poseInfo.getFloat("rotZ");
        }

        if (poseInfo.has("posX")) {
            bodyPart.rotationPointX = poseInfo.getFloat("posX");
        }
        if (poseInfo.has("posY")) {
            bodyPart.rotationPointY = poseInfo.getFloat("posY");
        }
        if (poseInfo.has("posZ")) {
            bodyPart.rotationPointZ = poseInfo.getFloat("posZ");
        }
    }

    public static int getAnimTicks(String animationID) {
        if (!animationID.equals("default")) {
            if (poses.has(animationID)) {
                JSONObject poseInfo = poses.getJSONObject(animationID);
                return poseInfo.getInt("animLength");
            }
        }
        return 60;
    }

    public static void updatePlayer(DataWatcher dw, EntityClientPlayerMP playerMP) { // this is for client updates

        NBTTagCompound data = playerMP.getEntityData();

        if (!dw.getWatchableObjectString(20).equals(data.getString("lastposeClient"))) {
            dw.updateObject(25, 0);
            data.setString("lastposeClient", dw.getWatchableObjectString(20));
        }

        if (dw.getWatchableObjectString(20).equals(data.getString("lastposeClient")) & getAnimTicks(dw.getWatchableObjectString(20)) > dw.getWatchableObjectInt(25)) {
            float delta = PlayerRenderTickEvent.delta;
            while (delta-- >= 1) {
                if (getAnimTicks(dw.getWatchableObjectString(20)) > dw.getWatchableObjectInt(25)) {
                    dw.updateObject(25, dw.getWatchableObjectInt(25) + 1);
                }
            }
        } else {
            dw.updateObject(26, dw.getWatchableObjectString(20));
        }
    }

    public static void updateClient(EntityClientPlayerMP playerMP) {

        NBTTagCompound data = playerMP.getEntityData();

        DataWatcher dw = playerMP.getDataWatcher();
        if (!dw.getWatchableObjectString(20).equals(data.getString("lastposeClient"))) {
            dw.updateObject(25, 0);
            data.setString("lastposeClient", dw.getWatchableObjectString(20));
            PlayerRenderTickEvent.hasFiredAnimationUpdate = false;
        }

        if (dw.getWatchableObjectString(20).equals(data.getString("lastposeClient")) & getAnimTicks(dw.getWatchableObjectString(20)) > dw.getWatchableObjectInt(25)) {
            float delta = PlayerRenderTickEvent.delta;
            while (delta-- >= 1) {
                if (getAnimTicks(dw.getWatchableObjectString(20)) > dw.getWatchableObjectInt(25)) {
                    dw.updateObject(25, dw.getWatchableObjectInt(25) + 1);
                }
                // TODO this could possibly be the cause of the animation problem
                //else{
                //if(!PlayerRenderTickEvent.hasFiredAnimationUpdate){
                //dw.updateObject(26, dw.getWatchableObjectString(20));
                //animationComplete(playerMP);
                //}
                //PlayerRenderTickEvent.hasFiredAnimationUpdate = true;
                //}
            }
        } else {
            if (!PlayerRenderTickEvent.hasFiredAnimationUpdate) {
                dw.updateObject(26, dw.getWatchableObjectString(20));
                animationComplete(playerMP);
                PlayerRenderTickEvent.hasFiredAnimationUpdate = true;
            }
        }
    }

    private static void animationComplete(EntityClientPlayerMP playerMP) { // triggered by the client to get the next animation
        DataWatcher dw = playerMP.getDataWatcher();
        String animationID = dw.getWatchableObjectString(20);
        if (poses.has(animationID)) {
            JSONObject poseInfo = poses.getJSONObject(animationID);
            if (poseInfo.has("nextPose")) {
                String nextPose = poseInfo.getString("nextPose");

                // TODO add code here to possibly activate parts of a jutsu or different piece if needed for a specific jutsu such as add timings to the chidori :D
                PacketAnimationUpdate.animationUpdate(nextPose, playerMP);
            }
            if (poseInfo.has("completeAction")) {

                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                DataOutputStream outputStream = new DataOutputStream(bos);
                try {
                    outputStream.writeInt(Integer.parseInt(poseInfo.getString("completeAction")));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                PacketDispatcher.sendPacketToServer(new ServerAnimationPacket(bos.toByteArray()));

            }
        }
    }

    private String readJSONFileStream(InputStream filestreamJson) throws IOException {
        InputStreamReader inr = new InputStreamReader(filestreamJson, "ASCII");
        BufferedReader br = new BufferedReader(inr);
        StringBuffer sb = new StringBuffer();
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            sb.append(line);
            sb.append("\n");
        }

        br.close();
        inr.close();

        return sb.toString();
    }

    public String readJSONFile(File file)
            throws IOException {
        FileInputStream fin = new FileInputStream(file);
        InputStreamReader inr = new InputStreamReader(fin, "ASCII");
        BufferedReader br = new BufferedReader(inr);
        StringBuffer sb = new StringBuffer();
        while (true) {
            String line = br.readLine();
            if (line == null)
                break;
            sb.append(line);
            sb.append("\n");
        }

        br.close();
        inr.close();
        fin.close();

        return sb.toString();
    }

}
