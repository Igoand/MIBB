package com.ian.igoand.mibb;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Okhttp {
    private static final String TAG = Okhttp.class.getName();
    OkHttpClient okHttpClient;
    Request request;
    String url = "http://b22faa58.ngrok.io/";

    int sprawdzNumerKarty() {

        okHttpClient = new OkHttpClient();

        request = new Request.Builder().url(url + "restSprawdzKarte.php").build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            Log.i(TAG, response.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
