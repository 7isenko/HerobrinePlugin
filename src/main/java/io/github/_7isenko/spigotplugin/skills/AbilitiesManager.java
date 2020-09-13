package io.github._7isenko.spigotplugin.skills;

import io.github._7isenko.spigotplugin.skills.abstracts.Passive;

import java.util.*;

public class AbilitiesManager {
    private final Set<Passive> passives;

    public AbilitiesManager() {
        passives = new HashSet<>();
    }

    /**
     * @param passive - instance of player's passive ability
     * @return true if it is enabled and false if disabled
     */
    public boolean switchPassive(Passive passive) {
        if (passives.contains(passive)) {
            passive.cancel();
            passives.remove(passive);
            return false;
        } else {
            passives.add(passive);
            return true;
        }
    }

}
