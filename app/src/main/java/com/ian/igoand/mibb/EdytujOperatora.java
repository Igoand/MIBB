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
import android.widget.Toast;

import java.io.IOException;

public class EdytujOperatora extends AppCompatActivity {

    EditText inputImie;
    EditText inputNazwisko;
    EditText inputEmail;
    EditText inputTelefon;
    EditText inputUlica;
    EditText inputKodPocztowy;
    EditText inputNrDomu;
    AutoCompleteTextView wprowadzWojewodztwo;
    AutoCompleteTextView wprowadzPowiat;
    AutoCompleteTextView wprowadzGmine;
    AutoCompleteTextView wprowadzMiejscowosc;
    Button btnZapisz;

    Operator operator = new Operator();
    PrzygotujDaneTERYT teryt = new PrzygotujDaneTERYT();
    ObslugaPolWprowadzaniaDanych tekst = new ObslugaPolWprowadzaniaDanych();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edycja_operatora_mibb);

        //Inicjalizacja zmiennych
        inputImie = findViewById(R.id.inputImie);
        inputNazwisko = findViewById(R.id.inputNazwisko);
        inputEmail = findViewById(R.id.inputEmail);
        inputTelefon = findViewById(R.id.inputTelefon);
        inputUlica = findViewById(R.id.inputUlica);
        inputKodPocztowy = findViewById(R.id.inputKodPocztowy);
        inputNrDomu = findViewById(R.id.inputNrDomu);
        btnZapisz = findViewById(R.id.buttonZapisz);
        wprowadzWojewodztwo = findViewById(R.id.inputWojewodztwo);
        wprowadzPowiat = findViewById(R.id.inputPowiat);
        wprowadzGmine = findViewById(R.id.inputGmina);
        wprowadzMiejscowosc = findViewById(R.id.inputMiejscowosc);

//        Wyświetlenie danych operatora w formatce
        final Context context = getApplicationContext();

        inputImie.setText(operator.odczytajDaneOperatora(context, "imie"));
        inputNazwisko.setText(operator.odczytajDaneOperatora(context, "nazwisko"));
        inputEmail.setText(operator.odczytajDaneOperatora(context, "email"));
        inputTelefon.setText(operator.odczytajDaneOperatora(context, "telefon"));
        wprowadzWojewodztwo.setText(operator.odczytajDaneOperatora(context, "wojewodztwo"));
        wprowadzPowiat.setText(operator.odczytajDaneOperatora(context, "powiat"));
        wprowadzGmine.setText(operator.odczytajDaneOperatora(context, "gminaKarty"));
        wprowadzMiejscowosc.setText(operator.odczytajDaneOperatora(context, "miejscowosc"));
        inputUlica.setText(operator.odczytajDaneOperatora(context, "ulica"));
        inputKodPocztowy.setText(operator.odczytajDaneOperatora(context, "kod"));
        inputNrDomu.setText(operator.odczytajDaneOperatora(context, "dom"));

        // Autocomplete z wyborem wojewodztwa
        try {
            teryt.szukajWojewodztwo(wprowadzWojewodztwo, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Autocomplete z wyborem powiatu
        try {
            teryt.szukajPowiat(wprowadzPowiat, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Autocomplete z wyborem gminy
        try {
            teryt.szukajGmine(wprowadzGmine, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Autocomplete z wyborem miejscowosci
        try {
            teryt.szukajGmine(wprowadzMiejscowosc, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnZapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator.imie = tekst.pobierzStringPola(inputImie);
                operator.nazwisko = tekst.pobierzStringPola(inputNazwisko);
                operator.email = tekst.pobierzStringPola(inputEmail);
                operator.telefon = tekst.pobierzStringPola(inputTelefon);
                operator.wojewodztwo = tekst.pobierzStringPola(wprowadzWojewodztwo);
                operator.powiat = tekst.pobierzStringPola(wprowadzPowiat);
                operator.gmina = tekst.pobierzStringPola(wprowadzGmine);
                operator.miejscowosc = tekst.pobierzStringPola(wprowadzMiejscowosc);
                operator.ulica = tekst.pobierzStringPola(inputUlica);
                operator.kodPocztowy = tekst.pobierzStringPola(inputKodPocztowy);
                operator.nrDomu = tekst.pobierzStringPola(inputNrDomu);

                operator.zapiszOperatora(context);
                Toast.makeText(getApplicationContext(), "Pomyślnie zmieniono dane Operatora", Toast.LENGTH_LONG).show();
                startActivity(new Intent(EdytujOperatora.this, com.ian.igoand.mibb.Menu.class));
            }
        });
    }
}
