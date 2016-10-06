package reddit;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import firebase.StoreLPTSubreddit;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonNode;
import org.json.JSONArray;
import org.json.JSONObject;

import static java.lang.System.in;

/**
 * Created by Vishal Rathod on 2016-10-01.
 */
public class RedditHTTP {
    private String accessTokenURL, clientId, clientSecret, username, password, userAgent;
    public RedditToken redditToken;
    private final String LPTSubredditURL = "https://oauth.reddit.com/r/LifeProTips/";

    /*
    Retrieve access token from Reddit API
     */
    public void getToken() throws Exception {
        getRedditProperties();
        URL getTokenURL = new URL(accessTokenURL);
        HttpURLConnection redditConnection = (HttpURLConnection) getTokenURL.openConnection();

        String redditAuthDetails = clientId + ":" + clientSecret;
        String redditAuth = "Basic " + new String(new Base64().encode( redditAuthDetails.getBytes() ));
        String redditURLParameters = "grant_type=password&username=" + username + "&password=" + password;

        redditConnection.setRequestMethod("POST");
        redditConnection.setRequestProperty("User-Agent", userAgent);
        redditConnection.setRequestProperty("Authorization", redditAuth);
        redditConnection.setUseCaches(false);
        redditConnection.setDoOutput(true);

        DataOutputStream redditOutputStream = new DataOutputStream(redditConnection.getOutputStream());
        redditOutputStream.writeBytes(redditURLParameters);
        redditOutputStream.flush();
        redditOutputStream.close();

        int redditResponseCode = redditConnection.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + getTokenURL);
        System.out.println("Post parameters : " + redditURLParameters);
        System.out.println("Response Code : " + redditResponseCode);

        BufferedReader redditBuffer = new BufferedReader(new InputStreamReader(redditConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = redditBuffer.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        //System.out.println(response.toString());

        JSONObject redditAPITokenResponse = new JSONObject( response.toString() );
        redditToken = new RedditToken(redditAPITokenResponse.getString("access_token"), redditAPITokenResponse.getString("token_type"), redditAPITokenResponse.getDouble("expires_in"), redditAPITokenResponse.getString("scope"));
        System.out.println(redditToken.toString());
    }

    /*
    Retrieve LifeProTips subreddit from Reddit API
     */
    public void getLPTSubreddit(String after) throws Exception {
        URL getLPTSubredditURL;
        if(after != null && !after.isEmpty())
            getLPTSubredditURL = new URL(LPTSubredditURL + "?after=" + after);
        else
            getLPTSubredditURL = new URL(LPTSubredditURL);

        HttpURLConnection redditConnection = (HttpURLConnection) getLPTSubredditURL.openConnection();

        redditConnection.setRequestMethod("GET");
        redditConnection.setRequestProperty("User-Agent", userAgent);
        redditConnection.setRequestProperty("Authorization", redditToken.getTokenType() + " " + redditToken.getAccessToken());

        int responseCode = redditConnection.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + getLPTSubredditURL);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(redditConnection.getInputStream()));
        String inputLine;
        StringBuffer redditResponseCode = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            redditResponseCode.append(inputLine);
        }
        in.close();

        StoreLPTSubreddit storeSubreddit = new StoreLPTSubreddit();
        storeSubreddit.storeLPTs(new JSONObject( redditResponseCode.toString() ));
        /*String afterId = getAfterId(redditResponseCode.toString());
        if(afterId != null && !afterId.isEmpty()) {
            System.out.println("After ID : " + afterId);
            getLPTSubreddit(afterId);
        }*/
    }

    public String getAfterId(String subredditJSONText) throws Exception {
        JSONObject subredditJSON = new JSONObject(subredditJSONText);
        return subredditJSON.getJSONObject("data").getString("after");
    }

    public void getRedditProperties() throws Exception {
        Properties reddit = new Properties();
        try (FileReader in = new FileReader("../LPTServer/src/main/resources/reddit.properties")) {
            reddit.load(in);
        }
        this.clientId = reddit.getProperty("clientId");
        this.clientSecret = reddit.getProperty("clientSecret");
        this.accessTokenURL = reddit.getProperty("accessTokenURL");
        this.username = reddit.getProperty("username");
        this.password = reddit.getProperty("password");
        this.userAgent = reddit.getProperty("userAgent");
    }
}
