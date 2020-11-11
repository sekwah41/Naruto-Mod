package com.sekwah.narutomod.item;

import com.sekwah.narutomod.entity.projectile.KunaiEntity;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class KunaiItem extends Item {

    public KunaiItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack usedItem = playerIn.getHeldItem(handIn);

        // TODO add throwing sound

        // TODO add entity spawning

        if (!worldIn.isRemote) {
            KunaiEntity arrowEntity = new KunaiEntity(worldIn, playerIn);

            arrowEntity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
            if(playerIn.abilities.isCreativeMode) {

            }
            arrowEntity.pickupStatus = playerIn.abilities.isCreativeMode ?
                    AbstractArrowEntity.PickupStatus.CREATIVE_ONLY: AbstractArrowEntity.PickupStatus.ALLOWED;


            worldIn.addEntity(arrowEntity);
        }


        if (!playerIn.abilities.isCreativeMode) {
            usedItem.shrink(1);
        }

        return ActionResult.func_233538_a_(usedItem, worldIn.isRemote);

    }

}
