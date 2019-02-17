package com.codeutsava.jeevandeep.introsliders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codeutsava.jeevandeep.R;
import com.codeutsava.jeevandeep.auth.login.view.LoginActivity;
import com.codeutsava.jeevandeep.home.HomeActivity;
import com.codeutsava.jeevandeep.introsliders.adapter.IntroSliderViewPagerAdapter;
import com.codeutsava.jeevandeep.utils.SharedPrefs;


public class IntroSliderActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;

    private Button btnSkip;
    private Button btnNext;

    private IntroSliderViewPagerAdapter introSliderViewPagerAdapter;

    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);

        sharedPrefs = new SharedPrefs(this);

        introSliderViewPagerAdapter = new IntroSliderViewPagerAdapter(this);
        viewPager.setAdapter(introSliderViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        // adding bottom dots
        addBottomDots(0);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < 3) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });

    }


    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position ==  2) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    private void addBottomDots(int currentPage) {
        dots = new TextView[3];

        int colorsActive = getResources().getColor(R.color.dot_dark_screen1);
        int colorsInactive = getResources().getColor(R.color.dot_light_screen1);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        sharedPrefs.setFirstLaunch(false);
        startActivity(new Intent(IntroSliderActivity.this, LoginActivity.class));
        finish();
    }

}
