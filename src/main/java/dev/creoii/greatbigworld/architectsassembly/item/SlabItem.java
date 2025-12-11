package dev.creoii.greatbigworld.architectsassembly.item;

import dev.creoii.greatbigworld.architectsassembly.block.VerticalSlabBlock;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlocks;
import dev.creoii.greatbigworld.architectsassembly.util.SlabPlacer;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SlabItem extends BlockItem {
    private final VerticalSlabBlock verticalSlab;

    public SlabItem(Block slab, Block verticalSlab, Properties settings) {
        super(slab, settings);
        if (verticalSlab instanceof VerticalSlabBlock)
            this.verticalSlab = (VerticalSlabBlock) verticalSlab;
        else this.verticalSlab = (VerticalSlabBlock) ArchitectsAssemblyBlocks.VERTICAL_OAK_SLAB;
    }

    @Override
    public void registerBlocks(Map<Block, Item> map, Item item) {
        super.registerBlocks(map, item);
        map.put(verticalSlab, item);
    }

    @Nullable
    @Override
    protected BlockState getPlacementState(BlockPlaceContext context) {
        if (context.getPlayer() == null || !((SlabPlacer) context.getPlayer()).gbw$getSlabPlacementState().equals(SlabPlacement.NORMAL))
            return super.getPlacementState(context);

        BlockPlaceContext context1 = new BlockPlaceContext(context.getPlayer(), context.getHand(), new ItemStack(verticalSlab), context.getHitResult());
        BlockState state = verticalSlab.getStateForPlacement(context1);
        return state != null && canPlace(context1, state) ? state : null;
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        if (user.isSecondaryUseActive() && user instanceof SlabPlacer slabPlacer) {
            SlabPlacement currentState = slabPlacer.gbw$getSlabPlacementState();
            if (currentState == SlabPlacement.NORMAL) {
                slabPlacer.gbw$setSlabPlacementState(SlabPlacement.VERTICAL);
            } else if (currentState.equals(SlabPlacement.VERTICAL))
                slabPlacer.gbw$setSlabPlacementState(SlabPlacement.NORMAL); {
            }

            if (!world.isClientSide())
                ((ServerPlayer) user).sendSystemMessage(Component.translatable("gui.placement.switch_slab_placement", Component.translatable(currentState.getTranslationKey())), true);
        }
        return super.use(world, user, hand);
    }

    public enum SlabPlacement {
        NORMAL("placement.slab.normal"),
        VERTICAL("placement.slab.vertical");

        private final String translationKey;

        SlabPlacement(String translationKey) {
            this.translationKey = translationKey;
        }

        public String getTranslationKey() {
            return translationKey;
        }
    }
}
