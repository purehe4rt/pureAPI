package eu.pureheart.api.action.actions;

import eu.pureheart.api.action.ActionHandler;
import org.bukkit.entity.Player;

public class Sound implements ActionHandler {

    private final org.bukkit.Sound sound;
    private final float volume;
    private final float pitch;

    public Sound(String params) {
        String[] parts = params.split(";", 3);

        this.sound = org.bukkit.Sound.valueOf(parts[0].toUpperCase());
        this.volume = parts.length > 1 ? Float.parseFloat(parts[1]) : 1.0f;
        this.pitch = parts.length > 2 ? Float.parseFloat(parts[2]) : 1.0f;
    }

    @Override
    public void execute(Player player) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }
}
