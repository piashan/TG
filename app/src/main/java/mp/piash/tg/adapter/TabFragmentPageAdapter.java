package mp.piash.tg.adapter;



import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import mp.piash.tg.fragment.BlankFragment;
import mp.piash.tg.fragment.SettingFragment;


/**
 * Created by piash on 11/24/16.
 */

public class TabFragmentPageAdapter extends android.support.v4.app.FragmentStatePagerAdapter{

    private String tabTitles[] = new String[] { "age", "Salary" ,"Net worth", "Education", "job"};

    public TabFragmentPageAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        if (position == 4){
            return new SettingFragment();
        }else {
            return new BlankFragment();
        }

    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
