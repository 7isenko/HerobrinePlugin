package io.github._7isenko.spigotplugin.skills.abstracts;

import org.bukkit.entity.Player;

public abstract class PassiveAbility extends PlayerAbility {
    public PassiveAbility(Player player) {
        super(player);
    }

    public abstract void cancel();
}
