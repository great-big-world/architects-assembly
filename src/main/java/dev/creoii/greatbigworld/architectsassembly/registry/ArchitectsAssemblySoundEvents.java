package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public final class ArchitectsAssemblySoundEvents {
    public static final SoundEvent ITEM_PICKAXE_CRACK = SoundEvent.of(Identifier.of(GreatBigWorld.NAMESPACE, "item.pickaxe.crack"));
    public static final SoundEvent BLOCK_GLASS_REPAIR = SoundEvent.of(Identifier.of(GreatBigWorld.NAMESPACE, "block.glass.repair"));
    public static final SoundEvent UI_SAWMILL_TAKE_RESULT = SoundEvent.of(Identifier.of(GreatBigWorld.NAMESPACE, "ui.sawmill.take_result"));
    public static final SoundEvent UI_SAWMILL_SELECT_RECIPE = SoundEvent.of(Identifier.of(GreatBigWorld.NAMESPACE, "ui.sawmill.select_recipe"));

    public static void register() {
        Registry.register(Registries.SOUND_EVENT, Identifier.of(GreatBigWorld.NAMESPACE, "item.pickaxe.crack"), ITEM_PICKAXE_CRACK);
        Registry.register(Registries.SOUND_EVENT, Identifier.of(GreatBigWorld.NAMESPACE, "block.glass.repair"), BLOCK_GLASS_REPAIR);
        Registry.register(Registries.SOUND_EVENT, Identifier.of(GreatBigWorld.NAMESPACE, "ui.sawmill.take_result"), UI_SAWMILL_TAKE_RESULT);
        Registry.register(Registries.SOUND_EVENT, Identifier.of(GreatBigWorld.NAMESPACE, "ui.sawmill.select_recipe"), UI_SAWMILL_SELECT_RECIPE);
    }
}
