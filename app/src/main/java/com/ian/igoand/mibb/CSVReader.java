package com.ian.igoand.mibb;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVReader {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void main(String sciezkaPliku) {
        long startTime = System.nanoTime();

        String[] odczyt = odczytajPlik(sciezkaPliku);

        long stopTime = System.nanoTime();
        System.out.print("Plik odczytany w czasie: " + (stopTime - startTime) / 1000000);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String[] odczytajPlik(String nazwaPliku) {


        // Tworzę obiekt typu Path
        Path sciezka = Paths.get(nazwaPliku);
        // Lista będzie przechowywała kolejne linie odczytane z pliku jako String
        ArrayList<String> odczyt = new ArrayList<>();
        try {
            // odczyt wszystkich linii pliku i umieszczenie w liscie
            odczyt = (ArrayList) Files.readAllLines(sciezka);
        } catch (IOException ex) {
            System.out.println("Brak pliku! Kod błędu: " + ex);
        }


        // Konwersja odczytanych łańcuchów znaków i umieszenie ich w tablicy

        // Tablica dla odczytanych danych - deklaracji ilości "kolumn"
        String[] daneOdczytane = new String[odczyt.size()];

        // Indeksator linni
        Integer nrLinii = 0;

        // Pobranie następujących linii z listy
        for (String linia : odczyt) {

            daneOdczytane[nrLinii] = linia;
            nrLinii++;
        }
        return daneOdczytane;
    }
}
