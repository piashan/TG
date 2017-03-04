package mp.piash.tg.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mp.piash.tg.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnlockableFragment extends Fragment {

    private Button mButtonOne;
    public UnlockableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_unlockable, container, false);
        mButtonOne = (Button)view.findViewById(R.id.buttonOne);

        mButtonOne.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast("paypal link will be provided");
                    }
                }
        );

        return view;
    }
    private void Toast(String string){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_root,
                (ViewGroup)getView().findViewById(R.id.toast_layout_root));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(string);

        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
