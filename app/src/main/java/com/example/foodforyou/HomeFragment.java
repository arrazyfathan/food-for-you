package com.example.foodforyou;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    /*- 01 Class Variables -------------------------------------------------------------- */
    private View mainView;
    private Cursor listCursor;


    // Holder for buttons on toolbar
    private String currentId;
    private String currentName;

    private String currentDateYear = "";
    private String currentDateMonth = "";
    private String currentDateDay = "";


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public HomeFragment() {

    }

    /*- 04 Creating Fragment ------------------------------------------------------------- */
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //OnActivity Created
    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        ((MainActivity) getActivity()).setActionBarTitle("Home");
        initalizeHome();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_home, container, false);
        return mainView;
    }

    private void setMainView(int id) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainView = inflater.inflate(id, null);
        ViewGroup rootView = (ViewGroup) getView();
        rootView.removeAllViews();
        rootView.addView(mainView);
    }

    private void initalizeHome() {
        //Find Date
        if(currentDateYear.equals("") || currentDateMonth.equals("") || currentDateDay.equals("")) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Year
            currentDateYear = "" + year;

            // Month
            month = month+1; // Month starts with 0
            if(month < 10){
                currentDateMonth = "0" + month;
            }
            else{
                currentDateMonth = "" + month;
            }
            // Day
            if(day < 10){
                currentDateDay = "0" + day;
            }
            else{
                currentDateDay = "" + day;
            }
        }
        String stringFdDate = currentDateYear + "-" + currentDateMonth + "-" + currentDateDay;

        //Fill table
        updateTable(stringFdDate, "0");



        /* Breakfast listener */
        ImageView imageViewAddBreakfast = getActivity().findViewById(R.id.imageViewAddBreakfast);
        imageViewAddBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood(0); // 0 == Breakfast
            }
        });

    }// inisialisai home

    //Update table
    private void updateTable(String stringDate, String stringMealNumber) {
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        // Select
        String fields[] = new String[] {
                "_id",
                "fd_food_id",
                "fd_serving_size_gram",
                "fd_serving_size_gram_mesurment",
                "fd_serving_size_pcs",
                "fd_serving_size_pcs_mesurment",
                "fd_energy_calculated",
                "fd_protein_calculated",
                "fd_carbohydrates_calculated",
                "fd_fat_calculated"
        };
        String stringDateSQL = db.quoteSmart(stringDate);
        Cursor cursorFd = db.select("food_diary", fields, "fd_date", stringDateSQL);

        // Select for food name
        String fieldsFood[] = new String[] {
                "_id",
                "food_name",
                "food_manufactor_name"
        };
        Cursor cursorFood;

        // Loop trough cursor
        int intCursorFdCount = cursorFd.getCount();
        for(int x=0;x<intCursorFdCount;x++){
            String stringFdId = cursorFd.getString(0);
            //Toast.makeText(getActivity(), "ID: " + stringFdId, Toast.LENGTH_SHORT).show();

            // Variables from food diary
            String fdFoodId = cursorFd.getString(1);
            String fdFoodIdSQL = db.quoteSmart(fdFoodId);

            String fdServingSizeGram = cursorFd.getString(2);
            String fdServingSizeGramMesurment = cursorFd.getString(3);
            String fdServingSizePcs = cursorFd.getString(4);
            String fdServingSizePcsMesurment = cursorFd.getString(5);
            String fdEnergyCalculated = cursorFd.getString(6);

            // Get food name
            cursorFood = db.select("food", fieldsFood, "_id", fdFoodIdSQL);

            // Variables from food
            String foodID = cursorFood.getString(0);
            String foodName = cursorFood.getString(1);
            String foodManufactorName  = cursorFood.getString(2);

            String subLine = foodManufactorName + ", " +
                    fdServingSizeGram + " " +
                    fdServingSizeGramMesurment + ", " +
                    fdServingSizePcs + " " +
                    fdServingSizePcsMesurment;


            // Add table rows
            TableLayout tl = getActivity().findViewById(R.id.tableLayoutBreakfastItems); /* Find Tablelayout defined in main.xml */
            TableRow tr1 = new TableRow(getActivity()); /* Create a new row to be added. */
            tr1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TableRow tr2 = new TableRow(getActivity()); /* Create a new row to be added. */
            tr2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            // Table row: TextView Name
            TextView textViewName = new TextView(getActivity()); // Add textview
            textViewName.setText(foodName);
            tr1.addView(textViewName);

            // Table row: TextView Energy
            TextView textViewEnergy = new TextView(getActivity()); // Add textview
            textViewEnergy.setText(fdEnergyCalculated);
            tr1.addView(textViewEnergy);

            // Table row: TextView subLine
            TextView textViewSubLine = new TextView(getActivity()); // Add textview
            textViewSubLine.setText(subLine);
            tr2.addView(textViewSubLine);

            // Add row to table
            tl.addView(tr1, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)); /* Add row to TableLayout. */
            tl.addView(tr2, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)); /* Add row to TableLayout. */


            cursorFd.moveToNext();
        }



        db.close();
    } // Update table


    //add food
    private void addFood(int mealNumber) {

        /* Inialize fragmet */
        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = AddFoodToDiaryFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Send variable
        Bundle bundle = new Bundle();
        bundle.putString("mealNumber", "" + mealNumber); // Put anything what you want
        fragment.setArguments(bundle);

        //
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit();


    } // initalizeHome

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
