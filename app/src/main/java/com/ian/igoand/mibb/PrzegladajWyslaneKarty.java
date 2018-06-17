package com.ian.igoand.mibb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import m_MySQL.Downloader;

public class PrzegladajWyslaneKarty extends AppCompatActivity {


    final String urlAddress = "https://a491e0ba.ngrok.io/restSelectKartaObserwacji.php";
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przeglad_wyslanych_kart);
        listView = findViewById(R.id.listViewKarty);
        new Downloader(PrzegladajWyslaneKarty.this, urlAddress, listView).execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentSzczegolyObserwacji = new Intent(PrzegladajWyslaneKarty.this, PrzegladajSzczegolyObserwacji.class);

                String idKartyStr = (String) ((TextView) view).getText();

                int idx = idKartyStr.indexOf("Numer Karty:");
                idKartyStr = idKartyStr.substring(idx);
                idx = idKartyStr.indexOf(": ");
                idKartyStr = idKartyStr.substring(idx+2);

                intentSzczegolyObserwacji.putExtra("idKarty", idKartyStr);

                startActivity(intentSzczegolyObserwacji);
            }
        });
    }
}