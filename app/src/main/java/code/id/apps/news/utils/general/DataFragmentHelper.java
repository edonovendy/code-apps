package code.id.apps.news.utils.general;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import code.id.apps.R;

/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public class DataFragmentHelper {
    private FragmentManager fragmentManagerHelper;

    public void setDataFragmentHelper(FragmentManager fragmentManager){
        this.fragmentManagerHelper = fragmentManager;
    }


    public void returnLastFragment(){
        AppData.fragments.pop();
        Fragment fr = AppData.fragments.peek();
        FragmentManager fragmentManager = this.fragmentManagerHelper;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fr);
        fragmentTransaction.commit();
    }

    public void changeFragment(Fragment fr){
        AppData.fragments.push(fr);
        FragmentManager fragmentManager = this.fragmentManagerHelper;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fr);
        fragmentTransaction.commit();
    }


}
