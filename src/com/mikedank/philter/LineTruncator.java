package com.mikedank.philter;

public class LineTruncator {
	
	// truncate
	// Takes in a line and determines if it is too long.
	// If so, cut it up and return it.
	public static String truncate(String line){
		if(line.length()<77)
			return line;
    	else
    		return line.substring(0,77)+"...";
	}

}
