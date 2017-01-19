package mp.piash.tg.fragment;




import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import mp.piash.tg.MainActivity;
import mp.piash.tg.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainContentFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener{

    private static  int RC_SIGN_IN = 0;
    private static String TAG = "Main_Fragment";
    private Button mButtonPlay;
    private ImageView mImageViewSetting;
    private TextView mTextViewAcievement;
    private ImageView mImageViewHelp;
    private Button mButtonSignIn;
    private Button mButtonCancel;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Dialog mDialogFragmentLogIn;
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
        mDialogFragmentLogIn = new Dialog(getActivity(), R.style.AppCompatAlertDialogStyle);
        mDialogFragmentLogIn.setContentView(R.layout.google_log_dialog_layout);
        mButtonSignIn = (Button)mDialogFragmentLogIn.findViewById(R.id.textViewGoogleLogIn);
        mButtonCancel = (Button)mDialogFragmentLogIn.findViewById(R.id.textViewCancel);
        dialogDismiss();
        mAuth = FirebaseAuth.getInstance();
        googleAuth();

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

    private void dialogDismiss(){
        mButtonCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialogFragmentLogIn.dismiss();
                    }
                }
        );
        mButtonSignIn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        singIn();
                        mDialogFragmentLogIn.dismiss();
                    }
                }
        );
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
                    mDialogFragmentLogIn.show();


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

    private void singIn(){
        Intent singInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(singInIntent, RC_SIGN_IN);
    }
    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getActivity(),"you are logged out from google", Toast.LENGTH_SHORT).show();
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
}
