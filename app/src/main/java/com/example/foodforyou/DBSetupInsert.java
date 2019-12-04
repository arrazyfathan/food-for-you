package com.example.foodforyou;

import android.content.Context;

public class DBSetupInsert {

    //Variable -------------------------------------------------------------------
    private final Context context;

    // Public Class ------------------------------------------------------------------------
    public DBSetupInsert(Context ctx){
        this.context = ctx;
    }

    // Setup insert to food ----------------------------------------------------------------------
    public void setupInsertToFood(String values) {

        DBAdapter db = new DBAdapter(context);
        db.open();
        db.insert("food",
                "food_id, food_name, food_serving_size, food_serving_mesurment, food_serving_name_number, food_energy_calculated, food_proteins_calculated, food_carbohydrates_calculated, food_fat_calculated, food_user_id, food_category_id, food_notes",
                values);

        db.close();
    }


    // Setup insert to categories --------------------------------------------------------------------
    public void setupInsertToCategories(String values){
        DBAdapter db = new DBAdapter(context);
        db.open();
        db.insert("categories",
                "category_id, category_name, category_parent_id",
                values);
    }



    //Insert all food into food table
    public void insertAllFood(){
        setupInsertToFood("NULL, 'Ayam Goreng', '100', 'gram', '1', '260', '21.93', '10.76', '14.55', 'NULL', 'NULL', 'NULL'");

    }

    //Insert all categories into categories table
    public void insertAllCaegories(){
        setupInsertToCategories("NULL,'Food', 1");
    }
}
