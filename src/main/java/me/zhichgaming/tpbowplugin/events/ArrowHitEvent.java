package me.zhichgaming.tpbowplugin.events;

import me.zhichgaming.tpbowplugin.TPBowPlugin;
import me.zhichgaming.tpbowplugin.commands.TPBowCommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import static me.zhichgaming.tpbowplugin.SendMessage.*;

public class ArrowHitEvent implements Listener {

    TPBowPlugin plugin;

    @EventHandler
    public void onArrowLand(ProjectileHitEvent e) {

        if (e.getEntity() instanceof Arrow) {

            Player player = (Player) e.getEntity().getShooter();

            if (player.getItemInHand().equals(TPBowCommand.teleportBow())) {

                Location location = e.getEntity().getLocation();
                location.setYaw(player.getLocation().getYaw());
                location.setPitch(player.getLocation().getPitch());

                e.getEntity().remove();

                String blockLocation = location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ();
                player.teleport(location);
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);

                if (plugin.getConfig().getString("teleportMessage") != null && !(plugin.getConfig().getString("teleportMessage").equals(""))) {
                    sendMessage(player, ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("teleportMessage").replace("%location%", blockLocation)));
                }
            }
        }
    }

    public ArrowHitEvent(TPBowPlugin plugin) {
        this.plugin = plugin;
    }
}
