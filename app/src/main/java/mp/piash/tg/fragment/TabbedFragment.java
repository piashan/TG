package mp.piash.tg.fragment;


import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;
import java.util.Arrays;

import mp.piash.tg.MainActivity;
import mp.piash.tg.R;
import mp.piash.tg.adapter.TabFragmentPageAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabbedFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    private static  int RC_SIGN_IN = 0;
    private static String TAG = "Tabbed_Fragment";
    private TabFragmentPageAdapter mAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ImageView mImageViewProfileImage;
    private TextView mSignInButton;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    public TabbedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabbed, container, false);
        mViewPager = (ViewPager)view.findViewById(R.id.viewpager);
        mTabLayout = (TabLayout)view.findViewById(R.id.sliding_tabs);
        mImageViewProfileImage = (ImageView)view.findViewById(R.id.ImageViewloginImage);
        mSignInButton = (TextView)view.findViewById(R.id.sign_in_button);
      /*  Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);*/

       // mAdapter = new TabFragmentPageAdapter(getChildFragmentManager());
        mAuth = FirebaseAuth.getInstance();
        googleAuth();
        if (getArguments() != null) {
            if (getArguments().getInt("trace") == 1) {
                Glide.with(getActivity())
                        .load(getArguments().getString("photoUrl"))
                        .placeholder(R.drawable.tackgainlogo)
                        .centerCrop()
                        .crossFade()
                        .into(mImageViewProfileImage);
                mSignInButton.setText("Sign out");

            }else {
                mSignInButton.setText("Sign in");
            }
        }
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getArguments().getInt("trace") == 1){
                    showDialogBeforSignOut();
                    getArguments().putInt("trace", 0);

                }else {
                    singIn();
                    getArguments().putInt("trace", 1);
                    Log.e(TAG, "onClick: "+ getArguments().getInt("trace") );

                }

            }
        });

        Integer[] myImageList = new Integer[]{R.drawable.bank, R.drawable.education, R.drawable.index22, R.drawable.lodging};
        String[] albums = getResources().getStringArray(R.array.third);
        mAdapter = new TabFragmentPageAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
      for (int i = 0; i < mTabLayout.getTabCount(); i++){
          if (i == 0){
              mTabLayout.getTabAt(i).setIcon(R.drawable.index11);
          }else if (i == 1){
              mTabLayout.getTabAt(i).setIcon(R.drawable.index22);
          }else if (i == 2){
              mTabLayout.getTabAt(i).setIcon(R.drawable.index33);
          }else if (i == 3){
              mTabLayout.getTabAt(i).setIcon(R.drawable.index44);
          }else if (i == 4){
              mTabLayout.getTabAt(i).setIcon(R.mipmap.ic_settings_black_24dp);
          }
      }
        return view;
    }
    private void googleAuth(){
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null ){
                    Log.e(TAG, "user logged in : "+user.getEmail() );
                    Toast.makeText(getActivity(),"you are Connected to google", Toast.LENGTH_SHORT).show();

                } else{
                    Log.e(TAG, "user logged out" );

                }

            }
        };
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage((MainActivity)getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    public void singIn(){
        Intent singInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(singInIntent, RC_SIGN_IN);
        Glide.with(getActivity())
                .load(getArguments().getString("photoUrl"))
                .centerCrop()
                .placeholder(R.drawable.tackgainlogo)
                .crossFade()
                .into(mImageViewProfileImage);
        mSignInButton.setText("Sign out");
    }
    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        mSignInButton.setText("Sign in");
        Toast.makeText(getActivity(),"you are logged out from google", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }else {
                Log.e(TAG, "Google Login Faile" );
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthStateListener);
        if (mGoogleApiClient != null)
            mGoogleApiClient.stopAutoManage((MainActivity)getActivity());
    }


    public void firebaseAuthWithGoogle(GoogleSignInAccount account){
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.e(TAG, "SingInwithCredential : Complete"+task.isSuccessful());
                    }
                });
    }

    public void showDialogBeforSignOut(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Confirm");
        builder.setMessage("Do you want to logout?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                signOut();
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
