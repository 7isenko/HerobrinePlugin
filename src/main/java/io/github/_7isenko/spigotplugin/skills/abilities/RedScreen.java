package io.github._7isenko.spigotplugin.skills.abilities;

import io.github._7isenko.spigotplugin.skills.abstracts.EntityAbility;
import io.github._7isenko.spigotplugin.skills.abstracts.Passive;
import io.github._7isenko.spigotplugin.skills.abstracts.PlayerAbility;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_12_R1.WorldBorder;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class RedScreen extends EntityAbility implements Passive {
    public RedScreen(Entity herobrine) {
        super(herobrine);
    }

    @Override
    public void cast() {
        entity.getNearbyEntities(16, 16, 16).forEach(p -> {
            if (p instanceof Player) {
                sendWorldBorderPacket((Player) p, (int) entity.getLocation().distance(p.getLocation()));
            }
        });
    }

    public void cancel() {
        entity.getNearbyEntities(30, 30, 30).forEach(p -> {
            if (p instanceof Player) {
                sendWorldBorderPacket((Player) p, 1000);
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
}
