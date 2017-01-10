package mp.piash.tg.fragment;



import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import mp.piash.tg.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainContentFragment extends Fragment {

    private Button mButtonPlay;
    private ImageView mImageViewSetting;
    private TextView mTextViewAcievement;
    private ImageView mImageViewHelp;
    public MainContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_content, container, false);
        mButtonPlay = (Button)view.findViewById(R.id.buttonPlay);
        mImageViewSetting = (ImageView)view.findViewById(R.id.imageViewSetting);
        mTextViewAcievement = (TextView)view.findViewById(R.id.textViewAchievement);
        mImageViewHelp = (ImageView)view.findViewById(R.id.imagViewhelp);

        play();
        setting();
        achievement();
        help();
      /*  android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.activity_main, new MainContentFragment());
        ft.commit();*/


        return view;
    }

    private void play(){
        mButtonPlay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        android.app.FragmentManager fm = getActivity().getFragmentManager();
                        android.app.FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.activity_main, new TabbedFragment());
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                }
        );

    }
    private void setting(){
        mImageViewSetting.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        android.app.FragmentManager fm = getActivity().getFragmentManager();
                        android.app.FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.activity_main, new SettingFragment());
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                }
        );
    }

    private void achievement(){

        mTextViewAcievement.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        android.app.FragmentManager fm = getActivity().getFragmentManager();
                        android.app.FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.activity_main, new AchievementFragment());
                        ft.addToBackStack(null);
                        ft.commit();
                       /* FragmentManager fm = getChildFragmentManager();
                        DialogFragment newFragment = new AchievementFragment();
                        newFragment.show(fm, "dialog");*/
                    }
                }
        );
    }

    private void help(){
        mImageViewHelp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        android.app.FragmentManager fm = getActivity().getFragmentManager();
                        android.app.FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.activity_main, new BankAccountFragment());
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                }
        );
    }
}
