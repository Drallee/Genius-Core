package me.dralle.genius.tabCompletion;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LastSeenTabCompletion implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> playerNames = new ArrayList<>();
            String input = args[0].toLowerCase(); // Convert input to lowercase for case-insensitive comparison

            // Iterate through offline players and add names that contain the input to the list
            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                if (offlinePlayer.hasPlayedBefore() && offlinePlayer.getName().toLowerCase().contains(input)) {
                    playerNames.add(offlinePlayer.getName());
                }
            }
            return playerNames;
        }
        return new ArrayList<>();
    }
}
