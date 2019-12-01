package com.example.foodforyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.FrameMetrics;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        Transparent Action Bar
        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }

        //Load Fragment
        loadFragment(new HomeFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_rekomendasi:
                fragment = new RekomendasiFragment();
                break;
            case R.id.nav_tips:
                fragment = new TipsFragment();
                break;
            case R.id.nav_profil:
                fragment = new ProfileFragment();
                break;
        }
        return loadFragment(fragment);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}

