package eu.pureheart.api.action.actions;

import eu.pureheart.api.PureAPI;
import eu.pureheart.api.action.ActionHandler;
import org.bukkit.entity.Player;

public class Title implements ActionHandler {

    private final String title;
    private final String subtitle;
    private final int fadeIn;
    private final int stay;
    private final int fadeOut;

    public Title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        this.title = PureAPI.getWithColor().hexToMinecraftColor(title);
        this.subtitle = PureAPI.getWithColor().hexToMinecraftColor(subtitle);
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    @Override
    public void execute(Player player) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }
}
