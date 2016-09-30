package cursoandroid.primeiroapp.exemplo11;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import cursoandroid.primeiroapp.R;

public class SharedPrefsActivity extends ActionBarActivity {
    private static final String PREF_FILE_NAME = "cursoandroidprefs";

    private static final String PREF_LOGIN = "LOGIN";
    private static final String PREF_SENHA = "SENHA";
    private static final String PREF_NOTIFICAR = "NOTIFICAR";
    private static final String PREF_VIBRAR = "VIBRAR";
    private EditText edtLogin;
    private EditText edtSenha;
    private Switch  swNot;
    private CheckBox chkVibrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefs);

        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        swNot = (Switch) findViewById(R.id.swPrefNotificacoes);
        chkVibrar = (CheckBox) findViewById(R.id.chkVibrar);

        //Exibe os valores gravados
        edtLogin.setText(getStringPrefsByKey(PREF_LOGIN));
        edtSenha.setText(getStringPrefsByKey(PREF_SENHA));
        swNot.setChecked(getBooleanPrefsByKey(PREF_NOTIFICAR));
        chkVibrar.setChecked(getBooleanPrefsByKey(PREF_VIBRAR));

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarValores();
            }
        });

    }

    //Grava os valores preechidos
    private void guardarValores() {
        putStringPrefs(PREF_LOGIN, edtLogin.getText().toString());
        putStringPrefs(PREF_SENHA, edtSenha.getText().toString());
        putBooleanPrefs(PREF_VIBRAR, chkVibrar.isChecked());
        putBooleanPrefs(PREF_NOTIFICAR, swNot.isChecked());
        Toast.makeText(this, "Dados gravados com sucesso.", Toast.LENGTH_SHORT).show();

    }

    private void putStringPrefs(String chave, String valor) {
        SharedPreferences prefs = getSharedPreferences(PREF_FILE_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(chave, valor);
        editor.commit();
    }

    private void putBooleanPrefs(String chave, boolean valor){
        SharedPreferences prefs = getSharedPreferences(PREF_FILE_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(chave, valor);
        editor.commit();
    }

    private boolean getBooleanPrefsByKey(String chave){
        SharedPreferences prefs = getSharedPreferences(PREF_FILE_NAME, 0);
        return prefs.getBoolean(chave, false);

    }


    private String getStringPrefsByKey(String chave){
        SharedPreferences prefs = getSharedPreferences(PREF_FILE_NAME, 0);
        return prefs.getString(chave, "");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shared_prefs, menu);
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
