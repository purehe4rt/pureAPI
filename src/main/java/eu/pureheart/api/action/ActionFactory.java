package eu.pureheart.api.action;

import eu.pureheart.api.action.actions.*;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    private static final Map<String, Class<? extends ActionHandler>> commandMap = new HashMap<>();

    static {
        register("[message]", Message.class);
        register("[actionbar]", ActionBar.class);
        register("[title]", Title.class);
        register("[broadcast]", Broadcast.class);
        register("[command]", Command.class);
        register("[player]", Player.class);
        register("[sound]", Sound.class);
    }

    public static void register(String prefix, Class<? extends ActionHandler> handlerClass) {
        commandMap.put(prefix, handlerClass);
    }

    public static ActionHandler create(String commandString) {
        for (Map.Entry<String, Class<? extends ActionHandler>> entry : commandMap.entrySet()) {
            if (commandString.startsWith(entry.getKey())) {
                try {
                    String content = commandString.substring(entry.getKey().length()).trim();
                    return entry.getValue().getConstructor(String.class).newInstance(content);
                } catch (Exception error) {
                    error.printStackTrace();
                }
            }
        }

        return null;
    }
}
