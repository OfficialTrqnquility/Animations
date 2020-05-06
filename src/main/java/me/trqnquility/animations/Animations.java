package me.trqnquility.animations;

import me.trqnquility.animations.particles.commands.ConeCommand;
import me.trqnquility.animations.particles.commands.HelixCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Animations extends JavaPlugin {

    private static Animations instance;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private final void registerCommands() {
        getCommand("helix").setExecutor(new HelixCommand());
        getCommand("cone").setExecutor(new ConeCommand());
    }

    public static Animations getInstance() {
        return instance;
    }
}
