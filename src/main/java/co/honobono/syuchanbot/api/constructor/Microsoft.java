
package co.honobono.syuchanbot.api.constructor;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Microsoft {

    private String ClientID;
    private String  Secret;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The ClientID
     */
    public String getClientID() {
        return ClientID;
    }

    /**
     * 
     * @param ClientID
     *     The ClientID
     */
    public void setClientID(String ClientID) {
        this.ClientID = ClientID;
    }

    /**
     * 
     * @return
     *     The Translator
     */
    public String getSecret() {
        return Secret;
    }

    /**
     * 
     * @param Translator
     *     The Translator
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
