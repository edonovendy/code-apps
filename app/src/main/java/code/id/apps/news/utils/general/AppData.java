package code.id.apps.news.utils.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import androidx.fragment.app.Fragment;
import code.id.apps.news.model.ModelNews;

/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public class AppData {
    public static Stack<Fragment> fragments = new Stack<>();
    public static String country = "id";
    public static String category = "general";
    public static String search = "";
    public static String url = "";
    public static String news_source = "";
    public static ModelNews modelNews = new ModelNews();
    public static List<ModelNews> listModelNews = new ArrayList<>();
}
