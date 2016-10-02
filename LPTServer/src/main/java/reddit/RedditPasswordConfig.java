package reddit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

/**
 * Created by Vishal Rathod on 2016-09-30.
 */
@Configuration
@EnableOAuth2Client
public class RedditPasswordConfig {
    @Bean
    protected OAuth2ProtectedResourceDetails redditResourceDetails() {
        //ClientCredentialsResourceDetails redditDetails = new ClientCredentialsResourceDetails ();
        ResourceOwnerPasswordResourceDetails redditDetails = new ResourceOwnerPasswordResourceDetails();

        redditDetails.setId("thhd678");
        redditDetails.setClientId("6rU4Pl3kEnJolw");
        redditDetails.setClientSecret("PQS4fmA_Ez_4heiklEG9KeJ4Poc");
        redditDetails.setAccessTokenUri("https://www.reddit.com/api/v1/access_token");
        redditDetails.setTokenName("oauth_token");
        redditDetails.setScope(Arrays.asList("read"));

        redditDetails.setGrantType("password");
        redditDetails.setUsername("vvvvvman");
        redditDetails.setPassword("THEamazingspiderman773");

        return redditDetails;
    }

    @Bean
    public OAuth2RestOperations redditRestTemplate() {
        ClientCredentialsAccessTokenProvider provider = new ClientCredentialsAccessTokenProvider();
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        OAuth2RestTemplate template = new OAuth2RestTemplate(redditResourceDetails(), new DefaultOAuth2ClientContext(atr));
        AccessTokenProvider accessTokenProvider = new AccessTokenProviderChain(
                Arrays.<AccessTokenProvider> asList(
                        new ImplicitAccessTokenProvider(),
                        new ResourceOwnerPasswordAccessTokenProvider(),
                        new ClientCredentialsAccessTokenProvider())
        );
        template.setAccessTokenProvider(accessTokenProvider);

        return template;
    }
}
