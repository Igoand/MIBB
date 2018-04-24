package com.ian.igoand.mibb;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.io.IOException;
import java.util.ArrayList;

public class PrzygotujDaneTERYT {

    CSVReader czytajCSV = new CSVReader();

//    Funkcja wypełniająca pole AutoCompleteTextView wyszukanymi wartościami TERYTu
    void szukajWojewodztwo(AutoCompleteTextView obiekt, Context kontekst, int zrodlo) throws IOException {
        int rawIdWojewodztwa = R.raw.wojewodztwa_import_file;
        ArrayList<String> nazwyWojewodztwList = (ArrayList<String>) czytajCSV.odczytaneDane(kontekst, rawIdWojewodztwa);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(kontekst, zrodlo, nazwyWojewodztwList);

        obiekt.setAdapter(adapter);
        obiekt.setThreshold(2);
        obiekt.setAdapter(adapter);
    }

    //    Funkcja wypełniająca pole AutoCompleteTextView wyszukanymi wartościami TERYTu
    void szukajPowiat(AutoCompleteTextView obiekt, Context kontekst, int zrodlo) throws IOException {
        int rawIdPowiaty = R.raw.powiaty_import_file;
        ArrayList<String> nazwyPowiatow = (ArrayList<String>) czytajCSV.odczytaneDane(kontekst, rawIdPowiaty);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(kontekst, zrodlo, nazwyPowiatow);

        obiekt.setAdapter(adapter);
        obiekt.setThreshold(3);
        obiekt.setAdapter(adapter);
    }

    //    Funkcja wypełniająca pole AutoCompleteTextView wyszukanymi wartościami TERYTu
    void szukajGmine(AutoCompleteTextView obiekt, Context kontekst, int zrodlo) throws IOException {
        int rawIdGminy = R.raw.gminy_import_file;
        ArrayList<String> nazwyGmin = (ArrayList<String>) czytajCSV.odczytaneDane(kontekst, rawIdGminy);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(kontekst, zrodlo, nazwyGmin);

        obiekt.setAdapter(adapter);
        obiekt.setThreshold(3);
        obiekt.setAdapter(adapter);
    }

    //    Funkcja wypełniająca pole AutoCompleteTextView wyszukanymi wartościami TERYTu
    void szukajMiejscowosc(AutoCompleteTextView obiekt, Context kontekst, int zrodlo) throws IOException {
        int rawIdMiejscowosci = R.raw.miejscowosci_import_file;
        ArrayList<String> nazwyMiejscowosci = (ArrayList<String>) czytajCSV.odczytaneDane(kontekst, rawIdMiejscowosci);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(kontekst, zrodlo, nazwyMiejscowosci);

        obiekt.setAdapter(adapter);
        obiekt.setThreshold(5);
        obiekt.setAdapter(adapter);
    }
}