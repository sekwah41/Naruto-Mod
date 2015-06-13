package sekwah.mods.narutomod.worldgeneration;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSpawnSakuraTrees implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        BiomeGenBase b = world.getBiomeGenForCoords(chunkX, chunkZ);
        int spawnChance = 1; // if it is 1 it will never spawn in that biome
        //System.out.println("Gen :DDD");
        if (b.biomeName.equals("Plains")) {
            //System.out.println("Plains :DDD");
            spawnChance = random.nextInt(20);
        }

        if (spawnChance == 0) {
            //System.out.println("Tree :DDD");
            int X = chunkX * 16 + random.nextInt(16);

            int Z = chunkZ * 16 + random.nextInt(16);

            int Y = world.getHeightValue(X, Z);
            //System.out.println("X:"  + X + "Y:"  + Y + "Z:"  + Z);
            WorldGenerator tree = new WorldGenSakuraTrees(true, 4 + random.nextInt(3), 3, 3, false);

            if (random.nextInt(10) == 0) {
                tree = new WorldGenBigSakuraTree(true);
            }

            tree.generate(world, random, X, Y, Z);
        }
    }

}