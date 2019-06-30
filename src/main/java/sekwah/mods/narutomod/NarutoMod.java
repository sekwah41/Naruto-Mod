package sekwah.mods.narutomod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sekwah.mods.narutomod.animation.NarutoAnimator;
import sekwah.mods.narutomod.client.player.SharinganHandler;
import sekwah.mods.narutomod.client.render.DelayedRender;
import sekwah.mods.narutomod.command.CommandJutsu;
import sekwah.mods.narutomod.common.CommonProxy;
import sekwah.mods.narutomod.common.EventServerHook;
import sekwah.mods.narutomod.common.NarutoEffects;
import sekwah.mods.narutomod.common.PlayerCommonTickEvent;
import sekwah.mods.narutomod.common.block.NarutoBlocks;
import sekwah.mods.narutomod.common.entity.NarutoEntitys;
import sekwah.mods.narutomod.common.items.NarutoItems;
import sekwah.mods.narutomod.network.UpdateChecker;
import sekwah.mods.narutomod.network.UsageReport;
import sekwah.mods.narutomod.packets.clientbound.ClientAnimationPacket;
import sekwah.mods.narutomod.packets.clientbound.ClientJutsuPacket;
import sekwah.mods.narutomod.packets.clientbound.ClientJutsuCommandPacket;
import sekwah.mods.narutomod.packets.clientbound.ClientParticleEffectPacket;
import sekwah.mods.narutomod.packets.clientbound.ClientSoundPacket;
import sekwah.mods.narutomod.packets.serverbound.*;
import sekwah.mods.narutomod.settings.NarutoSettings;
import sekwah.mods.narutomod.worldgeneration.NarutoWorldGeneration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// --username sekwah41
@Mod(modid = NarutoMod.modid, name = "Naruto Mod", version = NarutoMod.version)
public class NarutoMod {

    public static final String modid = "narutomod";

    public static final Logger logger = LogManager.getLogger("Sekwah41's Naruto Mod");

    @SidedProxy(clientSide = "sekwah.mods.narutomod.client.ClientProxy", serverSide = "sekwah.mods.narutomod.common.CommonProxy")
    public static CommonProxy proxy;

    public static final String version = "0.5.0b17";

    public static List<DelayedRender> delayedRenders = new ArrayList<>();

    /**
     * Need to start using instances more.
     */
    @Mod.Instance
	public static NarutoMod instance;

    // update data
    public static int[] mcVersion = {1,7,10};

    public static int[] modVersion = {0,5,1};

    // leave this as false or be more careful!
    public static boolean isPreRelease = true;

    public static SimpleNetworkWrapper packetNetwork;

    public static NarutoAnimator entityAnimator;

    public static UsageReport usageReport;
    private UpdateChecker updateChecker;

    public SharinganHandler sharinganHandler;

    @EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.commands();

        // TODO look into the mod hooks for the in game gui so i can change (hp) to a number
        //  also so i can change the chakra back

        //new PlayerPoseAnimator();

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

        sharinganHandler = new SharinganHandler();

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

        /**if (Loader.isModLoaded("shadersmod")) { // a way to detect other mods
         throw new NullPointerException("A lighting error has been detected, another mod seems to be overwriting one of the core classes!");
         }*/

    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        this.packetNetwork();

        entityAnimator = new NarutoAnimator();

        InputStream fileStreamJson = NarutoMod.class.getResourceAsStream("/assets/narutomod/mod/poseData.json");
        NarutoAnimator.playerPoses = entityAnimator.addPoses(fileStreamJson, NarutoAnimator.playerPoses);

        NarutoEntitys.addEntities(this);

        NarutoEffects.editBasePotion();

        NarutoSettings.preInit(event);

        NarutoEffects.addEffects();

        NarutoBlocks.addBlocks(NarutoSettings.config);

        proxy.addItems();

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
        packetNetwork.registerMessage(ClientJutsuCommandPacket.class, ClientJutsuCommandPacket.class, 4, Side.CLIENT);

        packetNetwork.registerMessage(ServerParticleEffectPacket.class, ServerParticleEffectPacket.class, 100, Side.SERVER);
        packetNetwork.registerMessage(ServerJutsuPacket.class, ServerJutsuPacket.class, 101, Side.SERVER);
        packetNetwork.registerMessage(ServerAnimationPacket.class, ServerAnimationPacket.class, 102, Side.SERVER);
        packetNetwork.registerMessage(ServerSoundPacket.class, ServerSoundPacket.class, 103, Side.SERVER);
        packetNetwork.registerMessage(ServerEyePacket.class, ServerEyePacket.class, 104, Side.SERVER);

    }

    @EventHandler
    public void onServerStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandJutsu());
    }

}
