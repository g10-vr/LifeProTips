package firebase;

/**
 * Created by Vishal Rathod on 2016-10-03.
 */
public class LPTThread {
    public String author, url, title, link_flair_text;
    public int ups, downs, num_comments;

    public LPTThread(String author, String url, String title, String link_flair_text, int ups, int downs, int num_comments) {
        this.author = author;
        this.url = url;
        this.title = title;
        this.link_flair_text = link_flair_text;
        this.ups = ups;
        this.downs = downs;
        this.num_comments = num_comments;
    }
}
