package com.zolli.rodolffoutilsreloaded.utils;

import org.bukkit.Location;

import com.zolli.rodolffoutilsreloaded.rodolffoUtilsReloaded;

public class configUtils {
	
	private rodolffoUtilsReloaded plugin;
	public configUtils(rodolffoUtilsReloaded instance) {
		plugin = instance;
	}
	
	public void setLocation(Location loc, String type, String name) {
		
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		String world = loc.getWorld().getName().toString();
		
		plugin.button.set("specialbutton." + name + ".type", type);
		plugin.button.set("specialbutton." + name + ".world", world);
		plugin.button.set("specialbutton." + name + ".x", x);
		plugin.button.set("specialbutton." + name + ".y", y);
		plugin.button.set("specialbutton." + name + ".z", z);
		
	}
	
}
