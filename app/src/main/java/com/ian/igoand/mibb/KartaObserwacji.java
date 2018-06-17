package com.ian.igoand.mibb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class KartaObserwacji extends AppCompatActivity {

    Operator operator = new Operator();
    PrzygotujDaneTERYT teryt = new PrzygotujDaneTERYT();

    // Deklaracja pól klasy
    String daneObserwatora;
    String emailObserwatora;
    String telefonOperatora;
    String gminaKarty;
    String powiatKarty;
    String wojewodztwoKarty;
    String numerKarty;

    // Deklaracja zmiennych obiektów GUI
    ObslugaDB obslugaDB;
    TextView viewDaneOperatora;
    TextView viewTelOperatora;
    TextView viewMailOperatora;
    AutoCompleteTextView edtWojewodztwo;
    AutoCompleteTextView edtPowiat;
    AutoCompleteTextView edtGmina;
    Button btnAnuluj;
    Button btnDodajObserwacje;
    TextView lblNumerKarty;
    ProgressBar progressBar;
    AlertDialog alertDialog;

    public String getNumerKarty() {
        return numerKarty;
    }

    public void setNumerKarty(String numerKarty) {
        this.numerKarty = numerKarty;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja_karty_obserwacji);

//        Pobranie daty systemowej oraz konwersja do odpowiedniego formatu
        final Context context = getApplicationContext();

//        Inicjalizacja pól do wyświetlenia
        viewDaneOperatora = findViewById(R.id.textDaneOperatora);
        viewTelOperatora = findViewById(R.id.textNrTel);
        viewMailOperatora = findViewById(R.id.textMail);
        edtWojewodztwo = findViewById(R.id.inputWojewodztwo);
        edtPowiat = findViewById(R.id.inputPowiat);
        edtGmina = findViewById(R.id.inputGmina);
        btnAnuluj = findViewById(R.id.btnAnuluj);
        btnDodajObserwacje = findViewById(R.id.btnDalej);
        lblNumerKarty = findViewById(R.id.lblNumerKarty);
        progressBar = findViewById(R.id.progressBar);
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Stetus operacji");

        obslugaDB = new ObslugaDB(this, progressBar);

//        Wyświeltenie zapisanych danych operatora na polach ekranu
        viewDaneOperatora.setText(operator.odczytajDaneOperatora(context, "imie").concat(" ")
                .concat(operator.odczytajDaneOperatora(context, "nazwisko")).concat(", ")
                .concat(operator.odczytajDaneOperatora(context, "ulica")).concat(" ")
                .concat(operator.odczytajDaneOperatora(context, "dom")).concat(" ")
                .concat(operator.odczytajDaneOperatora(context, "kod")).concat(" ")
                .concat(operator.odczytajDaneOperatora(context, "miejscowosc")));
        viewTelOperatora.setText(operator.odczytajDaneOperatora(context, "telefon"));
        viewMailOperatora.setText(operator.odczytajDaneOperatora(context, "email"));
        edtWojewodztwo.setText(operator.odczytajDaneOperatora(context, "wojewodztwo"));
        edtPowiat.setText(operator.odczytajDaneOperatora(context, "powiat"));
        edtGmina.setText(operator.odczytajDaneOperatora(context, "gminaKarty"));


        // Dodanie możliwości wprowadzania danych teryt
        try {
            teryt.szukajWojewodztwo(edtWojewodztwo, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            teryt.szukajPowiat(edtPowiat, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            teryt.szukajGmine(edtGmina, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Obsługa przycisków
        btnAnuluj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KartaObserwacji.this, Menu.class));
            }
        });

        btnDodajObserwacje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daneObserwatora = viewDaneOperatora.getText().toString();
                emailObserwatora = viewMailOperatora.getText().toString();
                telefonOperatora = viewTelOperatora.getText().toString();
                wojewodztwoKarty = edtWojewodztwo.getText().toString();
                powiatKarty = edtPowiat.getText().toString();
                gminaKarty = edtGmina.getText().toString();

                obslugaDB.execute("wyslijKarteObserwacji", daneObserwatora, emailObserwatora, telefonOperatora, wojewodztwoKarty, powiatKarty, gminaKarty);
                try {
                    setNumerKarty(String.valueOf(obslugaDB.get()));
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
//                alertDialog.setMessage("Dodano nową kartę obserwacji: " + getNumerKarty());
//                alertDialog.show();

                Toast.makeText(getApplicationContext(), "Dodano nową kartę obserwacji: " + numerKarty, Toast.LENGTH_LONG).show();

                // Restart aktywności
                Intent intentObserwacja = getIntent();
                finish();
                startActivity(intentObserwacja);

                Intent intentDodajObserwacjeDoKarty = new Intent(KartaObserwacji.this, Obserwacja.class);
                intentDodajObserwacjeDoKarty.putExtra("numerKarty", getNumerKarty());
                startActivity(intentDodajObserwacjeDoKarty);
                finish();
            }
        });
    }
}