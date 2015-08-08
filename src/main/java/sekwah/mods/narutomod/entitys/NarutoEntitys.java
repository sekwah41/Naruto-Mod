package sekwah.mods.narutomod.entitys;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.entitys.jutsuprojectiles.EntityFlameFireball;
import sekwah.mods.narutomod.entitys.jutsuprojectiles.EntityWaterBullet;
import sekwah.mods.narutomod.entitys.projectiles.*;

import java.awt.*;

public class NarutoEntitys {

    public static void addEntities(NarutoMod narutoMod) {
        // Check when you have internet/are back if the entity list is needed and also if the id's are global or
        // if the same ids can be shared across different mods. Its registering a mod id so it may not be like modloader
        // they may be able to start at 1 for each mod and work their way up(would be pretty useful rather than always
        // looking at different ids and being unnecessarily odd... (however it does say mod specific id so its
        // pointing towards being good)

        // Ok it has been reasearched and it is mod specific so it can start from 1 as entities are stored by their string
        // names, however when changed back you will have to create a custom spawn egg sadly :(

        // Also look at the tracker, see if you can fix the kunai and stuff having to extend arrows and also the wheelchair
        //  tracking which is odd...
        EntityRegistry.registerModEntity(EntityKunai.class, "Kunai", 200, narutoMod, 64, 1, true);
        EntityList.IDtoClassMapping.put(200, EntityKunai.class);

        EntityRegistry.registerModEntity(EntityShuriken.class, "Shuriken", 201, narutoMod, 64, 1, true);
        EntityList.IDtoClassMapping.put(201, EntityShuriken.class);

        EntityRegistry.registerModEntity(EntityPaperBomb.class, "PaperBomb", 202, narutoMod, 64, 1, true);
        EntityList.IDtoClassMapping.put(202, EntityPaperBomb.class);

        EntityRegistry.registerModEntity(EntitySenbon.class, "Senbon", 203, narutoMod, 64, 1, true);
        EntityList.IDtoClassMapping.put(203, EntitySenbon.class);

        EntityRegistry.registerModEntity(EntityExplosiveKunai.class, "Explosive Kunai", 204, narutoMod, 64, 1, true);
        EntityList.IDtoClassMapping.put(204, EntityExplosiveKunai.class);

        EntityRegistry.registerModEntity(EntityFlameFireball.class, "Fireball", 205, narutoMod, 64, 1, true);
        EntityList.IDtoClassMapping.put(205, EntityFlameFireball.class);

        EntityRegistry.registerModEntity(EntityWaterBullet.class, "WaterBullet", 206, narutoMod, 64, 1, true);
        EntityList.IDtoClassMapping.put(206, EntityWaterBullet.class);

        EntityRegistry.registerModEntity(EntityPuppet.class, "Puppet", 73, narutoMod, 80, 5, true);
        EntityList.IDtoClassMapping.put(73, EntityPuppet.class);
        EntityList.entityEggs.put(73, new EntityList.EntityEggInfo(73, (new Color(50, 50, 50)).getRGB(), (new Color(195, 173, 169)).getRGB()));

        EntityRegistry.registerModEntity(EntityRogueNinja.class, "Rogue_Ninja", 70, narutoMod, 80, 1, true);
        EntityList.IDtoClassMapping.put(70, EntityRogueNinja.class);
        EntityList.entityEggs.put(70, new EntityList.EntityEggInfo(70, (new Color(40, 40, 40)).getRGB(), (new Color(149, 94, 39)).getRGB()));

        EntityRegistry.registerModEntity(EntityNinjaVillager.class, "Ninja_Villager", 71, narutoMod, 80, 1, true);
        EntityList.IDtoClassMapping.put(71, EntityNinjaVillager.class);
        EntityList.entityEggs.put(71, new EntityList.EntityEggInfo(71, (new Color(42, 135, 58)).getRGB(), (new Color(149, 94, 39)).getRGB()));

        EntityRegistry.registerModEntity(EntityNinjaVillagerAnbu.class, "Ninja_VillagerAnbu", 72, narutoMod, 80, 1, true);
        EntityList.IDtoClassMapping.put(72, EntityNinjaVillagerAnbu.class);
        EntityList.entityEggs.put(72, new EntityList.EntityEggInfo(72, (new Color(173, 173, 173)).getRGB(), (new Color(23, 23, 23)).getRGB()));

        EntityRegistry.registerModEntity(EntityBandit.class, "Bandit", 74, narutoMod, 80, 1, true);
        EntityList.IDtoClassMapping.put(74, EntityBandit.class);
        EntityList.entityEggs.put(74, new EntityList.EntityEggInfo(74, (new Color(227, 13, 13)).getRGB(), (new Color(242, 198, 121)).getRGB()));

        EntityRegistry.registerModEntity(EntityShadowClone.class, "Shadow_Clone", 75, narutoMod, 80, 1, true);
        EntityList.IDtoClassMapping.put(75, EntityShadowClone.class);
        EntityList.entityEggs.put(75, new EntityList.EntityEggInfo(75, (new Color(40, 40, 40)).getRGB(), (new Color(149, 94, 39)).getRGB()));

        EntityRegistry.registerModEntity(EntitySubstitution.class, "Substitution", 76, narutoMod, 80, 1, true);
        EntityList.IDtoClassMapping.put(76, EntitySubstitution.class);

        EntityRegistry.registerModEntity(EntitySubstitutionLog.class, "SubstitutionLog", 77, narutoMod, 80, 1, true);
        EntityList.IDtoClassMapping.put(77, EntitySubstitutionLog.class);

    }

    public static void addSpawns() {
        BiomeGenBase[] biomeList = BiomeGenBase.getBiomeGenArray();
        for (int i = 0; i < biomeList.length; i++) {
            if (biomeList[i] != null && biomeList[i] != BiomeGenBase.ocean && biomeList[i] != BiomeGenBase.hell && biomeList[i] != BiomeGenBase.sky) {
                EntityRegistry.addSpawn(EntityRogueNinja.class, 1, 1, 3, EnumCreatureType.monster, biomeList[i]);

                EntityRegistry.addSpawn(EntityBandit.class, 3, 1, 3, EnumCreatureType.monster, biomeList[i]);
            }
        }

    }

}
