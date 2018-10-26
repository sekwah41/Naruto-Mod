package sekwah.mods.narutomod.sekcore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import sekwah.mods.narutomod.sekcore.mojangapi.UUIDToProfile;

import java.io.File;

public class SkinLoader {

    public static ResourceLocation getUserSkin(String username) {
        return new ResourceLocation("skins/" + StringUtils.stripControlCodes(username));
    }

    public static ResourceLocation getUserCape(String username) {
        return new ResourceLocation("capes/" + StringUtils.stripControlCodes(username));
    }

    public static void getDownloadImageSkin(ResourceLocation skinResourceLocation, ResourceLocation capeResourceLocation, String username)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        Object skin = texturemanager.getTexture(skinResourceLocation);
        Object cape = texturemanager.getTexture(capeResourceLocation);
        String skinLocation = "";
        String capeLocation = "";
        if (skin == null && cape == null) {
            UUIDToProfile.TextureLocations textureData = MojangAPI.getUserSkin(StringUtils.stripControlCodes(username));
            if (textureData == null) {
                return;
            }
            if(textureData.SKIN != null) {
                skinLocation = textureData.SKIN.url;
            }
            if(textureData.CAPE != null) {
                capeLocation = textureData.CAPE.url;
            }
        }

        if (skinResourceLocation == null) {
           skinResourceLocation = getUserSkin(username);
        }
        if (capeResourceLocation == null) {
           capeResourceLocation = getUserCape(username);
        }

        if (skin == null && skinResourceLocation != null && !skinLocation.equals(""))
        {
            skin = new ThreadDownloadImageData(null, skinLocation, AbstractClientPlayer.locationStevePng, new ImageBufferDownload());
            texturemanager.loadTexture(skinResourceLocation, (ITextureObject)skin);
        }

        if (cape == null && capeResourceLocation != null && !capeLocation.equals(""))
        {
            cape = new ThreadDownloadImageData(null, capeLocation, null, new ImageBufferDownload());
            texturemanager.loadTexture(capeResourceLocation, (ITextureObject)cape);
        }
    }

}
