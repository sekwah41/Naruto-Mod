package com.sekwah.narutomod.item;

import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class KunaiItem extends Item {

    public KunaiItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack usedItem = playerIn.getHeldItem(handIn);
        // TODO add throwing sound

        // TODO add entity spawning

        if (!playerIn.abilities.isCreativeMode) {
            usedItem.shrink(1);
        }

        return ActionResult.func_233538_a_(usedItem, worldIn.isRemote);

    }

}
