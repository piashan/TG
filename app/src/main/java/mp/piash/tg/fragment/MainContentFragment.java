package mp.piash.tg.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mp.piash.tg.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainContentFragment extends Fragment {

    private Button mButtonPlay;
    public MainContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_content, container, false);
        mButtonPlay = (Button)view.findViewById(R.id.buttonPlay);

        mButtonPlay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.activity_main, new TabbedFragment());
                        ft.commit();
                    }
                }
        );

      /*  android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.activity_main, new MainContentFragment());
        ft.commit();*/


        return view;
    }

}
