package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RestauranteViewActivity extends AppCompatActivity {

    Franquia franquia;
    Adaptador_restaurante adaptadorrestaurante;
    ListView listviewrestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listviewrestaurante = findViewById(R.id.listview);
        Intent intent = getIntent();
        if(intent.getExtras()!=null) {
            this.franquia = intent.getParcelableExtra("item");
            adaptadorrestaurante = new Adaptador_restaurante(this, (ArrayList<Restaurante>) franquia.restaurantes);
            listviewrestaurante = (ListView) findViewById(R.id.listview);
            listviewrestaurante.setAdapter(adaptadorrestaurante);
        }
    }
}
