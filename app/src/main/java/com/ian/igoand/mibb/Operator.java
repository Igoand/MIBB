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

//        Zapisanie pól obiektu do pliku lokalnego pliku SharedPreferenes
        edytujDaneOperatora.commit();

        Log.i("succes", "Udało się zapisać dane");

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
