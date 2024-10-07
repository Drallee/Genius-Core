package me.dralle.genius.utilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

public class PlayerHeadSkinChange {

    // Change the player head's skin using a player's name
    public static ItemStack changePlayerHeadSkinByString(@NotNull String playerName, @NotNull ItemStack item) {
        if (item.getType() != Material.PLAYER_HEAD) {
            throw new IllegalArgumentException("Item must be a PLAYER_HEAD");
        }

        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();

        if (skullMeta != null) {
            // Set the player's name as the owner, works for both online and offline players
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);
            skullMeta.setOwningPlayer(offlinePlayer);

            item.setItemMeta(skullMeta);
        }

        return item;
    }

    // Change the player head's skin using a Player object
    public static ItemStack changePlayerHeadSkinByPlayer(@NotNull OfflinePlayer player, @NotNull ItemStack item) {
        if (item.getType() != Material.PLAYER_HEAD) {
            throw new IllegalArgumentException("Item must be a PLAYER_HEAD");
        }

        SkullMeta meta = (SkullMeta) item.getItemMeta();

        if (meta != null) {
            // Set the player as the owner of the skull
            meta.setOwningPlayer(player);
            item.setItemMeta(meta);
        }

        return item;
    }

}
