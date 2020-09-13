package io.github._7isenko.spigotplugin.skills.abilities.passive;

import io.github._7isenko.spigotplugin.skills.abstracts.PassiveAbility;
import io.github._7isenko.spigotplugin.utils.Geometry;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SmokyFloor extends PassiveAbility {

    public SmokyFloor(Player player) {
        super(player);
    }

    @Override
    public void cancel() {
        // nothing
    }

    @Override
    public void cast() {
        Geometry.getGroundedCircle(player.getLocation(), 10, 100).forEach(location -> {
            location.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location, 2, 1, 1, 1, 0.005);
        });
    }
}
