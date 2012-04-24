package com.zolli.rodolffoutilsreloaded;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.zolli.rodolffoutilsreloaded.listeners.commandExecutor;
import com.zolli.rodolffoutilsreloaded.listeners.playerListener;

public class rodolffoUtilsReloaded extends JavaPlugin {
	
	private PluginManager pm;
	public Economy econ = null;
	public Permission perm = null;
	public Configuration config;
	public Logger log;
	private PluginDescriptionFile pdfile;
	private CommandExecutor commandExec;
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perm = rsp.getProvider();
        return perm != null;
    }
	
	private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        econ = rsp.getProvider();
        return econ != null;
    }
	
	public void onLoad() {
		
		log = Logger.getLogger("Minecraft");
		config = getConfig();
		pdfile = getDescription();
		pm = getServer().getPluginManager();
		commandExec = new commandExecutor(this);
		
		config.options().copyDefaults(true);
		
	}
	
	public void onEnable() {
		
		final playerListener playerListener = new playerListener(this);
		pm.registerEvents(playerListener, this);
		
		getCommand("achat").setExecutor(commandExec);
		getCommand("fakechat").setExecutor(commandExec);
		
		if(pm.isPluginEnabled("Vault")) {
			setupPermissions();
			log.info("[" + pdfile.getName() + "] Hooked with " + perm.getName() + "!");
			setupEconomy();
			log.info("[" + pdfile.getName() + "] Hooked with " + econ.getName() + "!");
		} else {
			log.warning("[" + pdfile.getName() + "] Vault not found! Disabling plugin...");
			pm.disablePlugin(this);
		}
		
		log.info("[" + pdfile.getName() + "] Version: " + pdfile.getVersion() + " Sucessfully enabled!");
		
	}
	
	public void onDisable() {
		
		saveConfig();
		log.info("[" + pdfile.getName() + "] Version: " + pdfile.getVersion() + " Sucessfully disabled!");
		
	}
	
}
