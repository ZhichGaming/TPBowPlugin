package me.zhichgaming.tpbowplugin;

import me.zhichgaming.tpbowplugin.commands.TPBowCommand;
import me.zhichgaming.tpbowplugin.events.ArrowHitEvent;
import me.zhichgaming.tpbowplugin.events.JoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TPBowPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new ArrowHitEvent(this), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        getCommand("tpbow").setExecutor(new TPBowCommand(this));
    }
}
