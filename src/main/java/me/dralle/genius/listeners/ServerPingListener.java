package me.dralle.genius.listeners;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.jetbrains.annotations.NotNull;

import static me.dralle.genius.utilities.ExtraUtil.*;
import static me.dralle.genius.utilities.TextUtil.*;
import static org.bukkit.Bukkit.getServer;

public class ServerPingListener implements Listener {
    public static Server server = getServer();
    public static String serverVersion = server.getVersion();
    public static String minecraftVersion = server.getBukkitVersion();
    public static String pluginVersion = plugin.getDescription().getVersion();
    protected static String Ping(){
        String motd = textReplacer(
                getConfigMotd("settings.motd.ping.message"),
                "{plugin_version}", pluginVersion,
                "{server_version}", serverVersion,
                "{minecraft_version}", minecraftVersion,
                "{discord_link}", getConfigMessage("formatting.discord"),
                "{website_link}", getConfigMessage("formatting.website")
        );
        return motd;
    }
    public static String MOTD(){
        String motd = textReplacer(
                getConfigMotd("settings.motd.join.message"),
                "{plugin_version}", pluginVersion,
                "{server_version}", serverVersion,
                "{minecraft_version}", minecraftVersion,
                "{discord_link}", getConfigMessage("formatting.discord"),
                "{website_link}", getConfigMessage("formatting.website")
        );
        return motd;
    }

    @EventHandler
    protected void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        if(!getConfigCheck("settings.motd.ping.enable")){
            return;
        }
        sendPlayerMessage(player, MOTD(), true);
    }
    @EventHandler
    protected void ServerListPing(@NotNull ServerListPingEvent e){
        if(!getConfigCheck("settings.motd.ping.enable")){
            consoleMessage("&4Error while loading server motd &c" + getConfigCheck("settings.server-motd.ping.enable"), true);
            return;
        }
        e.setMotd(colouredText(Ping()));
        //e.setMaxPlayers(e.getNumPlayers() + 1);
    }
}
