package com.example.foodforyou;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {

    /*- 01 Class Variables -------------------------------------------------------------- */
    private View mainView;
    private Cursor listCursor;

    // Action buttons on toolbar
    private MenuItem menuItemEdit;
    private MenuItem menuItemDelete;

    // Holder for buttons on toolbar
    private String currentId;
    private String currentName;

    /*- 02 Fragment Variables ----------------------------------------------------------- */
    // Nessesary for making fragment run
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /*- 03 Constructur ------------------------------------------------------------------ */
    // Nessesary for having Fragment as class
    public FoodFragment() {
        // Required empty public constructor
    }


    /*- 04 Creating Fragment ------------------------------------------------------------- */
    public static FoodFragment newInstance(String param1, String param2) {
        FoodFragment fragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /*- 05 on Activity Created ---------------------------------------------------------- */
    // Run methods when started
    // Set toolbar menu items
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /* Set title */
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Food List");

        // Populate the list of categories
        populateListFood();

        // Create menu
        setHasOptionsMenu(true);
    } // onActivityCreated

    /*- 06 On create view ---------------------------------------------------------------- */
    // Sets main View variable to the view, so we can change views in fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_food, container, false);
        return mainView;
    }

    /*- 07 set main view ----------------------------------------------------------------- */
    // Changing view method in fragmetn
    private void setMainView(int id) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainView = inflater.inflate(id, null);
        ViewGroup rootView = (ViewGroup) getView();
        rootView.removeAllViews();
        rootView.addView(mainView);
    }

    /*- 08 on Create Options Menu -------------------------------------------------------- */
    // Creating action icon on toolbar
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // Inflate menu
        //MenuInflater menuInflater = ((MainActivity)getActivity()).getMenuInflater();
        // inflater.inflate(R.menu.menu_categories, menu);

        getActivity().getMenuInflater().inflate(R.menu.menu_food, menu);

        // Assign menu items to variables
        menuItemEdit = menu.findItem(R.id.menu_action_food_edit);
        menuItemDelete = menu.findItem(R.id.menu_action_food_delete);

        // Hide as default
        menuItemEdit.setVisible(false);
        menuItemDelete.setVisible(false);
    }

    /*- 09 on Options Item Selected ------------------------------------------------------ */
    // Action icon clicked on
    // Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        int id = menuItem.getItemId();
        if (id == R.id.menu_action_food_add) {

        }
        if (id == R.id.menu_action_food_edit) {

        }
        if (id == R.id.menu_action_food_delete) {

        }
        return super.onOptionsItemSelected(menuItem);
    }



    /*- Our own methods -*/


    /*- populate List -------------------------------------------------------------- */
    public void populateListFood() {

        /* Database */
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        //Get categories
        String fields[] = new String[]{
                "_id",
                "food_name",
                "food_manufactor_name",
                "food_description",
                "food_serving_size",
                "food_serving_mesurment",
                "food_serving_name_number",
                "food_serving_name_word",
                "food_energy_calculated"
        };
        listCursor = db.select("food", fields,"","","food_name","ASC");

        ListView lvItems = (ListView) getActivity().findViewById(R.id.listViewFood);


        FoodCursorAdapter continentsAdapter = new FoodCursorAdapter(getActivity(), listCursor);
        lvItems.setAdapter(continentsAdapter);
        // Close db
        db.close();

    }

    /*- List item clicked ------------------------------------------------------------ */
    public void listItemClicked(int listItemIDClicked) {

    }

    /*- Fragment  methods -*/


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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
