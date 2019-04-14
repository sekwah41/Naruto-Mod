package sekwah.mods.narutomod.client.player;

import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class SharinganHandler {

    private static final ResourceLocation sharingan2Overlay = new ResourceLocation("narutomod:textures/skinOverlays/liam_eyes.png");

    private static final ResourceLocation mangekyouOverlay = new ResourceLocation("narutomod:textures/skinOverlays/mangekyou.png");

    private static final ResourceLocation sharinganOverlay = new ResourceLocation("narutomod:textures/skinOverlays/sharingan.png");

    private static final ResourceLocation rinneganOverlay = new ResourceLocation("narutomod:textures/skinOverlays/rinnegan2x2.png");

    private static final ResourceLocation smove = new ResourceLocation("narutomod:textures/skinOverlays/smove.png");

    private static final ResourceLocation jougan = new ResourceLocation("narutomod:textures/skinOverlays/jougan.png");

    private static final ResourceLocation sageMode = new ResourceLocation("narutomod:textures/skinOverlays/sagemode.png");

    private static final ResourceLocation[] animateToMon = getAnimatedTextures("narutomod:textures/skinOverlays/sharingantransform_frame", 4);

    public ResourceLocation getEyes(String username, int eyeStatus) {
        return this.getEyes(username, eyeStatus, -1);
    }

    public ResourceLocation getEyes(String username, int eyeStatus, int animate){

        if (username.endsWith("Zaromaru") && eyeStatus == 1) {
            return rinneganOverlay;
        }
        else if(username.endsWith("HeroGamezFTW") && eyeStatus == 1) {
            return jougan;
        }
        else if(username.endsWith("HeroGamezFTW") && eyeStatus == 2){
            return sageMode;
        }
        else if(username.endsWith("liam3011") && eyeStatus != 0){
            return returnEyesPlusSusanoo(eyeStatus, sharinganOverlay, sharingan2Overlay);
        }
        else if(username.endsWith("Smove33") && eyeStatus != 0){
            return returnEyesPlusSusanoo(eyeStatus, sharinganOverlay, smove);
        }
        else if(eyeStatus != 0) {
            return returnEyesAnimateSusanoo(sharinganOverlay, mangekyouOverlay, animate);
        }
        return null;
    }

    private ResourceLocation returnEyesAnimateSusanoo(ResourceLocation loc1, ResourceLocation loc2, int animate) {
        switch(animate) {
            case -1:
                return null;
            case 0:
                return loc1;
            case 5:
                return loc2;
            default:
                return animateToMon[animate-1];
        }
    }

    private ResourceLocation returnEyesPlusSusanoo(int eyeStatus, ResourceLocation loc1, ResourceLocation loc2) {
        switch(eyeStatus){
            case 1: return loc1;
            case 2: return loc2;
            case 3: return loc2;
        }
        return null;
    }

    public float[] getColor(String username, int eyeStatus){
        float[] colors = {1f,1f,1f};
        if(username.endsWith("GohanPlays_") && eyeStatus == 1){
            colors[1] = 0;
            colors[2] = 0;
        }
        else if(username.endsWith("Smove33") && eyeStatus != 0){
            colors[1] = 0;
            colors[2] = 0;
        }
        else if(username.endsWith("liam3011") && eyeStatus != 0){
            colors[1] = 0;
            colors[2] = 0;
        }
        return colors;
    }

    private static ResourceLocation[] getAnimatedTextures(String baseName, int count) {
        List<ResourceLocation> tempList = new ArrayList<ResourceLocation>();
        for (int i = 1; i < count + 1; i++) {
            tempList.add(new ResourceLocation(baseName + i + ".png"));
        }
        return tempList.toArray(new ResourceLocation[count]);
    }

}
