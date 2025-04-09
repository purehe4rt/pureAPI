package eu.pureheart.api.action.actions;

import eu.pureheart.api.action.ActionHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Command implements ActionHandler {

    private final String command;

    public Command(String command) {
        this.command = command;
    }

    @Override
    public void execute(Player player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", player.getName()));
    }
}
