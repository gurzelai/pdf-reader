package com.example.listasencilla;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    private final Context context;
    private final int layout;
    private final ArrayList<String> listaDeseos;

    public MyAdapter(Context context, int layout, List<String> listaDeseos){
        this.context = context;
        this.layout = layout;
        this.listaDeseos = (ArrayList<String>) listaDeseos;
    }


    @Override
    public int getCount() {
        return listaDeseos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDeseos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        // Copiamos la vista
        View v = convertView;

         //Inflamos la vista con nuestro propio layout
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);

        v = layoutInflater.inflate(R.layout.list_item, null);
         // Valor actual según la posición

        String currentName = listaDeseos.get(position);

        // Referenciamos el elemento a modificar y lo rellenamos
        TextView textView = (TextView) v.findViewById(R.id.textView);
        textView.setText(currentName);
        //Devolvemos la vista inflada
        return v;
    }
}
