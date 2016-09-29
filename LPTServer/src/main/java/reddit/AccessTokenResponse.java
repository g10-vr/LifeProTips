package reddit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Vishal Rathod on 2016-09-28.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessTokenResponse {
    private String accessToken, tokenType, scope;
    private Long expiresIn;

    public AccessTokenResponse() {

    }

    public void setAccessToken() {
        this.accessToken = accessToken;
    }

    public void setTokentype() {
        this.tokenType = tokenType;
    }

    public void setExpiresIn() {
        this.expiresIn = expiresIn;
    }

    public void setScope() {
        this.scope = scope;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getTokentype() {
        return this.tokenType;
    }

    public Long getExpiresIn() {
        return this.expiresIn;
    }

    public String gsetScope() {
        return this.scope;
    }

    @Override
    public String toString() {
        return "{ \"access_token\": " + accessToken +
                ",\n" +
                "\"token_type\": " + tokenType +
                ",\n" +
                "\"expires_in\": " + expiresIn +
                ",\n" +
                "\"scope\": " + scope +
                ",\n  }";
    }
}
