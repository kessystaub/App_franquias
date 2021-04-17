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

    List<Franquia> franquias = new ArrayList();
    List<Restaurante> restaurantes_mc = new ArrayList();
    List<Restaurante> restaurantes_bobs = new ArrayList();
    List<Restaurante> restaurantes_burguerking = new ArrayList();
    List<Restaurante> restaurantes_subway = new ArrayList();
    Restaurante restaurante;
    AdaptadorCustomizado adaptador_customizado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        restaurante = new Restaurante("Franquia São Paulo","Avenida Paulista","São Paulo",R.drawable.mc);
        restaurantes_mc.add(restaurante);
        restaurante = new Restaurante("Franquia Rio de Janeiro","Rua conceicao","Rio de Janeiro",R.drawable.mc);
        restaurantes_mc.add(restaurante);
        restaurante = new Restaurante("Franquia Itapema","Rua jovem","Itapema",R.drawable.mc);
        restaurantes_mc.add(restaurante);

        restaurante = new Restaurante("Franquia Bahia","Rua amada","Bahia",R.drawable.bobs);
        restaurantes_bobs.add(restaurante);
        restaurante = new Restaurante("Franquia Santa Catarina","Rua oeste","Joinville",R.drawable.bobs);
        restaurantes_bobs.add(restaurante);
        restaurante = new Restaurante("Franquia Santa Cruz do Sul","Rua amarela","Santa Cruz do Sul",R.drawable.bobs);
        restaurantes_bobs.add(restaurante);

        restaurante = new Restaurante("Franquia Vacaria","Rua espaçoca","Vacaria",R.drawable.burguer);
        restaurantes_burguerking.add(restaurante);
        restaurante = new Restaurante("Franquia Balneario Camboriu","Rua coronel","Balneario Camboriu",R.drawable.burguer);
        restaurantes_burguerking.add(restaurante);
        restaurante = new Restaurante("Franquia Itajaí","Rua tenente","itajaí",R.drawable.burguer);
        restaurantes_burguerking.add(restaurante);

        restaurante = new Restaurante("Franquia Paraná","Rua 8223","curitiba",R.drawable.subway);
        restaurantes_subway.add(restaurante);
        restaurante = new Restaurante("Franquia Blumenau","Rua carolina","Blumenau",R.drawable.subway);
        restaurantes_subway.add(restaurante);
        restaurante = new Restaurante("Franquia Santa Maria","Rua joana","Santa Maria",R.drawable.subway);
        restaurantes_subway.add(restaurante);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);

        for(int i = 0; i < names.length; i++){
            if(i==0){
                Franquia franquia = new Franquia(names[i],desc[i],images[i],restaurantes_mc);
                franquias.add(franquia);
            }else if(i==1){
                Franquia franquia = new Franquia(names[i],desc[i],images[i],restaurantes_bobs);
                franquias.add(franquia);
            }else if(i==2){
                Franquia franquia = new Franquia(names[i],desc[i],images[i],restaurantes_burguerking);
                franquias.add(franquia);
            }else if(i==3){
                Franquia franquia = new Franquia(names[i],desc[i],images[i],restaurantes_subway);
                franquias.add(franquia);
            }
        }

        adaptador_customizado = new AdaptadorCustomizado(franquias,this);
        listView.setAdapter(adaptador_customizado);
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
                adaptador_customizado.getFilter().filter(newText);
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

    public class AdaptadorCustomizado extends BaseAdapter implements Filterable {

        private List<Franquia> lista_franquia;
        private List<Franquia> lista_filtrada_franquia;
        private Context context;

        public AdaptadorCustomizado(List<Franquia> lista_franquia, Context contexto) {
            this.lista_franquia = lista_franquia;
            this.lista_filtrada_franquia = lista_franquia;
            this.context = contexto;
        }

        @Override
        public int getCount() {
            return lista_filtrada_franquia.size();
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

            imageView.setImageResource(lista_filtrada_franquia.get(position).getImage());
            itemName.setText(lista_filtrada_franquia.get(position).getName());
            itemDesc.setText(lista_filtrada_franquia.get(position).getDesc());

            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,RestauranteViewActivity.class).putExtra("item", lista_filtrada_franquia.get(position)));
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
                        filterResults.count = lista_franquia.size();
                        filterResults.values = lista_franquia;
                    } else {
                        String searchStr = constraint.toString().toLowerCase();
                        List<Franquia> resultData = new ArrayList<>();
                        for (Franquia itemsModel : lista_franquia) {
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
                    lista_filtrada_franquia = (List<Franquia>) results.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }
}
