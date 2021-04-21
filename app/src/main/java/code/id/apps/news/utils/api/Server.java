package code.id.apps.news.utils.api;

/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public class Server {

    private Server() { }

    public static final String URL_API = "https://newsapi.org/";

    public static ApiService getApiService(){
        return RetrofitApi.getClient(URL_API).create(ApiService.class);
    }
}
