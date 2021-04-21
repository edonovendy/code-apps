package code.id.apps.news.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import code.id.apps.BuildConfig;
import code.id.apps.R;
import code.id.apps.news.adapter.AdapterNews;
import code.id.apps.news.model.ModelNews;
import code.id.apps.news.utils.api.ApiService;
import code.id.apps.news.utils.api.ResponseApi;
import code.id.apps.news.utils.api.Server;
import code.id.apps.news.utils.general.AppData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public class FragmentNews extends Fragment {

    private AdapterNews adapterNews;
    private List<ModelNews> modelNews;
    private ProgressDialog progressLoading;
    private ApiService apiService;

    @BindView(R.id.list_news)
    RecyclerView rvNews;

    @BindView(R.id.text_empty)
    TextView tvEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, rootView);
        apiService = Server.getApiService();
        modelNews = new ArrayList<>();
        adapterNews = new AdapterNews(getActivity(), modelNews);

        rvNews.setHasFixedSize(true);
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNews.setAdapter(adapterNews);
        GetNews(AppData.country, AppData.category, AppData.search);

        return rootView;
    }


    public void GetNews(String country, String category, String search) {
        progressLoading = new ProgressDialog(getActivity());
        progressLoading.setCancelable(false);
        progressLoading.setMessage("Loading...");
        showDialog();

        if(search.isEmpty()) {
            apiService.getListNews(country, category, BuildConfig.NEWS_API_TOKEN).enqueue(new Callback<ResponseApi>() {
                @Override
                public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                    if (response.isSuccessful()) {
                        hideDialog();
                        if(response.body().getTotalResults() == 0){
                            tvEmpty.setVisibility(View.VISIBLE);
                            rvNews.setVisibility(View.GONE);
                        }
                        else {
                            tvEmpty.setVisibility(View.GONE);
                            rvNews.setVisibility(View.VISIBLE);
                            modelNews = response.body().getNewsList();
                            rvNews.setAdapter(new AdapterNews(getActivity(), modelNews));
                            adapterNews.notifyDataSetChanged();
                        }
                    } else {
                        hideDialog();
                        Toast.makeText(getActivity(), "Gagal mengambil data !", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseApi> call, Throwable t) {
                    hideDialog();
                    Toast.makeText(getActivity(), "Gagal menyambung ke internet !", Toast.LENGTH_SHORT).show();
                }
            });
        } else{
            apiService.getListSearchNews(country, search, BuildConfig.NEWS_API_TOKEN).enqueue(new Callback<ResponseApi>() {
                @Override
                public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                    if (response.isSuccessful()) {
                        hideDialog();
                        if(response.body().getTotalResults() == 0){
                            tvEmpty.setVisibility(View.VISIBLE);
                            rvNews.setVisibility(View.GONE);
                        }
                        else {
                            tvEmpty.setVisibility(View.GONE);
                            rvNews.setVisibility(View.VISIBLE);
                            modelNews = response.body().getNewsList();
                            rvNews.setAdapter(new AdapterNews(getActivity(), modelNews));
                            adapterNews.notifyDataSetChanged();
                        }
                    } else {
                        hideDialog();
                        Toast.makeText(getActivity(), "Gagal mengambil data !", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseApi> call, Throwable t) {
                    hideDialog();
                    Toast.makeText(getActivity(), "Gagal menyambung ke internet !", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showDialog() {
        if (!progressLoading.isShowing())
            progressLoading.show();
    }

    private void hideDialog() {
        if (progressLoading.isShowing())
            progressLoading.dismiss();
    }
}
