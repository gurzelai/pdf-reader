    package com.example.exploradordearchivos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    public class MainActivity extends AppCompatActivity {

    final Uri CARPETA_POR_DEFECTO = Uri.parse(Environment.getExternalStorageDirectory().getPath());
    private int VALOR_RETORNO = 1;

    TextView nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = (TextView) this.findViewById(R.id.nombre);
    }

    public void openFolder(android.view.View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setDataAndType(CARPETA_POR_DEFECTO, "*/*");
        intent.setType("*/*");
        startActivityForResult(Intent.createChooser(intent, "Open folder"), VALOR_RETORNO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            //Cancelado por el usuario
        }
        if ((resultCode == RESULT_OK) && (requestCode == VALOR_RETORNO )) {
            //Procesar el resultado
            Uri uri = data.getData(); //obtener el uri content
            String path = uri.getPath();
            List<String> valores = new ArrayList<String>(Arrays.asList(path.split("[/]")));
            String nombreArchivo = valores.get(valores.size()-1);
            nombre.setText(nombreArchivo);
        }
    }
}
