package mp.piash.tg.fragment;


import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private TextView mTextViewCash;
    private TextView mTextViewPersonalBankAccount;
    private TextView mTextViewCompanyBankAccount;
    private Button mButtonBack;
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
        mTextViewCash = (TextView)view.findViewById(R.id.tvCash);
        mTextViewPersonalBankAccount = (TextView)view.findViewById(R.id.tvPersonalBankAccount);
        mTextViewCompanyBankAccount = (TextView)view.findViewById(R.id.tvPersonalBankAccount);
        mButtonBack = (Button) view.findViewById(R.id.BackButton);
        mTextViewWithdraw = (TextView)view.findViewById(R.id.tvWithdrawAmount);
        mTextViewDeposit = (TextView)view.findViewById(R.id.tvDepositAmount);
        mEditTextWithdrawAmount = (EditText)view.findViewById(R.id.etWithdrawAmount);

        mTechGaintHandler = new TechGaintHandler(getActivity());
        mTextViewCash.setText("Cash - $" +String.valueOf(mTechGaintHandler.getAllCash().get(0)));
        backButton();
        clickEvent();
        return view;
    }

    private void backButton(){
        mButtonBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getFragmentManager().popBackStack();
                    }
                }
        );
    }
    public void clickEvent(){

        mTextViewWithdraw.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!mEditTextWithdrawAmount.getText().toString().isEmpty() && mTechGaintHandler.getAllCash().get(0) > Integer.valueOf(mEditTextWithdrawAmount.getText().toString()) ){
                            showDialogWithDraw( "withdraw",Integer.valueOf(mEditTextWithdrawAmount.getText().toString()));
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
                        if (!mEditTextWithdrawAmount.getText().toString().isEmpty() && Integer.parseInt(mEditTextWithdrawAmount.getText().toString()) >= 100 && mTechGaintHandler.getAllCash().get(0) >= Integer.parseInt(mEditTextWithdrawAmount.getText().toString())){
                            showDialogDeposit("Deposit" ,Integer.valueOf(mEditTextWithdrawAmount.getText().toString()));
                        }else {
                            if (Integer.parseInt(mEditTextWithdrawAmount.getText().toString()) < 100){
                                Toast.makeText(getActivity(), " Minimum Amount to open an Account is $100", Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();

                            }
                        }


                    }
                }
        );
    }
    public void showDialogDeposit(String mString, final int balance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm");
        builder.setMessage("Do you want to "+mString+" " + balance);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                int totalSumision =  mTechGaintHandler.getAllCompanyPersonalBankAccount().get(0) + balance ;
                int totalSubstract =  mTechGaintHandler.getAllCash().get(0) - balance ;
                mTechGaintHandler.updatePersonalBankAccount(totalSumision);
                mTechGaintHandler.updateCash(totalSubstract);
                Toast.makeText(getActivity(), "your current Balance is "+mTechGaintHandler.getAllCash().get(0), Toast.LENGTH_SHORT).show();
                mTextViewCash.setText("Cash - $" +String.valueOf(mTechGaintHandler.getAllCash().get(0)));
                mTextViewPersonalBankAccount.setText("Personal BankAccount - $" +String.valueOf(mTechGaintHandler.getAllCompanyPersonalBankAccount().get(0)));

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

    public void showDialogWithDraw(String mString, final int balance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm");
        builder.setMessage("Do you want to "+mString+" " + balance);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                int total = mTechGaintHandler.getAllCash().get(0) - balance;
                mTechGaintHandler.updateCash(total);
                Toast.makeText(getActivity(), "your current Balance is "+mTechGaintHandler.getAllCash().get(0), Toast.LENGTH_SHORT).show();
                mTextViewCash.setText("Cash - $" +String.valueOf(mTechGaintHandler.getAllCash().get(0)));

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
