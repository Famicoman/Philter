package com.mikedank.philter.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.mikedank.philter.LineTruncator;
import com.mikedank.philter.URLReader;
import com.mikedank.philter.Keys;

public class TrafficPanel extends JPanel {
	
	private Vector<String> trafficList = new Vector<String>();
	
	// trafficParse
	// Retrieves any incidents on the roadways.
	public void trafficParse(){
		// Since we run this for an update, clear the old info
		trafficList.clear();
		
		try {
			String buff = URLReader.readUrl("http://www.mapquestapi.com/traffic/v1/incidents?key="+Keys.getMapquestKey()+"&callback=handleIncidentsResponse&boundingBox=40.162237,-75.439810,39.741141,-74.890494&filters=construction,incidents&inFormat=kvp&outFormat=json");
			JsonElement json = new JsonParser().parse(buff.toString().substring(24,buff.toString().length()-2));
			JsonObject obj = json.getAsJsonObject();
			
			//Get the latest five incidents and store them
	        for(int i=0; i<5; i++){
	        	String incident = obj.get("incidents").getAsJsonArray()
	        					 .get(i).getAsJsonObject()
	        					 .get("shortDesc").getAsString();
	        	
	        	trafficList.add(LineTruncator.truncate(incident));
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0;i<trafficList.size();i++){
        	g.drawString(trafficList.get(i), 5, 55+(15*i));
        }
    }
}