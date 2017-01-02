package mp.piash.tg.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        mAdapter = new TabFragmentPageAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
      for (int i = 0; i < mTabLayout.getTabCount(); i++){
          mTabLayout.getTabAt(i).setIcon(R.mipmap.ic_settings_black_24dp);
      }
        return view;
    }

}
