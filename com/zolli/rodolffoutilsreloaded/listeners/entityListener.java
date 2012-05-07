package com.zolli.rodolffoutilsreloaded.listeners;

import java.util.Date;

import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.zolli.rodolffoutilsreloaded.rodolffoUtilsReloaded;

public class entityListener implements Listener {
	
	private rodolffoUtilsReloaded plugin;
	public entityListener(rodolffoUtilsReloaded instance) {
		plugin = instance;
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void givingSoup(PlayerInteractEntityEvent e) {
		
		Player pl = e.getPlayer();
		
		if((e.getRightClicked() instanceof MushroomCow) && (pl.getItemInHand().getTypeId() == 281)) {
			
			MushroomCow mushroom = (MushroomCow) e.getRightClicked();
			int neededTime = plugin.config.getInt("mushroomSoupRegain", 300);
			
			if(((int) (new Date().getTime() / 1000L) > mushroom.getAge() + neededTime * 20) || (pl.isOp() || plugin.perm.has(pl, "rur.mushroomCooldown.exempt"))) {
				
				mushroom.setAge((int) (new Date().getTime() / 1000L));
				
			} else {
				
				e.setCancelled(true);
				
			}
			
		}
		
		
	}
	
}
