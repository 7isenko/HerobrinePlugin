package io.github._7isenko.spigotplugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotPlugin extends JavaPlugin {
    // How to build: Maven/SpigotPlugin/Lifecycle/package
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.getCommand("command").setExecutor(new SpigotPluginCommand());
    }

    @Override
    public void onDisable() {

    }

}