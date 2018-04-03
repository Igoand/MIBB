package com.ian.igoand.mibb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    /*Nazwa bazy danych MIBB*/
    public static final String DATEBASE_NAME = "mibb.db";



    public DatabaseHelper(Context context) {
        super(context, DATEBASE_NAME, null, 1);
        Toast.makeText(context, "Baza danych" + DATEBASE_NAME + " została utworzona", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE wojewodztwa (ID INTEGER PRIMARY KEY AUTOINCREMENT, NazwaWojewodztwa TEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE powiaty (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, NazwaPowiatu TEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE gminy (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, NazwaGminy TEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE miejscowosci (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, NazwaMiejscowosci TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "wojewodztwa");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "powiaty");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "gminy");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "miejscowosci");
    }

    public boolean utworzBazeTERYT(String tekst){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NazwaWojewodztwa", tekst);
        contentValues.put("ID", "");
        long result = db.insert("wojewodztwa", null, contentValues);
        if (result == -1){
            return false;
        }else
            return  true;
    };
}