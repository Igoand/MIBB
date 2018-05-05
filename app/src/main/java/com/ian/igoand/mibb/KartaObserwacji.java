package com.ian.igoand.mibb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class KartaObserwacji extends AppCompatActivity {

    Operator operator = new Operator();
    PrzygotujDaneTERYT teryt = new PrzygotujDaneTERYT();

    TextView viewDaneOperatora;
    TextView viewTelOperatora;
    TextView viewMailOperatora;
    AutoCompleteTextView edtWojewodztwo;
    AutoCompleteTextView edtPowiat;
    AutoCompleteTextView edtGmina;
    EditText inpData;
    Button btnAnuluj;
    Button btnDodajObserwacje;
    int numerKarty;
    int numerGniazda;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja_karty_obserwacji);
        numerKarty = 0;

//        Pobranie daty systemowej oraz konwersja do odpowiedniego formatu
        Date data = Calendar.getInstance().getTime();
        SimpleDateFormat formatDaty = new SimpleDateFormat("dd-MM-yyyy");
        String sformatowanaData = formatDaty.format(data);
        Context context = getApplicationContext();


//        Inicjalizacja pól do wyświetlenia
        viewDaneOperatora = (TextView) findViewById(R.id.textDaneOperatora);
        viewTelOperatora = (TextView) findViewById(R.id.textNrTel);
        viewMailOperatora = (TextView) findViewById(R.id.textMail);
        edtWojewodztwo = (AutoCompleteTextView) findViewById(R.id.inputWojewodztwo);
        edtPowiat = (AutoCompleteTextView) findViewById(R.id.inputPowiat);
        edtGmina = (AutoCompleteTextView) findViewById(R.id.inputGmina);
        inpData = (EditText) findViewById(R.id.inputData);
        btnAnuluj = (Button) findViewById(R.id.btnAnuluj);
        btnDodajObserwacje = (Button) findViewById(R.id.btnDalej);


//        Wyświeltenie zapisanych danych operatora na polach ekranu
        viewDaneOperatora.setText(operator.odczytajDaneOperatora(context, "imie").concat(" ")
                .concat(operator.odczytajDaneOperatora(context, "nazwisko")).concat(",   ")
                .concat(operator.odczytajDaneOperatora(context, "ulica")).concat(" ")
                .concat(operator.odczytajDaneOperatora(context, "dom")).concat(" ")
                .concat(operator.odczytajDaneOperatora(context, "kod")).concat(" ")
                .concat(operator.odczytajDaneOperatora(context, "miejscowosc")));
        viewTelOperatora.setText(operator.odczytajDaneOperatora(context, "telefon"));
        viewMailOperatora.setText(operator.odczytajDaneOperatora(context, "email"));
        edtWojewodztwo.setText(operator.odczytajDaneOperatora(context, "wojewodztwo"));
        edtPowiat.setText(operator.odczytajDaneOperatora(context, "powiat"));
        edtGmina.setText(operator.odczytajDaneOperatora(context, "gmina"));
        inpData.setText(sformatowanaData);


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
                startActivity(new Intent(KartaObserwacji.this, Obserwacja.class));
                numerKarty++;
            }
        });
    }

    public String dajNazweGniazda(String miejscowosc) {
        String nazwaGniazda = "";
        nazwaGniazda = nazwaGniazda.concat(miejscowosc + numerGniazda);
        return nazwaGniazda;
    }

    public void zwiekszNumerGniazda() {
        numerGniazda++;
    }
}