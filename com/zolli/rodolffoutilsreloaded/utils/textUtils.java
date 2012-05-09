package com.zolli.rodolffoutilsreloaded.utils;

public class textUtils {
	
	/**
	 * Implode an array to string
	 * @param array The string array
	 * @param fromIndex From specified index
	 * @return The imploded string
	 */
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
