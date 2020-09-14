package io.github._7isenko.spigotplugin;

import io.github._7isenko.spigotplugin.skills.abilities.active.BlindCircle;
import io.github._7isenko.spigotplugin.skills.abilities.active.HoleDown;
import io.github._7isenko.spigotplugin.skills.abilities.active.PlayerSearch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AbilityCastListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            switch (event.getMaterial()) {
                case EYE_OF_ENDER:
                    event.setCancelled(true);
                    new BlindCircle(event.getPlayer().getTargetBlock(null, 100).getLocation()).cast();
                    break;
                case STRING:
                    event.setCancelled(true);
                    break;
                case END_ROD:
                    event.setCancelled(true);
                    new HoleDown(event.getPlayer().getTargetBlock(null, 100).getLocation()).cast();
                    break;
                case NETHER_STAR:
                    event.setCancelled(true);
                    new PlayerSearch(event.getPlayer()).cast();
                    break;
                default:

            }

        }
    }
}
