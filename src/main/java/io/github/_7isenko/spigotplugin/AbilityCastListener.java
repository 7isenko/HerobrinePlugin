package io.github._7isenko.spigotplugin;

import io.github._7isenko.spigotplugin.skills.AbilitiesManager;
import io.github._7isenko.spigotplugin.skills.abilities.active.BlindCircle;
import io.github._7isenko.spigotplugin.skills.abilities.active.GoUp;
import io.github._7isenko.spigotplugin.skills.abilities.active.HoleDown;
import io.github._7isenko.spigotplugin.skills.abilities.active.PlayerSearch;
import io.github._7isenko.spigotplugin.skills.abilities.passive.RedScreen;
import io.github._7isenko.spigotplugin.skills.abilities.passive.SmokyFloor;
import io.github._7isenko.spigotplugin.skills.abilities.passive.YouCantSeeMee;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AbilityCastListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getPlayer().getScoreboardTags().contains("herobrine") && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            switch (event.getMaterial()) {
                case EYE_OF_ENDER:
                    event.setCancelled(true);
                    new BlindCircle(event.getPlayer().getTargetBlock(null, 100).getLocation()).cast();
                    break;
                case FEATHER:
                    event.setCancelled(true);
                    new GoUp(event.getPlayer().getTargetBlock(null, 100).getLocation()).cast();
                    break;
                case END_ROD:
                    event.setCancelled(true);
                    new HoleDown(event.getPlayer().getTargetBlock(null, 100).getLocation()).cast();
                    break;
                case NETHER_STAR:
                    event.setCancelled(true);
                    new PlayerSearch(event.getPlayer()).cast();
                    break;
                case BARRIER:
                    event.setCancelled(true);
                    AbilitiesManager.getInstance().switchPassive(RedScreen.class, event.getPlayer());
                    break;
                case INK_SACK:
                    event.setCancelled(true);
                    AbilitiesManager.getInstance().switchPassive(SmokyFloor.class, event.getPlayer());
                    break;
                case STRING:
                    event.setCancelled(true);
                    AbilitiesManager.getInstance().switchPassive(YouCantSeeMee.class, event.getPlayer());
                    break;

            }

        }
    }
}
