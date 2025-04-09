package eu.pureheart.api.action.actions;

import eu.pureheart.api.action.ActionHandler;
import org.bukkit.entity.Player;

public class Sound implements ActionHandler {

    private final org.bukkit.Sound sound;
    private final float volume;
    private final float pitch;

    public Sound(String sound, float volume, float pitch) {
        this.sound = org.bukkit.Sound.valueOf(sound);
        this.volume = volume;
        this.pitch = pitch;
    }

    @Override
    public void execute(Player player) {
        player.playSound(player.getLocation(), sound, volume, pitch);
    }
}
