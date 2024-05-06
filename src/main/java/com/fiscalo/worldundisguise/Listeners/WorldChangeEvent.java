package com.fiscalo.worldundisguise.Listeners;

import com.fiscalo.worldundisguise.DisguiseWorldFix;
import de.robingrether.idisguise.api.DisguiseAPI;
import de.robingrether.idisguise.api.UndisguiseEvent;
import de.robingrether.idisguise.disguise.Disguise;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;
public class WorldChangeEvent implements Listener {

    private final DisguiseWorldFix plugin;
    private final DisguiseAPI disguiseAPI;

    public WorldChangeEvent(DisguiseWorldFix plugin, DisguiseAPI disguiseAPI) {
        this.plugin = plugin;
        this.disguiseAPI = disguiseAPI;
    }

    @EventHandler
    public void onWorldChange(PlayerTeleportEvent e) {
        Player player = e.getPlayer();
        Location toLocation = e.getTo();
        World toWorld = toLocation.getWorld();
        String worldName = toWorld.getName();
        String disableMessage = plugin.getConfig().getString("disabled-message");

        if (isWorldDisabled(worldName)) {
            if (isPlayerDisguised(player)) {
                disguiseAPI.undisguise(player);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', disableMessage));
            }
        }
    }

    private boolean isWorldDisabled(String worldName){
        List<String> disabledWorlds = plugin.getConfig().getStringList("disabled-worlds");

        for(String disabledWorld : disabledWorlds){
            if(disabledWorld.equalsIgnoreCase(worldName)){
                return true;
            }
        }

        return false;
    }

    private boolean isPlayerDisguised(Player player){
        Disguise disguise = disguiseAPI.getDisguise(player);
        return disguise != null;
    }

}
