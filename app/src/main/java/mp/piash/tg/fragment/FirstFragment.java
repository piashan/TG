package mp.piash.tg.fragment;



import android.os.Bundle;
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
public class FirstFragment extends Fragment {
    private AdapterPlay mAdapterPlay;
    private RecyclerView mRecyclerViewPlay;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> mStringList = new ArrayList<>();
    private List<Integer> mIntegerList = new ArrayList<>();

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

}
