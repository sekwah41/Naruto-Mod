package com.sekwah.narutomod.mixin;

import com.sekwah.narutomod.block.NarutoBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TripWireBlock;
import net.minecraft.util.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TripWireBlock.class)
public class TripWireBlockMixin {

    @Inject(method = "shouldConnectTo", at = @At("RETURN"), cancellable = true)
    public void shouldConnectTo(BlockState state, Direction direction, CallbackInfoReturnable<Boolean> callback) {
        boolean shouldCheck = callback.getReturnValue();
        Block block = state.getBlock();
        if(!shouldCheck && block == NarutoBlocks.PAPER_BOMB.get()) {
            callback.setReturnValue(true);
        }
    }

}
