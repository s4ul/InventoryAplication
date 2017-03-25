package com.fiuady.android.inventoryaplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.fiuady.db.InventoryDbSchema.*;
import com.fiuady.db.InventoryHelper;

public class MainActivity extends AppCompatActivity {

    private InventoryHelper inventoryHelper;
    private SQLiteDatabase db;
    private Spinner categoriesSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoriesSpinner = (Spinner)findViewById(R.id.Categories_list);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        adapter.add("Hola");
        adapter.add("Android");
        adapter.add("Java");
        categoriesSpinner.setAdapter(adapter);



        //InventoryHelper.backupDatabaseFile(getApplicationContext());

        inventoryHelper= new InventoryHelper(getApplicationContext());
        db= inventoryHelper.getWritableDatabase();

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(adapter);
        }


    }
}
