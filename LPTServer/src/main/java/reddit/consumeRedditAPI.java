package reddit;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Vishal Rathod on 2016-09-27.
 */
public class consumeRedditAPI {
    private static final Logger log = LoggerFactory.getLogger(consumeRedditAPI.class);
    public static void main(String[] args) {

        /*RedditConfig config = new RedditConfig();
        OAuth2RestOperations template = config.redditRestTemplate();
        System.out.println("Token in consumeRedditAPI : " + template.getAccessToken().getValue());*/

        RedditPasswordConfig config = new RedditPasswordConfig();
        OAuth2RestOperations template = config.redditRestTemplate();
        //System.out.println("Token in consumeRedditAPI : " + template.getAccessToken().getValue());
        if(template != null) {
            String accessToken = template.getAccessToken().getValue();

            /*Map<String, String> afterTokenUrlVariables = new HashMap<String, String>();
            afterTokenUrlVariables.put("Authorization", "bearer " + accessToken);
            afterTokenUrlVariables.put("User-Agent", "ChangeMeClient/0.1 by YourUsername");
            System.out.println("Token variables : " + afterTokenUrlVariables);

            JsonNode myRedditInfo = template.getForObject("https://oauth.reddit.com/api/v1/me", JsonNode.class, afterTokenUrlVariables);
            System.out.println("Print my info : " + myRedditInfo);*/
            HttpHeaders requestHeaders = new HttpHeaders();
            MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
            body.add("Authorization", "bearer " + accessToken);
            body.add("User-Agent", "LifeProTips_Script/0.1 by vvvvvman");
            HttpEntity<?> httpEntity = new HttpEntity<Object>(body, requestHeaders);
            Object redditMyInfo = template.exchange("https://oauth.reddit.com/subreddits/popular", HttpMethod.POST, httpEntity, Object.class);
            //System.out.println("Exchange result : " + redditMyInfo);
        }
        else {
            System.out.println("NULL TEMPLATE");
        }


        //Object remove_token = template.postForLocation("https://www.reddit.com/api/v1/revoke_token", Object.class);
        //System.out.println("Remove token : " + remove_token);
    }
}
