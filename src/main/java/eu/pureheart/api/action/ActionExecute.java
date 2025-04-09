package eu.pureheart.api.action;

import org.bukkit.entity.Player;

import java.util.List;

public class ActionExecute {

    public static void execute(List<String> commands, Player player) {
        if (commands == null || commands.isEmpty()) {
            return;
        }

        for (String commandString : commands) {
            ActionHandler handler = ActionFactory.create(commandString);
            if (handler != null) {
                handler.execute(player);
            }
        }
    }
}
