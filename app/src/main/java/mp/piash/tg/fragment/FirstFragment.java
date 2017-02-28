package mp.piash.tg.fragment;



import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mp.piash.tg.R;
import mp.piash.tg.adapter.AdapterPlay;
import mp.piash.tg.database.TechGaintHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {
    private AdapterPlay mAdapterPlay;
    private RecyclerView mRecyclerViewPlay;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> mStringList = new ArrayList<>();
    private List<Integer> mIntegerList = new ArrayList<>();
    private TechGaintHandler mTechGaintHandler;
    private ProgressBar mProgesBarHealth;
    private ProgressBar mProgressBarExperience;
    private ProgressBar mProgressBarComapnyExperience;
    private ProgressBar mProgesBarEquity;
    private TextView mTextViewHealth;
    private TextView mTextViewExperience;
    private TextView mTextViewCompanyExperience;
    private TextView mTextViewEquity;

    public FirstFragment(){

    }
    public FirstFragment newIstance(List<String > stringList, List<Integer> integerList) {
        // Required empty public constructor
        FirstFragment blankFragment = new FirstFragment();
        this.mStringList = stringList;
        this.mIntegerList = integerList;
        return blankFragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_first, container, false);
        mProgesBarHealth = (ProgressBar)view.findViewById(R.id.progressBarHealth);
        mProgressBarExperience = (ProgressBar)view.findViewById(R.id.progressBarExperience);
        mProgressBarComapnyExperience = (ProgressBar)view.findViewById(R.id.progressBarComapnyExperience);
        mProgesBarEquity = (ProgressBar)view.findViewById(R.id.progressBarEauity);
        mTextViewHealth = (TextView)view.findViewById(R.id.txtHealthValue);
        mTextViewExperience = (TextView)view.findViewById(R.id.txtExperienceValue);
        mTextViewEquity = (TextView)view.findViewById(R.id.txtCompanyEquityValue);
        mTextViewCompanyExperience = (TextView)view.findViewById(R.id.txtCompanyExperienceValue);
        mTechGaintHandler = new TechGaintHandler(getActivity());
        updateProgressBar();
       /* mRecyclerViewPlay = (RecyclerView)view.findViewById(R.id.recyclerViewPlay);
        for (int i = 0; i <11 ; i++) {
            mStringList.add("Testing Text" + i);
        }
        Integer[] myImageList = new Integer[]{R.drawable.bank, R.drawable.education, R.drawable.index22, R.drawable.lodging};
        String[] albums = getResources().getStringArray(R.array.third);
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerViewPlay.setLayoutManager(mLayoutManager);
        mAdapterPlay = new AdapterPlay( getActivity(), mStringList, mIntegerList);
        mRecyclerViewPlay.setAdapter(mAdapterPlay);*/
        return view;
    }
    private void updateProgressBar(){
        mProgesBarHealth.setProgress(mTechGaintHandler.getAllHealthData().get(0).intValue());
        mProgressBarExperience.setProgress(mTechGaintHandler.getAllExperienceData().get(0).intValue());
        mProgesBarEquity.setProgress(100);
        mProgressBarComapnyExperience.setProgress(mTechGaintHandler.getAllCompanyExperienceData().get(0).intValue());
        mTextViewHealth.setText(mTechGaintHandler.getAllHealthData().get(0).toString());
        mTextViewExperience.setText(mTechGaintHandler.getAllExperienceData().get(0).toString());
        mTextViewEquity.setText(String.valueOf(100));
        mTextViewCompanyExperience.setText(mTechGaintHandler.getAllCompanyExperienceData().get(0).toString());

    }
}
