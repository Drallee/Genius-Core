package me.dralle.genius.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.dralle.genius.listeners.ServerPingListener.MOTD;
import static me.dralle.genius.utilities.TextUtil.sendPlayerMessage;

public class MOTDCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("This command can only be executed by players.");
            return true;
        }

        sendPlayerMessage(player, MOTD(), true);

        return true;
    }
}
