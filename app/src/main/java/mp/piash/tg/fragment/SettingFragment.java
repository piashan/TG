package mp.piash.tg.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import mp.piash.tg.MainActivity;
import mp.piash.tg.R;
import mp.piash.tg.utility.AppRater;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    private ImageView mImageViewRateApp;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_setting, container, false);
        mImageViewRateApp = (ImageView)view.findViewById(R.id.imageViewRateApp);

        mImageViewRateApp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppRater.app_launched(getActivity());
                        Toast.makeText(getActivity(), "Rate the app", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        return view;
    }

}
