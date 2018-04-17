package com.ian.igoand.mibb;

import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class ObslugaPolWprowadzaniaDanych {
    public String pobierzStringPola(EditText pole) {
        String tekst = pole.getText().toString();
        return tekst;
    }

    public String pobierzStringPola(AutoCompleteTextView pole) {
        String tekst = pole.getText().toString();
        return tekst;

    }
}
