package com.sekwah.narutomod.item.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.sekwah.narutomod.entity.projectile.KunaiEntity;
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

public class KunaiItem extends Item {

    protected Multimap<Attribute, AttributeModifier> weaponAttributes;

    public KunaiItem(Properties properties) {
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 3.0D, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -1.8F, AttributeModifier.Operation.ADDITION));
        this.weaponAttributes = builder.build();
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack usedItem = playerIn.getItemInHand(handIn);

        worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F);
        if (!worldIn.isClientSide) {
            AbstractArrowEntity kunaiEntity = createShootingEntity(worldIn, playerIn);

            kunaiEntity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 3.0F, 1.0F);
            kunaiEntity.setBaseDamage(2);

            worldIn.addFreshEntity(kunaiEntity);
        }


        if (!playerIn.abilities.instabuild) {
            usedItem.shrink(1);
        }

        return ActionResult.sidedSuccess(usedItem, worldIn.isClientSide);

    }

    public AbstractArrowEntity createShootingEntity(World worldIn, PlayerEntity playerIn) {

        KunaiEntity entity = new KunaiEntity(worldIn, playerIn);

        entity.pickup = playerIn.abilities.instabuild ?
                AbstractArrowEntity.PickupStatus.CREATIVE_ONLY: AbstractArrowEntity.PickupStatus.ALLOWED;

        return entity;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
        return equipmentSlot == EquipmentSlotType.MAINHAND ? weaponAttributes : super.getAttributeModifiers(equipmentSlot, stack);
    }

}
