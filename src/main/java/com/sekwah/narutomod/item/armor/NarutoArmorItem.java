package com.sekwah.narutomod.item.armor;

import com.sekwah.narutomod.NarutoMod;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class NarutoArmorItem extends ArmorItem {

    private BipedModel armorModel = null;
    private String armorTexture = null;

    public NarutoArmorItem(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
    }

    public BipedModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, BipedModel _default) {
        return armorModel;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return armorTexture;
    }

    public NarutoArmorItem setArmorModel(BipedModel armorModel) {
        this.armorModel = armorModel;
        return this;
    }

    public NarutoArmorItem setArmorTexture(String armorTexture) {
        this.armorTexture = NarutoMod.MOD_ID + ":" + armorTexture;
        return this;
    }
}
