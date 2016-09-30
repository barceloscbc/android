
package cursoandroid.primeiroapp.exemplo5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cursoandroid.primeiroapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpinnerListActivity extends ActionBarActivity {
    String[] opcoes = new String[]{"Listview simples", "ListView customizado"};
    String[] planetas = new String[]{"Mercurio", "Venus", "Terra", "Marte",
            "Jupiter", "Saturno", "Urano", "Netuno", "Plutão"};
    Spinner spinnerEscolha;
    ListView listviewAlunos;

    List<Aluno> listaAlunos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_list);

        spinnerEscolha = (Spinner) findViewById(R.id.spinnerEscolha);
        listviewAlunos = (ListView) findViewById(R.id.listViewPlanetas);

        //Criando um spinner simples
        //simple_spinner_item = layout do item selecionado
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, opcoes);
        //simple_spinner_dropdown_item = layout de cada item/linha do dowpdown
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEscolha.setAdapter(spinnerAdapter);


        spinnerEscolha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Opcao selecionada: " + spinnerEscolha.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                if (position == 0)
                    makeAdapterListViewSimples();
                else
                    makeAdapterListViewCustomizado();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //Litsa customizada com texto e imagem
    private void makeAdapterListViewCustomizado()  {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AlunoInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AlunoInterface service = retrofit.create(AlunoInterface.class);

        Call<ResponseAlunos> call = service.listAlunos();

        call.enqueue(new Callback<ResponseAlunos>() {
            @Override
            public void onResponse(Call<ResponseAlunos> call, Response<ResponseAlunos> response) {
                Log.d("Response", String.valueOf(response.code()));
                listaAlunos = response.body().getResults();
            }

            @Override
            public void onFailure(Call<ResponseAlunos> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

       // final AdapterPlanetas adapterCustomizado = new AdapterPlanetas(this, Planeta.getPlanetas());
        final AdapterAlunos adapterCustomizado = new AdapterAlunos(this, listaAlunos);

        listviewAlunos.setAdapter(adapterCustomizado);

        listviewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String endereco = listaAlunos.get(position).getEndereco();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+endereco);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    //Lista simples com layout de texto
    private void makeAdapterListViewSimples() {

        final ArrayAdapter<String> adapterListaSimples = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, planetas);
        listviewAlunos.setAdapter(adapterListaSimples);
        listviewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String planeta = (String) adapterListaSimples.getItem(position);
                Toast.makeText(getApplicationContext(), planeta, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spinner_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
