package com.ian.igoand.mibb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.concurrent.ExecutionException;

import m_MySQL.DataParser;

public class PrzegladajSzczegolyObserwacji extends AppCompatActivity {
    ObslugaDB obslugaDB;
    String idKarty;
    String nazwaGminy;

    ListView listViewObserwacje;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przeglad_szczegolow_obserwacji);
        progressBar = findViewById(R.id.progressBarSzczegolyObserwacji);
        listViewObserwacje = findViewById(R.id.listViewObserwacje);
        obslugaDB = new ObslugaDB(this, progressBar);
        Bundle bundle = getIntent().getExtras();

        idKarty = bundle.getString("idKarty");
        nazwaGminy = bundle.getString("nazwaGminy");
        String idKartyStr = String.valueOf(idKarty);

        obslugaDB.execute("wyszukajObserwacje", idKartyStr);
        try {
            String json = obslugaDB.get();
            new DataParser(this, json, listViewObserwacje, "obserwacja").execute();
//            Toast.makeText(getApplicationContext(), test, Toast.LENGTH_LONG).show();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}