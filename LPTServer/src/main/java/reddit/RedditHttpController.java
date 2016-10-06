package reddit;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Properties;

/**
 * Created by Vishal Rathod on 2016-10-01.
 */
public class RedditHttpController {
    public static void main(String[] args) throws Exception {
        RedditHTTP Reddit = new RedditHTTP();
        try {
            Reddit.getToken();
            Reddit.getLPTSubreddit(null);
        }
        catch(MalformedURLException url_e){
            System.out.println(url_e);
        }
        catch(IOException io_e){
            System.out.println(io_e);
        }
    }
}
