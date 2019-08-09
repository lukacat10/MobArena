package com.garbagemule.MobArena.things;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void markItemStack(){
        ItemMeta meta = stack.getItemMeta();
        List<String> lore = new ArrayList<>();
        //TODO: Name is volunerable to items being transferred between arenas
        //TODO: Configurable name
        lore.add("MobArena kit");
        meta.setLore(lore);
        stack.setItemMeta(meta);
    }
}
