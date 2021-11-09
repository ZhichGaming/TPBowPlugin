package me.zhichgaming.tpbowplugin.commands;

import me.zhichgaming.tpbowplugin.TPBowPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.zhichgaming.tpbowplugin.SendMessage.*;



public class TPBowCommand implements CommandExecutor {

    static TPBowPlugin plugin;

    public TPBowCommand(TPBowPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args.length == 0) {
                if (player.hasPermission("tpbow.bow")) {
                    player.getInventory().addItem(teleportBow());
                    addArrow(player);

                } else {
                    sendMessage(player, "You do not have the permissions necessary to spawn a tpbow.");
                }
            }

            if (args.length == 1) {

                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    if (player.hasPermission("tpbow.bow.others")) {

                        target.getInventory().addItem(teleportBow());
                        addArrow(target);

                        if (plugin.getConfig().getString("giveMessage") != null && plugin.getConfig().getString("giveMessage") != "") {
                            sendMessage(player, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("giveMessage").replace("%player%", target.getDisplayName())));
                        }
                    } else {
                        sendMessage(player, "You do not have the permissions necessary to give others a tpbow.");
                    }
                } else {
                    sendMessage(player, "This player is not online.");
                }
            }

        } else {
            sendMessage("Only a player can execute this command.");
            System.out.println(teleportBow());
        }

        return true;
    }

    public static ItemStack teleportBow() {

        ItemStack teleportBow = new ItemStack(Material.BOW);
        teleportBow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);

        ItemMeta meta = teleportBow.getItemMeta();
        meta.spigot().setUnbreakable(true);

        if (plugin.getConfig().getString("bowName") != null && plugin.getConfig().getString("bowName") != "") {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("bowName")));
        } else {
            meta.setDisplayName(ChatColor.GRAY + "[TeleportBow]" + ChatColor.RESET);
        }

        ArrayList<String> lore = new ArrayList<>();

        if (plugin.getConfig().getString("description") != null && plugin.getConfig().getString("description") != "") {
            List<String> description = new ArrayList<>(plugin.getConfig().getStringList("description"));

            for (int i = 0; i < description.size(); i++) {
                description.set(i, ChatColor.translateAlternateColorCodes('&', description.get(i)));
            }
            lore.addAll(description);
        }

        meta.setLore(lore);
        teleportBow.setItemMeta(meta);

        return teleportBow;
    }

    public static void addArrow(Player player) {

        ItemStack arrow = new ItemStack(Material.ARROW);

        if (!(player.getInventory().contains(arrow))) {
            for (int i = 9; i <= 35; i++) {
                if (player.getInventory().getItem(i) == null) {
                    player.getInventory().setItem(i, arrow);
                    break;
                } else if (i == 35) {
                    for (int j = 8; j >= 0; j--) {
                        System.out.println(j);
                        if (player.getInventory().getItem(j) == null) {
                            player.getInventory().setItem(j, arrow);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

}
