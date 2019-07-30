package sekwah.mods.narutomod.client;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.animation.NarutoAnimator;
import sekwah.mods.narutomod.client.block.itemrenderers.ItemRendererBonsaiTree;
import sekwah.mods.narutomod.client.block.rendereres.TileEntityBonsaiRenderer;
import sekwah.mods.narutomod.client.entity.render.*;
import sekwah.mods.narutomod.client.entity.render.jutsuprojectiles.RenderChibakuTensei;
import sekwah.mods.narutomod.client.entity.render.jutsuprojectiles.RenderFireball;
import sekwah.mods.narutomod.client.entity.render.jutsuprojectiles.RenderWaterBullet;
import sekwah.mods.narutomod.client.entity.render.projectiles.*;
import sekwah.mods.narutomod.client.entity.render.special.RenderChibakuBlock;
import sekwah.mods.narutomod.client.entity.render.special.RenderMovingBlock;
import sekwah.mods.narutomod.client.gui.GuiChakraAndStaminaBar;
import sekwah.mods.narutomod.client.gui.GuiNotificationUpdate;
import sekwah.mods.narutomod.client.player.RenderNinjaPlayer;
import sekwah.mods.narutomod.common.CommonProxy;
import sekwah.mods.narutomod.common.block.NarutoBlocks;
import sekwah.mods.narutomod.common.block.tileentity.TileEntityBonzaiSakuraTree;
import sekwah.mods.narutomod.common.block.tileentity.TileEntityBonzaiTree;
import sekwah.mods.narutomod.common.entity.*;
import sekwah.mods.narutomod.common.entity.jutsuprojectiles.EntityChibakuTensei;
import sekwah.mods.narutomod.common.entity.jutsuprojectiles.EntityFlameFireball;
import sekwah.mods.narutomod.common.entity.jutsuprojectiles.EntityWaterBullet;
import sekwah.mods.narutomod.common.entity.projectiles.*;
import sekwah.mods.narutomod.common.entity.specials.EntityChibakuBlock;
import sekwah.mods.narutomod.common.entity.specials.EntityMovingBlock;
import sekwah.mods.narutomod.common.items.ItemNinjaArmour;
import sekwah.mods.narutomod.common.items.NarutoItems;
import sekwah.mods.narutomod.common.items.itemmodels.ModelFlakJacket;
import sekwah.mods.narutomod.common.items.itemmodels.armor.*;
import sekwah.mods.narutomod.common.items.itemrenderers.RenderItemKubikiribocho;
import sekwah.mods.narutomod.common.items.itemrenderers.RenderItemSamehada;
import sekwah.mods.narutomod.common.items.itemrenderers.RenderItemScroll;
import sekwah.mods.narutomod.common.items.itemrenderers.RenderItemWeaponSmall;
import sekwah.mods.narutomod.network.UsageReport;

public class ClientProxy extends CommonProxy {

    private  SkinFetcher skinFetcher;

    public void addKeyBindings() {

        new NarutoKeyHandler();
        //FMLCommonHandler.instance().bus().register(new NarutoKeyHandler());
        //MinecraftForge.EVENT_BUS.register();

    }

    public boolean isClient(){
        return true;
    }


    public void addTickHandelers() {

        // Has been readded, but in the lsiteners, remove this part soon and make sure its removed everywhere else
        /**TickRegistry.registerTickHandler(new PlayerRenderTickHandler(EnumSet.of(TickType.RENDER)), Side.CLIENT);

         TickRegistry.registerTickHandler(new PlayerClientTickHandler(EnumSet.of(TickType.CLIENT)), Side.CLIENT);*/

        //TickRegistry.registerTickHandler(new PlayerAllPlayerTickHandler(EnumSet.of(TickType.PLAYER)), Side.CLIENT);

    }

    public void getSkin(GameProfile profile, SkinCallback entity) {
        skinFetcher.getSkin(profile, entity);
    }

    public void registerRenderers() {

        skinFetcher = new SkinFetcher(Minecraft.getMinecraft().getSessionService());

        // causes problems at the moment, update it at some point for new animations.
        // RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new RenderNinjaPlayer());

        // register the mob render
        RenderingRegistry.registerEntityRenderingHandler(EntityRogueNinja.class, new RenderRogueNinja());
        RenderingRegistry.registerEntityRenderingHandler(EntityPuppet.class, new RenderPuppet());

        RenderingRegistry.registerEntityRenderingHandler(EntityKunai.class, new RenderKunai());
        RenderingRegistry.registerEntityRenderingHandler(EntityShuriken.class, new RenderShuriken());
        RenderingRegistry.registerEntityRenderingHandler(EntityExplosiveKunai.class, new RenderExplosiveKunai());

        RenderingRegistry.registerEntityRenderingHandler(EntityFlameFireball.class, new RenderFireball());

        RenderingRegistry.registerEntityRenderingHandler(EntityPaperBomb.class, new RenderPaperBomb());

        RenderingRegistry.registerEntityRenderingHandler(EntitySenbon.class, new RenderSenbon());

        RenderingRegistry.registerEntityRenderingHandler(EntityNinjaVillager.class, new RenderNinjaVillager());
        RenderingRegistry.registerEntityRenderingHandler(EntityNinjaVillagerAnbu.class, new RenderNinjaVillagerAnbu());

        RenderingRegistry.registerEntityRenderingHandler(EntityBandit.class, new RenderBandit());

        RenderingRegistry.registerEntityRenderingHandler(EntityShadowClone.class, new RenderShadowClone());

        RenderingRegistry.registerEntityRenderingHandler(EntitySubstitution.class, new RenderSubstitution());

        RenderingRegistry.registerEntityRenderingHandler(EntitySubstitutionLog.class, new RenderSubstitutionLog());

        RenderingRegistry.registerEntityRenderingHandler(EntityWaterBullet.class, new RenderWaterBullet());

        RenderingRegistry.registerEntityRenderingHandler(EntityMovingBlock.class, new RenderMovingBlock());
        
        RenderingRegistry.registerEntityRenderingHandler(EntityChibakuBlock.class, new RenderChibakuBlock());

        RenderingRegistry.registerEntityRenderingHandler(EntityChibakuTensei.class, new RenderChibakuTensei());

        NarutoAnimator.playerRenderer = new RenderNinjaPlayer();

        RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, NarutoAnimator.playerRenderer);

        // Register the custom block render
        GameRegistry.registerTileEntity(TileEntityBonzaiSakuraTree.class, "bonsaitree");

    }

    public void addEventListener() {
        // TODO change common stuff to be in the common proxy but activate it with
        // super.addEventListener(); // much tidier
        MinecraftForge.EVENT_BUS.register(new EventHook());
        FMLCommonHandler.instance().bus().register(new EventHook());

        FMLCommonHandler.instance().bus().register(new PlayerClientTickEvent());

        FMLCommonHandler.instance().bus().register(new PlayerRenderTickEvent());
    }

    public void addInGameGUIs() {
        //MinecraftForge.EVENT_BUS.register(new GuiChakraBar(Minecraft.getMinecraft()));

        MinecraftForge.EVENT_BUS.register(new GuiChakraAndStaminaBar(Minecraft.getMinecraft()));

        MinecraftForge.EVENT_BUS.register(new GuiNotificationUpdate(Minecraft.getMinecraft()));
    }

    public void registerCustomItems() {
        MinecraftForgeClient.registerItemRenderer(NarutoItems.SAMEHADA, new RenderItemSamehada());

        MinecraftForgeClient.registerItemRenderer(NarutoItems.SUMMON_SCROLL, new RenderItemScroll());

        MinecraftForgeClient.registerItemRenderer(NarutoItems.kubikiribocho, new RenderItemKubikiribocho());

        MinecraftForgeClient.registerItemRenderer(NarutoItems.Kunai, new RenderItemWeaponSmall());
        MinecraftForgeClient.registerItemRenderer(NarutoItems.ExplosiveKunai, new RenderItemWeaponSmall());
    }

    public void registerCustomBlocks() {
        ClientRegistry.registerTileEntity(TileEntityBonzaiTree.class, "bonzai_tree", new TileEntityBonsaiRenderer(new ResourceLocation("narutomod:textures/blocks/bonsaitreeTexture.png")));

        ClientRegistry.registerTileEntity(TileEntityBonzaiSakuraTree.class, "bonzai_sakura_tree", new TileEntityBonsaiRenderer(new ResourceLocation("narutomod:textures/blocks/bonsaitreeSakuraTexture.png")));

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(NarutoBlocks.bonsaiTree), new ItemRendererBonsaiTree(new ResourceLocation("narutomod:textures/blocks/bonsaitreeTexture.png")));

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(NarutoBlocks.bonsaiSakuraTree), new ItemRendererBonsaiTree(new ResourceLocation("narutomod:textures/blocks/bonsaitreeSakuraTexture.png")));

    }

    public void addModderCapes() {
        // Create a cape with your logo on for all stuff ;3 However it may be best to leave it out.
        //String capeURL = "http://i.imgur.com/sZ6wSBh.png"; // change!
        //String[] devs = {"SEKWAH41", "Orcwaagh"};

        //ThreadDownloadImageData image = new ThreadDownloadImageData(capeURL, null, null);

        //for (String username : devs) {
        //	Minecraft.getMinecraft().renderEngine.loadTexture(new ResourceLocation("cloaks/" + username), (TextureObject) image);
        //}
    }

    public void startUsageReport() {

        NarutoMod.usageReport = new UsageReport(true);
        NarutoMod.usageReport.startUsageReport();
    }

    public void addItems() {
        super.addItems();

        ((ItemNinjaArmour) NarutoItems.flakJacket).setModelArmor(new ModelFlakJacket());
        ((ItemNinjaArmour) NarutoItems.JONIN_ARMOUR).setModelArmor(new ModelUpdateJoninArmor());
        ((ItemNinjaArmour) NarutoItems.NARUTO_SHIPPUDEN_ARMOUR).setModelArmor(new ModelNarutoShippudenArmor());
        ((ItemNinjaArmour) NarutoItems.BORUTO_KID_ARMOUR).setModelArmor(new ModelBorutoKidArmor());
        ((ItemNinjaArmour) NarutoItems.BORUTO_KAKASHI_ARMOUR).setModelArmor(new ModelBorutoKakashiArmor());
        ((ItemNinjaArmour) NarutoItems.BORUTO_KID_MOVIE_ARMOUR).setModelArmor(new ModelBorutoMovieKidArmor());
        ((ItemNinjaArmour) NarutoItems.ANBU_ARMOUR).setModelArmor(new ModelAnbuArmor());
        ((ItemNinjaArmour) NarutoItems.SASUKE_KID_ARMOUR).setModelArmor(new ModelSasukeKidArmour());
        ((ItemNinjaArmour) NarutoItems.KID_NARUTO_ARMOUR).setModelArmor(new ModelNarutoKidArmor());
        ((ItemNinjaArmour) NarutoItems.HAN_HAT).setModelArmor(new ModelHanHat());
    }

    public void commands() {
        ClientCommandHandler.instance.registerCommand(new CommandReloadPoses());
    }

}
