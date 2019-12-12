package com.example.foodforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class SignUp extends AppCompatActivity {

    //Variable
    private String[] arraySpinnerDOBDay = new String[31];
    private String[] arraySpinnerDOBYear = new String[100];

    boolean doubleBackToExit =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Fill number day spinner -------------------------------------------------------------------
        int human_counter = 0;
        for (int x=0;x<31;x++){
            human_counter = x+1;
            this.arraySpinnerDOBDay[x] = "" + human_counter;
            }
        Spinner spinnerDOBDay = (Spinner) findViewById(R.id.spinnerDOBDay);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraySpinnerDOBDay);
        spinnerDOBDay.setAdapter(adapter);

        //Fill number Year Spinner
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int end = year-100;
        int index = 0;
        for (int x=year;x>end;x--){
            this.arraySpinnerDOBYear[index] = "" + x;
            index++;
        }
        Spinner spinnerDOBYear = (Spinner) findViewById(R.id.spinnerDOBYear);
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinnerDOBYear);
        spinnerDOBYear.setAdapter(adapterYear);

        //-------------------------------------------------------------------

        //Transparent Action Bar
        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle("Get Ready");

        //Listener
        Button buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpSubmit();
            }
        });

        TextView errorMessage = (TextView) findViewById(R.id.errorMessage);
        errorMessage.setVisibility(View.GONE);

    } // OnCreate

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

//    @Override
//    public void onBackPressed(){
//        if (doubleBackToExit){
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExit = true;
//        Toast.makeText(this, "Press again to exit",Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                doubleBackToExit=false;
//            }
//        }, 2000);
//    }


    public void signUpSubmit(){
        //Error
        TextView errorMessage = (TextView) findViewById(R.id.errorMessage);
        errorMessage.setVisibility(View.INVISIBLE);
        String TexterrorMessage = "";

        //Email
        TextInputEditText editTextEmail = (TextInputEditText) findViewById(R.id.editTextEmail);
        String stringEmail = editTextEmail.getText().toString();
        if (stringEmail.isEmpty() | stringEmail.startsWith(" ")){
            errorMessage.setVisibility(View.VISIBLE);
            TexterrorMessage = "Pelase fill the name.";
        }

        //Date of Bird Day
        Spinner spinnerDOBDay = (Spinner) findViewById(R.id.spinnerDOBDay);
        String stringDOBDay = spinnerDOBDay.getSelectedItem().toString();
        int intDOBDay = 0;
        try {
            intDOBDay = Integer.parseInt(stringDOBDay);
            if (intDOBDay < 10) {
                stringDOBDay = "0" + stringDOBDay;
            }
        }catch (NumberFormatException nfe){
            System.out.println("Could not parse " + nfe);
            errorMessage.setVisibility(View.VISIBLE);
            TexterrorMessage = "Pelase selet a day for your birthday.";
        }

        //Date of Bird Month

        Spinner spinnerDOBMonth = (Spinner)findViewById(R.id.spinnerDOBMonth);
        String stringDOBMonth = spinnerDOBMonth.getSelectedItem().toString();
        int positionDOBMonth = spinnerDOBMonth.getSelectedItemPosition();
        int month = positionDOBMonth+1;
        if(month < 10){
            stringDOBMonth = "0" + month;
        }
        else{
            stringDOBMonth = "" + month;
        }
//        Spinner spinnerDOBMonth = (Spinner) findViewById(R.id.spinnerDOBMonth);
//        String stringDOBMonth = spinnerDOBMonth.getSelectedItem().toString();
//        if (stringDOBMonth.startsWith("January")){
//            stringDOBMonth = "01";
//        }else if (stringDOBMonth.startsWith("February")){
//            stringDOBMonth = "02";
//        }else if (stringDOBMonth.startsWith("March")){
//            stringDOBMonth = "03";
//        }else if (stringDOBMonth.startsWith("April")){
//            stringDOBMonth = "04";
//        }else if (stringDOBMonth.startsWith("May")){
//            stringDOBMonth = "05";
//        }else if (stringDOBMonth.startsWith("June")){
//            stringDOBMonth = "06";
//        }else if (stringDOBMonth.startsWith("July")){
//            stringDOBMonth = "07";
//        }else if (stringDOBMonth.startsWith("August")){
//            stringDOBMonth = "08";
//        }else if (stringDOBMonth.startsWith("September")){
//            stringDOBMonth = "09";
//        }else if (stringDOBMonth.startsWith("October")){
//            stringDOBMonth = "10";
//        }else if (stringDOBMonth.startsWith("November")){
//            stringDOBMonth = "11";
//        }else if (stringDOBMonth.startsWith("December")){
//            stringDOBMonth = "12";
//        }

        //Date of Bird Year
        Spinner spinnerDOBYear = (Spinner)findViewById(R.id.spinnerDOBYear);
        String stringDOBYear = spinnerDOBYear.getSelectedItem().toString();
        int intDOBYear = 0;
        try {
            intDOBYear = Integer.parseInt(stringDOBYear);
        }
        catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
            TexterrorMessage = "Please select a year for your birthday.";
        }

        // Put date of birth togheter
        String dateOfBirth = intDOBYear + "-" + stringDOBMonth + "-" + stringDOBDay;



        //Gender radio button
        RadioGroup radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        int radioButtonID = radioGroupGender.getCheckedRadioButtonId();
        View radioButtonGender = radioGroupGender.findViewById(radioButtonID);
        int position = radioGroupGender.indexOfChild(radioButtonGender);

        String stringGender = "";
        if(position == 0){
                stringGender = "male";
            }
        else{
            stringGender = "female";
        }

        //Mesurement Spinner
        Spinner spinnerMesurment = (Spinner) findViewById(R.id.spinnerMesurment);
        String stringMesurment = spinnerMesurment.getSelectedItem().toString();

        // Height
        TextInputEditText editTextHeightCm = (TextInputEditText) findViewById(R.id.editTextHeight);
        String stringHeightCm = editTextHeightCm.getText().toString();
        double heightCm = 0;
        try {
            heightCm = Double.parseDouble(stringHeightCm);
        }catch (NumberFormatException nfe){
            TexterrorMessage = "Height has to be  a number.";
        }


        //Weight
        TextInputEditText editTextWeightKg = (TextInputEditText) findViewById(R.id.editTextWeight);
        String stringWeightKg = editTextWeightKg.getText().toString();
        double weightKg = 0;
        try {
            weightKg = Double.parseDouble(stringWeightKg);
        }catch (NumberFormatException nfe) {
            TexterrorMessage = "Weight has to be a number";
        }


        //Activity Level
        Spinner spinnerActivityLevel = (Spinner) findViewById(R.id.spinnerActivityLevel);
        int intActivityLevel = spinnerActivityLevel.getSelectedItemPosition();
        //  0: Little to no exercise
        // 1: Light exercise (1–3 days per week)
        // 2: Moderate exercise (3–5 days per week)
        // 3: Heavy exercise (6–7 days per week)
        // 4: Very heavy exercise (twice per day, extra heavy workouts)



        //Error Handling
        if (TexterrorMessage.isEmpty()){
            errorMessage.setVisibility(View.GONE);


            // inset data into database
            DBAdapter db = new DBAdapter(this);
            db.open();

            //quoteSmart
            String stringEmailSQL = db.quoteSmart(stringEmail);
            String dateOfBirthSQL = db.quoteSmart(dateOfBirth);
            String stringGenderSQL = db.quoteSmart(stringGender);
            double heightCmSQL = db.quoteSmart(heightCm);
            int intActivityLevelSQL = db.quoteSmart(intActivityLevel);
            double weightKgSQL = db.quoteSmart(weightKg);

            String stringInput = "NULL, " + stringEmailSQL + "," + dateOfBirthSQL + "," + stringGenderSQL + "," + heightCmSQL;


            // Input for user
            db.insert("users",
                    "_id, user_email, user_dob, user_gender, user_height",
                    stringInput);

            // Input for gaols
            DateFormat dfl = new SimpleDateFormat("yyyy-MM-dd");
            String goalDate = dfl.format(Calendar.getInstance().getTime());

            String goalDateSQL = db.quoteSmart(goalDate);

            stringInput = "NULL, " + weightKgSQL + "," + goalDateSQL + "," +intActivityLevelSQL;
            db.insert("goal",
                    "_id, goal_current_weight, goal_date,goal_activity_level",
                    stringInput);

            db.close();

            //Move User back to mainactivity
            Intent i = new Intent(SignUp.this, SetGoal.class);
            startActivity(i);

        }else {
            errorMessage.setText(TexterrorMessage);
            errorMessage.setVisibility(View.VISIBLE);
        }
    } // signUpSubmit



} //public class
