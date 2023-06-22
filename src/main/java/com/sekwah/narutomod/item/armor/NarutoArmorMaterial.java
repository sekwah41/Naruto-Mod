package com.sekwah.narutomod.item.armor;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

/**
 * Balance against {@link net.minecraft.world.item.ArmorMaterial}
 */
public enum NarutoArmorMaterial implements ArmorMaterial {

    ANBU_MAT("anbu", 35, Util.make(new EnumMap<>(ArmorItem.Type.class), (type) -> {
        type.put(ArmorItem.Type.BOOTS, 2);
        type.put(ArmorItem.Type.LEGGINGS, 5);
        type.put(ArmorItem.Type.CHESTPLATE, 7);
        type.put(ArmorItem.Type.HELMET, 2);
    }), 8, SoundEvents.ARMOR_EQUIP_IRON, 2.1F, 0.0F, () -> {
        return null;//Ingredient.of(Items.LEATHER);
    }),
    FLAK_MAT("flak", 35, Util.make(new EnumMap<>(ArmorItem.Type.class), (type) -> {
        type.put(ArmorItem.Type.BOOTS, 2);
        type.put(ArmorItem.Type.LEGGINGS, 5);
        type.put(ArmorItem.Type.CHESTPLATE, 6);
        type.put(ArmorItem.Type.HELMET, 2);
    }), 8, SoundEvents.ARMOR_EQUIP_IRON, 2.1F, 0.0F, () -> {
        return null;//Ingredient.of(Items.LEATHER);
    }),
    FLAK_STRONGER_MAT("flak_strong", 35, Util.make(new EnumMap<>(ArmorItem.Type.class), (type) -> {
        type.put(ArmorItem.Type.BOOTS, 2);
        type.put(ArmorItem.Type.LEGGINGS, 7);
        type.put(ArmorItem.Type.CHESTPLATE, 7);
        type.put(ArmorItem.Type.HELMET, 2);
    }), 8, SoundEvents.ARMOR_EQUIP_IRON, 2.1F, 0.0F, () -> {
        return null;//Ingredient.of(Items.LEATHER);
    }),
    CHARACTER_CLOTHES("character_clothes", 35, Util.make(new EnumMap<>(ArmorItem.Type.class), (type) -> {
        type.put(ArmorItem.Type.BOOTS, 2);
        type.put(ArmorItem.Type.LEGGINGS, 5);
        type.put(ArmorItem.Type.CHESTPLATE, 7);
        type.put(ArmorItem.Type.HELMET, 2);
    }), 8, SoundEvents.ARMOR_EQUIP_LEATHER, 2.1F, 0.0F, () -> {
        return null;//Ingredient.of(Items.LEATHER);
    }),
    FULL_CHARACTER_CLOTHES("full_character_clothes", 35, Util.make(new EnumMap<>(ArmorItem.Type.class), (type) -> {
        type.put(ArmorItem.Type.BOOTS, 2);
        type.put(ArmorItem.Type.LEGGINGS, 5);
        type.put(ArmorItem.Type.CHESTPLATE, 10);
        type.put(ArmorItem.Type.HELMET, 2);
    }), 6, SoundEvents.ARMOR_EQUIP_LEATHER, 2.1F, 0.0F, () -> {
        return null;//Ingredient.of(Items.LEATHER);
    }),
    HEADBAND("headband", 35, Util.make(new EnumMap<>(ArmorItem.Type.class), (type) -> {
        type.put(ArmorItem.Type.BOOTS, 2);
        type.put(ArmorItem.Type.LEGGINGS, 5);
        type.put(ArmorItem.Type.CHESTPLATE, 5);
        type.put(ArmorItem.Type.HELMET, 2);
    }), 6, SoundEvents.ARMOR_EQUIP_LEATHER, 2.1F, 0.0F, () -> {
        return null;//Ingredient.of(Items.LEATHER);
    }),
    ;

    public static final StringRepresentable.EnumCodec<ArmorMaterials> CODEC = StringRepresentable.fromEnum(ArmorMaterials::values);
    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private NarutoArmorMaterial(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> protectionFunctionForType, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionFunctionForType = protectionFunctionForType;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getDurabilityForType(ArmorItem.Type p_266745_) {
        return HEALTH_FUNCTION_FOR_TYPE.get(p_266745_) * this.durabilityMultiplier;
    }

    public int getDefenseForType(ArmorItem.Type type) {
        return this.protectionFunctionForType.get(type);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public String getSerializedName() {
        return this.name;
    }
}
