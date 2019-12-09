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


    /* Variables */
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View mainView;
    Cursor categoriesCursor;

    private MenuItem menuItemEdit;
    private MenuItem menuItemDelete;

    private String currentId;
    private String currenName;

    private String currentCategoryId;
    private String currentCategoryName;


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

    /*- On create ----------------------------------------------------------------- */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    private void setMainView(int id) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainView = inflater.inflate(id, null);
        ViewGroup rootView = (ViewGroup) getView();
        rootView.removeAllViews();
        rootView.addView(mainView);
    }

    /*- on Activity Created --------------------------------------------------------- */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Populate the list of categories
        populateList("0", ""); // Parent
        FloatingActionButton fabAdd = (FloatingActionButton) getActivity().findViewById(R.id.addCategory);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcategory();
            }
        });
        setHasOptionsMenu(true);
        /* Set title */
        ((MainActivity) getActivity()).setActionBarTitle("Categories");

    } // onActivityCreated


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_categories, container, false);
        return mainView;
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
        } else if (id == R.id.action_del) {
            deleteCategory();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /*- populate List -------------------------------------------------------------- */
    public void populateList(String parentID, String parentName) {
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        String fields[] = new String[]{
                "_id",
                "category_name",
                "category_parent_id"
        };
        categoriesCursor = db.select("categories", fields, "category_parent_id", parentID);

        // Createa a array
        ArrayList<String> values = new ArrayList<String>();

        // Convert categories to string
        int categoriesCount = categoriesCursor.getCount();
        for (int x = 0; x < categoriesCount; x++) {
            values.add(categoriesCursor.getString(categoriesCursor.getColumnIndex("category_name")));


            categoriesCursor.moveToNext();
        }


        // Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);

        // Set Adapter
        ListView lv = (ListView) getActivity().findViewById(R.id.listViewCategories);
        lv.setAdapter(adapter);

        // OnClick
        if (parentID.equals("0")) {
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
    public void listItemClicked(int listItemIDClicked) {

        // Move cursor to ID clicked
        categoriesCursor.moveToPosition(listItemIDClicked);

        // Get ID and name from cursor
        String id = categoriesCursor.getString(0);
        String name = categoriesCursor.getString(1);
        String parentID = categoriesCursor.getString(2);

        // Change title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(name);

        // Move to sub class
        populateList(id, name);

        currenName = name;
        currentId = id;
    } // listItemClicked


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
        Spinner spinnerParent = (Spinner) getActivity().findViewById(R.id.spinnerCategoryParent);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinnerCategories);
        spinnerParent.setAdapter(adapter);



        /* SubmitButton listener */
        Button buttonHome = (Button) getActivity().findViewById(R.id.buttonCategoriesSubmit);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewCategorySubmitOnClick();
            }
        });

        /* Close db */
        db.close();
    }

    public void createNewCategorySubmitOnClick() {
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

        if (error == 0) {
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

        /* Close db */
        db.close();
    }

    public void editCategory() {
        //Toast.makeText(this, "You are edit : " + currenName + "ID" +currentId,Toast.LENGTH_SHORT).show();
        int id = R.layout.fragment_categories_add_edit;
        setMainView(id);

        // Database
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        Cursor c;
        String fieldsC[] = new String[]{"category_parent_id"};
        String currentIdSQL = db.quoteSmart(currentId);
        c = db.select("categories", fieldsC, "_id", currentIdSQL);
        String currentParentID = c.getString(0);
        int intCurrentParentID = 0;
        try {
            intCurrentParentID = Integer.parseInt(currentParentID);
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse" + nfe);
        }

        TextInputEditText editTextName = getActivity().findViewById(R.id.editTextName);
        editTextName.setText(currenName);

        String fields[] = new String[]{
                "_id",
                "category_name",
                "category_parent_id"
        };
        Cursor dbCursor = db.select("categories", fields, "category_parent_id", "0", "category_name", "ASC");

        int dbCursorCount = dbCursor.getCount();
        String[] arraySpinnerCategories = new String[dbCursorCount + 1];

        arraySpinnerCategories[0] = "-";

        int correctParentID = 0;
        for (int x = 1; x < dbCursorCount + 1; x++) {
            arraySpinnerCategories[x] = dbCursor.getString(1).toString();

            if (dbCursor.getString(0).toString().equals(currentParentID)) {
                correctParentID = x;
            }

            dbCursor.moveToNext();
        }

        Spinner spinnerParent = (Spinner) getActivity().findViewById(R.id.spinnerCategoryParent);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arraySpinnerCategories);
        spinnerParent.setAdapter(adapter);

        spinnerParent.setSelection(correctParentID);

        /* Close db */
        db.close();


        /* SubmitButton listener */
        Button buttonHome = (Button) getActivity().findViewById(R.id.buttonCategoriesSubmit);
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

        if (error == 0) {
            long longCurrentId = Long.parseLong(currentId);

            long currentIdSQL = db.quoteSmart(longCurrentId);
            String stringNameSQL = db.quoteSmart(stringName);
            String parentIdSQL = db.quoteSmart(parentID);

            String input = "NULL, +" + stringNameSQL + ", " + parentIdSQL;

            db.update("categories", "_id", currentIdSQL, "category_name", stringNameSQL);
            db.update("categories", "_id", currentIdSQL, "category_parent_id", parentIdSQL);

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
    public void deleteCategory() {

        /* Change layout */
        int id = R.layout.fragment_categories_delete;
        setMainView(id);

        /* SubmitButton listener */
        Button buttonCancel = (Button) getActivity().findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCategoryCancelOnClick();
            }
        });

        Button buttonConfirm = (Button) getActivity().findViewById(R.id.buttonConfirmDelete);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCategoryConfirmOnClick();
            }
        });


    }

    public void deleteCategoryCancelOnClick() {
        // Move user back to correct design
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame, new CategoriesFragment(), CategoriesFragment.class.getName()).commit();

    }

    public void deleteCategoryConfirmOnClick() {
        // Delete from SQL
        /* Database */
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        // Current ID to long
        long longCurrentID = Long.parseLong(currentId);

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
