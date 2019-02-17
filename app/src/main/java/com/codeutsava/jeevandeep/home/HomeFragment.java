package com.codeutsava.jeevandeep.home;


import android.content.Context;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.utils.SharedPrefs;
import com.codeutsava.jeevandeep.utils.fcm.RetrofitSendFCMProvider;
import com.codeutsava.jeevandeep.utils.fcm.presenter.SendFCMPresenter;
import com.codeutsava.jeevandeep.utils.fcm.presenter.SendFCMPresenterImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.donateBlood)
    CardView donateBlood;

    @BindView(R.id.needBlood)
    CardView needBlood;

    @BindView(R.id.needBloodText)
    TextView needBloodText;

    @BindView(R.id.donateBloodText)
    TextView donateBloodText;


    private Context context;
    private SendFCMPresenter sendFCMPresenter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();
        ButterKnife.bind(this,view);

        final SharedPrefs sharedPrefs= new SharedPrefs(context);

        sendFCMPresenter = new SendFCMPresenterImpl(new RetrofitSendFCMProvider());

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        sendFCMPresenter.sendFCM(sharedPrefs.getAccessToken(), token);
                    }
                });

        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);

        String donateBloodString= "I want to Donate";
        SpannableString spanDonate=  new SpannableString(donateBloodString);
        spanDonate.setSpan(new RelativeSizeSpan(2f), 10,16, 0); // set size
        spanDonate.setSpan(new ForegroundColorSpan(Color.RED), 10, 16, 0);// set color
        donateBloodText.setText(spanDonate);


        String needBloodString= "I need Blood";
        SpannableString spanNeed=  new SpannableString(needBloodString);
        spanNeed.setSpan(new RelativeSizeSpan(2f), 7,12, 0); // set size
        spanNeed.setSpan(new ForegroundColorSpan(Color.RED), 7, 12, 0);// set color
        needBloodText.setText(spanNeed);



        needBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).setFragment(new DonorFragment(),"");
            }
        });

        donateBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).setFragment(new ReceiverFragment(),"");
            }
        });



        return view;
    }


}
