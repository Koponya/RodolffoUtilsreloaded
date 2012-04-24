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
		
		if(command.getName().equalsIgnoreCase("achat")) {
			
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
		
		return false;
	}

}
