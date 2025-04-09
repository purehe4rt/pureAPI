package eu.pureheart.api.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseCommand implements CommandExecutor, TabCompleter {

    private final String name;
    private final String[] usages;

    public BaseCommand(String name, String... aliases) {
        this.name = name;
        this.usages = aliases;
    }

    public String[] getAliases() {
        return this.usages;
    }

    public String getName() {
        return this.name;
    }

    public static void register(Plugin plugin, BaseCommand... executors) {
        for (BaseCommand executor : executors) {
            List<String> list = new ArrayList<>();
            Collections.addAll(list, executor.getAliases());
            list.add(executor.getName());
            RegisterCommand.reg(plugin, executor.getCommandExecutor(), list, "i love Mama <3, thanks for API", executor.getName());
        }
    }

    public CommandExecutor getCommandExecutor() {
        return this;
    }

    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        try {
            execute(sender, args);
        } catch (ClassCastException error) {
            sender.sendMessage("NonNull");
        }
        return false;
    }

    @Override
    public abstract List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args);
}
