package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemViewActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    ItemsModel itemsModel;
    Adapter2 adapter2;
    ListView listviewrestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //imageView = findViewById(R.id.imageViewItem);
        //textView = findViewById(R.id.textViewName);

        listviewrestaurante = findViewById(R.id.listview);

        Intent intent = getIntent();
        this.itemsModel = intent.getParcelableExtra("item");

        adapter2 = new Adapter2(this, (ArrayList<Restaurante>)itemsModel.restaurantes);
        listviewrestaurante = (ListView) findViewById(R.id.listview);
        listviewrestaurante.setAdapter(adapter2);
    }
}
