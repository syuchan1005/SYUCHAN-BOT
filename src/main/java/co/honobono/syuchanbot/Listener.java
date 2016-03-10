package co.honobono.syuchanbot;

import co.honobono.syuchanbot.api.Weather;
import co.honobono.syuchanbot.constructor.Twitters;
import twitter4j.*;

/**
 * Created by syuchan on 2016/03/10.
 */
public class Listener extends UserStreamAdapter {
	private Twitters twitters;

	public Listener(Twitters twitters) {
		this.twitters = twitters;
	}

	@Override
	public void onStatus(Status status) { // TL取得
		String text = status.getText();
		if(!text.startsWith("@syu_chan_1005")) return;
		if(text.indexOf("の天気は？") != -1) {
			String loc = text.replaceAll("¥n", " ").split(" ")[1].replaceAll("の天気は？", "");
			if(loc.length() != loc.getBytes().length) loc = Weather.toLocation(loc);
			try {
				Weather.call(twitters, status.getUser().getScreenName(), status.getId(), loc);
			} catch (Exception e) {
				try {
					twitters.sendReply("@" + status.getUser().getScreenName() + e.getMessage(), status.getId());
				} catch (TwitterException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
