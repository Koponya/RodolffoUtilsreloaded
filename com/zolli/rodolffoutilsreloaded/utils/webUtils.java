package com.zolli.rodolffoutilsreloaded.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import org.bukkit.entity.Player;

public class webUtils {
	
	private Random randomGenerator = new Random();
	
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
	
	public String hasIntroduction(Player pl) {
		
		String returnLine = getLine("http://szerver.minecraft.hu/login/game/get.php?hasIntroduction=" + pl.getName());
		
		if(returnLine != null) {
			return returnLine;
		}
		
		return null;
		
	}
	
	public String idBan(String name) {
		
		String url = "http://szerver.minecraft.hu/login/game/get.php?banPlayer=" + randomGenerator.nextInt(100000) + "&name=" + name;
		String returnLine = getLine(url);
		
		if(returnLine != null) {
			return returnLine;
		}
		
		return null;
		
	}
	
	public String idunBan(String name) {
		
		String url = "http://szerver.minecraft.hu/login/game/get.php?unbanPlayer=" + randomGenerator.nextInt(100000) + "&name=" + name;
		String returnLine = getLine(url);
		
		if(returnLine != null) {
			return returnLine;
		}
		
		return null;
		
	}
	
}
