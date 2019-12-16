package com.example.foodforyou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images = {
        R.drawable.slide_one,
        R.drawable.sushi,
        R.drawable.track
    };

    public String[] slide_headings = {
        "food for you",
        "recommendation",
        "set your goals"
    };

    public String[] slide_desc = {
         "Food for you is a solution for you who\nhave weight problems and have a \n less healthy diet",
         "We give you food recommendations to meet\nyour calorie needs",
         "Set your goals and keep track of your weight\nand your calorie needs"
    };

    @Override
    public int getCount(){
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o){
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.imageSlide);
        TextView slideHeading = (TextView) view.findViewById(R.id.textHeading);
        TextView slideDesc = (TextView) view.findViewById(R.id.textDescription);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDesc.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout)object);
    }
}
