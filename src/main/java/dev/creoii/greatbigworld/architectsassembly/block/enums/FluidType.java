package dev.creoii.greatbigworld.architectsassembly.block.enums;

import java.util.function.Supplier;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public enum FluidType implements StringRepresentable {
    EMPTY("empty", Fluids.EMPTY, () -> ItemStack.EMPTY),
    WATER("water", Fluids.WATER, () -> Items.WATER_BUCKET.getDefaultInstance()), // DO NOT USE METHOD REFERENCES HERE
    LAVA("lava", Fluids.LAVA, () -> Items.LAVA_BUCKET.getDefaultInstance());

    private final String name;
    private final Fluid fluid;
    private final Supplier<ItemStack> bucket;

    FluidType(String name, Fluid fluid, Supplier<ItemStack> bucket) {
        this.name = name;
        this.fluid = fluid;
        this.bucket = bucket;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public Fluid getFluid() {
        return fluid;
    }

    public Supplier<ItemStack> getBucket() {
        return bucket;
    }
}
