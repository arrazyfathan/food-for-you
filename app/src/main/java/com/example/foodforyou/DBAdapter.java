package com.example.foodforyou;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    //* 01 Variables ---------------------------------------- */
    private static final String databaseName = "foodforyou";
    private static final int databaseVersion = 1;

    /* 02 Database variables ------------------------------- */
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    /* 03 Class DbAdapter ---------------------------------- */
    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    /* 04 DatabaseHelper ------------------------------------ */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, databaseName, null, databaseVersion);
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS goal (" +
                        " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " goal_id INT," +
                        " goal_current_weight INT," +
                        " goal_target_weight INT," +
                        " goal_i_want_to INT," +
                        " goal_weekly_goal VARCHAR," +
                        " goal_activity_level INT, " +
                        " goal_date DATE," +
                        " goal_energy_bmr INT," +
                        " goal_energy_diet INT," +
                        " goal_energy_with_activity INT," +
                        " goal_energy_with_activity_and_diet INT, " +
                        " goal_bmi INT, " +
                        " goal_notes VARCHAR);");

            } catch (SQLException e) {
                e.printStackTrace();
            }


            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS users(" +
                        " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " user_id INTEGER," +
                        " user_email VARCHAR," +
                        " user_passsword VARCHAR," +
                        " user_salt VARCHAR," +
                        " user_dob DATE," +
                        " user_gender INT," +
                        " user_height INT," +
                        " user_last_seen TIME," +
                        " user_note VARCHAR);");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS food_diary_cal_eaten(" +
                        " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " cal_eaten_id INTEGER , " +
                        " cal_eaten_date DATE," +
                        " cal_eaten_meal_no INT," +
                        " cal_eaten_energy INT," +
                        " cal_eaten_proteins INT," +
                        " cal_eaten_carbs INT," +
                        " cal_eaten_fat INT);");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS food_diary( " +
                        " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " fd_id INTEGER , " +
                        " fd_date DATE, " +
                        " fs_meal_number INT, " +
                        " fd_food_id INT, " +
                        " fd_serving_size DOUBLE," +
                        " fd_serving_mesurment VARCHAR, " +
                        " fd_energy_calculated DOUBLE," +
                        " fd_protein_caculated DOUBLE," +
                        " fd_carbohydrates_calculated DOUBLE, " +
                        " fd_fat_calculated INT, " +
                        " fd_fat_meal_id INT);");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS categories (" +
                        " _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " category_id INTEGER," +
                        " category_name VARCHAR," +
                        " category_parent_id INT," +
                        " category_icon VARCHAR," +
                        " category_note VARCHAR);");

            } catch (SQLException e) {
                e.printStackTrace();
            }


            try {

                db.execSQL("CREATE TABLE IF NOT EXISTS food (" +
                        " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " food_id INTEGER , " +
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

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS goal");
            db.execSQL("DROP TABLE IF EXISTS users");
            db.execSQL("DROP TABLE IF EXISTS food_diary_cal_eaten");
            db.execSQL("DROP TABLE IF EXISTS food_diary");
            db.execSQL("DROP TABLE IF EXISTS categories");
            db.execSQL("DROP TABLE IF EXISTS food");
            onCreate(db);

            String TAG = "tag";
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        }
    }


    /* 05 Open database --------------------------------------------------------- */
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    /* 06 Close database --------------------------------------------------------- */
    public void close() {
        DBHelper.close();
    }

    /* 07 Quote smart ------------------------------------------------------------ */
    public String quoteSmart(String value){
        // Is numeric?
        boolean isNumeric = false;
        try {
            double myDouble = Double.parseDouble(value);
            isNumeric = true;
        }
        catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        if(isNumeric == false){
            // Escapes special characters in a string for use in an SQL statement
            if (value != null && value.length() > 0) {
                value = value.replace("\\", "\\\\");
                value = value.replace("'", "\\'");
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
    public double quoteSmart(double value) {
        return value;
    }
    public int quoteSmart(int value) {
        return value;
    }

    //Insert Data
    public void insert(String table, String fields, String values){

        try {
            db.execSQL("INSERT INTO " + table +  "(" + fields + ") VALUES (" + values + ")");
        }
        catch(SQLiteException e){
            System.out.println("Insert error: " + e.toString());
        }
    }

    //Count data in table
    public int count(String table)
    {
        try {
            Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM " + table + "", null);
            mCount.moveToFirst();
            int count = mCount.getInt(0);
            mCount.close();
            return count;
        }
        catch(SQLiteException e){
            return -1;
        }

    }

    //Select
//    public Cursor selectPrimaryKey(String table, String primaryKey, long rowId, String[] fields) throws SQLException {
//
//
//        Cursor mCursor = db.query(table, fields, primaryKey + "=" + rowId, null, null, null, null, null);
//        if (mCursor != null) {
//            mCursor.moveToFirst();
//
//        }
//        return mCursor;
//    }
    public Cursor select(String table, String[] fields) throws SQLException
    {
        Cursor mCursor = db.query(table, fields, null, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    // Select All where (String)
    public Cursor select(String table, String[] fields, String whereClause, String whereCondition) throws SQLException
    {
        Cursor mCursor = db.query(table, fields, whereClause + "=" + whereCondition, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    // Select All where (Long)
    public Cursor select(String table, String[] fields, String whereClause, long whereCondition) throws SQLException {
        Cursor mCursor = db.query(table, fields, whereClause + "=" + whereCondition, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor select(String table, String[] fields, String whereClause, String whereCondition, String orderBy, String OrderMethod) throws SQLException
    {
        Cursor mCursor = null;
        if(whereClause.equals("")) {
            // We dont want to se where
            mCursor = db.query(table, fields, null, null, null, null, orderBy + " " + OrderMethod, null);
        }
        else {
            mCursor = db.query(table, fields, whereClause + "=" + whereCondition, null, null, null, orderBy + " " + OrderMethod, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //Update
    public boolean update(String table, String primaryKey, long rowId, String field, String value) {
        value = value.substring(1, value.length()-1); // removes ' after running quote smart
        ContentValues args = new ContentValues();
        args.put(field, value);
        return db.update(table, args, primaryKey + "=" + rowId, null) > 0;
    }

    public boolean update(String table, String primaryKey, long rowId, String field, double value) {
        ContentValues args = new ContentValues();
        args.put(field, value);
        return db.update(table, args, primaryKey + "=" + rowId, null) > 0;
    }

    public boolean update(String table, String primaryKey, long rowId, String field, int value) {
        ContentValues args = new ContentValues();
        args.put(field, value);
        return db.update(table, args, primaryKey + "=" + rowId, null) > 0;
    }

}
