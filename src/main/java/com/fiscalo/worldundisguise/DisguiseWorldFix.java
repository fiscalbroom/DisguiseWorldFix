package com.fiscalo.worldundisguise;

import com.fiscalo.worldundisguise.Commands.MainCommand;
import com.fiscalo.worldundisguise.Listeners.WorldChangeEvent;
import de.robingrether.idisguise.api.DisguiseAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisguiseWorldFix extends JavaPlugin {

    public DisguiseWorldFix plugin;
    public DisguiseAPI disguiseAPI;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        registerCommand();
        registerListeners();
        pluginEnabled();

    }

    @Override
    public void onDisable() {

    }

    public void pluginEnabled() {
        getLogger().info("--------------------------------");
        getLogger().info("DisguiseWorldFix enabled");
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("Author: " + getDescription().getAuthors());
        getLogger().info("--------------------------------");
    }

    public void registerCommand() {
        getCommand("disguiseworldfix").setExecutor(new MainCommand(this));
    }

    public void registerListeners(){
        WorldChangeEvent worldChangeEvent = new WorldChangeEvent(this, disguiseAPI);
        getServer().getPluginManager().registerEvents(worldChangeEvent, this);
    }

}