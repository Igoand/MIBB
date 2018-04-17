package com.ian.igoand.mibb;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CSVReader {
    public List<String> odczytaneDane(Context kontekst, int rawId) throws IOException {
        InputStream inputStream = kontekst.getResources().openRawResource(rawId);


        List<String> odczytaneWiersze = new ArrayList<String>();
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputReader);
        int nrLinii=0;


        while(bufferedReader.ready()) {
            String linia = bufferedReader.readLine();
            odczytaneWiersze.add(linia);
            nrLinii++;
            //System.out.println(nrLinii);
        }
        return odczytaneWiersze;
    }
}