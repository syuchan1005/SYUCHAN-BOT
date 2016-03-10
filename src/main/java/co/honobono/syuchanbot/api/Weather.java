package co.honobono.syuchanbot.api;


import co.honobono.syuchanbot.api.constructor.WeatherData;
import co.honobono.syuchanbot.api.interfaces.WeatherApi;
import co.honobono.syuchanbot.constructor.Twitters;
import com.ibm.icu.text.Transliterator;
import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import twitter4j.TwitterException;

import java.util.List;

/**
 * Created by syuchan on 2016/03/11.
 */
public class Weather {
	private static String URL = "http://api.openweathermap.org/data/2.5/";
	private static String openWeatherMapAPIKey = "ff4e85307564fc3ca3ec8041c38c25da";
	private static Retrofit retrofit = null;
	private static WeatherApi api = null;

	public static void call(Twitters twitters, String UserId, long tweetId, String loc) {
		if(retrofit == null) retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
		if(api == null) api = retrofit.create(WeatherApi.class);
		api.getWeather(loc, openWeatherMapAPIKey).enqueue(new Callback<WeatherData>()  {
			@Override
			public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
				StringBuilder text = new StringBuilder();
				text.append("@"); text.append(UserId); text.append("\n");
				WeatherData weatherData = response.body();
				try {
					text.append("天気:\n");
					text.append("  都市: ");
					text.append(weatherData.getName());
					text.append("\n");
					text.append("  国: ");
					text.append(weatherData.getSys().getCountry());
					text.append("\n");
					// text.append("計測地点:\n");
					// text.append("    緯度: "); text.append(weatherData.getCoord().getLat()); text.append("\n");
					// text.append("    経度: "); text.append(weatherData.getCoord().getLon()); text.append("\n");
					// text.append("  気圧: "); text.append(weatherData.getMain().getPressure()); text.append("(hPa)\n");
					text.append("  湿度: ");
					text.append(weatherData.getMain().getHumidity());
					text.append("%\n");
					text.append("  天候: ");
					text.append(weatherData.getWeather().get(0).getMain());
					text.append("\n");
					text.append("  気温:\n");
					text.append("    現在: ");
					text.append(weatherData.getMain().getTemp());
					text.append("℃\n");
					text.append("    最高: ");
					text.append(weatherData.getMain().getTempMax());
					text.append("℃\n");
					text.append("    最低: ");
					text.append(weatherData.getMain().getTempMin());
					text.append("℃\n");
					text.append("by.openWeatherMap");
				} catch (Exception e) {
					try {
						text.append("何かエラーが発生しました。");
						twitters.sendReply(text.toString(), tweetId);
					} catch (TwitterException e2) {
						e2.printStackTrace();
					}
				}
				try {
					twitters.sendReply(text.toString(), tweetId);
				} catch (TwitterException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Call<WeatherData> call, Throwable throwable) {
				StringBuilder text = new StringBuilder();
				text.append("@"); text.append(UserId); text.append("\n");
				text.append("取得に失敗しました");
				try {
					twitters.sendReply(text.toString(), tweetId);
				} catch (TwitterException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public static String toLocation(String JPName) {
		return toKatakana(JPName);
	}

	private static Transliterator transliterator = Transliterator.getInstance("Katakana-Latin");
	private static Tokenizer tokenizer = null;
	private static String toKatakana(String word) {
		if(tokenizer == null) {
			Tokenizer.Builder builder = Tokenizer.builder();
			builder.mode(Tokenizer.Mode.NORMAL);
			tokenizer = builder.build();
		}
		List<Token> tokens = tokenizer.tokenize(word);
		StringBuilder sb = new StringBuilder();
		for (Token token : tokens) sb.append(transliterator.transliterate(token.getReading()));
		return sb.toString();
	}
}

