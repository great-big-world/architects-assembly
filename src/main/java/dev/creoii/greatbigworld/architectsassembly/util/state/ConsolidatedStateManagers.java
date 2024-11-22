package dev.creoii.greatbigworld.architectsassembly.util.state;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.block.WallBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ConsolidatedStateManagers {
    public static ConsolidatedBlockStateManager WALL_MANAGER = createWallManager();

    private static ConsolidatedBlockStateManager createWallManager() {
        WallBlock templateWall = new WallBlock(AbstractBlock.Settings.copyShallow(Blocks.STONE).solid(), true);
        Registry.register(Registries.BLOCK, Identifier.of(GreatBigWorld.NAMESPACE, "template_wall"), templateWall);
        return new ConsolidatedBlockStateManager(WallBlock.class, templateWall.getStateManager());
    }
}
