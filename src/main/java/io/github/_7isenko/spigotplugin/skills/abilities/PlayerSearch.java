package io.github._7isenko.spigotplugin.skills.abilities;

import io.github._7isenko.spigotplugin.HerobrinePlugin;
import io.github._7isenko.spigotplugin.skills.abstracts.EntityAbility;
import io.github._7isenko.spigotplugin.utils.Laser;
import org.bukkit.GameMode;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class PlayerSearch extends EntityAbility {
    private BukkitRunnable task;
    private final HashMap<Player, Laser> laserMap;
    private final int seconds = 20;

    public PlayerSearch(Entity entity) {
        super(entity);
        laserMap = new HashMap<>();
    }

    @Override
    public void cast() {
        EnderCrystal crystal = entity.getWorld().spawn(entity.getLocation(), EnderCrystal.class);
        crystal.setInvulnerable(true);
        int i = 0;
        for (int j = 5; i < 10 && j <= 150; j += 5)
            for (Entity e : crystal.getNearbyEntities(j, j, j)) {
                if (e instanceof Player && !e.isDead() && ((Player) e).getGameMode() != GameMode.SPECTATOR && ((Player) e).getGameMode() != GameMode.CREATIVE && !e.getScoreboardTags().contains("herobrine")) {
                    try {
                        Laser laser = new Laser(crystal.getLocation(), e.getLocation(), seconds, 5 + (int) Math.ceil(crystal.getLocation().distance(e.getLocation())));
                        laser.start(HerobrinePlugin.plugin);
                        laserMap.put((Player) e, laser);
                        i++;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (!crystal.isValid()) {
                    stop();
                    try {
                        laserMap.values().forEach(Laser::stop);
                    } catch (Exception ex) {
                        // ignore
                    }
                    return;
                }
                laserMap.forEach((e, l) -> {
                    try {
                        if (e != null && !e.isDead() && e.isOnline() && e.getGameMode() != GameMode.SPECTATOR && e.getGameMode() != GameMode.CREATIVE) {
                            l.moveEnd(e.getLocation().clone());
                        } else {
                            if (l.isStarted())
                                l.stop();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
        };
        task.runTaskTimer(HerobrinePlugin.plugin, 5, 5);
        new BukkitRunnable() {
            @Override
            public void run() {
                stop();
                crystal.remove();
            }
        }.runTaskLaterAsynchronously(HerobrinePlugin.plugin, seconds * 20);
    }

    private void stop() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}
