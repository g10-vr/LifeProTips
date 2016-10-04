package reddit;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import firebase.StoreLPTSubreddit;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import static java.lang.System.in;

/**
 * Created by Vishal Rathod on 2016-10-01.
 */
public class RedditHTTP {
    //getToken variables
    private final String accessTokenURL = "https://www.reddit.com/api/v1/access_token";
    private final String clientId = "6rU4Pl3kEnJolw";
    private final String clientSecret = "PQS4fmA_Ez_4heiklEG9KeJ4Poc";
    private final String myUsername = "vvvvvman";
    private final String myPassword = "THEamazingspiderman773";
    private final String userAgent = "LifeProTips_Script/0.1 by vvvvvman";

    public RedditToken redditToken;
    //getLPTSubreddit variables
    private final String LPTSubredditURL = "https://oauth.reddit.com/r/LifeProTips/?limit=5";

    public void getToken() throws Exception {
        URL getTokenURL = new URL(accessTokenURL);
        HttpURLConnection redditConnection = (HttpURLConnection) getTokenURL.openConnection();

        String redditAuthDetails = clientId + ":" + clientSecret;
        String redditAuth = "Basic " + new String(new Base64().encode( redditAuthDetails.getBytes() ));
        String redditURLParameters = "grant_type=password&username=" + myUsername + "&password=" + myPassword;

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
        System.out.println(response.toString());

        JSONObject redditAPITokenResponse = new JSONObject( response.toString() );
        redditToken = new RedditToken(redditAPITokenResponse.getString("access_token"), redditAPITokenResponse.getString("token_type"), redditAPITokenResponse.getDouble("expires_in"), redditAPITokenResponse.getString("scope"));
    }

    public void getLPTSubreddit() throws Exception {
        URL getLPTSubredditURL = new URL(LPTSubredditURL);
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
    }
}
