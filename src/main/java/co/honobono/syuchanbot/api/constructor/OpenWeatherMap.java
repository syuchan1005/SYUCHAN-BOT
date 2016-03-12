
package co.honobono.syuchanbot.api.constructor;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class OpenWeatherMap {

    private String AppID;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The AppID
     */
    public String getAppID() {
        return AppID;
    }

    /**
     * 
     * @param AppID
     *     The AppID
     */
    public void setAppID(String AppID) {
        this.AppID = AppID;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
