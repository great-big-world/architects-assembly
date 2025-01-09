package dev.creoii.greatbigworld.architectsassembly.util.state;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.block.TemplateWallBlock;
import dev.creoii.greatbigworld.architectsassembly.block.WallBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ConsolidatedStateManagers {
    private final ConsolidatedBlockStateManager wallManager;

    public ConsolidatedStateManagers() {
        wallManager = new ConsolidatedBlockStateManager(WallBlock.class, () -> {
            Block templateWall = new TemplateWallBlock(AbstractBlock.Settings.create().solid());
            return Registry.register(Registries.BLOCK, Identifier.of(GreatBigWorld.NAMESPACE, "template_wall"), templateWall);
        });
    }

    public ConsolidatedBlockStateManager getWallManager() {
        return wallManager;
    }
}
