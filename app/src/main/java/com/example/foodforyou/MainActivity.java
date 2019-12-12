package com.example.foodforyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    /*
    food for you
    Topik atau masalah yang ingin diselesaikankan pada aplikasi ini adalah permasalahan berat badan.
    Dimana dengan aplikasi ini pengguna dapat megngetahui berapa kebutuhan kalori yang dibutuhkan setiapharinya,
    sehingga pengguna dapat mengontrol kalori yang masuk kedalam tubuh agar sesuai dengan kebutuhan.


    Mengapa aplikasi tersebut harus dibangun di Android ?
    Karena pengguna android sangat banyak. Selain karena penggunanya bnyak kasus ini membutuhkan monitoring kebutuhan
    kalori setiap harinya, sehingga jika inggin hasil yang maksimal pengguna harus konsisten mengecek kebutuhan
    kalori setiap harinya. Harus di android karena bisa dibawa kemana-mana.


    Pengguna yang akan menggunakan aplikasi ini adalah masyarakat umum (Remaja, Orang tua dll) yang mempunyai masalah
    berat badan.

    Clientnya adalah ahli gizi.


    Inputnya
        Data pengguna berupa : nama pengguna, email, berat bedan, tinggi badan, tingkat aktivitas,
                               tanggal lahir, target berat badan.
        Data makanan         : nama makanan, jumlah kalori.


    Outputnya
        Index BMI, kebutuhan kalori per hari dan  kategori makanan.


    Fungsi-fungsi/fitur apa saja yang ada pada aplikasi tersebut
        1. Fitur sistem pendukung dalam pemilihan makanan
        2. Fitur perhitungan index BMI dan BMR
        3. Menghitung jumlah kalori yang dimakan setiap harinya.

    */

    boolean doubleBackToExit =false;

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
            setupInsert.insertAllCategories();
            setupInsert.insertAllFood();

        }
        /* Check if there is user in the user table */
        // Count rows in user table
        numberRows = db.count("users");

        /* Close database */
        db.close();

        if(numberRows < 1){
            // Sign up
            // Toast.makeText(this, "You are only few fields away from signing up...", Toast.LENGTH_LONG).show();

            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(MainActivity.this, FragmentActivity.class);
            startActivity(i);

        }

    }

    @Override
    public void onBackPressed(){
        if (doubleBackToExit){
            super.onBackPressed();
            return;
        }

        this.doubleBackToExit = true;
        Toast.makeText(this, "Press again to exit",Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExit=false;
            }
        }, 2000);
    }


}

