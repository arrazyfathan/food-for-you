package com.example.foodforyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private String[] mTitleText;
    private String[] mInfoText;
    private int[] imageResource;
    private String[] placeGuide;
    private String[] mAuthor;

    /*
    food for you
    Topik atau masalah yang ingin diselesaikankan pada aplikasi ini adalah permasalahan berat badan.
    Mengapa aplikasi tersebut harus dibangun di Android ?
    Karena pengguna android sangat banyak
    Pengguna yang akan menggunakan aplikasi ini adalah masyarakat umum (Remaja, Orang tua dll)
    Clientnya adalah ahli gizi
    Inputnya
        Data pengguna berupa : nama pengguna, email, berat bedan, tinggi badan, tingkat aktivitas,
                               tanggal lahir, target berat badan.
        Data makanan         : nama makanan,
    Outputnya
        Index BMI, kebutuhan kalori per hari dan  kategori makanan.
    Fungsi-fungsi/fitur apa saja yang ada pada aplikasi tersebut
        1. Fitur sistem pendukung dalam pemilihan makanan
        2. Fitur perhitungan index BMI
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Stetho
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        // Database
        DBAdapter db = new DBAdapter(this);
        db.open();

        //Count rows in food
        int numberRows = db.count("food");
        if (numberRows < 1) {
            DBSetupInsert setupInsert = new DBSetupInsert(this);
            setupInsert.insertAllFood();
            setupInsert.insertAllCategories();
        }

        //Check user login
        numberRows = db.count("users");
        if (numberRows <1 ){
            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);
        }


        db.close();
        //close database


        // Transparent Action Bar
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
                fragment = new FoodFragment();
                break;
            case R.id.nav_tips:
                fragment = new TipsFragment(mTitleText,mInfoText,imageResource, placeGuide, mAuthor);
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


    public void toCategoryFood(View view) {
       loadFragment(new CategoriesFragment());
    }
}

