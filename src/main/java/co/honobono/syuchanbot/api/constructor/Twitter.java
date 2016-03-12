
package co.honobono.syuchanbot.api.constructor;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Twitter {

    private co.honobono.syuchanbot.api.constructor.Comsumer Comsumer;
    private co.honobono.syuchanbot.api.constructor.AccessToken AccessToken;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Comsumer
     */
    public co.honobono.syuchanbot.api.constructor.Comsumer getComsumer() {
        return Comsumer;
    }

    /**
     * 
     * @param Comsumer
     *     The Comsumer
     */
    public void setComsumer(co.honobono.syuchanbot.api.constructor.Comsumer Comsumer) {
        this.Comsumer = Comsumer;
    }

    /**
     * 
     * @return
     *     The AccessToken
     */
    public co.honobono.syuchanbot.api.constructor.AccessToken getAccessToken() {
        return AccessToken;
    }

    /**
     * 
     * @param AccessToken
     *     The AccessToken
     */
    public void setAccessToken(co.honobono.syuchanbot.api.constructor.AccessToken AccessToken) {
        this.AccessToken = AccessToken;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
