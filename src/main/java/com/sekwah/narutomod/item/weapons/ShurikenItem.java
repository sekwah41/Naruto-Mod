package com.sekwah.narutomod.item.weapons;

import com.google.common.collect.Multimap;
import com.sekwah.narutomod.entity.projectile.ShurikenEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ShurikenItem extends Item {

    protected Multimap<Attribute, AttributeModifier> weaponAttributes;

    public ShurikenItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(Level worldIn, PlayerEntity playerIn, InteractionHand handIn) {
        ItemStack usedItem = playerIn.getItemInHand(handIn);

        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F);
        if (!worldIn.isClientSide) {
            AbstractArrow kunaiEntity = createShootingEntity(worldIn, playerIn);

            kunaiEntity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 3.0F, 1.0F);
            kunaiEntity.setBaseDamage(2.5);

            worldIn.addFreshEntity(kunaiEntity);
        }


        if (!playerIn.abilities.instabuild) {
            usedItem.shrink(1);
        }

        return ActionResult.sidedSuccess(usedItem, worldIn.isClientSide);

    }

    public AbstractArrow createShootingEntity(Level worldIn, PlayerEntity playerIn) {

        ShurikenEntity entity = new ShurikenEntity(worldIn, playerIn);

        entity.pickup = playerIn.abilities.instabuild ?
                AbstractArrowEntity.PickupStatus.CREATIVE_ONLY: AbstractArrowEntity.PickupStatus.ALLOWED;

        return entity;
    }

}
