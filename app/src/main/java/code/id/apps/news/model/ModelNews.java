package code.id.apps.news.model;

import com.google.gson.annotations.SerializedName;

/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public class ModelNews {
    @SerializedName("urlToImage")
    String img;
    @SerializedName("title")
    String title;
    @SerializedName("publishedAt")
    String date;
    @SerializedName("author")
    String author;
    @SerializedName("content")
    String content;
    @SerializedName("url")
    String url;
    @SerializedName("source")
    ModelSource source;

    public ModelNews() { }

    public ModelNews(String img, String title, String date, String author, String content, String url, ModelSource source) {
        this.img = img;
        this.title = title;
        this.date = date;
        this.author = author;
        this.content = content;
        this.url = url;
        this.source = source;
    }

    public String getImgNews() {
        return img;
    }

    public String getTitleNews() {
        return title;
    }

    public String getDateNews() {
        return date;
    }

    public String getAuthorNews() {
        return author;
    }

    public String getContentNews() { return content; }

    public String getUrlNews() {
        return url;
    }

    public ModelSource getSource() { return source; }
}


