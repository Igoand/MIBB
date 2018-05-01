package com.ian.igoand.mibb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button menuWprowadzObserwacje;
    Button menuPrzegladajObserwacje;
    Button menuWyslijObserwacje;
    Button menuZmienDaneOperatora;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_start_mibb);


        menuWprowadzObserwacje = findViewById(R.id.menuBtnWprowadzObserwacje);
        menuPrzegladajObserwacje = findViewById(R.id.menuBtnPrzegladajObserwacje);
        menuWyslijObserwacje = findViewById(R.id.menuBtnWyslijKarte);
        menuZmienDaneOperatora = findViewById(R.id.menuBtnZmienDaneOper);

        menuWprowadzObserwacje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, KartaObserwacji.class));
            }
        });
        menuZmienDaneOperatora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, EdytujOperatora.class));
            }
        });
    }
}
