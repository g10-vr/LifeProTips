package reddit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vishal Rathod on 2016-09-27.
 */
@Configuration
@EnableOAuth2Client
//@PropertySource("C:\Users\Vishal Rathod\Documents\GitHub\LifeProTips\LPTServer\src\main\resources\reddit.properties")
public class RedditConfig {
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
        ClientCredentialsResourceDetails redditDetails = new ClientCredentialsResourceDetails ();
        redditDetails.setId("reddit");
        redditDetails.setClientId("_LYEUGVjLfyzxQ");
        redditDetails.setClientSecret("a80lOmOa2402cRcZRj7U8Awkm70");
        redditDetails.setAccessTokenUri("https://www.reddit.com/api/v1/access_token");
        redditDetails.setTokenName("oauth_token");
        redditDetails.setScope(Arrays.asList("read"));
        redditDetails.setGrantType("client_credentials");

        return redditDetails;
    }

    @Bean
    public OAuth2RestOperations redditRestTemplate() {
        ClientCredentialsAccessTokenProvider provider = new ClientCredentialsAccessTokenProvider();
        //OAuth2AccessToken accessToken = provider.obtainAccessToken(redditResourceDetails(), new DefaultAccessTokenRequest());
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        OAuth2RestTemplate template = new OAuth2RestTemplate(redditResourceDetails(), new DefaultOAuth2ClientContext(atr));

        return template;
    }
}
