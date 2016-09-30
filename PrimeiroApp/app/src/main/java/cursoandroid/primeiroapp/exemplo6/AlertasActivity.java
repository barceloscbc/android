package cursoandroid.primeiroapp.exemplo6;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cursoandroid.primeiroapp.R;

public class AlertasActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertas);
        Button btnToast = (Button) findViewById(R.id.btnToast);
        Button btnAlerta = (Button) findViewById(R.id.btnAlert);

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });

        btnAlerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showAlerta();
                showAlertaCustom();
            }
        });
    }

    private void showAlerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_menu_info_details);
        builder.setTitle("Titulo");
        builder.setMessage("Mensagem");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Clicou em SIM", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Talvez", null);
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Clicou em NAO", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }

    private void showAlertaCustom() {
        LayoutInflater inflater=getLayoutInflater();
        View view =inflater.inflate(R.layout.layout_alerta, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_menu_info_details);
        builder.setTitle("Titulo");
        builder.setMessage("Mensagem");
        builder.setView(view);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Clicou em SIM", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Clicou em NAO", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }

    private void showToast() {
        LayoutInflater inflater=getLayoutInflater();

        View view =inflater.inflate(R.layout.layout_alerta, null);
        Toast customToast=new Toast(this);
        customToast.setView(view);
        customToast.setDuration(Toast.LENGTH_LONG);
        customToast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alertas, menu);
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
