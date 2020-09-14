package io.github._7isenko.spigotplugin.skills.abilities.active;

import io.github._7isenko.spigotplugin.HerobrinePlugin;
import io.github._7isenko.spigotplugin.skills.abstracts.LocationAbility;
import io.github._7isenko.spigotplugin.utils.Geometry;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class HoleDown extends LocationAbility {
    public HoleDown(Location location) {
        super(location);
    }

    @Override
    public void cast() {
        location.add(0, 3, 0);
        for (int i = location.getBlockY(); i >= 0; i--) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    HashMap<Block, Material> blocks = new HashMap<>();
                    Geometry.getCircleBlocks(location.add(0, -1, 0), 5).forEach(block -> {
                        blocks.put(block, block.getType());
                        block.setType(Material.AIR);
                    });
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            blocks.forEach((Block::setType));
                        }
                    }.runTaskLater(HerobrinePlugin.plugin, 40);

                }
            }.runTaskLater(HerobrinePlugin.plugin, 2 * (location.getBlockY() - i));
        }
    }

}
