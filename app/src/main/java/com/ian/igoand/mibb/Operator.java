package com.ian.igoand.mibb;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Operator {
    // Deklaracja pól obiektu
    String imie;
    String nazwisko;
    String numerTelefonu;
    String adresEmail;
    String wojewodztwo;
    String powiat;
    String gmina;
    String miejscowosc;
    String ulica;
    String kodPocztowy;
    String nrDomu;
    Boolean czyIstnieje;

    String plikOperatora = "DaneOperatora";

    public void zapiszOperatora(Context context) {
        SharedPreferences daneOperatora = context.getSharedPreferences(plikOperatora, Activity.MODE_PRIVATE);

        SharedPreferences.Editor edytujDaneOperatora = daneOperatora.edit();
        edytujDaneOperatora.clear();
        edytujDaneOperatora.putString("imie", imie);
        edytujDaneOperatora.putString("nazwisko", nazwisko);
        edytujDaneOperatora.putString("email", adresEmail);
        edytujDaneOperatora.putString("telefon", numerTelefonu);
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

    public String odczytOperatora(Context context) {

        SharedPreferences daneOperatora = context.getSharedPreferences(plikOperatora, Activity.MODE_PRIVATE);

        String danePodstOperatora = "";
        danePodstOperatora.concat(daneOperatora.getString("imie", null).concat(" ")
                .concat(daneOperatora.getString("nazwisko", null)).concat(", ")
                .concat(daneOperatora.getString("ulica", null)).concat(" ")
                .concat(daneOperatora.getString("dom", null)).concat(", "))
                .concat(daneOperatora.getString("kod", null).concat(", "))
                .concat(daneOperatora.getString("miejscowosc", null));

        String mail = "";
        mail.concat(daneOperatora.getString("email", null));

        String telefon = "";
        telefon.concat(daneOperatora.getString("telefon", null));

        String czyIstnieje = "false";
        czyIstnieje = daneOperatora.getString("czyIstnieje", null);

        return czyIstnieje;
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
        return numerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public String getAdresEmail() {
        return adresEmail;
    }

    public void setAdresEmail(String adresEmail) {
        this.adresEmail = adresEmail;
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
