package code.id.apps.news.utils.general;

import android.app.Application;
import code.id.apps.R;
import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Gotham.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

    }

}
