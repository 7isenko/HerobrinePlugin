package io.github._7isenko.spigotplugin.skills.abilities.active;

import io.github._7isenko.spigotplugin.HerobrinePlugin;
import io.github._7isenko.spigotplugin.skills.abstracts.LocationAbility;
import io.github._7isenko.spigotplugin.utils.Geometry;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class BlindCircle extends LocationAbility {
    private static final Map<LivingEntity, Integer> affected = new HashMap<>();
    private static final BukkitRunnable task = new BukkitRunnable() {
        @Override
        public void run() {
            Iterator<Map.Entry<LivingEntity, Integer>> iterator = affected.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<LivingEntity, Integer> entry = iterator.next();
                if (entry.getKey().isValid()) {
                    entry.getKey().getWorld().spawnParticle(Particle.SMOKE_NORMAL, entry.getKey().getEyeLocation().add(0, 1, 0), 1, 0.1, 0.1, 0.1, 0.002);
                    int val = entry.getValue() - 1;
                    if (val <= 0) iterator.remove();
                    else entry.setValue(val);
                } else {
                    iterator.remove();
                }

            }
        }
    };

    static {
        task.runTaskTimer(HerobrinePlugin.plugin, 0, 1);
    }

    public BlindCircle(Location location) {
        super(location);
    }

    @Override
    public void cast() {
        Geometry.getGroundedRing(location.add(0, 1, 0), 15, 200).forEach(location1 -> {
            location1.getWorld().spawnParticle(Particle.DRAGON_BREATH, location1, 2, 0, 0.1, 0, 0.05);
        });
        Geometry.getGroundedCircle(location.add(0, 2, 0), 15).forEach(l -> {
            l.getWorld().getNearbyEntities(l, 1, 1, 1).forEach(entity -> {
                if (entity instanceof LivingEntity && !entity.getScoreboardTags().contains("herobrine")) {
                    ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 500, 1, true));
                    affected.put((LivingEntity) entity, 500);
                    Geometry.getRing(entity.getLocation().add(0, 1, 0), 2, 50).forEach(loc -> {
                        loc.getWorld().spawnParticle(Particle.DRIP_LAVA, loc, 1, 0, 0.05, 0);
                    });
                }
            });
        });


    }
}
