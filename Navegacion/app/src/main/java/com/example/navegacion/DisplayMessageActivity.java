package com.example.navegacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String nombre = intent.getStringExtra(MainActivity.EXTRA_NOMBRE);
        String descripcion = intent.getStringExtra(MainActivity.EXTRA_DESCRIPCION);
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        EditText etNombre = findViewById(R.id.etNombre);
        etNombre.setText(nombre);
        EditText etDescripcion = findViewById(R.id.etDescripcion);
        etDescripcion.setText(descripcion);



    }
}