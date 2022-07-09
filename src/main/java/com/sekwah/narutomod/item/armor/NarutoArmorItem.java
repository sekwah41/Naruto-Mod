package com.sekwah.narutomod.item.armor;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.client.renderer.NarutoRenderEvents;
import com.sekwah.narutomod.item.interfaces.IShouldHideNameplate;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class NarutoArmorItem extends ArmorItem implements IShouldHideNameplate {

    private HumanoidModel armorModel = null;
    private String armorTexture = null;
    private boolean forceHideName = false;

    public NarutoArmorItem(ArmorMaterial p_i48534_1_, EquipmentSlot p_i48534_2_, Properties p_i48534_3_) {
        super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
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


    // A little confusing but this is how it works now
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer()
            {
                return NarutoRenderEvents.NARUTO_RENDERER;
            }

            @Override
            public HumanoidModel getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel _default) {
                return armorModel;
            }
        });
    }
}
