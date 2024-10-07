package me.dralle.genius.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import static me.dralle.genius.utilities.TextUtil.colouredText;
import static me.dralle.genius.utilities.TextUtil.sendPlayerMessage;


public class ViewEnderChest implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(colouredText("&cThis command can only be executed by players"));
            return true;
        }
        if(!player.hasPermission("genius.enderchest.use.others")){
            sendPlayerMessage(player, "&4&lPERMISSION &7》 &cYou do not have permission to use this command", true);
            return true;
        }
        if(args.length < 1){
            sendPlayerMessage(player, "&5&lEnderChest &7》 &cYou need to specify a player &7[&e/"+label+" <player>&7]", true);
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            sendPlayerMessage(player,"&5&lEnderChest &7》 &cPlayer not found", true);
            return true;
        }
        if(target == player){
            sendPlayerMessage(player, "&5&lEnderChest &7》 &cYou can't look at your own &5EnderChest", true);
            return true;
        }
        if(target.hasPermission("genius.deny.others.view.enderchest")){
            sendPlayerMessage(player, "&5&lEnderChest &7》 &cYou can't look at &b" + target.getName() + "'s &5EnderChest", true);
            return true;
        }
        Inventory targetEnderChest = target.getEnderChest();
        player.openInventory(targetEnderChest);
        sendPlayerMessage(player, "&5&lEnderChest &7》 &aYou are viewing &b" + target.getName() + "'s &5EnderChest", true);
        return true;
    }
}
