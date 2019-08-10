package com.garbagemule.MobArena.things;

import com.garbagemule.MobArena.util.ItemMarker;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackThing implements Thing {
    private ItemStack stack;

    public ItemStackThing(ItemStack stack) {
        this.stack = stack;
    }

    @Override
    public boolean giveTo(Player player) {
        return player.getInventory().addItem(stack.clone()).isEmpty();
    }

    @Override
    public boolean takeFrom(Player player) {
        return player.getInventory().removeItem(stack.clone()).isEmpty();
    }

    @Override
    public boolean heldBy(Player player) {
        return player.getInventory().containsAtLeast(stack, stack.getAmount());
    }

    ItemStack getItemStack() {
        return stack;
    }

    @Override
    public String toString() {
        String name = getName();
        int amount = stack.getAmount();
        if (amount > 1) {
            return amount + "x " + name;
        }
        return name;
    }

    private String getName() {
        ItemMeta meta = stack.getItemMeta();
        if (meta.hasDisplayName()) {
            return meta.getDisplayName();
        }
        return stack.getType()
            .name()
            .replace("_", " ")
            .toLowerCase();
    }

    /**
     * Marks the
     * TODO: Operations are done twice in the called methods, make it more efficient (aka by creating a class called
     * "MarkedItem" containing the meta and the lore
     */
    public void markItemStack(){
        if(ItemMarker.checkForMark(stack))
            return;
        ItemMarker.addMark(stack);
    }
}
