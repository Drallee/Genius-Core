package me.dralle.genius.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.dralle.genius.utilities.ExtraUtil.reloadDefaultConfig;
import static me.dralle.genius.utilities.TextUtil.*;
import static me.dralle.genius.utilities.TimeUtil.updateTimestamp;

public class ReloadConfigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args) {
        if(!(sender instanceof Player p)){
            sender.sendMessage("errorMessageNotAPlayer");
            return true;
        }
        if (!p.hasPermission("dralle.reload")) {
            sendPlayerMessage(p, "&cYou do not have the permission to do this", true);
            return true;
        }
        String name = p.getName();
        String displayName = p.getDisplayName();
        String chat_prefix = "&e&lRELOAD &7》";
        String reloadMessage = textReplacer(
                "{chat_prefix} &eReloading config.yml",
                "{player}", name,
                "{displayname}", displayName,
                "{timestamp}", updateTimestamp(),
                "{chat_prefix}", chat_prefix
        );
        String reloadCompleteMessage = textReplacer(
                "{chat_prefix} &aReloading of configs complete",
                "{player}", name,
                "{displayname}", displayName,
                "{timestamp}", updateTimestamp(),
                "{chat_prefix}", chat_prefix
        );
        String reloadMessageConsole = textReplacer(
                "&7[&e{timestamp}&7] {chat_prefix} &b{player} &eis reloading plugin configs",
                "{player}", name,
                "{displayname}", displayName,
                "{timestamp}", updateTimestamp(),
                "{chat_prefix}", chat_prefix
        );

        sendPlayerMessage(p, reloadMessage, true);
        consoleMessage(reloadMessageConsole, true);
        reloadDefaultConfig();
        sendPlayerMessage(p, reloadCompleteMessage, true);

        return true;
    }
}
