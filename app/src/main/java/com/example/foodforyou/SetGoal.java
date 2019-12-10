package com.example.foodforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Objects;

public class SetGoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);

        //Transparent Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle("Set Goal");

        //Listener
        Button buttonSubmit = (Button) findViewById(R.id.letsGo);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpGoalSubmit();
            }
        });

        hideErrorhandling();


    }// Oncreate

    public void signUpGoalSubmit() {
        //Open databse
        DBAdapter db = new DBAdapter(this);
        db.open();

        //Error
        TextView errorMessage = (TextView) findViewById(R.id.errorMessage);
        errorMessage.setVisibility(View.INVISIBLE);
        String TexterrorMessage = "";

        // Target Weight
        TextInputEditText editTextTargetWeight = (TextInputEditText) findViewById(R.id.targetWeight);
        String stringTargetWeight = editTextTargetWeight.getText().toString();
        double doubleTargetWeight = 0;
        try {
            doubleTargetWeight = Double.parseDouble(stringTargetWeight);
        } catch (NumberFormatException nfe) {
            TexterrorMessage = "Target weight must to be a number.";
        }

        // Spinner I want to
        Spinner spinnerIwantTo = (Spinner) findViewById(R.id.spinnerIWantTo);
        int intIWantTo = spinnerIwantTo.getSelectedItemPosition();

        //Spinner Weekly Goal
        Spinner spinnerWeeklyGoal = (Spinner) findViewById(R.id.spinnerWeeklyGoal);
        String stringWeeklyGoal = spinnerWeeklyGoal.getSelectedItem().toString();

        // Update Fields
        if (TexterrorMessage.isEmpty()) {
            errorMessage.setVisibility(View.GONE);


            long goalID = 1;

            double doubleTargetWeightSQL = db.quoteSmart(doubleTargetWeight);
            db.update("goal", "_id", goalID, "goal_target_weight", doubleTargetWeightSQL);

            int intIWantToSQL = db.quoteSmart(intIWantTo);
            db.update("goal", "_id", goalID, "goal_i_want_to", intIWantToSQL);

            String stringWeeklyGoalSQL = db.quoteSmart(stringWeeklyGoal);
            db.update("goal", "_id", goalID, "goal_weekly_goal", stringWeeklyGoalSQL);
        }

        //Calculate Energy
        if (TexterrorMessage.isEmpty()) {

            //Get row number one from users
            long rowID = 1;
            String fields[] = new String[]{
                    "_id",
                    "user_dob",
                    "user_gender",
                    "user_height",
            };
            Cursor c = db.select("users", fields, "_id", rowID);
            String userDob = c.getString(1);
            String stringUserGender = c.getString(2);
            String stringUserHeight = c.getString(3);

            // Get weight actvity level
            rowID = 1;
            String fieldsGoal[] = new String[]{
                    "_id",
                    "goal_current_weight",
                    "goal_activity_level"
            };
            Cursor cGoal = db.select("goal", fieldsGoal, "_id", rowID);
            String stringUserCurrentWeight = cGoal.getString(1);
            String stringUserActivityLevel = cGoal.getString(2);

            // Get weight
            double doubleUserCurrentWeight = 0;
            try {
                doubleUserCurrentWeight = Double.parseDouble(stringUserCurrentWeight);
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }


            // Get user age
            String[] items1 = userDob.split("-");
            String stringYear = items1[0];
            String stringMonth = items1[1];
            String stringDay = items1[2];

            int intYear = 0;
            try {
                intYear = Integer.parseInt(stringYear);
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }

            int intMonth = 0;
            try {
                intMonth = Integer.parseInt(stringMonth);
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }
            int intDay = 0;
            try {
                intDay = Integer.parseInt(stringDay);
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }

            String stringUserAge = getAge(intYear, intMonth, intDay);

            int intAge = 0;
            try {
                intAge = Integer.parseInt(stringUserAge);
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }

            //Height
            double doubleUserHeight = 0;
            try {
                doubleUserHeight = Double.parseDouble(stringUserHeight);
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }

            long goalID = 1;

            // Hitung BMI
            double goalBMI = 0;
            goalBMI = doubleUserCurrentWeight / ((doubleUserHeight / 100) * (doubleUserHeight / 100));
            goalBMI = Math.round(goalBMI);
            db.update("goal", "_id", goalID, "goal_bmi", goalBMI);


            /*    Hitung BMR    */
            // Start calculation
            double goalEnergyBMR = 0;
            if (stringUserGender.startsWith("m")) {
                // Male
                goalEnergyBMR = 88.362 + (13.397 * doubleUserCurrentWeight) + (4.799 * doubleUserHeight) - (5.677 * intAge);
            } else {
                //Female
                goalEnergyBMR = 447.593 + (9.247 * doubleUserCurrentWeight) + (3.098 * doubleUserHeight) - (4.330 * intAge);

            }
            // Update database
            goalEnergyBMR = Math.round(goalEnergyBMR);
            double energyBmrSQL = db.quoteSmart(goalEnergyBMR);
            db.update("goal", "_id", goalID, "goal_energy_bmr", energyBmrSQL);

            /*    Hitung Diet    */
            double doubleWeeklyGoal = 0;
            try {
                doubleWeeklyGoal = Double.parseDouble(stringWeeklyGoal);
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }
            // 1 kg fat = 7700 kcal
            double kcal = 0;
            double energyDiet = 0;
            kcal = 7700 * doubleWeeklyGoal;
            if (intIWantTo == 0) {
                // Loose weight
                energyDiet = Math.round((goalEnergyBMR - (kcal / 7)) * 1.2);

            } else {
                // Gain weight
                energyDiet = Math.round((goalEnergyBMR + (kcal / 7)) * 1.2);
            }
            // Update database
            double energyDietSQL = db.quoteSmart(energyDiet);
            db.update("goal", "_id", goalID, "goal_energy_diet", energyDietSQL);


            /*  With activity */
            double energyWithActivity = 0;
            if (stringUserActivityLevel.equals("0")) {
                energyWithActivity = goalEnergyBMR * 1.2;
            } else if (stringUserActivityLevel.equals("1")) {
                energyWithActivity = goalEnergyBMR * 1.375;
            } else if (stringUserActivityLevel.equals("2")) {
                energyWithActivity = goalEnergyBMR * 1.55;
            } else if (stringUserActivityLevel.equals("3")) {
                energyWithActivity = goalEnergyBMR * 1.725;
            } else if (stringUserActivityLevel.equals("4")) {
                energyWithActivity = goalEnergyBMR * 1.9;
            }
            energyWithActivity = Math.round(energyWithActivity);
            double energyWithActivitySQL = db.quoteSmart(energyWithActivity);
            db.update("goal", "_id", goalID, "goal_energy_with_activity", energyWithActivitySQL);


            /* 4: With_activity_and_diet */
            // If you want to loose your weight
            // With activity
            // 1 kg fat = 7700 kcal
            kcal = 0;
            double energyWithActivityAndDiet = 0;
            kcal = 7700 * doubleWeeklyGoal;

            if (intIWantTo == 0) {
                // Loose weight
                energyWithActivityAndDiet = goalEnergyBMR - (kcal / 7);

            } else {
                // Gain weight
                energyWithActivityAndDiet = goalEnergyBMR + (kcal / 7);
            }

            if (stringUserActivityLevel.equals("0")) {
                energyWithActivityAndDiet = energyWithActivityAndDiet * 1.2;
            } else if (stringUserActivityLevel.equals("1")) {
                energyWithActivityAndDiet = energyWithActivityAndDiet * 1.375; // slightly_active
            } else if (stringUserActivityLevel.equals("2")) {
                energyWithActivityAndDiet = energyWithActivityAndDiet * 1.55; // moderately_active
            } else if (stringUserActivityLevel.equals("3")) {
                energyWithActivityAndDiet = energyWithActivityAndDiet * 1.725; // active_lifestyle
            } else if (stringUserActivityLevel.equals("4")) {
                energyWithActivityAndDiet = energyWithActivityAndDiet * 1.9; // very_active
            }
            energyWithActivityAndDiet = Math.round(energyWithActivityAndDiet);


            // Update database
            double energyWithActivityAndDietSQL = db.quoteSmart(energyWithActivityAndDiet);
            db.update("goal", "_id", goalID, "goal_energy_with_activity_and_diet", energyWithActivityAndDietSQL);


        } //calculate Energy

        // Error Handling
        if (!(TexterrorMessage.isEmpty())) {
            errorMessage.setText(TexterrorMessage);
            errorMessage.setVisibility(View.VISIBLE);
        }

        db.close();

        if (TexterrorMessage.isEmpty()) {
            Intent i = new Intent(SetGoal.this, MainActivity.class);
            startActivity(i);
        }

    }// SignUP Submit

    private String getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();
        return ageS;
    }

    public void hideErrorhandling() {
        TextView errorMessage = (TextView) findViewById(R.id.errorMessage);
        errorMessage.setVisibility(View.GONE);
    }

}
