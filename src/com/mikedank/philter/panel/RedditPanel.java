package com.mikedank.philter.panel;

import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.mikedank.philter.LineTruncator;
import com.mikedank.philter.URLReader;

public class RedditPanel extends JPanel {
	
	private Vector<String> redditList = new Vector<String>();
	
	// redditParse
	// Retrieves and stores info from r/Philadelphia.
	public void redditParse(){
		// Since we run this for an update, clear the old info
		redditList.clear();
		 
		try {
			String buff = URLReader.readUrl("http://reddit.com/r/Philadelphia/.json");
			JsonElement json = new JsonParser().parse(buff);
	        JsonObject obj = json.getAsJsonObject();
	        
	        for(int i=0; i<5; i++){
	        	String score = obj.get("data").getAsJsonObject()
	                	.getAsJsonArray("children")
	                	.get(i).getAsJsonObject()
	                	.get("data").getAsJsonObject()
	                	.get("score").getAsString();
	        	String up = obj.get("data").getAsJsonObject()
	                	.getAsJsonArray("children")
	                	.get(i).getAsJsonObject()
	                	.get("data").getAsJsonObject()
	                	.get("ups").getAsString();
	        	String down = obj.get("data").getAsJsonObject()
	                	.getAsJsonArray("children")
	                	.get(i).getAsJsonObject()
	                	.get("data").getAsJsonObject()
	                	.get("downs").getAsString();
	        	String comments = obj.get("data").getAsJsonObject()
	                	.getAsJsonArray("children")
	                	.get(i).getAsJsonObject()
	                	.get("data").getAsJsonObject()
	                	.get("num_comments").getAsString();
	        	String author = obj.get("data").getAsJsonObject()
	                	.getAsJsonArray("children")
	                	.get(i).getAsJsonObject()
	                	.get("data").getAsJsonObject()
	                	.get("author").getAsString();
	        	String title = obj.get("data").getAsJsonObject()
	            		.getAsJsonArray("children")
	            		.get(i).getAsJsonObject()
	            		.get("data").getAsJsonObject()
	            		.get("title").getAsString();
	        	
	        	redditList.add(LineTruncator.truncate(title));
	        	redditList.add("Score: "+score+" Upvotes: "+up+" Downvotes: "+down+" Comments:"+comments+" Author: "+author);
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0;i<redditList.size();i++){
        	if(i%2==0){ // For the headlines
        		Font f = new Font ("Sanserif", Font.BOLD, 12);
        		g.setFont(f);
        		g.drawString(redditList.get(i), 10, 80+(15*i));
        		g.setFont(new Font ("Sanserif", Font.PLAIN, 12));
	        }
        	else // For the details
        		g.drawString(redditList.get(i), 10, 80+(15*i));
        }
    }
	}