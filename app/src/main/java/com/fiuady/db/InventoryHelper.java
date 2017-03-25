package com.fiuady.db;

import android.content.Context;
import android.content.Entity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;


public final class InventoryHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "inventory.db";
    public static final int SCHEMA_VERSION = 1;
    private Context context;
    public InventoryHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        this.context = context;
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.JELLY_BEAN) {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("PRAGMA foreign_keys = ON");
        }

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        applySQLFile(db,"schema.sql");
        applySQLFile(db,"Initial.sql");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }


    public void applySQLFile(SQLiteDatabase db,String filename)
    {
        BufferedReader reader = null;
        try
        {
            InputStream is=context.getAssets().open(filename);
            reader=new BufferedReader(new InputStreamReader(is));

            StringBuilder statement = new StringBuilder();
            for (String line; (line = reader.readLine())!=null;) {
                line=line.trim();
            if(!TextUtils.isEmpty(line) && !line.startsWith("--")){
                statement.append(line);
            }
                if(line.endsWith(";")){
                    db.execSQL(statement.toString());
                    statement.setLength(0);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            if (reader!=null)
            {
                try{
                    reader.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    public static void backupDatabaseFile(Context context)
    {
        try{
        File sd = Environment.getExternalStorageDirectory();
        if (sd.canWrite())
        {
            String currentDBPath = "/data/data/" +
                    context.getPackageName() + "/databases/inventory.db";
            String backUpDbPath = "Inventory-bk.db";
            File currentDB = new File(currentDBPath);
            File backupDB = new File(sd,backUpDbPath);

            if (currentDB.exists()) {
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src,0,src.size());
                src.close();
                dst.close();
            }


            }
        }catch(IOException e){

        }
    }

}
