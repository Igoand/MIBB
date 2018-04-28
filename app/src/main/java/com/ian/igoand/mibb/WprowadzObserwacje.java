package com.ian.igoand.mibb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class WprowadzObserwacje extends AppCompatActivity {

    Operator operator = new Operator();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja_operacji);

        Toast toast = new Toast(this);
        String test = operator.odczytOperatora(getApplication().getApplicationContext());
        toast.makeText(getApplicationContext(), test, LENGTH_LONG).show();

    }
}