package com.example.foodforyou;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class GoalFragment extends Fragment {

    private View mainView;


    private MenuItem menuItemEdit;
    private MenuItem menuItemDelete;

    private String currentId;
    private String currentName;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public GoalFragment() {

    }


    public static GoalFragment newInstance(String param1, String param2) {
        GoalFragment fragment = new GoalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Goal");

        initalizeGetDataFromDbAndDisplay();

        setHasOptionsMenu(true);
    } // onActivityCreated

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_goal, container, false);
        return mainView;
    }

    private void setMainView(int id){
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainView = inflater.inflate(id, null);
        ViewGroup rootView = (ViewGroup) getView();
        rootView.removeAllViews();
        rootView.addView(mainView);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        MenuInflater menuInflater = ((MainActivity)getActivity()).getMenuInflater();
        inflater.inflate(R.menu.menu_goal, menu);


        menuItemEdit = menu.findItem(R.id.menu_action_goal_edit);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        int id = menuItem.getItemId();
        if (id == R.id.menu_action_goal_edit) {
            goalEdit();
            setHasOptionsMenu(false);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void initalizeGetDataFromDbAndDisplay(){
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        long rowID = 1;
        String fields[] = new String[] {
                "_id"
        };
        Cursor c = db.select("users", fields, "_id", rowID);


        // Get Goal data
        String fieldsGoal[] = new String[] {
                "_id",
                "goal_current_weight",
                "goal_target_weight",
                "goal_i_want_to",
                "goal_weekly_goal",
                "goal_activity_level",
                "goal_date",
        };
        Cursor goalCursor = db.select("goal", fieldsGoal, "", "", "_id", "DESC");


        // Ready as variables
        String goalID = goalCursor.getString(0);
        String goalCurrentWeight = goalCursor.getString(1);
        String goalTargetWeight = goalCursor.getString(2);
        String goalIWantTo = goalCursor.getString(3);
        String goalWeeklyGoal = goalCursor.getString(4);
        String goalActivityLevel = goalCursor.getString(5);
        String goalDate = goalCursor.getString(6);


        // Current weight
        TextView textViewGoalCurrentWeightNumber = (TextView)getActivity().findViewById(R.id.textViewGoalCurrentWeightNumber);
        textViewGoalCurrentWeightNumber.setText(goalCurrentWeight);

        //  Target
        TextView textViewGoalCurrentTargetNumber = (TextView)getActivity().findViewById(R.id.textViewGoalCurrentTargetNumber);
        textViewGoalCurrentTargetNumber.setText(goalTargetWeight);

        // Method
        TextView textViewGoalMethodText = (TextView)getActivity().findViewById(R.id.textViewGoalMethodText);

        String method = "";
        if(goalIWantTo.equals("0")){
            method = "Lose Weight "  + goalWeeklyGoal;
        }
        else{
            method = "Gain Weight "  + goalWeeklyGoal;
        }


        /* Activity level */
        TextView textViewActivityLevel = (TextView)getActivity().findViewById(R.id.textViewActivityLevel);
        if(goalActivityLevel.equals("0")){
            textViewActivityLevel.setText("Little to no exercise");
        }
        else if(goalActivityLevel.equals("1")){
            textViewActivityLevel.setText("Light exercise (1–3 days per week)");
        }
        else if(goalActivityLevel.equals("2")){
            textViewActivityLevel.setText("Moderate exercise (3–5 days per week)");
        }
        else if(goalActivityLevel.equals("3")){
            textViewActivityLevel.setText("Heavy exercise (6–7 days per week)");
        }
        else if(goalActivityLevel.equals("4")){
            textViewActivityLevel.setText("Very heavy exercise (twice per day, extra heavy workouts)");
        }


        /* Numbers */
        updateNumberTable();

        db.close();
    } // initalizeGetDataFromDbAndDisplay



    /*- Goal edit ------------------------------------------------------------------ */
    public void goalEdit(){
        /* Change layout */
        int id = R.layout.fragment_goal_edit;
        setMainView(id);


        DBAdapter db = new DBAdapter(getActivity());
        db.open();


        /* Get row number one from users */
        long rowID = 1;
        String fields[] = new String[] {
                "_id"

        };
        Cursor c = db.select("users", fields, "_id", rowID);

        // Get Goal data
        String fieldsGoal[] = new String[] {
                "_id",
                "goal_current_weight",
                "goal_target_weight",
                "goal_i_want_to",
                "goal_weekly_goal",
                "goal_activity_level"
        };
        Cursor goalCursor = db.select("goal", fieldsGoal, "", "", "_id", "DESC");

        // Ready as variables
        String goalID = goalCursor.getString(0);
        String goalCurrentWeight = goalCursor.getString(1);
        String goalTargetWeight = goalCursor.getString(2);
        String goalIWantTo = goalCursor.getString(3);
        String goalWeeklyGoal = goalCursor.getString(4);
        String goalActivityLevel = goalCursor.getString(5);

        // Current weight
        TextInputEditText editTextGoalCurrentWeight = getActivity().findViewById(R.id.editTextGoalCurrentWeight);
        editTextGoalCurrentWeight.setText(goalCurrentWeight);


        //  Target
        TextInputEditText editTextGoalTargetWeight =getActivity().findViewById(R.id.editTextGoalTargetWeight);
        editTextGoalTargetWeight.setText(goalTargetWeight);


        // I want to
        Spinner spinnerIWantTo =getActivity().findViewById(R.id.spinnerIWantTo);
        if(goalIWantTo.equals("0")){
            spinnerIWantTo.setSelection(0);
        }
        else{
            spinnerIWantTo.setSelection(1);
        }

        // Weekly goal
        Spinner spinnerWeeklyGoal = getActivity().findViewById(R.id.spinnerWeeklyGoal);
        if(goalWeeklyGoal.equals("0.5")){
            spinnerWeeklyGoal.setSelection(0);
        }
        else if(goalWeeklyGoal.equals("1")){
            spinnerWeeklyGoal.setSelection(1);
        }
        else if(goalWeeklyGoal.equals("1.5")){
            spinnerWeeklyGoal.setSelection(2);
        }

        // Activity level
        Spinner spinnerActivityLevel = (Spinner)getActivity().findViewById(R.id.spinnerActivityLevel);
        int intActivityLevel = 0;
        try{
            intActivityLevel = Integer.parseInt(goalActivityLevel);
        }
        catch (NumberFormatException e){

        }
        spinnerActivityLevel.setSelection(intActivityLevel);



        // Update table
        updateNumberTable();

        /* SubmitButton listener */
        Button buttonGoalSubmit = (Button)getActivity().findViewById(R.id.buttonGoalSubmit);
        buttonGoalSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editGoalSubmitOnClick();
            }
        });

        // Close db
        db.close();



    } // goalEdit

    /*- editGoalSubmitOnClick ------------------------------------------------------ */
    public void editGoalSubmitOnClick(){

        int error = 0;
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        long rowID = 1;
        String fields[] = new String[] {
                "_id",
                "user_dob",
                "user_gender",
                "user_height"
        };
        Cursor c = db.select("users", fields, "_id", rowID);
        String stringUserDob = c.getString(1);
        String stringUserGender  = c.getString(2);
        String stringUserHeight = c.getString(3);


        // Get Age
        String[] items1 = stringUserDob.split("-");
        String stringYear = items1[0];
        String stringMonth = items1[1];
        String stringDay = items1[2];

        int intYear = 0;
        try {
            intYear = Integer.parseInt(stringYear);
        }
        catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        int intMonth = 0;
        try {
            intMonth = Integer.parseInt(stringMonth);
        }
        catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        int intDay = 0;
        try {
            intDay = Integer.parseInt(stringDay);
        }
        catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        String stringUserAge = getAge(intYear, intMonth, intDay);

        int intUserAge = 0;
        try {
            intUserAge = Integer.parseInt(stringUserAge);
        }
        catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }

        // Get height
        double doubleUserHeight = 0;

        try {
            doubleUserHeight = Double.parseDouble(stringUserHeight);
        }
        catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }


        // Current weight
        TextInputEditText editTextGoalCurrentWeight = getActivity().findViewById(R.id.editTextGoalCurrentWeight);
        String stringCurrentWeight = editTextGoalCurrentWeight.getText().toString();
        double doubleCurrentWeight = 0;
        if(stringCurrentWeight.isEmpty()){
            Toast.makeText(getActivity(), "Please enter current weight", Toast.LENGTH_LONG).show();
             error = 1;
        }
        else{
            try {
                doubleCurrentWeight = Double.parseDouble(stringCurrentWeight);
            }
            catch(NumberFormatException nfe) {
                Toast.makeText(getActivity(), "Current weight has to be a number.\nError: " + nfe.toString(), Toast.LENGTH_LONG).show();
                error = 1;
            }
        }
        String stringCurrentWeightSQL = db.quoteSmart(stringCurrentWeight);

        // Target weight
        TextInputEditText editTextGoalTargetWeight = getActivity().findViewById(R.id.editTextGoalTargetWeight);
        String stringTargetWeight = editTextGoalTargetWeight.getText().toString();
        double doubleTargetWeight = 0;
        if(stringTargetWeight.isEmpty()){
            Toast.makeText(getActivity(), "Please enter target weight", Toast.LENGTH_LONG).show();
            error = 1;
        }
        else{
            try {
                doubleTargetWeight = Double.parseDouble(stringTargetWeight);
            }
            catch(NumberFormatException nfe) {
                Toast.makeText(getActivity(), "Target weight has to be a number.\nError: " + nfe.toString(), Toast.LENGTH_LONG).show();
                error = 1;
            }
        }
        String stringTargetWeightSQL = db.quoteSmart(stringTargetWeight);

        // I want to
        Spinner spinnerIWantTo = getActivity().findViewById(R.id.spinnerIWantTo);
        int intIWantTo = spinnerIWantTo.getSelectedItemPosition();
        String stringIWantTo = "" + intIWantTo;
        String stringIWantToSQL = db.quoteSmart(stringIWantTo);

        /* Spinner spinnerWeeklyGoal */
        Spinner spinnerWeeklyGoal = getActivity().findViewById(R.id.spinnerWeeklyGoal);
        String stringWeeklyGoal = spinnerWeeklyGoal.getSelectedItem().toString();
        String stringWeeklyGoalSQL = db.quoteSmart(stringWeeklyGoal);

        Spinner spinnerActivityLevel = getActivity().findViewById(R.id.spinnerActivityLevel);

        int intActivityLevel = spinnerActivityLevel.getSelectedItemPosition();
        String stringActivityLevel = ""+intActivityLevel;
        String stringActivityLevelSQL = db.quoteSmart(stringActivityLevel);

        /* TextView Calculation */
        TextView textViewCalculation = (TextView)getActivity().findViewById(R.id.textViewCalculation);

        if(error == 0) {
            /* Insert into database */

            // Date
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");//foramt date
            String goalDate = df1.format(Calendar.getInstance().getTime());
            String goalDateSQL = db.quoteSmart(goalDate);

            // Hitung BMI
            double goalBMI = 0;
            goalBMI = doubleCurrentWeight / ((doubleUserHeight / 100) * (doubleUserHeight / 100));
            goalBMI = Math.round(goalBMI);
            String goalEnergyBMISQL = db.quoteSmart(""+goalBMI);


            double goalEnergyBMR = 0;

            if(stringUserGender.startsWith("m")){
                goalEnergyBMR = 66.5+(13.75*doubleCurrentWeight)+(5.003*doubleUserHeight)-(6.755*intUserAge);
            }
            else{
                goalEnergyBMR = 655+(9.563*doubleCurrentWeight)+(1.850*doubleUserHeight)-(4.676*intUserAge);

            }
            goalEnergyBMR = Math.round(goalEnergyBMR);
            String goalEnergyBMRSQL = db.quoteSmart(""+goalEnergyBMR);



            double doubleWeeklyGoal = 0;
            try {
                doubleWeeklyGoal = Double.parseDouble(stringWeeklyGoal);
            }
            catch(NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }

            // 1 kg fat = 7700 kcal
            double kcal = 0;
            double energyDiet = 0;
            kcal = 7700*doubleWeeklyGoal;
            if(intIWantTo == 0){
                // Loose weight
                energyDiet = Math.round((goalEnergyBMR - (kcal/7)) * 1.2);

            }
            else{
                // Gain weight
                energyDiet = Math.round((goalEnergyBMR + (kcal/7)) * 1.2);
            }

            // Update database
            double energyDietSQL = db.quoteSmart(energyDiet);





            /* 3: With activity */
            // If you want to keep your weight
            //
            // Taking in to account activity
            double energyWithActivity = 0;
            if(stringActivityLevel.equals("0")) {
                energyWithActivity = goalEnergyBMR * 1.2;
            }
            else if(stringActivityLevel.equals("1")) {
                energyWithActivity = goalEnergyBMR * 1.375; // slightly_active
            }
            else if(stringActivityLevel.equals("2")) {
                energyWithActivity = goalEnergyBMR*1.55; // moderately_active
            }
            else if(stringActivityLevel.equals("3")) {
                energyWithActivity = goalEnergyBMR*1.725; // active_lifestyle
            }
            else if(stringActivityLevel.equals("4")) {
                energyWithActivity = goalEnergyBMR * 1.9; // very_active
            }
            energyWithActivity = Math.round(energyWithActivity);
            double energyWithActivitySQL = db.quoteSmart(energyWithActivity);



            /* 4: With_activity_and_diet */
            // If you want to loose your weight
            // With activity
            // 1 kg fat = 7700 kcal
            kcal = 0;
            double energyWithActivityAndDiet = 0;
            kcal = 7700*doubleWeeklyGoal;
            if(intIWantTo == 0){
                // Loose weight
                energyWithActivityAndDiet = goalEnergyBMR - (kcal/7);
            }
            else{
                // Gain weight
                energyWithActivityAndDiet = goalEnergyBMR + (kcal/7);
            }
            if(stringActivityLevel.equals("0")) {
                energyWithActivityAndDiet= energyWithActivityAndDiet* 1.2;
            }
            else if(stringActivityLevel.equals("1")) {
                energyWithActivityAndDiet= energyWithActivityAndDiet* 1.375; // slightly_active
            }
            else if(stringActivityLevel.equals("2")) {
                energyWithActivityAndDiet= energyWithActivityAndDiet*1.55; // moderately_active
            }
            else if(stringActivityLevel.equals("3")) {
                energyWithActivityAndDiet= energyWithActivityAndDiet*1.725; // active_lifestyle
            }
            else if(stringActivityLevel.equals("4")) {
                energyWithActivityAndDiet = energyWithActivityAndDiet* 1.9; // very_active
            }
            energyWithActivityAndDiet = Math.round(energyWithActivityAndDiet);

            // Update database
            double energyWithActivityAndDietSQL = db.quoteSmart(energyWithActivityAndDiet);




            // Insert
            String inpFields = "'_id', " +
                    "'goal_current_weight', " +
                    "'goal_target_weight', " +
                    "'goal_i_want_to', " +
                    "'goal_weekly_goal', " +
                    "'goal_date'," +
                    "'goal_activity_level'," +
                    "'goal_energy_bmr'," +
                    "'goal_energy_diet'," +
                    "'goal_energy_with_activity'," +
                    "'goal_bmi'," +
                    "'goal_energy_with_activity_and_diet'";

            String inpValues = "NULL, " +
                    stringCurrentWeightSQL + ", " +
                    stringTargetWeightSQL + ", " +
                    stringIWantToSQL  + ", " +
                    stringWeeklyGoalSQL + ", " +
                    goalDateSQL  + ", " +
                    stringActivityLevelSQL + ", " +
                    goalEnergyBMRSQL + ", " +
                    energyDietSQL + ", " +
                    energyWithActivity + ", " +
                    goalEnergyBMISQL + ", " +
                    energyWithActivityAndDietSQL;


            db.insert("goal", inpFields, inpValues);


            // Update table
            updateNumberTable();

            // Give feedback
            Toast.makeText(getActivity(), "Changes saved", Toast.LENGTH_SHORT).show();


            // Move user back to correct design
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            //fragmentManager.beginTransaction().replace(R.id.flContent, new GoalFragment(), GoalFragment.class.getName()).commit();


        } // error == 0

    } // editGoalSubmitOnClick


    /* getAge -------------------------------------------------------------- */
    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }


    /* Update table ------------------------------------------------------- */
    private void updateNumberTable(){
        DBAdapter db = new DBAdapter(getActivity());
        db.open();
        // Get Goal data
        String fieldsGoal[] = new String[] {
                "goal_energy_bmr",
                "goal_energy_diet",
                "goal_energy_with_activity",
                "goal_energy_with_activity_and_diet"

        };
        Cursor goalCursor = db.select("goal", fieldsGoal, "", "", "_id", "DESC");


        // Ready as variables
        String goalEnergyBmr = goalCursor.getString(0);
        String goalEnergyDiet = goalCursor.getString(1);
        String goalEnergyWithActivity = goalCursor.getString(2);
        String goalEnergyWithActivityAndDiet = goalCursor.getString(3);




        /* Numbers */

        // 1 Diet
        TextView textViewGoalEnergyDiet = (TextView)getActivity().findViewById(R.id.textViewGoalEnergyDiet);
        textViewGoalEnergyDiet.setText(goalEnergyDiet);

        // 2 WithActivityAndDiet
        TextView textViewGoalEnergyWithActivityAndDiet = (TextView)getActivity().findViewById(R.id.textViewGoalEnergyWithActivityAndDiet);
        textViewGoalEnergyWithActivityAndDiet.setText(goalEnergyWithActivityAndDiet);

        // 3 BMR
        TextView textViewGoalEnergyBMR = (TextView)getActivity().findViewById(R.id.textViewGoalEnergyBMR);
        textViewGoalEnergyBMR.setText(goalEnergyBmr);


        // 4 WithActivity
        TextView textViewGoalEnergyWithActivity = (TextView)getActivity().findViewById(R.id.textViewGoalEnergyWithActivity);
        textViewGoalEnergyWithActivity.setText(goalEnergyWithActivity);

        db.close();
    } // updateTable



    /*- Fragment  methods -*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
