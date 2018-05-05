package com.ian.igoand.mibb;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;

public class Obserwacja extends AppCompatActivity {

    public FusedLocationProviderClient klientLokalizacji;
    public double szerokosc, wysokosc;
    public String miejscowosc = "";
    //    Tworzenie obiektów
    KartaObserwacji karta = new KartaObserwacji();
    PrzygotujDaneTERYT teryt = new PrzygotujDaneTERYT();
    Geocoder geocoder;
    //    Deklaracja zmiennych
    TextView lblNazwaGniazda;
    AutoCompleteTextView inputMiejscowosc;
    TextView lblOdczytanaLokalizacja;
    Button btnOdczytajLokalizacjeGPS;
    ImageButton btnPokazNaMapie;
    Spinner spinnerUsytuowanie;
    String adres;
    double lokalizacjaX;
    double lokalizacjaY;
    Spinner spinnerUsytuowanie2;
    Spinner spinnerUsytuowanie3;
    Spinner spinnerUsytuowanie4;
    EditText edtNazwaDrzewa;
    Spinner spinnerPlatforma;
    Spinner spinnerEfektLegu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodanie_obserwacji);


//        Obsługa nazwy miejscowosci i nazwy gniazda
        lblNazwaGniazda = findViewById(R.id.lblNazwaGniazda);
        inputMiejscowosc = findViewById(R.id.inputLokalizacjaMiejscowosc);
        btnOdczytajLokalizacjeGPS = findViewById(R.id.btnPobierzLokalizacje);
        btnPokazNaMapie = findViewById(R.id.btnPokazNaMapie);
        lblOdczytanaLokalizacja = findViewById(R.id.lblLokGPS);
        spinnerUsytuowanie = findViewById(R.id.spinnerUsytuowanie);
        spinnerUsytuowanie2 = findViewById(R.id.spinnerUsytuowanie2);
        spinnerUsytuowanie3 = findViewById(R.id.spinnerUsytuowanie3);
        spinnerUsytuowanie4 = findViewById(R.id.spinnerUsytuowanie4);
        edtNazwaDrzewa = findViewById(R.id.edtNazwaDrzewa);
        spinnerPlatforma = findViewById(R.id.spinnerPlatforma);
        spinnerEfektLegu = findViewById(R.id.spinnerEfektLegu);

        btnOdczytajLokalizacjeGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dajLokalizacje();
                inputMiejscowosc.setText(miejscowosc);
                //lblNazwaGniazda.setText(inputMiejscowosc.getText().toString() + "01");
                inputMiejscowosc.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        lblNazwaGniazda.setText("GniazdoX");
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        lblNazwaGniazda.setText(inputMiejscowosc.getText().toString() + "01");
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });
                lblOdczytanaLokalizacja.setText(adres + "\nSzerokość: " + lokalizacjaX + ", wysokość: " + lokalizacjaY);
            }
        });

        btnPokazNaMapie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Obserwacja.this, ObslugaMapy.class);
                intent.putExtra("x", lokalizacjaX);
                intent.putExtra("y", lokalizacjaY);
                intent.putExtra("pointer", miejscowosc);
                startActivity(intent);
            }
        });

        try {
            teryt.szukajGmine(inputMiejscowosc, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        spinnerUsytuowanie.setAdapter(przygotujAdapter(R.array.usytuowanieI));

        spinnerUsytuowanie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spinnerUsytuowanie.getSelectedItem().toString()) {
                    case "słup":
                        spinnerUsytuowanie2.setAdapter(przygotujAdapter(R.array.uSlupTyp));
                        spinnerUsytuowanie3.setVisibility(View.VISIBLE);
                        spinnerUsytuowanie3.setAdapter(przygotujAdapter(R.array.uSlupMaterial));
                        spinnerUsytuowanie4.setVisibility(View.VISIBLE);
                        spinnerUsytuowanie4.setAdapter(przygotujAdapter(R.array.uSlupKsztalt));
                        break;
                    case "drzewo":
                        spinnerUsytuowanie2.setAdapter(przygotujAdapter(R.array.uTypDrzewa));
                        spinnerUsytuowanie3.setVisibility(View.VISIBLE);
                        spinnerUsytuowanie3.setAdapter(przygotujAdapter(R.array.uGatunekDrzewa));
                        spinnerUsytuowanie4.setVisibility(View.INVISIBLE);
                        break;
                    case "dach":
                        spinnerUsytuowanie2.setAdapter(przygotujAdapter(R.array.uTypBudynku));
                        spinnerUsytuowanie3.setVisibility(View.VISIBLE);
                        spinnerUsytuowanie3.setAdapter(przygotujAdapter(R.array.uTypDachu));
                        spinnerUsytuowanie4.setVisibility(View.INVISIBLE);
                        break;
                    case "komin":
                        spinnerUsytuowanie2.setAdapter(przygotujAdapter(R.array.uTypKomina));
                        spinnerUsytuowanie3.setVisibility(View.INVISIBLE);
                        spinnerUsytuowanie4.setVisibility(View.INVISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerUsytuowanie3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spinnerUsytuowanie3.getSelectedItem().toString()) {
                    case "słup":
//                        spinnerUsytuowanie4.setVisibility(View.VISIBLE);
//                        spinnerUsytuowanie4.setAdapter(przygotujAdapter(R.array.uSlupKsztalt));
                        break;
                    case "Inne":
                        edtNazwaDrzewa.setVisibility(View.VISIBLE);
                        break;
                    default:
                        edtNazwaDrzewa.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerPlatforma.setAdapter(przygotujAdapter(R.array.platforma));

        spinnerEfektLegu.setAdapter(przygotujAdapter(R.array.efektLegu));
    }

    public ArrayAdapter<CharSequence> przygotujAdapter(int typDancyh) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, typDancyh, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public void dajLokalizacje() {

        klientLokalizacji = LocationServices.getFusedLocationProviderClient(this);
        klientLokalizacji.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        String wartosc = "";
                        Address lokal = null;
                        if (location != null) {
                            szerokosc = location.getLatitude();
                            wysokosc = location.getLongitude();
                            lokal = dajAdres(szerokosc, wysokosc);
                            //wyswietl(lblOdczytanaLokalizacja, lokal.getAddressLine(0));

                        }
                    }
                });
    }

    public Address dajAdres(double x, double y) {
        List<Address> adres = null;
        geocoder = new Geocoder(getApplicationContext());
        try {
            adres = geocoder.getFromLocation(x, y, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert adres != null;
        setMiejscowosc(adres.get(0).getLocality());
        setAdres(adres.get(0).getAddressLine(0));
        setLokalizacjaX(adres.get(0).getLatitude());
        setLokalizacjaY(adres.get(0).getLongitude());

        return adres.get(0);
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public double getLokalizacjaX() {
        return this.lokalizacjaX;
    }

    public void setLokalizacjaX(double lokalizacjaX) {
        this.lokalizacjaX = lokalizacjaX;
    }

    public double getLokalizacjaY() {
        return this.lokalizacjaY;
    }

    public void setLokalizacjaY(double lokalizacjaY) {
        this.lokalizacjaY = lokalizacjaY;
    }

    public String getMiejscowosc() {
        return this.miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }
}
