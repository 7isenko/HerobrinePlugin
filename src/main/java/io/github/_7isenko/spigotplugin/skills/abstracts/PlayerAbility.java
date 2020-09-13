package io.github._7isenko.spigotplugin.skills.abstracts;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public abstract class PlayerAbility implements Ability {
    protected final Player player;

    public PlayerAbility(Player player) {
        this.player = player;
    }

}
