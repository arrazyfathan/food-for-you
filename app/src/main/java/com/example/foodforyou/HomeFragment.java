package com.example.foodforyou;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);



    }

    //OnActivity Created
    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        initalizeHome();


    }



    //Change Actionbar tilte based selected navigation bottom bar
    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity()).setActionBarTitle("Home");
    }

    private void initalizeHome(){
        getGoal();
    }


    public void getGoal(){
        /* Database */
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        int numberRows = db.count("users");
        if (numberRows <1 ){
            Intent i = new Intent(getActivity(), SignUp.class);
            startActivity(i);
        }

        String fieldsGoal[] = new String[]{
                "_id",
                "goal_energy_with_activity_and_diet",
                "goal_bmi"
        };
        Cursor cursorGoal = db.select("goal", fieldsGoal);
        cursorGoal.moveToLast();
        String stringGoalEnergyWithActivityAndDiet = cursorGoal.getString(1);
        String stringBmi = cursorGoal.getString(2);

        // TextView goal
        TextView textViewBodyGoalWithActivity = (TextView) getActivity().findViewById(R.id.jumlah_kalori);
        textViewBodyGoalWithActivity.setText(stringGoalEnergyWithActivityAndDiet);

        //TextView bmi
        TextView textViewBmi = (TextView) getActivity().findViewById(R.id.bmi);
        textViewBmi.setText(stringBmi);


        db.close();
    }
}
