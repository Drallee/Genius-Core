package me.dralle.genius.tabCompletion;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InventoryViewCompletion implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        if (args.length == 1) {
            List<String> playerNames = new ArrayList<>();
            String input = args[0].toLowerCase(); // Convert input to lowercase for case-insensitive comparison

            // Iterate through online players and add names that contain the input to the list
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                if (player.getName().toLowerCase().contains(input)) {
                    playerNames.add(player.getName());
                }
            }
            return playerNames;
        }
        // If more than one argument is provided, return an empty list
        return new ArrayList<>();
    }
}
