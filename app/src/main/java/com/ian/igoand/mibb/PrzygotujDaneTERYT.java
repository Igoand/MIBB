package com.ian.igoand.mibb;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class PrzygotujDaneTERYT {


    void pobierzDaneTeryt(Context kontekst, String typDanychTeryt){
        DatabaseHelper mibbDB = new DatabaseHelper(kontekst);

    }

    void szukajWojewodztwo(AutoCompleteTextView obiekt, Context kontekst, int zrodlo){
        String[] nazwyWojewodztwList = {"test"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(kontekst, zrodlo, nazwyWojewodztwList);

        obiekt.setAdapter(adapter);
        obiekt.setThreshold(2);
        obiekt.setAdapter(adapter);
    }

    void szukajPowiat(AutoCompleteTextView obiekt, Context kontekst, int zrodlo){
        String[] nazwyPowiatow = {"test"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(kontekst, zrodlo, nazwyPowiatow);

        obiekt.setAdapter(adapter);
        obiekt.setThreshold(3);
        obiekt.setAdapter(adapter);
    }

    void szukajGmine(AutoCompleteTextView obiekt, Context kontekst, int zrodlo){
        String[] nazwyGmin = {"test"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(kontekst, zrodlo, nazwyGmin);

        obiekt.setAdapter(adapter);
        obiekt.setThreshold(4);
        obiekt.setAdapter(adapter);
    }
}
