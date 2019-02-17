package com.codeutsava.jeevandeep.auth.otpverify.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.auth.otpverify.presenter.OtpVerfiyPresenter;
import com.codeutsava.jeevandeep.auth.otpverify.presenter.OtpVerifyPtresenterImpl;
import com.codeutsava.jeevandeep.auth.otpverify.provider.OtpverifyRetrofitProvider;
import com.codeutsava.jeevandeep.home.HomeActivity;
import com.codeutsava.jeevandeep.utils.NetworkUtils;
import com.codeutsava.jeevandeep.utils.SharedPrefs;
import com.codeutsava.jeevandeep.utils.resendotp.RetrofitRegenerateOtpProvider;
import com.codeutsava.jeevandeep.utils.resendotp.presenter.RegenerateOtpPresenter;
import com.codeutsava.jeevandeep.utils.resendotp.presenter.RegenerateOtpPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtpVerifyActivity extends AppCompatActivity implements OtpVerifyView {


    @BindView(R.id.etotpVerify1)
    EditText editTextOtp1;

    @BindView(R.id.etotpVerify2)
    EditText editTextOtp2;

    @BindView(R.id.etotpVerify3)
    EditText editTextOtp3;

    @BindView(R.id.etotpVerify4)
    EditText editTextOtp4;

    @BindView(R.id.button_verify)
    Button buttonVerify;

    @BindView(R.id.textmobile)
    TextView mobileText;

    @BindView(R.id.imageProgressBar)
    ProgressBar progressBar;


    private OtpVerfiyPresenter otpVerfiyPresenter;

    private String otp, otp1, otp2, otp3, otp4, contactNo;
    private SharedPrefs sharedPrefs;
    private String access_token;
    Boolean enableResend = true;

    private RegenerateOtpPresenter regenerateOtpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_veify);
        ButterKnife.bind(this);
        sharedPrefs = new SharedPrefs(this);

//        if (!NetworkUtils.isNetworkAvailable(this)) {
//            Intent networkactivity = new Intent(this, NoNetworkActivity.class);
//            startActivity(networkactivity);
//        }

        Intent intent = getIntent();
        contactNo = intent.getStringExtra("mobile");
//        mobileText.append("+91" + " " + contactNo);

        mobileText.setText("your number");
        editTextOtp1.setFilters(new InputFilter[] { new InputFilter.LengthFilter(1) });
        editTextOtp2.setFilters(new InputFilter[] { new InputFilter.LengthFilter(1) });
        editTextOtp3.setFilters(new InputFilter[] { new InputFilter.LengthFilter(1) });
        editTextOtp4.setFilters(new InputFilter[] { new InputFilter.LengthFilter(1) });


        initialize();
        otpVerfiyPresenter = new OtpVerifyPtresenterImpl(this, new OtpverifyRetrofitProvider());
        regenerateOtpPresenter = new RegenerateOtpPresenterImpl(new RetrofitRegenerateOtpProvider());

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOtp();
            }
        });

    }


    public void sendOtp(){
        otp1 = editTextOtp1.getText().toString();
        otp2 = editTextOtp2.getText().toString();
        otp3 = editTextOtp3.getText().toString();
        otp4 = editTextOtp4.getText().toString();
        otp= otp1+otp2+otp3+otp4;

        if (otp.equals("")) {
            Toast.makeText(OtpVerifyActivity.this, "Please enter OTP", Toast.LENGTH_SHORT).show();

            buttonVerify.setBackgroundColor(Color.GRAY);
        } else {
            otpVerfiyPresenter.verifyOtp(sharedPrefs.getAccessToken(), Integer.parseInt(otp));
            buttonVerify.setEnabled(false);
        }
    }
    public void initialize(){
        editTextOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(editTextOtp1.getText().toString().length()==1){
                        editTextOtp2.requestFocus();
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editTextOtp2.getText().toString().length()==1){
                    editTextOtp3.requestFocus();
                }
                editTextOtp2.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                        if(keyCode == KeyEvent.KEYCODE_DEL) {
                            editTextOtp2.setText("");
                            editTextOtp1.requestFocus();
                        }
//                        else{
//                            editTextOtp3.requestFocus();
//                        }
                        return false;
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editTextOtp3.getText().toString().length()==1){
                    editTextOtp4.requestFocus();
                }
                editTextOtp3.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                        if(keyCode == KeyEvent.KEYCODE_DEL) {
                            editTextOtp3.setText("");
                            editTextOtp2.requestFocus();
                        }
                        return false;
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editTextOtp4.getText().toString().length()==1){
                    sendOtp();
                }
                editTextOtp4.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                        if(keyCode == KeyEvent.KEYCODE_DEL) {
                            editTextOtp4.setText("");
                            editTextOtp3.requestFocus();
                        }
                        return false;
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        buttonVerify.setEnabled(true);
        buttonVerify.setBackgroundColor(Color.BLACK);
        editTextOtp1.setText("");
        editTextOtp2.setText("");
        editTextOtp3.setText("");
        editTextOtp4.setText("");

    }

    @Override
    public void showProgressBar(boolean show) {
        buttonVerify.setEnabled(true);
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onOtpVerified(String access_token) {
        sharedPrefs.setLoggedInOurApp(true);
        sharedPrefs.setAccessToken(access_token);
        sharedPrefs.setMobileNumber(contactNo);
//        Intent next = new Intent(this, HomeActivity.class);
        Intent next = new Intent(this, HomeActivity.class);
        startActivity(next);
        finish();
    }

    @Override
    public void onOtpNotVerified() {
        editTextOtp1.requestFocus();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    }

