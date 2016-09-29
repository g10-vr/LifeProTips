package reddit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;

/**
 * Created by Vishal Rathod on 2016-09-27.
 */
@Service
@SuppressWarnings("unchecked")
public class RedditController {
    @Autowired
    private OAuth2RestOperations redditTemplate;

    public RedditController() {
        this.redditTemplate = new RedditConfig().redditRestTemplate();
        //System.out.println(redditTemplate.getAccessToken().getValue());
    }

    public String printToken() {
        return redditTemplate.getAccessToken().getValue();
    }

    public OAuth2RestOperations getTemplate() {
        return this.redditTemplate;
    }
}
