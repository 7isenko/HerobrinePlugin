package io.github._7isenko.spigotplugin.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.*;

public class Geometry {

    public static Set<Block> getCircleBlocks(Location center, double radius) {
        Set<Block> blocks = new HashSet<>();
        getCircle(center, radius).forEach(location -> {
            blocks.add(location.getBlock());
        });
        return blocks;
    }

    public static ArrayList<Location> getCircle(Location center, double radius) {
        ArrayList<Location> locations = new ArrayList<>();
        for (int r = 0; r < radius; r++) {
            for (int i = 0; i < 4*3*r; i++) {
                double y = center.getY();
                double x = center.getX() + Math.sin(i) * r;
                double z = center.getZ() + Math.cos(i) * r;
                Location loc = new Location(center.getWorld(), x, y, z);
                locations.add(loc);
            }
        }
        return locations;
    }

    public static ArrayList<Location> getGroundedCircle(Location center, double radius) {
        ArrayList<Location> locations = getCircle(center.add(0, 1, 0), radius);
        locations.forEach(Geometry::ground);
        return locations;
    }


    // Code source: https://bukkit.org/threads/util-getting-circle-locations.175278/
    public static ArrayList<Location> getRing(Location center, double radius, int amount) {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        ArrayList<Location> locations = new ArrayList<Location>();
        for (int i = 0; i < amount; i++) {
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }

    public static List<Location> getGroundedRing(Location center, double radius, int amount) {
        ArrayList<Location> locations = getRing(center.add(0, 1, 0), radius, amount);
        locations.forEach(Geometry::ground);
        return locations;
    }

    public static void ground(Location location) {
        if (location.getY() > 0 && location.clone().add(0, -0.1, 0).getBlock().getType() == Material.AIR) {
            location.add(0, -1, 0);
            ground(location);
        }
    }
}
