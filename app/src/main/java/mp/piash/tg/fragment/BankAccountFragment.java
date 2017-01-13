package mp.piash.tg.fragment;


import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mp.piash.tg.R;
import mp.piash.tg.database.TechGaintHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class BankAccountFragment extends Fragment {

    public static final String TAG ="Account";
    private TextView mTextViewAccount;
    private TextView mTextViewWithdraw;
    private TextView mTextViewDeposit;
    private TechGaintHandler mTechGaintHandler;
    private EditText mEditTextWithdrawAmount;

    public BankAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_bank_account, container, false);
        mTextViewAccount = (TextView)view.findViewById(R.id.tvBankAccount);
        mTextViewWithdraw = (TextView)view.findViewById(R.id.tvWithdrawAmount);
        mTextViewDeposit = (TextView)view.findViewById(R.id.tvDepositAmount);
        mEditTextWithdrawAmount = (EditText)view.findViewById(R.id.etWithdrawAmount);

        mTechGaintHandler = new TechGaintHandler(getActivity());
        mTextViewAccount.setText("Bank Account - $" +String.valueOf(mTechGaintHandler.getAllWishlistData().get(0)));
        clickEvent();
        return view;
    }

    public void clickEvent(){

        mTextViewWithdraw.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!mEditTextWithdrawAmount.getText().toString().isEmpty()){
                            showDialog( "withdraw",Integer.valueOf(mEditTextWithdrawAmount.getText().toString()));
                        }else {
                            Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        mTextViewDeposit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!mEditTextWithdrawAmount.getText().toString().isEmpty()){
                            showDialog("Deposit" ,Integer.valueOf(mEditTextWithdrawAmount.getText().toString()));
                        }else {
                            Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
        );
    }
    public void showDialog(String mString, final int balance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm");
        builder.setMessage("Do you want to "+mString+" " + balance);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                int total = balance + mTechGaintHandler.getAllWishlistData().get(0);
                mTechGaintHandler.updataData(total);
                Toast.makeText(getActivity(), "your current Balance is "+mTechGaintHandler.getAllWishlistData().get(0), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

}
