package com.zolli.rodolffoutilsreloaded.listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.zolli.rodolffoutilsreloaded.rodolffoUtilsReloaded;

public class commandExecutor implements CommandExecutor {

	
	private rodolffoUtilsReloaded plugin;
	public commandExecutor(rodolffoUtilsReloaded instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		
		return false;
	}

}
