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
    @SerializedName("description")
    String description;
    @SerializedName("url")
    String source;

    public ModelNews(String img, String title, String date, String author, String description, String source) {
        this.img = img;
        this.title = title;
        this.date = date;
        this.author = author;
        this.description = description;
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

    public String getContentNews() {
        return description;
    }

    public String getSourceNews() {
        return source;
    }
}
