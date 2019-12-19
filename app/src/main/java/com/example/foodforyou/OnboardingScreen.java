package com.example.foodforyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnboardingScreen extends AppCompatActivity {

    private ViewPager slideViewPager;
    private LinearLayout dotsLayout;

    private TextView[] dots;
    private SlideAdapter slideAdapter;

    private Button nextButton;
    private Button skipButton;
    private Button finishButton;
    private Button backButton;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen);

        getSupportActionBar().hide();

        slideViewPager = findViewById(R.id.slideViewPager);
        dotsLayout = findViewById(R.id.dots_layout);

        skipButton = findViewById(R.id.skipButton);
        finishButton = findViewById(R.id.finishButton);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);

        slideAdapter = new SlideAdapter(this);
        slideViewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListener);

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                slideViewPager.setCurrentItem(currentPage+1);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                slideViewPager.setCurrentItem(currentPage-1);
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(OnboardingScreen.this, SignUp.class));
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(OnboardingScreen.this, SignUp.class));
            }
        });
    }

    @Override
    public void onBackPressed(){



        moveTaskToBack(true);
    }

    public void addDotsIndicator(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorWgiteTransparent));

            dotsLayout.addView(dots[i]);
        }

        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            currentPage = position;

            if(position == 0){
                nextButton.setEnabled(true);
                skipButton.setEnabled(true);
                skipButton.setVisibility(View.VISIBLE);
                backButton.setEnabled(false);
                backButton.setVisibility(View.INVISIBLE);
                finishButton.setEnabled(false);
                finishButton.setVisibility(View.INVISIBLE);

                nextButton.setText("Next");
                backButton.setText("");
                finishButton.setText("");
            }else if(position == dots.length - 1){
                nextButton.setEnabled(false);
                skipButton.setVisibility(View.INVISIBLE);
                skipButton.setEnabled(false);
                nextButton.setVisibility(View.INVISIBLE);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE);
                finishButton.setEnabled(true);
                finishButton.setVisibility(View.VISIBLE);

                nextButton.setText("");
                backButton.setText("Back");
                finishButton.setText("Finish");
            }else{
                nextButton.setEnabled(true);
                skipButton.setEnabled(false);
                skipButton.setVisibility(View.INVISIBLE);
                nextButton.setVisibility(View.VISIBLE);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE);
                finishButton.setEnabled(false);
                finishButton.setVisibility(View.INVISIBLE);

                nextButton.setText("Next");
                backButton.setText("Back");
                finishButton.setText("");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
