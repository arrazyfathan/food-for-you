package com.example.foodforyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

public class Recommendation extends AppCompatActivity
        implements RecA.OnFragmentInteractionListener,
        RecB.OnFragmentInteractionListener,
        RecC.OnFragmentInteractionListener,
        RecD.OnFragmentInteractionListener,
        RecE.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        // Transparent Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

        getRecommendation();
    }

    public void getRecommendation() {
        DBAdapter db = new DBAdapter(this);
        db.open();


        // Get goal
        String fieldsGoal[] = new String[]{
                "_id",
                "goal_energy_with_activity_and_diet"
        };
        Cursor cursorGoal = db.select("goal", fieldsGoal);
        cursorGoal.moveToLast();
        String stringGoalEnergyWithActivityAndDiet = cursorGoal.getString(1);

        int intGoalEnergyWithActivityAndDiet = 0;
        try {
            intGoalEnergyWithActivityAndDiet = Integer.parseInt(stringGoalEnergyWithActivityAndDiet);
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }

        if (intGoalEnergyWithActivityAndDiet >= 1 && intGoalEnergyWithActivityAndDiet <= 1800) {
            loadFragment(new RecA());

        } else if (intGoalEnergyWithActivityAndDiet > 1800 && intGoalEnergyWithActivityAndDiet <= 2200) {
            loadFragment(new RecB());
        } else if (intGoalEnergyWithActivityAndDiet > 2200 && intGoalEnergyWithActivityAndDiet <= 2600) {
            loadFragment(new RecC());
        } else if (intGoalEnergyWithActivityAndDiet > 2600 && intGoalEnergyWithActivityAndDiet <= 3000) {
            loadFragment(new RecD());
        } else {
            loadFragment(new RecE());
        }

    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_recommendation, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
