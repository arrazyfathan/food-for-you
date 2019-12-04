package com.example.foodforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class SignUp extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Transparent Action Bar
        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle("Sign Up");
        


    }


    public void KeMenu(View view) {
        Intent intent = new Intent(SignUp.this, SetGoal.class);
        startActivity(intent);
    }
}
