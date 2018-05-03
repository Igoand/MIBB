package com.ian.igoand.mibb;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Operator {

    // Deklaracja pól obiektu
    String imie;
    String nazwisko;
    String telefon;
    String email;
    String wojewodztwo;
    String powiat;
    String gmina;
    String miejscowosc;
    String ulica;
    String kodPocztowy;
    String nrDomu;

    // Nazwa pliku SharedPreferences
    String plikOperatora = "DaneOperatora";

    public void zapiszOperatora(Context context) {
        SharedPreferences daneOperatora = context.getSharedPreferences(plikOperatora, Activity.MODE_PRIVATE);

        SharedPreferences.Editor edytujDaneOperatora = daneOperatora.edit();
        edytujDaneOperatora.clear();
        edytujDaneOperatora.putString("imie", imie);
        edytujDaneOperatora.putString("nazwisko", nazwisko);
        edytujDaneOperatora.putString("email", email);
        edytujDaneOperatora.putString("telefon", telefon);
        edytujDaneOperatora.putString("wojewodztwo", wojewodztwo);
        edytujDaneOperatora.putString("powiat", powiat);
        edytujDaneOperatora.putString("gmina", gmina);
        edytujDaneOperatora.putString("miejscowosc", miejscowosc);
        edytujDaneOperatora.putString("ulica", ulica);
        edytujDaneOperatora.putString("kod", kodPocztowy);
        edytujDaneOperatora.putString("dom", nrDomu);
        edytujDaneOperatora.putString("czyIstnieje", "true");

//        Zapisanie pól obiektu do pliku lokalnego pliku SharedPreferenes
        edytujDaneOperatora.commit();

        Log.i("succes", "Udało się zapisać dane");
    }

    public String odczytajDaneOperatora(Context context, String klucz) {
        String wartosc = "";
        try {
            SharedPreferences daneOperatora = context.getSharedPreferences(plikOperatora, Activity.MODE_PRIVATE);

            switch (klucz) {
                case "imie":
                    wartosc = daneOperatora.getString(klucz, null);
                    break;
                case "nazwisko":
                    wartosc = daneOperatora.getString(klucz, null);
                    break;
                case "email":
                    wartosc = daneOperatora.getString(klucz, null);
                    break;
                case "telefon":
                    wartosc = daneOperatora.getString(klucz, null);
                    break;
                case "wojewodztwo":
                    wartosc = daneOperatora.getString(klucz, null);
                    break;
                case "powiat":
                    wartosc = daneOperatora.getString(klucz, null);
                    break;
                case "gmina":
                    wartosc = daneOperatora.getString(klucz, null);
                    break;
                case "miejscowosc":
                    wartosc = daneOperatora.getString(klucz, null);
                    break;
                case "ulica":
                    wartosc = daneOperatora.getString(klucz, null);
                    break;
                case "kod":
                    wartosc = daneOperatora.getString(klucz, null);
                    break;
                case "dom":
                    wartosc = daneOperatora.getString(klucz, null);
                    break;
                case "czyIstnieje":
                    wartosc = daneOperatora.getString(klucz, null);
                    break;
                default:
                    wartosc = "Przekazano zły parametr dla odczytu danych operatora";
            }
        } catch (Error e) {
            return "Wystąpił problem z odczytem danych " + e;
        } finally {
            return wartosc;
        }
    }
}
