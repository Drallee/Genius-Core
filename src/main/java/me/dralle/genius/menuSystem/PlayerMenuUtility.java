package me.dralle.genius.menuSystem;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerMenuUtility {
    private Player owner;

    //store the player
    private Player player;
    private OfflinePlayer target;
    // Store the home to settings page
    private Object item;
    private ArrayList<String> items;

    public PlayerMenuUtility(Player player) {
        this.owner = player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setTarget(OfflinePlayer player) {
        this.target = player;
    }

    public void setItemToChange(Object item) {
        this.item = item;
    }

    public void setItems(ArrayList items) {
        this.items = items;
    }

    public Player getOwner() {
        return owner;
    }

    public Player getPlayer() {
        return player;
    }
    public OfflinePlayer getTarget() {
        return target;
    }

    public Object getItem() {
        return item;
    }

    public ArrayList<String> getItems() {
        return items;
    }

}
