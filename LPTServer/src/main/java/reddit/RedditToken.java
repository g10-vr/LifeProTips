package reddit;

/**
 * Created by Vishal Rathod on 2016-10-01.
 */
public class RedditToken {
    private String accessToken, tokenType, scope;
    private double expiresIn;

    public RedditToken(String accessToken, String tokenType, Double expiresIn, String scope) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.scope = scope;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getTokenType() { return this.tokenType; }

    public Double getExpiresIn() {
        return this.expiresIn;
    }

    public String getScope() {
        return this.scope;
    }

    @Override
    public String toString() {
        return "{\n\"access_token\": " + accessToken +
                ",\n" +
                "\"token_type\": " + tokenType +
                ",\n" +
                "\"expires_in\": " + expiresIn +
                ",\n" +
                "\"scope\": " + scope +
                "\n }";
    }
}
