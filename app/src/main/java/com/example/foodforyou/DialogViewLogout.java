package com.example.foodforyou;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogViewLogout extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirm Logout").setMessage("If you logout, your data will be lost. \n" +
                "Do you want to logout ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        deleteAcount();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }

    public void deleteAcount(){
        DBAdapter db = new DBAdapter(getActivity());
        db.open();

        db.deleteAll();

        db.close();

        Intent intent = new Intent(getActivity(), SignUp.class);
        startActivity(intent);

    }


}

