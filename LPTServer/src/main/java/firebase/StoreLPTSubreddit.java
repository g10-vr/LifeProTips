package firebase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Vishal Rathod on 2016-10-03.
 */
public class StoreLPTSubreddit {
    public void storeLPTs(JSONObject LPTSubreddit) throws Exception {
        initFirebaseRedditDatabase firebaseRedditDatabase = new initFirebaseRedditDatabase();
        JSONArray threadsOnPage = LPTSubreddit.getJSONObject("data").getJSONArray("children");
        HashMap<String, Object> threadCollection = new HashMap<String, Object>();

        for(int iterateThreads = 0; iterateThreads < threadsOnPage.length(); iterateThreads++) {
            if( !threadsOnPage.getJSONObject(iterateThreads).getJSONObject("data").getString("distinguished").contains("moderator") ) {
                if( !threadsOnPage.getJSONObject(iterateThreads).getJSONObject("data").getString("link_flair_text").contains("Request") ) {
                    HashMap<String, Object> threadDetails = new HashMap<String, Object>();
                    threadDetails = getThreadDetails(threadsOnPage.getJSONObject(iterateThreads));
                    collectThreadsToBeStored(threadCollection, threadDetails);
                }
            }
        }
        firebaseRedditDatabase.storeThreadsinDatabase(threadCollection);
    }

    public void collectThreadsToBeStored(HashMap threadCollection, HashMap threadDetails) {
        threadCollection.put(threadDetails.get("id"), new LPTThread(threadDetails.get("author").toString(), threadDetails.get("url").toString(),
                threadDetails.get("title").toString(), threadDetails.get("link_flair_text").toString(), (Integer)threadDetails.get("ups"),
                (Integer)threadDetails.get("downs"), (Integer)threadDetails.get("num_comments")));
    }

    public HashMap<String, Object> getThreadDetails(JSONObject thread) throws Exception {
        HashMap<String, Object> threadDetails = new HashMap<String, Object>();
        threadDetails.put("id", thread.getJSONObject("data").getString("id"));
        threadDetails.put("author", thread.getJSONObject("data").getString("author"));
        threadDetails.put("url", thread.getJSONObject("data").getString("url"));

        if(thread.getJSONObject("data").getString("title").contains("LPT")) {
            String threadTitle = thread.getJSONObject("data").getString("title");
            threadDetails.put("title", threadTitle.substring(threadTitle.indexOf(' ')+1));
        }
        else
            threadDetails.put("title", thread.getJSONObject("data").getString("title"));

        threadDetails.put("link_flair_text", thread.getJSONObject("data").getString("link_flair_text"));
        threadDetails.put("ups", thread.getJSONObject("data").getInt("ups"));
        threadDetails.put("downs", thread.getJSONObject("data").getInt("downs"));
        threadDetails.put("num_comments", thread.getJSONObject("data").getInt("num_comments"));

        return threadDetails;
    }
}
