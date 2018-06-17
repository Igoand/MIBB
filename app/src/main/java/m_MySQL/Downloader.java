package m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Downloader extends AsyncTask<Void, Void, String> {
    Context context;
    String urlAddress;
    ListView listView;
    ProgressDialog progressDialog;

    public Downloader(Context context, String urlAddress, ListView listView) {
        this.context = context;
        this.urlAddress = urlAddress;
        this.listView = listView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Odczyt danych");
        progressDialog.setMessage("Pobieram.. Proszę czekać");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);
        progressDialog.dismiss();
        if (jsonData.startsWith("Error")) {
            Toast.makeText(context, "Niepowodzenie! dane nie zostały pobrane\n" + jsonData, Toast.LENGTH_LONG).show();
        }
        else{
            new DataParser(context, jsonData, listView, "kartaObserwacji").execute();
        }
    }

    private String downloadData() {
        Object connection = Connector.connect(urlAddress);
        if (connection.toString().startsWith("Error")) {
            return connection.toString();
        }
        try {
            HttpURLConnection con = (HttpURLConnection) connection;
            InputStream inputStream = new BufferedInputStream((con.getInputStream()));
            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(inputStream)));

            String line;
            StringBuffer jsonData = new StringBuffer();

            while ((line = bufferedReader.readLine()) != null) {
                jsonData.append(line).append("\n");
            }

            bufferedReader.close();
            inputStream.close();

            return jsonData.toString();

        } catch (IOException e) {
            return "Error " + e.getMessage();
        }
    }
}
