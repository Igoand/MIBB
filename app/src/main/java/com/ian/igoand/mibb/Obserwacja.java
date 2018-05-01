package com.ian.igoand.mibb;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class Obserwacja extends AppCompatActivity {

    //    Tworzenie obiektów
    KartaObserwacji karta = new KartaObserwacji();
    PrzygotujDaneTERYT teryt = new PrzygotujDaneTERYT();
    ObslugaGPS gps = new ObslugaGPS();
    Context context;

    //    Deklaracja zmiennych
    String nazwaGniazda;
    String miejscowosc;
    String lokalizacja;
    LocationManager locationManager = null;
    LocationListener locationListener = null;
    TextView lblNazwaGniazda;
    AutoCompleteTextView inputMiejscowosc;
    Button btnGPS;
    TextView lblLokalizacja;

    //    Constructor
/*    public Obserwacja(String nazwaGniazda, String miejscowosc, String lokalizacja) {
        this.nazwaGniazda = nazwaGniazda;
        this.miejscowosc = miejscowosc;
        this.lokalizacja = lokalizacja;
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodanie_obserwacji);
        context = getApplicationContext();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

//        Obsługa nazwy miejscowosci i nazwy gniazda
        lblNazwaGniazda = (TextView) findViewById(R.id.lblNazwaGniazda);
        inputMiejscowosc = (AutoCompleteTextView) findViewById(R.id.inputLokalizacjaMiejscowosc);
        btnGPS = (Button) findViewById(R.id.btnPobierzLokalizacje);
        lblLokalizacja = (TextView) findViewById(R.id.lblLokGPS);

        karta.zwiekszNumerGniazda();
        String miejscowosc = "";
        try {
            teryt.szukajGmine(inputMiejscowosc, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        miejscowosc = miejscowosc.concat(inputMiejscowosc.getText().toString());

        lblNazwaGniazda.setText(karta.dajNazweGniazda(miejscowosc));

//        Obsługa lokalizacji GPS
        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                locationListener = new ObslugaGPS();
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

                String lokalizacja = ObslugaGPS.dajDaneGPS();
                gps.dajDaneGPS();
                lblLokalizacja.setText(lokalizacja);
            }
        });
    }
}
