package dev.creoii.greatbigworld.architectsassembly.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.recipebook.RecipeResultCollection;

@Environment(EnvType.CLIENT)
public interface RecipeResultCollectionView {
    void gbw$setResults(RecipeResultCollection results);
}
