package com.sekwah.narutomod.item.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.sekwah.narutomod.entity.projectile.KunaiEntity;
import com.sekwah.narutomod.entity.projectile.SenbonEntity;
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

public class SenbonItem extends Item {

    protected Multimap<Attribute, AttributeModifier> weaponAttributes;

    public SenbonItem(Properties properties) {
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 1D, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", -1.8F, AttributeModifier.Operation.ADDITION));
        this.weaponAttributes = builder.build();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack usedItem = playerIn.getHeldItem(handIn);

        worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F);
        if (!worldIn.isRemote) {
            AbstractArrowEntity kunaiEntity = createShootingEntity(worldIn, playerIn);

            kunaiEntity.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.0F, 1.0F);
            kunaiEntity.setDamage(0.5);

            worldIn.addEntity(kunaiEntity);
        }


        if (!playerIn.abilities.isCreativeMode) {
            usedItem.shrink(1);
        }

        return ActionResult.func_233538_a_(usedItem, worldIn.isRemote);

    }

    public AbstractArrowEntity createShootingEntity(World worldIn, PlayerEntity playerIn) {

        SenbonEntity entity = new SenbonEntity(worldIn, playerIn);

        entity.pickupStatus = playerIn.abilities.isCreativeMode ?
                AbstractArrowEntity.PickupStatus.CREATIVE_ONLY: AbstractArrowEntity.PickupStatus.ALLOWED;

        return entity;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
        return equipmentSlot == EquipmentSlotType.MAINHAND ? weaponAttributes : super.getAttributeModifiers(equipmentSlot, stack);
    }

}