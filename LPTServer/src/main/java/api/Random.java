package api;

/**
 * Created by Vishal Rathod on 2016-10-05.
 */
public class Random {
    private String id, author, title, url, link_flair_text;
    private Double ups, downs, num_comments;

    public Random(String id, String author, String title, String url, String link_flair_text, Double ups, Double downs, Double num_comments) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.url = url;
        this.link_flair_text = link_flair_text;
        this.ups = ups;
        this.downs = downs;
        this.num_comments = num_comments;
    }

    public String getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public String getLink_flair_text() {
        return this.link_flair_text;
    }

    public Double getUps() {
        return this.ups;
    }

    public Double getDowns() {
        return this.downs;
    }

    public Double getNum_comments() {
        return this.num_comments;
    }

    public String toString() {
        return "ID: " + id +
                "\nAuthor: " + author +
                "\nTitle: " + title +
                "\nURL: " + url +
                "\nLink flair text: " + link_flair_text +
                "\nUps: " + ups +
                "\nDowns: " + downs +
                "\nNum comments: " + num_comments;
    }
}
