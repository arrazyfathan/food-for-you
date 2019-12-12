package com.example.foodforyou;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    /*- 01 Class Variables -------------------------------------------------------------- */
    private View mainView;
    private Cursor listCursor;

    private String currentId;
    private String currentName;

    private MenuItem menuItemAddFood;

    private String currentDateYear = "";
    private String currentDateMonth = "";
    private String currentDateDay = "";

    private String currentFoodId;
    private String currentFoodName;
    private String currentFdId;

    private boolean lockPortionSizeByPcs;
    private boolean lockPortionSizeByGram;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public HomeFragment() {

    }

    /*- 04 Creating Fragment ------------------------------------------------------------- */
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //OnActivity Created
    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        ((MainActivity) getActivity()).setActionBarTitle("Home");
        initalizeHome();

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_home, container, false);
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
        MenuInflater menuInflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);

        // Assign menu items to variables
        menuItemAddFood = menu.findItem(R.id.menu_action_add_food);

        // Hide as default
        menuItemAddFood.setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        int id = menuItem.getItemId();
        if (id == R.id.menu_action_add_food) {
            addFoodToDiarySelectMealNumber();
        }
        return super.onOptionsItemSelected(menuItem);
    }


    private void initalizeHome() {
        //Find Date
        if (currentDateYear.equals("") || currentDateMonth.equals("") || currentDateDay.equals("")) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Year
            currentDateYear = "" + year;

            // Month
            month = month + 1; // Month starts with 0
            if (month < 10) {
                currentDateMonth = "0" + month;
            } else {
                currentDateMonth = "" + month;
            }
            // Day
            if (day < 10) {
                currentDateDay = "0" + day;
            } else {
                currentDateDay = "" + day;
            }
        }
        String stringFdDate = currentDateYear + "-" + currentDateMonth + "-" + currentDateDay;

        //Fill table
        /* Fill table */
        updateTableItems(stringFdDate, "0");
        updateTableItems(stringFdDate, "1");
        updateTableItems(stringFdDate, "2");
        updateTableItems(stringFdDate, "3");
        updateTableItems(stringFdDate, "4");


        /* Breakfast listener */
        ImageView imageViewAddBreakfast = getActivity().findViewById(R.id.imageViewAddBreakfast);
        imageViewAddBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood(0); // 0 == Breakfast
            }
        });

    }// inisialisai home

    //Update table
    private void updateTableItems(String stringDate, String stringMealNumber) {
        DBAdapter db = new DBAdapter(getActivity());
        db.open();


        // Meal number SQL
        String stringMealNumberSQL = db.quoteSmart(stringMealNumber);
        String stringDateSQL = db.quoteSmart(stringDate);

        // Select
        String fdFields[] = new String[]{
                "_id",
                "fd_food_id",
                "fd_serving_size_gram",
                "fd_serving_size_gram_mesurment",
                "fd_serving_size_pcs",
                "fd_serving_size_pcs_mesurment",
                "fd_energy_calculated",
                "fd_protein_calculated",
                "fd_carbohydrates_calculated",
                "fd_fat_calculated"
        };
        String fdWhereClause[] = new String[]{
                "fd_date",
                "fd_meal_number"
        };
        String fdWhereCondition[] = new String[]{
                stringDateSQL,
                stringMealNumberSQL
        };
        String fdWhereAndOr[] = new String[]{
                "AND"
        };
        Cursor cursorFd = db.select("food_diary", fdFields, fdWhereClause, fdWhereCondition, fdWhereAndOr);

        // Select for food name
        String fieldsFood[] = new String[]{
                "_id",
                "food_name",
                "food_manufactor_name"
        };
        Cursor cursorFood;

        // Select for food_diary_cal_eaten
        Cursor cursorFdce;
        String fieldsFdce[] = new String[]{
                "_id",
                "fdce_id",
                "fdce_date",
                "fdce_meal_no",
                "fdce_eaten_energy",
                "fdce_eaten_proteins",
                "fdce_eaten_carbs",
                "fdce_eaten_fat"
        };
        String whereClause[] = new String[]{
                "fdce_date",
                "fdce_meal_no"
        };
        String whereCondition[] = new String[]{
                stringDateSQL,
                stringMealNumberSQL
        };
        String whereAndOr[] = new String[]{
                "AND"
        };
        cursorFdce = db.select("food_diary_cal_eaten", fieldsFdce, whereClause, whereCondition, whereAndOr);
        int cursorFdceCount = cursorFdce.getCount();

        int errorFdce = 0;
        if (cursorFdceCount == 0) {
            // Toast.makeText(getActivity(), sqle.toString(), Toast.LENGTH_LONG).show();
            String insFields = "_id, fdce_date, fdce_meal_no, fdce_eaten_energy, fdce_eaten_proteins, fdce_eaten_carbs, fdce_eaten_fat";
            String insValues = "NULL, " + stringDateSQL + ", " + stringMealNumberSQL + ", '0', '0', '0', '0'";
            db.insert("food_diary_cal_eaten", insFields, insValues);

            cursorFdce = db.select("food_diary_cal_eaten", fieldsFdce, whereClause, whereCondition, whereAndOr);
        }
        String stringFdceId = cursorFdce.getString(0);
        long longstringFdceId = Long.parseLong(stringFdceId);

        // Ready variables for sum
        int intFdceEatenEnergy = 0;
        int intFdceEatenProteins = 0;
        int intFdceEatenCarbs = 0;
        int intFdceEatenFat = 0;

        // Loop trough cursor
        int intCursorFdCount = cursorFd.getCount();
        for (int x = 0; x < intCursorFdCount; x++) {
            String stringFdId = cursorFd.getString(0);
            //Toast.makeText(getActivity(), "ID: " + stringFdId, Toast.LENGTH_SHORT).show();

            // Variables from food diary
            String fdFoodId = cursorFd.getString(1);
            String fdFoodIdSQL = db.quoteSmart(fdFoodId);

            String fdServingSizeGram = cursorFd.getString(2);
            String fdServingSizeGramMesurment = cursorFd.getString(3);
            String fdServingSizePcs = cursorFd.getString(4);
            String fdServingSizePcsMesurment = cursorFd.getString(5);
            String fdEnergyCalculated = cursorFd.getString(6);
            String fdProteinsCalculated = cursorFd.getString(7);
            String fdCarbsCalculated = cursorFd.getString(8);
            String fdFatCalculated = cursorFd.getString(9);
            int intFdEnergyCalculated = Integer.parseInt(fdEnergyCalculated);
            int intFdProteinsCalculated = Integer.parseInt(fdProteinsCalculated);
            int intFdCarbsCalculated = Integer.parseInt(fdCarbsCalculated);
            int intFdFatCalculated = Integer.parseInt(fdFatCalculated);

            // Get food name
            cursorFood = db.select("food", fieldsFood, "_id", fdFoodIdSQL);

            // Variables from food
            String foodID = cursorFood.getString(0);
            String foodName = cursorFood.getString(1);
            String foodManufactorName = cursorFood.getString(2);

            String subLine = foodManufactorName + ", " +
                    fdServingSizeGram + " " +
                    fdServingSizeGramMesurment + ", " +
                    fdServingSizePcs + " " +
                    fdServingSizePcsMesurment;


            // Add table rows
            // Add table rows
            TableLayout tl = null;
            if (stringMealNumber.equals("0")) {
                tl = (TableLayout) getActivity().findViewById(R.id.tableLayoutBreakfastItems); /* Find Tablelayout defined in main.xml */
            } else if (stringMealNumber.equals("1")) {
                tl = (TableLayout) getActivity().findViewById(R.id.tableLayoutLunchItems); /* Find Tablelayout defined in main.xml */
            } else if (stringMealNumber.equals("2")) {
                tl = (TableLayout) getActivity().findViewById(R.id.tableLayoutDinnerItems); /* Find Tablelayout defined in main.xml */
            } else if (stringMealNumber.equals("3")) {
                tl = (TableLayout) getActivity().findViewById(R.id.tableLayoutSnackItems); /* Find Tablelayout defined in main.xml */
            } else {
                tl = (TableLayout) getActivity().findViewById(R.id.tableLayoutExerciseItems); /* Find Tablelayout defined in main.xml */
            }


            TableRow tr1 = new TableRow(getActivity()); /* Create a new row to be added. */
            tr1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TableRow tr2 = new TableRow(getActivity()); /* Create a new row to be added. */
            tr2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            // Table row: TextView Name
            TextView textViewName = new TextView(getActivity()); // Add textview
            textViewName.setText(foodName);
            tr1.addView(textViewName);

            // Table row: TextView Energy
            TextView textViewEnergy = new TextView(getActivity()); // Add textview
            textViewEnergy.setText(fdEnergyCalculated);
            tr1.addView(textViewEnergy);

            // Table row: TextView subLine
            TextView textViewSubLine = new TextView(getActivity()); // Add textview
            textViewSubLine.setText(subLine);
            tr2.addView(textViewSubLine);

            // Add row to table
            tl.addView(tr1, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)); /* Add row to TableLayout. */
            tl.addView(tr2, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT)); /* Add row to TableLayout. */

            // Add Listener
            tr1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

                    // Get the row text
                    Context context = getContext();
                    TableRow row = (TableRow) v;
                    TextView tv = (TextView) row.getChildAt(0);

                    // Send it to edit
                    String tvText = "" + tv.getText(); // Lot VALUE Selected
                    rowOnClickEditDeleteFdLine(tvText);
                }
            });

            // Add Listener
            tr2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

                    // Get the row text
                    Context context = getContext();
                    TableRow row = (TableRow) v;
                    TextView tv = (TextView) row.getChildAt(0);

                    // Send it to edit
                    String tvText = "" + tv.getText(); // Lot VALUE Selected
                    rowOnClickEditDeleteFdLine(tvText);
                }
            });

            // Sum fields
            intFdceEatenEnergy = intFdceEatenEnergy + intFdEnergyCalculated;
            intFdceEatenProteins = intFdceEatenProteins + intFdProteinsCalculated;
            intFdceEatenCarbs = intFdceEatenCarbs + intFdCarbsCalculated;
            intFdceEatenFat = intFdceEatenFat + intFdFatCalculated;

            cursorFd.moveToNext();
        }

        // Update fdce
        if (stringMealNumber.equals("0")) {
            TextView textViewEnergyX = getActivity().findViewById(R.id.textViewEnergyBreakfast);
            textViewEnergyX.setText("" + intFdceEatenEnergy);
        } else if (stringMealNumber.equals("1")) {
            TextView textViewEnergyX = getActivity().findViewById(R.id.textViewEnergyLunch);
            textViewEnergyX.setText("" + intFdceEatenEnergy);
        } else if (stringMealNumber.equals("2")) {
            TextView textViewEnergyX = getActivity().findViewById(R.id.textViewEnergyDinner);
            textViewEnergyX.setText("" + intFdceEatenEnergy);
        } else if (stringMealNumber.equals("3")) {
            TextView textViewEnergyX = getActivity().findViewById(R.id.textViewEnergySnack);
            textViewEnergyX.setText("" + intFdceEatenEnergy);
        } else {
            TextView textViewEnergyX = getActivity().findViewById(R.id.textViewEnergyExercise);
            textViewEnergyX.setText("" + intFdceEatenEnergy);
        }

        String updateFields[] = new String[]{
                "fdce_eaten_energy",
                "fdce_eaten_proteins",
                "fdce_eaten_carbs",
                "fdce_eaten_fat"
        };
        String updateValues[] = new String[]{
                "'" + intFdceEatenEnergy + "'",
                "'" + intFdceEatenProteins + "'",
                "'" + intFdceEatenCarbs + "'",
                "'" + intFdceEatenFat + "'"
        };

        db.update("food_diary_cal_eaten", "_id", longstringFdceId, updateFields, updateValues);
        db.close();
    } // Update table

    private void addFoodToDiarySelectMealNumber() {
        /* Change layout */
        int newViewID = R.layout.fragment_home_select_meal_number;
        setMainView(newViewID);

        TextView textViewBreakfast = getActivity().findViewById(R.id.textViewBreakfast);
        textViewBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood(0);
            }
        });

        TextView textViewLunch = (TextView) getActivity().findViewById(R.id.textViewLunch);
        textViewLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood(1);
            }
        });

        TextView textViewDinner = (TextView) getActivity().findViewById(R.id.textViewDinner);
        textViewDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood(2);
            }
        });

        TextView textViewSnacks = (TextView) getActivity().findViewById(R.id.textViewSnacks);
        textViewSnacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood(3);
            }
        });

        TextView textViewSupper = (TextView) getActivity().findViewById(R.id.textViewExercise);
        textViewSupper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood(4);
            }
        });
    } // addFoodToDiarySelectMealNumber


    //add food
    private void addFood(int mealNumber) {

        /* Inialize fragmet */
        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = AddFoodToDiaryFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Send variable
        Bundle bundle = new Bundle();
        bundle.putString("mealNumber", "" + mealNumber); // Put anything what you want
        fragment.setArguments(bundle);

        //
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit();


    } // initalizeHome

    /*- Edit or delete fd line ---------------------------------------------- */
    private void rowOnClickEditDeleteFdLine(String stringTableRowTextName) {
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        /* Change layout */
        int newViewID = R.layout.fragment_home_edit_or_delete;
        setMainView(newViewID);

        /* Find information */
        // Select
        String fields[] = new String[]{
                "_id",
                "fd_food_id",
                "fd_serving_size_gram",
                "fd_serving_size_gram_mesurment",
                "fd_serving_size_pcs",
                "fd_serving_size_pcs_mesurment",
                "fd_energy_calculated",
                "fd_protein_calculated",
                "fd_carbohydrates_calculated",
                "fd_fat_calculated"
        };
        String stringFdDate = currentDateYear + "-" + currentDateMonth + "-" + currentDateDay;
        String stringDateSQL = db.quoteSmart(stringFdDate);
        Cursor cursorFd = db.select("food_diary", fields, "fd_date", stringDateSQL);
        String stringFdId = cursorFd.getString(0);

        // Select for food name
        String fieldsFood[] = new String[]{
                "_id",
                "food_name",
                "food_manufactor_name"
        };
        Cursor cursorFood;

        // Ready variables
        String stringFdFoodId = "";
        String stringFdFoodIdSQL = "";

        String stringFdServingSizeGram = "";
        String stringFdServingSizeGramMesurment = "";
        String stringFdServingSizePcs = "";
        String stringFdServingSizePcsMesurment = "";
        String stringFdEnergyCalculated = "";

        String stringFoodID = "";
        String stringFoodName = "";
        String stringFoodManufactorName = "";

        // Loop trough cursor, find the corresponding line that has been clicked
        int intCursorFdCount = cursorFd.getCount();
        for (int x = 0; x < intCursorFdCount; x++) {


            // Variables from food diary
            stringFdFoodId = cursorFd.getString(1);
            stringFdFoodIdSQL = db.quoteSmart(stringFdFoodId);
            stringFdServingSizeGram = cursorFd.getString(2);
            stringFdServingSizeGramMesurment = cursorFd.getString(3);
            stringFdServingSizePcs = cursorFd.getString(4);
            stringFdServingSizePcsMesurment = cursorFd.getString(5);
            stringFdEnergyCalculated = cursorFd.getString(6);

            // Get food name
            cursorFood = db.select("food", fieldsFood, "_id", stringFdFoodIdSQL);

            // Variables from food
            stringFoodID = cursorFood.getString(0);
            stringFoodName = cursorFood.getString(1);
            stringFoodManufactorName = cursorFood.getString(2);

            String subLine = stringFoodManufactorName + ", " +
                    stringFdServingSizeGram + " " +
                    stringFdServingSizeGramMesurment + ", " +
                    stringFdServingSizePcs + " " +
                    stringFdServingSizePcsMesurment;


            if (stringTableRowTextName.equals(stringFoodName)) {
                //Toast.makeText(getActivity(), "Fant." + stringTableRowTextName + "==" + foodName, Toast.LENGTH_LONG).show();
                break;
            } else if (stringTableRowTextName.equals(subLine)) {
                break;
            }


            cursorFd.moveToNext();
        }

        // Show fields
        if (stringFoodName.equals("")) {
            Toast.makeText(getActivity(), "Error: Could not load food name.", Toast.LENGTH_LONG).show();
        } else {
            // Add to current
            currentFoodName = stringFoodName;
            currentFoodId = stringFoodID;
            currentFdId = stringFdId;

            TextView textViewViewFoodName = getActivity().findViewById(R.id.textViewViewFoodName);
            textViewViewFoodName.setText(stringFoodName);

            TextView textViewViewFoodManufactorName = getActivity().findViewById(R.id.textViewViewFoodManufactorName);
            textViewViewFoodManufactorName.setText(stringFoodManufactorName);


            TextInputEditText editTextServingSizePcs = getActivity().findViewById(R.id.editTextServingSizePcs);
            editTextServingSizePcs.setText(stringFdServingSizePcs);

            TextView textViewServingSizePcsMesurment = getActivity().findViewById(R.id.textViewServingSizePcsMesurment);
            textViewServingSizePcsMesurment.setText(stringFdServingSizePcsMesurment);

            TextInputEditText editTextServingSizeGram = getActivity().findViewById(R.id.editTextServingSizeGram);
            editTextServingSizeGram.setText(stringFdServingSizeGram);

            TextView textViewServingSizeGramMesurment = getActivity().findViewById(R.id.textViewServingSizeGramMesurment);
            textViewServingSizeGramMesurment.setText(stringFdServingSizeGramMesurment);

            /* Listener for editTextPortionSizePcs */
            editTextServingSizePcs.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    if (!(s.toString().equals(""))) {
                        // My code here
                        editTextPortionSizePcsOnChange();
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
            editTextServingSizePcs.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                    } else {
                        String lock = "portionSizePcs";
                        releaseLock(lock);
                    }
                }
            });

            editTextServingSizeGram.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    if (!(s.toString().equals(""))) {
                        // My code here
                        editTextPortionSizeGramOnChange();
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
            editTextServingSizeGram.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                    } else {
                        String lock = "portionSizeGram";
                        releaseLock(lock);
                    }
                }
            });


            // Watcher
            Button buttonSubmitEdit = getActivity().findViewById(R.id.buttonSubmitEdit);
            buttonSubmitEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnClickEditFdLineSubmit();
                }
            });
            Button buttonSubmitDelete = getActivity().findViewById(R.id.buttonSubmitDelete);
            buttonSubmitDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnClickDeleteFdLineSubmit();
                }
            });
        }
        /* Close db */
        db.close();
    }

    private void releaseLock(String lock) {
        if (lock.equals("portionSizeGram")) {
            lockPortionSizeByGram = false;
        } else {
            lockPortionSizeByPcs = false;
        }
    }

    /*- editTextPortionSizePcsOnChange ---------------------------------------------------- */
    public void editTextPortionSizePcsOnChange() {
        if (!(lockPortionSizeByGram)) {
            // Lock
            lockPortionSizeByPcs = true;

            // Get value of pcs
            TextInputEditText editTextPortionSizePcs = getActivity().findViewById(R.id.editTextServingSizePcs);
            String stringPortionSizePcs = editTextPortionSizePcs.getText().toString();

            double doublePortionSizePcs = 0;

            if (stringPortionSizePcs.equals("")) {
                doublePortionSizePcs = 0;
            } else {
                try {
                    doublePortionSizePcs = Double.parseDouble(stringPortionSizePcs);
                } catch (NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
            }

            DBAdapter db = new DBAdapter(getActivity());
            db.open();

            String fields[] = new String[]{
                    "food_serving_size_gram"
            };
            String currentIdSQL = db.quoteSmart(currentFoodId);
            Cursor foodCursor = db.select("food", fields, "_id", currentIdSQL);

            // Convert cursor to strings
            String stringServingSize = foodCursor.getString(0);
            db.close();

            // Convert cursor to double
            double doubleServingSize = 0;
            try {
                doubleServingSize = Double.parseDouble(stringServingSize);
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }

            double doublePortionSizeGram = Math.round(doublePortionSizePcs * doubleServingSize);

            // Update portion size gram
            TextInputEditText editTextPortionSizeGram = getActivity().findViewById(R.id.editTextServingSizeGram);
            editTextPortionSizeGram.setText("" + doublePortionSizeGram);
        }
    } // editTextPortionSizePcs

    /*- editTextPortionSizeGramOnChange ---------------------------------------------------- */
    public void editTextPortionSizeGramOnChange(){
        if(!(lockPortionSizeByPcs)) {

            // Lock
            lockPortionSizeByGram = true;

            // Get value of gram
            TextInputEditText editTextPortionSizeGram = getActivity().findViewById(R.id.editTextServingSizeGram);
            String stringPortionSizeGram = editTextPortionSizeGram.getText().toString();
            double doublePortionSizeGram = 0;
            try {
                doublePortionSizeGram = Double.parseDouble(stringPortionSizeGram);
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }

            DBAdapter db = new DBAdapter(getActivity());
            db.open();

            String fields[] = new String[]{
                    "food_serving_size_gram"
            };
            String currentIdSQL = db.quoteSmart(currentFoodId);
            Cursor foodCursor = db.select("food", fields, "_id", currentIdSQL);

            // Convert cursor to strings
            String stringServingSizeGram = foodCursor.getString(0);
            db.close();

            // Convert cursor to double
            double doubleServingSizeGram = 0;
            try {
                doubleServingSizeGram = Double.parseDouble(stringServingSizeGram);
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }

            // Calculate pcs
            double doublePortionSizePcs = Math.round(doublePortionSizeGram / doubleServingSizeGram);


            // Update
            // Get value of pcs
            TextInputEditText editTextPortionSizePcs = getActivity().findViewById(R.id.editTextServingSizePcs);
            editTextPortionSizePcs.setText("" + doublePortionSizePcs);
        }

    } // editTextPortionSizeGramOnChange

    /*- Edit fd line submit ---------------------------------------------------------------- */
    public void OnClickEditFdLineSubmit(){
        int error = 0;

        // Database
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        // FdID
        long longFdID = 0;
        try{
            longFdID = Long.parseLong(currentFdId);
        }
        catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }


        // Get food info
        String fields[] = new String[] {
                "food_serving_size_gram",
                "food_energy",
                "food_proteins",
                "food_carbohydrates",
                "food_fat"
        };
        String currentIdSQL = db.quoteSmart(currentFoodId);
        Cursor foodCursor = db.select("food", fields, "_id", currentIdSQL);

        // Convert cursor to strings
        String stringGetFromSQLFoodServingSizeGram = foodCursor.getString(0);
        double doubleGetFromSQLFoodServingSizeGram = Double.parseDouble(stringGetFromSQLFoodServingSizeGram);

        String stringGetFromSQLFoodEnergy = foodCursor.getString(1);
        double doubleGetFromSQLFoodEnergy = Double.parseDouble(stringGetFromSQLFoodEnergy);

        String stringGetFromSQLFoodProteins = foodCursor.getString(2);
        double doubleGetFromSQLFoodProteins = Double.parseDouble(stringGetFromSQLFoodProteins);

        String stringGetFromSQLFoodCarbohydrates = foodCursor.getString(3);
        double doubleGetFromSQLFoodCarbohydrates = Double.parseDouble(stringGetFromSQLFoodCarbohydrates);

        String stringGetFromSQLFoodFat = foodCursor.getString(4);
        double doubleGetFromSQLFoodFat = Double.parseDouble(stringGetFromSQLFoodFat);


        // Update fd serving size gram
        TextInputEditText editTextServingSizeGram = getActivity().findViewById(R.id.editTextServingSizeGram);
        String stringFdServingSizeGram = editTextServingSizeGram.getText().toString();
        String stringFdServingSizeGramSQL = db.quoteSmart(stringFdServingSizeGram);
        db.update("food_diary", "_id", longFdID, "fd_serving_size_gram", stringFdServingSizeGramSQL);
        double doubleFdServingSizeGram = Double.parseDouble(stringFdServingSizeGram);

        // Update fd serving size pcs
        double doubleFdServingSizePcs = Math.round(doubleFdServingSizeGram / doubleGetFromSQLFoodServingSizeGram);
        String stringFdServingSizePcs = "" + doubleFdServingSizePcs;
        String stringFdServingSizePcsSQL = db.quoteSmart(stringFdServingSizePcs);
        db.update("food_diary", "_id", longFdID, "fd_serving_size_pcs", stringFdServingSizePcsSQL);

        // Update fd energy calculated
        double doubleFdEnergyCalculated = Math.round((doubleFdServingSizeGram*doubleGetFromSQLFoodEnergy)/100);
        String stringFdEnergyCalcualted = "" + doubleFdEnergyCalculated;
        String stringFdEnergyCalcualtedSQL = db.quoteSmart(stringFdEnergyCalcualted);
        db.update("food_diary", "_id", longFdID, "fd_energy_calculated", stringFdEnergyCalcualtedSQL);

        // Proteins calcualted
        double doubleFdProteinsCalculated = Math.round((doubleFdServingSizeGram*doubleGetFromSQLFoodProteins)/100);
        String stringFdProteinsCalcualted = "" + doubleFdProteinsCalculated;
        String stringFdProteinsCalcualtedSQL = db.quoteSmart(stringFdProteinsCalcualted);
        db.update("food_diary", "_id", longFdID, "fd_protein_calculated", stringFdProteinsCalcualtedSQL);

        // Carbohydrates calcualted
        double doubleFdCarbohydratesCalculated = Math.round((doubleFdServingSizeGram*doubleGetFromSQLFoodCarbohydrates)/100);
        String stringFdCarbohydratesCalcualted = "" + doubleFdCarbohydratesCalculated;
        String stringFdCarbohydratesCalcualtedSQL = db.quoteSmart(stringFdCarbohydratesCalcualted);
        db.update("food_diary", "_id", longFdID, "fd_carbohydrates_calculated", stringFdCarbohydratesCalcualtedSQL);

        // Fat calcualted
        double doubleFdFatCalculated = Math.round((doubleFdServingSizeGram*doubleGetFromSQLFoodFat)/100);
        String stringFdFatCalcualted = "" + doubleFdFatCalculated;
        String stringFdFatCalcualtedSQL = db.quoteSmart(stringFdFatCalcualted);
        db.update("food_diary", "_id", longFdID, "fd_fat_calculated", stringFdFatCalcualtedSQL);


        db.close();

        Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();

        /* Restart fragment */
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame, new HomeFragment(), HomeFragment.class.getName()).commit();

    }

    /*- Delete fd line submit ---------------------------------------------------------------- */
    public void OnClickDeleteFdLineSubmit(){
        Toast.makeText(getActivity(), "Deleted " + currentFoodName, Toast.LENGTH_SHORT).show();

        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        long longPrimaryKey = 0;
        try{
            longPrimaryKey = Long.parseLong(currentFdId);
        }
        catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }

        db.delete("food_diary", "_id", longPrimaryKey);

        db.close();

        /* Restart fragment */
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame, new HomeFragment(), HomeFragment.class.getName()).commit();
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
