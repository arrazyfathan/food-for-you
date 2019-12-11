package com.example.foodforyou;


import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import javax.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    public static ImageView imageView;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_profile, container, false);
        imageView = rootView.findViewById(R.id.gender_icon);

        return rootView;
//        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onResume() {
        super.onResume();

        ((MainActivity) getActivity()).setActionBarTitle("Profile");
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
        return super.onOptionsItemSelected(item);
    }

    public void openDialog(){
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

        if(gender.equals("male")){
            ProfileFragment.imageView.setImageResource(R.drawable.ic_male);
        }else{
            ProfileFragment.imageView.setImageResource(R.drawable.ic_female);
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


    public interface OnFragmentInteractionListener {
    }
}
