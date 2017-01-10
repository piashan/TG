package mp.piash.tg.adapter;



import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import mp.piash.tg.fragment.FirstFragment;
import mp.piash.tg.fragment.FouthFragment;
import mp.piash.tg.fragment.SecondFragment;
import mp.piash.tg.fragment.SettingFragment;
import mp.piash.tg.fragment.ThirdFragment;


/**
 * Created by piash on 11/24/16.
 */

public class TabFragmentPageAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[] = new String[] { "age", "Salary" ,"Net worth", "Education", "job"};
    public TabFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 4){
            return new SettingFragment();
        }else if (position == 1){
            return new SecondFragment();
        }else if (position == 2){
            return new ThirdFragment();
        }else if (position == 3){
            return new FouthFragment();
        }else {
            return new FirstFragment();

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
