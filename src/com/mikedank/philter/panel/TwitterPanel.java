package com.mikedank.philter.panel;

import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

import com.mikedank.philter.Keys;
import com.mikedank.philter.LineTruncator;

public class TwitterPanel extends JPanel {
	
	private Vector<String> twitterList = new Vector<String>();
	
	// twitterParse
	// Takes the account name and retrieves the statuses. 
	public void twitterParse(String account){
		try {
			// Do the Twitter auth
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			  .setOAuthConsumerKey(Keys.getTwitterConsumerKey())
			  .setOAuthConsumerSecret(Keys.getTwitterConsumerSecret())
			  .setOAuthAccessToken(Keys.getTwitterAccessToken())
			  .setOAuthAccessTokenSecret(Keys.getTwitterAccessTokenSecret());
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();
			User user = twitter.verifyCredentials();
			ResponseList<Status> statuses = twitter.getUserTimeline(account);

			// Go through the statuses and store them
			for (Status status : statuses) {
				String tweet = status.getText();
				twitterList.add(LineTruncator.truncate(tweet));
			}
		}
		catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
		}
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0;i<10;i++){
        	if(twitterList.size()>0)
        		g.drawString(twitterList.get(i), 10, 55+(15*i));
        }
    }
}