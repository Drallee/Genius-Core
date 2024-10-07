package me.dralle.genius.commands;

import me.dralle.genius.menuSystem.menus.allPlayersListMenu;
import me.dralle.genius.menuSystem.menus.playerListMenu;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static me.dralle.genius.Core.getPlayerMenuUtility;
import static me.dralle.genius.utilities.ExtraUtil.plugin;
import static me.dralle.genius.utilities.TextUtil.*;

public class PlayerListCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            ArrayList<Player> players = new ArrayList<>(plugin.getServer().getOnlinePlayers());
            List<String> playerNames = new ArrayList<>();

            if (!players.isEmpty()) {
                for (Player onlinePlayer : players) {
                    playerNames.add(onlinePlayer.getName());
                }

                String onlinePlayersMessage = String.join("&7, &b", playerNames);
                consoleMessage("&6&lPlayer List &7》", true);
                consoleMessage("&6There are currently &7(&a" + players.size() + "&7/&c" + plugin.getServer().getMaxPlayers() + "&7) &6players online:", true);
                consoleMessage("&b" + onlinePlayersMessage, true);
            } else {
                consoleMessage("&cNo players online", true);
            }
            return true;
        }
        if(args.length < 1) {
            ArrayList<OfflinePlayer> players = new ArrayList<>(player.getServer().getOnlinePlayers());
            List<String> playerNames = new ArrayList<>();

            if (!players.isEmpty()) {
                for (OfflinePlayer onlinePlayer : players) {
                    playerNames.add(onlinePlayer.getName());
                }

                String onlinePlayersMessage = String.join("&7, &b", playerNames);
                sendPlayerMessage(player, "&6&lPlayer List &7》", true);
                sendPlayerMessage(player, "&6There are currently &7(&a" + players.size() + "&7/&c" + player.getServer().getMaxPlayers() + "&7) &6players online:", true);
                sendPlayerMessage(player, "&b" + onlinePlayersMessage, true);
            } else {
                sendPlayerMessage(player, "&cNo players online", true);
            }
            new playerListMenu(getPlayerMenuUtility(player)).open();
            return true;
        }
        if(args[0].equals("all")){
            if(!player.hasPermission("genius.list.all")){
                ArrayList<OfflinePlayer> players = new ArrayList<>(player.getServer().getOnlinePlayers());
                List<String> playerNames = new ArrayList<>();

                if (!players.isEmpty()) {
                    for (OfflinePlayer onlinePlayer : players) {
                        playerNames.add(onlinePlayer.getName());
                    }

                    String onlinePlayersMessage = String.join("&7, &b", playerNames);
                    sendPlayerMessage(player, "&6&lPlayer List &7》", true);
                    sendPlayerMessage(player, "&6There are currently &7(&a" + players.size() + "&7/&c" + player.getServer().getMaxPlayers() + "&7) &6players online:", true);
                    sendPlayerMessage(player, "&b" + onlinePlayersMessage, true);
                } else {
                    sendPlayerMessage(player, "&cNo players online", true);
                }
                new playerListMenu(getPlayerMenuUtility(player)).open();
                return true;
            }
            new allPlayersListMenu(getPlayerMenuUtility(player)).open();
            return true;

        }
        new playerListMenu(getPlayerMenuUtility(player)).open();
        return true;
    }
}
