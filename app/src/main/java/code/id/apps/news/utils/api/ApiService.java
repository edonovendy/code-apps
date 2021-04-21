package code.id.apps.news.utils.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public interface ApiService {

    @GET("/v2/top-headlines")
    Call<ResponseApi> getListNews(@Query("country") String country, @Query("category") String category, @Query("apiKey") String apiKey);

    @GET("/v2/top-headlines")
    Call<ResponseApi> getListSearchNews(@Query("country") String country, @Query("q") String search, @Query("apiKey") String apiKey);

    @GET("/v2/top-headlines")
    Call<ResponseApi> getListAllNews(@Query("country") String country, @Query("apiKey") String apiKey);


}
