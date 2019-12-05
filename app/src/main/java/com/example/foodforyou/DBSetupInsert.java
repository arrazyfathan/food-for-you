package com.example.foodforyou;

import android.content.Context;
import android.database.sqlite.SQLiteException;

public class DBSetupInsert {

    //Variable -------------------------------------------------------------------
    private final Context context;

    // Public Class ------------------------------------------------------------------------
    public DBSetupInsert(Context ctx){
        this.context = ctx;
    }

    // Setup insert to food ----------------------------------------------------------------------



    // Setup insert to categories --------------------------------------------------------------------
    public void setupInsertToCategories(String values){
        try{
            DBAdapter db = new DBAdapter(context);
            db.open();
            db.insert("categories",
                    "_id, category_name, category_parent_id, category_note",
                    values);
            db.close();
        }
        catch (SQLiteException e){
            // Toast.makeText(context, "Error; Could not insert categories.", Toast.LENGTH_SHORT).show();
        }
    }

    //Insert all categories into categories table
    public void insertAllCategories(){
        setupInsertToCategories("NULL, 'Bread', '0', NULL");
        setupInsertToCategories("NULL, 'Bread', '1', NULL");
        setupInsertToCategories("NULL, 'Cereals', '1', NULL");
        setupInsertToCategories("NULL, 'Frozen bread and rolls', '1', NULL");
        setupInsertToCategories("NULL, 'Crispbread', '1', NULL");

        // Parent id: 6
        setupInsertToCategories("NULL, 'Dessert and baking', '0', NULL");
        setupInsertToCategories("NULL, 'Baking', '6', NULL");
        setupInsertToCategories("NULL, 'Biscuit', '6', NULL");


        setupInsertToCategories("NULL, 'Drinks', '0', NULL");
        setupInsertToCategories("NULL, 'Soda', '9', NULL");


        setupInsertToCategories("NULL, 'Fruit and vegetables', '0', NULL");
        setupInsertToCategories("NULL, 'Frozen fruits and vegetables', '11', NULL");
        setupInsertToCategories("NULL, 'Fruit', '11', NULL");
        setupInsertToCategories("NULL, 'Vegetables', '11', NULL");
        setupInsertToCategories("NULL, 'Canned fruits and vegetables', '11', NULL");


        setupInsertToCategories("NULL, 'Health', '0', NULL");
        setupInsertToCategories("NULL, 'Meal substitutes', '16', NULL");
        setupInsertToCategories("NULL, 'Protein bars', '16' NULL");
        setupInsertToCategories("NULL, 'Protein powder', '16', NULL");


        setupInsertToCategories("NULL, 'Meat, chicken and fish', '0', NULL");
        setupInsertToCategories("NULL, 'Meat', '20', NULL");
        setupInsertToCategories("NULL, 'Chicken', '20', NULL");
        setupInsertToCategories("NULL, 'Seafood', '20', NULL");


        setupInsertToCategories("NULL, 'Dairy and eggs', '0', NULL");
        setupInsertToCategories("NULL, 'Eggs', '24', NULL");
        setupInsertToCategories("NULL, 'Cream and sour cream', '24', NULL");
        setupInsertToCategories("NULL, 'Yogurt', '24', NULL");


        setupInsertToCategories("NULL, 'Dinner', '0',NULL");
        setupInsertToCategories("NULL, 'Ready dinner dishes', '28', NULL");
        setupInsertToCategories("NULL, 'Pizza', '28', NULL");
        setupInsertToCategories("NULL, 'Noodle', '28', NULL");
        setupInsertToCategories("NULL, 'Pasta', '28', NULL");
        setupInsertToCategories("NULL, 'Rice', '28', NULL");
        setupInsertToCategories("NULL, 'Taco', '28', NULL");


        setupInsertToCategories("NULL, 'Cheese', '0', NULL");
        setupInsertToCategories("NULL, 'Cream cheese', '35', NULL");


        setupInsertToCategories("NULL, 'On bread', '0', NULL");
        setupInsertToCategories("NULL, 'Cold meats', '37', NULL");
        setupInsertToCategories("NULL, 'Sweet spreads', '37', NULL");
        setupInsertToCategories("NULL, 'Jam', '37', NULL");


        setupInsertToCategories("NULL, 'Snacks', '0', NULL");
        setupInsertToCategories("NULL, 'Nuts', '41', NULL");
        setupInsertToCategories("NULL, 'Potato chips', '41', NULL");

    }



    public void setupInsertToFood(String values) {
        try {
            DBAdapter db = new DBAdapter(context);
            db.open();
            db.insert("food",
                    "_id, food_name, food_serving_size, food_serving_mesurment, food_serving_name_number, food_energy_calculated, food_proteins_calculated, food_carbohydrates_calculated, food_fat_calculated, food_user_id, food_category_id, food_notes",
                    values);

            db.close();

        }catch (SQLiteException e){
        }
    }

    //Insert all food into food table
    public void insertAllFood(){
        setupInsertToFood("NULL, 'Ayam Goreng', '100', 'gram', '1', '260', '21.93', '10.76', '14.55', 'NULL', 'NULL', 'NULL'");
        setupInsertToFood("NULL, 'Ayam Godog', '100', 'gram', '1', '260', '21.93', '10.76', '14.55', 'NULL', 'NULL', 'NULL'");

    }


}
