package com.ian.igoand.mibb;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

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

import okhttp3.OkHttpClient;

import static java.net.URLEncoder.encode;

public class ObslugaDB extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;
    String wynik = "";
    OkHttpClient okHttpClient = new OkHttpClient();

    ObslugaDB(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String adresSerwera = "https://b22faa58.ngrok.io";
        String usluga = params[0];
        //String dodajOperacje_url = adresSerwera + "/statusDanychObserwacji.php";
        String restInsertObserwacja_url = adresSerwera + "/restInsertObserwacja.php";
        String restSprawdzGniazdo_url = adresSerwera + "/restSprawdzGniazdo.php";
        String restInsertKartaObserwacji_url = adresSerwera + "/restInsertKartaObserwacji.php";
        String restSprawdzKarte_url = adresSerwera + "/restSprawdzKarte.php";

        // Decyzja o wyborze usługi PHP
        if (usluga.equals("wyslijObserwacje")) {
            try {
                URL url = new URL(restInsertObserwacja_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                // Obsługa przekazywanych do usługi parametrów
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

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

                return result.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (usluga.equals("sprawdzNrGniazda")) {
            wynik = przygotujPOST(restSprawdzGniazdo_url, "miejscowosc", params[1]);
            return wynik;
        } else if (usluga.equals("wyslijKarteObserwacji")) {
            try {
                URL url = new URL(restInsertKartaObserwacji_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                // Obsługa przekazywanych do usługi parametrów
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                // Parametry przekazane do PHP
                String post_data = dajURLenoder("obserwator", params[1]) + "&" +
                        dajURLenoder("email", params[2]) + "&" +
                        dajURLenoder("telefon", params[3]) + "&" +
                        dajURLenoder("wojewodztwo", params[4]) + "&" +
                        dajURLenoder("powiat", params[5]) + "&" +
                        dajURLenoder("gmina", params[6]) + "&" +
                        dajURLenoder("data_obserwacji", params[7]);

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

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

                return result.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (usluga.equals("sprawdzNrKartyObserwacji")) {
            wynik = przygotujPOST(restSprawdzKarte_url, "gminaKarty", params[1]);
            return wynik;
        }
        return wynik;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status połączenia");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        //alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    private String przygotujPOST(String urlUslugi, String zmiennaPHP, String... param) {
        String post_data = "";
        try {
            URL url = new URL(urlUslugi);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            // Obsługa przekazywanych do usługi parametrów
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));


            // Parametry przekazane do PHP
            post_data = dajURLenoder(zmiennaPHP, param[0]);

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();

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
