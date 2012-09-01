package com.zolli.rodolffoutilsreloaded.utils;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.data.Group;
import org.anjocaido.groupmanager.data.User;
import org.anjocaido.groupmanager.dataholder.OverloadedWorldHolder;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.zolli.rodolffoutilsreloaded.rodolffoUtilsReloaded;

public class permHandler {
	
	rodolffoUtilsReloaded plugin;
	PermissionManager pexPlugin;
	GroupManager gmPlugin;
	public static net.milkbowl.vault.permission.Permission vaultPermission;
	Perms handler = null;
	
	public permHandler(rodolffoUtilsReloaded plugin) {
		this.plugin = plugin;
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		if(pm.isPluginEnabled("PermissionsEx")) {
			handler = Perms.PEX;
			pexPlugin = PermissionsEx.getPermissionManager();
			plugin.log.info(plugin.logPrefix + "Using PermissionsEx to handle permissions!");
			return;
		} else if(pm.isPluginEnabled("GroupManager")) {
			handler = Perms.GM;
			gmPlugin = (GroupManager) pm.getPlugin("GroupManager");
			plugin.log.info(plugin.logPrefix + "Using GroupManager for permission support!");
			return;
		} else if(pm.isPluginEnabled("Vault")) {
			RegisteredServiceProvider<net.milkbowl.vault.permission.Permission> permissionProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
			
			if(permissionProvider != null) {
				vaultPermission = permissionProvider.getProvider();
				handler = Perms.VAULT;
				plugin.log.info(plugin.logPrefix + "Using Vault to handle permissions! Vault linked with: " + vaultPermission.getName());
				return;
			}
		} else {
			handler = Perms.BUKKIT;
			plugin.log.info(plugin.logPrefix + "Not found valuable permission system! Using Bukkit standard permission API!");
		}
	}
	
	public boolean has(CommandSender sender, String node) {
		Player pl = (Player) sender;
		
		if((sender instanceof ConsoleCommandSender) || pl.isOp()) {
			return true;
		}
		
		AnjoPermissionsHandler gmHandler = gmPlugin.getWorldsHolder().getWorldPermissions(pl);
		
		switch(handler) {
			case PEX:
				return pexPlugin.has(pl, node);
			case GM:
				return gmHandler.has(pl, node);
			case VAULT:
				return vaultPermission.has(pl, node);
			case BUKKIT:
				return pl.hasPermission(node);
		}
		return false;
	}
	
	public boolean has(Player pl, String node) {
		
		if(pl.isOp()) {
			return true;
		}
		
		AnjoPermissionsHandler gmHandler = gmPlugin.getWorldsHolder().getWorldPermissions(pl);
		
		switch(handler) {
			case PEX:
				return pexPlugin.has(pl, node);
			case GM:
				return gmHandler.has(pl, node);
			case VAULT:
				return vaultPermission.has(pl, node);
			case BUKKIT:
				return pl.hasPermission(node);
		}
		return false;
	}
	
	public String getPrimaryGroup(Player pl) {
		String word = pl.getLocation().getWorld().getName();
		
		PermissionUser user = pexPlugin.getUser(pl);
		AnjoPermissionsHandler gmHandler;
		
		switch(handler) {
			case PEX:
				if(user.getGroupsNames(word).length > 0) {
					return user.getGroupsNames(word)[0];
				} else {
					return null;
				}
			case GM:
				if(word != null) {
					gmHandler = gmPlugin.getWorldsHolder().getWorldPermissions(word);
					return gmHandler.getGroup(pl.getName());
				} else {
					return null;
				}
			case VAULT:
				return vaultPermission.getPrimaryGroup(pl);
			case BUKKIT:
				new UnsupportedOperationException("BukkitPerms does not support getPrimaryGroup() function!");
		}
		return null;
	}
	
	public boolean playerRemoveGroup(Player pl, String groupName) {
		String word = pl.getLocation().getWorld().getName();
		
		OverloadedWorldHolder owh = gmPlugin.getWorldsHolder().getWorldData(word);
		
		switch(handler) {
			case PEX:
				pexPlugin.getUser(pl).removeGroup(groupName);
				return true;
			case GM:
				User user = owh.getUser(pl.getName());
				
				if(user == null) {
					return false;
				}
				
				if(user.getGroup().getName().equalsIgnoreCase(groupName)) {
					user.setGroup(owh.getDefaultGroup());
					return true;
				} else {
					Group group = owh.getGroup(groupName);
					
					if(group == null) {
						return false;
					}
					
					user.removeSubGroup(group);
				}
			case VAULT:
				vaultPermission.playerRemoveGroup(pl, groupName);
				return true;
			case BUKKIT:
				new UnsupportedOperationException("BukkitPerms does not support playerRemoveGroup() function!");
		}
		return false;
	}
	
	public boolean playerAddGroup(Player pl, String groupName) {
		String world = pl.getLocation().getWorld().getName();
		
		PermissionGroup pexGroup = pexPlugin.getGroup(groupName);
		PermissionUser pexUser = pexPlugin.getUser(pl);
		OverloadedWorldHolder owh;
		
		switch(handler) {
			case PEX:
				if((pexGroup == null) || (pexUser == null)) {
					return false;
				} else {
					pexUser.addGroup(pexGroup);
					return true;
				}
			case GM:
				owh = gmPlugin.getWorldsHolder().getWorldData(world);
				
				if(owh == null) {
					return false;
				}
				
				User gmUser = owh.getUser(pl.getName());
				if(gmUser == null) {
					return false;
				}
				
				Group gmGroup = owh.getGroup(groupName);
				if(gmGroup == null) {
					return false;
				}
				
				if(gmUser.getGroup().equals(owh.getDefaultGroup())) {
					gmUser.setGroup(gmGroup);
				} else if(gmGroup.getInherits().contains(gmUser.getGroup().getName().toLowerCase())) {
					gmUser.setGroup(gmGroup);
				} else {
					gmUser.addSubGroup(gmGroup);
				}
				return true;
			case VAULT:
				vaultPermission.playerAddGroup(pl, groupName);
				return true;
			case BUKKIT:
				new UnsupportedOperationException("BukkitPerms does not support playerAddGroup() function!");
		}
		
		return false;
	}
	
	enum Perms {
		GM,
		PEX,
		VAULT,
		BUKKIT;
	}
	
}
