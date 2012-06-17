package com.zolli.rodolffoutilsreloaded;

import org.bukkit.Bukkit;

public class LagDetectThread extends Thread {

	public boolean running;
	private final rodolffoUtilsReloaded plugin;
	private long detectStart;
	public double lag;
	
	public LagDetectThread(rodolffoUtilsReloaded instance) {
		this.plugin = instance;
	}
	
	public void run() {
    	try {
    		Thread.sleep(30000);
    	} catch (Exception ex) { }
		this.running = true;
		long next = System.currentTimeMillis();
		while(running) {
			next += 60*1000;
			this.detectStart = System.currentTimeMillis();
			Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
				
				@Override
				public void run() {
					lag = ((double)(System.currentTimeMillis() - detectStart)/1000); 
					plugin.log.info("[" + plugin.pdfile.getName() + "] Lag: "+lag);
					
				}
			}, 20L);
			try {
	    		Thread.sleep(next-System.currentTimeMillis());
	    	} catch (Exception ex) { }
		}
	}
}
