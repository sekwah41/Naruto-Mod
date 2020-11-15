package com.sekwah.narutomod.server;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.capabilities.NinjaDataProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID)
public class ServerEventHook {

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(NarutoMod.MOD_ID, "ninja_data"), new NinjaDataProvider());
        }
    }

}
