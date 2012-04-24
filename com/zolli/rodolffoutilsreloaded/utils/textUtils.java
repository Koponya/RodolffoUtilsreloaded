package com.zolli.rodolffoutilsreloaded.utils;

public class textUtils {
	
	public String arrayToString(String[] array, int fromIndex) {
		
		StringBuilder fullstr = new StringBuilder();

		if(array.length > 0) {
			
			for(int i = fromIndex; i < array.length; i++) {
				
				fullstr.append(array[i]);
				fullstr.append(" ");
				
			}
			
		}
		
		return fullstr.toString();
	}
	
	
	
}
