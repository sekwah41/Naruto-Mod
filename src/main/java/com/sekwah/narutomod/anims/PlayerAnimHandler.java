package com.sekwah.narutomod.anims;

import com.sekwah.narutomod.abilities.NarutoAbilities;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class PlayerAnimHandler {

    public static <T extends LivingEntity> void sprintingAnim(Entity entity, PlayerModel playerModel) {
        entity.getCapability(NinjaCapabilityHandler.NINJA_DATA).ifPresent((ninjaData -> {
            if(!ninjaData.isNinjaModeEnabled()) {
                return;
            }
            var channeledAbility = ninjaData.getCurrentlyChanneledAbility();
            if (channeledAbility != null && channeledAbility.equals(NarutoAbilities.CHAKRA_CHARGE.getId())) {
                playerModel.rightArm.setRotation(-1.375616F, -0.5948606F, 0F);
                playerModel.leftArm.setRotation(-1.375616F, 0.5948606F, 0F);
            }
            if(entity.isSprinting() && !entity.isVisuallySwimming()) {
                playerModel.rightArm.setRotation(1.412787F, 0F, 0F);
                playerModel.rightArm.setPos(-5F, 3.933333F, -3F - 2F);

                playerModel.leftArm.setRotation(1.412787F, 0F, 0F);
                playerModel.leftArm.setPos(5F, 3.266667F, -3F - 2F);

                playerModel.head.xRot = 0F;
                playerModel.head.setPos(0F, 3.133333F - 1F, -5F - 1F);

                playerModel.body.setRotation(0.5435722F, 0F, 0F);
                playerModel.body.setPos(0F, 3F - 1F, -3.5F - 2F);
            }
            else {
                playerModel.head.setPos(0F, 0F, 0F);
                playerModel.body.setPos(0F, 0F, 0F);
            }
        }));
    }
}
