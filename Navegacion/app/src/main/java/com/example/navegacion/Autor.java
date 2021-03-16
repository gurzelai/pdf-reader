package com.example.navegacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Autor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor);

        Intent intent = getIntent();
        String nombreAutor = intent.getStringExtra(MainActivity.EXTRA_AUTOR);
        TextView autor = findViewById(R.id.tvAutor);
        autor.setText(nombreAutor);

    }
}