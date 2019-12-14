package com.example.foodforyou;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecE.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecE#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecE extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Cursor listCursor;


    public RecE() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecE.
     */
    // TODO: Rename and change types and number of parameters
    public static RecE newInstance(String param1, String param2) {
        RecE fragment = new RecE();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rec, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RecE.OnFragmentInteractionListener) {
            mListener = (RecE.OnFragmentInteractionListener) context;
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
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((Recommendation) getActivity()).getSupportActionBar().setTitle("Food Recommendation");

        populateListFood();

        setHasOptionsMenu(true);

//        /* Get data from fragment */
//        Bundle bundle = this.getArguments();
//        if(bundle != null){
//            currentId = bundle.getString("currentFoodId");
//
//            // Need to run to get edit and delete buttons: onCreateOptionsMenu();
//        }
//        if(currentId.equals("")) {
//            // Populate the list of categories
//            populateListFood();
//        }
//        else{
//            preListItemClickedReadyCursor();
//        }
    } // onActivityCreated

    /*- populate List -------------------------------------------------------------- */
    public void populateListFood(){

        /* Database */
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        // Get categories
        String fields[] = new String[] {
                "_id",
                "food_name",
                "food_manufactor_name",
                "food_description",
                "food_serving_size_gram",
                "food_serving_size_gram_mesurment",
                "food_serving_size_pcs",
                "food_serving_size_pcs_mesurment",
                "food_energy_calculated"
        };
        try{
            listCursor = db.select("food", fields, "food_energy_calculated", "900","food_energy_calculated","1000000", "food_name", "ASC");
        }
        catch (SQLException sqle){
            Toast.makeText(getActivity(), sqle.toString(), Toast.LENGTH_LONG).show();
        }


        // Find ListView to populate
        ListView lvItems = (ListView)getActivity().findViewById(R.id.listViewFoodA);


        // Setup cursor adapter using cursor from last step
        FoodCursorAdapter continentsAdapter = new FoodCursorAdapter(getActivity(), listCursor);

        // Attach cursor adapter to the ListView
        try{
            lvItems.setAdapter(continentsAdapter); // uses ContinensCursorAdapter
        }
        catch (Exception e){
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }


//        // OnClick
//        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                listItemClicked(arg2);
//            }
//        });

        // Close db
        db.close();

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
