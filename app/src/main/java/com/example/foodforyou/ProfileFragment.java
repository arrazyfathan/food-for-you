package com.example.foodforyou;


import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.Calendar;

import javax.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    public static ImageView imageView;
    public static ImageView imageProfile;
    private String cekGender;

    private View mainView;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        imageView = rootView.findViewById(R.id.gender_icon);
        imageProfile = rootView.findViewById(R.id.profileImage);
        return rootView;

    }

    private void setMainView(int id) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainView = inflater.inflate(id, null);
        ViewGroup rootView = (ViewGroup) getView();
        rootView.removeAllViews();
        rootView.addView(mainView);
    }

    public void onResume() {
        super.onResume();

        ((FragmentActivity) getActivity()).setActionBarTitle("Profile");
        getUser();
        getGoal();
    }

    //    membuat toolbar
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.setting_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            openDialog();
        }

        if (id == R.id.action_editProfile) {
            editProfile();
            setHasOptionsMenu(false);
        }
        return super.onOptionsItemSelected(item);
    }
    public void openDialog() {
        DialogViewLogout dialogView = new DialogViewLogout();
        dialogView.show(getActivity().getSupportFragmentManager(), "Example");
    }

    // Mengambil data user
    public void getUser() {
        /* Database */
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        String fieldsUser[] = new String[]{
                "user_email",
                "user_dob",
                "user_gender",
                "user_height"

        };
        Cursor cursorUser = db.select("users", fieldsUser);
        cursorUser.moveToLast();
        String email = cursorUser.getString(0);
        String date = cursorUser.getString(1);
        String gender = cursorUser.getString(2);
        String height = cursorUser.getString(3);


        // TextView email
        TextView textViewEmail = getActivity().findViewById(R.id.user_name);
        textViewEmail.setText(email);

        TextView textViewDate = getActivity().findViewById(R.id.user_date);
        textViewDate.setText(date);

        //TextView gender
        TextView textViewGender = getActivity().findViewById(R.id.user_gender);
        textViewGender.setText(gender);


        if (gender.equals("male")) {
            ProfileFragment.imageView.setImageResource(R.drawable.ic_male);
            ProfileFragment.imageProfile.setImageResource(R.drawable.profile_male);
        } else {
            ProfileFragment.imageView.setImageResource(R.drawable.ic_female);
            ProfileFragment.imageProfile.setImageResource(R.drawable.profile_female);
        }

        //TextView Height
        TextView textViewHeight = getActivity().findViewById(R.id.user_height);
        textViewHeight.setText(height + " cm");

//        if (gender == "male") {
//        ImageView genderImage = (ImageView) getActivity().findViewById(R.id.gender_icon);
//        // Drawable new_image = getResources().getDrawable(R.drawable.ic_action_email);
//        genderImage.setImageResource(R.drawable.ic_action_email);
//        }

        // Get user age
        String[] items1 = date.split("-");
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

        //TextView ttl
        TextView textViewAge = getActivity().findViewById(R.id.user_dob);
        textViewAge.setText(stringUserAge + " Tahun");

        db.close();
    }

    public void getGoal() {
        /* Database */
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        String fieldsGoal[] = new String[]{
                "_id",
                "goal_energy_with_activity_and_diet",
                "goal_bmi",
                "goal_target_weight",
                "goal_current_weight"
        };
        Cursor cursorGoal = db.select("goal", fieldsGoal);
        cursorGoal.moveToLast();
        String stringGoalEnergyWithActivityAndDiet = cursorGoal.getString(1);
        String stringBmi = cursorGoal.getString(2);
        String stringTarget = cursorGoal.getString(3);
        String stringCurrent = cursorGoal.getString(4);

        // TextView goal
        TextView textViewBodyGoalWithActivity = getActivity().findViewById(R.id.user_kalori);
        textViewBodyGoalWithActivity.setText(stringGoalEnergyWithActivityAndDiet);

        //TextView bmi
        TextView textViewBmi = getActivity().findViewById(R.id.user_bmi);
        textViewBmi.setText(stringBmi);

        //TextView bmi
        TextView textViewTarget = getActivity().findViewById(R.id.user_target);
        textViewTarget.setText("Your target weight is " + stringTarget + " kg");

        //TextView Current Weight
        TextView textViewCurrent = getActivity().findViewById(R.id.user_current_weight);
        textViewCurrent.setText(stringCurrent + " Kg");

        db.close();


    }

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


    public void editProfile() {
        int id = R.layout.fragment_profile_edit;
        setMainView(id);

        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        long rowID = 1;
        String fields[] = new String[]{
                "_id",
                "user_email",
                "user_dob",
                "user_gender",
                "user_height"
        };

        Cursor c = db.select("users", fields, "_id", rowID);
        String stringEmail = c.getString(1);
        String stringUserDob = c.getString(2);
        String stringUserGender = c.getString(3);
        String stringUserHeight = c.getString(4);

        TextInputEditText editTexEmail = getActivity().findViewById(R.id.editTextEmail);
        editTexEmail.setText(stringEmail);


        /* DOB */
        String[] items1 = stringUserDob.split("-");
        String stringUserDobYear = items1[0];
        String stringUserDobMonth = items1[1];
        String stringUserDobYDay = items1[2];

        int spinnerDOBDaySelectedIndex = 0;

        String[] arraySpinnerDOBDay = new String[31];
        int human_counter = 0;
        for (int x = 0; x < 31; x++) {
            human_counter = x + 1;
            arraySpinnerDOBDay[x] = "" + human_counter;

            if (stringUserDobYDay.equals("0" + human_counter) || stringUserDobYDay.equals("" + human_counter)) {
                spinnerDOBDaySelectedIndex = x;
                //Toast.makeText(getActivity(), "Day: " + stringUserDobYDay + " Index: " + spinnerDOBDaySelectedIndex, Toast.LENGTH_LONG).show();
            }

        }

        Spinner spinnerDOBDay = getActivity().findViewById(R.id.spinnerEditProfileDOBDay);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinnerDOBDay);
        spinnerDOBDay.setAdapter(adapter);

        spinnerDOBDay.setSelection(spinnerDOBDaySelectedIndex);

        int intUserDobMonth = 0;
        stringUserDobYDay.replace("0", "");
        try {
            intUserDobMonth = Integer.parseInt(stringUserDobMonth);
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        intUserDobMonth = intUserDobMonth - 1;
        Spinner spinnerDOBMonth = (Spinner) getActivity().findViewById(R.id.spinnerEditProfileDOBMonth);
        spinnerDOBMonth.setSelection(intUserDobMonth); // Select index

        int spinnerDOBYearSelectedIndex = 0;

        // get current year、month and day
        String[] arraySpinnerDOBYear = new String[100];
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int end = year-100;
        int index = 0;
        for(int x=year;x>end;x--){
            arraySpinnerDOBYear[index] = "" + x;
            // Toast.makeText(this, "x = " + x, Toast.LENGTH_SHORT).show();

            if(stringUserDobYear.equals(""+x)){
                spinnerDOBYearSelectedIndex = index;
                //Toast.makeText(getActivity(), "Year: " + x + " Index: " + spinnerDOBYearSelectedIndex, Toast.LENGTH_LONG).show();
            }
            index++;
        }
        Spinner spinnerDOBYear = (Spinner)getActivity().findViewById(R.id.spinnerEditProfileDOBYear);
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinnerDOBYear);
        spinnerDOBYear.setAdapter(adapterYear);
        spinnerDOBYear.setSelection(spinnerDOBYearSelectedIndex); // Select index

        /* Gender */
        RadioButton radioButtonGenderMale = (RadioButton)getActivity().findViewById(R.id.radioButtonGenderMale);
        RadioButton radioButtonGenderFemale = (RadioButton)getActivity().findViewById(R.id.radioButtonGenderFemale);
        if(stringUserGender.startsWith("m")){
            radioButtonGenderMale.setChecked(true);
            radioButtonGenderFemale.setChecked(false);
        }
        else{
            radioButtonGenderMale.setChecked(false);
            radioButtonGenderFemale.setChecked(true);
        }

        TextInputEditText editTextHeightCm = getActivity().findViewById(R.id.editTextHeight);
        editTextHeightCm.setText(stringUserHeight);

        Button buttonEditProfileSubmit = getActivity().findViewById(R.id.buttonEditProfile);
        buttonEditProfileSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                editProfileSubmit();
            }
        });

        db.close();

    }

    public void editProfileSubmit(){
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        int error = 0;

        TextInputEditText editEmail = getActivity().findViewById(R.id.editTextEmail);
        String stringEmail = editEmail.getText().toString();
        String emailSQL = db.quoteSmart(stringEmail);

        Spinner spinnerDOBDay = getActivity().findViewById(R.id.spinnerEditProfileDOBDay);
        String stringDOBDay = spinnerDOBDay.getSelectedItem().toString();
        int intDOBDay = 0;
        try {
            intDOBDay = Integer.parseInt(stringDOBDay);

            if(intDOBDay < 10){
                stringDOBDay = "0" + stringDOBDay;
            }

        }
        catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
            error = 1;
            Toast.makeText(getActivity(), "Please select a day for your birthday.", Toast.LENGTH_SHORT).show();
        }


        Spinner spinnerDOBMonth = getActivity().findViewById(R.id.spinnerEditProfileDOBMonth);
        String stringDOBMonth = spinnerDOBMonth.getSelectedItem().toString();
        int positionDOBMonth = spinnerDOBMonth.getSelectedItemPosition();
        int month = positionDOBMonth+1;
        if(month < 10){
            stringDOBMonth = "0" + month;
        }
        else{
            stringDOBMonth = "" + month;
        }

        Spinner spinnerDOBYear = (Spinner)getActivity().findViewById(R.id.spinnerEditProfileDOBYear);
        String stringDOBYear = spinnerDOBYear.getSelectedItem().toString();
        int intDOBYear = 0;
        try {
            intDOBYear = Integer.parseInt(stringDOBYear);
        }
        catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
            error = 1;
            Toast.makeText(getActivity(), "Please select a year for your birthday.", Toast.LENGTH_SHORT).show();
        }

        String dateOfBirth = intDOBYear + "-" + stringDOBMonth + "-" + stringDOBDay;
        String dateOfBirthSQL = db.quoteSmart(dateOfBirth);

        RadioGroup radioGroupGender = (RadioGroup)getActivity().findViewById(R.id.radioGroupGender);
        int radioButtonID = radioGroupGender.getCheckedRadioButtonId(); // get selected radio button from radioGroup
        View radioButtonGender = radioGroupGender.findViewById(radioButtonID);
        int position = radioGroupGender.indexOfChild(radioButtonGender); // If you want position of Radiobutton

        String stringGender = "";
        if(position == 0){
            stringGender = "male";
        }
        else{
            stringGender = "female";
        }
        String genderSQL = db.quoteSmart(stringGender);

        TextInputEditText editTextHeightCm = getActivity().findViewById(R.id.editTextHeight);
        String stringHeightCm = editTextHeightCm.getText().toString();
        String heightCmSQL = db.quoteSmart(stringHeightCm);

        if(error == 0){

            long id = 1;

            String fields[] = new String[] {
                    "user_email",
                    "user_dob",
                    "user_gender",
                    "user_height"
            };
            String values[] = new String[] {
                    emailSQL,
                    dateOfBirthSQL,
                    genderSQL,
                    heightCmSQL
            };

            db.update("users", "_id", id, fields, values);

            Toast.makeText(getActivity(), "Changes saved", Toast.LENGTH_SHORT).show();

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_frame, new ProfileFragment(), ProfileFragment.class.getName()).commit();

        } // error == 0

        db.close();
    }


    public interface OnFragmentInteractionListener {
    }
}
