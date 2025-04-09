package eu.pureheart.api.action.actions;

import eu.pureheart.api.action.ActionHandler;

public class Player implements ActionHandler {

    private final String command;

    public Player(String command) {
        this.command = command;
    }

    @Override
    public void execute(org.bukkit.entity.Player player) {
        player.performCommand(command);
    }
}
