package me.dralle.genius.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

import static me.dralle.genius.utilities.ExtraUtil.getConfigMessage;
import static me.dralle.genius.utilities.ExtraUtil.plugin;
import static me.dralle.genius.utilities.TextUtil.*;
import static me.dralle.genius.utilities.TimeUtil.calculateTime;

public class LastSeenCommand implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            if (args.length != 1) {
                sender.sendMessage("Usage: /lastseen <player>");
                return true;
            }

            OfflinePlayer targetPlayer = plugin.getServer().getOfflinePlayer(args[0]);
            if (!targetPlayer.hasPlayedBefore()) {
                consoleMessage(textReplacer(getConfigMessage("settings.commands.last-seen.never-played.message"), "{player}", args[0]), true);
                return true;
            }

            String playerName = targetPlayer.getName();
            if (targetPlayer.isOnline()) {
                consoleMessage(textReplacer(getConfigMessage("settings.commands.last-seen.online.message"), "{player}", playerName), true);
            } else {
                Date now = new Date();
                Date lastSeen = new Date(targetPlayer.getLastPlayed());

                String lastSeenMessage = textReplacer(
                        getConfigMessage("settings.commands.last-seen.offline.message"),
                        "{player}", playerName,
                        "{time}", calculateTime(lastSeen, now)
                );
                consoleMessage(lastSeenMessage, true);
            }
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("Usage: /lastseen <player>");
            return true;
        }

        OfflinePlayer targetPlayer = plugin.getServer().getOfflinePlayer(args[0]);
        if (!targetPlayer.hasPlayedBefore()) {
            sendPlayerMessage(player, textReplacer(getConfigMessage("formatting.lastPlayed.command.never-played"), "{player}", args[0]), true);
            return true;
        }

        String playerName = targetPlayer.getName();
        if (targetPlayer.isOnline()) {
            sendPlayerMessage(player, textReplacer(getConfigMessage("formatting.lastPlayed.command.online"), "{player}", playerName), true);
        } else {
            Date now = new Date();
            Date lastSeen = new Date(targetPlayer.getLastPlayed());

            String lastSeenMessage = textReplacer(
                    getConfigMessage("formatting.lastPlayed.command.offline"),
                    "{player}", playerName,
                    "{time}", calculateTime(lastSeen, now)
            );
            sendPlayerMessage(player, lastSeenMessage, true);
        }

        return true;
    }
}