package me.dralle.genius.menuSystem;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;


import static me.dralle.genius.utilities.MenuUtil.createItem;

public abstract class Menu implements InventoryHolder {

    protected PlayerMenuUtility playerMenuUtilityTool;
    protected Inventory inventory;
    protected ItemStack FILLER_GLASS = createItem("GRAY_STAINED_GLASS_PANE", 1, "");

    public Menu(PlayerMenuUtility playerMenuUtilityTool) {
        this.playerMenuUtilityTool = playerMenuUtilityTool;
    }


    public abstract String getMenuName();

    public abstract int getSlots();

    public abstract void handleMenuItems(InventoryClickEvent e);

    public abstract void setMenuItems();

    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

        this.setMenuItems();
        playerMenuUtilityTool.getOwner().openInventory(inventory);

    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setFillerGlass(){
        for (int i = 0; i < getSlots(); i++) {
            if (inventory.getItem(i) == null){
                inventory.setItem(i, FILLER_GLASS);
            }
        }
    }


}
