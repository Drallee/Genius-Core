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


public class ViewInventory implements CommandExecutor {
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
            sendPlayerMessage(player, "&9&lInventory &7》 &cYou need to specify a player &7[&e/"+label+" <player>&7]", true);
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if(target == null){
            sendPlayerMessage(player,"&9&lInventory &7》 &cPlayer not found", true);
            return true;
        }
        if(target == player){
            sendPlayerMessage(player, "&9&lInventory &7》 &cYou can't look at your own &9inventory", true);
            return true;
        }
        if(target.hasPermission("genius.deny.others.view.inventory")){
            sendPlayerMessage(player, "&9&lInventory &7》 &cYou can't look at &b" + target.getName() + "'s &9Inventory", true);
            return true;
        }
        Inventory targetInventory = target.getInventory();
        player.openInventory(targetInventory);
        sendPlayerMessage(player, "&9&lInventory &7》 &aYou are viewing &b" + target.getName() + "'s &9Inventory", true);
        return true;
    }
}
