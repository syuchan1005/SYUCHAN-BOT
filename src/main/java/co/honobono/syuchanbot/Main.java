package co.honobono.syuchanbot;

import co.honobono.syuchanbot.constructor.Twitters;
import twitter4j.TwitterStream;

/**
 * Created by syuchan on 2016/03/10.
 */
public class Main {

	public static void main(String[] args) {
		try {
			Twitters twitters = new Twitters("OdgsOXH5jjLpGGBMZs0vqSvdO", "2lOrT63wMCLXAGq0Gu28yzPeQfrRC5jHLqwJMHDTD5pzOOmE6J"
					, "2548450969-ZvgUYMz6n72hl3awi2M6oS5VX6RYhYfiNjlJrme", "kgn6p5Tum3zLUhcQ09rOmBrsZFzACVWlnQw25S8BGcV8J");
			TwitterStream twitterStream = twitters.getTwitterStream();
			twitterStream.addListener(new Listener(twitters));
			twitterStream.user();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
