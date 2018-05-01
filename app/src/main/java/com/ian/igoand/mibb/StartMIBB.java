package com.ian.igoand.mibb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    //String imie, nazwisko, email, telefon, ulica, kodPocztowy, nrDomu, wojewodztwo, powiat, gmina, miejscowosc;
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
        setContentView(R.layout.activity_edycja_operatora_mibb);


        Boolean statusOperatora;
        statusOperatora = operator.czyIstniejeOperator(getApplicationContext());

        if (statusOperatora == false) {
            Log.w("Error", "Nie ma operatora - czyIstnieje: " + statusOperatora);

//            startActivity(new Intent(StartMIBB.this, Menu.class));

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

//                                             Wywołanie funkcji zapisującej wprowadzone w formatce dane Operatora(pola obiektu operator) do pliku lokalnego SharedPreferences na urządzeniu
                    Toast toast = new Toast(getApplicationContext());
                    try {
                        operator.zapiszOperatora(getApplicationContext());
                        toast.makeText(getApplicationContext(), "Pomyślnie zapisano dane Operatora", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(StartMIBB.this, com.ian.igoand.mibb.Menu.class));
                    } catch (Error e) {
                        toast.makeText(getApplicationContext(), "Wystąpił problem z zapisem danych: " + e, Toast.LENGTH_LONG).show();
                    } finally {
                        finish();
                    }
                }
            });
        } else {
            startActivity(new Intent(StartMIBB.this, Menu.class));
            Log.w("Error", "Operator istnieje - czyIstnieje: " + statusOperatora);
        }
    }
}