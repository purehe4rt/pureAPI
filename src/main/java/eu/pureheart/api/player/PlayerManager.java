package eu.pureheart.api.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerManager {

    public static List<Player> getPlayersNear(Location centerLocation, int radius) {
        if (radius <= 0) {
            return centerLocation.getWorld().getPlayers();
        }

        double squaredDistance = radius * radius;
        return centerLocation.getWorld().getPlayers().stream()
                .filter(player -> centerLocation.distanceSquared(player.getLocation()) <= squaredDistance)
                .collect(Collectors.toList());
    }

    public static List<Player> getPlayersWithPermission(String permission) {
        return Bukkit.getOnlinePlayers().stream()
                .filter(player -> player.hasPermission(permission))
                .collect(Collectors.toList());
    }
}
