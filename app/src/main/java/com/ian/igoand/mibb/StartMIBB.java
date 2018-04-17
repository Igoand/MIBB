package com.ian.igoand.mibb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.io.IOException;

public class StartMIBB extends AppCompatActivity {

    PrzygotujDaneTERYT teryt = new PrzygotujDaneTERYT();

    //    Deklaracja globalnych zmiennych
    EditText inputImie;
    EditText inputNazwisko;
    EditText inputEmail;
    EditText inputTelefon;
    AutoCompleteTextView inputWojewodztwo;
    AutoCompleteTextView inputPowiat;
    AutoCompleteTextView inputGmina;
    AutoCompleteTextView inputMiejscowosc;
    EditText inputKodPocztowy;
    EditText inputNrDomu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_mibb);


        // Autocomplete z wyborem wojewodztwa
        AutoCompleteTextView wprowadzWojewodztwo = (AutoCompleteTextView) findViewById(R.id.inputWojewodztwo);
        try {
            teryt.szukajWojewodztwo(wprowadzWojewodztwo, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AutoCompleteTextView wprowadzPowiat = (AutoCompleteTextView) findViewById(R.id.inputPowiat);
        try {
            teryt.szukajPowiat(wprowadzPowiat, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AutoCompleteTextView wprowadzGmine = (AutoCompleteTextView) findViewById(R.id.inputGmina);
        try {
            teryt.szukajGmine(wprowadzGmine, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AutoCompleteTextView wprowadzMiejscowosc = (AutoCompleteTextView) findViewById(R.id.inputMiejscowosc);
        try {
            teryt.szukajGmine(wprowadzMiejscowosc, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}