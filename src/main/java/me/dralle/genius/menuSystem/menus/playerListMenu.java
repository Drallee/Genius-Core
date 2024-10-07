package me.dralle.genius.menuSystem.menus;

import me.dralle.genius.menuSystem.PaginatedMenu;
import me.dralle.genius.menuSystem.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

import static me.dralle.genius.utilities.ExtraUtil.*;
import static me.dralle.genius.utilities.PlayerHeadSkinChange.changePlayerHeadSkinByPlayer;
import static me.dralle.genius.utilities.TextUtil.*;
import static me.dralle.genius.utilities.MenuUtil.createItem;
import static me.dralle.genius.utilities.TimeUtil.*;
import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Statistic.TOTAL_WORLD_TIME;

public class playerListMenu extends PaginatedMenu {


    public playerListMenu(PlayerMenuUtility playerMenuUtilityTool) {
        super(playerMenuUtilityTool);
    }

    @Override
    public String getMenuName() {
        ArrayList<OfflinePlayer> players = new ArrayList<>(getServer().getOnlinePlayers());
        Player player = playerMenuUtilityTool.getOwner();
        String playerName = player.getName();
        String displayName = player.getDisplayName();
        String chat_prefix = "&6&lPlayer List &7》";
        int max_players = getServer().getMaxPlayers();
        int current_players = players.size();
        String title;
        title = colouredText(textReplacer(getConfigMessage("GUI.menus.player-list.title"),
                "{player_name}", playerName,
                "{displayname}", displayName,
                "{timestamp}", updateTimestamp(),
                "{chat_prefix}", chat_prefix,
                "{current}", current_players,
                "{max}", max_players));
        return title;
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenuItems(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        ArrayList<Player> players = new ArrayList<Player>(getServer().getOnlinePlayers());
        switch (Objects.requireNonNull(e.getCurrentItem()).getType()){
            case ARROW:
                if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Left")){
                    if (page == 0){
                        player.sendMessage(colouredText("&cYou are already on the first page."));
                    }else{
                        page = page - 1;
                        super.open();
                    }
                }else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Right")){
                    if (!((index + 1) >= players.size())){
                        page = page + 1;
                        super.open();
                    }else{
                        player.sendMessage(colouredText("&cYou are on the last page."));
                    }
                }

                break;
            case BARRIER:
                player.closeInventory();
        }
    }

    @Override
    public void setMenuItems() {
        addMenuBorderDefault();
        ArrayList<OfflinePlayer> players = new ArrayList<>(getServer().getOnlinePlayers());
        players.sort(Comparator.comparingLong(OfflinePlayer::getFirstPlayed));
        if(players != null && !players.isEmpty()) {
            for(int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if(index >= players.size()) break;
                if (players.get(index) != null){
                    OfflinePlayer targetPlayer = players.get(index);

                    String displayName = getConfigMessage("GUI.menus.player-list.item.name");
                    displayName = textReplacer(displayName, "{player}", targetPlayer.getName());


                    String lore = getConfigMessage("GUI.menus.player-list.item.lore");

                    Date now = new Date();
                    Date firstPlayed = new Date(targetPlayer.getFirstPlayed());

                    Location location = targetPlayer.getLocation();

                    String isWhitelisted = checkIsWhitelisted(targetPlayer.isWhitelisted());

                    lore = textReplacer(lore,
                            "{player}", targetPlayer.getName(),
                            "{firstPlayed}", calculateTime(firstPlayed, now),
                            "{totalWorldTime}", getTimePlayed(targetPlayer),
                            "{location}", getLocationString(location),
                            "{loc_x}", df.format(location.getX()),
                            "{loc_y}", df.format(location.getY()),
                            "{loc_z}", df.format(location.getZ()),
                            "{loc_yaw}", df.format(location.getYaw()),
                            "{loc_pitch}", df.format(location.getPitch()),
                            "{loc_world}", location.getWorld().getName(),
                            "{isWhitelisted}", isWhitelisted);
                    String[] loreLines = lore.split("\n");


                    ItemStack playerItem = createItem("PLAYER_HEAD", 1, displayName, loreLines);

                    inventory.addItem(changePlayerHeadSkinByPlayer(targetPlayer, playerItem));
                }
            }
        }
    }
}

