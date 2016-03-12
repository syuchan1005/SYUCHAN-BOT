
package co.honobono.syuchanbot.api.constructor;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Comsumer {

    private String Key;
    private String Secret;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Key
     */
    public String getKey() {
        return Key;
    }

    /**
     * 
     * @param Key
     *     The Key
     */
    public void setKey(String Key) {
        this.Key = Key;
    }

    /**
     * 
     * @return
     *     The Secret
     */
    public String getSecret() {
        return Secret;
    }

    /**
     * 
     * @param Secret
     *     The Secret
     */
    public void setSecret(String Secret) {
        this.Secret = Secret;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
