package com.lkcdroid.ownstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class BuscarActivity extends AppCompatActivity {
    private ImageButton volver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        ImageButton volver = findViewById(R.id.VolverButton);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverInicio();
            }
        });
    }

    private void volverInicio() {
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
    }
}
