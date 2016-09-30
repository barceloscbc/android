package cursoandroid.primeiroapp.exemplo8;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import cursoandroid.primeiroapp.R;
import cursoandroid.primeiroapp.exemplo4.ComponentesActivity;

public class MenuIntentActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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
            showOpcoes();
        } else if (id == R.id.action_sobre) {
            showSobre();
        } else if (id == R.id.action_intent_abrir_browser) {
            showBrowser();
        } else if (id == R.id.action_intent_compartilhar) {
            showCompartilhar();
        } else if (id == R.id.action_intent_enviar_sms) {
            showEnviarSMS();
        } else if (id == R.id.action_intent_ligar_numero) {
            showLigar();
        }


        return super.onOptionsItemSelected(item);
    }

    private void showLigar() {
        Intent callintent = new Intent(Intent.ACTION_DIAL);
        callintent.setData(Uri.parse("tel: 99999999"));
        startActivity(callintent);
    }

    private void showEnviarSMS() {
        Uri uri = Uri.parse("sms:99999999");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", "Olá");
        startActivity(intent);

    }

    private void showCompartilhar() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Compartilhar");
        intent.putExtra(Intent.EXTRA_TEXT, "bla bla bla bla");
        startActivity(intent);
    }

    private void showBrowser() {
        String url = "http://www.globo.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void showSobre() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sobre");
        builder.setMessage("mensagem sobre askdsak daskld aslkd klasmdkl askldml asmdlk asklmdlk a");
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showOpcoes() {
        Intent it = new Intent(this, ComponentesActivity.class);
        startActivity(it);
    }
}
