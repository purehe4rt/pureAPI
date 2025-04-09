package eu.pureheart.api.gui.menu;

import eu.pureheart.api.PureAPI;
import eu.pureheart.api.gui.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuListener implements Listener {

    @EventHandler
    public void onInteract(InventoryInteractEvent event) {
        Inventory clicked = event.getView().getTopInventory();
        Menu menu = PureAPI.getMenuManager().getMenu(clicked);

        if (menu != null && menu.isInteractDisabled()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Inventory closed = event.getInventory();
        Menu menu = PureAPI.getMenuManager().getMenu(closed);

        if (menu != null) {
            menu.stopAutoUpdate();

            if (menu.hasCloseListener()) {
                menu.getCloseListener().onClose((Player) event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getAction() == InventoryAction.NOTHING || event.isCancelled()) return;

        Inventory clicked = event.getInventory();
        Menu menu = PureAPI.getMenuManager().getMenu(clicked);

        if (menu != null) {
            if (menu.isInteractDisabled()) {
                event.setCancelled(true);
                return;
            }

            Button button = menu.getSlot(event.getSlot());
            if (button != null) {
                if (button.isInteractDisabled() &&
                        event.getClickedInventory().equals(event.getView().getTopInventory())) {
                    event.setCancelled(true);
                }

                button.executeListener(event);

                ItemStack clickedItem = event.getCurrentItem();
                if (clickedItem != null && clickedItem.isSimilar(menu.getPrevPageItem())) {
                    menu.setPage(menu.getCurrentPage() - 1);
                } else if (clickedItem != null && clickedItem.isSimilar(menu.getNextPageItem())) {
                    menu.setPage(menu.getCurrentPage() + 1);
                }
            }
        }
    }
}
