package sekwah.mods.narutomod.client;

public class EventHook {

    /**@ForgeSubscribe
     @SideOnly(Side.CLIENT)
     public void onLivingJumpEvent(LivingJumpEvent event)
     {
     if (event.entity instanceof EntityPlayer)
     {
     if (PlayerClientTickHandler.chakraDash)
     {
     event.entity.motionY += 0.2D;
     PlayerClientTickHandler.chakra -= 0.04F;

     ParticleEffects.addEffect(3, (EntityClientPlayerMP) event.entity);
     }
     }
     }*/


    // The tick event is in its own class
    /**@SubscribeEvent public void tick(ClientTickEvent event) {
    PlayerClientTickHandeler.firePlayerTick();
    //TickRegistry.registerTickHandler(new PlayerCommonTickHandler(), Side.SERVER);
    }*/

    /**@ForgeSubscribe public void renderWorldLastEvent(RenderWorldLastEvent evt)
    {
    // for extra render effects
    }*/

    /**@ForgeSubscribe
     @SideOnly(Side.CLIENT)
     public void entityAttacked(LivingAttackEvent event)
     {
     EntityLiving attackedEnt = event.entityLiving;
     DamageSource attackSource = event.source;
     }*/

}
