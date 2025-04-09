package eu.pureheart.api.action.actions;

import eu.pureheart.api.PureAPI;
import eu.pureheart.api.action.ActionHandler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ActionBar implements ActionHandler {

    private final String message;

    public ActionBar(String message) {
        this.message = PureAPI.getWithColor().hexToMinecraftColor(message);
    }

    @Override
    public void execute(Player player) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
    }
}
