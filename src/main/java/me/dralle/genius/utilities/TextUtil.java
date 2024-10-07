package me.dralle.genius.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class TextUtil {

    public static String strippedColouredText(String message){
        return ChatColor.stripColor(message);
    }

    public static String colouredText(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendPlayerMessage(Player player, String message, boolean colouredText){
        if(colouredText){
            message = ChatColor.translateAlternateColorCodes('&', message);
        }
        player.sendMessage(message);
    }

    public static void broadcast(String message, boolean colouredText) {
        if(colouredText){
            message = ChatColor.translateAlternateColorCodes('&', message);
        }
        Bukkit.broadcastMessage(message);
    }

    public static void consoleMessage(String message, boolean colouredText){
        if(colouredText){
            message = ChatColor.translateAlternateColorCodes('&', message);
        }
        Bukkit.getServer().getConsoleSender().sendMessage(message);
    }

    public static String textReplacer(@NotNull String message, Object... replacements) {
        Map<String, String> replacementMap = new HashMap<>();

        // Populate the replacement map with provided key-value pairs
        for (int i = 0; i < replacements.length; i += 2) {
            if (i + 1 < replacements.length && replacements[i] instanceof String) {
                replacementMap.put((String) replacements[i], String.valueOf(replacements[i + 1]));
            }
        }

        // Perform replacements in the string
        for (Map.Entry<String, String> entry : replacementMap.entrySet()) {
            message = message.replace(entry.getKey(), entry.getValue());
        }
        return message;
    }

}
