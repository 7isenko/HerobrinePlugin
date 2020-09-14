package io.github._7isenko.spigotplugin.skills.abilities.passive;

import io.github._7isenko.spigotplugin.skills.abstracts.PassiveAbility;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_12_R1.WorldBorder;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class RedScreen extends PassiveAbility {
    public RedScreen(Player herobrine) {
        super(herobrine);
    }

    @Override
    public void cast() {
        player.getNearbyEntities(40, 40, 40).forEach(p -> {
            if (p instanceof Player) {
                if (player.getLocation().distance(p.getLocation()) < 16D)
                    addBarrier((Player) p);
                else removeBarrier((Player) p);
            }
        });
    }

    public void cancel() {
        player.getNearbyEntities(100, 100, 100).forEach(p -> {
            if (p instanceof Player) {
                removeBarrier((Player) p);
            }
        });
    }


    private void addBarrier(Player p) {
        CraftPlayer cp = (CraftPlayer) p;
        // to add
        WorldBorder w = new WorldBorder();
        w.setSize(1);
        w.setCenter(p.getLocation().getX() + 10_000, p.getLocation().getZ() + 10_000);
        cp.getHandle().playerConnection.sendPacket(new PacketPlayOutWorldBorder(w, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE));
    }

    private void removeBarrier(Player p) {
        CraftPlayer cp = (CraftPlayer) p;
        // to remove
        WorldBorder ww = new WorldBorder();
        ww.setSize(30_000_000);
        ww.setCenter(p.getLocation().getX(), p.getLocation().getZ());
        cp.getHandle().playerConnection.sendPacket(new PacketPlayOutWorldBorder(ww, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE));

    }
}
