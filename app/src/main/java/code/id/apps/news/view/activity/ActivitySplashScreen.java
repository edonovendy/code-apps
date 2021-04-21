package code.id.apps.news.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import code.id.apps.R;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public class ActivitySplashScreen extends AppCompatActivity {

    private int mWaited = 0;
    @BindView(R.id.pbWelcome)
    ProgressBar pbWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        loadingProgressBar();
    }

    private void loadingProgressBar() {
        final Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <= 500; i++) {
                        sleep(4);
                        pbWelcome.setProgress(mWaited / 5);
                        mWaited += 1;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(getBaseContext(), ActivityHome.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }
            }

        };
        splashThread.start();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
