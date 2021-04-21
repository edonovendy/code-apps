package code.id.apps.news.utils.general;


/*
 * Created by Edo Aditya Novendy on 21/4/2021
 */
public class PersistenceDataHelper {
    private static PersistenceDataHelper persistenceDataHelper;
    public DataFragmentHelper fragmentHelper;

    private PersistenceDataHelper(){
        fragmentHelper = new DataFragmentHelper();
    }

    public static PersistenceDataHelper getInstance(){
        if( persistenceDataHelper == null)
        {
            persistenceDataHelper = new PersistenceDataHelper();
        }
        return persistenceDataHelper;
    }



}
