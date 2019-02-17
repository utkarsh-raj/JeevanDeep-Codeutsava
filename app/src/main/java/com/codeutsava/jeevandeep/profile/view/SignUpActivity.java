package com.codeutsava.jeevandeep.profile.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;


import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.auth.login.view.LoginActivity;
import com.codeutsava.jeevandeep.home.HomeActivity;
import com.codeutsava.jeevandeep.profile.data.SignupResponse;
import com.codeutsava.jeevandeep.profile.presenter.SignupPresenter;
import com.codeutsava.jeevandeep.profile.presenter.SignupPresenterImpl;
import com.codeutsava.jeevandeep.profile.provider.SignupRetrofitProvider;
import com.codeutsava.jeevandeep.utils.ImageFileUtils;
import com.codeutsava.jeevandeep.utils.SharedPrefs;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class SignUpActivity extends AppCompatActivity implements SignupView {

    @BindView(R.id.signup_button)
    Button signUpButton;
    @BindView(R.id.et_fullname)
    EditText userFullName;
    @BindView(R.id.et_name)
    EditText userLocation;
    @BindView(R.id.et_mobileno)
    EditText userMobileNo;

    @BindView(R.id.spinner)
    Spinner spinner;


    @BindView(R.id.tv_fullname)
    TextView tv_fullname;
    @BindView(R.id.tv_name)
    TextView tv_companyname;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.user_image)
    ImageView userImage;

    @BindView(R.id.edit_image)
    ImageView editImage;

    private SignupPresenter signupPresenter;
    private ProgressDialog progressDialog;
    private String stringUserLocation, stringUserFullName, stringBloodGroup;
    private SharedPrefs sharedPrefs;
    private Context context;
    private static final int GALLERY = 222;
    private Bitmap userImageBitmap;
    boolean is_imageUpdated = false;
    String[] items = new String[]{"A+", "A-","AB+", "AB-","B+","B-","O-","O+"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        context = this;
        sharedPrefs = new SharedPrefs(this);

            signupPresenter = new SignupPresenterImpl(this, new SignupRetrofitProvider());
            progressDialog = new ProgressDialog(SignUpActivity.this);
            progressDialog.setTitle("Please wait, we are creating your account.");


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        ArrayAdapter adapter = new ArrayAdapter<>

        (context, R.layout.spinner_item, items);

        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

         @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            stringBloodGroup = (String) parent.getItemAtPosition(position);
           }

     @Override

           public void onNothingSelected(AdapterView<?> parent) {
         }
      });


        initialize();
        stringUserFullName = userFullName.getText().toString();
        stringUserLocation = userLocation.getText().toString();

        signUpButton.setOnClickListener(v -> {


            if (stringUserFullName.isEmpty()) {
                userFullName.setError("Please enter your name");
                userFullName.setFocusable(true);
                userFullName.requestFocus();
            } else {
                if (stringUserLocation.isEmpty()) {
                    userLocation.setError("Please enter your company name");
                    userLocation.setFocusable(true);
                    userLocation.requestFocus();
                }
                else{
                    if (is_imageUpdated) {
                        signupPresenter.requestSignup(stringUserFullName, stringUserLocation, stringBloodGroup, ImageFileUtils.BitmapToFileConverter(context, userImageBitmap), is_imageUpdated);
                    } else {
                        signupPresenter.requestSignup(stringUserFullName, stringUserLocation, stringBloodGroup, null, is_imageUpdated);
                    }

                }

            }
        });

    }


    private boolean isValidEmail(String username) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private void initialize(){

        tv_fullname.setVisibility(View.GONE);
        tv_companyname.setVisibility(View.GONE);



        userFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(userFullName.getText().toString().equals("")){
                    tv_fullname.setVisibility(View.GONE);
                }
                else{
                    tv_fullname.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        userLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(userLocation.getText().toString().equals("")){
                    tv_companyname.setVisibility(View.GONE);
                }
                else{
                    tv_companyname.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void showMessage(String message) {
        progressDialog.dismiss();
//        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SignUpActivity.this, R.style.AlertDialogTheme);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SignUpActivity.this);
        alertDialog.setTitle("Error")
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
    public void onVerified(SignupResponse signupResponse) {

//        sharedPrefs.setLoginSkipped(true);
//        sharedPrefs.setLoggedInOurApp(false);

        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToLogin(View view) {
        Intent Login = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(Login);
        finish();
    }

    private void SelectImage() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                (dialog, which) -> {
                    switch (which) {
                        case 0:
                            choosePhotoFromGallery();
                            break;
                        case 1:
                            takePhotoFromCamera();
                            break;
                    }
                });
        pictureDialog.show();
    }

    private void takePhotoFromCamera() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted, open the camera
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, CAMERA);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            // navigate user to app settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }

//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
                }).check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }
    private void choosePhotoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (data != null) {
            if (requestCode == GALLERY) {
                Uri filePath = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), filePath);
                    saveImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();

                }
            } else if (requestCode == CAMERA) {
                @SuppressLint
                        ({"NewApi", "LocalSuppress"})
                Bitmap thumbnail = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                assert thumbnail != null;
                saveImage(thumbnail);
            }
        }
    }

    private void saveImage(Bitmap bitmap) {
        userImage.setImageBitmap(bitmap);
        userImageBitmap = bitmap;
        is_imageUpdated = true;
    }

}
