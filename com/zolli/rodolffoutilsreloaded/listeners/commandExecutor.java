package com.zolli.rodolffoutilsreloaded.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.zolli.rodolffoutilsreloaded.rodolffoUtilsReloaded;
import com.zolli.rodolffoutilsreloaded.utils.textUtils;
import com.zolli.rodolffoutilsreloaded.utils.webUtils;

public class commandExecutor implements CommandExecutor {

	private String matchedPlayers;
	private Player bannedPlayer;
	private Player unbannedPlayer;
	private rodolffoUtilsReloaded plugin;
	private textUtils tu = new textUtils();
	public webUtils wu = new webUtils();
	public commandExecutor(rodolffoUtilsReloaded instance) {
		plugin = instance;
	}
	
	
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		
		if(command.getName().equalsIgnoreCase("achat") || command.getName().equalsIgnoreCase("ac")) {
			
			if(args.length < 1) {
				return false;
			}
			
			if(sender.isOp() || plugin.perm.has(sender, "rur.adminChat")) {
				
				String msgFormat = plugin.config.getString("adminChatFormat").replace("(NAME)", sender.getName());
				String param = tu.arrayToString(args, 0);
				
				for(Player p : Bukkit.getServer().getOnlinePlayers()) {
					
					if(p.isOp() || plugin.perm.has(p, "rur.adminchat")) {
						
						p.sendMessage(msgFormat.replace("(MSG)", param).toString());
						
					}
					
				}
				
			} else {
				
				return false;
				
			}
			
			return true;
		}
		
		if(command.getName().equalsIgnoreCase("fakechat") || command.getName().equalsIgnoreCase("/fc")) {
			
			Player p = null;
			String message = tu.arrayToString(args, 1);
			
			if(sender.isOp() || plugin.perm.has(sender, "rur.fakeChat")) {
			
				if(args.length < 2) {
					return false;
				}
				
				if(Bukkit.matchPlayer(args[0]) == null) {
					sender.sendMessage("Nem található játékos ezen a néven!");
				} else {
					p = plugin.getServer().getPlayer(args[0]);
				}
				
				if(!plugin.perm.has(p, "rur.fakeChat.exempt") && p != null) {
				
					for(Player pl : Bukkit.getServer().getOnlinePlayers()) {
						
						if(plugin.perm.has(pl, "rur.fakeChat")) {
							
							pl.sendMessage(pl.getName().toString() + " írta:");
							
						}
						
					}
					
					p.chat(message);
				
				} else {
					sender.sendMessage("Nem beszélhetsz ezen játékos nevében!");
				}
				
			} else {
				return false;
			}
			
			return true;
			
		}
		
		if(command.getName().equalsIgnoreCase("definebutton")) {
			
			if(sender.isOp() || plugin.perm.has(sender, "rur.specialButton.define")) {
				
				if(args.length < 2) {
					return false;
				}
				
				if(args[0].equalsIgnoreCase("weathersun") || args[0].equalsIgnoreCase("promote")) {
				
					plugin.SelectorPlayer = sender.getName();
					plugin.selectType = args[0];
					plugin.selectName = args[1];
					sender.sendMessage("A gomb tipusa: " + args[0]);
					sender.sendMessage("Válaszd ki a gombot jobb kattintással!");
					
				} else {
					
					sender.sendMessage("A gomb csak a következö tipusu lehet: promote, weathersun!");
					
				}
				
			} else {
				return false;
			}
			
			return true;
			
		}
		
		if(command.getName().equalsIgnoreCase("idban")) {
			
			if(sender.isOp() || plugin.perm.has(sender, "rur.idban")) {
				
				if(args.length < 1) {
					return false;
				}
				
				List<Player> mathcPlayerList = Bukkit.matchPlayer(args[0]);
				
				if(mathcPlayerList.isEmpty() == false) {
					
					if(mathcPlayerList.size() == 1) {
						
						bannedPlayer = mathcPlayerList.get(0);
						String answer = wu.idBan(bannedPlayer.getName());
						
						if(answer.equalsIgnoreCase("ok")) {
							
							sender.sendMessage(bannedPlayer.getName() + " örökre ki lett tiltva!");
							
							if(bannedPlayer.isBanned() == false && plugin.config.getBoolean("idbanAlsoBanPlayer")) {
								plugin.getServer().getPlayer(sender.getName()).performCommand("ban " + args[0]);
							}
							
						} else {
							
							sender.sendMessage("Hiba " + args[0] + " bannolása közben: " + answer);
							
						}
						
					} else {
						
						sender.sendMessage("Több játékos is illeszkedik erre a névre:");
						
						for(Player p : mathcPlayerList) {
							matchedPlayers = matchedPlayers + p.getName() + ", ";
						}
						
						sender.sendMessage(matchedPlayers);
						
					}
					
				} else {
					
					OfflinePlayer offlinePl = plugin.getServer().getOfflinePlayer(args[0]);
					
					if(offlinePl != null) {
						
						bannedPlayer = offlinePl.getPlayer();
						String answer = wu.idBan(bannedPlayer.getName());
						
						if(answer.equalsIgnoreCase("ok")) {
							
							sender.sendMessage(bannedPlayer.getName() + " örökre ki lett tiltva!");
							
							if(bannedPlayer.isBanned() == false && plugin.config.getBoolean("idbanAlsoBanPlayer")) {
								plugin.getServer().getPlayer(sender.getName()).performCommand("ban " + args[0]);
							}
							
						} else {
							
							sender.sendMessage("Hiba " + args[0] + " bannolása közben: " + answer);
							
						}
						
					}
					
				}
				
			} else {
				return false;
			}
			
			return true;
			
		}
		
		if(command.getName().equalsIgnoreCase("idunban")) {
			
			if(sender.isOp() || plugin.perm.has(sender, "rur.idunban")) {
				
				if(args.length < 1) {
					return false;
				}
				
				List<Player> mathcPlayerList = Bukkit.matchPlayer(args[0]);
				
				if(mathcPlayerList.isEmpty() == false) {
					
					if(mathcPlayerList.size() == 1) {
						
						unbannedPlayer = mathcPlayerList.get(0);
						String answer = wu.idunBan(unbannedPlayer.getName());
						
						if(answer.equalsIgnoreCase("ok")) {
							
							sender.sendMessage(unbannedPlayer.getName() + " kitiltása feloldva!");
							
							if(plugin.config.getBoolean("idUnbanAlsoUnbanPlayer")) {
								plugin.getServer().getPlayer(sender.getName()).performCommand("unban " + args[0]);
							}
							
						} else {
							
							sender.sendMessage("Hiba " + args[0] + " unbannolása közben: " + answer);
							
						}
						
					} else {
						
						sender.sendMessage("Több játékos is illeszkedik erre a névre:");
						
						for(Player p : mathcPlayerList) {
							matchedPlayers = matchedPlayers + p.getName() + ", ";
						}
						
						sender.sendMessage(matchedPlayers);
						
					}
					
				} else {
					
					OfflinePlayer offlinePl = plugin.getServer().getOfflinePlayer(args[0]);
					
					if(offlinePl != null) {
						
						unbannedPlayer = offlinePl.getPlayer();
						String answer = wu.idBan(unbannedPlayer.getName());
						
						if(answer.equalsIgnoreCase("ok")) {
							
							sender.sendMessage(unbannedPlayer.getName() + " kitiltása feloldva!");
							
							if(unbannedPlayer.isBanned() == true && plugin.config.getBoolean("idUnbanAlsoUnbanPlayer")) {
								plugin.getServer().getPlayer(sender.getName()).performCommand("unban " + args[0]);
							}
							
						} else {
							
							sender.sendMessage("Hiba " + args[0] + " unbannolása közben: " + answer);
							
						}
						
					}
					
				}
				
			} else {
				return false;
			}
			
			return true;
			
		}
		
		return false;
	}

}
