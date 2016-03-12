
package co.honobono.syuchanbot.api.constructor;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class APIKeySet {

    private Twitter Twitter;
    private OpenWeatherMap openWeatherMap;
    private Microsoft Microsoft;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Twitter
     */
    public Twitter getTwitter() {
        return Twitter;
    }

    /**
     * 
     * @param Twitter
     *     The Twitter
     */
    public void setTwitter(Twitter Twitter) {
        this.Twitter = Twitter;
    }

    /**
     * 
     * @return
     *     The openWeatherMap
     */
    public OpenWeatherMap getOpenWeatherMap() {
        return openWeatherMap;
    }

    /**
     * 
     * @param openWeatherMap
     *     The openWeatherMap
     */
    public void setOpenWeatherMap(OpenWeatherMap openWeatherMap) {
        this.openWeatherMap = openWeatherMap;
    }

    /**
     * 
     * @return
     *     The Microsoft
     */
    public Microsoft getMicrosoft() {
        return Microsoft;
    }

    /**
     * 
     * @param Microsoft
     *     The Microsoft
     */
    public void setMicrosoft(Microsoft Microsoft) {
        this.Microsoft = Microsoft;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
