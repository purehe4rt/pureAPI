package eu.pureheart.api.gui;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface CloseListener {

    void onClose(Player player);
}