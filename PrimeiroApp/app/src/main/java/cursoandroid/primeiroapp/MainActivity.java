package cursoandroid.primeiroapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import cursoandroid.primeiroapp.exemplo0.PrimeiraActivity;
import cursoandroid.primeiroapp.exemplo1.CalculadoraActivity;
import cursoandroid.primeiroapp.exemplo10.AlarmActivity;
import cursoandroid.primeiroapp.exemplo11.SharedPrefsActivity;
import cursoandroid.primeiroapp.exemplo12.BancoActivity;
import cursoandroid.primeiroapp.exemplo13.WebserviceActivity;
import cursoandroid.primeiroapp.exemplo2.LayoutInflaterActivity;
import cursoandroid.primeiroapp.exemplo3.RecursosActivity;
import cursoandroid.primeiroapp.exemplo4.ComponentesActivity;
import cursoandroid.primeiroapp.exemplo5.SpinnerListActivity;
import cursoandroid.primeiroapp.exemplo6.AlertasActivity;
import cursoandroid.primeiroapp.exemplo7.WebViewActivity;
import cursoandroid.primeiroapp.exemplo8.MenuIntentActivity;
import cursoandroid.primeiroapp.exemplo9.NotificacaoActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listViewExemplos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEx0 = (Button) findViewById(R.id.btnExemplo0);
        Button btnEx1 = (Button) findViewById(R.id.btnExemplo1);
        btnEx0.setOnClickListener(this);
        btnEx1.setOnClickListener(this);

        String[] arrayItens = getResources().getStringArray(R.array.lista_exemplos);

        listViewExemplos = (ListView) findViewById(R.id.listViewExemplos);
        ArrayAdapter<String> itemsAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayItens);
        listViewExemplos.setAdapter(itemsAdapter);

        listViewExemplos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class classe=null;

                switch (position){
                    case 0:
                        classe = PrimeiraActivity.class;
                        break;
                    case 1:
                        classe = CalculadoraActivity.class;
                        break;
                    case 2:
                        classe =  LayoutInflaterActivity.class;
                        break;
                    case 3:
                        classe =  RecursosActivity.class;
                        break;
                    case 4:
                        classe =  ComponentesActivity.class;
                        break;
                    case 5:
                        classe =  SpinnerListActivity.class;
                        break;
                    case 6:
                        classe =  AlertasActivity.class;
                        break;
                    case 7:
                        classe = WebViewActivity.class;
                        break;
                    case 8:
                        classe = MenuIntentActivity.class;
                        break;
                    case 9:
                        classe = NotificacaoActivity.class;
                        break;
                    case 10:
                        classe = AlarmActivity.class;
                        break;
                    case 11:
                        classe = SharedPrefsActivity.class;
                        break;
                    case 12:
                        classe = BancoActivity.class;
                        break;
                    case 13:
                        classe = WebserviceActivity.class;
                        break;
                    default:
                        classe =  PrimeiraActivity.class;
                        break;

                }
                Intent it = new Intent(getContext(), classe);
                startActivity(it);
            }
        });

    }

    private Context getContext() {
        return this;
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnExemplo0){
            Intent it = new Intent(this, PrimeiraActivity.class);
            startActivity(it);
        }else if(v.getId() == R.id.btnExemplo1){
            Intent it = new Intent(this, CalculadoraActivity.class);
            startActivity(it);
        }
    }
}
