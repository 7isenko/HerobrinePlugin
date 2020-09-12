package io.github._7isenko.spigotplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class HerobrineCommand implements CommandExecutor {
    private final String tag = "herobrine";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) return false;

        Player player = null;
        if (args.length == 0) {
            if (sender instanceof Player)
                player = (Player) sender;
            else {
                sender.sendMessage("Добавьте никнейм игрока, как аргумент");
                return true;
            }
        }
        if (args.length == 1) {
            player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage("Такого игрока нет на сервере");
                return true;
            }
        }

        if (player.getScoreboardTags().contains(tag)) {
            player.removeScoreboardTag(tag);
            if (player != sender)
                sender.sendMessage(player.getName() + " перестал быть херобрином");
            player.sendMessage("Вы больше не херобрин");
            // TODO: смена скина
        } else {
            player.addScoreboardTag(tag);
            if (player != sender)
                sender.sendMessage(player.getName() + " стал херобрином");
            player.sendMessage("Теперь вы херобрин");
            // TODO: смена скина
        }

        return true;
    }
}
