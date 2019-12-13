package com.example.foodforyou;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class CategoriesFragment extends Fragment {


    /*- 01 Class Variables -------------------------------------------------------------- */
    private View mainView;
    private Cursor listCursorCategory;
    private Cursor listCursorFood;

    // Action buttons on toolbar
    private MenuItem menuItemEdit;
    private MenuItem menuItemDelete;

    // Holder for buttons on toolbar
    private String currentCategoryId;
    private String currentCategoryName;

    private String currentFoodId;
    private String currentFoodName;

    /*- 02 Fragment Variables ----------------------------------------------------------- */
    // Nessesary for making fragment run
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CategoriesFragment() {

    }

    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /*- on Activity Created --------------------------------------------------------- */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        /* Set title */
        ((FragmentActivity) getActivity()).setActionBarTitle("Categories");


        populateList("0", ""); // Parent

        setHasOptionsMenu(true);

        FloatingActionButton fabAdd = (FloatingActionButton) getActivity().findViewById(R.id.addCategory);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcategory();
            }
        });


    } // onActivityCreated

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_categories, container, false);
        return mainView;
    }

    private void setMainView(int id) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainView = inflater.inflate(id, null);
        ViewGroup rootView = (ViewGroup) getView();
        rootView.removeAllViews();
        rootView.addView(mainView);
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // Inflate menu
        getActivity().getMenuInflater().inflate(R.menu.menu_categories_item, menu);

        // Assign menu items to variables
        menuItemEdit = menu.findItem(R.id.action_edit);
        menuItemDelete = menu.findItem(R.id.action_del);

        // Hide as default
        menuItemEdit.setVisible(false);
        menuItemDelete.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        int id = menuItem.getItemId();
        if (id == R.id.action_edit) {
            editCategory();
            setHasOptionsMenu(false);

        } else if (id == R.id.action_del) {
            deleteCategory();
            setHasOptionsMenu(false);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /*- populate List -------------------------------------------------------------- */
    public void populateList(String parentID, String parentName){
        /* Database */
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        // Get categories
        String fields[] = new String[] {
                "_id",
                "category_name",
                "category_parent_id"
        };
        listCursorCategory = db.select("categories", fields, "category_parent_id", parentID, "category_name", "ASC");

        // Createa a array
        ArrayList<String> values = new ArrayList<String>();

        // Convert categories to string
        int categoriesCount = listCursorCategory.getCount();
        for(int x=0;x<categoriesCount;x++){
            values.add(listCursorCategory.getString(listCursorCategory.getColumnIndex("category_name")));

            /* Toast.makeText(getActivity(),
                    "Id: " + categoriesCursor.getString(0) + "\n" +
                            "Name: " + categoriesCursor.getString(1), Toast.LENGTH_SHORT).show();*/
            listCursorCategory.moveToNext();
        }


        // Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);

        // Set Adapter
        ListView lv = (ListView)getActivity().findViewById(R.id.listViewCategories);
        lv.setAdapter(adapter);

        // OnClick
        if(parentID.equals("0")) {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    listItemClicked(arg2);
                }
            });
        }

        // Close db
        db.close();
        if (parentID.equals("0")) {

        } else {
            menuItemEdit.setVisible(true);
            menuItemDelete.setVisible(true);

        }
    } // populateList

    /*- List item clicked ------------------------------------------------------------ */
    public void listItemClicked(int listItemIDClicked){

        // Move cursor to ID clicked
        listCursorCategory.moveToPosition(listItemIDClicked);

        // Get ID and name from cursor
        // Set current name and id
        currentCategoryId = listCursorCategory.getString(0);
        currentCategoryName = listCursorCategory.getString(1);
        String parentID = listCursorCategory.getString(2);

        // Change title
        ((FragmentActivity) getActivity()).getSupportActionBar().setTitle(currentCategoryName);

        // Move to sub class
        populateList(currentCategoryId, currentCategoryName);

        // Show food in category
        showFoodInCategory(currentCategoryId, currentCategoryName, parentID);


    }

    public void addcategory() {
        /* Change layout */
        int id = R.layout.fragment_categories_add_edit;
        setMainView(id);

        /* Database */
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        /* Fill spinner with categories */
        String fields[] = new String[]{
                "_id",
                "category_name",
                "category_parent_id"
        };
        Cursor dbCursor = db.select("categories", fields, "category_parent_id", "0", "category_name", "ASC");

        // Creating array
        int dbCursorCount = dbCursor.getCount();
        String[] arraySpinnerCategories = new String[dbCursorCount + 1];

        // This is parent
        arraySpinnerCategories[0] = "-";

        // Convert Cursor to String
        for (int x = 1; x < dbCursorCount + 1; x++) {
            arraySpinnerCategories[x] = dbCursor.getString(1).toString();
            dbCursor.moveToNext();
        }

        // Populate spinner
        Spinner spinnerParent = getActivity().findViewById(R.id.spinnerCategoryParent);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinnerCategories);
        spinnerParent.setAdapter(adapter);



        /* SubmitButton listener */
        Button buttonHome = getActivity().findViewById(R.id.buttonCategoriesSubmit);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewCategorySubmitOnClick();
            }
        });

        /* Close db */
        db.close();
    }

    /*- Create new category Submit on click ----------------------------------------------- */
    public void createNewCategorySubmitOnClick() {
        /* Database */
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        // Error?
        int error = 0;

        // Name
        TextInputEditText editTextName = getActivity().findViewById(R.id.editTextName);
        String stringName = editTextName.getText().toString();
        if (stringName.equals("")) {
            Toast.makeText(getActivity(), "Please fill in a name.", Toast.LENGTH_SHORT).show();
            error = 1;
        }

        // Parent
        Spinner spinner = (Spinner)getActivity().findViewById(R.id.spinnerCategoryParent);
        String stringSpinnerCategoryParent = spinner.getSelectedItem().toString();
        String parentID;
        if(stringSpinnerCategoryParent.equals("-")){
            parentID = "0";
        }
        else{
            // Find we want to find parent ID from the text
            String stringSpinnerCategoryParentSQL = db.quoteSmart(stringSpinnerCategoryParent);
            String fields[] = new String[] {
                    "_id",
                    "category_name",
                    "category_parent_id"
            };
            Cursor findParentID = db.select("categories", fields, "category_name", stringSpinnerCategoryParentSQL);
            parentID = findParentID.getString(0).toString();


        }

        if(error == 0){
            // Ready variables
            String stringNameSQL = db.quoteSmart(stringName);
            String parentIDSQL = db.quoteSmart(parentID);

            // Insert into database
            String input = "NULL, " + stringNameSQL + ", " + parentIDSQL;
            db.insert("categories", "_id, category_name, category_parent_id", input);

            // Give feedback
            Toast.makeText(getActivity(), "Category created", Toast.LENGTH_LONG).show();

            // Move user back to correct design
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_frame, new CategoriesFragment(), CategoriesFragment.class.getName()).commit();

        }

        db.close();
    }

    public void editCategory() {

        /* Change layout */
        int id = R.layout.fragment_categories_add_edit;
        setMainView(id);

        // Database
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        /* Ask for parent ID */
        Cursor c;
        String fieldsC[] = new String[] { "category_parent_id" };
        String currentIdSQL = db.quoteSmart(currentCategoryId);
        c = db.select("categories", fieldsC, "_id", currentIdSQL);
        String currentParentID = c.getString(0);
        int intCurrentParentID = 0;
        try {
            intCurrentParentID = Integer.parseInt(currentParentID);
        }
        catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }

        TextInputEditText editTextName = getActivity().findViewById(R.id.editTextName);
        editTextName.setText(currentCategoryName);

        /* Fill spinner with categories */
        String fields[] = new String[] {
                "_id",
                "category_name",
                "category_parent_id"
        };
        Cursor dbCursor = db.select("categories", fields, "category_parent_id", "0", "category_name", "ASC");

        // Creating array
        int dbCursorCount = dbCursor.getCount();
        String[] arraySpinnerCategories = new String[dbCursorCount+1];

        // This is parent
        arraySpinnerCategories[0] = "-";

        // Convert Cursor to String
        int correctParentID = 0;
        for(int x=1;x<dbCursorCount+1;x++){
            arraySpinnerCategories[x] = dbCursor.getString(1).toString();

            if(dbCursor.getString(0).toString().equals(currentParentID)){
                correctParentID = x;
            }

            // Move to next
            dbCursor.moveToNext();
        }

        // Populate spinner
        Spinner spinnerParent = (Spinner) getActivity().findViewById(R.id.spinnerCategoryParent);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinnerCategories);
        spinnerParent.setAdapter(adapter);

        // Select corrent spinner item, that is the parent to currentID
        spinnerParent.setSelection(correctParentID);

        /* Close db */
        db.close();


        /* SubmitButton listener */
        Button buttonHome = getActivity().findViewById(R.id.buttonCategoriesSubmit);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCategorySubmitOnClick();
            }
        });

    }


    /*- Edit category submit on click -------------------------------------------- */
    public void editCategorySubmitOnClick() {
        /* Database */
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        // Error?
        int error = 0;

        // Name
        TextInputEditText editTextName = (TextInputEditText) getActivity().findViewById(R.id.editTextName);
        String stringName = editTextName.getText().toString();
        if (stringName.equals("")) {
            Toast.makeText(getActivity(), "Please fill in a name.", Toast.LENGTH_SHORT).show();
            error = 1;
        }

        // Parent
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinnerCategoryParent);
        String stringSpinnerCategoryParent = spinner.getSelectedItem().toString();
        String parentID;
        if (stringSpinnerCategoryParent.equals("-")) {
            parentID = "0";
        } else {
            // Find we want to find parent ID from the text
            String stringSpinnerCategoryParentSQL = db.quoteSmart(stringSpinnerCategoryParent);
            String fields[] = new String[]{
                    "_id",
                    "category_name",
                    "category_parent_id"
            };
            Cursor findParentID = db.select("categories", fields, "category_name", stringSpinnerCategoryParentSQL);
            parentID = findParentID.getString(0).toString();


        }

        if(error == 0){
            // Current ID to long
            long longCurrentID = Long.parseLong(currentCategoryId);

            // Ready variables
            long currentIDSQL = db.quoteSmart(longCurrentID);
            String stringNameSQL = db.quoteSmart(stringName);
            String parentIDSQL = db.quoteSmart(parentID);

            // Insert into database
            String input = "NULL, " + stringNameSQL + ", " + parentIDSQL;
            // db.insert("categories", "_id, category_name, category_parent_id", input);
            db.update("categories", "_id", currentIDSQL, "category_name", stringNameSQL);
            db.update("categories", "_id", currentIDSQL, "category_parent_id", parentIDSQL);

            // Give feedback
            Toast.makeText(getActivity(), "Changes saved", Toast.LENGTH_LONG).show();

            // Move user back to correct design
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_frame, new CategoriesFragment(), CategoriesFragment.class.getName()).commit();

        }
        /* Close db */
        db.close();
    } // editCategorySubmitOnClick


    /*- Delete category ----------------------------------------------------------- */
    public void deleteCategory(){

        /* Change layout */
        int id = R.layout.fragment_categories_delete;
        setMainView(id);

        /* SubmitButton listener */
        Button buttonCancel = getActivity().findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCategoryCancelOnClick();
            }
        });

        Button buttonConfirmDelete = getActivity().findViewById(R.id.buttonConfirmDelete);
        buttonConfirmDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCategoryConfirmOnClick();
            }
        });
    }

    public void deleteCategoryCancelOnClick(){
        // Move user back to correct design
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame, new CategoriesFragment(), CategoriesFragment.class.getName()).commit();

    }

    public void deleteCategoryConfirmOnClick(){
        // Delete from SQL

        /* Database */
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        // Current ID to long
        long longCurrentID = 0;
        try {
            longCurrentID = Long.parseLong(currentCategoryId);
        }
        catch (NumberFormatException e){
            Toast.makeText(getActivity(), "Error: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        // Ready variables
        long currentIDSQL = db.quoteSmart(longCurrentID);

        // Delete
        db.delete("categories", "_id", currentIDSQL);

        // Close db
        db.close();

        // Give message
        Toast.makeText(getActivity(), "Category deleted", Toast.LENGTH_LONG).show();

        // Move user back to correct design
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame, new CategoriesFragment(), CategoriesFragment.class.getName()).commit();

    }

    /*- Show food in category ----------------------------------------------------------------- */
    public void showFoodInCategory(String categoryId, String categoryName, String categoryParentID){
        if(!(categoryParentID.equals("0"))) {

            /* Change layout */
            int id = R.layout.fragment_food;
            setMainView(id);

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
            listCursorFood = db.select("food", fields, "food_category_id", categoryId, "food_name", "ASC");

            // Find ListView to populate
            ListView lvItemsFood = getActivity().findViewById(R.id.listViewFood);

            // Setup cursor adapter using cursor from last step
            FoodCursorAdapter continentsAdapter = new FoodCursorAdapter(getActivity(), listCursorFood);

            // Attach cursor adapter to the ListView
            lvItemsFood.setAdapter(continentsAdapter);// uses ContinensCursorAdapter


            // OnClick
            lvItemsFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    foodListItemClicked(arg2);
                }
            });


            // Close db
            db.close();

        } //categoryParentID.equals
    } // showFoodInCategory


    /*- Food list item clicked ------------------------------------------------------------ */
    private void foodListItemClicked(int intFoodListItemIndex){
        // We should use
        currentFoodId = listCursorFood.getString(0);
        currentFoodName = listCursorFood.getString(1);
        // Toast.makeText(getActivity(), "currentFoodID: " + currentFoodId + "\ncurrentFoodName: " + currentFoodName, Toast.LENGTH_LONG).show();


        /* Change fragment to FoodView */

        /* Change fragment to FoodView */

        /* Inialize fragmet */
        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = FoodFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Send variable
        Bundle bundle = new Bundle();
        bundle.putString("currentFoodId", ""+currentFoodId); // Put anything what you want
        fragment.setArguments(bundle);

        // Need to pass meal number
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
        void onFragmentInteraction(Uri uri);
    }
}
