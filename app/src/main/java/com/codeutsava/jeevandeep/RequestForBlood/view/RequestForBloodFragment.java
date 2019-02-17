package com.codeutsava.jeevandeep.RequestForBlood.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.RequestForBlood.data.RequestForBloodResponse;
import com.codeutsava.jeevandeep.RequestForBlood.presenter.RequestForBloodPresenter;
import com.codeutsava.jeevandeep.RequestForBlood.presenter.RequestForBloodPresenterImpl;
import com.codeutsava.jeevandeep.RequestForBlood.provider.RequestForBloodRetrofitProvider;
import com.codeutsava.jeevandeep.home.HomeActivity;
import com.codeutsava.jeevandeep.utils.SharedPrefs;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestForBloodFragment extends Fragment implements RequestForBloodView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    @BindView(R.id.signup_button)
    Button signUpButton;

    @BindView(R.id.et_mobileno)
    EditText userMobileNo;

    @BindView(R.id.et_emailid)
    EditText userAge;

    @BindView(R.id.et_hospitalname)
    EditText userHospitalName;

    @BindView(R.id.et_fullname)
    EditText userFullName;

    @BindView(R.id.et_treatment)
    EditText userTreatment;

    @BindView(R.id.et_numberofunits)
    EditText userNumberOfUnits;

    @BindView(R.id.et_bloodgroup)
    EditText userBloodGroup;

    @BindView(R.id.tv_fullname)
    TextView tv_fullname;

    @BindView(R.id.tv_mobileno)
    TextView tv_mobileno;

    @BindView(R.id.tv_hospitalname)
    TextView tv_hospitalname;

    @BindView(R.id.tv_emailid)
    TextView tv_age;

    @BindView(R.id.tv_bloodgroup)
    TextView tv_bloodgroup;

    @BindView(R.id.tv_numberofunits)
    TextView tv_numberofunits;

    @BindView(R.id.tv_treatment)
    TextView tv_treatment;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RequestForBloodPresenter requestForBloodPresenter;
    private ProgressDialog progressDialog;
    private String stringUserHospitalName, stringUserMobile, stringUserAge, stringUserPassword, stringUserTreatment,  stringUserBloodGroup, stringUserName, stringUserNumberOfUnits;
    private SharedPrefs sharedPrefs;
    private Context context;


    private OnFragmentInteractionListener mListener;

    public RequestForBloodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestForBloodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestForBloodFragment newInstance(String param1, String param2) {
        RequestForBloodFragment fragment = new RequestForBloodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request_for_blood, container, false);

        context = getContext();
        ButterKnife.bind(this, view);
        sharedPrefs = new SharedPrefs(context);

        requestForBloodPresenter = new RequestForBloodPresenterImpl(new RequestForBloodRetrofitProvider(), this);
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please wait, we are processing your request");

//        initialize();


        signUpButton.setOnClickListener(v -> {
            stringUserHospitalName = userHospitalName.getText().toString();
            stringUserTreatment = userTreatment.getText().toString();
            stringUserMobile = userMobileNo.getText().toString();
            stringUserAge = userAge.getText().toString();
            stringUserName = userFullName.getText().toString();
            stringUserNumberOfUnits = userNumberOfUnits.getText().toString();
            stringUserBloodGroup = userBloodGroup.getText().toString();


            if (stringUserName.isEmpty()) {
                userFullName.setError("Please enter your name");
                userFullName.setFocusable(true);
                userFullName.requestFocus();
            } else {
                if(stringUserMobile.isEmpty() || stringUserMobile.length()<10){
                    userMobileNo.setError("Please enter your mobile number");
                    userMobileNo.setFocusable(true);
                    userMobileNo.requestFocus();
                }
                else{
                        if (stringUserBloodGroup.isEmpty()) {
                            userBloodGroup.setError("Please enter your blood group");
                            userBloodGroup.setFocusable(true);
                            userBloodGroup.requestFocus();
                        } else {

                            requestForBloodPresenter.requestSignup(stringUserName, stringUserMobile, stringUserTreatment, stringUserHospitalName, stringUserBloodGroup, stringUserAge, stringUserNumberOfUnits, sharedPrefs.getAccessToken());

                    }
                }
            }
        });




        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void showMessage(String message) {
        progressDialog.dismiss();
//        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SignUpActivity.this, R.style.AlertDialogTheme);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
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
    public void onVerified(RequestForBloodResponse signupResponse) {
        Toast.makeText(context, "Your request has been sent!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        intent.putExtra("mobile", stringUserMobile);
        startActivity(intent);
        getActivity().finish();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
