package com.garbagemule.MobArena.util;

import com.garbagemule.MobArena.RewardManager;
import com.garbagemule.MobArena.things.ItemStackThing;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemMarker {
    public static final String MARK = "MobArena Kitttt";

    public static boolean checkForMark(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        if(lore == null)
            return false;
        for (String line:
                lore) {
            if(line == null)
                continue;
            if(line.equals(MARK))
                return true;
        }
        return false;
    }

    public static void addMark(ItemStack stack){
        ItemMeta meta = stack.getItemMeta();
        List<String> lore = meta.getLore();
        if(lore == null){
            lore = new ArrayList<>();
        }
        //TODO: Name is volunerable to items being transferred between arenas
        //TODO: Configurable name
        lore.add(MARK);
        meta.setLore(lore);
        stack.setItemMeta(meta);
    }

    public static void rewardUnmarkedItems(Player player, RewardManager rewardManager){
        ItemStack[] contents = player.getInventory().getContents();
        for (ItemStack item:
                contents) {
            if(item == null)
                continue;
            if(checkForMark(item))
                continue;

            ItemStackThing thing = new ItemStackThing(item.clone());
            rewardManager.addReward(player, thing);
        }
    }

    public static void markPlayersItems(Player player){
        ItemStack[] itemStacks = player.getInventory().getContents();
        for (ItemStack item:
             itemStacks) {
            if(item == null)
                continue;
            addMark(item);
        }
    }
}
