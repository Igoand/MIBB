package com.ian.igoand.mibb;

import android.os.AsyncTask;

public class wyslijObserwacje extends AsyncTask<String, Void, String> {
    String adresSerwera = "https://a7fe4732.ngrok.io";

    @Override
    protected String doInBackground(String... strings) {
        String restInsertObserwacja_url = adresSerwera + "/restInsertObserwacja.php";


        return null;
    }
}
