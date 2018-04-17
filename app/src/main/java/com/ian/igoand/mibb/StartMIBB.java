package com.ian.igoand.mibb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class StartMIBB extends AppCompatActivity {

    Operator operator = new Operator();
    PrzygotujDaneTERYT teryt = new PrzygotujDaneTERYT();
    ObslugaPolWprowadzaniaDanych tekst = new ObslugaPolWprowadzaniaDanych();

    //    Deklaracja globalnych zmiennych
    EditText inputImie;
    EditText inputNazwisko;
    EditText inputEmail;
    EditText inputTelefon;
    AutoCompleteTextView inputWojewodztwo;
    AutoCompleteTextView inputPowiat;
    AutoCompleteTextView inputGmina;
    AutoCompleteTextView inputMiejscowosc;
    EditText inputUlica;
    EditText inputKodPocztowy;
    EditText inputNrDomu;
    Button btnZapisz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_mibb);

        //Inicjalizacja zmiennych
        inputImie = (EditText) findViewById(R.id.inputImie);
        inputNazwisko = (EditText) findViewById(R.id.inputNazwisko);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputTelefon = (EditText) findViewById(R.id.inputTelefon);
        inputUlica = (EditText) findViewById(R.id.inputUlica);
        inputKodPocztowy = (EditText) findViewById(R.id.inputKodPocztowy);
        inputNrDomu = (EditText) findViewById(R.id.inputNrDomu);
        btnZapisz = findViewById(R.id.buttonZapisz);


        // Autocomplete z wyborem wojewodztwa
        final AutoCompleteTextView wprowadzWojewodztwo = (AutoCompleteTextView) findViewById(R.id.inputWojewodztwo);
        try {
            teryt.szukajWojewodztwo(wprowadzWojewodztwo, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Autocomplete z wyborem powiatu
        final AutoCompleteTextView wprowadzPowiat = (AutoCompleteTextView) findViewById(R.id.inputPowiat);
        try {
            teryt.szukajPowiat(wprowadzPowiat, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Autocomplete z wyborem gminy
        final AutoCompleteTextView wprowadzGmine = (AutoCompleteTextView) findViewById(R.id.inputGmina);
        try {
            teryt.szukajGmine(wprowadzGmine, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Autocomplete z wyborem miejscowosci
        final AutoCompleteTextView wprowadzMiejscowosc = (AutoCompleteTextView) findViewById(R.id.inputMiejscowosc);
        try {
            teryt.szukajGmine(wprowadzMiejscowosc, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Ustawinie akcji przy przyciśnięciu buttona
        btnZapisz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                operator.setImie(tekst.pobierzStringPola(inputImie));
                operator.setNazwisko(tekst.pobierzStringPola(inputNazwisko));
                operator.setAdresEmail(tekst.pobierzStringPola(inputEmail));
                operator.setNumerTelefonu(tekst.pobierzStringPola(inputTelefon));
                operator.setWojewodztwo(tekst.pobierzStringPola(wprowadzWojewodztwo));
                operator.setPowiat(tekst.pobierzStringPola(wprowadzPowiat));
                operator.setGmina(tekst.pobierzStringPola(wprowadzGmine));
                operator.setMiejscowosc(tekst.pobierzStringPola(wprowadzMiejscowosc));
                operator.setUlica(tekst.pobierzStringPola(inputUlica));
                operator.setKodPocztowy(tekst.pobierzStringPola(inputKodPocztowy));
                operator.setNrDomu(tekst.pobierzStringPola(inputNrDomu));

                operator.zapiszOperatora(getApplicationContext());
            }
        });
    }
}