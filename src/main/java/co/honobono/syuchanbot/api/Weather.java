package co.honobono.syuchanbot.api;


import co.honobono.syuchanbot.Main;
import co.honobono.syuchanbot.api.constructor.Forecast;
import co.honobono.syuchanbot.api.constructor.WeatherData;
import co.honobono.syuchanbot.api.interfaces.WeatherApi;
import co.honobono.syuchanbot.constructor.Twitters;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import twitter4j.TwitterException;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by syuchan on 2016/03/11.
 */
public class Weather {
	private static String URL = "http://api.openweathermap.org/data/2.5/";
	private static String openWeatherMapAPIKey = null;
	private static Retrofit retrofit = null;
	private static WeatherApi api = null;

	public static void call_now(Twitters twitters, String UserId, long tweetId, String loc) {
		init();
		if(loc.length() <= 0) {
			twitters.sendReply("@" + UserId + "\n地域名が不正です", tweetId);
			return;
		}
		api.getWeather(loc, openWeatherMapAPIKey).enqueue(new Callback<WeatherData>()  {
			@Override
			public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
				try {
					StringBuilder text = new StringBuilder();
					text.append("@"); text.append(UserId); text.append("\n");
					WeatherData weatherData = response.body();
					text.append("天気:\n");
					text.append("  国: "); text.append(weatherData.getSys().getCountry()); text.append("\n");
					text.append("    都市: ");text.append(weatherData.getName());text.append("\n");
					// text.append("計測地点:\n");
					// text.append("    緯度: "); text.append(weatherData.getCoord().getLat()); text.append("\n");
					// text.append("    経度: "); text.append(weatherData.getCoord().getLon()); text.append("\n");
					// text.append("  気圧: "); text.append(weatherData.getMain().getPressure()); text.append("(hPa)\n");
					text.append("  天候: "); text.append(weatherData.getWeather().get(0).getJPMain()); text.append("\n");
					text.append("  湿度: "); text.append(weatherData.getMain().getHumidity()); text.append("%\n");
					text.append("  気温:\n");
					text.append("    現在: "); text.append(weatherData.getMain().getTemp());text.append("℃\n");
					text.append("    最高: "); text.append(weatherData.getMain().getTempMax()); text.append("℃\n");
					text.append("    最低: "); text.append(weatherData.getMain().getTempMin()); text.append("℃\n");
					text.append("by.openWeatherMap");
					twitters.sendReply(text.toString(), tweetId);
				} catch (Exception e) {
					String[] exception = e.toString().split("\\.");
					twitters.sendReply("@" + UserId + "\n" + exception[exception.length - 1], tweetId);
				}
			}

			@Override
			public void onFailure(Call<WeatherData> call, Throwable throwable) {
				StringBuilder text = new StringBuilder();
				text.append("@"); text.append(UserId); text.append("\n");
				text.append("取得に失敗しました");
				twitters.sendReply(text.toString(), tweetId);
			}
		});
	}

	public static void call_future(Twitters twitters, String UserId, long tweetId, String loc, int future) {
		init();
		if(loc.length() <= 0) {
			twitters.sendReply("@" + UserId + "\n地域名が不正です", tweetId);
			return;
		}
		api.getForecast(loc, openWeatherMapAPIKey).enqueue(new Callback<Forecast>() {
			@Override
			public void onResponse(Call<Forecast> call, Response<Forecast> response) {
				try {
					StringBuilder text = new StringBuilder();
					text.append("@"); text.append(UserId); text.append("\n");
					Forecast forecast = response.body();
					text.append("天気:\n");
					text.append("  国: "); text.append(forecast.getCity().getCountry()); text.append("\n");
					text.append("    都市: ");text.append(forecast.getCity().getName());text.append("\n");
					for(int i = 0; i < 4; i++) {
						co.honobono.syuchanbot.api.constructor.List l = forecast.getList().get(i);
						text.append("  時間: "); text.append(l.getDtTxt()); text.append("\n");
						text.append("  天候: "); text.append(l.getWeather().get(0).getJPMain()); text.append("\n");
						text.append("  湿度: "); text.append(l.getMain().getHumidity()); text.append("%\n");
						text.append("  気温:\n");
						text.append("    最高: "); text.append(l.getMain().getTempMax()); text.append("℃\n");
						text.append("    最低: "); text.append(l.getMain().getTempMin()); text.append("℃\n");
						text.append("by.openWeatherMap");
						twitters.sendReply(text.toString(), tweetId);
						text = new StringBuilder();
						text.append("@"); text.append(UserId); text.append("\n");
					}
				} catch (Exception e) {
					String[] exception = e.toString().split("\\.");
					twitters.sendReply("@" + UserId + "\n" + exception[exception.length - 1], tweetId);
				}
			}

			@Override
			public void onFailure(Call<Forecast> call, Throwable throwable) {
				StringBuilder text = new StringBuilder();
				text.append("@"); text.append(UserId); text.append("\n");
				text.append("取得に失敗しました");
				twitters.sendReply(text.toString(), tweetId);
			}
		});
	}

	private static void init() {
		if(openWeatherMapAPIKey == null) openWeatherMapAPIKey = Main.getApiKeySet().getOpenWeatherMap().getAppID();
		if(retrofit == null) retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
		if(api == null) api = retrofit.create(WeatherApi.class);
	}

	public static String toLocation(String JPName) {
		try {
			return Translate.execute(JPName, Language.JAPANESE, Language.ENGLISH);
		} catch (Exception e) {
			return "";
		}
	}
}

