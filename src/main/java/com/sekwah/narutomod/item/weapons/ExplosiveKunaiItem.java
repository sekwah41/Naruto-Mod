package com.sekwah.narutomod.item.weapons;

import com.sekwah.narutomod.entity.projectile.ExplosiveKunaiEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ExplosiveKunaiItem extends KunaiItem {

    public ExplosiveKunaiItem(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createShootingEntity(Level worldIn, Player playerIn) {
        return new ExplosiveKunaiEntity(worldIn, playerIn);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if (!(attacker instanceof PlayerEntity) || !((PlayerEntity) attacker).abilities.instabuild) {
            stack.shrink(1);
        }

        if(!attacker.level.isClientSide) {
            ExplosiveKunaiEntity.explodeKunai(attacker);
        }

        return false;
    }

    @Override
    public ActionResult<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ActionResult<ItemStack> action = super.use(worldIn, playerIn, handIn);

        return action;
    }

}
