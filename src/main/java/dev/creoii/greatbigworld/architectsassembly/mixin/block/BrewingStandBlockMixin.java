package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.BrewingStandBlock;
import net.minecraft.item.ItemPlacementContext;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BrewingStandBlock.class)
public abstract class BrewingStandBlockMixin extends BlockWithEntity {
    protected BrewingStandBlockMixin(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = getDefaultState();
        if (!state.getProperties().contains(Fluidloggable.FLUIDLOGGED))
            return super.getPlacementState(ctx);

        return state.with(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid()));
    }
}
