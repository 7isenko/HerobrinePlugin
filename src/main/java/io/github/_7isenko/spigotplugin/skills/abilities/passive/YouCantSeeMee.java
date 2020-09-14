package io.github._7isenko.spigotplugin.skills.abilities.passive;

import io.github._7isenko.spigotplugin.skills.abstracts.PassiveAbility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class YouCantSeeMee extends PassiveAbility {
    public YouCantSeeMee(Player player) {
        super(player);
    }

    @Override
    public void cancel() {

    }

    @Override
    public void cast() {
        if (!getEntities(player).isEmpty())
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 1, true, false), true);
    }

    private Set<Player> getEntities(Player player) {
        Set<Player> entities = new HashSet<>();
        Iterator var3 = player.getNearbyEntities(50.0D, 100.0D, 50.0D).iterator();

        while (var3.hasNext()) {
            Entity entity = (Entity) var3.next();
            if (entity instanceof Player && !((LivingEntity)entity).hasPotionEffect(PotionEffectType.BLINDNESS) && isLookingAt(player, (LivingEntity) entity) && isCloser(player, (LivingEntity) entity)) {
                entities.add((Player) entity);
            }
        }

        return entities;
    }

    private boolean isLookingAt(Player player, LivingEntity livingEntity) {
        Location eyes = livingEntity.getEyeLocation();
        Vector toPlayer = player.getEyeLocation().toVector().subtract(eyes.toVector());
        double dot = toPlayer.normalize().dot(eyes.getDirection());
        Vector toPlayer2 = player.getLocation().toVector().subtract(eyes.toVector());
        dot = Math.max(toPlayer2.normalize().dot(eyes.getDirection()), dot);
        return dot > 0.80D;
    }

    private boolean isCloser(Player player, LivingEntity livingEntity) {
        double toBlock = livingEntity.getEyeLocation().distance(livingEntity.getTargetBlock(null, 30).getLocation());
        double toPlayer = Math.min(player.getLocation().distance(livingEntity.getEyeLocation()), player.getEyeLocation().distance(livingEntity.getEyeLocation()));
        return toPlayer <= toBlock;
    }
}
