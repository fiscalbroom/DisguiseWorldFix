package com.fiscalo.worldundisguise.Commands;

import com.fiscalo.worldundisguise.DisguiseWorldFix;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.IOException;

public class MainCommand implements CommandExecutor {

    private final DisguiseWorldFix plugin;

    public MainCommand(DisguiseWorldFix plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            if(!(sender.hasPermission("disguiseworldfix.admin"))){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("no-permission")));
            }
            if(sender.hasPermission("disguiseworldfix.admin")){
                try {
                    plugin.getConfig().save("config.yml");
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("reload-message")));
                    plugin.getConfig().load("config.yml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InvalidConfigurationException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return true;
    }
}
