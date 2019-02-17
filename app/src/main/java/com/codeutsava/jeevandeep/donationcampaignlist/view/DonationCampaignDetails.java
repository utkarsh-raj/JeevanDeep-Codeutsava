package com.codeutsava.jeevandeep.donationcampaignlist.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeutsava.jeevandeep.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DonationCampaignDetails extends AppCompatActivity {

//    @BindView(R.id.bankname)
//    TextView bankname;

//    @BindView(R.id.bankadress)
//    TextView bankaddress;
//
//    @BindView(R.id.banknumber)
//    TextView banknumber;
//
//    @BindView(R.id.bloodbankdescription)
//    TextView bankdescription;
//
//    @BindView(R.id.share)
//    ImageView share;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

//        Intent intent = getIntent();
//        String name = intent.getStringExtra("name" );
//        String description = intent.getStringExtra("description" );
//        String organizer = intent.getStringExtra("organizer" );
//        String venue = intent.getStringExtra("venue" );

////        bankname.setText(name);
//        bankdescription.setText(description);
//        bankdescription.setText(description);

        setContentView(R.layout.activity_donation_campaign_details);


    }

}
