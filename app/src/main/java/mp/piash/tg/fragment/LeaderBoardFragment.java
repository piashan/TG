package mp.piash.tg.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mp.piash.tg.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderBoardFragment extends Fragment {


    public LeaderBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leader_board, container, false);
    }

}
