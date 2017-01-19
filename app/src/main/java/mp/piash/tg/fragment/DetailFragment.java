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
    private ProgressBar mProgressBarComapnyExperience;
    private TextView mTextViewHealth;
    private TextView mTextViewExperience;
    private TextView mTextViewCompanyExperience;
    private TextView mTextViewAccount;



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
        mProgressBarComapnyExperience = (ProgressBar)view.findViewById(R.id.progressBarComapnyExperience);
        mTextViewHealth = (TextView)view.findViewById(R.id.txtHealthValue);
        mTextViewExperience = (TextView)view.findViewById(R.id.txtExperienceValue);
        mTextViewCompanyExperience = (TextView)view.findViewById(R.id.txtCompanyExperienceValue);
        mTextViewAccount = (TextView)view.findViewById(R.id.tvBankAccount);
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
                        /*Toast.makeText(getActivity(), "Detail aslo working", Toast.LENGTH_SHORT).show();
                        mTechGaintHandler.getAllBalanceData();
                        mTechGaintHandler.updateBalance(50);
                        Log.e("Test", "onItemClicked: "+ mTechGaintHandler.getAllBalanceData().get(0));*/
                        UpdateDatabaseByLogic(position);
                        /*if ( getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 1){
                            UpdateDatabaseByLogic(position);
                        }else if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 1){
                            secondFragmentSkill();
                        }*/

                    }
                }
        );
        mTextViewAccount.setText("Bank Account - $" +String.valueOf(mTechGaintHandler.getAllBalanceData().get(0)));
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
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)),0, 5 ,0 , 110);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 1 && position == 1){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 10, 0, 0);
        }
        else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 1 && position == 2){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 10, 0, 112);
        }
        else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 1 && position == 3){

            if (mTechGaintHandler.isExists(110) <= 0 || mTechGaintHandler.isExists(112) <= 0){
              if (mTechGaintHandler.isExists(110) <= 0){
                  Toast.makeText(getActivity(), "please complete High School", Toast.LENGTH_SHORT).show();
              }else if (mTechGaintHandler.isExists(112) <= 0){
                  Toast.makeText(getActivity(), "Please Complete University", Toast.LENGTH_SHORT).show();
              }
            }else {
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 15, 0, 0);

            }

        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 0){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 1, 0, 120);
        }
        else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 1){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 1, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 2){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 1, 0, 122);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 3){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 2, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 4){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 3, 0, 124);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 5){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 4, 0, 125);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 6){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 4, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 7){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 5, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 8){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 6, 0, 128);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 9){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 7, 0, 0);
        }
        else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 0){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, -2, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 1){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, -1, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 2){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, -5, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 3){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, -1, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 4){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 0, 0, 134);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 5){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 1, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 6){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 2, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 7){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 0, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 8){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 5, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 9){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 2, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 10){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 2, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 11){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 2, 0, 1311);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 12){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 2, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 13){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 3, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 14){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 3, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 15){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 5, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 16){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 7, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 17){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 8, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 18){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 8, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 19){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 10, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 20){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 20, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 21){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 25, 0, 0);
        }
        else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 0){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 1, -0.5, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 1){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 1, -0.5, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 2){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 5, 0.5, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 3){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 1, 0.5, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 4){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 3, 0.5, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 5){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 5, 5, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 6){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 2, 0, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 7){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 5, 0, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 8){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 5, -1, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 9){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 10, -2.5, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 10){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 5, -5, 0, 0);
        }
        else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 0){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -1, 0, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 1){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -3, 0.5, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 2){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -3, 1, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 3){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 0, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 4){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 0, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 5){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 0, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 6){
            if (mTechGaintHandler.isExists(134) <= 0 ){
                if (mTechGaintHandler.isExists(134) <= 0){
                    Toast.makeText(getActivity(), "please Buy a Bicycle", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 0, 0, 0);
            }

        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 7){
            if (mTechGaintHandler.isExists(110) <= 0 ){
                if (mTechGaintHandler.isExists(110) <= 0){
                    Toast.makeText(getActivity(), "please Finish Highschool", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -10, 0, 0, 0);
            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 8){
            if (mTechGaintHandler.isExists(110) <= 0 ||mTechGaintHandler.isExists(122) <= 0){
                if (mTechGaintHandler.isExists(110) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Highschool", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(122) <= 0){
                    Toast.makeText(getActivity(), "please Finish Communication Skills", Toast.LENGTH_SHORT).show();

                }
            }else {
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 1, 0, 0);
            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 9){
            if (mTechGaintHandler.isExists(110) <= 0 ||mTechGaintHandler.isExists(125) <= 0){
                if (mTechGaintHandler.isExists(110) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Highschool", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(125) <= 0){
                    Toast.makeText(getActivity(), "please Finish Business Skills", Toast.LENGTH_SHORT).show();

                }
            }else {
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 3, 0, 0);
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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 3, 0, 0);
            }
        }
        else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 11){
            if (mTechGaintHandler.isExists(110) <= 0 ||mTechGaintHandler.isExists(120) <= 0 ||mTechGaintHandler.isExists(122) <= 0 ||mTechGaintHandler.isExists(1311) <= 0 ||mTechGaintHandler.isExists(128) <= 0){
                if (mTechGaintHandler.isExists(110) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Highschool", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(120) <= 0){
                    Toast.makeText(getActivity(), "please Finish Driving Skills and license", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(122) <= 0){
                    Toast.makeText(getActivity(), "please Finish Communication Skills", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(1311) <= 0){
                    Toast.makeText(getActivity(), "please Buy Car", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(128) <= 0){
                    Toast.makeText(getActivity(), "please Finish Management Skills", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 4, 0, 0);
            }
        }
    }


    public void showDialogBeforUpdate(String mString, final int balance, final double health, final double experience, final double companyExperience, final int trace){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure "+ mString);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                int totalBalance = balance + mTechGaintHandler.getAllBalanceData().get(0);
                double totalHealth = health + mTechGaintHandler.getAllHealthData().get(0);
                double totalExperience = experience +mTechGaintHandler.getAllExperienceData().get(0);
                double totalCompanyExperience = companyExperience + mTechGaintHandler.getAllCompanyExperienceData().get(0);
                mTechGaintHandler.updateBalance(totalBalance);
                mTechGaintHandler.updateHealth(totalHealth);
                mTechGaintHandler.updateExperience(totalExperience);
                mTechGaintHandler.updateCompanyExperience(totalCompanyExperience);
                if (trace > 0){
                    mTechGaintHandler.insertForTrace(trace);
                }
                Log.e("Trace", "onClick: "+trace );
                updateProgressBar();
                mTextViewAccount.setText("Bank Account - $" +String.valueOf(mTechGaintHandler.getAllBalanceData().get(0)));
                Toast.makeText(getActivity(), "your current Balance is "+mTechGaintHandler.getAllBalanceData().get(0), Toast.LENGTH_SHORT).show();
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
        mProgressBarComapnyExperience.setProgress(mTechGaintHandler.getAllCompanyExperienceData().get(0).intValue());
        mTextViewHealth.setText(mTechGaintHandler.getAllHealthData().get(0).toString());
        mTextViewExperience.setText(mTechGaintHandler.getAllExperienceData().get(0).toString());
        mTextViewCompanyExperience.setText(mTechGaintHandler.getAllCompanyExperienceData().get(0).toString());
    }
}
