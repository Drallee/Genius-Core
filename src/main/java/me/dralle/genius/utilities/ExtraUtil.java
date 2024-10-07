package me.dralle.genius.utilities;

import me.dralle.genius.Core;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import java.util.Date;
import java.util.Objects;

import static me.dralle.genius.utilities.TextUtil.*;
import static me.dralle.genius.utilities.TextUtil.sendPlayerMessage;
import static me.dralle.genius.utilities.TimeUtil.calculateTime;
import static me.dralle.genius.utilities.TimeUtil.df;

public class ExtraUtil {

    public static Plugin plugin = Core.getPlugin(Core.class);

    public static String getConfigMessage(String path){
        if (plugin != null && plugin.getConfig() != null && plugin.getConfig().isSet(path)) {
            return plugin.getConfig().getString(path);
        }
        return "&cError&r";
    }

    public static String getConfigMotd(String path){
        if (plugin != null && plugin.getConfig() != null && plugin.getConfig().isSet(path)) {
            return plugin.getConfig().getString(path);
        }
        return "&e&lLoading...&r";
    }

    public static int getConfigNumber(String path){
        if (plugin != null && plugin.getConfig() != null && plugin.getConfig().isSet(path)) {
            return plugin.getConfig().getInt(path);
        }
        return 0;
    }

    public static Boolean getConfigCheck(String path){
        if (plugin != null && plugin.getConfig() != null && plugin.getConfig().isSet(path)) {
            return plugin.getConfig().getBoolean(path);
        }
        return false;
    }

    public static void reloadDefaultConfig(){
        consoleMessage("&eReloading config.yml", true);
        plugin.reloadConfig();
        consoleMessage("&eReload completed", true);
    }

    public static String getLocationString(Location location){
        String world = Objects.requireNonNull(location.getWorld()).getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        double pitch = location.getPitch();
        double yaw = location.getYaw();
        return textReplacer(getConfigMessage("formatting.location"),
                "{world}", world,
                "{x}", df.format(x),
                "{y}", df.format(y),
                "{z}", df.format(z),
                "{pitch}", df.format(pitch),
                "{yaw}", df.format(yaw));
    }

    public static String checkIsWhitelisted(boolean isWhitelisted){
        if(!isWhitelisted){
            return getConfigMessage("formatting.whitelisted.false");
        }
        return getConfigMessage("formatting.whitelisted.true");
    }

    public static String checkLastPlayed(OfflinePlayer targetPlayer){

        String playerName = targetPlayer.getName();
        if (targetPlayer.isOnline()) {
            return textReplacer(getConfigMessage("formatting.lastPlayed.menu.online"), "{player}", playerName);
        }

        Date now = new Date();
        Date lastSeen = new Date(targetPlayer.getLastPlayed());

        return textReplacer(
                getConfigMessage("formatting.lastPlayed.menu.offline"),
                "{player}", playerName,
                "{time}", calculateTime(lastSeen, now)
        );
    }

}
