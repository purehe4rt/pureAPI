package eu.pureheart.api;

import eu.pureheart.api.color.BukkitColor;
import eu.pureheart.api.color.ColorImpl;
import eu.pureheart.api.gui.menu.MenuManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PureAPI extends JavaPlugin {

    private static PureAPI api;
    private static BukkitColor withColor;
    private static MenuManager menuManager;

    @Override
    public void onEnable() {
        api = this;
        saveDefaultConfig();

        withColor = new ColorImpl();
        menuManager = new MenuManager();
    }

    public static PureAPI getAPI() {
        return api;
    }

    public static BukkitColor getWithColor() {
        return withColor;
    }

    public static MenuManager getMenuManager() {
        return menuManager;
    }
}
