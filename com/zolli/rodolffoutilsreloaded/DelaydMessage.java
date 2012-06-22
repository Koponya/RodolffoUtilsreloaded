package com.zolli.rodolffoutilsreloaded;

import org.bukkit.entity.Player;

public class DelaydMessage implements Runnable {

	private Player p;
	private String msg;
	
	public DelaydMessage(Player p, String msg) {
		this.p = p;
		this.msg = msg;
	}
	
	@Override
	public void run() {
		p.sendMessage(msg);
	}

}
