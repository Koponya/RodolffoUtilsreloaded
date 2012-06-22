package com.zolli.rodolffoutilsreloaded;

public class AutoSaveThread extends Thread {
	
	public boolean running;
	private final rodolffoUtilsReloaded plugin;
	
	public AutoSaveThread(rodolffoUtilsReloaded instance) {
		this.plugin = instance;
	}
	
	public void run() {
    	try {
    		Thread.sleep(10000);
    	} catch (Exception ex) { }
		this.running = true;
		long next = System.currentTimeMillis();
		while(running) {
			next += plugin.config.getLong("savealldelay")*60*1000;
			plugin.log.info("[" + plugin.pdfile.getName() + "] Auto save start...");
	    	plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "save-all");
	    	try {
	    		Thread.sleep(next-System.currentTimeMillis());
	    	} catch (Exception ex) { }
		}
	}
}
