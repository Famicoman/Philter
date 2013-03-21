package com.mikedank.philter.panel;

import java.awt.Graphics;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.mikedank.philter.URLReader;

public class BusPanel extends JPanel {
	
	private Vector<String> busList = new Vector<String>();
	
	// busParse
	// Takes in Bus route number and returns next buses.
	public void busParse(String route){
		// Since we run this for an update, clear the old info
		busList.clear();
		
		try {
			String buff = URLReader.readUrl("http://www3.septa.org/hackathon/TransitView/"+route);
			if(buff.trim().equals("Invalid Route"))
				busList.add("Incorrect Route");
			else {
				JsonElement json = new JsonParser().parse(buff.toString());
				JsonArray obj = json.getAsJsonObject().get("bus").getAsJsonArray();
			
				for(int i=0; i<obj.size(); i++){
					String busNum = obj.get(i).getAsJsonObject()
	            		.get("label").getAsString();
					String direction = obj.get(i).getAsJsonObject()
	            		.get("Direction").getAsString();
					String destination = obj.get(i).getAsJsonObject()
	            		.get("destination").getAsString();
					
					busList.add("Bus #: "+busNum+" Direction: "+direction+" Destination: "+destination);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int x= 0;
        if(busList.size()>11)
        	x = 11;
        else
        	x = busList.size();
        
        for(int i=0;i<x;i++){
        	g.drawString(busList.get(i), 5, 55+(15*i));
        }
        
    }
}