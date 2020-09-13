package io.github._7isenko.spigotplugin;

import io.github._7isenko.spigotplugin.skills.AbilitiesManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        if (event.getPlayer().getScoreboardTags().contains("herobrine")) {
            AbilitiesManager.getInstance().addAll(event.getPlayer());
        }
    }
}
