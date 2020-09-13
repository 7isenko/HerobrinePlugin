package io.github._7isenko.spigotplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        if (event.getPlayer().getScoreboardTags().contains("herobrine")){
            // TODO: business
        }
    }
}
