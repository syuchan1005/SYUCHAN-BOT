
package co.honobono.syuchanbot.api.constructor;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Main {

    private Double temp;
    private Double pressure;
    private Integer humidity;
    private Double temp_min;
    private Double temp_max;
    private Double sea_level;
    private Double grnd_level;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The temp
     */
    public Double getTemp() {
        return temp;
    }

    /**
     * 
     * @param temp
     *     The temp
     */
    public void setTemp(Double temp) {
        this.temp = temp;
    }

    /**
     * 
     * @return
     *     The pressure
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     * 
     * @param pressure
     *     The pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * 
     * @return
     *     The humidity
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     * 
     * @param humidity
     *     The humidity
     */
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    /**
     * 
     * @return
     *     The tempMin
     */
    public Double getTempMin() {
        return temp_min;
    }

    /**
     * 
     * @param tempMin
     *     The temp_min
     */
    public void setTempMin(Double tempMin) {
        this.temp_min = tempMin;
    }

    /**
     * 
     * @return
     *     The tempMax
     */
    public Double getTempMax() {
        return temp_max;
    }

    /**
     * 
     * @param tempMax
     *     The temp_max
     */
    public void setTempMax(Double tempMax) {
        this.temp_max = tempMax;
    }

    /**
     * 
     * @return
     *     The sea_level
     */
    public Double getSeaLevel() {
        return sea_level;
    }

    /**
     * 
     * @param sea_level
     *     The sea_level
     */
    public void setSeaLevel(Double sea_level) {
        this.sea_level = sea_level;
    }

    /**
     * 
     * @return
     *     The grnd_level
     */
    public Double getGrndLevel() {
        return grnd_level;
    }

    /**
     * 
     * @param grnd_level
     *     The grnd_level
     */
    public void setGrndLevel(Double grnd_level) {
        this.grnd_level = grnd_level;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
