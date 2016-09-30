package puc.com.student;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import puc.com.student.model.Aluno;
import puc.com.student.model.AlunoInterface;
import puc.com.student.model.ResponseAlunos;
import puc.com.student.ui.AdapterAlunos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ListView listviewAlunos;
    List<Aluno> listaAlunos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        this.montaListView();
    }

    public ListView getListviewAlunos() {
        return this.listviewAlunos;
    }

    private void montaListView() {
        listviewAlunos = (ListView) findViewById(R.id.listViewAlunos);
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
                // final AdapterPlanetas adapterCustomizado = new AdapterPlanetas(this, Planeta.getPlanetas());
                final AdapterAlunos adapterCustomizado = new AdapterAlunos(MainActivity.this, listaAlunos);
                listviewAlunos.setAdapter(adapterCustomizado);
            }

            @Override
            public void onFailure(Call<ResponseAlunos> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });


        listviewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String endereco = listaAlunos.get(position).getEndereco();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + endereco);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
