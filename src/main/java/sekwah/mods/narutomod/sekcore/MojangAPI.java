package sekwah.mods.narutomod.sekcore;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import com.google.gson.Gson;
import sekwah.mods.narutomod.sekcore.mojangapi.UUIDToProfile;
import sekwah.mods.narutomod.sekcore.mojangapi.UsernameToUUID;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MojangAPI {

    private static Gson gson = new Gson();

    public static UUIDToProfile.TextureLocations getUserSkin(String username) {
        try {
            URL url = new URL(String.format("https://api.mojang.com/users/profiles/minecraft/%s?at=%s", username, System.currentTimeMillis() / 1000L));
            InputStream stream = url.openStream();
            UsernameToUUID uuidData = gson.fromJson(new InputStreamReader(stream), UsernameToUUID.class);
            if(uuidData == null) {
                return null;
            }
            url = new URL(String.format("https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false", uuidData.id));
            stream = url.openStream();
            UUIDToProfile profile = gson.fromJson(new InputStreamReader(stream), UUIDToProfile.class);
            if(profile == null || profile.properties.length < 1) {
                return null;
            }
            UUIDToProfile.ProfileProperties properties = profile.properties[0];
            if(!properties.name.equals("textures")) {
                return null;
            }
            byte[] data = BaseEncoding.base64().decode(properties.value);
            String textureString = new String(data, Charsets.UTF_8);
            UUIDToProfile.TextureData textureData = gson.fromJson(textureString, UUIDToProfile.TextureData.class);
            return textureData.textures;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
