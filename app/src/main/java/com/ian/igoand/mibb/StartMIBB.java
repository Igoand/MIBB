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

        teryt.pobierzDaneTeryt(this, "test");

        AutoCompleteTextView wprowadzWojewodztwo = (AutoCompleteTextView) findViewById(R.id.inputWojewodztwo);
        teryt.szukajWojewodztwo(wprowadzWojewodztwo, this, android.R.layout.simple_list_item_1);

        AutoCompleteTextView wprowadzPowiat = (AutoCompleteTextView) findViewById(R.id.inputPowiat);
        teryt.szukajPowiat(wprowadzPowiat, this, android.R.layout.simple_list_item_1);

        AutoCompleteTextView wprowadzGmine = (AutoCompleteTextView) findViewById(R.id.inputGmina);
        teryt.szukajGmine(wprowadzGmine, this, android.R.layout.simple_list_item_1);



        // Próba odczytania danych z CSV
        CSVReaderAlternative csvRead = new CSVReaderAlternative();


        int rawId = R.raw.miejscowosci_import_file;
        try {
            csvRead.odczytaneDane(this, rawId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}