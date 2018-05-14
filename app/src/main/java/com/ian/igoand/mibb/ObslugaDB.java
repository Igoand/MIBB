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
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class ObslugaDB extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;

    public ObslugaDB(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String adresNGrok = "http://225d2e8c.ngrok.io";
        String usluga = params[0];
        String dodajOperacje_url = adresNGrok + "/statusDanychObserwacji.php";
        String restInsertObserwacja_url = adresNGrok + "/restInsertObserwacja.php";
        String restSprawdzGniazdo_url = adresNGrok + "/restSprawdzGniazdo.php";

        // Decyzja o wyborze usługi PHP
        if (usluga == "sprawdzNumerGniazda") {
            try {
                // Ustanowienie połączenia do bazy
                URL url = new URL(dodajOperacje_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                // Obsługa przekazywanych do usługi parametrów
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                // Parametry przekazane do PHP
                String post_data = URLEncoder.encode("nazwaTabeli", "UTF-8") + "=" + URLEncoder.encode(usluga, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                // Odczyt danych zwróconych przez PHP
                String result = "", line;

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (usluga == "wyslijObserwacje") {
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
                String post_data = URLEncoder.encode("nazwaGniazda", "UTF-8") + "=" + URLEncoder.encode((String) params[1], "UTF-8") + "&" +
                        URLEncoder.encode("lokalizacjaGniazda", "UTF-8") + "=" + URLEncoder.encode((String) params[2], "UTF-8") + "&" +
                        URLEncoder.encode("usytuowanieGniazda", "UTF-8") + "=" + URLEncoder.encode((String) params[3], "UTF-8") + "&" +
                        URLEncoder.encode("platformaGniazda", "UTF-8") + "=" + URLEncoder.encode((String) params[4], "UTF-8") + "&" +
                        URLEncoder.encode("efektLegu", "UTF-8") + "=" + URLEncoder.encode((String) params[5], "UTF-8") + "&" +
                        URLEncoder.encode("stanGniazda", "UTF-8") + "=" + URLEncoder.encode((String) params[6], "UTF-8") + "&" +
                        URLEncoder.encode("obecnoscObraczki", "UTF-8") + "=" + URLEncoder.encode((String) params[7], "UTF-8") + "&" +
                        URLEncoder.encode("uwagi", "UTF-8") + "=" + URLEncoder.encode((String) params[8], "UTF-8") + "&" +
                        URLEncoder.encode("idZdjecia", "UTF-8") + "=" + URLEncoder.encode((String) params[9], "UTF-8") + "&" +
                        URLEncoder.encode("fkKartaObserwacji", "UTF-8") + "=" + URLEncoder.encode((String) params[10], "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                // Odczyt danych zwróconych przez PHP
                String result = "", line;

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (usluga == "sprawdzNrGniazda"){
            przygotujPOST(restInsertObserwacja_url, "miejscowosc", params[1]);
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status połączenia");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    public String przygotujPOST(String urlUslugi, String zmiennaPHP, String... param) {
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
            post_data = dajURLenoder(zmiennaPHP, param[1]);

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            // Odczyt danych zwróconych przez PHP
            String result = "", line;

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return post_data;
    }

    public String dajURLenoder(String zmiennaPHP, String parametr) throws UnsupportedEncodingException {
        return URLEncoder.encode(zmiennaPHP, "UTF-8") + "=" + URLEncoder.encode(parametr, "UTF-8");
    }

    ;

}
