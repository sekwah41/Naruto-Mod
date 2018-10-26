package sekwah.mods.narutomod.sekcore.mojangapi;

public class UUIDToProfile {

    public String id;

    public String name;

    public ProfileProperties[] properties;

    public class ProfileProperties {
        public String name;
        public String value;
    }

    public class TextureData {
        public long timestamp;
        public String profileId;
        public String profileName;
        public boolean signatureRequired;
        public TextureLocations textures;
    }

    public class TextureLocations {
        public URLContainer SKIN;
        public URLContainer CAPE;
    }

    public class URLContainer {
        public String url;
    }

}
