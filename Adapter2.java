package com.example.myproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter2 extends BaseAdapter{

        Activity context;
        ArrayList<Restaurante> restaurantes;
        private static LayoutInflater inflater = null;

        public Adapter2(Activity context, ArrayList<Restaurante>restaurantes){
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
            itemview = (itemview == null) ? inflater.inflate(R.layout.row_items,null): itemview;
            TextView textViewName = (TextView) itemview.findViewById(R.id.itemName);
            TextView textViewDesc = (TextView) itemview.findViewById(R.id.itemDesc);
            ImageView imageview = itemview.findViewById(R.id.imageView);
            Restaurante selectedRestaurante = restaurantes.get(position);
            textViewName.setText(selectedRestaurante.name);
            textViewDesc.setText(selectedRestaurante.desc);
            return itemview;
        }

}
