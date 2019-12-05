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

import javax.annotation.Nullable;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity()).setActionBarTitle("Profile");
        getUser();
        getGoal();
    }

//    membuat toolbar
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.setting_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
@Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_logout){
            Toast.makeText(getActivity(), "Logout", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    // Mengambil data user
    public void getUser(){
        /* Database */
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        String fieldsUser[] = new String[]{
                "user_email",
                "user_dob",
                "user_gender"
        };
        Cursor cursorUser = db.select("users", fieldsUser);
        cursorUser.moveToLast();
        String email = cursorUser.getString(0);
        String date = cursorUser.getString(1);
        String gender = cursorUser.getString(2);

        // TextView email
        TextView textViewEmail = (TextView) getActivity().findViewById(R.id.user_email);
        textViewEmail.setText(email);

        //TextView ttl
        TextView textViewDate = (TextView) getActivity().findViewById(R.id.user_dob);
        textViewDate.setText(date);

        //TextView gender
        TextView textViewGender = (TextView) getActivity().findViewById(R.id.user_gender);
        textViewGender.setText(gender);

//        if (gender == "male") {
//        ImageView genderImage = (ImageView) getActivity().findViewById(R.id.gender_icon);
//        // Drawable new_image = getResources().getDrawable(R.drawable.ic_action_email);
//        genderImage.setImageResource(R.drawable.ic_action_email);
//        }

        db.close();
    }

    public void getGoal(){
        /* Database */
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        String fieldsGoal[] = new String[]{
                "_id",
                "goal_energy_with_activity_and_diet",
                "goal_bmi",
                "goal_target_weight"
        };
        Cursor cursorGoal = db.select("goal", fieldsGoal);
        cursorGoal.moveToLast();
        String stringGoalEnergyWithActivityAndDiet = cursorGoal.getString(1);
        String stringBmi = cursorGoal.getString(2);
        String stringTarget = cursorGoal.getString(3);

        // TextView goal
        TextView textViewBodyGoalWithActivity = (TextView) getActivity().findViewById(R.id.user_kalori);
        textViewBodyGoalWithActivity.setText(stringGoalEnergyWithActivityAndDiet);

        //TextView bmi
        TextView textViewBmi = (TextView) getActivity().findViewById(R.id.user_bmi);
        textViewBmi.setText(stringBmi);

        //TextView bmi
        TextView textViewTarget = (TextView) getActivity().findViewById(R.id.user_target);
        textViewTarget.setText("Your target weight is " + stringTarget + " kg" );

        db.close();


    }
}
