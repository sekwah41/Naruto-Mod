package sekwah.mods.narutomod.common.entity.ai;

import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import sekwah.mods.narutomod.common.entity.EntityNinjaVillager;

public class EntityAILookAtNinjaTradePlayer extends EntityAIWatchClosest {
    private final EntityNinjaVillager theMerchant;

    public EntityAILookAtNinjaTradePlayer(EntityNinjaVillager par1EntityNinjaVillager) {
        super(par1EntityNinjaVillager, EntityPlayer.class, 8.0F);
        this.theMerchant = par1EntityNinjaVillager;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (this.theMerchant.isTrading()) {
            this.closestEntity = this.theMerchant.getCustomer();
            return true;
        } else {
            return false;
        }
    }
}
