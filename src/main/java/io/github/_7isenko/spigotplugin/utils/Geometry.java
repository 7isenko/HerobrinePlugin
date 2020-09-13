package io.github._7isenko.spigotplugin.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class Geometry {
    // Code source: https://bukkit.org/threads/util-getting-circle-locations.175278/
    public static ArrayList<Location> getCircle(Location center, double radius, int amount) {
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

    public static List<Location> getGroundedCircle(Location center, double radius, int amount) {
        ArrayList<Location> locations = getCircle(center.add(0, 1, 0), radius, amount);
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
