package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import dev.creoii.greatbigworld.architectsassembly.util.Fluidloggable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BrewingStandBlock.class)
public abstract class BrewingStandBlockMixin extends BaseEntityBlock {
    protected BrewingStandBlockMixin(Properties settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState state = defaultBlockState();
        if (!state.getProperties().contains(Fluidloggable.FLUIDLOGGED))
            return super.getStateForPlacement(ctx);

        return state.setValue(Fluidloggable.FLUIDLOGGED, Fluidloggable.FLUIDS.get(ctx.getLevel().getFluidState(ctx.getClickedPos()).getType()));
    }
}
