package co.honobono.syuchanbot.constructor;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.File;
import java.util.Scanner;

/**
 * Created by syuchan on 2016/03/05.
 */
public class Twitters {
	public static Scanner scanner = new Scanner(System.in);
	private Twitter twitter;
	private TwitterStream twitterStream;

	public Twitters(String ConsumerKey, String ConsumerSecret) throws TwitterException {
		createInstance(ConsumerKey, ConsumerSecret, null);
	}

	public Twitters(String ConsumerKey, String ConsumerSecret, String AccessToken, String AccessTokenSecret) throws TwitterException {
		this.createInstance(ConsumerKey, ConsumerSecret, new AccessToken(AccessToken, AccessTokenSecret));
	}

	public Twitters(String ConsumerKey, String ConsumerSecret, AccessToken accessToken) throws TwitterException {
		this.createInstance(ConsumerKey, ConsumerSecret, accessToken);
	}

	private Twitters createInstance(String ConsumerKey, String ConsumerSecret, AccessToken accessToken) throws TwitterException {
		this.twitter = new TwitterFactory().getInstance();
		this.twitterStream = new TwitterStreamFactory().getInstance();
		this.twitter.setOAuthConsumer(ConsumerKey, ConsumerSecret);
		this.twitterStream.setOAuthConsumer(ConsumerKey, ConsumerSecret);
		if(accessToken == null) {
			RequestToken requestToken = this.twitter.getOAuthRequestToken();
			while (null == accessToken) {
				System.out.println("Open the following URL and grant access to your account:");
				System.out.println(requestToken.getAuthorizationURL());
				System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
				String pin = scanner.nextLine();
				if (pin.length() > 0) {
					accessToken = this.twitter.getOAuthAccessToken(requestToken, pin);
				} else {
					accessToken = this.twitter.getOAuthAccessToken();
				}
			}
		}
		this.twitter.setOAuthAccessToken(accessToken);
		this.twitterStream.setOAuthAccessToken(accessToken);
		return this;
	}

	public Twitter getTwitter() {
		return this.twitter;
	}

	public TwitterStream getTwitterStream() {
		return this.twitterStream;
	}

	public void sendReply(String message, long tweetId) throws TwitterException {
		this.Update(new StatusUpdate(message).inReplyToStatusId(tweetId));
	}

	public void tweet(String message) throws TwitterException {
		this.Update(new StatusUpdate(message));
	}

	public void tweet(String message, File... picture) throws TwitterException {
		if (picture.length > 5) throw new TwitterException("写真は5枚までです。");
		long[] mediaIDs = new long[5];
		for (int i = 0; i < mediaIDs.length; i++) {
			if (picture[i] != null) mediaIDs[i] = twitter.uploadMedia(picture[i]).getMediaId();
		}
		StatusUpdate update = new StatusUpdate(message);
		update.setMediaIds(mediaIDs);
		this.Update(update);
	}

	private void Update(final StatusUpdate update) throws TwitterException {
		new Thread() {
			@Override
			public void run() {
				try {
					twitter.updateStatus(update);
				} catch (TwitterException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
