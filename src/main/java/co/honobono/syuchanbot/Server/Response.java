package co.honobono.syuchanbot.Server;

import co.honobono.syuchanbot.Listener;
import spark.Spark;

import java.util.Map;

/**
 * Created by syuchan on 2016/03/16.
 */
public class Response {

	public static void main(String[] args) {
		Response response = new Response();
	}

	public Response() {
		Spark.port(25566);
		this.init();
	}

	public void init() {
		Spark.get("/set/*/*", (request, response) -> {
			String[] splat = request.splat();
			if(splat[0].length() == 0) {
				return "登録できませんでした";
			}
			if(Listener.getMap().containsKey(splat[0])) {
				return "すでに登録されています";
			}
			Listener.setMap(splat[0], splat[1]);
			return "登録しました:\n語句: " + splat[0] + "\n返答: " + splat[1];
		});
		Spark.get("/list", (request, response) -> {
			StringBuilder sb = new StringBuilder();
			for(Map.Entry<String, String> e : Listener.getMap().entrySet()) {
				sb.append(e.getKey());
				sb.append("  :  ");
				sb.append(e.getValue());
				sb.append("\n");
			}
			return sb.toString();
		});
		Spark.get("/remove/*", (request, response) -> {
			String s = request.splat()[0];
			if(Listener.getMap().containsKey(s)){
				Listener.getMap().remove(s);
				return "削除しました\n語句: " + s;
			} else {
				return s + "は存在しません";
			}
		});
	}
}
