package com.ian.igoand.mibb;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_mibb);

        teryt.pobierzDaneTeryt(this, "test");

        AutoCompleteTextView wprowadzWojewodztwo = (AutoCompleteTextView) findViewById(R.id.inputWojewodztwo);
        teryt.szukajWojewodztwo(wprowadzWojewodztwo, this, android.R.layout.simple_list_item_1);

        AutoCompleteTextView wprowadzPowiat = (AutoCompleteTextView) findViewById(R.id.inputPowiat);
        teryt.szukajPowiat(wprowadzPowiat, this, android.R.layout.simple_list_item_1);

        AutoCompleteTextView wprowadzGmine = (AutoCompleteTextView) findViewById(R.id.inputGmina);
        teryt.szukajGmine(wprowadzGmine, this, android.R.layout.simple_list_item_1);


        CSVReaderAlternative csvRead = new CSVReaderAlternative();

        try {
            csvRead.main(this, "R.raw.miejscowosci_import_file");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}