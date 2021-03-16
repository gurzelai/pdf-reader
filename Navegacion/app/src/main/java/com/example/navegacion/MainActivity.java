package com.example.navegacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NOMBRE = "NOMBRE";
    public static final String EXTRA_DESCRIPCION = "DESCRIPCION";
    public static final String EXTRA_MESSAGE = "com.example.navegacion.MESSAGE";
    public static final String EXTRA_AUTOR = "AUTOR";

    ListView lv;
    List<Deseo> listaDeseos;
    ArrayAdapter<Deseo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);

        inicializarListView();

    }

    //INICIALIZAR
    private void inicializarListView() {
        //configuración basica de la lista
        listaDeseos = new ArrayList<Deseo>();
        listaDeseos.add(new Deseo("parapente", null));
        listaDeseos.add(new Deseo("tabla de surf", "para surfear en verano"));
        adapter = new ArrayAdapter<Deseo>(this, android.R.layout.simple_list_item_1, listaDeseos);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Intent intent = new Intent(this,  ActivityEdit.class); //da error porque no es this, sino el MainActivity
                //Toast.makeText(this , "has elegido "+ listaDeseos.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cambiarActividad(View view){
        //El constructor Intent toma dos parámetros, un Context y un Class.
        Intent intent = new Intent(this, Autor.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String autor = editText.getText().toString();
        //El método putExtra() agrega el valor de EditText al intent. Un Intent puede transportar tipos de datos como pares clave-valor llamados extras.
        intent.putExtra(EXTRA_AUTOR, autor);
        startActivity(intent);
    }
    public void cambiarActividad2(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_NOMBRE, listaDeseos.get(0).getNombre());
        intent.putExtra(EXTRA_DESCRIPCION, listaDeseos.get(0).getDescripcion());
        startActivity(intent);
    }
    public void cambiarActividad3(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_NOMBRE, listaDeseos.get(1).getNombre());
        intent.putExtra(EXTRA_DESCRIPCION, listaDeseos.get(1).getDescripcion());
        startActivity(intent);
    }
}