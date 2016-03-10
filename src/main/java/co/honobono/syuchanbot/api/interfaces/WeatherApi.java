package co.honobono.syuchanbot.api.interfaces;

import co.honobono.syuchanbot.api.constructor.WeatherData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by syuchan on 2016/03/11.
 */
public interface WeatherApi {
	@GET("weather?units=metric")
	Call<WeatherData> getWeather(@Query("q") String location, @Query("appid") String id);
}
