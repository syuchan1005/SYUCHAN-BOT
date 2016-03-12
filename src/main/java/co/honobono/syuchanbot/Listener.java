package co.honobono.syuchanbot;

import co.honobono.syuchanbot.api.Weather;
import co.honobono.syuchanbot.constructor.Twitters;
import twitter4j.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by syuchan on 2016/03/10.
 */
public class Listener extends UserStreamAdapter {
	private Twitters twitters;

	public Listener(Twitters twitters) {
		this.twitters = twitters;
	}

	private static Pattern weather = Pattern.compile("(?:@syu_chan_1005)?(.*)?の天気は[\\?？]");

	@Override
	public void onStatus(Status status) { // TL取得
		String text = status.getText();
		if(!text.startsWith("@syu_chan_1005")) return;
		if(!text.endsWith("openWeatherMap")) System.out.println("ReserveReply: @" + status.getUser().getScreenName() + " " + text);
		Matcher matcher = weather.matcher(text);
		if (matcher.find()) {
			String loc = matcher.group(1).replaceAll(" ", "");
			if(loc.length() != loc.getBytes().length) loc = Weather.toLocation(loc);
			Weather.call(twitters, status.getUser().getScreenName(), status.getId(), loc);
		}
	}
}
