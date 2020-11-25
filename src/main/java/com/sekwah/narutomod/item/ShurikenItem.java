package com.sekwah.narutomod.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.sekwah.narutomod.entity.projectile.KunaiEntity;
import com.sekwah.narutomod.entity.projectile.ShurikenEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
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
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack usedItem = playerIn.getHeldItem(handIn);

        worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F);
        if (!worldIn.isRemote) {
            AbstractArrowEntity kunaiEntity = createShootingEntity(worldIn, playerIn);

            kunaiEntity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
            kunaiEntity.setDamage(2.5);

            worldIn.addEntity(kunaiEntity);
        }


        if (!playerIn.abilities.isCreativeMode) {
            usedItem.shrink(1);
        }

        return ActionResult.func_233538_a_(usedItem, worldIn.isRemote);

    }

    public AbstractArrowEntity createShootingEntity(World worldIn, PlayerEntity playerIn) {

        ShurikenEntity entity = new ShurikenEntity(worldIn, playerIn);

        entity.pickupStatus = playerIn.abilities.isCreativeMode ?
                AbstractArrowEntity.PickupStatus.CREATIVE_ONLY: AbstractArrowEntity.PickupStatus.ALLOWED;

        return entity;
    }

}
