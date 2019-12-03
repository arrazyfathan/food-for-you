package com.example.foodforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class SetGoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);

        //Transparent Action Bar
        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle("Set Goal");
    }

    public void mainMenu(View view) {
        Intent intent = new Intent(SetGoal.this, MainActivity.class);
        startActivity(intent);
    }
}
