
package co.honobono.syuchanbot.api.constructor;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Weather {

    private Integer id;
    private String main;
    private String description;
    private String icon;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The main
     */
    public String getMain() {
        return main;
    }

    Map<String, String> weathers = new HashMap<>();
    public String getJPMain() {
        if(weathers.isEmpty()) {
            weathers.put("Thunderstorm", "雷");
            weathers.put("Drizzle", "霧雨");
            weathers.put("Rain", "雨");
            weathers.put("Snow", "雪");
            weathers.put("Atmosphere", "大気");
            weathers.put("Clear", "晴れ");
            weathers.put("Clouds", "曇り");
            weathers.put("Extreme", "異常気象");
            weathers.put("Additional", "不明");
            weathers.put("Mist", "霧");
            weathers.put("Smoke", "煙");
            weathers.put("Haze","煙霧");
        }
        if(weathers.containsKey(getMain())) {
            return weathers.get(getMain());
        } else {
            return getMain();
        }
    }

    /**
     * 
     * @param main
     *     The main
     */
    public void setMain(String main) {
        this.main = main;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
