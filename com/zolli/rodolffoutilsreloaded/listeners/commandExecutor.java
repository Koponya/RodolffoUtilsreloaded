package com.zolli.rodolffoutilsreloaded.listeners;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.zolli.rodolffoutilsreloaded.rodolffoUtilsReloaded;
import com.zolli.rodolffoutilsreloaded.utils.textUtils;

public class commandExecutor implements CommandExecutor {

	
	private rodolffoUtilsReloaded plugin;
	private textUtils tu = new textUtils();
	public commandExecutor(rodolffoUtilsReloaded instance) {
		plugin = instance;
	}
	
	
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if(command.getName().equalsIgnoreCase("achat") || command.getName().equalsIgnoreCase("ac")) {
			
			if(args.length < 1) {
				return false;
			}
			
			if(sender.isOp() || plugin.perm.has(sender, "rur.adminChat")) {
				
				String msgFormat = plugin.config.getString("adminChatFormat").replace("(NAME)", sender.getName());
				String param = tu.arrayToString(args, 0);
				
				for(Player p : Bukkit.getServer().getOnlinePlayers()) {
					
					if(p.isOp() || plugin.perm.has(p, "rur.adminchat")) {
						
						p.sendMessage(msgFormat.replace("(MSG)", param).toString());
						
					}
					
				}
				
			} else {
				
				return false;
				
			}
			
			return true;
		}
		
		if(command.getName().equalsIgnoreCase("fakechat") || command.getName().equalsIgnoreCase("/fc")) {
			
			Player p = null;
			String message = tu.arrayToString(args, 1);
			
			if(sender.isOp() || plugin.perm.has(sender, "rur.fakeChat")) {
			
				if(args.length < 2) {
					return false;
				}
				
				if(Bukkit.matchPlayer(args[0]) == null) {
					sender.sendMessage("Nem található játékos ezen a néven!");
				} else {
					p = plugin.getServer().getPlayer(args[0]);
				}
				
				if(!plugin.perm.has(p, "rur.fakeChat.exempt") && p != null) {
				
					for(Player pl : Bukkit.getServer().getOnlinePlayers()) {
						
						if(plugin.perm.has(pl, "rur.fakeChat")) {
							
							pl.sendMessage(pl.getName().toString() + " írta:");
							
						}
						
					}
					
					p.chat(message);
				
				} else {
					sender.sendMessage("Nem beszélhetsz ezen játékos nevében!");
				}
				
			} else {
				return false;
			}
			
			return true;
			
		}
		
		return false;
	}

}
