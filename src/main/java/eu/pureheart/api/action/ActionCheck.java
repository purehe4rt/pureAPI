package eu.pureheart.api.action;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class ActionCheck {

    private static FileConfiguration config;

    public static void loadConfig(FileConfiguration fileConfig) {
        config = fileConfig;
    }

    public static void executeFromConfig(Player player, String path) {
        if (config == null) {
            throw new IllegalStateException("[v] Конфигурация загружена! Используйте ActionCheck.loadConfig() для начала.");
        }

        List<String> commands = config.getStringList(path);
        if (commands.isEmpty()) {
            player.sendMessage(ChatColor.RED + "[x] Нет команд в пути: " + path);
            return;
        }

        ActionExecute.execute(commands, player);
    }
}
