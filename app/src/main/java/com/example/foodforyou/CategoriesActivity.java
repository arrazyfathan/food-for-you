package com.example.foodforyou;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    private View mainView;
    Cursor categoriesCursor;

    private MenuItem menuItemEdit;
    private MenuItem menuItemDelete;

    private String currentId;
    private String currenName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        getSupportActionBar().setTitle("Category");

        populateList("0", "");

        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.addCategory);


    }

    private void setMainView(int id){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainView = inflater.inflate(id, null);
        ViewGroup rootView = (ViewGroup) getView();
        rootView.removeAllViews();
        rootView.addView(mainView);
    }

    private Object getView() {
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_categories_item, menu);

        menuItemEdit = menu.findItem(R.id.action_edit);
        menuItemDelete = menu.findItem(R.id.action_del);

        boolean visible = false;
        menuItemEdit.setVisible(false);
        menuItemDelete.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();

        if (id == R.id.action_edit){
            editCategory();
        }

        if (id == R.id.action_del){
            deleteCategory();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(CategoriesActivity.this, MainActivity.class));
        finish();
    }


    public void populateList(String parentID, String parentName){

        /* Database */
        DBAdapter db = new DBAdapter(this);
        db.open();

        // Get categories
        String fields[] = new String[] {
                "_id",
                "category_name",
                "category_parent_id"
        };
        categoriesCursor = db.select("categories", fields, "category_parent_id", parentID, "category_name", "ASC");

        // Createa a array
        ArrayList<String> values = new ArrayList<String>();

        // Convert categories to string
        int categoriesCount = categoriesCursor.getCount();
        for(int x=0;x<categoriesCount;x++){
            values.add(categoriesCursor.getString(categoriesCursor.getColumnIndex("category_name")));


            categoriesCursor.moveToNext();
        }


        // Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);

        // Set Adapter
        ListView lv = (ListView) findViewById(R.id.listViewCategories);
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

        if (parentID.equals("0")){

        }else {
            menuItemDelete.setVisible(true);
            menuItemEdit.setVisible(true);
        }
    } // populateList

    /*- List item clicked ------------------------------------------------------------ */
    public void listItemClicked(int listItemIDClicked){

        // Move cursor to ID clicked
        categoriesCursor.moveToPosition(listItemIDClicked);

        // Get ID and name from cursor
        String id = categoriesCursor.getString(0);
        String name = categoriesCursor.getString(1);
        String parentID = categoriesCursor.getString(2);

        // Change title
        getSupportActionBar().setTitle(name);

        // Move to sub class
        populateList(id, name);

        currenName = name;
        currentId = id;

    } // listItemClicked

    public void editCategory(){
        //Toast.makeText(this, "You are edit : " + currenName + "ID" +currentId,Toast.LENGTH_SHORT).show();
        int id = R.layout.fragment_categories_add_edit;
        setMainView(id);

        // Database
        DBAdapter db = new DBAdapter(this);
        db.open();

        TextInputEditText editTextName = findViewById(R.id.editTextName);
        editTextName.setText(currenName);

        String fields[] = new String[] {
                "_id",
                "category_name",
                "category_parent_id"
        };
        Cursor dbCursor = db.select("categories",fields, "category_parent_id", "0", "category_name", "ASC");

        int dbCursorCount = dbCursor.getCount();
        String[] arraySpinnerCategories = new String[dbCursorCount+1];

        arraySpinnerCategories[0] = "-";

        for (int x=1;x<dbCursorCount+1;x++){
            arraySpinnerCategories[x] = dbCursor.getString(1).toString();
            dbCursor.moveToNext();
        }

        Spinner spinnerParent = (Spinner) findViewById(R.id.spinnerCategoryParent);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinnerCategories);
        spinnerParent.setAdapter(adapter);



    }

    public void deleteCategory(){

    }


}
