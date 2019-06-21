package sekwah.mods.narutomod.animation;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.client.PlayerRenderTickEvent;
import sekwah.mods.narutomod.client.player.RenderNinjaPlayer;
import sekwah.mods.narutomod.common.DataWatcherIDs;
import sekwah.mods.narutomod.json.JSONObject;
import sekwah.mods.narutomod.packets.PacketAnimationUpdate;
import sekwah.mods.narutomod.packets.PacketDispatcher;
import sekwah.mods.narutomod.packets.serverbound.ServerJutsuPacket;

import java.io.*;
import java.util.ArrayList;

/**
 * TODO look how it works and maybe change this to a hashmap with playerdata refferenced to the playerID.
 */
public class NarutoAnimator {

    public static Pose[] playerPoses = {new Pose("default")};

    public static RenderNinjaPlayer playerRenderer;

    // If you think its nessasary, change it so there is an animator for the player and one for all other mobs(if you think itll create a significant difference)
    public NarutoAnimator() {
        // Setup the default pose, used as a placeholder to stop crashes or potentially replace the playerModels default pose to reduce lag.
        /*playerPoses = new Pose[1];
        playerPoses[0] = new Pose("default");*/
    }



    // old bubblesort algorithm
    /*public static Pose[] sortAnimations(Pose[] poses) {
        for (int i = poses.length - 1; i >= 1; i--) {
            int swaps = 0;
            for (int z = 0; z < i; z++) {
                for (int j = 0; j < poses[z].poseName.length(); j++) {
                    if (j <= poses[z].poseName.length() - 1 && j <= poses[z + 1].poseName.length() - 1) {
                        char animChar = poses[z].poseName.charAt(j);
                        char poseChar = poses[z + 1].poseName.charAt(j);
                        if (animChar > poseChar) {
                            Pose tempPose = poses[z];
                            poses[z] = poses[z + 1];
                            poses[z + 1] = tempPose;
                            swaps++;
                            break;
                        } else if (animChar < poseChar) {
                            break;
                        }
                    } else if (j >= poses[z].poseName.length() - 1) {
                        Pose tempPose = poses[z];
                        poses[z] = poses[z + 1];
                        poses[z + 1] = tempPose;
                        swaps++;
                        break;
                    }
                }
            }
            if(swaps == 0){
                break;
            }
        }
        return poses;
    }*/

    public static Pose[] quickSortAnimations(Pose[] poses) {
        ArrayList<Pose> poseList = new ArrayList<Pose>();
        for(int i = 0; i < poses.length; i++){
            poseList.add(poses[i]);
        }
        poseList = quickSortAnimations(poseList);
        Pose[] newPoses = new Pose[poseList.size()];
        for(int i = 0; i < poseList.size(); i++){
            newPoses[i] = poseList.get(i);
        }
        return newPoses;
    }

    // quicksort
    public static ArrayList<Pose> quickSortAnimations(ArrayList<Pose> poses) {

        ArrayList<Pose> finalPoseList = new ArrayList<Pose>();

        Pose centerPose = poses.get(poses.size() / 2);

        poses.remove(centerPose);

        ArrayList<Pose> beforePoseList = new ArrayList<Pose>();
        ArrayList<Pose> afterPoseList = new ArrayList<Pose>();

        for(Pose pose: poses){
                for (int i = 0; i <= pose.poseName.length(); i++) {
                    if (i < pose.poseName.length() && i < centerPose.poseName.length()) {
                        char animChar = pose.poseName.charAt(i);
                        char centerAnimChar = centerPose.poseName.charAt(i);
                        if (animChar > centerAnimChar) {
                            afterPoseList.add(pose);
                            break;
                        } else if (animChar < centerAnimChar) {
                            beforePoseList.add(pose);
                            break;
                        }
                    } else if (i < pose.poseName.length()) {
                        afterPoseList.add(pose);
                        break;
                    }
                    else{
                        beforePoseList.add(pose);
                        break;
                    }
                }
        }

        if(beforePoseList.size() > 0){
            beforePoseList = quickSortAnimations(beforePoseList);
        }

        if(afterPoseList.size() > 0){
            afterPoseList = quickSortAnimations(afterPoseList);
        }

        finalPoseList.addAll(beforePoseList);
        finalPoseList.add(centerPose);
        finalPoseList.addAll(afterPoseList);

        return finalPoseList;
    }

    public static PartData[] quickSortParts(PartData[] parts) {
        ArrayList<PartData> partList = new ArrayList<PartData>();
        for(int i = 0; i < parts.length; i++){
            partList.add(parts[i]);
        }
        partList = quickSortParts(partList);
        PartData[] newParts = new PartData[partList.size()];
        for(int i = 0; i < partList.size(); i++){
            newParts[i] = partList.get(i);
        }
        return newParts;
    }

    // quicksort
    public static ArrayList<PartData> quickSortParts(ArrayList<PartData> poses) {

        ArrayList<PartData> finalPartDataList = new ArrayList<PartData>();

        PartData centerPartData = poses.get(poses.size() / 2);

        poses.remove(centerPartData);

        ArrayList<PartData> beforePartDataList = new ArrayList<PartData>();
        ArrayList<PartData> afterPartDataList = new ArrayList<PartData>();

        for(PartData part: poses){
                for (int i = 0; i <= part.partName.length(); i++) {
                    if (i < part.partName.length() && i < centerPartData.partName.length()) {
                        char animChar = part.partName.charAt(i);
                        char centerAnimChar = centerPartData.partName.charAt(i);
                        if (animChar > centerAnimChar) {
                            afterPartDataList.add(part);
                            break;
                        } else if (animChar < centerAnimChar) {
                            beforePartDataList.add(part);
                            break;
                        }
                    } else if (i >= part.partName.length() - 1) {
                        afterPartDataList.add(part);
                        break;
                    }
                    else {
                        beforePartDataList.add(part);
                        break;
                    }
                }
        }

        if(beforePartDataList.size() > 0){
            beforePartDataList = quickSortParts(beforePartDataList);
        }

        if(afterPartDataList.size() > 0){
            afterPartDataList = quickSortParts(afterPartDataList);
        }

        finalPartDataList.addAll(beforePartDataList);
        finalPartDataList.add(centerPartData);
        finalPartDataList.addAll(afterPartDataList);

        return finalPartDataList;
    }

    // Old bubble sort for parts
    /*public static PartData[] sortParts(PartData[] parts) {
        for (int i = parts.length - 1; i >= 1; i--) {
            int swaps = 0;
            for (int z = 0; z < i; z++) {
                for (int j = 0; j < parts[z].partName.length(); j++) {
                    if (j <= parts[z].partName.length() - 1 && j <= parts[z + 1].partName.length() - 1) {
                        char animChar = parts[z].partName.charAt(j);
                        char poseChar = parts[z + 1].partName.charAt(j);
                        if (animChar > poseChar) {
                            PartData tempPart = parts[z];
                            parts[z] = parts[z + 1];
                            parts[z + 1] = tempPart;
                            swaps++;
                            break;
                        } else if (animChar < poseChar) {
                            break;
                        }
                    } else if (j >= parts[z].partName.length() - 1) {
                        PartData tempPart = parts[z];
                        parts[z] = parts[z + 1];
                        parts[z + 1] = tempPart;
                        swaps++;
                        break;
                    }
                }
            }
            if(swaps == 0){
                break;
            }
        }
        return parts;
    }*/

    public static boolean animationExists(String animationID, Pose[] poseArray) {
        return getPose(animationID, poseArray) != null;
    }

    public static Pose getPose(String animationID, Pose[] poseArray) {
        int first = 0;
        int last = poseArray.length - 1;
        int currentCharacter = 0;
        int center = -1;
        while (last - first >= 0) {
            center = (int) Math.ceil(first + (last - first) / 2F);
            if (poseArray[center].poseName.equals(animationID)) {
                return poseArray[center];
            }
            int difference = last - first;
            for (int i = 0; i < animationID.length(); i++) {
                if (i <= poseArray[center].poseName.length() - 1 && i <= animationID.length() - 1) {
                    char animChar = animationID.charAt(i);
                    char poseChar = poseArray[center].poseName.charAt(i);
                    if (animChar < poseChar) {
                        last = center - 1;
                        break;
                    } else if (animChar > poseChar) {
                        first = center;
                        break;
                    }
                } else if (i > poseArray[center].poseName.length() - 1) {
                    first = center;
                    break;
                } else if (i > animationID.length() - 1) {
                    last = center;
                    break;
                }
            }
            if (last - first == difference) {
                last -= 1;
            }
        }
        return null;
    }

    public static PartData getPart(String animationID, PartData[] partArray) {
        if(partArray != null){
            int first = 0;
            int last = partArray.length - 1;
            int currentCharacter = 0;
            int center = -1;
            while (last - first >= 0) {
                center = (int) Math.ceil(first + (last - first) / 2F);
                if (partArray[center].partName.equals(animationID)) {
                    return partArray[center];
                }
                int difference = last - first;
                for (int i = 0; i < animationID.length(); i++) {
                    if (i <= partArray[center].partName.length() - 1 && i <= animationID.length() - 1) {
                        char animChar = animationID.charAt(i);
                        char poseChar = partArray[center].partName.charAt(i);
                        if (animChar < poseChar) {
                            last = center - 1;
                            break;
                        } else if (animChar > poseChar) {
                            first = center;
                            break;
                        }
                    } else if (i > partArray[center].partName.length() - 1) {
                        first = center;
                        break;
                    } else if (i > animationID.length() - 1) {
                        last = center;
                        break;
                    }
                }
                if (last - first == difference) {
                    last -= 1;
                }
            }
        }
        return null;
    }

    public static void animate(String animationID, String animationlastID, float animationTick, ArrayList<AnimModelRenderer> animatedParts, Pose[] poseArray) {

        Pose lastPose = null;
        Pose currentPose = null;
        if(!animationID.equals("default") || !animationlastID.equals("default")){
            lastPose = getPose(animationlastID, poseArray);
            currentPose = getPose(animationID, poseArray);
            if(currentPose == null) {
                NarutoMod.logger.error("PoseData not found for: " + animationID);
                return;
            }
            if(lastPose == null){
                NarutoMod.logger.error("PoseData not found for: " + animationlastID);
                return;
            }
        }

        if (animationID.equals("default") && !animationlastID.equals("default")) { // add animation to default too!
            // TODO Switch code to get poses from the pose array
            if (currentPose.animLength >= animationTick) {
                for (AnimModelRenderer part : animatedParts) {
                    PartData partData = getPart(part.boxName, lastPose.partData);
                    animPosePart(part, partData, currentPose.animLength - animationTick, currentPose.animLength);
                }
                // Old format    if(locData.has("rightArmUpper")){animPosePart(modelBiped.bipedRightArmUpper, locData.getJSONObject("rightArmUpper"), animLength - animationTick, animLength);}
            }
        } else if (!animationlastID.equals("default")) {
            for (AnimModelRenderer part : animatedParts) {
                PartData partData = getPart(part.boxName, lastPose.partData);
                setPosePart(part, partData);
            }
        }


        if (!animationID.equals("default")) { // add animation to default too!

            if (currentPose.animLength >= animationTick) {
                for (AnimModelRenderer part : animatedParts) {
                    PartData partData = getPart(part.boxName, currentPose.partData);
                    animPosePart(part, partData, animationTick, currentPose.animLength);
                }

                // Old format    if(locData.has("rightArmUpper")){animPosePart(modelBiped.bipedRightArmUpper, locData.getJSONObject("rightArmUpper"), animationTick, animLength);}
            } else {
                for (AnimModelRenderer part : animatedParts) {
                    PartData partData = getPart(part.boxName, currentPose.partData);
                    setPosePart(part, partData);
                }

                // Old format    if(locData.has("rightArmUpper")){setPosePart(modelBiped.bipedRightArmUpper, locData.getJSONObject("rightArmUpper"));}
            }
        }

    }

    private static void animPosePart(AnimModelRenderer bodyPart, PartData partData, float animationTick, int animLength) {
        if (partData != null) {
            if (partData.shouldRot[0]) {
                bodyPart.rotateAngleX -= ((bodyPart.rotateAngleX - partData.rotateAngleX) / animLength) * animationTick;
            }
            if (partData.shouldRot[1]) {
                bodyPart.rotateAngleY -= ((bodyPart.rotateAngleY - partData.rotateAngleY) / animLength) * animationTick;
            }
            if (partData.shouldRot[2]) {
                bodyPart.rotateAngleZ -= ((bodyPart.rotateAngleZ - partData.rotateAngleZ) / animLength) * animationTick;
            }

            if (partData.hasPos[0]) {
                bodyPart.rotationPointX -= ((bodyPart.rotationPointX - partData.rotationPointX) / animLength) * animationTick;
            }
            if (partData.hasPos[1]) {
                bodyPart.rotationPointY -= ((bodyPart.rotationPointY - partData.rotationPointY) / animLength) * animationTick;
            }
            if (partData.hasPos[2]) {
                bodyPart.rotationPointZ -= ((bodyPart.rotationPointZ - partData.rotationPointZ) / animLength) * animationTick;
            }
        }
    }

    private static void setPosePart(AnimModelRenderer bodyPart, PartData partData) {
        if (partData != null) {
            if (partData.shouldRot[0]) {
                bodyPart.rotateAngleX = partData.rotateAngleX;
            }
            if (partData.shouldRot[1]) {
                bodyPart.rotateAngleY = partData.rotateAngleY;
            }
            if (partData.shouldRot[2]) {
                bodyPart.rotateAngleZ = partData.rotateAngleZ;
            }

            if (partData.hasPos[0]) {
                bodyPart.rotationPointX = partData.rotationPointX;
            }
            if (partData.hasPos[1]) {
                bodyPart.rotationPointY = partData.rotationPointY;
            }
            if (partData.hasPos[2]) {
                bodyPart.rotationPointZ = partData.rotationPointZ;
            }
        }
    }

    /**
     * At the moment this is just updating players
     *
     * @param dw
     * @param entity
     */
    public static void updateEntity(DataWatcher dw, Entity entity, Pose[] poseArray) { // this is for client updates

        //NBTTagCompound data = entity.getEntityData();

        if (!dw.getWatchableObjectString(DataWatcherIDs.jutsuPose).equals(dw.getWatchableObjectString(DataWatcherIDs.poseClient))) {
            dw.updateObject(DataWatcherIDs.animationTick, Float.valueOf(0));
            dw.updateObject(DataWatcherIDs.poseClient, dw.getWatchableObjectString(DataWatcherIDs.jutsuPose));
        }

        if (dw.getWatchableObjectString(DataWatcherIDs.jutsuPose).equals(dw.getWatchableObjectString(DataWatcherIDs.poseClient)) && getPose(dw.getWatchableObjectString(DataWatcherIDs.jutsuPose), poseArray) != null && getPose(dw.getWatchableObjectString(DataWatcherIDs.jutsuPose), poseArray).animLength > dw.getWatchableObjectFloat(DataWatcherIDs.animationTick)) {
            float delta = PlayerRenderTickEvent.delta;
            if (getPose(dw.getWatchableObjectString(DataWatcherIDs.jutsuPose), poseArray).animLength > dw.getWatchableObjectFloat(DataWatcherIDs.animationTick)) {
                dw.updateObject(DataWatcherIDs.animationTick, Float.valueOf(dw.getWatchableObjectFloat(DataWatcherIDs.animationTick) + delta));
            }
        }/* else {
            dw.updateObject(DataWatcherIDs.lastPose, dw.getWatchableObjectString(DataWatcherIDs.jutsuPose));
        }*/
    }

    public static void updateClient(EntityClientPlayerMP playerMP, Pose[] poseArray) {

        NBTTagCompound data = playerMP.getEntityData();

        DataWatcher dw = playerMP.getDataWatcher();
        if (!dw.getWatchableObjectString(DataWatcherIDs.jutsuPose).equals(dw.getWatchableObjectString(DataWatcherIDs.poseClient))) {
            dw.updateObject(DataWatcherIDs.animationTick, Float.valueOf(0));
            dw.updateObject(DataWatcherIDs.poseClient, dw.getWatchableObjectString(DataWatcherIDs.jutsuPose));
            PlayerRenderTickEvent.hasFiredAnimationUpdate = false;
        }

        if (dw.getWatchableObjectString(DataWatcherIDs.jutsuPose).equals(dw.getWatchableObjectString(DataWatcherIDs.poseClient)) && getPose(dw.getWatchableObjectString(DataWatcherIDs.jutsuPose), poseArray) != null && getPose(dw.getWatchableObjectString(DataWatcherIDs.jutsuPose), poseArray).animLength > dw.getWatchableObjectFloat(DataWatcherIDs.animationTick)) {
            float delta = PlayerRenderTickEvent.delta;
            //while (delta-- >= 1) {
            Pose currentPose = getPose(dw.getWatchableObjectString(DataWatcherIDs.jutsuPose), poseArray);
            if (currentPose.animLength > dw.getWatchableObjectFloat(DataWatcherIDs.animationTick)) {
                if(dw.getWatchableObjectFloat(DataWatcherIDs.animationTick) + delta >= currentPose.animLength){
                    dw.updateObject(DataWatcherIDs.animationTick, Float.valueOf(currentPose.animLength));
                }
                else{
                    dw.updateObject(DataWatcherIDs.animationTick, Float.valueOf(dw.getWatchableObjectFloat(DataWatcherIDs.animationTick) + delta));
                }
            } else {
                if (!PlayerRenderTickEvent.hasFiredAnimationUpdate) {
                    animationComplete(playerMP, poseArray);
                }
                PlayerRenderTickEvent.hasFiredAnimationUpdate = true;
            }
            //}
//            if (NarutoSettings.experimentalFirstPersonMode == 2) {
//                NarutoSettings.experimentalFirstPerson = true;
//            }
        } else {
            if (!PlayerRenderTickEvent.hasFiredAnimationUpdate) {
                animationComplete(playerMP, poseArray);
                PlayerRenderTickEvent.hasFiredAnimationUpdate = true;
            }

           /* if (dw.getWatchableObjectString(DataWatcherIDs.jutsuPose).equals("default")) {
                if (NarutoSettings.experimentalFirstPersonMode == 2) {
                    NarutoSettings.experimentalFirstPerson = false;
                }
            }*/
            /**if(NarutoSettings.experimentalFirstPersonMode == 2){
             if(dw.getWatchableObjectString(DataWatcherIDs.jutsuPose).equals("default") && dw.getWatchableObjectString(DataWatcherIDs.poseClient).equals("default")){
             NarutoSettings.experimentalFirstPerson = false;
             }
             else{
             NarutoSettings.experimentalFirstPerson = true;
             }
             }*/
        }
    }

    private static void animationComplete(EntityClientPlayerMP playerMP, Pose[] poseArray) { // triggered by the client to get the next animation
        DataWatcher dw = playerMP.getDataWatcher();
        dw.updateObject(DataWatcherIDs.lastPose, dw.getWatchableObjectString(DataWatcherIDs.jutsuPose));
        String animationID = dw.getWatchableObjectString(DataWatcherIDs.jutsuPose);
        Pose pose = getPose(animationID, poseArray);
        if (pose != null) {
            if (pose.nextPose != null) {
                PacketAnimationUpdate.animationUpdate(pose.nextPose, playerMP);
            }
            if (pose.completeAction != 0) {

                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                DataOutputStream outputStream = new DataOutputStream(bos);
                try {
                    outputStream.writeInt(pose.completeAction);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                PacketDispatcher.sendPacketToServer(new ServerJutsuPacket(bos.toByteArray()));

            }
        }
    }

    public Pose[] addPoses(InputStream filestreamJson, Pose[] poseArray) {
        JSONObject poseFile;
        JSONObject poseData;
        try {
            String json = readJSONFileStream(filestreamJson);
            poseFile = new JSONObject(json);
            poseData = poseFile.getJSONObject("poses");
            NarutoMod.logger.info(JSONObject.getNames("poses"));
            String[] poseNames = JSONObject.getNames(poseData);

            Pose[] oldPoses = poseArray;

            poseArray = new Pose[poseArray.length + poseNames.length];

            for (int i = 0; i < oldPoses.length; i++) {
                poseArray[i] = oldPoses[i];
            }

            for (int i = oldPoses.length; i < poseArray.length; i++) {
                // each pose
                // TODO add code to generate the objects and data from the json

                String currentPoseName = poseNames[i - oldPoses.length];

                NarutoMod.logger.info("Adding pose: " + currentPoseName);

                JSONObject poseInfo = poseData.getJSONObject(currentPoseName);

                JSONObject locData = poseInfo.getJSONObject("locData");
                String[] positionNames = JSONObject.getNames(locData);

                PartData[] partArray = new PartData[positionNames.length];

                for (int n = 0; n < positionNames.length; n++) {
                    // body parts
                    //NarutoMod.logger.info("Pose data: " + positionNames[n]);
                    JSONObject partData = locData.getJSONObject(positionNames[n]);
                    //  NarutoMod.logger.info(positionNames[n]);
                    //String[] partNames = poseFile.getNames(partData);

                    PartData currentPart = new PartData(positionNames[n]);

                    if (partData.has("posX")) {
                        currentPart.hasPos[0] = true;
                        currentPart.rotationPointX = partData.getFloat("posX");
                    }
                    if (partData.has("posY")) {
                        currentPart.hasPos[1] = true;
                        currentPart.rotationPointY = partData.getFloat("posY");
                    }
                    if (partData.has("posZ")) {
                        currentPart.hasPos[2] = true;
                        currentPart.rotationPointZ = partData.getFloat("posZ");
                    }
                    if (partData.has("rotX")) {
                        currentPart.shouldRot[0] = true;
                        currentPart.rotateAngleX = partData.getFloat("rotX");
                    }
                    if (partData.has("rotY")) {
                        currentPart.shouldRot[1] = true;
                        currentPart.rotateAngleY = partData.getFloat("rotY");
                    }
                    if (partData.has("rotZ")) {
                        currentPart.shouldRot[2] = true;
                        currentPart.rotateAngleZ = partData.getFloat("rotZ");
                    }

                    partArray[n] = currentPart;
                }

                Pose currentPose = new Pose(currentPoseName, quickSortParts(partArray));

                currentPose.poseName = currentPoseName;

                // TODO Add other data stuff
                if (poseInfo.has("animLength")) {
                    currentPose.animLength = poseInfo.getInt("animLength");
                }
                if (poseInfo.has("nextPose")) {
                    currentPose.nextPose = poseInfo.getString("nextPose");
                }
                if (poseInfo.has("completeAction")) {
                    currentPose.completeAction = poseInfo.getInt("completeAction");
                }

                poseArray[i] = currentPose;
            }

        } catch (IOException e) {
            NarutoMod.logger.error("Error loading poseData");
            e.printStackTrace();
        }

        /*poseArray = sortAnimations(poseArray);*/
        poseArray = quickSortAnimations(poseArray);

        /*for(Pose pose: poseArray){
            NarutoMod.logger.info(pose.poseName);
        }*/

        return poseArray;
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

    public String readJSONFile(File file) throws IOException {
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
