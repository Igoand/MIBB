package com.ian.igoand.mibb;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.URLEncoder.encode;
public class ObslugaDB extends AsyncTask<String, Integer, String> {
     Context context;
     ProgressBar pb;
     AlertDialog alertDialog;
     String wynik = "";

    ObslugaDB(Context ctx, ProgressBar progressBar) {
        this.context = ctx;
        this.pb = progressBar;
    }

    @Override
    protected String doInBackground(String... params) {
        String adresSerwera = "https://a491e0ba.ngrok.io";
        String usluga = params[0];
        //String dodajOperacje_url = adresSerwera + "/statusDanychObserwacji.php";
        String restInsertObserwacja_url = adresSerwera + "/restInsertObserwacja.php";
        String restSprawdzGniazdo_url = adresSerwera + "/restSprawdzGniazdo.php";
        String restInsertKartaObserwacji_url = adresSerwera + "/restInsertKartaObserwacji.php";
        String restSprawdzKarte_url = adresSerwera + "/restSprawdzKarte.php";
        String restSelectObserwacja = adresSerwera + "/restSelectObserwacja.php";
        publishProgress(10);
        // Decyzja o wyborze usługi PHP
        switch (usluga) {
            case "wyslijObserwacje":
                publishProgress(20);
                try {
                    URL url = new URL(restInsertObserwacja_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    publishProgress(30);
                    // Obsługa przekazywanych do usługi parametrów
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    publishProgress(40);
                    // Parametry przekazane do PHP
                    String post_data = encode("nazwaGniazda", "UTF-8") + "=" + encode(params[1], "UTF-8") + "&" +
                            encode("lokalizacjaGniazda", "UTF-8") + "=" + encode(params[2], "UTF-8") + "&" +
                            encode("usytuowanieGniazda", "UTF-8") + "=" + encode(params[3], "UTF-8") + "&" +
                            encode("platformaGniazda", "UTF-8") + "=" + encode(params[4], "UTF-8") + "&" +
                            encode("efektLegu", "UTF-8") + "=" + encode(params[5], "UTF-8") + "&" +
                            encode("stanGniazda", "UTF-8") + "=" + encode(params[6], "UTF-8") + "&" +
                            encode("obecnoscObraczki", "UTF-8") + "=" + encode(params[7], "UTF-8") + "&" +
                            encode("uwagi", "UTF-8") + "=" + encode(params[8], "UTF-8") + "&" +
                            encode("idZdjecia", "UTF-8") + "=" + encode(params[9], "UTF-8") + "&" +
                            encode("fkKartaObserwacji", "UTF-8") + "=" + encode(params[10], "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    publishProgress(80);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                    // Odczyt danych zwróconych przez PHP
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    publishProgress(100);
                    return result.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "sprawdzNrGniazda":
                publishProgress(20);
                wynik = przygotujPOST(restSprawdzGniazdo_url, "miejscowosc", params[1]);
                publishProgress(80);
                return wynik;

            case "wyslijKarteObserwacji":
                try {
                    publishProgress(20);
                    URL url = new URL(restInsertKartaObserwacji_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    publishProgress(30);
                    // Obsługa przekazywanych do usługi parametrów
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    publishProgress(40);
                    // Parametry przekazane do PHP
                    String post_data = dajURLenoder("obserwator", params[1]) + "&" +
                            dajURLenoder("email", params[2]) + "&" +
                            dajURLenoder("telefon", params[3]) + "&" +
                            dajURLenoder("wojewodztwo", params[4]) + "&" +
                            dajURLenoder("powiat", params[5]) + "&" +
                            dajURLenoder("gmina", params[6]);
                    publishProgress(50);
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    publishProgress(60);
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                    // Odczyt danych zwróconych przez PHP
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                    publishProgress(80);
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    publishProgress(100);
                    return result.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "sprawdzNrKartyObserwacji":

                publishProgress(20);
                wynik = przygotujPOST(restSprawdzKarte_url, "gminaKarty", params[1]);
                publishProgress(80);
                return wynik;

            case "wyszukajObserwacje":
                publishProgress(20);
                wynik = przygotujPOST(restSelectObserwacja, "idKartaObserwacji", params[1]);
                publishProgress(80);
                return wynik;
        }
        return wynik;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status połączenia");
        pb.setMax(100);
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage("Pobrano dane");
        //alertDialog.show();
        pb.setVisibility(View.GONE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        pb.setProgress(values[0]);
    }

    private String przygotujPOST(String urlUslugi, String zmiennaPHP, String... param) {
        publishProgress(40);
        String post_data;
        try {
            URL url = new URL(urlUslugi);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            // Obsługa przekazywanych do usługi parametrów
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            publishProgress(60);
            // Parametry przekazane do PHP
            post_data = dajURLenoder(zmiennaPHP, param[0]);

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            publishProgress(80);
            // Odczyt danych zwróconych przez PHP
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            publishProgress(90);
            return result.toString();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private String dajURLenoder(String zmiennaPHP, String parametr) throws UnsupportedEncodingException {
        return encode(zmiennaPHP, "UTF-8") + "=" + encode(parametr, "UTF-8");
    }
}
