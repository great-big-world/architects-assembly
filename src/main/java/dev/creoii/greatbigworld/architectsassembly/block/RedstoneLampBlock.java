package dev.creoii.greatbigworld.architectsassembly.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RedstoneLampBlock extends Block {
    public static final MapCodec<RedstoneLampBlock> CODEC = createCodec(RedstoneLampBlock::new);
    public static final IntProperty LIGHT = IntProperty.of("light", 0, 15);

    public MapCodec<RedstoneLampBlock> getCodec() {
        return CODEC;
    }

    public RedstoneLampBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(LIGHT, 0));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();

        if (world.isReceivingRedstonePower(ctx.getBlockPos())) {
            return getDefaultState().with(LIGHT, world.getReceivedRedstonePower(ctx.getBlockPos()));
        }
        return super.getPlacementState(ctx);
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            if (!world.isReceivingRedstonePower(pos)) {
                world.scheduleBlockTick(pos, this, 4);
            } else {
                int power = world.getReceivedRedstonePower(pos);
                if (state.get(LIGHT) != power) {
                    world.setBlockState(pos, state.with(LIGHT, power), 2);
                }
            }
        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LIGHT) != 0 && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.with(LIGHT, 0), 2);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIGHT);
    }
}
