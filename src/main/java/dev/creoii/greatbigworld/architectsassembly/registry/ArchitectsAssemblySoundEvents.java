package dev.creoii.greatbigworld.architectsassembly.registry;

import dev.creoii.greatbigworld.GreatBigWorld;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public final class ArchitectsAssemblySoundEvents {
    public static final SoundEvent ITEM_PICKAXE_CRACK = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "item.pickaxe.crack"));
    public static final SoundEvent BLOCK_GLASS_REPAIR = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "block.glass.repair"));
    public static final SoundEvent UI_SAWMILL_TAKE_RESULT = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "ui.sawmill.take_result"));
    public static final SoundEvent UI_SAWMILL_SELECT_RECIPE = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "ui.sawmill.select_recipe"));

    public static void register() {
        Registry.register(BuiltInRegistries.SOUND_EVENT, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "item.pickaxe.crack"), ITEM_PICKAXE_CRACK);
        Registry.register(BuiltInRegistries.SOUND_EVENT, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "block.glass.repair"), BLOCK_GLASS_REPAIR);
        Registry.register(BuiltInRegistries.SOUND_EVENT, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "ui.sawmill.take_result"), UI_SAWMILL_TAKE_RESULT);
        Registry.register(BuiltInRegistries.SOUND_EVENT, Identifier.fromNamespaceAndPath(GreatBigWorld.NAMESPACE, "ui.sawmill.select_recipe"), UI_SAWMILL_SELECT_RECIPE);
    }
}
