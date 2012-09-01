package com.zolli.rodolffoutilsreloaded.utils;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.earth2me.essentials.Essentials;
import com.iCo6.iConomy;
import com.iCo6.system.Accounts;
import com.zolli.rodolffoutilsreloaded.rodolffoUtilsReloaded;

import cosine.boseconomy.BOSEconomy;

public class econHandler {
	
	rodolffoUtilsReloaded plugin;
	Essentials essEco;
	iConomy iCon;
	BOSEconomy bosEcon;
	public static net.milkbowl.vault.economy.Economy vaultEconomy;
	
	private Accounts iConAccounts;
	Econs handler = null;
	
	public econHandler(rodolffoUtilsReloaded plugin) {
		this.plugin = plugin;
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		if(pm.isPluginEnabled("iConomy")) {
			Plugin iConPlugin = pm.getPlugin("iConomy");
			
			if(iConPlugin.isEnabled() && iConPlugin.getClass().getName().equals("com.iCo6.iConomy")) {
				handler = Econs.iCon;
				iCon = (iConomy) iConPlugin;
				iConAccounts = new Accounts();
				plugin.log.info(plugin.logPrefix + "Using iConomy6 for economy support!");
				return;
			}
		} else if(pm.isPluginEnabled("BOSEconomy")) {
			Plugin bosEconPlugin = pm.getPlugin("BOSEconomy");
			
			if(bosEconPlugin.isEnabled() && bosEconPlugin.getDescription().getVersion().startsWith("0.7")) {
				handler = Econs.boseCon;
				bosEcon = (BOSEconomy) bosEconPlugin;
				plugin.log.info(plugin.logPrefix + "Using BOSEconomy for economy support!");
				return;
			}
		} else if(pm.isPluginEnabled("Essentials")) {
			Plugin essEcoPlugin = pm.getPlugin("Essentials");
			
			if(essEcoPlugin.isEnabled()) {
				handler = Econs.eEco;
				essEco = (Essentials) essEcoPlugin;
				plugin.log.info(plugin.logPrefix + "Using EssentialsEco for economy support!");
				return;
			}
		} else if(pm.isPluginEnabled("Vault")) {
			RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
			
			if(economyProvider != null) {
				handler = Econs.Vault;
				vaultEconomy = economyProvider.getProvider();
				plugin.log.info(plugin.logPrefix + "Using Vault for Economy Support! Vault hooked with: " + vaultEconomy.getName());
				return;
			}
		}
	}
	
	public void deposit(Player pl, int amount) {
		String playerName = pl.getName();
		
		switch(handler) {
			case iCon:
				iConAccounts.get(playerName).getHoldings().add(amount);
				break;
			case boseCon:
				double currentMoney = bosEcon.getPlayerMoneyDouble(playerName);
				double newAmount = currentMoney + amount;
				bosEcon.setPlayerMoney(playerName, newAmount, true);
				break;
			case eEco:
				try {
					com.earth2me.essentials.api.Economy.add(playerName, amount);
				} catch(Exception e) {
					plugin.log.warning(plugin.logPrefix + "Unknown exception. See console!");
					e.printStackTrace();
				}
				break;
			case Vault:
				vaultEconomy.depositPlayer(playerName, amount);
				break;
		}
	}
	
	public void withdraw(Player pl, int amount) {
		String playerName = pl.getName();
		
		switch(handler) {
			case iCon:
				iConAccounts.get(playerName).getHoldings().subtract(amount);
				break;
			case boseCon:
				double currentMoney = bosEcon.getPlayerMoneyDouble(playerName);
				double newAmount = currentMoney - amount;
				bosEcon.setPlayerMoney(playerName, newAmount, true);
				break;
			case eEco:
				try {
					com.earth2me.essentials.api.Economy.subtract(playerName, amount);
				} catch(Exception e) {
					plugin.log.warning(plugin.logPrefix + "Unknown exception. See console!");
					e.printStackTrace();
				}
				break;
			case Vault:
				vaultEconomy.withdrawPlayer(playerName, amount);
				break;
		}
	}
	
	public boolean hasEnought(Player pl, int amount) {
		String playerName = pl.getName();
		
		switch(handler) {
			case iCon:
				return iConAccounts.get(playerName).getHoldings().hasEnough(amount);
			case boseCon:
				if((bosEcon.getPlayerMoneyDouble(playerName) - amount) >= 0) {
					return true;
				} else {
					return false;
				}
			case eEco:
				try {
					return com.earth2me.essentials.api.Economy.hasEnough(playerName, amount);
				} catch(Exception e) {
					plugin.log.warning(plugin.logPrefix + "Unknown exception. See console!");
					e.printStackTrace();
				}
			case Vault:
				
		}
		return false; 
	}
	
	public String format(int amount) {
		switch(handler) {
			case Vault:
				return vaultEconomy.format(amount);
			case iCon:
				return iConomy.format(amount);
			case boseCon:
				return bosEcon.getMoneyFormatted(amount);
			case eEco:
				return com.earth2me.essentials.api.Economy.format(amount);
		}
		return null;
	}
	
	enum Econs {
		eEco,
		iCon,
		boseCon,
		Vault;
	}
	
}
