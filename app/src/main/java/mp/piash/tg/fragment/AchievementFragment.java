package mp.piash.tg.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mp.piash.tg.R;
import mp.piash.tg.adapter.AdapterPlay;

/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementFragment extends DialogFragment {
    private AdapterPlay mAdapterPlay;
    private RecyclerView mRecyclerViewPlay;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> mStringList;

    public AchievementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_achievement, container, false);
        mRecyclerViewPlay = (RecyclerView)view.findViewById(R.id.recyclerViewAchievement);

        mStringList = new ArrayList<>();
        for (int i = 0; i <11 ; i++) {
            mStringList.add("Testing Text" + i);
        }
        /*mLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerViewPlay.setLayoutManager(mLayoutManager);
        mAdapterPlay = new AdapterPlay( getActivity(),mStringList, );
        mRecyclerViewPlay.setAdapter(mAdapterPlay);*/
        return view;
    }

}
