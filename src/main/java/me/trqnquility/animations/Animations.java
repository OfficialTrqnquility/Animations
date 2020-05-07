package me.trqnquility.animations;

import me.trqnquility.animations.armorstands.commands.RiseCommand;
import me.trqnquility.animations.armorstands.commands.SpinCommand;
import me.trqnquility.animations.particles.commands.CircleCommand;
import me.trqnquility.animations.particles.commands.HelixCommand;
import me.trqnquility.animations.particles.commands.LineCommand;
import me.trqnquility.animations.particles.commands.TunnelCommand;
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
        getCommand("line").setExecutor(new LineCommand());
        getCommand("circle").setExecutor(new CircleCommand());
        getCommand("tunnel").setExecutor(new TunnelCommand());

        getCommand("rise").setExecutor(new RiseCommand());
        getCommand("spin").setExecutor(new SpinCommand());
        getCommand("packet").setExecutor(new PacketCommand());
    }

    public static Animations getInstance() {
        return instance;
    }
}
