package mp.piash.tg.fragment;


import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    TechGaintHandler mTechGaintHandler;


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
                        mTechGaintHandler.getAllWishlistData();
                        mTechGaintHandler.updataData(50);
                        Log.e("Test", "onItemClicked: "+ mTechGaintHandler.getAllWishlistData().get(0));*/
                        secondFragmentEducation(position);
                        /*if ( getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 1){
                            secondFragmentEducation(position);
                        }else if (getArguments().getInt("viewpager") == 1 &&  getArguments().getInt("position") == 1){
                            secondFragmentSkill();
                        }*/

                    }
                }
        );
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
             mString = Arrays.asList(getResources().getStringArray(R.array.fouthOfficeFurnitureString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.fouthOfficeFurnitureValue));
         } else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 3){
             mString = Arrays.asList(getResources().getStringArray(R.array.fouthChooseOfficeString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.fouthChooseOfficeValue));
         }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 4){
             mString = Arrays.asList(getResources().getStringArray(R.array.fouthCustomerBaseString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.fouthCustomerBaseValue));
         }else if (getArguments().getInt("viewpager") == 3 &&  getArguments().getInt("position") == 5){
             mString = Arrays.asList(getResources().getStringArray(R.array.fouthFundingRoundString));
             mStringValues = Arrays.asList(getResources().getStringArray(R.array.fouthFundingRoundValue));
         }else {
            mString = Arrays.asList(getResources().getStringArray(R.array.SecondEducationString));
            mStringValues = Arrays.asList(getResources().getStringArray(R.array.SecondEducationValue));
        }

    }
    public void secondFragmentEducation(int position){

        if (getInterger(mStringValues.get(position)) > mTechGaintHandler.getAllWishlistData().get(0)){
            showDialog(mStringValues.get(position), getInterger(mStringValues.get(position)) );
        }else {
            showDialog(mStringValues.get(position), getInterger(mStringValues.get(position)) );
            //Toast.makeText(getActivity(), getInterger(mStringValues.get(position))+ " is  not sufficient Balance", Toast.LENGTH_SHORT).show();
        }
        /*if (position == 0){
            *//*int recentbalance  = getInterger(mStringValues.get(position));
            int currentbalance = mTechGaintHandler.getAllWishlistData().get(0);*//*
            if (getInterger(mStringValues.get(position)) > mTechGaintHandler.getAllWishlistData().get(0)){
                showDialog(mStringValues.get(position), getInterger(mStringValues.get(position)) );
            }else {
                Toast.makeText(getActivity(), getInterger(mStringValues.get(position))+ " is  not sufficient Balance", Toast.LENGTH_SHORT).show();

            }

        }*/
    }

    public void secondFragmentSkill(){
        Toast.makeText(getActivity(),"skill", Toast.LENGTH_SHORT).show();
    }

    public void showDialog(String mString, final int balance){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure "+ mString);

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

    public int getInterger(String string){

        int intergerValue =(int) Double.parseDouble(string.replaceAll("[^\\d.]", ""));

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

}
