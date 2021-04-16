package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    int images[]= {R.drawable.mc, R.drawable.bobs, R.drawable.burguer, R.drawable.subway};
    String names[]={"mc donals","bobs","burguer king","subway"};
    String desc[]={"amo muito tudo isso","pior que mc","melhor que mc","saudavel"};

    List<ItemsModel> franquias = new ArrayList();

    List<Restaurante> restaurantes = new ArrayList();
    Restaurante restaurante;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //restaurante = new Restaurante(names[0],desc[0],images[0]);
        //restaurantes.add(restaurante);
        restaurante = new Restaurante("cleitin","ola",R.drawable.subway);
        restaurantes.add(restaurante);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);

        for(int i = 0; i < names.length; i++){
            //restaurante = new Restaurante(names[i],desc[i],images[i]);
            //funçao random 1 a 3
            ItemsModel itemsModel = new ItemsModel(names[i],desc[i],images[i],restaurante);
            franquias.add(itemsModel);
        }

        customAdapter = new CustomAdapter(franquias,this);
        listView.setAdapter(customAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.search_view);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapter.getFilter().filter(newText);

                return true;
            }
        });

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.search_view){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    public class CustomAdapter extends BaseAdapter implements Filterable {

        private List<ItemsModel> itemsModelList;
        private List<ItemsModel> itemsModelListFiltered;
        private Context context;

        public CustomAdapter(List<ItemsModel> itemsModelList, Context context) {
            this.itemsModelList = itemsModelList;
            this.itemsModelListFiltered = itemsModelList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return itemsModelListFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.row_items,null);

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView itemName = view.findViewById(R.id.itemName);
            TextView itemDesc = view.findViewById(R.id.itemDesc);

            imageView.setImageResource(itemsModelListFiltered.get(position).getImage());
            itemName.setText(itemsModelListFiltered.get(position).getName());
            itemDesc.setText(itemsModelListFiltered.get(position).getDesc());

            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,ItemViewActivity.class).putExtra("item",itemsModelListFiltered.get(position)));
                }
            });

            return view;
        }


        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults filterResults = new FilterResults();

                    if (constraint == null || constraint.length() == 0) {
                        filterResults.count = itemsModelList.size();
                        filterResults.values = itemsModelList;
                    } else {
                        String searchStr = constraint.toString().toLowerCase();

                        List<ItemsModel> resultData = new ArrayList<>();

                        for (ItemsModel itemsModel : itemsModelList) {
                            if (itemsModel.getName().contains(searchStr) || itemsModel.getDesc().contains(searchStr)) {
                                resultData.add(itemsModel);
                            }

                            filterResults.count = resultData.size();
                            filterResults.values = resultData;
                        }
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    itemsModelListFiltered = (List<ItemsModel>) results.values;
                    notifyDataSetChanged();
                }
            };

            return filter;
        }
    }
}
