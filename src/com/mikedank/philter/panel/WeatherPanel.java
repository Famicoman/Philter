package com.mikedank.philter.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.mikedank.philter.URLReader;
import com.mikedank.philter.Keys;


public class WeatherPanel extends JPanel {

	private Vector<String> weatherList = new Vector<String>();
	private Vector<String> weatherPics = new Vector<String>();
	
	// forecastParse
	// Takes the zipcode and retrieves the JSON from weather underground. 
	// The info about the next two days is parsed and stored.
	public void forecastParse(String zip){
		try {
			String buff = URLReader.readUrl("http://api.wunderground.com/api/"+Keys.getWeatherundergroundKey()+"/forecast/q/PA/"+zip+".json");
			JsonElement json = new JsonParser().parse(buff);
	        JsonObject obj = json.getAsJsonObject();
	        
	        // We need the forecast for today and tomorrow
	        for(int i=0;i<2;i++){
	        	//Get some of the attributes from the JSON
				String day = obj.get("forecast").getAsJsonObject()
						.get("simpleforecast").getAsJsonObject()
						.getAsJsonArray("forecastday").get(i).getAsJsonObject()
						.get("date").getAsJsonObject()
						.get("weekday").getAsString();
				String conditions = obj.get("forecast").getAsJsonObject()
						.get("simpleforecast").getAsJsonObject()
						.getAsJsonArray("forecastday").get(i).getAsJsonObject()
						.get("conditions").getAsString();
				String high = obj.get("forecast").getAsJsonObject()
						.get("simpleforecast").getAsJsonObject()
						.getAsJsonArray("forecastday").get(i).getAsJsonObject()
						.get("high").getAsJsonObject()
						.get("fahrenheit").getAsString();
				String low = obj.get("forecast").getAsJsonObject()
						.get("simpleforecast").getAsJsonObject()
						.getAsJsonArray("forecastday").get(i).getAsJsonObject()
						.get("low").getAsJsonObject()
						.get("fahrenheit").getAsString();
				weatherList.add(day+" - "+conditions+" - High/Low: "+high+"/"+low);
				
				// We also need the pictures
				weatherPics.add(obj.get("forecast").getAsJsonObject()
						.get("simpleforecast").getAsJsonObject()
						.getAsJsonArray("forecastday").get(i).getAsJsonObject()
						.get("icon_url").getAsString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// weatherParse
	// Takes the zipcode and retrieves the JSON from weather underground. 
	// The info about the current conditions is parsed and stored.
	public void weatherParse(String zip){
		// Since we run this for an update, clear the old info
		weatherPics.clear();
		weatherList.clear();
		
		try {
			String buff = URLReader.readUrl("http://api.wunderground.com/api/"+Keys.getWeatherundergroundKey()+"/conditions/q/PA/"+zip+".json");
			JsonElement json = new JsonParser().parse(buff);
	        JsonObject obj = json.getAsJsonObject();
	        
			weatherPics.add(obj.get("current_observation").getAsJsonObject()
					.get("icon_url").getAsString());
			
			// It's the weather at this instant, just get temperature and conditions
			String conditions = obj.get("current_observation").getAsJsonObject()
					.get("weather").getAsString();
			String temp = obj.get("current_observation").getAsJsonObject()
					.get("temp_f").getAsString();
			weatherList.add("Current - "+conditions+" - Temp: "+temp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// getWeatherPics
	// Takes the a url for an image and retrieves the image from it. 
	public static Image getWeatherPics(String input)
    {
    	Image image = null;
        try {
            URL url = new URL(input);
            image = ImageIO.read(url);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return image;
    }
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0;i<weatherList.size();i++){
        	g.drawImage(getWeatherPics(weatherPics.get(i)), 15,60+50*i, this);
        	g.drawString(weatherList.get(i), 65, 90+50*i);
        }
    }
	
}
