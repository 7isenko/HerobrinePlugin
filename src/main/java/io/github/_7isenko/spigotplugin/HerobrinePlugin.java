package io.github._7isenko.spigotplugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class HerobrinePlugin extends JavaPlugin {
    // How to build: Maven/SpigotPlugin/Lifecycle/package
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.getCommand("herobrine").setExecutor(new HerobrineCommand());
    }

    @Override
    public void onDisable() {

    }

}