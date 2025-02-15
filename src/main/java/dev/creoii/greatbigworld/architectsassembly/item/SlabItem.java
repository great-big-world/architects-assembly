package dev.creoii.greatbigworld.architectsassembly.item;

import dev.creoii.greatbigworld.architectsassembly.block.VerticalSlabBlock;
import dev.creoii.greatbigworld.architectsassembly.registry.ArchitectsAssemblyBlocks;
import dev.creoii.greatbigworld.architectsassembly.util.SlabPlacer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SlabItem extends BlockItem {
    private final VerticalSlabBlock verticalSlab;

    public SlabItem(Block slab, Block verticalSlab, Settings settings) {
        super(slab, settings);
        if (verticalSlab instanceof VerticalSlabBlock)
            this.verticalSlab = (VerticalSlabBlock) verticalSlab;
        else this.verticalSlab = (VerticalSlabBlock) ArchitectsAssemblyBlocks.VERTICAL_OAK_SLAB;
    }

    @Override
    public void appendBlocks(Map<Block, Item> map, Item item) {
        super.appendBlocks(map, item);
        map.put(verticalSlab, item);
    }

    @Nullable
    @Override
    protected BlockState getPlacementState(ItemPlacementContext context) {
        if (context.getPlayer() == null || !((SlabPlacer) context.getPlayer()).gbw$getSlabPlacementState().equals(SlabPlacement.NORMAL))
            return super.getPlacementState(context);

        ItemPlacementContext context1 = new ItemPlacementContext(context.getPlayer(), context.getHand(), new ItemStack(verticalSlab), context.getHitResult());
        BlockState state = verticalSlab.getPlacementState(context1);
        return state != null && canPlace(context1, state) ? state : null;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (user.shouldCancelInteraction() && user instanceof SlabPlacer slabPlacer) {
            SlabPlacement currentState = slabPlacer.gbw$getSlabPlacementState();
            if (currentState == SlabPlacement.NORMAL) {
                slabPlacer.gbw$setSlabPlacementState(SlabPlacement.VERTICAL);
            } else if (currentState.equals(SlabPlacement.VERTICAL))
                slabPlacer.gbw$setSlabPlacementState(SlabPlacement.NORMAL); {
            }

            if (!world.isClient)
                ((ServerPlayerEntity) user).sendMessageToClient(Text.translatable("gui.placement.switch_slab_placement", Text.translatable(currentState.getTranslationKey())), true);
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
