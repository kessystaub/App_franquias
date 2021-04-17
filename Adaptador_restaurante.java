package com.example.myproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador_restaurante extends BaseAdapter{

        Activity context;
        ArrayList<Restaurante> restaurantes;
        private static LayoutInflater inflater = null;

        public Adaptador_restaurante(Activity context, ArrayList<Restaurante>restaurantes){
            this.context = context;
            this.restaurantes = restaurantes;
            inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return restaurantes.size();
        }

        @Override
        public Restaurante getItem(int position) {
            return restaurantes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemview = convertView;
            itemview = (itemview == null) ? inflater.inflate(R.layout.row_items2,null): itemview;
            TextView textViewName = (TextView) itemview.findViewById(R.id.IDName);
            TextView textViewAdress = (TextView) itemview.findViewById(R.id.IDAdress);
            TextView textViewCity = (TextView) itemview.findViewById(R.id.IDCity);
            ImageView imageview = itemview.findViewById(R.id.IDimage);
            Restaurante selectedRestaurante = restaurantes.get(position);
            textViewName.setText(selectedRestaurante.name);
            textViewAdress.setText(selectedRestaurante.adress);
            textViewCity.setText(selectedRestaurante.city);
            imageview.setImageResource(selectedRestaurante.image);

            return itemview;
        }
}
