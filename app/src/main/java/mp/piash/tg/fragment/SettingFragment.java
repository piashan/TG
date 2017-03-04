package mp.piash.tg.fragment;


import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
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
    private ImageView mImageViewShareApp;
    private ImageView mImageViewUnlock;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_setting, container, false);
        mImageViewRateApp = (ImageView)view.findViewById(R.id.imageViewRateApp);
        mImageViewShareApp = (ImageView)view.findViewById(R.id.imageViewShateApp);
        mImageViewUnlock = (ImageView)view.findViewById(R.id.imageViewUnlock);

        clickEvent();

        return view;
    }
    private void clickEvent(){

        mImageViewRateApp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /*AppRater.app_launched(getActivity());
                        Toast.makeText(getActivity(), "Rate the app", Toast.LENGTH_SHORT).show();*/
                        Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                        }
                    }
                }
        );

        mImageViewShareApp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int applicationNameId = getActivity().getApplicationInfo().labelRes;
                        final String appPackageName = getActivity().getPackageName();
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.putExtra(Intent.EXTRA_SUBJECT, getActivity().getString(applicationNameId));
                        String text = "Install this cool application: ";
                        String link = "https://play.google.com/store/apps/details?id=" + appPackageName;
                        i.putExtra(Intent.EXTRA_TEXT, text + " " + link);
                        startActivity(Intent.createChooser(i, "Share link:"));
                    }
                }
        );

        mImageViewUnlock.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        android.app.FragmentManager fm = getActivity().getFragmentManager();
                        android.app.FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.activity_main,new UnlockableFragment());
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                }
        );
    }

}
