
package co.honobono.syuchanbot.api.constructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class List {

    private Integer dt;
    private Main main;
    private java.util.List<Weather> weather = new ArrayList<Weather>();
    private Clouds clouds;
    private Wind wind;
    private Rain rain;
    private Snow snow;
    private Sys_ sys;
    private String dt_txt;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The dt
     */
    public Integer getDt() {
        return dt;
    }

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public String getTime() {
        return simpleDateFormat.format(getDt() * 1000);
    }

    /**
     * 
     * @param dt
     *     The dt
     */
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    /**
     * 
     * @return
     *     The main
     */
    public Main getMain() {
        return main;
    }

    /**
     * 
     * @param main
     *     The main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * 
     * @return
     *     The weather
     */
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    /**
     * 
     * @param weather
     *     The weather
     */
    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    /**
     * 
     * @return
     *     The clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * 
     * @param clouds
     *     The clouds
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     * 
     * @return
     *     The wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * 
     * @param wind
     *     The wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * 
     * @return
     *     The rain
     */
    public Rain getRain() {
        return rain;
    }

    /**
     * 
     * @param rain
     *     The rain
     */
    public void setRain(Rain rain) {
        this.rain = rain;
    }

    /**
     * 
     * @return
     *     The snow
     */
    public Snow getSnow() {
        return snow;
    }

    /**
     * 
     * @param snow
     *     The snow
     */
    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    /**
     * 
     * @return
     *     The sys
     */
    public Sys_ getSys() {
        return sys;
    }

    /**
     * 
     * @param sys
     *     The sys
     */
    public void setSys(Sys_ sys) {
        this.sys = sys;
    }

    /**
     * 
     * @return
     *     The dt_txt
     */
    public String getDtTxt() {
        return dt_txt;
    }

    /**
     * 
     * @param dt_txt
     *     The dt_txt
     */
    public void setDtTxt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
