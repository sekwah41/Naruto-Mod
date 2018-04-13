package sekwah.mods.narutomod.client.player;

import net.minecraft.util.ResourceLocation;

public class SharinganHandler {

    private static final ResourceLocation sharingan2Overlay = new ResourceLocation("narutomod:textures/skinOverlays/mongekyo.png");

    private static final ResourceLocation sharinganOverlay = new ResourceLocation("narutomod:textures/skinOverlays/sharingan.png");

    private static final ResourceLocation rinneganOverlay = new ResourceLocation("narutomod:textures/skinOverlays/rinnegan2x2.png");

    private static final ResourceLocation motherFuckingDEMONSOverlay = new ResourceLocation("narutomod:textures/skinOverlays/demonEyes2x2.png");

    private static final ResourceLocation sharingan1eye2x2 = new ResourceLocation("narutomod:textures/skinOverlays/sharingan1eye2x2.png");

    private static final ResourceLocation sharingan1eye2_2x2 = new ResourceLocation("narutomod:textures/skinOverlays/sharingan1eye2_2x2.png");

    private static final ResourceLocation byakugan = new ResourceLocation("narutomod:textures/skinOverlays/byakugan_2x2.png");

    private static final ResourceLocation hiroCurseMark = new ResourceLocation("narutomod:textures/skinOverlays/hiro_cursemark.png");

    private static final ResourceLocation smove = new ResourceLocation("narutomod:textures/skinOverlays/smove.png");


    public ResourceLocation getEyes(String username, int eyeStatus){

        if (username.endsWith("Zaromaru") && eyeStatus == 1) {
            return rinneganOverlay;
        }
        else if(username.endsWith("Gingershadow") && eyeStatus == 1){
            return motherFuckingDEMONSOverlay;
        }
        else if(username.endsWith("owTreyalP") && eyeStatus == 1){
            return sharingan1eye2x2;
        }
        // TODO add rae to the rest of them
        else if(username.endsWith("KawaiiRae") && eyeStatus == 1){
            return sharingan1eye2_2x2;
        }
        // add miches name
        else if(username.endsWith("CrazyMtch42")){
            return byakugan;
        }
        else if(username.endsWith("GohanPlays_") && eyeStatus != 0){
            return sharinganOverlay;
        }
        else if(username.endsWith("liam3011") && eyeStatus != 0){
            switch(eyeStatus){
                case 1: return sharinganOverlay;
                case 2: return sharingan2Overlay;
                case 3: return sharingan2Overlay;
            }
        }
        else if(username.endsWith("Smove33") && eyeStatus != 0){
            switch(eyeStatus){
                case 1: return sharinganOverlay;
                case 2: return smove;
                case 3: return smove;
            }
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

}
