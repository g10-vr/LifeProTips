package reddit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishal Rathod on 2016-09-27.
 */
@Configuration
@EnableOAuth2Client
@PropertySource("classpath:reddit.properties")
public class redditConfig {
    @Value("${accessTokenUri}")
    private String accessTokenUri;

    @Value("${userAuthorizationUri}")
    private String userAuthorizationUri;

    @Value("${clientID}")
    private String clientID;

    @Value("${clientSecret}")
    private String clientSecret;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean
    protected OAuth2ProtectedResourceDetails redditResourceDetails() {
        ResourceOwnerPasswordResourceDetails redditDetails = new ResourceOwnerPasswordResourceDetails();
        List accessScopes = new ArrayList<String>(1);
        accessScopes.add("identity");

        redditDetails.setClientId(clientID);
        redditDetails.setClientSecret(clientSecret);
        redditDetails.setAccessTokenUri(accessTokenUri);
        redditDetails.setGrantType("password");
        redditDetails.setScope(accessScopes);
        redditDetails.setUsername(username);
        redditDetails.setPassword(password);

        return redditDetails;
    }

    @Bean
    public OAuth2RestTemplate redditRestTemplate(OAuth2ClientContext clientContext) {
        AccessTokenRequest redditToken = new DefaultAccessTokenRequest();
        OAuth2RestTemplate redditTemplate = new OAuth2RestTemplate(redditResourceDetails(), clientContext);

        return redditTemplate;
    }
}
