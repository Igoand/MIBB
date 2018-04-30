package com.ian.igoand.mibb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.io.IOException;

public class WprowadzObserwacje extends AppCompatActivity {

    Operator operator = new Operator();
    PrzygotujDaneTERYT teryt = new PrzygotujDaneTERYT();
    TextView viewDaneOperatora;
    TextView viewTelOperatora;
    TextView viewMailOperatora;
    AutoCompleteTextView edtWojewodztwo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja_operacji);

        viewDaneOperatora = (TextView) findViewById(R.id.textDaneOperatora);
        viewTelOperatora = (TextView) findViewById(R.id.textNrTel);
        viewMailOperatora = (TextView) findViewById(R.id.textMail);
        edtWojewodztwo = (AutoCompleteTextView) findViewById(R.id.inputWojewodztwo);
        viewDaneOperatora.setText(operator.odczytPodstOperatora((getApplication().getApplicationContext())));
        viewTelOperatora.setText(operator.odczytTelOperatora((getApplication().getApplicationContext())));
        viewMailOperatora.setText(operator.odczytMailOperatora((getApplication().getApplicationContext())));
        edtWojewodztwo.setText(operator.odczytWojOperatora((getApplication().getApplicationContext())));

        try {
            teryt.szukajWojewodztwo(edtWojewodztwo, this, android.R.layout.simple_list_item_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}