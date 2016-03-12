package co.honobono.syuchanbot;

import co.honobono.syuchanbot.api.constructor.APIKeySet;
import co.honobono.syuchanbot.constructor.Twitters;
import com.google.gson.Gson;
import com.memetix.mst.translate.Translate;

import java.io.InputStreamReader;

/**
 * Created by syuchan on 2016/03/10.
 */
public class Main {
	private static APIKeySet apiKeySet;

	public static void main(String[] args) {
		apiKeySet = new Gson().fromJson(new InputStreamReader(ClassLoader.getSystemResourceAsStream("APIKey.json")), APIKeySet.class);
		Translate.setClientId(apiKeySet.getMicrosoft().getClientID());
		Translate.setClientSecret(apiKeySet.getMicrosoft().getSecret());
		try {
			Twitters twitters = new Twitters(apiKeySet.getTwitter().getComsumer().getKey(), apiKeySet.getTwitter().getComsumer().getSecret()
					, apiKeySet.getTwitter().getAccessToken().getKey(), apiKeySet.getTwitter().getAccessToken().getSecret());
			twitters.getTwitterStream().addListener(new Listener(twitters));
			twitters.getTwitterStream().user();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static APIKeySet getApiKeySet() {
		return apiKeySet;
	}
}
