package code.id.apps.news.utils.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import code.id.apps.news.model.ModelNews;

/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public class ResponseApi {
    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<ModelNews> newsList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() { return totalResults; }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ModelNews> getNewsList() { return newsList; }

    public void setNewsList(List<ModelNews> newsList) {
        this.newsList = newsList;
    }
}

