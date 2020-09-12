package io.github._7isenko.spigotplugin.skills.abstracts;

import org.bukkit.entity.Entity;

public abstract class EntityAbility implements Ability {
    protected final Entity entity;

    public EntityAbility(Entity entity) {
        this.entity = entity;
    }
}
