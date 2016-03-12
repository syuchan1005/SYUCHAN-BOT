package co.honobono.syuchanbot;

import co.honobono.syuchanbot.api.RPN;
import co.honobono.syuchanbot.api.Weather;
import co.honobono.syuchanbot.constructor.Twitters;
import twitter4j.*;

import java.util.Base64;
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
	// private static Pattern weather_details = Pattern.compile("(?:@syu_chan_1005)?(.*)の(.*)の天気は[\\?？]");
	private static Pattern hex = Pattern.compile("(?:@syu_chan_1005)?toHex (.*)");
	private static Pattern bin = Pattern.compile("(?:@syu_chan_1005)?toBin (.*)");
	private static Pattern oct = Pattern.compile("(?:@syu_chan_1005)?toOct (.*)");
	private static Pattern encode = Pattern.compile("(?:@syu_chan_1005)?base64_encode (.*)");
	private static Pattern decode = Pattern.compile("(?:@syu_chan_1005)?base64_decode (.*)");
	private static Pattern rpn = Pattern.compile("(?:@syu_chan_1005)?[rpn|RPN] (.*)");
	private static Pattern neta = Pattern.compile("(?:@syu_chan_1005)?([Oo]K(.*)[Gg]oogle(.*)|[Hh]ey(.*)[Ss]iri(.*)|コルタナさん(.*)|[Cc]ortanaさん(.*))");

	@Override
	public void onStatus(Status status) {
		String text = status.getText();
		if (!text.startsWith("@syu_chan_1005")) return;
		if (status.getSource().indexOf("SYUCHAN-BOT") == -1)
			System.out.println("ReserveReply: @" + status.getUser().getScreenName() + " " + text.replaceAll("\n", " "));
		else return;
		text = text.replaceAll("\n", " ");
		Matcher matcher;
		if ((matcher = weather.matcher(text)).find()) {
			/*
			String loc;
			Matcher matcher1;
			if((matcher1 = weather_details.matcher(text)).find()) {
				loc = matcher1.group(2).replaceAll(" ", "");
				if (loc.length() != loc.getBytes().length) loc = Weather.toLocation(loc);
				if(matcher1.group(1).replaceAll(" ", "").equalsIgnoreCase("明日")) {
					Weather.call_future(twitters, status.getUser().getScreenName(), status.getId(), loc, 1);
					return;
				}
			} else {
				loc = matcher.group(1).replaceAll(" ", "");
				if (loc.length() != loc.getBytes().length) loc = Weather.toLocation(loc);
			}
			Weather.call_now(twitters, status.getUser().getScreenName(), status.getId(), loc);
			*/
			String loc = matcher.group(1).replaceAll(" ", "");
			if (loc.length() != loc.getBytes().length) loc = Weather.toLocation(loc);
			Weather.call_now(twitters, status.getUser().getScreenName(), status.getId(), loc);
		} else if((matcher = hex.matcher(text)).find()) {
			twitters.sendReply("@" + status.getUser().getScreenName() + "\n" + Integer.toHexString(Integer.valueOf(matcher.group(1))), status.getId());
		} else if((matcher = bin.matcher(text)).find()) {
			twitters.sendReply("@" + status.getUser().getScreenName() + "\n" + Integer.toBinaryString(Integer.valueOf(matcher.group(1))), status.getId());
		} else if((matcher = oct.matcher(text)).find()) {
			twitters.sendReply("@" + status.getUser().getScreenName() + "\n" + Integer.toOctalString(Integer.valueOf(matcher.group(1))), status.getId());
		} else if((matcher = encode.matcher(text)).find()) {
			twitters.sendReply("@" + status.getUser().getScreenName() + "\n" + new String(Base64.getEncoder().encode(matcher.group(1).getBytes())), status.getId());
		} else if((matcher = decode.matcher(text)).find()) {
			twitters.sendReply("@" + status.getUser().getScreenName() + "\n" + new String(Base64.getDecoder().decode(matcher.group(1).getBytes())), status.getId());
		} else if((matcher = rpn.matcher(text)).find()) {
			twitters.sendReply("@" + status.getUser().getScreenName() + "\n" + RPN.calc(matcher.group(1)), status.getId());
		} else if((matcher = neta.matcher(text)).find()) {
			twitters.sendReply("@" + status.getUser().getScreenName() + "\n私はTwitterです.", status.getId());
		}
	}
}
