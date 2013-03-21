package com.mikedank.philter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class URLReader {
	
	// readURL
	// Takes in a url and reads the JSON from it.
	// Puts all of the data in a string and returns it.
	public static String readUrl(String urlString) throws IOException {
		URL url = new URL(urlString);
		InputStream input = url.openStream();

        BufferedReader rd = new BufferedReader(new InputStreamReader(input));
        String line;
        StringBuffer buff = new StringBuffer();
        while ((line = rd.readLine()) != null) {
        	buff.append(line);
        }
        return buff.toString();
	}

}
