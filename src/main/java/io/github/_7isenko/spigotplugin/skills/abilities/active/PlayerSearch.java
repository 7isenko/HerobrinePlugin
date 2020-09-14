package io.github._7isenko.spigotplugin.skills.abilities.active;

import io.github._7isenko.spigotplugin.HerobrinePlugin;
import io.github._7isenko.spigotplugin.skills.abstracts.EntityAbility;
import org.bukkit.GameMode;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class PlayerSearch extends EntityAbility {
    private final int seconds = 20;

    public PlayerSearch(Entity entity) {
        super(entity);
    }

    @Override
    public void cast() {
        int i = 0;
        for (int j = 5; i < 10 && j <= 150; j += 5)
            for (Entity e : entity.getNearbyEntities(j, j, j)) {
                if (e instanceof Player && !e.isDead() && ((Player) e).getGameMode() != GameMode.SPECTATOR && ((Player) e).getGameMode() != GameMode.CREATIVE && !e.getScoreboardTags().contains("herobrine")) {
                    try {
                        ((Player)e).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, seconds*20, 1, true, false));
                        i++;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
    }
}
