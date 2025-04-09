package eu.pureheart.api.gui.menu;

import eu.pureheart.api.PureAPI;
import eu.pureheart.api.gui.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory topInventory = event.getView().getTopInventory();
        Menu menu = PureAPI.getMenuManager().getMenu(topInventory);

        if (menu == null) return;

        event.setCancelled(true);

        if (event.getClickedInventory() != null && event.getClickedInventory().equals(topInventory)) {
            Button button = menu.getSlot(event.getSlot());

            if (button != null && !button.isInteractDisabled() && button.hasListener()) {
                button.executeListener(event);
            }
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        Inventory topInventory = event.getView().getTopInventory();
        Menu menu = PureAPI.getMenuManager().getMenu(topInventory);

        if (menu != null) {
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
}
