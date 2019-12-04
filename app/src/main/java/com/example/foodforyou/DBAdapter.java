package com.example.foodforyou;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    // variabel
    private static final String databaseName = "foodforyou";
    private static final int databaseVersion = 15;

    // Database variable
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    //Class DBAdapter
    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    //Database Helper
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, databaseName, null, databaseVersion);
        }

        public void onCreate(SQLiteDatabase db) {
            try {

                db.execSQL("CREATE TABLE IF NOT EXISTS users("+
                        " user_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        " user_email VARCHAR,"+
                        " user_passsword VARCHAR,"+
                        " user_salt VARCHAR,"+
                        " user_dob DATE,"+
                        " user_gender INT,"+
                        " user_locationn VARCHAR,"+
                        " user_height INT,"+
                        " user_activity_level INT,"+
                        " user_weight INT,"+
                        " user_target_weight INT,"+
                        " user_target_weight_level INT,"+
                        " user_last_seen TIME,"+
                        " user_note VARCHAR);");

                db.execSQL("CREATE TABLE IF NOT EXISTS food_diary_cal_eaten("+
                        " cal_eaten_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " cal_eaten_date DATE,"+
                        " cal_eaten_meal_no INT,"+
                        " cal_eaten_energy INT,"+
                        " cal_eaten_proteins INT,"+
                        " cal_eaten_carbs INT,"+
                        " cal_eaten_fat INT);");

                db.execSQL("CREATE TABLE IF NOT EXISTS food_diary( "+
                        " fd_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        " fd_date DATE, "+
                        " fs_meal_number INT, "+
                        " fd_food_id INT, "+
                        " fd_serving_size DOUBLE,"+
                        " fd_serving_mesurment VARCHAR, "+
                        " fd_energy_calculated DOUBLE,"+
                        " fd_protein_caculated DOUBLE,"+
                        " fd_carbohydrates_calculated DOUBLE, "+
                        " fd_fat_calculated INT, "+
                        " fd_fat_meal_id INT);");

                db.execSQL("CREATE TABLE IF NOT EXISTS categories (" +
                        " category_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        " category_name VARCHAR, "+
                        " category_parent_id INT,"+
                        " category_notes VARCHAR);");

                db.execSQL("CREATE TABLE IF NOT EXISTS food (" +
                        " food_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " food_name VARCHAR, " +
                        " food_serving_size DOUBLE, " +
                        " food_serving_mesurment VARCHAR, " +
                        " food_serving_name_number DOUBLE, " +
                        " food_serving_name_word VARCHAR, " +
                        " food_energy DOUBLE, " +
                        " food_proteins DOUBLE, " +
                        " food_carbohydrates DOUBLE, " +
                        " food_fat DOUBLE, " +
                        " food_energy_calculated DOUBLE, " +
                        " food_proteins_calculated DOUBLE, " +
                        " food_carbohydrates_calculated DOUBLE, " +
                        " food_fat_calculated DOUBLE, " +
                        " food_user_id INT, " +
                        " food_barcode DOUBLE, " +
                        " food_category_id INT, " +
                        " food_thumb VARCHAR, " +
                        " food_image_a VARCHAR, " +
                        " food_image_b VARCHAR, " +
                        " food_image_c VARCHAR, " +
                        " food_notes VARCHAR); ");

            }catch (SQLException e){
                e.printStackTrace();
            }


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

            db.execSQL("DROP TABLE IF EXISTS food_diary_cal_eaten");
            db.execSQL("DROP TABLE IF EXISTS food_diary");
            db.execSQL("DROP TABLE IF EXISTS fooddiary");
            db.execSQL("DROP TABLE IF EXISTS categories");
            onCreate(db);

            String TAG = "tag";
            Log.w(TAG,  "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data" );
        }
    }


    // Open database
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //Close Database
    public void close(){
        DBHelper.close();
    }

    //Quote Smart
    public String quoteSmart(String value){
        //is Nmeric?
        boolean isNumeric = false;
        try {
            double myDouble = Double.parseDouble(value);
            isNumeric = true;
        }
        catch (NumberFormatException nfe){
            System.out.println("Cpould not parse " + nfe);
        }
        if (isNumeric == false){
            //
            if (value != null && value.length() > 0){
                value = value.replace("\\", "\\\\");
                value = value.replace("'", "\\");
                value = value.replace("\0", "\\0");
                value = value.replace("\n", "\\n");
                value = value.replace("\r", "\\r");
                value = value.replace("\"", "\\\"");
                value = value.replace("\\x1a", "\\Z");
            }
        }
        value = "'" + value + "'";
        return value;
    }

    public double quoteSmart(double value){
        return value;
    }

    public int quoteSmart(int value){
        return value;
    }

    //Insert Data
    public void insert(String table, String fields, String values){
        db.execSQL("INSERT INTO " + table + "(" + fields +") VALUES (" + values + ")");
    }

    //Count data in table
    public int count(String table){
        Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM " + table + "", null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }


}
