package com.ian.igoand.mibb;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class CSVReaderAlternative {
    public void main(Context kontekst, String sciezkaPliku) throws IOException {
        InputStream inputStream = kontekst.getResources().openRawResource(sciezkaPliku);
        odczytPliku(inputStream);
    }

    public static Set odczytPliku(InputStream inputStream) throws IOException {
        Set<String> odczytaneWiersze = new HashSet<>();
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputReader);
        int nrLinii=0;

        while(bufferedReader.readLine()!=null) {
            odczytaneWiersze.add(bufferedReader.readLine());
            nrLinii++;
        }

        return odczytaneWiersze;
    }
}
