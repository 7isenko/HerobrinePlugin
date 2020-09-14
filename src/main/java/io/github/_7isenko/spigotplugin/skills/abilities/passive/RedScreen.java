package io.github._7isenko.spigotplugin.skills.abilities.passive;

import io.github._7isenko.spigotplugin.skills.abstracts.PassiveAbility;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_12_R1.WorldBorder;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class RedScreen extends PassiveAbility {
    public RedScreen(Player herobrine) {
        super(herobrine);
    }

    @Override
    public void cast() {
        player.getNearbyEntities(32, 32, 32).forEach(p -> {
            if (p instanceof Player) {
                if (player.getLocation().distance(p.getLocation()) < 15)
                    sendWorldBorderPacket((Player) p, 300_000);
                else sendWorldBorderPacket((Player) p, 1);
            }
        });
    }

    public void cancel() {
        player.getNearbyEntities(100, 100, 100).forEach(p -> {
            if (p instanceof Player) {
                sendWorldBorderPacket((Player) p, 1);
            }
        });
    }

    protected void sendWorldBorderPacket(Player player, int warningBlocks) {
        EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
        WorldBorder playerWorldBorder = nmsPlayer.world.getWorldBorder();
        PacketPlayOutWorldBorder worldBorder = new PacketPlayOutWorldBorder(playerWorldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_WARNING_BLOCKS);
        try {
            Field field = worldBorder.getClass().getDeclaredField("i");
            field.setAccessible(true);
            field.setInt(worldBorder, warningBlocks);
            field.setAccessible(!field.isAccessible());
        } catch (Exception e) {
            e.printStackTrace();
        }
        nmsPlayer.playerConnection.sendPacket(worldBorder);
    }

    /*
        CraftPlayer cp = (CraftPlayer) p;

        // to add
        WorldBorder w = new WorldBorder();
        w.setSize(1);
        w.setCenter(p.getLocation().getX() + 10_000, p.getLocation().getZ() + 10_000);
        cp.getHandle().playerConnection.sendPacket(new PacketPlayOutWorldBorder(w, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE));

        // to remove
        WorldBorder ww = new WorldBorder();
        ww.setSize(30_000_000);
        ww.setCenter(p.getLocation().getX(), p.getLocation().getZ());
        cp.getHandle().playerConnection.sendPacket(new PacketPlayOutWorldBorder(ww, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE));
     */
}
