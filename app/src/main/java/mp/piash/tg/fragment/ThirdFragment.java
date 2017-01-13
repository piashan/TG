package mp.piash.tg.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mp.piash.tg.R;
import mp.piash.tg.adapter.AdapterPlay;
import mp.piash.tg.interfaces.RVClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    private AdapterPlay mAdapterPlay;
    private RecyclerView mRecyclerViewPlay;
    private RecyclerView.LayoutManager mLayoutManager;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_third, container, false);
        mRecyclerViewPlay = (RecyclerView)view.findViewById(R.id.recyclerThird);
        List<Integer> array_image = new ArrayList<Integer>();
        array_image.add(R.drawable.parttimejob);
        array_image.add( R.drawable.internship);
        array_image.add( R.drawable.fulltimejob);
        array_image.add( R.drawable.executivejob);
        String[] mString = getResources().getStringArray(R.array.third);
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerViewPlay.setLayoutManager(mLayoutManager);
        mAdapterPlay = new AdapterPlay( getActivity(), Arrays.asList(mString), array_image);
        mRecyclerViewPlay.setAdapter(mAdapterPlay);
        mAdapterPlay.setOnItemClickListener(
                new RVClickListener() {
                    @Override
                    public void onItemClicked(View view, int position) {
                        Toast.makeText(getActivity(), "Click is woking", Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putInt("position",position);
                        Fragment fragment = new DetailFragment();
                        fragment.setArguments(bundle);
                        android.app.FragmentManager fm = getActivity().getFragmentManager();
                        android.app.FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.activity_main,fragment);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                }
        );
        return view;
    }

}
