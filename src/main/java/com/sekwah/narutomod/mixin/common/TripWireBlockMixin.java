package com.sekwah.narutomod.mixin.common;

import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.block.weapons.PaperBombBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TripWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TripWireBlock.class)
public class TripWireBlockMixin {

    @Inject(method = "shouldConnectTo", at = @At("HEAD"), cancellable = true)
    public void shouldConnectTo(BlockState state, Direction direction, CallbackInfoReturnable<Boolean> callback) {
        boolean shouldCheck = callback.getReturnValueZ();
        Block block = state.getBlock();
        if(!shouldCheck && block == NarutoBlocks.PAPER_BOMB.get()) {
            callback.setReturnValue(true);
        }
    }

    @Inject(method = "updateSource", at = @At(value = "RETURN"))
    public void notifyHook(Level worldIn, BlockPos pos, BlockState state, CallbackInfo ci) {
        for(Direction direction : new Direction[]{Direction.SOUTH, Direction.WEST}) {
            mainCheckLoop: for(int i = 1; i < 42; ++i) {
                BlockPos blockpos = pos.relative(direction, i);
                BlockState blockstate = worldIn.getBlockState(blockpos);
                if (blockstate.is(NarutoBlocks.PAPER_BOMB.get()) && state.getValue(TripWireBlock.POWERED).equals(Boolean.TRUE)) {
                    for(int j = 1; j < 42; ++j) {
                        BlockPos checkOpposite = pos.relative(direction, -j);
                        BlockState blockStateOpposite = worldIn.getBlockState(checkOpposite);
                        if (blockStateOpposite.is(NarutoBlocks.PAPER_BOMB.get()) && state.getValue(TripWireBlock.POWERED).equals(Boolean.TRUE)) {
                            ((PaperBombBlock) NarutoBlocks.PAPER_BOMB.get()).spawnPaperbomb(blockstate, worldIn, blockpos, null, true);
                            worldIn.removeBlock(blockpos, false);

                            ((PaperBombBlock) NarutoBlocks.PAPER_BOMB.get()).spawnPaperbomb(blockStateOpposite, worldIn, checkOpposite, null, true);
                            worldIn.removeBlock(blockpos, false);
                        }
                        if (!blockStateOpposite.is((Block)(Object)this)) {
                            break mainCheckLoop;
                        }
                    }
                    break;
                }

                if (!blockstate.is((Block)(Object)this)) {
                    break;
                }
            }
        }
    }

}
