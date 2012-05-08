package com.zolli.rodolffoutilsreloaded.listeners;

import java.util.Date;

import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
	
	@EventHandler(priority=EventPriority.HIGH)
	public void levelChange(PlayerLevelChangeEvent e) {
		
		Player pl = e.getPlayer();
		
		if(plugin.config.getBoolean("showeffectonlevelchange")) {
			pl.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, plugin.config.getInt("effecttimeonlevechange")*10, 10));
		}
		
		if(plugin.config.getBoolean("showlevelchange")) {
			pl.sendMessage(plugin.messages.getString("common.levelchangemsg").replace("(LVL)", Integer.toString(e.getNewLevel())));
		}
		
	}
	
}
