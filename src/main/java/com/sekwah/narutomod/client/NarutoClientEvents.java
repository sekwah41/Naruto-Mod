package com.sekwah.narutomod.client;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.item.interfaces.IShouldHideNameplate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderNameplateEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class NarutoClientEvents {

    /**
     * {@link net.minecraftforge.client.event.RenderPlayerEvent}
     * @param event
     */
    @SubscribeEvent
    public static void playerRenderEvent(RenderPlayerEvent.Pre event) {
        //event.setCanceled(true);
    }

    @SubscribeEvent
    public static void renderNameplateEvent(RenderNameplateEvent event) {
        if(event.getResult() != Event.Result.DENY) {
            Entity entity = event.getEntity();
            if(entity instanceof PlayerEntity) {
                ItemStack itemStack = ((PlayerEntity) entity).getItemBySlot(EquipmentSlotType.HEAD);
                Item item = itemStack.getItem();
                if(item instanceof IShouldHideNameplate) {
                    if(((IShouldHideNameplate) item).shouldHideNameplate(entity)) {
                        event.setResult(Event.Result.DENY);
                    }
                }
            }
        }
    }

}
