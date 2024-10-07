package me.dralle.genius;

import me.dralle.genius.commands.*;
import me.dralle.genius.listeners.MenuListener;
import me.dralle.genius.listeners.ServerPingListener;
import me.dralle.genius.menuSystem.PlayerMenuUtility;
import me.dralle.genius.tabCompletion.InventoryViewCompletion;
import me.dralle.genius.tabCompletion.LastSeenTabCompletion;
import me.dralle.genius.tabCompletion.NoTabCompletion;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;

public final class Core extends JavaPlugin {

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    private static Core instance;
    private final PluginDescriptionFile pdf = this.getDescription();
    private final String currentVersion = pdf.getVersion();

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("Plugin enabled!");
        PluginManager pm = getServer().getPluginManager();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Register the command executor for the "list" command
        getCommand("motd").setExecutor(new MOTDCommand());
        getCommand("list").setExecutor(new PlayerListCommand());
        getCommand("reload-config").setExecutor(new ReloadConfigCommand());
        getCommand("lastseen").setExecutor(new LastSeenCommand());
        getCommand("viewinventory").setExecutor(new ViewInventory());
        getCommand("viewenderchest").setExecutor(new ViewEnderChest());


        // Register command tab completions
        getCommand("lastseen").setTabCompleter(new LastSeenTabCompletion());
        getCommand("motd").setTabCompleter(new NoTabCompletion());
        getCommand("list").setTabCompleter(new NoTabCompletion());
        getCommand("reload-config").setTabCompleter(new NoTabCompletion());
        getCommand("viewinventory").setTabCompleter(new InventoryViewCompletion());
        getCommand("viewenderchest").setTabCompleter(new InventoryViewCompletion());

        pm.registerEvents(new ServerPingListener(), this);
        pm.registerEvents(new MenuListener(), this);

    }

    //Provide a player and return a menu system for that player
    //create one if they don't already have one
    public static PlayerMenuUtility getPlayerMenuUtility(Player player) {
        PlayerMenuUtility playerMenuUtilityTool;
        if (!(playerMenuUtilityMap.containsKey(player))) { //See if the player has a playermenuutility "saved" for them

            //This player doesn't. Make one for them add it to the hashmap
            playerMenuUtilityTool = new PlayerMenuUtility(player);
            playerMenuUtilityMap.put(player, playerMenuUtilityTool);

            return playerMenuUtilityTool;
        } else {
            return playerMenuUtilityMap.get(player); //Return the object by using the provided player
        }
    }

    public static Core getInstance() {
        return instance;
    }


}
