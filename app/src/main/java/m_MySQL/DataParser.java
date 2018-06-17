package m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser extends AsyncTask<Void, Void, Boolean> {
    Context context;
    String jsonData, zakresDanych;
    ListView listView;
    ProgressDialog progressDialog;

    ArrayList<String> odkodowaneDaneLista = new ArrayList<>();

    public DataParser(Context context, String jsonData, ListView listView, String zakresDanych) {
        this.context = context;
        this.jsonData = jsonData;
        this.listView = listView;
        this.zakresDanych = zakresDanych;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Przetwarzanie");
        progressDialog.setMessage("Konwertuję dane.. Czekaj!");
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        progressDialog.dismiss();
        if (result) {
            ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, odkodowaneDaneLista);
            listView.setAdapter(adapter);
        }
    }

    private Boolean parseData() {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject;
            odkodowaneDaneLista.clear();
            if (zakresDanych.equals("kartaObserwacji")) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    String rekord = "";
                    rekord += "Lp:      " + dajWartosc("idKarty", jsonObject) + "\n";
                    rekord += "Obserwator   : " + dajWartosc("daneObserwatora", jsonObject) + "\n";
                    rekord += "Województwo: " + dajWartosc("wojewodztwo", jsonObject) + "\n";
                    rekord += "Powiat:  " + dajWartosc("powiat", jsonObject) + "\n";
                    rekord += "Gmina:   " + dajWartosc("gmina", jsonObject) + "\n";
                    rekord += "Data wprowadzenia karty: " + dajWartosc("dataRejestracjiKarty", jsonObject) + "\n";
                    rekord += "Numer Karty: " + dajWartosc("numerKarty", jsonObject);
                    odkodowaneDaneLista.add(rekord);
                }
            } else if (zakresDanych.equals("obserwacja")) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    String rekord = "";
                    rekord += "nazwaGniazda:      " + dajWartosc("nazwaGniazda", jsonObject) + "\n";
                    rekord += "Lokalizacja gniazda   : " + dajWartosc("lokalizacjaGniazda", jsonObject) + "\n";
                    rekord += "Usytuowanie gniazda: " + dajWartosc("usytuowanieGniazda", jsonObject) + "\n";
                    rekord += "Platfnorma:  " + dajWartosc("platformaGniazda", jsonObject) + "\n";
                    rekord += "Efekt lęgu:   " + dajWartosc("efektLegu", jsonObject) + "\n";
                    rekord += "Stan gniazda: " + dajWartosc("stanGniazda", jsonObject) + "\n";
                    rekord += "Obecność obrączki: " + dajWartosc("obecnoscObraczki", jsonObject) + "\n";
                    rekord += "Uwagi: " + dajWartosc("uwagi", jsonObject);
                    odkodowaneDaneLista.add(rekord);
                }
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
    private String dajWartosc(String kolumna, JSONObject j) throws JSONException {
        return j.getString(kolumna);
    }
}
