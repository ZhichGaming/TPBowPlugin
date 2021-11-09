package me.zhichgaming.tpbowplugin.events;

import me.zhichgaming.tpbowplugin.TPBowPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.zhichgaming.tpbowplugin.commands.TPBowCommand.addArrow;
import static me.zhichgaming.tpbowplugin.commands.TPBowCommand.teleportBow;

public class JoinEvent implements Listener {

    TPBowPlugin plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        if (plugin.getConfig().getString("giveBowOnJoin") != null) {
            if (plugin.getConfig().getBoolean("giveBowOnJoin")) {
                if (player.hasPermission("tpbow.join")) {
                    if (!(player.getInventory().contains(teleportBow()))) {
                        player.getInventory().addItem(teleportBow());
                        addArrow(player);
                    }
                }
            }
        }
    }

    public JoinEvent(TPBowPlugin plugin) {
        this.plugin = plugin;
    }
}
