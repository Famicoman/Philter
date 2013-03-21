package com.mikedank.philter;

public class Keys {
	
	// Twitter keys and tokens needed for Twitter4J
	// Get them from: https://dev.twitter.com/
	private static final String TWITTER_CONSUMER_KEY = "YOUR KEY HERE";
	private static final String TWITTER_CONSUMER_SECRET = "YOUR KEY HERE";
	private static final String TWITTER_ACCESS_TOKEN = "YOUR KEY HERE";
	private static final String TWITTER_ACCESS_TOKEN_SECRET = "YOUR KEY HERE";
	
	// Weather Underground API Key
	// Get it from http://www.wunderground.com/weather/api/
	private static final String WEATHERUNDERGROUND_KEY = "YOUR KEY HERE";
	
	// MapQuest API Key
	// Get it from http://developer.mapquest.com/
	private static final String MAPQUEST_KEY = "YOUR KEY HERE";
	
	/////////////////////////////////////////////////////////////
	// All of the accessors for these keys.
	////////////////////////////////////////////////////////////
	
	public static String getTwitterConsumerKey(){
		return TWITTER_CONSUMER_KEY;
	}
	public static String getTwitterConsumerSecret(){
		return TWITTER_CONSUMER_SECRET;
	}
	public static String getTwitterAccessToken(){
		return TWITTER_ACCESS_TOKEN;
	}
	public static String getTwitterAccessTokenSecret(){
		return TWITTER_ACCESS_TOKEN_SECRET;
	}
	
	public static String getWeatherundergroundKey(){
		return WEATHERUNDERGROUND_KEY;
	}
	
	public static String getMapquestKey(){
		return MAPQUEST_KEY;
	}
}
