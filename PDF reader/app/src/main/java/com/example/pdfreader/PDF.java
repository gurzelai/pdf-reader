package com.example.pdfreader;

import android.net.Uri;

import java.io.File;

public class PDF {
    Uri uri;
    String nombre;
    String path;
    File file;

    public PDF(String nombre, File file, Uri uri){
        this.nombre = nombre;
        this.path = file.getPath();
        this.file = file;
        this.uri = uri;
    }

    public PDF(String n) {
        nombre = n;
    }

    public String getPath(){
        return path;
    }
    public String getNombre(){
        return nombre;
    }


    public File getFile() {
        return file;
    }
    public Uri getUri(){
        return uri;
    }
}
