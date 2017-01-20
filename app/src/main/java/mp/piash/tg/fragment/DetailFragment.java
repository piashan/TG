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
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 10, 0, 111);
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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 15, 0, 113);

            }

        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 0){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 1, 0, 120);
        }
        else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 1){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 1, 0, 121);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 2){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 1, 0, 122);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 3){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 2, 0, 123);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 4){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 3, 0, 124);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 5){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 4, 0, 125);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 6){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 4, 0, 126);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 7){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 5, 0, 127);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 8){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 6, 0, 128);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 2 && position == 9){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 7, 0, 129);
        }
        else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 0){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, -2, 0, 130);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 1){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, -1, 0, 131);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 2){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, -5, 0, 132);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 3){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, -1, 0, 0);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 4){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 0, 0, 134);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 5){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 1, 0, 135);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 6){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 2, 0, 136);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 7){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 0, 0, 137);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 8){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 5, 0, 138);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 9){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 2, 0, 139);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 10){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 2, 0, 1310);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 11){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 2, 0, 1311);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 12){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 2, 0, 1312);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 13){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 3, 0, 1313);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 14){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 3, 0, 1314);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 15){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 5, 0, 1315);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 16){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 7, 0, 1316);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 17){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 8, 0, 1317);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 18){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 8, 0, 1318);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 19){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 10, 0, 1319);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 20){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 20, 0, 1320);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 3 && position == 21){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 0, 25, 0, 1321);
        }
        else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 0){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 1, -0.5, 0, 140);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 1){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 1, -0.5, 0, 141);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 2){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 5, 0.5, 0, 142);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 3){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 1, 0.5, 0, 143);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 4){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 3, 0.5, 0, 144);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 5){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 5, 5, 0, 145);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 6){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 2, 0, 0, 146);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 7){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 5, 0, 0, 147);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 8){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 5, -1, 0, 148);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 9){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 10, -2.5, 0, 149);
        }else   if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 4 && position == 10){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , 5, -5, 0, 1410);
        }
        else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 0){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -1, 0, 0, 200);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 1){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -3, 0.5, 0, 201);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 2){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -3, 1, 0, 202);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 3){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 0, 0, 203);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 4){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 0, 0, 204);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 5){
            showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 0, 0, 205);
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 6){
            if (mTechGaintHandler.isExists(134) <= 0 ){
                if (mTechGaintHandler.isExists(134) <= 0){
                    Toast.makeText(getActivity(), "please Buy a Bicycle", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 0, 0, 206);
            }

        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 7){
            if (mTechGaintHandler.isExists(110) <= 0 ){
                if (mTechGaintHandler.isExists(110) <= 0){
                    Toast.makeText(getActivity(), "please Finish Highschool", Toast.LENGTH_SHORT).show();
                }
            }else {
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -10, 0, 0, 207);
            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 8){
            if (mTechGaintHandler.isExists(110) <= 0 ||mTechGaintHandler.isExists(122) <= 0){
                if (mTechGaintHandler.isExists(110) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Highschool", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(122) <= 0){
                    Toast.makeText(getActivity(), "please Finish Communication Skills", Toast.LENGTH_SHORT).show();

                }
            }else {
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 1, 0, 208);
            }
        }else   if (getArguments().getInt("viewpager") == 2 &&  getArguments().getInt("position") == 0 && position == 9){
            if (mTechGaintHandler.isExists(110) <= 0 ||mTechGaintHandler.isExists(125) <= 0){
                if (mTechGaintHandler.isExists(110) <= 0 ){
                    Toast.makeText(getActivity(), "please Finish Highschool", Toast.LENGTH_SHORT).show();
                }else if (mTechGaintHandler.isExists(125) <= 0){
                    Toast.makeText(getActivity(), "please Finish Business Skills", Toast.LENGTH_SHORT).show();

                }
            }else {
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 3, 0, 209);
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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 3, 0, 2010);
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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 4, 0, 2011);
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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 3, 0, 2012);
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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 4, 0, 2013);
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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 6, 0, 2014);
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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 7, 0, 210);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 8, 0, 211);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 9, 0, 212);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 10, 0, 213);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 11, 0, 214);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 12, 0, 215);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -5, 13, 0, 216);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -6, 14, 0, 217);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -7, 15, 0, 218);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -10, 18, 0, 219);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -15, 20, 0, 2110);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -20, 20, 0, 2111);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -25, 20, 0, 2112);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -25, 22, 0, 2113);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -30, 30, 0, 220);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -35, 35, 0, 221);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -40, 40, 0, 222);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -45, 45, 0, 223);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -45, 50, 0, 224);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -50, 55, 0, 225);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -55, 60, 0, 226);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -60, 65, 0, 227);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -65, 70, 0, 228);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -70, 75, 0, 229);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -70, 75, 0, 2210);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -75, 80, 0, 2211);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -100, 90, 0, 230);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -115, 95, 0, 231);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -120, 100, 0, 232);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -115, 105, 0, 233);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -120, 115, 0, 234);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -125, 120, 0, 235);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -125, 120, 0, 236);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -130, 125, 0, 237);

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
                showDialogBeforUpdate(mStringValues.get(position), getInterger(mStringValues.get(position)) , -200, 125, 0, 238);

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
