package code.id.apps.news.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import code.id.apps.BuildConfig;
import code.id.apps.R;
import code.id.apps.news.adapter.AdapterNews;
import code.id.apps.news.adapter.AdapterNewsSource;
import code.id.apps.news.model.ModelNews;
import code.id.apps.news.model.ModelSource;
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

    private static AdapterNews adapterNews;
    private static RecyclerView rvNews;
    private List<ModelNews> modelNews = new ArrayList<>();;
    private List<ModelNews> modelNewsCopy = new ArrayList<>();;
    private AdapterNewsSource adapterSource;
    private List<ModelSource> modelSource;
    private ProgressDialog progressLoading;
    private ApiService apiService;

    @BindView(R.id.list_source)
    RecyclerView rvSource;

    @BindView(R.id.text_empty)
    TextView tvEmpty;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, rootView);
        apiService = Server.getApiService();

        rvNews = rootView.findViewById(R.id.list_news);
        adapterNews = new AdapterNews(getActivity(), modelNews);
        adapterSource = new AdapterNewsSource(getActivity(), modelSource);

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
                            rvSource.setVisibility(View.GONE);
                        }
                        else {
                            tvEmpty.setVisibility(View.GONE);
                            rvNews.setVisibility(View.VISIBLE);
                            rvSource.setVisibility(View.VISIBLE);

                            //Get all news on selected category
                            modelNews = response.body().getNewsList();
                            AppData.listModelNews = modelNews;
                            rvNews.setAdapter(new AdapterNews(getActivity(), modelNews));
                            adapterNews.notifyDataSetChanged();

                            //Get news sources from selected category
                            List<ModelSource> sourceList = new ArrayList<>();
                            sourceList.add(new ModelSource("All", "All"));
                            for(int i=0;i<modelNews.size();i++){
                                String source_name = modelNews.get(i).getSource().getName();
                                if(!containsName(sourceList, source_name)){
                                    sourceList.add(new ModelSource(source_name,source_name.trim()));
                                }
                            }

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            linearLayoutManager.setSmoothScrollbarEnabled(false);
                            modelSource = sourceList;
                            rvSource.setLayoutManager(linearLayoutManager);
                            rvSource.setAdapter(new AdapterNewsSource(getActivity(), modelSource));
                            adapterSource.notifyDataSetChanged();
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

                            //Get news sources from selected category
                            List<ModelSource> sourceList = new ArrayList<>();
                            for(int i=0;i<modelNews.size();i++){
                                String source_name = modelNews.get(i).getSource().getName();
                                if(!containsName(sourceList, source_name)){
                                    sourceList.add(new ModelSource(source_name,source_name.trim()));
                                }
                            }

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            linearLayoutManager.setSmoothScrollbarEnabled(false);
                            modelSource = sourceList;
                            rvSource.setLayoutManager(linearLayoutManager);
                            rvSource.setAdapter(new AdapterNewsSource(getActivity(), modelSource));
                            adapterSource.notifyDataSetChanged();
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

    public static boolean containsName(List<ModelSource> list, String name) {
        for (ModelSource object : list) {
            if (object.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void filter(Context context, String filterText) {
        filterText = filterText.toLowerCase(Locale.getDefault());
        if(filterText.equalsIgnoreCase("All")){
            modelNews = AppData.listModelNews;
        }else {
            for (ModelNews news : AppData.listModelNews) {
                if (news.getSource().getName().toLowerCase(Locale.getDefault()).contains(filterText)) {
                    modelNews.add(news);
                }
            }
        }
        rvNews.setAdapter(new AdapterNews(context, modelNews));
        adapterNews.notifyDataSetChanged();

    }
}
