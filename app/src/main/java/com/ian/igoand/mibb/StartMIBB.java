package com.ian.igoand.mibb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class StartMIBB extends AppCompatActivity {

    //    Inicjalizacja obiektów
    Operator operator = new Operator();
    PrzygotujDaneTERYT teryt = new PrzygotujDaneTERYT();
    ObslugaPolWprowadzaniaDanych tekst = new ObslugaPolWprowadzaniaDanych();

    //    Deklaracja globalnych zmiennych
    EditText inputImie;
    EditText inputNazwisko;
    EditText inputEmail;
    EditText inputTelefon;
    EditText inputUlica;
    EditText inputKodPocztowy;
    EditText inputNrDomu;
    Button btnZapisz;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String statusOperatora = operator.czyIstniejeOperator(getApplication().getApplicationContext());
        if (statusOperatora == "true") {
            setContentView(R.layout.activity_start_mibb);
        } else {

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

            // Autocomplete z wyborem wojewodztwa
            final AutoCompleteTextView wprowadzWojewodztwo = findViewById(R.id.inputWojewodztwo);
            try {
                teryt.szukajWojewodztwo(wprowadzWojewodztwo, this, android.R.layout.simple_list_item_1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Autocomplete z wyborem powiatu
            final AutoCompleteTextView wprowadzPowiat = findViewById(R.id.inputPowiat);
            try {
                teryt.szukajPowiat(wprowadzPowiat, this, android.R.layout.simple_list_item_1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Autocomplete z wyborem gminy
            final AutoCompleteTextView wprowadzGmine = findViewById(R.id.inputGmina);
            try {
                teryt.szukajGmine(wprowadzGmine, this, android.R.layout.simple_list_item_1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Autocomplete z wyborem miejscowosci
            final AutoCompleteTextView wprowadzMiejscowosc = findViewById(R.id.inputMiejscowosc);
            try {
                teryt.szukajGmine(wprowadzMiejscowosc, this, android.R.layout.simple_list_item_1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Ustawinie akcji po przyciśnięciu buttona
//        Zapisanie wprowadzonych danych
            btnZapisz.setOnClickListener(new View.OnClickListener() {

                                             @Override
                                             public void onClick(View view) {
//                                             Wywołanie kolejno metod set do zapisu pól obiektu
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

//                                             Wywołanie funkcji zapisującej wprowadzone w formatce dane Operatora(pola obiektu operator) do pliku lokalnego SharedPreferences na urządzeniu
                                                 Toast toast = new Toast(getApplicationContext());
                                                 try {
                                                     operator.zapiszOperatora(getApplicationContext());
                                                     toast.makeText(getApplicationContext(), "Pomyślnie zapisano dane Operatora", Toast.LENGTH_LONG).show();
                                                 } catch (Error e) {
                                                     toast.makeText(getApplicationContext(), "Wystąpił problem z zapisem danych: " + e, Toast.LENGTH_LONG).show();
                                                 }
                                                 startActivity(new Intent(StartMIBB.this, com.ian.igoand.mibb.Menu.class));
                                             }
                                         }
            );
        }
    }
}