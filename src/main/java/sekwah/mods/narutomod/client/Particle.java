package sekwah.mods.narutomod.client;

// Potentially start moving particles and everything over to this.
// This class contains particles which will be client side from entity effects(may change though)
public enum Particle {

    COLOURED_SMOKE("colouredSmoke"); // there is a normal and tracking type

    private final String particleName;

    private Particle(String name) {
        this.particleName = name;
    }

    public String getName() {
        return particleName;
    }

}
