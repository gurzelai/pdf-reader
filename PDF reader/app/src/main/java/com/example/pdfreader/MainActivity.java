package com.example.pdfreader;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.provider.CalendarContract.CalendarCache.URI;

public class MainActivity extends AppCompatActivity {

    final Uri CARPETA_POR_DEFECTO = Uri.parse(Environment.getExternalStorageDirectory().getPath());
    final private int ACTIVITY_EXPLORADOR = 1;
    final static public String EXTRA_NOMBRE = "nombre";
    final static public String EXTRA_PATH = "path";

    FloatingActionButton boton;
    ListView lv;

    AdaptadorPDF adapter;
    static ArrayList<PDF> lista;
    public static PDF actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();
        reconocer();

    }

    public void mostrarPDF(int position) {
        Intent intent = new Intent(this, MostrarPDF.class);
        intent.putExtra("nombre", lista.get(position).getNombre());
        intent.putExtra(EXTRA_PATH, position);
        startActivity(intent);
    }


    private void reconocer() {
        lv = (ListView) findViewById(R.id.lv);
        configListView();
        boton = (FloatingActionButton) this.findViewById(R.id.add);
    }

    private void configListView() {
        adapter = new AdaptadorPDF(this, R.layout.list_item, lista);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                actual = lista.get(position);
                mostrarPDF(position);
            }
        });
    }

    private void inicializar() {
        lista = new ArrayList<PDF>();
        lista.add(new PDF("Prueba1"));
        lista.add(new PDF("Prueba2"));

    }

    public void openFolder(android.view.View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(Intent.createChooser(intent, "Open folder"), ACTIVITY_EXPLORADOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            //Cancelado por el usuario
        }
         if ((resultCode == RESULT_OK) && (requestCode == ACTIVITY_EXPLORADOR )) {
            //Procesar el resultado
            Uri uri = data.getData(); //obtener el uri content
             String path = null;
             try {
                 path = UriFunciones.getPath(getApplicationContext(), uri);
             } catch (URISyntaxException e) {
                 e.printStackTrace();
             }
            lista.add(new PDF(path.toString(), new File(path), uri));
            adapter.notifyDataSetChanged();
        }
    }

    public static PDF getPdf(int num){
        return lista.get(num);
    }
}