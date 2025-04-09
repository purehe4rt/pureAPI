package eu.pureheart.api.gui.button;

import org.bukkit.event.inventory.InventoryClickEvent;

@FunctionalInterface
public interface ButtonListener {

    void execute(InventoryClickEvent event);
}
