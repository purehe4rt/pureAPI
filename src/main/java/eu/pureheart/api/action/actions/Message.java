package eu.pureheart.api.action.actions;

import eu.pureheart.api.PureAPI;
import eu.pureheart.api.action.ActionHandler;
import org.bukkit.entity.Player;

public class Message implements ActionHandler {

    private final String message;

    public Message(String message) {
        this.message = PureAPI.getWithColor().hexToMinecraftColor(message);
    }

    @Override
    public void execute(Player player) {
        player.sendMessage(message);
    }
}
