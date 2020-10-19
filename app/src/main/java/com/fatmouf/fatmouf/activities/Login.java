package com.fatmouf.fatmouf.activities;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.cardview.widget.CardView;

import com.fatmouf.fatmouf.R;
import com.fatmouf.fatmouf.Utilities.AppUtils;
import com.fatmouf.fatmouf.Utilities.MyLog;
import com.fatmouf.fatmouf.Utilities.PreferenceManger;
import com.fatmouf.fatmouf.models.LoginResponse;
import com.fatmouf.fatmouf.retrofit_provider.RetrofitService;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends MyAbstractActivity {

    private static final int RC_SIGN_IN = 100;

    @BindView(R.id.et_email)
    AppCompatEditText et_email;

    @BindView(R.id.et_password)
    AppCompatEditText et_password;

    @BindView(R.id.btn_loginwithfacebook)
    CardView facebook;


    @BindView(R.id.btn_loginwithgoogle)
    CardView google;


    @BindView(R.id.bar)
    ProgressBar bar;

    CallbackManager callbackManager;

    @BindView(R.id.loginFacebookButton)
    LoginButton loginButton;

    GoogleSignInClient mGoogleSignInClient;

    @BindView(R.id.sign_in_button)
    SignInButton signInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initview();
        listners();

    }


    @Override
    public void initview() {
        signInButton = findViewById(R.id.sign_in_button);
        loginButton = findViewById(R.id.loginFacebookButton);

        init();
    }

    @Override
    public void listners() {
//        getKeyHashesh();

    }


    private void init() {

/*
        Intent intent = new Intent(Login.this, SelectYourCategoryActivity.class);
        startActivity(intent);
*/

        // socialRegister("SG42442", "Shyam", "shyam.parmar@gmail.com");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("public_profile"));


        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {

                                if (object != null) {
                                    Log.d("facebook", object.toString());
                                  /*  socialRegister(object.optString("id"),
                                            object.optString("name"),
                                            object.optString("email"));
*/
                                }

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        facebook.setOnClickListener(v -> {
            loginButton.performClick();
        });

        google.setOnClickListener(v -> {
                    signIn();
                }
        );

    }


/*
    private void socialRegister(String id, String name, String email) {
        bar.setVisibility(View.VISIBLE);
        Call<LogInModel> call = RetrofitService.RetrofitService().signUpUser(id, email, name);
        call.enqueue(new Callback<LogInModel>() {
            @Override
            public void onResponse(Call<LogInModel> call, Response<LogInModel> response) {
                bar.setVisibility(View.GONE);
                MyLog.LogE("DATA", new Gson().toJson(response.body()));
                if (response.body() != null && response.body().getApi_status().equalsIgnoreCase("200")) {
                    MyLog.LogE("DATA", new Gson().toJson(response.body()));
                    PreferenceManger.setUserDetailS(response.body().getUserModel());
                    MyLog.LogE("TAG", ">>   " + response.body().getUserModel().getPhone());
                    if (response.body().getUserModel().getPhone().contains("AVS")) {
                        Intent intent = new Intent(Login.this, FillUserDetailsActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Login.this, NavigationActivity.class);
//                        Intent intent = new Intent(Login.this, FillUserDetailsActivity.class);
                        startActivity(intent);
                    }
                    finish();
                } else {
                    Toast.makeText(Login.this, "Failed to login please try later", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LogInModel> call, Throwable t) {
                bar.setVisibility(View.GONE);
                MyLog.LogE("DATA", "onFailure  " + t.getMessage());

                Toast.makeText(Login.this, "Failed to login please try later", Toast.LENGTH_SHORT).show();
            }
        });

    }
*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (account != null) {

            }
            MyLog.LogE("GOOGLE", account.getId());
            MyLog.LogE("GOOGLE", account.getEmail());
            MyLog.LogE("GOOGLE", account.getDisplayName());
            //      socialRegister(account.getId(), account.getDisplayName(), account.getEmail());
            // Signed in successfully, show authenticated UI.
            //  updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google", "signInResult:failed code=" + e.getStatusCode());
            // updateUI(null);
        }
    }


    private void getKeyHashesh() {
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.fatmouf.fatmouf", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    public void CreateNow(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void Login(View view) {
        String email = et_email.getText().toString();
        String pass = et_password.getText().toString();
        if (AppUtils.ValidateText(email, "Please enter valid email!", et_email) && AppUtils.ValidateText(pass, "Please enter valid password!", et_password)) {
            Login(email, pass);
        } else {
            Toast.makeText(this, "Please Enter Details", Toast.LENGTH_SHORT).show();
        }

    }

    private void Login(String email, String pass) {
        getProgressDialog().show();
        Call<LoginResponse> call = RetrofitService.RetrofitService().login(email, pass);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                getProgressDialog().dismiss();
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        PreferenceManger.getPreferenceManger().setAuthToken(response.body().getData().getToken());
                        PreferenceManger.getPreferenceManger().setUserdetail(response.body().getData().getUser());
                        startActivity(new Intent(Login.this, MainActivity.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                getProgressDialog().dismiss();
            }
        });
    }

    public void Forgot_password(View view) {
        startActivity(new Intent(this, ForgotpasswordActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


}
