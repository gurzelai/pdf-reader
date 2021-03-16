package com.example.listasencilla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    List<String> listaDeseos;
    MyAdapter adapter;
    FloatingActionButton add;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        add = this.findViewById(R.id.add);

        inicializarListView();

        i = 0;
    }

    //INICIALIZAR
    private void inicializarListView() {
        //configuración basica de la lista
        listaDeseos = new ArrayList<>();
        listaDeseos.add("gorka");
        listaDeseos.add("urzelai");
        adapter = new MyAdapter(this, R.layout.list_item, listaDeseos);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Has seleccionado " + listaDeseos.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //ACCIONES//
    public void add(android.view.View view){
        //accion de boton añadir
        i++;
        listaDeseos.add(String.valueOf(i));
        adapter = new MyAdapter(this, R.layout.list_item, listaDeseos);
        lv.setAdapter(adapter);
    }
}