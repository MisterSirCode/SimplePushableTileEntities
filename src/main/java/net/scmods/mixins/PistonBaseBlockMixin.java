package net.scmods.mixins;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;
import net.minecraft.block.PistonBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonBlock.class)
public class PistonBaseBlockMixin {
    @Inject(method = "isMovable",
    at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/block/BlockState;getHardness(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)F"), cancellable = true)
    private static void modifyMovable(BlockState state, World world, BlockPos pos, Direction direction, boolean canBreak, Direction pistonDir, CallbackInfoReturnable<Boolean> cir) {
        if (state.isOf(Blocks.CHEST) 
        || state.isOf(Blocks.DISPENSER) 
        || state.isOf(Blocks.DROPPER)
        || state.isOf(Blocks.TRAPPED_CHEST)
        || state.isOf(Blocks.FURNACE)
        || state.isOf(Blocks.BLAST_FURNACE)
        || state.isOf(Blocks.SMOKER)) {
            cir.setReturnValue(true);
        }
    }
}
