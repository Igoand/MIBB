package com.ian.igoand.mibb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    public String getNumerKarty() {
        return numerKarty;
    }

    public KartaObserwacji setNumerKarty(String numerKarty) {
        this.numerKarty = numerKarty;
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja_karty_obserwacji);

//        Pobranie daty systemowej oraz konwersja do odpowiedniego formatu
        Date data = Calendar.getInstance().getTime();
        SimpleDateFormat formatDaty = new SimpleDateFormat("dd-MM-yyyy");
        String sformatowanaData = formatDaty.format(data);
        final Context context = getApplicationContext();

//        Inicjalizacja pól do wyświetlenia
        viewDaneOperatora = (TextView) findViewById(R.id.textDaneOperatora);
        viewTelOperatora = (TextView) findViewById(R.id.textNrTel);
        viewMailOperatora = (TextView) findViewById(R.id.textMail);
        edtWojewodztwo = (AutoCompleteTextView) findViewById(R.id.inputWojewodztwo);
        edtPowiat = (AutoCompleteTextView) findViewById(R.id.inputPowiat);
        edtGmina = (AutoCompleteTextView) findViewById(R.id.inputGmina);
        btnAnuluj = (Button) findViewById(R.id.btnAnuluj);
        btnDodajObserwacje = (Button) findViewById(R.id.btnDalej);
        lblNumerKarty = findViewById(R.id.lblNumerKarty);
        progressBar = findViewById(R.id.progressBar);

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