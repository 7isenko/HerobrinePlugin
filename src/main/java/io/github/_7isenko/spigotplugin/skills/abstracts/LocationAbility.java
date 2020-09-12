package io.github._7isenko.spigotplugin.skills.abstracts;

import org.bukkit.Location;

public abstract class LocationAbility implements Ability{
    protected Location location;

    public LocationAbility(Location location) {
        this.location = location;
    }
}
