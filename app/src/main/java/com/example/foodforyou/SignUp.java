package com.example.foodforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        Objects.requireNonNull(getSupportActionBar()).setTitle("Sign Up");

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
            TexterrorMessage = "Pelase fill the e-mail address.";
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
        Spinner spinnerDOBMonth = (Spinner) findViewById(R.id.spinnerDOBMonth);
        String stringDOBMonth = spinnerDOBMonth.getSelectedItem().toString();
        if (stringDOBMonth.startsWith("January")){
            stringDOBMonth = "01";
        }else if (stringDOBMonth.startsWith("February")){
            stringDOBMonth = "02";
        }else if (stringDOBMonth.startsWith("March")){
            stringDOBMonth = "03";
        }else if (stringDOBMonth.startsWith("April")){
            stringDOBMonth = "04";
        }else if (stringDOBMonth.startsWith("May")){
            stringDOBMonth = "05";
        }else if (stringDOBMonth.startsWith("June")){
            stringDOBMonth = "06";
        }else if (stringDOBMonth.startsWith("July")){
            stringDOBMonth = "07";
        }else if (stringDOBMonth.startsWith("August")){
            stringDOBMonth = "08";
        }else if (stringDOBMonth.startsWith("September")){
            stringDOBMonth = "09";
        }else if (stringDOBMonth.startsWith("October")){
            stringDOBMonth = "10";
        }else if (stringDOBMonth.startsWith("November")){
            stringDOBMonth = "11";
        }else if (stringDOBMonth.startsWith("December")){
            stringDOBMonth = "12";
        }

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
        int selectedId =radioGroupGender.getCheckedRadioButtonId();
        RadioButton radioButtonGender = (RadioButton) findViewById(selectedId);

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

        Toast.makeText(this, " Height cm " + heightCm , Toast.LENGTH_LONG).show();


        //Error Handling
        if (TexterrorMessage.isEmpty()){
            errorMessage.setVisibility(View.GONE);

        }else {
            errorMessage.setText(TexterrorMessage);
            errorMessage.setVisibility(View.VISIBLE);
        }
    } // signUpSubmit



} //public class
