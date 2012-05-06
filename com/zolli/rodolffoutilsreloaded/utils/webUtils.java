package com.zolli.rodolffoutilsreloaded.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.entity.Player;

public class webUtils {
	
	public String hasIntroduction(Player pl) {
		
		try {
			
            URL url = new URL("http://szerver.minecraft.hu/login/game/get.php?hasIntroduction=" + pl.getName());
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
	
}
