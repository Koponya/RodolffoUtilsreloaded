package com.zolli.rodolffoutilsreloaded.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.entity.Player;

public class webUtils {
	
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
			System.out.println(returnLine);
			return returnLine;
		}
		
		return null;
		
	}
	
}
