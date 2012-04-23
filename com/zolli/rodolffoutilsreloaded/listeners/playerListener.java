package com.zolli.rodolffoutilsreloaded.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.zolli.rodolffoutilsreloaded.rodolffoUtilsReloaded;

public class playerListener implements Listener {
	
	private rodolffoUtilsReloaded plugin;
	public playerListener(rodolffoUtilsReloaded instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void overrideBukkitDefaults(PlayerCommandPreprocessEvent e) {
		
		String command = e.getMessage();
		Player commandSender = e.getPlayer();
		
		if((!commandSender.isOp() || !plugin.perm.has(commandSender, "rur.allowSeeBukkitVer")) && (command.equalsIgnoreCase("/ver") || command.equalsIgnoreCase("/version"))) {
			commandSender.sendMessage(plugin.config.getString("fakePluginsList"));
			e.setCancelled(true);
		}
		
		if((!commandSender.isOp() || !plugin.perm.has(commandSender, "rur.allowSeeRealPlugins")) && (command.equalsIgnoreCase("/pl") || command.equalsIgnoreCase("/plugins"))) {
			commandSender.sendMessage(plugin.config.getString("fakeBukkitVerString"));
			e.setCancelled(true);
		}
		
		if(command.equalsIgnoreCase("/reload")) {
			commandSender.sendMessage(ChatColor.DARK_RED + "Ne használd ezt a prancsot, inkább indítsd újra a szervert!");
			e.setCancelled(true);
		}
		
	}
	
}
