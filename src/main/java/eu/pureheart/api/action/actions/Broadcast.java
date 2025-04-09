package eu.pureheart.api.action.actions;

import eu.pureheart.api.PureAPI;
import eu.pureheart.api.action.ActionHandler;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Broadcast implements ActionHandler {

    private final String message;

    public Broadcast(String message) {
        this.message = message;
    }

    @Override
    public void execute(Player player) {
        Bukkit.broadcast(new TextComponent(PureAPI.getWithColor().hexToMinecraftColor(message)));
    }
}
