package dev.creoii.greatbigworld.architectsassembly.mixin.block;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.WallBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(WallBlock.class)
public abstract class WallBlockMixin extends Block {
    public WallBlockMixin(Settings settings) {
        super(settings);
    }

    /*@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;with(Lnet/minecraft/state/property/Property;Ljava/lang/Comparable;)Ljava/lang/Object;"))
    private Object gbw$cancelSetDefaultState(BlockState instance, Property property, Comparable comparable) {
        return instance.withIfExists(property, comparable);
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/WallBlock;getShapeMap(FFFFFF)Ljava/util/Map;"))
    private Map<BlockState, VoxelShape> gbw$cancelCreateShapeMaps(WallBlock instance, float f, float g, float h, float i, float j, float k) {
        return null;
    }*/

    @Inject(method = "getShapeMap", at = @At("HEAD"), cancellable = true)
    private void cancelShapeMap(float f, float g, float h, float i, float j, float k, CallbackInfoReturnable<Map<BlockState, VoxelShape>> cir) {
        cir.setReturnValue(Maps.newHashMap());
    }

    @Inject(method = "getOutlineShape", at = @At("HEAD"), cancellable = true)
    private void cancelOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        cir.setReturnValue(VoxelShapes.fullCube());
    }

    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void cancelCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        cir.setReturnValue(VoxelShapes.fullCube());
    }

    @Inject(method = "getPlacementState", at = @At("HEAD"), cancellable = true)
    private void cancelPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        cir.setReturnValue(getDefaultState());
    }

    @Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"), cancellable = true)
    private void cancelStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        cir.setReturnValue(getDefaultState());
    }

    @Inject(method = "getFluidState", at = @At("HEAD"), cancellable = true)
    private void cancelFluidState(BlockState state, CallbackInfoReturnable<FluidState> cir) {
        cir.setReturnValue(Fluids.EMPTY.getDefaultState());
    }

    @Inject(method = "isTransparent", at = @At("HEAD"), cancellable = true)
    private void cancelTransparent(BlockState state, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    /*@Inject(method = "appendProperties", at = @At("HEAD"), cancellable = true)
    private void cancelAppendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        ci.cancel();
    }*/

    @Inject(method = "rotate", at = @At("HEAD"), cancellable = true)
    private void cancelRotate(BlockState state, BlockRotation rotation, CallbackInfoReturnable<BlockState> cir) {
        cir.setReturnValue(getDefaultState());
    }

    @Inject(method = "mirror", at = @At("HEAD"), cancellable = true)
    private void cancelMirror(BlockState state, BlockMirror mirror, CallbackInfoReturnable<BlockState> cir) {
        cir.setReturnValue(getDefaultState());
    }
}
