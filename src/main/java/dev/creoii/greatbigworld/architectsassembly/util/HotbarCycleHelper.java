package dev.creoii.greatbigworld.architectsassembly.util;

import dev.creoii.greatbigworld.architectsassembly.ArchitectsAssembly;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.collection.DefaultedList;

public final class HotbarCycleHelper {
    public static void cycleHotbar(PlayerEntity player, boolean up) {
        if (player == null)
            return;

        PlayerInventory inventory = player.getInventory();
        DefaultedList<ItemStack> temp = DefaultedList.ofSize(PlayerInventory.MAIN_SIZE, ItemStack.EMPTY);
        for (int i = 0; i < 9; ++i) {
            if (up) {
                temp.set(i, inventory.getStack(cycle(i, 9)));
                temp.set(cycle(i, 9), inventory.getStack(cycle(i, 18)));
                temp.set(cycle(i, 18), inventory.getStack(cycle(i, 27)));
                temp.set(cycle(i, 27), inventory.getStack(i));
            } else {
                temp.set(i, inventory.getStack(cycle(i, -9)));
                temp.set(cycle(i, -9), inventory.getStack(cycle(i, -18)));
                temp.set(cycle(i, -18), inventory.getStack(cycle(i, -27)));
                temp.set(cycle(i, -27), inventory.getStack(i));
            }
        }

        PacketByteBuf buf = PacketByteBufs.create();
        for (int i = 0; i < PlayerInventory.MAIN_SIZE; ++i) {
            ItemStack stack = temp.get(i);
            inventory.main.set(i, stack);
            buf.writeInt(i);
            buf.writeItemStack(stack);
        }
        ClientPlayNetworking.send(ArchitectsAssembly.SYNC_INVENTORY_PACKET_ID, buf);
    }

    private static int cycle(int index, int amount) {
        int cycled = index + amount;

        // -9 returns 27
        if (cycled < 0) {
            return PlayerInventory.MAIN_SIZE + cycled;
        }

        // 40 returns 4
        if (cycled > PlayerInventory.MAIN_SIZE) {
            return -PlayerInventory.MAIN_SIZE + cycled;
        }

        return cycled;
    }
}
