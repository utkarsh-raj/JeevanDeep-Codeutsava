package com.codeutsava.jeevandeep.auth.login.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.auth.login.presenter.LoginPresenter;
import com.codeutsava.jeevandeep.auth.login.presenter.LoginPresenterImpl;
import com.codeutsava.jeevandeep.auth.login.provider.LoginRetrofitProvider;
import com.codeutsava.jeevandeep.auth.otpverify.view.OtpVerifyActivity;
import com.codeutsava.jeevandeep.utils.NetworkUtils;
import com.codeutsava.jeevandeep.utils.SharedPrefs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.et_mobileno)
    EditText editTextMobile;
    @BindView(R.id.login_button)
    Button login;
    @BindView(R.id.tv_mobileno)
    TextView textViewMobile;


    private LoginPresenter presenter;
    private ProgressDialog progressDialog;
    private String userNo, password;
    private SharedPrefs sharedPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedPrefs = new SharedPrefs(this);
//        if (!NetworkUtils.isNetworkAvailable(this)) {
//            Intent networkactivity = new Intent(this, NoNetworkActivity.class);
//            startActivity(networkactivity);
//        } else {
            presenter = new LoginPresenterImpl(this, new LoginRetrofitProvider());
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Please Wait...");
//        }

        textViewMobile.setVisibility(View.GONE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNo = editTextMobile.getText().toString();

                if (!isValidPhone(userNo)) {
                    editTextMobile.setError("Please enter mobile number.");
                    editTextMobile.setFocusable(true);
                    editTextMobile.requestFocus();
                } else {
                    presenter.requestLogin(userNo);

                }
            }
        });


        editTextMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editTextMobile.getText().toString().equals("")){
                    textViewMobile.setVisibility(View.GONE);
                }
                else{
                    textViewMobile.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean isValidPhone(String userNo) {
        return userNo.length() == 10 && android.util.Patterns.PHONE.matcher(userNo).matches();
    }

    @Override
    public void showMessage(String message) {
        progressDialog.dismiss();
//        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this, R.style.AlertDialogTheme);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        alertDialog.setTitle("Please try again!")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", (dialog, which) -> dialog.dismiss()).show();
    }

    @Override
    public void showProgressBar(boolean show) {
    }

    @Override
    public void showProgressDilog(boolean show) {
        if (show) {
            progressDialog.show();
        } else {
            progressDialog.hide();
        }
    }

    @Override
    public void onLoginSuccessful(String access_token) {
        progressDialog.dismiss();
        Intent intent = new Intent(LoginActivity.this, OtpVerifyActivity.class);
        startActivity(intent);

        sharedPrefs.setMobileNumber(editTextMobile.getText().toString());
        sharedPrefs.setAccessToken(access_token);
        Toast.makeText(this, ""+sharedPrefs.getAccessToken(), Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void onLoginfailure(String userNo) {
//        progressDialog.dismiss();
//        Intent otpPageIntent = new Intent(LoginActivity.this, OtpVerifyActivity.class);
//        otpPageIntent.putExtra("mobile", userNo);
//        startActivity(otpPageIntent);
//        finish();

    }

    public void goToSignup(View view) {
//        Intent signup = new Intent(LoginActivity.this, SignUpActivity.class);
//        startActivity(signup);
//        finish();
    }

    public void goToForgotPassword(View view) {
//        Intent forgotpassword = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
//        startActivity(forgotpassword);
//        finish();
    }
}
