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

    public Title(String params) {
        String[] parts = params.split(";", 5);

        this.title = PureAPI.getWithColor().hexToMinecraftColor(parts[0]);
        this.subtitle = parts.length > 1 ? PureAPI.getWithColor().hexToMinecraftColor(parts[1]) : "";
        this.fadeIn = parts.length > 2 ? Integer.parseInt(parts[2]) : 10;
        this.stay = parts.length > 3 ? Integer.parseInt(parts[3]) : 20;
        this.fadeOut = parts.length > 4 ? Integer.parseInt(parts[4]) : 10;
    }

    @Override
    public void execute(Player player) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }
}
