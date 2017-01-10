package mp.piash.tg.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;

import mp.piash.tg.R;
import mp.piash.tg.adapter.AdapterDetail;
import mp.piash.tg.adapter.AdapterPlay;
import mp.piash.tg.interfaces.RVClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private AdapterDetail mAdapterDetail;
    private RecyclerView mRecyclerViewDetail;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mString;
    private String[] mStringValues;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_detail, container, false);
        mRecyclerViewDetail = (RecyclerView)view.findViewById(R.id.recyclerviewDetail);

        setTitleAndValues();
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerViewDetail.setLayoutManager(mLayoutManager);
        mAdapterDetail = new AdapterDetail( getActivity(), Arrays.asList(mString), Arrays.asList(mStringValues));
        mRecyclerViewDetail.setAdapter(mAdapterDetail);
        mAdapterDetail.setOnClickListener(
                new RVClickListener() {
                    @Override
                    public void onItemClicked(View view, int position) {
                        Toast.makeText(getActivity(), "Detail aslo working", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        return view;
    }

    private void setTitleAndValues(){

        if (getArguments().getInt("position") == 0){
            mString = getResources().getStringArray(R.array.SecondEducationString);
            mStringValues = getResources().getStringArray(R.array.SecondEducationValue);
        }else if (getArguments().getInt("position") == 1){
            mString = getResources().getStringArray(R.array.SecondSkillsString);
            mStringValues = getResources().getStringArray(R.array.SecondSkillsValue);
        }

    }
}
