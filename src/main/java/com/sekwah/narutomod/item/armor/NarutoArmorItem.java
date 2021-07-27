package com.sekwah.narutomod.item.armor;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.item.interfaces.IShouldHideNameplate;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class NarutoArmorItem extends ArmorItem implements IShouldHideNameplate {

    private HumanoidModel armorModel = null;
    private String armorTexture = null;
    private boolean forceHideName = false;

    public NarutoArmorItem(ArmorMaterial p_i48534_1_, EquipmentSlot p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
    }

    public HumanoidModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel _default) {
        return armorModel;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return armorTexture;
    }

    public NarutoArmorItem setArmorModel(HumanoidModel<? extends LivingEntity> armorModel) {
        this.armorModel = armorModel;
        return this;
    }

    public NarutoArmorItem setShouldHideNameplate(boolean shouldHide) {
        this.forceHideName = shouldHide;
        return this;
    }

    public NarutoArmorItem setArmorTexture(String armorTexture) {
        this.armorTexture = NarutoMod.MOD_ID + ":" + armorTexture;
        return this;
    }

    @Override
    public boolean shouldHideNameplate(Entity entity) {
        return this.forceHideName;
    }
}
