package io.github._7isenko.spigotplugin.skills;

import io.github._7isenko.spigotplugin.HerobrinePlugin;
import io.github._7isenko.spigotplugin.skills.abilities.passive.RedScreen;
import io.github._7isenko.spigotplugin.skills.abilities.passive.SmokyFloor;
import io.github._7isenko.spigotplugin.skills.abilities.passive.YouCantSeeMee;
import io.github._7isenko.spigotplugin.skills.abstracts.PassiveAbility;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class AbilitiesManager {
    private static AbilitiesManager instance;
    private final HashMap<PassiveAbility, Player> passives;
    private final BukkitRunnable task;

    private AbilitiesManager() {
        passives = new HashMap<>();
        task = new PassivesRunnable();
        task.runTaskTimer(HerobrinePlugin.plugin, 0, 1);
    }

    public void addPassive(Class<? extends PassiveAbility> passiveClass, Player player) {
        try {
            passives.put(passiveClass.getDeclaredConstructor(Player.class).newInstance(player), player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean removePassive(Class<? extends PassiveAbility> passiveClass, Player player) {
        for (Map.Entry<PassiveAbility, Player> entry : passives.entrySet()) {
            if (player.equals(entry.getValue()) && entry.getKey().getClass().equals(passiveClass)) {
                passives.remove(entry.getKey());
                return true;
            }
        }
        return false;
    }

    /**
     * @param passiveClass - class of the ability
     * @param player       - player to add passive
     * @return false if removed and true if enabled
     */
    public boolean switchPassive(Class<? extends PassiveAbility> passiveClass, Player player) {
        if (removePassive(passiveClass, player)) {
            player.sendMessage("Пассивная способность " + passiveClass.getSimpleName() + " отключена");
            return false;
        } else {
            addPassive(passiveClass, player);
            player.sendMessage("Пассивная способность " + passiveClass.getSimpleName() + " включена");
            return true;
        }
    }

    public void removeAll(Player player) {
        passives.entrySet().removeIf(passiveAbilityPlayerEntry -> passiveAbilityPlayerEntry.getValue().equals(player));
    }

    public static AbilitiesManager getInstance() {
        if (instance == null)
            instance = new AbilitiesManager();
        return instance;
    }

    public void addAll(Player player) {
        addPassive(RedScreen.class, player);
        addPassive(SmokyFloor.class, player);
        addPassive(YouCantSeeMee.class, player);
    }

    public class PassivesRunnable extends BukkitRunnable {
        @Override
        public void run() {
            passives.keySet().forEach(PassiveAbility::cast);
        }
    }
}
