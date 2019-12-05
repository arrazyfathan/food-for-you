package com.example.foodforyou;


import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {


    //Variabel
    Cursor categoriesCursor;


    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Categories");
    }

    //OnActivity Created
    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        //populate the list of categories
        populateList("0", "");
    }

    public void populateList(String parentID, String parentName) {
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        //Get Categories
        String fields[] = new String[]{
                "_id",
                "category_name",
                "category_parent_id"
        };
        categoriesCursor = db.select("categories", fields, "category_parent_id", parentID);

        //Create array
        ArrayList<String> values = new ArrayList<String>();

        //Convert categories to string
        int categoriesCount = categoriesCursor.getCount();
        for (int x = 0; x < categoriesCount; x++) {
            values.add(categoriesCursor.getString(categoriesCursor.getColumnIndex("category_name")));

            categoriesCursor.moveToNext();
        }

//        categoriesCursor.close();

        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);

        //ListView
        ListView lv = (ListView) getActivity().findViewById(R.id.listViewCategories);
        lv.setAdapter(adapter);

        //onClick
        if (parentID.equals("0")) {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    listItemClicked(arg2);
                }
            });
        }
        //close db
        db.close();
    }


    public void listItemClicked(int listItemIDClicked) {

        //Move cursor to ID clicked
        categoriesCursor.moveToPosition(listItemIDClicked);

        //Get ID and name form cursor
        String id = categoriesCursor.getString(0);
        String name = categoriesCursor.getString(1);
        String parentID = categoriesCursor.getString(2);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(name);

        //Move to sub
        populateList(id, name);
    }




    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);

    }

}
