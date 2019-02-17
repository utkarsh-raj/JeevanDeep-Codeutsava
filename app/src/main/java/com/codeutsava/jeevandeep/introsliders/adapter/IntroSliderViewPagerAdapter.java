package com.codeutsava.jeevandeep.introsliders.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeutsava.jeevandeep.R;


public class IntroSliderViewPagerAdapter extends PagerAdapter {

    private Context context;

    public IntroSliderViewPagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        /**Geting Inflater from Context*/
        LayoutInflater inflater = LayoutInflater.from(context);
        /**Inflating the ViewPager Layout*/
        View view = inflater.inflate(R.layout.intro_slider_view_pager_layout,container,false);
        container.addView(view);

        /**Setting up Views*/
        ConstraintLayout introsliderBack = view.findViewById(R.id.introsliderBack);
        ImageView viewPagerImageView = view.findViewById(R.id.sliderImageView);
        TextView viewPagerTextView = view.findViewById(R.id.sliderTextView);
        TextView viewPagerDiscriptionTextView = view.findViewById(R.id.sliderDescriptionTextView);

        switch (position){
            case 0:
                introsliderBack.setBackgroundResource(R.drawable.slide1);
                viewPagerImageView.setImageResource(R.drawable.one);
                viewPagerTextView.setText("Woohoo 1");
                viewPagerDiscriptionTextView.setText("Woohoo 1 des");
                break;
            case 1:
                introsliderBack.setBackgroundResource(R.drawable.slide2);
                viewPagerImageView.setImageResource(R.drawable.two);
                viewPagerTextView.setText("Woohoo 2");
                viewPagerDiscriptionTextView.setText("Woohoo 2 des");
                break;
            case 2:
                introsliderBack.setBackgroundResource(R.drawable.slide3);
                viewPagerImageView.setImageResource(R.drawable.three);
                viewPagerTextView.setText("Woohoo 3");
                viewPagerDiscriptionTextView.setText("Woohoo 3 des");
                break;
        }


        /**Assigning Values to views*/
        viewPagerDiscriptionTextView.setText("Test");
        viewPagerTextView.setText("test");

        return view;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }



}
