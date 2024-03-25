package dev.creoii.greatbigworld.architectsassembly.block.enums;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.StringIdentifiable;

import java.util.function.Supplier;

public enum FluidType implements StringIdentifiable {
    EMPTY("empty", Fluids.EMPTY, () -> ItemStack.EMPTY),
    WATER("water", Fluids.WATER, () -> Items.WATER_BUCKET.getDefaultStack()),
    LAVA("lava", Fluids.LAVA, () -> Items.LAVA_BUCKET.getDefaultStack());

    private final String name;
    private final Fluid fluid;
    private final Supplier<ItemStack> bucket;

    FluidType(String name, Fluid fluid, Supplier<ItemStack> bucket) {
        this.name = name;
        this.fluid = fluid;
        this.bucket = bucket;
    }

    @Override
    public String asString() {
        return name;
    }

    public Fluid getFluid() {
        return fluid;
    }

    public Supplier<ItemStack> getBucket() {
        return bucket;
    }
}
