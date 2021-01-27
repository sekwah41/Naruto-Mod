package com.sekwah.narutomod.item.weapons;

import com.sekwah.narutomod.entity.projectile.ExplosiveKunaiEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ExplosiveKunaiItem extends KunaiItem {

    public ExplosiveKunaiItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrowEntity createShootingEntity(World worldIn, PlayerEntity playerIn) {
        return new ExplosiveKunaiEntity(worldIn, playerIn);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (!(attacker instanceof PlayerEntity) || !((PlayerEntity) attacker).abilities.isCreativeMode) {
            stack.shrink(1);
        }

        if(!attacker.world.isRemote) {
            ExplosiveKunaiEntity.explodeKunai(attacker);
        }

        return false;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ActionResult<ItemStack> action = super.onItemRightClick(worldIn, playerIn, handIn);

        return action;
    }

}
