package sekwah.mods.narutomod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sekwah.mods.narutomod.animation.NarutoAnimator;
import sekwah.mods.narutomod.animation.Pose;
import sekwah.mods.narutomod.blocks.NarutoBlocks;
import sekwah.mods.narutomod.entitys.NarutoEntitys;
import sekwah.mods.narutomod.generic.CommonProxy;
import sekwah.mods.narutomod.generic.EventServerHook;
import sekwah.mods.narutomod.generic.NarutoEffects;
import sekwah.mods.narutomod.generic.PlayerCommonTickEvent;
import sekwah.mods.narutomod.items.NarutoItems;
import sekwah.mods.narutomod.network.UpdateChecker;
import sekwah.mods.narutomod.network.UsageReport;
import sekwah.mods.narutomod.packets.clientbound.ClientAnimationPacket;
import sekwah.mods.narutomod.packets.clientbound.ClientJutsuPacket;
import sekwah.mods.narutomod.packets.clientbound.ClientParticleEffectPacket;
import sekwah.mods.narutomod.packets.clientbound.ClientSoundPacket;
import sekwah.mods.narutomod.packets.serverbound.ServerAnimationPacket;
import sekwah.mods.narutomod.packets.serverbound.ServerJutsuPacket;
import sekwah.mods.narutomod.packets.serverbound.ServerParticleEffectPacket;
import sekwah.mods.narutomod.packets.serverbound.ServerSoundPacket;
import sekwah.mods.narutomod.settings.NarutoSettings;
import sekwah.mods.narutomod.worldgeneration.NarutoWorldGeneration;

import java.io.InputStream;

// --username sekwah41
@Mod(modid = NarutoMod.modid, name = "Naruto Mod", version = NarutoMod.version)
public class NarutoMod {

    public static final String modid = "narutomod";
    public static final Logger LOGGER = LogManager.getLogger("Sekwah41's Naruto Mod");
    @SidedProxy(clientSide = "sekwah.mods.narutomod.client.ClientProxy", serverSide = "sekwah.mods.narutomod.generic.CommonProxy")
    public static CommonProxy proxy;

    // TODO for the lang files possibly use the \u0007 character code thing and see if that works for accents

    public static final String version = "0.3.4";

    // update data
    public static int mcversion = 1710; // e.g. 164 is 1.6.4

    public static int modversion = 34; // e.g. 030 is 0.3.1

    public static SimpleNetworkWrapper packetNetwork;

    public static NarutoAnimator entityAnimator;

    public static boolean isPreRelease = false;

    public static UsageReport usageReport;
    private UpdateChecker updateChecker;

    @EventHandler
    public void init(FMLInitializationEvent event) {

        // TODO look into the mod hooks for the in game gui so i can change it to a number
        //  also so i can change the chakra back

        //new PlayerPoseAnimator();

        for(int i = 0; i < 66; i++){
            LOGGER.info("Lel");
        }

        entityAnimator = new NarutoAnimator();

        entityAnimator.playerPoses = new Pose[1];
        entityAnimator.playerPoses[0] = new Pose("default");

        InputStream fileStreamJson = NarutoMod.class.getResourceAsStream("/assets/narutomod/mod/poseData.json");
        entityAnimator.playerPoses = entityAnimator.addPoses(fileStreamJson, entityAnimator.playerPoses);

        NarutoEntitys.addEntities(this);

        NarutoWorldGeneration.registerWorldGenerators();

        proxy.addEventListener();

        proxy.addModderCapes();

        proxy.addKeyBindings();

        proxy.addTickHandelers();

        proxy.registerCustomItems();

        proxy.registerCustomBlocks();

        proxy.startUsageReport();

        updateChecker = new UpdateChecker();
        updateChecker.startUpdateChecker();
        //Thread updateChecker = new Thread(new UpdateCheckerOld());
        //updateChecker.run();

        /** ticks are now an event so use an event listener like the bonemeal one or the data watcher one
         @SubscribeEvent public void tick(ClientTickEvent event) {
         TickRegistry.registerTickHandler(new PlayerCommonTickHandler(), Side.SERVER);
         }
         */

        MinecraftForge.EVENT_BUS.register(new EventServerHook());

        MinecraftForge.EVENT_BUS.register(new PlayerCommonTickEvent());

        NarutoEntitys.addSpawns();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // in game menu overlay
        proxy.addInGameGUIs();

        proxy.registerRenderers();

        while(true){
            LOGGER.info("SEKWAH IS AN IDIOT!");
        }

        /**if (Loader.isModLoaded("shadersmod")) { // a way to detect other mods
         throw new NullPointerException("A lighting error has been detected, another mod seems to be overwriting one of the core classes!");
         }*/

    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        this.packetNetwork();

        NarutoEffects.editBasePotion();

        NarutoSettings.preInit(event);

        NarutoEffects.addEffects();

        NarutoBlocks.addBlocks(NarutoSettings.config);

        NarutoItems.addItems(NarutoSettings.config);

        NarutoItems.addDispenserBehavior();

        NarutoItems.addCrafting();
    }

    private void packetNetwork() {
        // Stands for Sekwah's Naruto Mod
        packetNetwork = NetworkRegistry.INSTANCE.newSimpleChannel("SNM");
        packetNetwork.registerMessage(ClientParticleEffectPacket.class, ClientParticleEffectPacket.class, 0, Side.CLIENT);
        packetNetwork.registerMessage(ClientJutsuPacket.class, ClientJutsuPacket.class, 1, Side.CLIENT);
        packetNetwork.registerMessage(ClientSoundPacket.class, ClientSoundPacket.class, 2, Side.CLIENT);
        packetNetwork.registerMessage(ClientAnimationPacket.class, ClientAnimationPacket.class, 3, Side.CLIENT);

        packetNetwork.registerMessage(ServerParticleEffectPacket.class, ServerParticleEffectPacket.class, 4, Side.SERVER);
        packetNetwork.registerMessage(ServerJutsuPacket.class, ServerJutsuPacket.class, 5, Side.SERVER);
        packetNetwork.registerMessage(ServerAnimationPacket.class, ServerAnimationPacket.class, 6, Side.SERVER);
        packetNetwork.registerMessage(ServerSoundPacket.class, ServerSoundPacket.class, 7, Side.SERVER);

    }

}
