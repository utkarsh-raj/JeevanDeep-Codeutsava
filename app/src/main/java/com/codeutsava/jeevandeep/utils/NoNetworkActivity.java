//package com.codeutsava.jeevandeep.utils;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//
//
//import com.codeutsava.jeevandeep.R;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class NoNetworkActivity extends AppCompatActivity {
//
//    @BindView(R.id.retry)
//    Button button;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_no_network);
//        ButterKnife.bind(this);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (NetworkUtils.isNetworkAvailable(NoNetworkActivity.this)){
//                    finish();
//                }else{
//                    Intent networkactivity = new Intent(NoNetworkActivity.this,NoNetworkActivity.class);
//                    startActivity(networkactivity);
//                    finish();
//                }
//            }
//        });
//
//    }
//}
