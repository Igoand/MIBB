package com.ian.igoand.mibb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Obserwacja extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public FusedLocationProviderClient klientLokalizacji;
    public double szerokosc, wysokosc;
    public String miejscowosc = "";

    //    Tworzenie obiektów
    PrzygotujDaneTERYT teryt = new PrzygotujDaneTERYT();
    KartaObserwacji kartaObserwacjiClass = new KartaObserwacji();
    Geocoder geocoder;

    // Zmienne pomocnicze
    String adres;
    double lokalizacjaX;
    double lokalizacjaY;

    // Deklaracja pól klasy
    String nazwaGniazda = "";
    String lokalizacjaGniazda = "";
    String usytuowanieGniazda1 = "";
    String usytuowanieGniazda2 = "";
    String usytuowanieGniazda3 = "";
    String usytuowanieGniazda4 = "";
    String usytuowanieGniazdaAll = "";
    String platformaGniazda = "";
    String efektLeguGniazda = "";
    String stanGniazda = "";
    String obecnoscObraczki = "";
    String uwagiGniazda = "";
    String zdjeciaGniazda = null;
    String kartaObserwacji = "";

    //    Deklaracja zmiennych na potrzeby obsługi activity
    TextView lblNazwaGniazda;
    AutoCompleteTextView inputMiejscowosc;
    TextView lblOdczytanaLokalizacja;
    Button btnOdczytajLokalizacjeGPS;
    ImageButton btnPokazNaMapie;
    Spinner spinnerUsytuowanie;
    Spinner spinnerUsytuowanie2;
    Spinner spinnerUsytuowanie3;
    Spinner spinnerUsytuowanie4;
    EditText edtNazwaDrzewa;
    Spinner spinnerPlatforma;
    Spinner spinnerEfektLegu;
    Spinner spinnerStanGniazda;
    Switch switchStanGniazda;
    Switch switchObraczka;
    FloatingActionButton btnZrobZdjecia;
    ImageView imageZdjecie;
    Button btnDodajObserwacje;
    EditText edtUwagi;

    public void setObecnoscObraczki(String obecnoscObraczki) {
        this.obecnoscObraczki = obecnoscObraczki;
    }

    public String getUsytuowanieGniazdaAll() {
        return usytuowanieGniazdaAll;
    }

    public void setUsytuowanieGniazdaAll(String usytuowanieGniazdaAll) {
        this.usytuowanieGniazdaAll = usytuowanieGniazdaAll;
    }

    public String getUsytuowanieGniazda2() {
        return usytuowanieGniazda2;
    }

    public void setUsytuowanieGniazda2(String usytuowanieGniazda2) {
        this.usytuowanieGniazda2 = usytuowanieGniazda2;
    }

    public String getUsytuowanieGniazda3() {
        return usytuowanieGniazda3;
    }

    public void setUsytuowanieGniazda3(String usytuowanieGniazda3) {
        this.usytuowanieGniazda3 = usytuowanieGniazda3;
    }

    public String getUsytuowanieGniazda4() {
        return usytuowanieGniazda4;
    }

    public void setUsytuowanieGniazda4(String usytuowanieGniazda4) {
        this.usytuowanieGniazda4 = usytuowanieGniazda4;
    }

    public String getNazwaGniazda() {
        return nazwaGniazda;
    }

    public void setNazwaGniazda(String nazwaGniazda) {
        this.nazwaGniazda = nazwaGniazda;
    }

    public String getLokalizacjaGniazda() {
        return lokalizacjaGniazda;
    }

    public void setLokalizacjaGniazda(String lokalizacjaGniazda) {
        this.lokalizacjaGniazda = lokalizacjaGniazda;
    }

    public String getUsytuowanieGniazda1() {

        return usytuowanieGniazda1;
    }

    public void setUsytuowanieGniazda1(String usytuowanieGniazda1) {
        this.usytuowanieGniazda1 = usytuowanieGniazda1;
    }

    public String getPlatformaGniazda() {
        return platformaGniazda;
    }

    public void setPlatformaGniazda(String platformaGniazda) {
        this.platformaGniazda = platformaGniazda;
    }

    public String getEfektLeguGniazda() {
        return efektLeguGniazda;
    }

    public void setEfektLeguGniazda(String efektLeguGniazda) {
        this.efektLeguGniazda = efektLeguGniazda;
    }

    public String getStanGniazda() {
        return stanGniazda;
    }

    public void setStanGniazda(String stanGniazda) {
        this.stanGniazda = stanGniazda;
    }

    public String getUwagiGniazda() {
        return uwagiGniazda;
    }

    public void setUwagiGniazda(String uwagiGniazda) {
        this.uwagiGniazda = uwagiGniazda;
    }

    public void setZdjeciaGniazda(String zdjeciaGniazda) {
        this.zdjeciaGniazda = zdjeciaGniazda;
    }

    public void setKartaObserwacji(String kartaObserwacji) {
        this.kartaObserwacji = kartaObserwacji;
    }

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
        spinnerStanGniazda = findViewById(R.id.spinnerStanGniazda);
        switchStanGniazda = findViewById(R.id.switchStanGniazda);
        btnZrobZdjecia = findViewById(R.id.btnZrobZdjecie);
        imageZdjecie = findViewById(R.id.imgZdjecie);
        btnDodajObserwacje = findViewById(R.id.btnDodajObserwacje);
        switchObraczka = findViewById(R.id.switchObraczka);
        edtUwagi = findViewById(R.id.inputUwagi);

        btnOdczytajLokalizacjeGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Wyznaczenie lokalizacji
                dajLokalizacje();

                // Wstawienie odczytanej miejscowosci
                miejscowosc = getMiejscowosc();
                inputMiejscowosc.setText(miejscowosc);

                // Obsługa sytuacji przypadku zmiany miejscowości w polu input
                inputMiejscowosc.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        lblNazwaGniazda.setText("");
                        lblOdczytanaLokalizacja.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        ObslugaDB obslugaDB = new ObslugaDB(getApplicationContext());
                        obslugaDB.execute("wyslijObserwacje", nazwaGniazda, lokalizacjaGniazda, usytuowanieGniazdaAll, platformaGniazda, efektLeguGniazda, stanGniazda, obecnoscObraczki, uwagiGniazda, zdjeciaGniazda, kartaObserwacji);

                        setNazwaGniazda(inputMiejscowosc.getText().toString() + obslugaDB.execute("sprawdzNrGniazda", inputMiejscowosc.getText().toString()));
                        lblNazwaGniazda.setText(nazwaGniazda);

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                    }
                });
                lblOdczytanaLokalizacja.setText(adres + "\nSzerokość: " + lokalizacjaX + ", wysokość: " + lokalizacjaY);
                setLokalizacjaGniazda(adres + "\nSzerokość: " + lokalizacjaX + ", wysokość: " + lokalizacjaY);
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
                                                                     spinnerUsytuowanie4.setVisibility(View.GONE);
                                                                     break;
                                                                 case "dach":
                                                                     spinnerUsytuowanie2.setAdapter(przygotujAdapter(R.array.uTypBudynku));
                                                                     spinnerUsytuowanie3.setVisibility(View.VISIBLE);
                                                                     spinnerUsytuowanie3.setAdapter(przygotujAdapter(R.array.uTypDachu));
                                                                     spinnerUsytuowanie4.setVisibility(View.GONE);
                                                                     break;
                                                                 case "komin":
                                                                     spinnerUsytuowanie2.setAdapter(przygotujAdapter(R.array.uTypKomina));
                                                                     spinnerUsytuowanie3.setVisibility(View.INVISIBLE);
                                                                     spinnerUsytuowanie4.setVisibility(View.GONE);
                                                                     break;
                                                             }
                                                         }

                                                         @Override
                                                         public void onNothingSelected(AdapterView<?> adapterView) {
                                                         }
                                                     }
        );

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
                        edtNazwaDrzewa.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                setUsytuowanieGniazda4("");
                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                setUsytuowanieGniazda4(edtNazwaDrzewa.getText().toString());
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });
                        break;
                    default:
                        edtNazwaDrzewa.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        setUsytuowanieGniazdaAll(usytuowanieGniazda1 + usytuowanieGniazda2 + usytuowanieGniazda3 + usytuowanieGniazda4 + edtNazwaDrzewa.toString());

        spinnerPlatforma.setAdapter(przygotujAdapter(R.array.platforma));
        setPlatformaGniazda(dajWartoscSpinnera(spinnerPlatforma));
        if (getPlatformaGniazda().contains(" ")) {
            setEfektLeguGniazda(getPlatformaGniazda().substring(0, getPlatformaGniazda().indexOf(" ")));
        }

        spinnerEfektLegu.setAdapter(przygotujAdapter(R.array.efektLegu));
        setEfektLeguGniazda(spinnerEfektLegu.getSelectedItem().toString());
        if (getEfektLeguGniazda().contains(" ")) {
            setEfektLeguGniazda(getEfektLeguGniazda().substring(0, getEfektLeguGniazda().indexOf(" ")));
        }

        setStanGniazda(switchStanGniazda.getTextOn().toString());
        // Obsługa pola stanu gniazda
        switchStanGniazda.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!switchStanGniazda.isChecked()) {
                    spinnerStanGniazda.setVisibility(View.VISIBLE);
                    spinnerStanGniazda.setAdapter(przygotujAdapter(R.array.stanGniazda));
                } else spinnerStanGniazda.setVisibility(View.GONE);
                setStanGniazda(switchStanGniazda.getTextOff().toString() + " - " + spinnerStanGniazda.getSelectedItem().toString());
                ;
            }
        });

        // Obsługa pola dot. obrączki
        switchObraczka.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switchObraczka.isChecked()) {
                    setObecnoscObraczki(switchObraczka.getTextOn().toString());
                } else {
                    setObecnoscObraczki(switchObraczka.getTextOff().toString());
                }
            }
        });

//        Wartość z pola uwagi
        edtUwagi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setUwagiGniazda("Brak uwag.");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setUwagiGniazda(edtUwagi.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnZrobZdjecia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent zrobZdjecieIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File plik = getZdjecie();
                zrobZdjecieIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(plik));
                if (zrobZdjecieIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(zrobZdjecieIntent, REQUEST_IMAGE_CAPTURE);
                }
                setZdjeciaGniazda(plik.getName().toString());
            }
        });

        // Przekazanie numeru karty obserwacji
        setKartaObserwacji(String.valueOf(kartaObserwacjiClass.dajNumerKarty()));

        btnDodajObserwacje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ustawUsytuowanieGniazda(spinnerUsytuowanie, spinnerUsytuowanie2, spinnerUsytuowanie3, spinnerUsytuowanie4, edtNazwaDrzewa);
                dodajNowaObserwacje();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String patch = "sdcard/MIBB-obrazy/mibb_image.jpg";
        imageZdjecie.setImageDrawable(Drawable.createFromPath(patch));
        imageZdjecie.setVisibility(View.VISIBLE);
    }

    public String dajWartoscSpinnera(Spinner spinner) {
        String wybranyElement = "";
        wybranyElement = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
        if (spinner.getVisibility() == 8) {
            wybranyElement = "";
        } else if (wybranyElement.contains(" ")) {
            wybranyElement = wybranyElement.substring(0, wybranyElement.indexOf(" "));
        }
        return wybranyElement;
    }

    public void ustawUsytuowanieGniazda(Spinner spr1, Spinner spr2, Spinner spr3, Spinner spr4, EditText nazwaGnz) {
        usytuowanieGniazdaAll = dajWartoscSpinnera(spr1) + " " + dajWartoscSpinnera(spr2) + dajWartoscSpinnera(spr3) + dajWartoscSpinnera(spr4) + " " + nazwaGnz.getText().toString();
    }

    public File getZdjecie() {
        File folder = new File("sdcard/MIBB-obrazy");

        if (!folder.exists()) {
            folder.mkdir();
        }
        File zdjeciePlik = new File(folder, "mibb_image.jpg");
        return zdjeciePlik;
    }

    public ArrayAdapter<CharSequence> przygotujAdapter(int typDancyh) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, typDancyh, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    @SuppressLint("MissingPermission")
    public void dajLokalizacje() {

        klientLokalizacji = LocationServices.getFusedLocationProviderClient(this);
        klientLokalizacji.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        //String wartosc = "";
                        Address lokal = null;
                        if (location != null) {
                            szerokosc = location.getLatitude();
                            wysokosc = location.getLongitude();
                            lokal = dajAdres(szerokosc, wysokosc);
                        } else {
                            szerokosc = 53.743705;
                            wysokosc = 20.455782;
                            Toast.makeText(getApplicationContext(), "Usługa lokalizacyjna chwilowo niedostępna.\n Spróbuj ponownie", Toast.LENGTH_LONG).show();
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
            Toast.makeText(this, "Usługa lokalizacyjna adresu niedostępna. \n" + e, Toast.LENGTH_LONG).show();
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

    public void dodajNowaObserwacje() {
        // Wysłanie danych na bazę

        setZdjeciaGniazda("test");
        ObslugaDB obslugaDB = new ObslugaDB(this);
        obslugaDB.execute("wyslijObserwacje", nazwaGniazda, lokalizacjaGniazda, usytuowanieGniazdaAll, platformaGniazda, efektLeguGniazda, stanGniazda, obecnoscObraczki, uwagiGniazda, zdjeciaGniazda, kartaObserwacji);

        // Restart aktywności
/*        Intent intentObserwacja = getIntent();
        finish();
        startActivity(intentObserwacja);*/
    }
}