package me.dralle.genius.menuSystem;

import java.util.ArrayList;

import static me.dralle.genius.utilities.MenuUtil.*;

public abstract class PaginatedMenu extends Menu {
    //Keep track of what page the menu is on
    protected int page = 0;
    protected long pages = 1;
    protected int indexes = 0;
    //28 is max items because with the border set below,
    //28 empty slots are remaining.
    protected int maxItemsPerPage = 28;
    //the index represents the index of the slot
    //that the loop is on
    protected int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtilityTool) {
        super(playerMenuUtilityTool);
    }

    public void getIndexes(){
        ArrayList<String> items = playerMenuUtilityTool.getItems();
        long pageSize = getMaxItemsPerPage();
        long totalCount = items.size();
        pages = calculatePagesCount(pageSize, totalCount);
        if(!items.isEmpty()) {
            for(int i = 0; i < getMaxItemsPerPage(); i++) {
                indexes = getMaxItemsPerPage() * page + i;
                if(indexes >= items.size()) break;
            }
        }
    }

    //Set the border and menu buttons for the menu
    public void addMenuBorderSecondary(){
        ArrayList<String> items = playerMenuUtilityTool.getItems();
        getIndexes();

        if(page != 0){
            inventory.setItem(48, createPreviousPageItem(page+1, indexes));
        }

        if (!((indexes + 1) >= items.size())){
            inventory.setItem(50, createNextPageItem(page+1, indexes));
        }

        bottomLineItems();
    }

    public void addMenuBorderDefault(){
        inventory.setItem(48, createPreviousPageItem(page, indexes));
        inventory.setItem(50, createNextPageItem(page, indexes));

        bottomLineItems();
    }

    private void bottomLineItems() {
        inventory.setItem(49, createCloseButtomItem());

        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }

        inventory.setItem(17, super.FILLER_GLASS);
        inventory.setItem(18, super.FILLER_GLASS);
        inventory.setItem(26, super.FILLER_GLASS);
        inventory.setItem(27, super.FILLER_GLASS);
        inventory.setItem(35, super.FILLER_GLASS);
        inventory.setItem(36, super.FILLER_GLASS);

        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }

}
