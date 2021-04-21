package code.id.apps.news.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import code.id.apps.R;
import code.id.apps.news.utils.general.AppData;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

/*
 * Created by Edo Aditya Novendy on 22/4/2021
 */
public class ActivityWeb extends AppCompatActivity {

    @BindView(R.id.web_article)
    WebView webArticle;

    @BindView(R.id.toolbar)
    Toolbar toolBar;

    @BindView(R.id.search_news)
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        searchView.setVisibility(View.GONE);
        setSupportActionBar(toolBar);
        webArticle.loadUrl(AppData.source);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}