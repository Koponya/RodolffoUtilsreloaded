package com.zolli.rodolffoutilsreloaded.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import org.bukkit.entity.Player;

public class webUtils {
	
	private Random randomGenerator = new Random();
	
	/**
	 * Get the text from webpage
	 * @param uri The url want to open and read
	 * @return the readed string when query is sucess, on error null
	 */
	private String getLine(String uri) {
		
		try {
			
            URL url = new URL(uri);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            
            inputLine = in.readLine();
            in.close();
            
            return inputLine;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return null;
		
	}
	
	/**
	 * Check the specified player has a valid introduction
	 * @param pl The checked player object
	 * @return The value returned by website
	 */
	public String hasIntroduction(Player pl) {
		
		String returnLine = getLine("http://szerver.minecraft.hu/login/game/get.php?hasIntroduction=" + pl.getName());
		
		if(returnLine != null) {
			return returnLine;
		}
		
		return null;
		
	}
	
	public String multiUsers(Player pl) {
		
		String returnLine = getLine("http://szerver.minecraft.hu/login/ajax.php?multiUsers=" + randomGenerator.nextInt(10000000) + "&user=" + pl.getName());
		
		if(returnLine != null) {
			return returnLine;
		}
		
		return null;
		
	}
	
	/**
	 * Ban the player UID
	 * @param name The players name
	 * @return the wbsite response on success or null when error
	 */
	public String idBan(String name) {
		
		String url = "http://szerver.minecraft.hu/login/game/get.php?banPlayer=" + randomGenerator.nextInt(100000) + "&name=" + name;
		String returnLine = getLine(url);
		
		if(returnLine != null) {
			return returnLine;
		}
		
		return null;
		
	}
	
	/**
	 * Unban the player UID
	 * @param name The player name
	 * @return the website response on success or null on error
	 */
	public String idunBan(String name) {
		
		String url = "http://szerver.minecraft.hu/login/game/get.php?unbanPlayer=" + randomGenerator.nextInt(100000) + "&name=" + name;
		String returnLine = getLine(url);
		
		if(returnLine != null) {
			return returnLine;
		}
		
		return null;
		
	}
	
}
