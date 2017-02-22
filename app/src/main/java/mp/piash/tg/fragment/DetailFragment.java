package mp.piash.tg.fragment;


import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mp.piash.tg.R;
import mp.piash.tg.adapter.AdapterDetail;
import mp.piash.tg.database.TechGaintHandler;
import mp.piash.tg.interfaces.RVClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private AdapterDetail mAdapterDetail;
    private RecyclerView mRecyclerViewDetail;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button mButtonBack;
    private List<String> mString;
    private List<String> mStringValues;
    private TechGaintHandler mTechGaintHandler;
    private ProgressBar mProgesBarHealth;
    private ProgressBar mProgressBarExperience;
    private ProgressBar mProgressBarEquity;
    private ProgressBar mProgressBarComapnyExperience;
    private TextView mTextViewHealth;
    private TextView mTextViewExperience;
    private TextView mTextViewEquity;
    private TextView mTextViewCompanyExperience;
    private TextView mTextViewCash;
    private TextView mTextViewPersonalBankAccount;



    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_detail, container, false);
        mRecyclerViewDetail = (RecyclerView)view.findViewById(R.id.recyclerviewDetail);
        mButtonBack = (Button)view.findViewById(R.id.buttonBackButton);
        mProgesBarHealth = (ProgressBar)view.findViewById(R.id.progressBarHealth);
        mProgressBarExperience = (ProgressBar)view.findViewById(R.id.progressBarExperience);
        mProgressBarEquity = (ProgressBar)view.findViewById(R.id.progressBarEauity);
        mProgressBarComapnyExperience = (ProgressBar)view.findViewById(R.id.progressBarComapnyExperience);
        mTextViewHealth = (TextView)view.findViewById(R.id.txtHealthValue);
        mTextViewEquity = (TextView)view.findViewById(R.id.txtEquityValue);
        mTextViewExperience = (TextView)view.findViewById(R.id.txtExperienceValue);
        mTextViewCompanyExperience = (TextView)view.findViewById(R.id.txtCompanyExperienceValue);
        mTextViewCash = (TextView)view.findViewById(R.id.tvCash);
        mTextViewPersonalBankAccount = (TextView)view.findViewById(R.id.tvPersonalBankAccount);
        mString = new ArrayList<>();
        mStringValues = new ArrayList<>();
        mTechGaintHandler = new TechGaintHandler(getActivity());
        setTitleAndValues();
        backButton();
        mLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerViewDetail.setLayoutManager(mLayoutManager);
        mAdapterDetail = new AdapterDetail( getActivity(),mString, mStringValues);
        mRecyclerViewDetail.setAdapter(mAdapterDetail);
        mAdapterDetail.setOnClickListener(
                new RVClickListener() {
                    @Override
                    public void onItemClicked(View view, int position) {
                        //Toast.makeText(getActivity(), ""+getArguments().getInt("viewpager")+getArguments().getInt("position")+position, Toast.LENGTH_SHORT).show();
                        UpdateDatabaseByLogic(position);


                    }
                }
        );
        mTextViewCash.setText("Cash  $" +String.valueOf(mTechGaintHandler.getAllCash().get(0)));
        mTextViewPersonalBankAccount.setText("Personal BankAccount $"+ String.valueOf(mTechGaintHandler.getAllPersonalBankAccount().get(0)));
        updateProgressBar();
        return view;
    }

    private void setTitleAndValues(){
         if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 1){
            mString = Arrays.asList(getResources().getStringArray(R.array.SecondEducationString));
            mStringValues = Arrays.asList(getResources().getStringArray(R.array.SecondEducationValue));
        }else if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2){
            mString = Arrays.asList(getResources().getStringArray(R.array.SecondSkillsString));
            mStringValues = Arrays.asList(getResources().getStringArray(R.array.SecondSkillsValue));
        }else if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3){
            mString = Arrays.asList(getResources().getStringArray(R.array.SecondLodgingString));
            mStringValues = Arrays.asList(getResources().getStringArray(R.array.SecondLodgingValue));
        }else if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4){
             mString = Arrays.asList(getResources().getStringArray(R.array.SecondHealtLeisureString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.SecondHealtLeisureValue));
         } else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0){
             mString = Arrays.asList(getResources().getStringArray(R.array.thirdPartTimeJobString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.thirdPartTimeJobValue));
         }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1){
             mString = Arrays.asList(getResources().getStringArray(R.array.thirdInternshipJobString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.thirdInternshipJobValue));
         }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 2){
             mString = Arrays.asList(getResources().getStringArray(R.array.thirdFullTimeJobString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.thirdFullTimeJobValue));
         }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 3){
             mString = Arrays.asList(getResources().getStringArray(R.array.thirdExecutiveJobString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.thirdExecutiveJobValue));
         } else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 0){
             mString = Arrays.asList(getResources().getStringArray(R.array.fouthBuildYourIdeaString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.fouthBuildYourIdeaValue));
         }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 1){
             mString = Arrays.asList(getResources().getStringArray(R.array.fouthDevelopYourCompanyString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.fouthDevelopYourCompanyValue));
         } else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 2){
             mString = Arrays.asList(getResources().getStringArray(R.array.fouthChooseOfficeString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.fouthChooseOfficeValue));
         }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 3){
             mString = Arrays.asList(getResources().getStringArray(R.array.fouthOfficeFurnitureString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.fouthOfficeFurnitureValue));
         }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 4){
             mString = Arrays.asList(getResources().getStringArray(R.array.fouthHireTalentString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.fouthHireTalentValue));
         } else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 5){
             mString = Arrays.asList(getResources().getStringArray(R.array.fouthCustomerBaseString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.fouthCustomerBaseValue));
         }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 6){
             mString = Arrays.asList(getResources().getStringArray(R.array.fouthFundingRoundString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.fouthFundingRoundValue));
         }else {
            mString = Arrays.asList(getResources().getStringArray(R.array.SecondEducationString));
            mStringValues = Arrays.asList(getResources().getStringArray(R.array.SecondEducationValue));
        }

    }
    public void UpdateDatabaseByLogic(int position){

        if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 1 && position == 0){

            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)),0, 5 ,0, 0, 110);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 1 && position == 1){

            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 10, 0, 0, 111);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }
        else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 1 && position == 2){

            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 10, 0, 0, 112);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }
        else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 1 && position == 3){

            if (mTechGaintHandler.isExists(110) <= 0 || mTechGaintHandler.isExists(112) <= 0){
              if (mTechGaintHandler.isExists(110) <= 0){
                  Toast.makeText(getActivity(), "please complete High School", Toast.LENGTH_SHORT).show();
              }else if (mTechGaintHandler.isExists(112) <= 0){
                  Toast.makeText(getActivity(), "Please Complete University", Toast.LENGTH_SHORT).show();
              }
            }else {
                if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                    showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 15, 0, 0, 113);
                }else {
                    Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
                }

            }
            // skill Fragment
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 0){

            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 1, 0, 0, 120);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }
        else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 1){

            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 1, 0, 0, 121);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 2){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 1, 0, 0, 122);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 3){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 2, 0, 0, 123);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 4){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 3, 0, 0, 124);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 5){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 4, 0, 0, 125);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 6){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 4, 0, 0, 126);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 7){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 5, 0, 0, 127);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 8){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 6, 0, 0, 128);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 9){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 7, 0, 0, 129);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }
        // Lodging Fragment
        else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 0){
            showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, -2, 0, 0, 130);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 1){
            showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, -1, 0, 0, 131);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 2){
            showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, -5, 0, 0, 132);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 3){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, -1, 0, 0, 133);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 4){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 0, 0, 0, 134);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 5){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 1, 0, 0, 135);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 6){

            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 2, 0, 0, 136);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 7){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 0, 0, 0, 137);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 8){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 5, 0, 0, 138);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 9){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 2, 0, 0, 139);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 10){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 2, 0, 0, 1310);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 11){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 2, 0, 0, 1311);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 12){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 2, 0, 0, 1312);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 13){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 3, 0, 0, 1313);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 14){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 3, 0, 0, 1314);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 15){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 5, 0, 0, 1315);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 16){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 7, 0, 0, 1316);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 17){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 8, 0, 0, 1317);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 18){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 8, 0, 0, 1318);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 19){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 10, 0, 0, 1319);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 20){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 20, 0, 0, 1320);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 21){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 0, 25, 0, 0, 1321);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }
        // Health and Leisure
        else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 0){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 1, -0.5, 0, 0, 140);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 1){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 1, -0.5, 0, 0, 141);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 2){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 5, 0.5, 0, 0, 142);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 3){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 1, 0.5, 0, 0, 143);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 4){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 3, 0.5, 0, 0, 144);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 5){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 5, 5, 0, 0, 145);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 6){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 2, 0, 0, 0, 146);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 7){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 5, 0, 0, 0, 147);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 8){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 5, -1, 0, 0, 148);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 9){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 10, -2.5, 0, 0, 149);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 10){
            if (mTechGaintHandler.getAllCash().get(0) > getInterger(mStringValues.get(position))){
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), -getInterger(mStringValues.get(position)) , 5, -5, 0, 0, 1410);
            }else {
                Toast.makeText(getActivity(), "Insufficient Balance", Toast.LENGTH_SHORT).show();
            }
        }
        // Part Time jobs
        else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 0){
            showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -1, 0, 0, 0, 200);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 1){
            showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -3, 0.5, 0, 0, 201);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 2){
            showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -3, 1, 0, 0, 202);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 3){
            showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 0, 0, 0, 203);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 4){
            showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 0, 0, 0, 204);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 5){
            showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 0, 0, 0, 205);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 6){
            if (mTechGaintHandler.isExists(134) <= 0 ){
                if (mTechGaintHandler.isExists(134) <= 0){
                    Toast.makeText(getActivity(), "please Buy a Bicycle", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 0, 0, 0, 206);
            }

        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 7){
            if (mTechGaintHandler.isExists(110) <= 0 ){
                if (mTechGaintHandler.isExists(110) <= 0){
                    Toast.makeText(getActivity(), "please Finish Highschool", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -10, 0, 0, 0, 207);
            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 8){
            if (mTechGaintHandler.isExists(110) <= 0 ||mTechGaintHandler.isExists(122) <= 0){
                if (mTechGaintHandler.isExists(110) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Highschool", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(122) <= 0){
                    Toast.makeText(getActivity(), "please Finish Communication Skills", Toast.LENGTH_SHORT).show();

                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 1, 0, 0, 208);
            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 9){
            if (mTechGaintHandler.isExists(110) <= 0 ||mTechGaintHandler.isExists(125) <= 0){
                if (mTechGaintHandler.isExists(110) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Highschool", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(125) <= 0){
                    Toast.makeText(getActivity(), "please Finish Business Skills", Toast.LENGTH_SHORT).show();

                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 3, 0, 0, 209);
            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 10){
            if (mTechGaintHandler.isExists(110) <= 0 ||mTechGaintHandler.isExists(122) <= 0 ||mTechGaintHandler.isExists(124) <= 0 ||mTechGaintHandler.isExists(125) <= 0){
                if (mTechGaintHandler.isExists(110) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Highschool", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(122) <= 0){
                    Toast.makeText(getActivity(), "please Finish Communication Skills", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(124) <= 0){
                    Toast.makeText(getActivity(), "please Finish Newtworking Skills", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(125) <= 0){
                    Toast.makeText(getActivity(), "please Finish Busines Skills", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 3, 0, 0, 2010);
            }
        } else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 11){
            if (mTechGaintHandler.isExists(110) <= 0 ||mTechGaintHandler.isExists(120) <= 0 ||mTechGaintHandler.isExists(122) <= 0 ||mTechGaintHandler.isExists(1311) <= 0 ||mTechGaintHandler.isExists(128) <= 0){
                if (mTechGaintHandler.isExists(110) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Highschool", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(120) <= 0){
                    Toast.makeText(getActivity(), "please Finish Driving Skills and license", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(122) <= 0){
                    Toast.makeText(getActivity(), "please Finish Communication Skills", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(131) <= 0){
                    Toast.makeText(getActivity(), "please Buy Car", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(128) <= 0){
                    Toast.makeText(getActivity(), "please Finish Management Skills", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 4, 0, 0, 2011);
            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 12){
            if (mTechGaintHandler.isExists(122) <= 0 ||mTechGaintHandler.isExists(144) <= 0 ||mTechGaintHandler.isExists(146) <= 0 ){
                if (mTechGaintHandler.isExists(122) <= 0){
                    Toast.makeText(getActivity(), "please Finish Communication Skills", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(144) <= 0){
                    Toast.makeText(getActivity(), "please Buy Colths B", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(146) <= 0){
                    Toast.makeText(getActivity(), "please go to Gym", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 3, 0, 0, 2012);
            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 13){
            if (mTechGaintHandler.isExists(111) <= 0 ||mTechGaintHandler.isExists(122) <= 0 ||mTechGaintHandler.isExists(124) <= 0 ||mTechGaintHandler.isExists(125) <= 0 || mTechGaintHandler.getAllExperienceData().get(0).intValue() <= 4){
                if (mTechGaintHandler.isExists(111) <= 0){
                    Toast.makeText(getActivity(), "please Finish Study at college", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(122) <= 0){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(124) <= 0){
                    Toast.makeText(getActivity(), "please finish Networking skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(125) <= 0){
                    Toast.makeText(getActivity(), "please finish Business skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.getAllExperienceData().get(0).intValue() <= 4){
                    Toast.makeText(getActivity(), "you need +4 experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 4, 0, 0, 2013);
            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 14){
            if (mTechGaintHandler.isExists(122) <= 0 ||mTechGaintHandler.isExists(125) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ){
                if (mTechGaintHandler.isExists(122) <= 0){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else  if (mTechGaintHandler.isExists(125) <= 0){
                    Toast.makeText(getActivity(), "please finish Business skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(129) <= 0){
                    Toast.makeText(getActivity(), "Please finish programming skill", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 6, 0, 0, 2014);
            }
        }
        // Internship Menu
        else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 0){

            if (mTechGaintHandler.isExists(111) <= 0 ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(122) <= 0){
                if (mTechGaintHandler.isExists(111) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at college", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Computer skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(124) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Networking skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(125) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Business skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(144) <= 0 ){
                    Toast.makeText(getActivity(), "please Buy Coths B", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 7, 0, 0, 210);

            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 1){

            if (mTechGaintHandler.isExists(111) <= 0 ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(122) <= 0){
                if (mTechGaintHandler.isExists(111) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at college", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Computer skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(124) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Networking skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(125) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Business skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(144) <= 0 ){
                    Toast.makeText(getActivity(), "please Buy Coths B", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 8, 0, 0, 211);

            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 2){

            if (mTechGaintHandler.isExists(111) <= 0 ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(120) <= 0){
                if (mTechGaintHandler.isExists(111) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at college", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Computer skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(125) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Business skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(120) <= 0 ){
                    Toast.makeText(getActivity(), "you need driving skills", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 9, 0, 0, 212);

            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 3){

            if (mTechGaintHandler.isExists(111) <= 0 ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(134) <= 0 ||mTechGaintHandler.isExists(135) <= 0){
                if (mTechGaintHandler.isExists(111) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at college", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Computer skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(126) <= 0 ){
                    Toast.makeText(getActivity(), "please finish product development skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(134) <= 0 ){
                    Toast.makeText(getActivity(), "you need to buy bicycle", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(135) <= 0 ){
                    Toast.makeText(getActivity(), "you need to rent a basement", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 10, 0, 0, 213);

            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 4){

            if (mTechGaintHandler.isExists(111) <= 0 ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(134) <= 0 ||mTechGaintHandler.isExists(135) <= 0){
                if (mTechGaintHandler.isExists(111) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at college", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Computer skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(126) <= 0 ){
                    Toast.makeText(getActivity(), "please finish product development skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(134) <= 0 ){
                    Toast.makeText(getActivity(), "you need to buy bicycle", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(135) <= 0 ){
                    Toast.makeText(getActivity(), "you need to rent a basement", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 11, 0, 0, 214);

            }
        }
        else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 5){

            if (mTechGaintHandler.isExists(111) <= 0 ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(136) <= 0 ||mTechGaintHandler.isExists(135) <= 0){
                if (mTechGaintHandler.isExists(111) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at college", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Computer skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(126) <= 0 ){
                    Toast.makeText(getActivity(), "please finish product development skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(136) <= 0 ){
                    Toast.makeText(getActivity(), "you need to buy Secondhand Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(135) <= 0 ){
                    Toast.makeText(getActivity(), "you need to rent a basement", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 12, 0, 0, 215);

            }
        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 6){

            if (mTechGaintHandler.isExists(111) <= 0 ||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(136) <= 0 ||mTechGaintHandler.isExists(137) <= 0 ||mTechGaintHandler.isExists(146) <= 0){
                if (mTechGaintHandler.isExists(111) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at college", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(124) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Networking skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(137) <= 0 ){
                    Toast.makeText(getActivity(), "you need to rent a room", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(136) <= 0 ){
                    Toast.makeText(getActivity(), "you need to buy Secondhand Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(146) <= 0 ){
                    Toast.makeText(getActivity(), "you need to go gym", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 13, 0, 0, 216);

            }
        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 7){

            if (mTechGaintHandler.isExists(111) <= 0 ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(136) <= 0 ||mTechGaintHandler.isExists(137) <= 0 ){
                if (mTechGaintHandler.isExists(111) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at college", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Computer skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(137) <= 0 ){
                    Toast.makeText(getActivity(), "you need to rent a room", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(136) <= 0 ){
                    Toast.makeText(getActivity(), "you need to buy Secondhand Car", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -6, 14, 0, 0, 217);

            }
        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 8){

            if (mTechGaintHandler.isExists(111) <= 0 ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(136) <= 0 ||mTechGaintHandler.isExists(137) <= 0 ||mTechGaintHandler.isExists(126) <= 0){
                if (mTechGaintHandler.isExists(111) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at college", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Computer skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(137) <= 0 ){
                    Toast.makeText(getActivity(), "you need to rent a room", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(136) <= 0 ){
                    Toast.makeText(getActivity(), "you need to buy Secondhand Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(126) <= 0 ){
                    Toast.makeText(getActivity(), "please finish product development skill", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -7, 15, 0, 0, 218);

            }
        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 9){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(136) <= 0 ||mTechGaintHandler.isExists(137) <= 0 ||mTechGaintHandler.isExists(126) <= 0){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Computer skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(137) <= 0 ){
                    Toast.makeText(getActivity(), "you need to rent a room", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(136) <= 0 ){
                    Toast.makeText(getActivity(), "you need to buy Secondhand Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(126) <= 0 ){
                    Toast.makeText(getActivity(), "please finish product development skill", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -10, 18, 0, 0, 219);

            }
        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 10){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(136) <= 0 ||mTechGaintHandler.isExists(137) <= 0 ||mTechGaintHandler.isExists(126) <= 0){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Computer skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(123) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Risk management skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(124) <= 0 ){
                    Toast.makeText(getActivity(), "please finish networking skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(125) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Business skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(137) <= 0 ){
                    Toast.makeText(getActivity(), "you need to rent a room", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(136) <= 0 ){
                    Toast.makeText(getActivity(), "you need to buy Secondhand Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(126) <= 0 ){
                    Toast.makeText(getActivity(), "please finish product development skill", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -15, 20, 0, 0, 2110);

            }
        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 11){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(136) <= 0 ||mTechGaintHandler.isExists(137) <= 0 ||mTechGaintHandler.isExists(126) <= 0 ||mTechGaintHandler.isExists(129) <= 0){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Computer skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(123) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Risk management skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(137) <= 0 ){
                    Toast.makeText(getActivity(), "you need to rent a room", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(136) <= 0 ){
                    Toast.makeText(getActivity(), "you need to buy Secondhand Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(126) <= 0 ){
                    Toast.makeText(getActivity(), "please finish product development skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(129) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Programming skill", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -20, 20, 0, 0, 2111);

            }
        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 12){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(138) <= 0 ||mTechGaintHandler.isExists(139) <= 0 ||mTechGaintHandler.isExists(126) <= 0){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Computer skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(123) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Risk management skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(138) <= 0 ){
                    Toast.makeText(getActivity(), "you need to buy a bike", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(139) <= 0 ){
                    Toast.makeText(getActivity(), "you need to rent an apartment", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(126) <= 0 ){
                    Toast.makeText(getActivity(), "please finish product development skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(129) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Programming skill", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -25, 20, 0, 0, 2112);

            }
        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 1 && position == 13){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(138) <= 0 ||mTechGaintHandler.isExists(139) <= 0 ||mTechGaintHandler.isExists(126) <= 0){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Computer skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Communication skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(123) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Risk management skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(138) <= 0 ){
                    Toast.makeText(getActivity(), "you need to buy a bike", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(139) <= 0 ){
                    Toast.makeText(getActivity(), "you need to rent an apartment", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(126) <= 0 ){
                    Toast.makeText(getActivity(), "please finish product development skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(129) <= 0 ){
                    Toast.makeText(getActivity(), "please finish Programming skill", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -25, 22, 0, 0, 2113);

            }

        }

        // Full Time Jobs
        else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 2 && position == 0){

            if (mTechGaintHandler.isExists(112) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 70 ){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1317) <= 0 ){
                    Toast.makeText(getActivity(), "You need to rent a house", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 70){
                    Toast.makeText(getActivity(), "You need 70 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -30, 30, 0, 0, 220);

            }

        } else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 2 && position == 1){

            if (mTechGaintHandler.isExists(112) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1311) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 75 ){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1311) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1317) <= 0 ){
                    Toast.makeText(getActivity(), "You need to rent a house", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 75){
                    Toast.makeText(getActivity(), "You need 75 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -35, 35, 0, 0, 221);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 2 && position == 2){

            if (mTechGaintHandler.isExists(112) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1311) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 80 ){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1311) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1317) <= 0 ){
                    Toast.makeText(getActivity(), "You need to rent a house", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 80){
                    Toast.makeText(getActivity(), "You need 80 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -40, 40, 0, 0, 222);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 2 && position == 3){

            if (mTechGaintHandler.isExists(112) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1311) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 85 ){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1311) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1317) <= 0 ){
                    Toast.makeText(getActivity(), "You need to rent a house", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 85){
                    Toast.makeText(getActivity(), "You need 85 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -45, 45, 0, 0, 223);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 2 && position == 4){

            if (mTechGaintHandler.isExists(112) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1312) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 90 ){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1312) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Track", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1317) <= 0 ){
                    Toast.makeText(getActivity(), "You need to rent a house", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 90){
                    Toast.makeText(getActivity(), "You need 90 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -45, 50, 0, 0, 224);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 2 && position == 5){

            if (mTechGaintHandler.isExists(112) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1312) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 95 ){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1312) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Track", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1317) <= 0 ){
                    Toast.makeText(getActivity(), "You need to rent a house", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 95){
                    Toast.makeText(getActivity(), "You need 95 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -50, 55, 0, 0, 225);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 2 && position == 6){

            if (mTechGaintHandler.isExists(112) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1312) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 100 ){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1312) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Track", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1317) <= 0 ){
                    Toast.makeText(getActivity(), "You need to rent a house", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 100){
                    Toast.makeText(getActivity(), "You need 100 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -55, 60, 0, 0, 226);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 2 && position == 7){

            if (mTechGaintHandler.isExists(112) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1312) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 105 ){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1312) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Track", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1317) <= 0 ){
                    Toast.makeText(getActivity(), "You need to rent a house", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 105){
                    Toast.makeText(getActivity(), "You need 105 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -60, 65, 0, 0, 227);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 2 && position == 8){

            if (mTechGaintHandler.isExists(112) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1312) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 110 ){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1312) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Track", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1317) <= 0 ){
                    Toast.makeText(getActivity(), "You need to rent a house", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 110){
                    Toast.makeText(getActivity(), "You need 110 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -65, 70, 0, 0, 228);

            }
        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 2 && position == 9){

            if (mTechGaintHandler.isExists(112) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1312) <= 0||mTechGaintHandler.isExists(1310) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 115 ){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1312) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Track", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1310) <= 0 ){
                    Toast.makeText(getActivity(), "You need to rent a Condo", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 115){
                    Toast.makeText(getActivity(), "You need 115 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -70, 75, 0, 0, 229);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 2 && position == 10){

            if (mTechGaintHandler.isExists(112) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1313) <= 0||mTechGaintHandler.isExists(1310) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 120 ){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1313) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Electric Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1310) <= 0 ){
                    Toast.makeText(getActivity(), "You need to rent a Condo", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 120){
                    Toast.makeText(getActivity(), "You need 120 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -70, 75, 0, 0, 2210);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 2 && position == 11){

            if (mTechGaintHandler.isExists(112) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1313) <= 0||mTechGaintHandler.isExists(1310) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 125){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1313) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Electric Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1310) <= 0 ){
                    Toast.makeText(getActivity(), "You need to rent a Condo", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 125){
                    Toast.makeText(getActivity(), "You need 125 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -75, 80, 0, 0, 2211);

            }

        }

       // Excutive Jobs
        else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 3 && position == 0){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(113) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1315) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 200){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(113) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish MBA", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1315) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a SUV", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1317) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a House", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 200){
                    Toast.makeText(getActivity(), "You need 200 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -100, 90, 0, 0, 230);

            }
        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 3 && position == 1){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(113) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1316) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 250){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(113) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish MBA", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1316) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Sports Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1317) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a House", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 250){
                    Toast.makeText(getActivity(), "You need 250 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -115, 95, 0, 0, 231);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 3 && position == 2){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(113) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1316) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 300){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(113) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish MBA", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1316) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Sports Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1317) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a House", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 300){
                    Toast.makeText(getActivity(), "You need 300 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -120, 100, 0, 0, 232);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 3 && position == 3){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(113) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1316) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 350){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(113) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish MBA", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1316) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Sports Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1318) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Condo", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 350){
                    Toast.makeText(getActivity(), "You need 350 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -115, 105, 0, 0, 233);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 3 && position == 4){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(113) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1316) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 400){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(113) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish MBA", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1316) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Sports Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1318) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Condo", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 400){
                    Toast.makeText(getActivity(), "You need 400 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -120, 115, 0, 0, 234);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 3 && position == 5){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(113) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1316) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 450){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(113) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish MBA", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1316) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Sports Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1318) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Condo", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 450){
                    Toast.makeText(getActivity(), "You need 450 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -125, 120, 0, 0, 235);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 3 && position == 6){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(113) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1316) <= 0||mTechGaintHandler.isExists(1317) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 500){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(113) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish MBA", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1316) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Sports Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1318) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Condo", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 500){
                    Toast.makeText(getActivity(), "You need 500 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -125, 120, 0, 0, 236);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 3 && position == 7){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(113) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0 ||mTechGaintHandler.isExists(1319) <= 0||mTechGaintHandler.isExists(1320) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 600){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(113) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish MBA", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1316) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Sports Car", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1319) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Helicopter", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(1320) <= 0 ){
                    Toast.makeText(getActivity(), "You need to buy a Villa", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 600){
                    Toast.makeText(getActivity(), "You need 600 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -130, 125, 0, 0, 237);

            }

        }else if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 3 && position == 8){

            if (mTechGaintHandler.isExists(112) <= 0 ||mTechGaintHandler.isExists(113) <= 0 || mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0
                    ||mTechGaintHandler.isExists(131) <= 0||mTechGaintHandler.isExists(132) <= 0||mTechGaintHandler.isExists(134) <= 0||mTechGaintHandler.isExists(135) <= 0||mTechGaintHandler.isExists(136) <= 0||mTechGaintHandler.isExists(137) <= 0
                    ||mTechGaintHandler.isExists(138) <= 0||mTechGaintHandler.isExists(139) <= 0||mTechGaintHandler.isExists(1310) <= 0||mTechGaintHandler.isExists(1311) <= 0||mTechGaintHandler.isExists(1312) <= 0
                    ||mTechGaintHandler.isExists(1313) <= 0||mTechGaintHandler.isExists(1314) <= 0||mTechGaintHandler.isExists(1315) <= 0||mTechGaintHandler.isExists(1316) <= 0
                    ||mTechGaintHandler.isExists(1317) <= 0||mTechGaintHandler.isExists(1318) <= 0||mTechGaintHandler.isExists(1319) <= 0||mTechGaintHandler.isExists(1320) <= 0
                    ||mTechGaintHandler.isExists(1321) <= 0||mTechGaintHandler.isExists(1322) <= 0|| mTechGaintHandler.getAllExperienceData().get(0) < 875){
                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Study at University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(113) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish MBA", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(123) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0||mTechGaintHandler.isExists(126) <= 0||mTechGaintHandler.isExists(127) <= 0 ||mTechGaintHandler.isExists(128) <= 0 ||mTechGaintHandler.isExists(129) <= 0) {
                    Toast.makeText(getActivity(), "please Complete All skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.isExists(131) <= 0||mTechGaintHandler.isExists(132) <= 0||mTechGaintHandler.isExists(134) <= 0||mTechGaintHandler.isExists(135) <= 0||mTechGaintHandler.isExists(136) <= 0||mTechGaintHandler.isExists(137) <= 0
                        ||mTechGaintHandler.isExists(138) <= 0||mTechGaintHandler.isExists(139) <= 0||mTechGaintHandler.isExists(1310) <= 0||mTechGaintHandler.isExists(1311) <= 0||mTechGaintHandler.isExists(1312) <= 0
                        ||mTechGaintHandler.isExists(1313) <= 0||mTechGaintHandler.isExists(1314) <= 0||mTechGaintHandler.isExists(1315) <= 0||mTechGaintHandler.isExists(1316) <= 0
                        ||mTechGaintHandler.isExists(1317) <= 0||mTechGaintHandler.isExists(1318) <= 0||mTechGaintHandler.isExists(1319) <= 0||mTechGaintHandler.isExists(1320) <= 0
                        ||mTechGaintHandler.isExists(1321) <= 0||mTechGaintHandler.isExists(1322) <= 0 ){

                    Toast.makeText(getActivity(), "You need have all lodging", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 875){
                    Toast.makeText(getActivity(), "You need 875 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -200, 125, 0, 0, 238);

            }

        }
        // Build your idea
        else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 0 && position == 0){

            if (mTechGaintHandler.isExists(111) <= 0  ||mTechGaintHandler.isExists(125) <= 0
                    ||mTechGaintHandler.getAllCompanyExperienceData().get(0) <  25){

                 if (mTechGaintHandler.isExists(111) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish High School", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(125) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Business Skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 25){
                     Toast.makeText(getActivity(), "You need 25 Experience", Toast.LENGTH_SHORT).show();
                 }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 5, 5, 0, 300);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 0 && position == 1){

            if (mTechGaintHandler.isExists(111) <= 0  ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(124) <= 0||mTechGaintHandler.isExists(125) <= 0
                    ||mTechGaintHandler.getAllCompanyExperienceData().get(0) <  30){

                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish College", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(125) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Business Skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Computer Skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Comunication Skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(124) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Networking Skill", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 30){
                    Toast.makeText(getActivity(), "You need 30 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -10, 10, 10, 0, 301);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 0 && position == 2){

            if (mTechGaintHandler.isExists(112) <= 0  ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(124) <= 0 || mTechGaintHandler.isExists(125) <= 0|| mTechGaintHandler.isExists(139) <= 0
                    ||mTechGaintHandler.getAllCompanyExperienceData().get(0) <  35){

                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish College", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(125) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Business Skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Computer Skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Comunication Skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(124) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish networking Skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(139) <= 0 ){
                    Toast.makeText(getActivity(), "you need to rent an apartment", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 35){
                    Toast.makeText(getActivity(), "You need 35 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -10, 10, 15, 0, 302);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 0 && position == 3){

            if (mTechGaintHandler.isExists(113) <= 0  ||mTechGaintHandler.isExists(121) <= 0||mTechGaintHandler.isExists(122) <= 0||mTechGaintHandler.isExists(124) <= 0 || mTechGaintHandler.isExists(125) <= 0 || mTechGaintHandler.isExists(129) <= 0|| mTechGaintHandler.isExists(139) <= 0
                    ||mTechGaintHandler.getAllCompanyExperienceData().get(0) <  40){

                if (mTechGaintHandler.isExists(112) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish University", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(125) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Business Skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(121) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Computer Skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(122) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Comunication Skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(124) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish networking Skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(129) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish programming Skill", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(139) <= 0 ){
                    Toast.makeText(getActivity(), "you need to rent an apartment", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 40){
                    Toast.makeText(getActivity(), "You need 40 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -20, 20, 20, 0, 303);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 0 && position == 4){

            if (mTechGaintHandler.isExists(120) <= 0 ||mTechGaintHandler.isExists(121) <= 0 || mTechGaintHandler.isExists(122) <= 0
                    || mTechGaintHandler.isExists(123) <= 0|| mTechGaintHandler.isExists(124) <= 0|| mTechGaintHandler.isExists(125) <= 0
                    || mTechGaintHandler.isExists(126) <= 0|| mTechGaintHandler.isExists(127) <= 0|| mTechGaintHandler.isExists(128) <= 0|| mTechGaintHandler.isExists(129) <= 0
                    ||mTechGaintHandler.getAllCompanyExperienceData().get(0) <  45){

                if (mTechGaintHandler.isExists(120) <= 0 ||mTechGaintHandler.isExists(121) <= 0 || mTechGaintHandler.isExists(122) <= 0
                        || mTechGaintHandler.isExists(123) <= 0|| mTechGaintHandler.isExists(124) <= 0|| mTechGaintHandler.isExists(125) <= 0
                        || mTechGaintHandler.isExists(126) <= 0|| mTechGaintHandler.isExists(127) <= 0|| mTechGaintHandler.isExists(128) <= 0|| mTechGaintHandler.isExists(129) <= 0){
                    Toast.makeText(getActivity(), "please Finish All Skills", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 45){
                    Toast.makeText(getActivity(), "You need 45 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -25, 25, 20, 0, 304);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 0 && position == 5){

            if (mTechGaintHandler.isExists(301) <= 0 || mTechGaintHandler.isExists(302) <= 0 || mTechGaintHandler.isExists(303) <= 0 || mTechGaintHandler.isExists(304) <= 0){

                if (mTechGaintHandler.isExists(301) <= 0){
                    Toast.makeText(getActivity(), "Complete write down your idea", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(302) <= 0){
                    Toast.makeText(getActivity(), "Complete Develop your idea", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(303) <= 0){
                    Toast.makeText(getActivity(), "Complete Find co-founders", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(304) <= 0){
                    Toast.makeText(getActivity(), "Complete write Business plan", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -25, 25, 25, 0, 305);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 0 && position == 6){

            if (mTechGaintHandler.isExists(301) <= 0 || mTechGaintHandler.isExists(302) <= 0 || mTechGaintHandler.isExists(303) <= 0 || mTechGaintHandler.isExists(304) <= 0
                    ||mTechGaintHandler.getAllCompanyExperienceData().get(0) < 25 ||mTechGaintHandler.getAllExperienceData().get(0) < 15){

                if (mTechGaintHandler.isExists(301) <= 0){
                    Toast.makeText(getActivity(), "Complete write down your idea", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(302) <= 0){
                    Toast.makeText(getActivity(), "Complete Develop your idea", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(303) <= 0){
                    Toast.makeText(getActivity(), "Complete Find co-founders", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(304) <= 0){
                    Toast.makeText(getActivity(), "Complete write Business plan", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllCompanyExperienceData().get(0) < 25){
                    Toast.makeText(getActivity(), "You need 55 Company Experience", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 15){
                    Toast.makeText(getActivity(), "You need 15 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 15, 25, 0, 306);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 0 && position == 7){

            if (mTechGaintHandler.isExists(301) <= 0 || mTechGaintHandler.isExists(302) <= 0 || mTechGaintHandler.isExists(303) <= 0 || mTechGaintHandler.isExists(304) <= 0
                    ||mTechGaintHandler.getAllCompanyExperienceData().get(0) < 30 ||mTechGaintHandler.getAllExperienceData().get(0) < 20){

                if (mTechGaintHandler.isExists(301) <= 0){
                    Toast.makeText(getActivity(), "Complete write down your idea", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(302) <= 0){
                    Toast.makeText(getActivity(), "Complete Develop your idea", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(303) <= 0){
                    Toast.makeText(getActivity(), "Complete Find co-founders", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(304) <= 0){
                    Toast.makeText(getActivity(), "Complete write Business plan", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllCompanyExperienceData().get(0) < 30){
                    Toast.makeText(getActivity(), "You need 30 Company Experience", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 20){
                    Toast.makeText(getActivity(), "You need 20 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 20, 35, 0, 307);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 0 && position == 8){

            if (mTechGaintHandler.isExists(301) <= 0 || mTechGaintHandler.isExists(302) <= 0 || mTechGaintHandler.isExists(303) <= 0 || mTechGaintHandler.isExists(304) <= 0
                    ||mTechGaintHandler.getAllCompanyExperienceData().get(0) < 35 ||mTechGaintHandler.getAllExperienceData().get(0) < 25){

                if (mTechGaintHandler.isExists(301) <= 0){
                    Toast.makeText(getActivity(), "Complete write down your idea", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(302) <= 0){
                    Toast.makeText(getActivity(), "Complete Develop your idea", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(303) <= 0){
                    Toast.makeText(getActivity(), "Complete Find co-founders", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(304) <= 0){
                    Toast.makeText(getActivity(), "Complete write Business plan", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllCompanyExperienceData().get(0) < 35){
                    Toast.makeText(getActivity(), "You need 30 Company Experience", Toast.LENGTH_SHORT).show();
                }else if(mTechGaintHandler.getAllExperienceData().get(0) < 25){
                    Toast.makeText(getActivity(), "You need 20 Experience", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 25, 35, 0, 308);

            }

        }
     // Devlop yor Company
        else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 1 && position == 0){

            if (mTechGaintHandler.isExists(301) <= 0 || mTechGaintHandler.isExists(302) <= 0 || mTechGaintHandler.isExists(303) <= 0 || mTechGaintHandler.isExists(304) <= 0
                    || mTechGaintHandler.isExists(305) <= 0|| mTechGaintHandler.isExists(306) <= 0|| mTechGaintHandler.isExists(307) <= 0
                    || mTechGaintHandler.isExists(308) <= 0 ){

                if (mTechGaintHandler.isExists(300) <= 0){
                    Toast.makeText(getActivity(), "Complete write down your idea", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(301) <= 0){
                    Toast.makeText(getActivity(), "Complete Develop your idea", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(302) <= 0){
                    Toast.makeText(getActivity(), "Complete Find co-founders", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(303) <= 0){
                    Toast.makeText(getActivity(), "Complete write codes", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(304) <= 0){
                    Toast.makeText(getActivity(), "Write a Business Plan", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(305) <= 0){
                    Toast.makeText(getActivity(), "Complete Find seed investors", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(306) <= 0){
                    Toast.makeText(getActivity(), "Complete get License and Permit", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(307) <= 0){
                    Toast.makeText(getActivity(), "Complete Develop a corporate Identity", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(308) <= 0){
                    Toast.makeText(getActivity(), "Complete Get a Trademark and a Patent", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -30, 35, 35, 0, 310);

            }

        }
        else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 1 && position == 1){

            if ( mTechGaintHandler.isExists(310) <= 0 ){

                if (mTechGaintHandler.isExists(310) <= 0){
                    Toast.makeText(getActivity(), "Complete understand Trend and market", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -35, 25, 40, 0, 311);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 1 && position == 2){

            if ( mTechGaintHandler.isExists(310) <= 0 || mTechGaintHandler.isExists(311) <= 0 ){

                if (mTechGaintHandler.isExists(310) <= 0){
                    Toast.makeText(getActivity(), "Complete understand Trend and market", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(301) <= 0){
                    Toast.makeText(getActivity(), "Complete Develop your 1st Prototype", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position),mStringValues.get(position), getInterger(mStringValues.get(position)) , -35, 25, 40, 0, 312);

            }

        }
        else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 1 && position == 3){

            if ( mTechGaintHandler.isExists(310) <= 0 || mTechGaintHandler.isExists(311) <= 0 || mTechGaintHandler.isExists(312) <= 0 ){

                if (mTechGaintHandler.isExists(310) <= 0){
                    Toast.makeText(getActivity(), "Complete understand Trend and market", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(301) <= 0){
                    Toast.makeText(getActivity(), "Complete Develop your 1st Prototype", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(302) <= 0){
                    Toast.makeText(getActivity(), "Complete Join Company Accelerator", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , -50, 40, 60, 0, 313);

            }

        }
        else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 1 && position == 4){

            if ( mTechGaintHandler.isExists(310) <= 0 || mTechGaintHandler.isExists(311) <= 0 || mTechGaintHandler.isExists(312) <= 0 || mTechGaintHandler.isExists(313) <= 0 ){

                if (mTechGaintHandler.isExists(310) <= 0){
                    Toast.makeText(getActivity(), "Complete understand Trend and market", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(301) <= 0){
                    Toast.makeText(getActivity(), "Complete Develop your 1st Prototype", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(302) <= 0){
                    Toast.makeText(getActivity(), "Complete Join Company Accelerator", Toast.LENGTH_SHORT).show();
                }
                else if (mTechGaintHandler.isExists(303) <= 0){
                    Toast.makeText(getActivity(), "Complete Build a Beta version of your product", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , -100, 80, 100, 10, 314);

            }

        }
        // Get an Office
        else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 2 && position == 0){

            if ( mTechGaintHandler.isExists(314) <= 0 ){

                if (mTechGaintHandler.isExists(314) <= 0){
                    Toast.makeText(getActivity(), "Finish All previous stages", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 5, 5, 0, 320);

            }

        } else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 2 && position == 1){

            if ( mTechGaintHandler.isExists(320) <= 0 ){

                if (mTechGaintHandler.isExists(320) <= 0){
                    Toast.makeText(getActivity(), "Finish All previous stages", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 25, 25, 0, 321);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 2 && position == 2){

            if ( mTechGaintHandler.isExists(321) <= 0 ){

                if (mTechGaintHandler.isExists(321) <= 0){
                    Toast.makeText(getActivity(), "Finish All previous stages", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 50, 50, 0, 322);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 2 && position == 3){

            if ( mTechGaintHandler.isExists(322) <= 0 ){

                if (mTechGaintHandler.isExists(322) <= 0){
                    Toast.makeText(getActivity(), "Finish All previous stages", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 50, 50, 0, 323);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 2 && position == 4){

            if ( mTechGaintHandler.isExists(323) <= 0 ){

                if (mTechGaintHandler.isExists(323) <= 0){
                    Toast.makeText(getActivity(), "Finish All previous stages", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 75, 75, 0, 324);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 2 && position == 5){

            if ( mTechGaintHandler.isExists(323) <= 0 ){

                if (mTechGaintHandler.isExists(323) <= 0){
                    Toast.makeText(getActivity(), "Finish All previous stages", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 100, 100, 0, 325);

            }

        }
       // office Furniture and Equipment
        else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 3 && position == 0){

            if ( mTechGaintHandler.isExists(320) <= 0 ){

                if (mTechGaintHandler.isExists(320) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 1", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 5, 5, 0, 330);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 3 && position == 1){

            if ( mTechGaintHandler.isExists(321) <= 0 ){

                if (mTechGaintHandler.isExists(321) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 2", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 25, 25, 0, 331);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 3 && position == 2){

            if ( mTechGaintHandler.isExists(322) <= 0 ){

                if (mTechGaintHandler.isExists(322) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 3", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 50, 50, 0, 332);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 3 && position == 3){

            if ( mTechGaintHandler.isExists(323) <= 0 ){

                if (mTechGaintHandler.isExists(323) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 4", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 75, 75, 0, 333);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 3 && position == 4){

            if ( mTechGaintHandler.isExists(324) <= 0 ){

                if (mTechGaintHandler.isExists(324) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 5", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 100, 100, 0, 334);

            }

        }
        // Hire Talents
        else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 4 && position == 0){

            if ( mTechGaintHandler.isExists(320) <= 0|| mTechGaintHandler.isExists(330) <= 0){

                if (mTechGaintHandler.isExists(320) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 1", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(330) <= 0){
                    Toast.makeText(getActivity(), "Finish Get Equipment 1", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 5, 5, 0, 340);

            }

        } else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 4 && position == 1){

            if ( mTechGaintHandler.isExists(321) <= 0|| mTechGaintHandler.isExists(331) <= 0){

                if (mTechGaintHandler.isExists(321) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 2", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(331) <= 0){
                    Toast.makeText(getActivity(), "Finish Get Equipment 2", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 25, 25, 0, 341);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 4 && position == 2){

            if ( mTechGaintHandler.isExists(322) <= 0|| mTechGaintHandler.isExists(332) <= 0){

                if (mTechGaintHandler.isExists(322) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 3", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(332) <= 0){
                    Toast.makeText(getActivity(), "Finish Get Equipment 3", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 50, 50, 0, 342);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 4 && position == 3){

            if ( mTechGaintHandler.isExists(323) <= 0|| mTechGaintHandler.isExists(333) <= 0){

                if (mTechGaintHandler.isExists(323) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 4", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(333) <= 0){
                    Toast.makeText(getActivity(), "Finish Get Equipment 4", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 75, 75, 0, 343);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 4 && position == 4){

            if ( mTechGaintHandler.isExists(324) <= 0|| mTechGaintHandler.isExists(334) <= 0){

                if (mTechGaintHandler.isExists(324) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 5", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(334) <= 0){
                    Toast.makeText(getActivity(), "Finish Get Equipment 5", Toast.LENGTH_SHORT).show();
                }

            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 100, 100, 0, 344);

            }

        }
        // Build a customer Base
        else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 5 && position == 0){

            if ( mTechGaintHandler.isExists(320) <= 0|| mTechGaintHandler.isExists(330) <= 0 || mTechGaintHandler.isExists(340) <= 0){

                if (mTechGaintHandler.isExists(320) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 1", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(330) <= 0){
                    Toast.makeText(getActivity(), "Finish Get Equipment 1", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(340) <= 0){
                    Toast.makeText(getActivity(), "Finish Hire 15 talents", Toast.LENGTH_SHORT).show();
                }


            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 5, 5, 0, 350);

            }

        } else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 5 && position == 1){

            if ( mTechGaintHandler.isExists(321) <= 0|| mTechGaintHandler.isExists(331) <= 0 || mTechGaintHandler.isExists(341) <= 0){

                if (mTechGaintHandler.isExists(321) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 2", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(331) <= 0){
                    Toast.makeText(getActivity(), "Finish Get Equipment 2", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(331) <= 0){
                    Toast.makeText(getActivity(), "Finish Hire 50 talents", Toast.LENGTH_SHORT).show();
                }


            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 25, 25, 0, 351);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 5 && position == 2){

            if ( mTechGaintHandler.isExists(322) <= 0|| mTechGaintHandler.isExists(332) <= 0 || mTechGaintHandler.isExists(342) <= 0){

                if (mTechGaintHandler.isExists(322) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 3", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(332) <= 0){
                    Toast.makeText(getActivity(), "Finish Get Equipment 3", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(332) <= 0){
                    Toast.makeText(getActivity(), "Finish Hire 100 talents", Toast.LENGTH_SHORT).show();
                }


            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 50, 50, 0, 352);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 5 && position == 3){

            if ( mTechGaintHandler.isExists(323) <= 0|| mTechGaintHandler.isExists(333) <= 0 || mTechGaintHandler.isExists(343) <= 0){

                if (mTechGaintHandler.isExists(323) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 4", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(333) <= 0){
                    Toast.makeText(getActivity(), "Finish Get Equipment 4", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(333) <= 0){
                    Toast.makeText(getActivity(), "Finish Hire 1000 talents", Toast.LENGTH_SHORT).show();
                }


            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 75, 75, 0, 353);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 5 && position == 4){

            if ( mTechGaintHandler.isExists(324) <= 0|| mTechGaintHandler.isExists(334) <= 0 || mTechGaintHandler.isExists(344) <= 0){

                if (mTechGaintHandler.isExists(324) <= 0){
                    Toast.makeText(getActivity(), "Finish Lease office 5", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(334) <= 0){
                    Toast.makeText(getActivity(), "Finish Get Equipment 5", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(334) <= 0){
                    Toast.makeText(getActivity(), "Finish Hire 5000 talents", Toast.LENGTH_SHORT).show();
                }


            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 100, 100, 0, 354);

            }

        }
        // Funding Rounds
        else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 6 && position == 0){

            if ( mTechGaintHandler.getAllExperienceData().get(0) < 200){

                Toast.makeText(getActivity(), "Company Must Have 200+ Experience", Toast.LENGTH_SHORT).show();


            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 30, 0, 5, 360);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 6 && position == 1){

            if ( mTechGaintHandler.getAllExperienceData().get(0) < 300){

                Toast.makeText(getActivity(), "Company Must Have 300+ Experience", Toast.LENGTH_SHORT).show();


            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 40, 0, 10, 361);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 6 && position == 2){

            if ( mTechGaintHandler.getAllExperienceData().get(0) < 400){

                Toast.makeText(getActivity(), "Company Must Have 400+ Experience", Toast.LENGTH_SHORT).show();


            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 50, 0, 12, 362);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 6 && position == 3){

            if ( mTechGaintHandler.getAllExperienceData().get(0) < 500){

                Toast.makeText(getActivity(), "Company Must Have 500+ Experience", Toast.LENGTH_SHORT).show();


            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 60, 0, 14, 363);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 6 && position == 4){

            if ( mTechGaintHandler.getAllExperienceData().get(0) < 600){

                Toast.makeText(getActivity(), "Company Must Have 500+ Experience", Toast.LENGTH_SHORT).show();


            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 70, 0, 15, 364);

            }

        }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 6 && position == 5){

            if ( mTechGaintHandler.getAllExperienceData().get(0) < 1000){

                Toast.makeText(getActivity(), "Company Must Have 1000+ Experience", Toast.LENGTH_SHORT).show();


            }else {
                showDialogBeforSummision(mString.get(position), mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 600, 0,0, 365);

            }

        }


    }

    public void showDialogBeforSummision(final String mStringTitle, final String mStringValue, final int balance, final double health, final double experience, final double companyExperience, final int equity, final int trace){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure "+  mStringTitle+" ?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                double totalHealthTrace = health + mTechGaintHandler.getAllHealthData().get(0);
                int totalBalance = balance + mTechGaintHandler.getAllCash().get(0);
                double totalExperience = experience +mTechGaintHandler.getAllExperienceData().get(0);
                double totalCompanyExperience = companyExperience + mTechGaintHandler.getAllCompanyExperienceData().get(0);
                int totalEquity = equity + mTechGaintHandler.getAllEquity().get(0);

                if (totalHealthTrace >= 0 && totalBalance>= 0 && totalExperience >=0  && totalCompanyExperience >= 0 && totalEquity >= 0){

                    mTechGaintHandler.updateCash(totalBalance);
                    mTechGaintHandler.updateHealth(totalHealthTrace);
                    mTechGaintHandler.updateExperience(totalExperience);
                    mTechGaintHandler.updateCompanyExperience(totalCompanyExperience);
                    mTechGaintHandler.updateEquity(totalEquity);
                    if (mTechGaintHandler.isExists(trace) <= 0){
                        mTechGaintHandler.insertForTrace(trace,  mStringTitle);
                        Log.e("Detail", "onClick: "+ mStringValue );
                    }
                    updateProgressBar();
                    mTextViewCash.setText("Cash  $" +String.valueOf(mTechGaintHandler.getAllCash().get(0)));
                    mTextViewPersonalBankAccount.setText("Personal BankAccount $"+String.valueOf(mTechGaintHandler.getAllPersonalBankAccount().get(0)));
                    Toast.makeText(getActivity(), "your current Cash is "+mTechGaintHandler.getAllCash().get(0), Toast.LENGTH_SHORT).show();
                }else {
                    if (totalHealthTrace < 0){
                        Toast.makeText(getActivity(), "Game is over cause Health Can't be negative", Toast.LENGTH_SHORT).show();
                    }else if (totalBalance < 0){
                        Toast.makeText(getActivity(), "Game is over cause Balance Can't be negative", Toast.LENGTH_SHORT).show();
                    }else if (totalExperience < 0){
                        Toast.makeText(getActivity(), "Game is over cause Experience Can't be negative", Toast.LENGTH_SHORT).show();
                    }else if (totalCompanyExperience < 0){
                        Toast.makeText(getActivity(), "Game is over cause CompanyExperience Can't be negative", Toast.LENGTH_SHORT).show();
                    }else if (totalEquity < 0){
                        Toast.makeText(getActivity(), "Game is over cause Equity Can't be negative", Toast.LENGTH_SHORT).show();
                    }
                    mTechGaintHandler.updateCash(50);
                    mTechGaintHandler.updateHealth(0);
                    mTechGaintHandler.updateExperience(0);
                    mTechGaintHandler.updateCompanyExperience(0);
                    mTechGaintHandler.updateEquity(0);

                }

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
    public void showDialogBeforSubTract(final String mStringTitle, final String mStringValue, final int balance, final double health, final double experience, final double companyExperience, final int trace){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure about "+  mStringTitle+" ?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog

                double totalHealthTrace = health + mTechGaintHandler.getAllHealthData().get(0);

                if (totalHealthTrace >= 0){
                    int totalBalance = balance + mTechGaintHandler.getAllCash().get(0);
                    double totalExperience = experience +mTechGaintHandler.getAllExperienceData().get(0);
                    double totalCompanyExperience = companyExperience + mTechGaintHandler.getAllCompanyExperienceData().get(0);
                    mTechGaintHandler.updateCash(totalBalance);
                    mTechGaintHandler.updateHealth(totalHealthTrace);
                    mTechGaintHandler.updateExperience(totalExperience);
                    mTechGaintHandler.updateCompanyExperience(totalCompanyExperience);
                    if (mTechGaintHandler.isExists(trace) <= 0){
                        mTechGaintHandler.insertForTrace(trace,  mStringTitle);
                        Log.e("Detail", "onClick: "+ mStringValue );
                    }
                    updateProgressBar();
                    mTextViewCash.setText("Cash - $" +String.valueOf(mTechGaintHandler.getAllCash().get(0)));
                    Toast.makeText(getActivity(), "your current Balance is "+mTechGaintHandler.getAllCash().get(0), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Game is over cause Health Can't be negative", Toast.LENGTH_SHORT).show();
                    mTechGaintHandler.updateCash(50);
                    mTechGaintHandler.updateHealth(20);
                    mTechGaintHandler.updateExperience(10);
                    mTechGaintHandler.updateCompanyExperience(0);

                }

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

    public int getInterger(String string){

        int intergerValue =(int) Double.parseDouble(string.replaceAll("[^\\d.]", ""));

        Log.e("balance", "getInterger: "+intergerValue);
        return intergerValue;
    }

    public void backButton(){
        mButtonBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getFragmentManager().popBackStack();
                    }
                }
        );
    }

    private void updateProgressBar(){
        mProgesBarHealth.setProgress(mTechGaintHandler.getAllHealthData().get(0).intValue());
        mProgressBarExperience.setProgress(mTechGaintHandler.getAllExperienceData().get(0).intValue());
        mProgressBarEquity.setProgress(mTechGaintHandler.getAllEquity().get(0).intValue());
        mProgressBarComapnyExperience.setProgress(mTechGaintHandler.getAllCompanyExperienceData().get(0).intValue());
        mTextViewHealth.setText(mTechGaintHandler.getAllHealthData().get(0).toString());
        mTextViewExperience.setText(mTechGaintHandler.getAllExperienceData().get(0).toString());
        mTextViewEquity.setText(mTechGaintHandler.getAllEquity().get(0).toString());
        mTextViewCompanyExperience.setText(mTechGaintHandler.getAllCompanyExperienceData().get(0).toString());
    }
}
