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

    public String odczytPodstOperatora(Context context) {
        SharedPreferences daneOperatora = context.getSharedPreferences(plikOperatora, Activity.MODE_PRIVATE);
        String danePodstOperatora = "";
        danePodstOperatora = danePodstOperatora.concat(daneOperatora.getString("imie", "imie").concat(" ")
                .concat(daneOperatora.getString("nazwisko", "nazwisko")).concat(", ")
                .concat(daneOperatora.getString("ulica", "ulica")).concat(" ")
                .concat(daneOperatora.getString("dom", "dom")).concat(", "))
                .concat(daneOperatora.getString("kod", "kod").concat(", "))
                .concat(daneOperatora.getString("miejscowosc", "miejcowosc"));

        return danePodstOperatora;
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

    public String odczytTelOperatora(Context context) {
        SharedPreferences daneOperatora = context.getSharedPreferences(plikOperatora, Activity.MODE_PRIVATE);
        String telefon = "";
        telefon = telefon.concat(daneOperatora.getString("telefon", null));
        return telefon;
    }

    public String odczytMailOperatora(Context context) {
        SharedPreferences daneOperatora = context.getSharedPreferences(plikOperatora, Activity.MODE_PRIVATE);
        String mail = "";
        mail = mail.concat(daneOperatora.getString("email", null));
        return mail;
    }

    public String odczytWojOperatora(Context context) {
        SharedPreferences daneOperatora = context.getSharedPreferences(plikOperatora, Activity.MODE_PRIVATE);
        String wojewodztwo = "";
        wojewodztwo = wojewodztwo.concat(daneOperatora.getString("wojewodztwo", null));
        return wojewodztwo;
    }

    public String odczytPowOperatora(Context context) {
        SharedPreferences daneOperatora = context.getSharedPreferences(plikOperatora, Activity.MODE_PRIVATE);
        String powiat = "";
        powiat = powiat.concat(daneOperatora.getString("powiat", null));
        return powiat;
    }

    public String odczytGminOperatora(Context context) {
        SharedPreferences daneOperatora = context.getSharedPreferences(plikOperatora, Activity.MODE_PRIVATE);
        String gmina = "";
        gmina = gmina.concat(daneOperatora.getString("gmina", null));
        return gmina;
    }

    public String odczytMiejscOperatora(Context context) {
        SharedPreferences daneOperatora = context.getSharedPreferences(plikOperatora, Activity.MODE_PRIVATE);
        String miejscowosc = "";
        miejscowosc = miejscowosc.concat(daneOperatora.getString("miejscowosc", null));
        return miejscowosc;
    }


    public Boolean czyIstniejeOperator(Context context) {
        SharedPreferences daneOperatora = context.getSharedPreferences(plikOperatora, Activity.MODE_PRIVATE);
        String czyIstnieje;
        czyIstnieje = daneOperatora.getString("czyIstnieje", "false");
        return Boolean.parseBoolean(czyIstnieje);
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNumerTelefonu() {
        return telefon;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.telefon = numerTelefonu;
    }

    public String getAdresEmail() {
        return email;
    }

    public void setAdresEmail(String adresEmail) {
        this.email = adresEmail;
    }

    public String getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(String wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    public String getPowiat() {
        return powiat;
    }

    public void setPowiat(String powiat) {
        this.powiat = powiat;
    }

    public String getGmina() {
        return gmina;
    }

    public void setGmina(String gmina) {
        this.gmina = gmina;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(String nrDomu) {
        this.nrDomu = nrDomu;
    }


}
