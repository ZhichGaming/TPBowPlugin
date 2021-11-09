package me.zhichgaming.tpbowplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class SendMessage {

    public static String prefix = ChatColor.GRAY + "[" + ChatColor.YELLOW + "TPBow" + ChatColor.GRAY + "] " + ChatColor.RESET;

    public static void sendMessage(Player player, Object message) {
        player.sendMessage(prefix + message);
    }

    public static void sendMessage(Object message) {
        getServer().getConsoleSender().sendMessage(prefix + message);
    }
}
