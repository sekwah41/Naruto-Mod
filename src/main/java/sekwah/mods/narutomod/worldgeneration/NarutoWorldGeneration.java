package sekwah.mods.narutomod.worldgeneration;

import cpw.mods.fml.common.registry.GameRegistry;

public class NarutoWorldGeneration {
    public static WorldGenSpawnSakuraTrees worldGen = new WorldGenSpawnSakuraTrees();

    public static void registerWorldGenerators() {
        GameRegistry.registerWorldGenerator(worldGen, 1);

    }

}
