package twitter4j.examples.search;

import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class SearchTweets {

	//Hashtag & Count settings
	static final String hashTag = "#streama";
	static final int count = 5;

	public static void main(String[] args) {
		
		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("ZsvNpBlaEHp6gEv4OarzvOk9k")
		.setOAuthConsumerSecret("xf1cqLJb5alINyrzh3YbqE57VTjxn8NLCwAEZ3r0ocER4NA5oN")
		.setOAuthAccessToken("3300968563-19zGnWR491v3UDKfUXbZMPMBX5dS7zNmff8n7xp")
		.setOAuthAccessTokenSecret("hVRdrQF7WY4SvZLllRxGSdPCWtpklSUulrYRJFK7gPmQw");

		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter4j.Twitter twitter = tf.getInstance();

		//Create Query to send
		Query queryMax = new Query(hashTag);
		queryMax.setCount(count);

		//Run method
		getTweets(queryMax, twitter);

	}

	private static void getTweets(Query query, Twitter twitter) {

		try {
			QueryResult result = twitter.search(query);

			if(result.getTweets()==null || result.getTweets().isEmpty()){
				System.out.println("Sorry No Tweets Found!");
			}
			
			else{

				System.out.println("Found " + result.getTweets().size() + " tweets for the hashtag " + hashTag);

				for (Status status: result.getTweets()) {

					System.out.println("***********************************************");

					System.out.println("");

					System.out.println("Created: " + status.getCreatedAt());
					System.out.println("Name: " + status.getUser().getName()); 
					System.out.println("Username: " + "@" + status.getUser().getScreenName());
					System.out.println("Tweet: " + status.getText());
					System.out.println("link: https://twitter.com/" + status.getUser().getScreenName() 
							+ "/status/" + status.getId());

					System.out.println("");

				}

			}
		}catch (TwitterException te) {
			System.out.println("Couldn't connect: " + te);
			System.exit(-1);
		}catch (Exception e) {
			System.out.println("Something went wrong: " + e);
			System.exit(-1);
		}

	} 

}
