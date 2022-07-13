package com.sekwah.narutomod.item.armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

/**
 * Balance against {@link net.minecraft.world.item.ArmorMaterial}
 */
public enum NarutoArmorMaterial implements ArmorMaterial {

    ANBU_MAT("anbu", 35, new int[]{2, 5, 7, 2}, 8, null, 2.1F, 0.0F, () -> null),
    FLAK_MAT("flak", 35, new int[]{2, 5, 6, 2}, 8, null, 2.1F, 0.0F, () -> null),
    FLAK_STRONGER_MAT("flak_strong", 35, new int[]{2, 7, 7, 2}, 8, null, 2.1F, 0.0F, () -> null),
    CHARACTER_CLOTHES("character_clothes", 35, new int[]{2, 5, 7, 2}, 8, null, 2.1F, 0.0F, () -> null),
    FULL_CHARACTER_CLOTHES("full_character_clothes", 35, new int[]{2, 5, 10, 2}, 6, null, 2.1F, 0.0F, () -> null),
    HEADBAND("headband", 35, new int[]{2, 5, 5, 2}, 6, null, 2.1F, 0.0F, () -> null),
    ;

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    /**
     * May be Deprecated but is used in the vanilla materials
     */
    private final LazyLoadedValue<Ingredient> repairIngredient;

    NarutoArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlot p_200896_1_) {
        return HEALTH_PER_SLOT[p_200896_1_.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot p_200902_1_) {
        return this.slotProtections[p_200902_1_.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
