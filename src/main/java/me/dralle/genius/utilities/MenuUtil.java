package me.dralle.genius.utilities;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static me.dralle.genius.utilities.TextUtil.*;

public class MenuUtil {
    public static ItemStack createItem(@NotNull String materialString, @NotNull int amount, @NotNull String displayName, @NotNull String... lore){
        ItemStack item = new ItemStack(Material.getMaterial(materialString), amount);
        List<String> loreArray = new ArrayList<>();

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(colouredText(displayName));

        for (String s : lore){
            loreArray.add(colouredText(s));
        }
        itemMeta.setLore(loreArray);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack createCloseButtomItem() {
        return createItem("BARRIER", 1, "&cClose");
    }

    public static ItemStack createNextPageItem(int page, int totalPages){
        return createItem("ARROW", 1, "&aNext page", "&7Current page&8: &b" + (page+1), "&7Total pages&8: &c"+(totalPages+1));
    }

    public static ItemStack createPreviousPageItem(int page, int totalPages){
        return createItem("ARROW", 1, "&aPrevious page", "&7Current page&8: &b" + (page+1), "&7Total pages&8: &c"+(totalPages+1));
    }
    public static long calculatePagesCount(long pageSize, long totalCount) {

        return totalCount < pageSize ? 1 : (long) Math.ceil((double) totalCount / (double) pageSize);
    }

}
