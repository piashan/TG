package mp.piash.tg.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import mp.piash.tg.R;
import mp.piash.tg.adapter.TabFragmentPageAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabbedFragment extends Fragment {

    private TabFragmentPageAdapter mAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    public TabbedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabbed, container, false);
        mViewPager = (ViewPager)view.findViewById(R.id.viewpager);
        mTabLayout = (TabLayout)view.findViewById(R.id.sliding_tabs);
      /*  Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);*/

       // mAdapter = new TabFragmentPageAdapter(getChildFragmentManager());

        Integer[] myImageList = new Integer[]{R.drawable.bank, R.drawable.education, R.drawable.index22, R.drawable.lodging};
        String[] albums = getResources().getStringArray(R.array.third);
        mAdapter = new TabFragmentPageAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
      for (int i = 0; i < mTabLayout.getTabCount(); i++){
          if (i == 0){
              mTabLayout.getTabAt(i).setIcon(R.drawable.index11);
          }else if (i == 1){
              mTabLayout.getTabAt(i).setIcon(R.drawable.index22);
          }else if (i == 2){
              mTabLayout.getTabAt(i).setIcon(R.drawable.index33);
          }else if (i == 3){
              mTabLayout.getTabAt(i).setIcon(R.drawable.index44);
          }else if (i == 4){
              mTabLayout.getTabAt(i).setIcon(R.mipmap.ic_settings_black_24dp);
          }
      }
        return view;
    }

}
