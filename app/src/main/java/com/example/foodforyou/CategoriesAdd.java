package com.example.foodforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class CategoriesAdd extends AppCompatActivity {

    Cursor dbCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_add);

        getSupportActionBar().setTitle("Add Categories");
        addCategories();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(CategoriesAdd.this, CategoriesActivity.class));
        finish();
    }

    public void addCategories(){
        DBAdapter db = new DBAdapter(this);
        db.open();

        //get categories
        String fields[] = new  String[]{
                "_id",
                "category_name",
                "category_parent_id"
        };
        dbCursor = db.select("categories", fields, "category_parent_id", "0", "category_name", "ASC");

        //convert cursor to String
        int dbCursorCount = dbCursor.getCount();
        String[] arraySpinnerCategories = new String[dbCursorCount+1];

        arraySpinnerCategories[0] = "-";

        for(int x=1;x<dbCursorCount+1;x++){
            arraySpinnerCategories[x] = dbCursor.getString(1).toString();
            dbCursor.moveToNext();
        }

        Spinner spinnerParent = (Spinner)findViewById(R.id.spinnerCategoryParent);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinnerCategories);
        spinnerParent.setAdapter(adapter);

        //Submite button
        Button buttonAdd = (Button) findViewById(R.id.buttonCategoriesSubmit);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewCategories();
            }
        });

        db.close();
    }

    public void createNewCategories(){
        DBAdapter db = new DBAdapter(this);
        db.open();

        int error = 0;

        TextInputEditText nameCategory = (TextInputEditText) findViewById(R.id.editTextName);
        String stringNameCategory = nameCategory.getText().toString();
        if (stringNameCategory.equals("")){
            Toast.makeText(this, "Please fill a name", Toast.LENGTH_LONG).show();
            error = 1;
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinnerCategoryParent);
        String stringSpinner = spinner.getSelectedItem().toString();
        String parentID;
        if (stringSpinner.equals("-")){
            parentID = "0";
        }else {
            String stringSpinnerSQL = db.quoteSmart(stringSpinner);
            String fields[] = new String[] {
                    "_id",
                    "category_name",
                    "category_parent_id"
            };
            Cursor findParentID = db.select("categories", fields, "category_name", stringSpinnerSQL);
            parentID = findParentID.getString(0).toString();
        }

        if (error == 0){
            //insert
            String stringNameSQL = db.quoteSmart(stringNameCategory);
            String parentIDSQL = db.quoteSmart(parentID);

            String input = "NULL, " + stringNameSQL + ", " + parentIDSQL;
            db.insert("categories", "_id, category_name, category_parent_id", input);

            Toast.makeText(this, "Category created", Toast.LENGTH_LONG).show();

            Intent i = new Intent(CategoriesAdd.this, CategoriesActivity.class);
            startActivity(i);


        }
        db.close();
    }
}
