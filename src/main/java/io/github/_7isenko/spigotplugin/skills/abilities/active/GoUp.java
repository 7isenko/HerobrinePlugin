package io.github._7isenko.spigotplugin.skills.abilities.active;

import io.github._7isenko.spigotplugin.skills.abstracts.LocationAbility;
import io.github._7isenko.spigotplugin.utils.Geometry;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Item;
import org.bukkit.util.Vector;

public class GoUp extends LocationAbility {
    public GoUp(Location location) {
        super(location);
    }

    @Override
    public void cast() {
        Geometry.getGroundedRing(location.add(0, 2, 0), 15, 200).forEach(location1 -> {
            location1.getWorld().spawnParticle(Particle.SNOW_SHOVEL, location1, 2, 0, 0.1, 0, 0.05);
        });
        Geometry.getGroundedCircle(location.add(0, 2, 0), 15).forEach(l -> {
            l.getWorld().getNearbyEntities(l, 1, 4, 1).forEach(entity -> {
                if (!entity.getScoreboardTags().contains("herobrine") && !(entity instanceof Item)) {
                    entity.setVelocity(new Vector(0, 10, 0));
                    Geometry.getRing(entity.getLocation(), 1, 30).forEach(loc -> {
                        loc.getWorld().spawnParticle(Particle.SNOWBALL, loc, 1, 0, 0.05, 0);
                    });
                }
            });
        });

    }
}
