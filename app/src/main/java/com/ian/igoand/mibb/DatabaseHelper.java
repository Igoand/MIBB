package com.ian.igoand.mibb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOGCAT = null;


    /*Nazwa bazy danych MIBB*/
    public static final String DATEBASE_NAME = "mibb.db";

    public DatabaseHelper(Context context) {
        super(context, DATEBASE_NAME, null, 1); // Tworzenie bazy danych
        Log.d(LOGCAT, "Utworzono bazę danych");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS wojewodztwa (ID INTEGER PRIMARY KEY, NazwaWojewodztwa TEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS powiaty (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, NazwaPowiatu TEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS gminy (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, NazwaGminy TEXT NOT NULL);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS miejscowosci (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, NazwaMiejscowosci TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "wojewodztwa");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "powiaty");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "gminy");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "miejscowosci");
    }

    public ArrayList<HashMap<String, String>> getWojewodztwa(){
      ArrayList<HashMap<String, String>> listaWojewodztw;
      listaWojewodztw = new ArrayList<HashMap<String, String>>();
      String selectQuery = "SELECT * FROM listaWojewodztw";
      SQLiteDatabase mibbDB = this.getWritableDatabase();
      Cursor cursor = mibbDB.rawQuery(selectQuery, null);
      if (cursor.moveToFirst()){
          do{
              HashMap<String, String> map = new HashMap<String, String>();
              map.put("ID", cursor.getString(0));
              map.put("NazwaWojewodztwa", cursor.getString(1));
              listaWojewodztw.add(map);
          }while (cursor.moveToNext());
      }
      return  listaWojewodztw;
    };
}