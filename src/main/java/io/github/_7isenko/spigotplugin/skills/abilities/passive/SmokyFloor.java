package io.github._7isenko.spigotplugin.skills.abilities.passive;

import io.github._7isenko.spigotplugin.skills.abstracts.PassiveAbility;
import io.github._7isenko.spigotplugin.utils.Geometry;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Random;

public class SmokyFloor extends PassiveAbility {
    private final Random random = new Random();

    public SmokyFloor(Player player) {
        super(player);
    }

    @Override
    public void cancel() {
        // nothing
    }

    @Override
    public void cast() {
        Geometry.getGroundedCircle(player.getLocation(), 30).forEach(location -> {
            if (random.nextFloat() > 0.99)
                location.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, location, 1, 1, 0.1, 1, 0.005);
        });
    }
}
