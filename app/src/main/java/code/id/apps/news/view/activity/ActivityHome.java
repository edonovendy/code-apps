package code.id.apps.news.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import code.id.apps.R;
import code.id.apps.news.utils.general.AppData;
import code.id.apps.news.utils.general.DataFragmentHelper;
import code.id.apps.news.utils.general.PersistenceDataHelper;
import code.id.apps.news.view.fragment.FragmentDrawer;
import code.id.apps.news.view.fragment.FragmentNews;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public class ActivityHome extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener   {

    //private ActionBar actionBar;
    private FragmentDrawer drawerFragment;
    private DataFragmentHelper datafragmentHelper;
    private static long backPressed;
    private static final int TIME_LIMIT = 1800;
    private final int BUSINESS = 0;
    private final int ENTERTAINMENT = 1;
    private final int HEALTH = 2;
    private final int SCIENCE = 3;
    private final int SPORTS = 4;
    private final int TECHNOLOGY = 5;
    private final int NEWS_ALL = 6;
    private final int NEWS_SEARCH = 7;
    private String strQuery = "";

    @BindView(R.id.toolbar)
    Toolbar toolBar;

    @BindView(R.id.search_news)
    SearchView searchNews;

    @BindView(R.id.title)
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        datafragmentHelper = PersistenceDataHelper.getInstance().fragmentHelper;
        searchNews.setVisibility(View.VISIBLE);
        setSupportActionBar(toolBar);
        drawerFragment = (FragmentDrawer) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        drawerFragment.setUp(R.id.navigation_drawer, findViewById(R.id.drawer_layout), toolBar);
        drawerFragment.setDrawerListener(this);

        searchNews.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                strQuery = query;
                displayView(NEWS_SEARCH);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        displayView(NEWS_ALL);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (TIME_LIMIT + backPressed > System.currentTimeMillis()) {
                moveTaskToBack(true);
            } else {
                Toast.makeText(this, "Press back again to exit.", Toast.LENGTH_SHORT).show();
            }
            backPressed = System.currentTimeMillis();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        int x = position;
        displayView(x);
    }

    public void displayView(int position) {
        Fragment fragment = new FragmentNews();
        switch (position) {
            case BUSINESS:
                AppData.category = "Business";
                AppData.search = "";
                searchNews.onActionViewCollapsed();
                break;
            case ENTERTAINMENT:
                AppData.category = "Entertainment";
                AppData.search = "";
                searchNews.onActionViewCollapsed();
                break;
            case HEALTH:
                AppData.category = "Health";
                AppData.search = "";
                searchNews.onActionViewCollapsed();
                break;
            case SCIENCE:
                AppData.category = "Science";
                AppData.search = "";
                searchNews.onActionViewCollapsed();
                break;
            case SPORTS:
                AppData.category = "Sports";
                AppData.search = "";
                searchNews.onActionViewCollapsed();
                break;
            case TECHNOLOGY:
                AppData.category = "Technology";
                AppData.search = "";
                searchNews.onActionViewCollapsed();
                break;
            case NEWS_ALL:
                AppData.category = "General";
                AppData.search = "";
                searchNews.onActionViewCollapsed();
                break;
            case NEWS_SEARCH:
                AppData.category = "";
                AppData.search = strQuery;
                break;
            default:
                break;
        }

        Log.d("ActivityHome", AppData.category);
        if(!AppData.category.isEmpty())
            title.setText(AppData.category);
        else
            title.setText(("Search "));

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            datafragmentHelper.setDataFragmentHelper(fragmentManager);
            datafragmentHelper.changeFragment(fragment);
        }
    }
}