package com.codeutsava.jeevandeep.auth.bloodbanklogin.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.auth.bloodbanklogin.presenter.LoginPresenter;
import com.codeutsava.jeevandeep.auth.bloodbanklogin.presenter.LoginPresenterImpl;
import com.codeutsava.jeevandeep.auth.bloodbanklogin.provider.LoginRetrofitProvider;
import com.codeutsava.jeevandeep.auth.login.view.LoginActivity;
import com.codeutsava.jeevandeep.auth.otpverify.view.OtpVerifyActivity;
import com.codeutsava.jeevandeep.bloodbankinventory.view.InventoryActivity;
import com.codeutsava.jeevandeep.utils.SharedPrefs;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BloodBankLoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.et_mobileno)
    EditText editTextMobile;
    @BindView(R.id.login_button)
    Button login;
    @BindView(R.id.tv_mobileno)
    TextView textViewMobile;

    @BindView(R.id.et_password)
    EditText editTextPassword;
    @BindView(R.id.tv_password)
    TextView textViewPassword;


    private LoginPresenter presenter;
    private ProgressDialog progressDialog;
    private String userNo, password;
    private SharedPrefs sharedPrefs;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodbank_login);
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
                password = editTextPassword.getText().toString();

                showProgressDilog(true);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(BloodBankLoginActivity.this, InventoryActivity.class);
                        startActivity(intent);
                        showProgressDilog(false);
                    }
                }, 1000);
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

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editTextPassword.getText().toString().equals("")){
                    textViewPassword.setVisibility(View.GONE);
                }
                else{
                    textViewPassword.setVisibility(View.VISIBLE);
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
//        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(BloodBankLoginActivity.this, R.style.AlertDialogTheme);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(BloodBankLoginActivity.this);
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
        Intent intent = new Intent(BloodBankLoginActivity.this, OtpVerifyActivity.class);
        startActivity(intent);

        sharedPrefs.setMobileNumber(editTextMobile.getText().toString());
        sharedPrefs.setAccessToken(access_token);
        Toast.makeText(this, ""+sharedPrefs.getAccessToken(), Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void onLoginfailure(String userNo) {
//        progressDialog.dismiss();
//        Intent otpPageIntent = new Intent(BloodBankLoginActivity.this, OtpVerifyActivity.class);
//        otpPageIntent.putExtra("mobile", userNo);
//        startActivity(otpPageIntent);
//        finish();

    }

    public void goToSignup(View view) {
        Intent signup = new Intent(this, LoginActivity.class);
        startActivity(signup);
        finish();
    }

    public void goToForgotPassword(View view) {
//        Intent forgotpassword = new Intent(BloodBankLoginActivity.this, ForgotPasswordActivity.class);
//        startActivity(forgotpassword);
//        finish();
    }
}
