package com.mikedank.philter.panel;

import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.mikedank.philter.URLReader;

public class RegionalRailPanel extends JPanel {
	
	private DefaultTableModel tmodel = new DefaultTableModel(0, 6);
	
	// getDefaultTableModel
	// Accessor for the table model.
	public DefaultTableModel getDefaultTableModel(){
		return tmodel;
	}
	
	// regionalRailParse
	// Takes in the from station and two station.
	// Uses this to retrieve the next trains and associated info.
	public void regionalRailParse(String fromStation, String toStation){
		// Since we run this for an update, clear the old info
		tmodel.setRowCount(0);
		
		try {
			String fromStationURL = fromStation.replaceAll(" ", "%20");
			String toStationURL = toStation.replaceAll(" ", "%20");
			String buff = URLReader.readUrl("http://www3.septa.org/hackathon/NextToArrive/"+fromStationURL+"/"+toStationURL+"/5");
			JsonElement json = new JsonParser().parse(buff);
			JsonArray obj = json.getAsJsonArray();
	        
	        for(int i=0; i<obj.size(); i++){
	        	String trainNo = obj.get(i).getAsJsonObject()
	            		.get("orig_train").getAsString();
	        	String departTime = obj.get(i).getAsJsonObject()
	            		.get("orig_departure_time").getAsString();
	            String arriveTime = obj.get(i).getAsJsonObject()
	            		.get("orig_arrival_time").getAsString();
	            String delay = obj.get(i).getAsJsonObject()
	            		.get("orig_delay").getAsString();
	            
	        	tmodel.addRow(new Object[]{fromStation,toStation,trainNo,departTime,arriveTime,delay});
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
