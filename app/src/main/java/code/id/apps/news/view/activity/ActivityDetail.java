package code.id.apps.news.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import code.id.apps.R;
import code.id.apps.news.utils.general.AppData;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public class ActivityDetail extends AppCompatActivity {

    @BindView(R.id.img)
    ImageView tvImg;

    @BindView(R.id.title)
    TextView tvTitle;

    @BindView(R.id.article)
    TextView tvArticle;

    @BindView(R.id.date)
    TextView tvDate;

    @BindView(R.id.author)
    TextView tvAuthor;

    String source = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        String img = getIntent().getStringExtra("imgNews");
        String title = getIntent().getStringExtra("titleNews");
        String article = getIntent().getStringExtra("contentNews");
        String date = getIntent().getStringExtra("dateNews");
        String author = getIntent().getStringExtra("authorNews");
        source = getIntent().getStringExtra("sourceNews");

        Glide.with(getApplicationContext())
                .load(img)
                .placeholder(R.drawable.img_default)
                .into(tvImg);
        tvTitle.setText(title);
        tvArticle.setText(article);
        tvDate.setText(date);
        tvAuthor.setText("Ditulis oleh "+author);

        if(getSupportActionBar() != null)getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void goToSource(View view) {
        AppData.source = source;
        Intent intent = new Intent(ActivityDetail.this, ActivityWeb.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(ActivityDetail.this, ActivityHome.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityDetail.this, ActivityHome.class);
        startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

}
