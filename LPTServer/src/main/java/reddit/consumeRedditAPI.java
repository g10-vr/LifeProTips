package reddit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Vishal Rathod on 2016-09-27.
 */
public class consumeRedditAPI {
    /*public static void main(String[] args) {
        RedditController token = new RedditController();
        //System.out.println("Token in consumeRedditAPI : " + token.printToken());
    }*/
    private static final Logger log = LoggerFactory.getLogger(consumeRedditAPI.class);
    public static void main(String[] args) {
        //OAuth2RestOperations RedditTemplate = new RedditController().getTemplate();
        // AccessTokenResponse redditResponse = RedditTemplate.getForObject("https://www.reddit.com/api/v1/r/LifeProTips/new", AccessTokenResponse.class);

        //log.info(redditResponse.toString());

        RedditConfig config = new RedditConfig();
        OAuth2RestOperations template = config.redditRestTemplate();
        System.out.println("Token in consumeRedditAPI : " + template.getAccessToken().getValue());
    }
}
