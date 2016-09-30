package cursoandroid.primeiroapp.exemplo4;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import cursoandroid.primeiroapp.R;

public class ComponentesActivity extends ActionBarActivity {

    RadioGroup rdSexGroup;
    Switch swNotificacoes;
    CheckBox chkAndroid;
    CheckBox chkIos;
    CheckBox chkWp;
    Button  btnValores;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_componentes);
     
        chkIos = (CheckBox) findViewById(R.id.chkIos);
        chkAndroid = (CheckBox) findViewById(R.id.chkAndroid);
        chkWp = (CheckBox) findViewById(R.id.chkWp);
        swNotificacoes = (Switch) findViewById(R.id.swNotificacoes);
        btnValores = (Button) findViewById(R.id.btnValores);
        rdSexGroup = (RadioGroup) findViewById(R.id.rdSexGroup);


        btnValores.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
        //Evento ao trocar selecao de radio button

        rdSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                boolean fem = R.id.rdFem == checkedId;
                boolean masc = R.id.rdMasc == checkedId;

                if (fem)
                    Toast.makeText(getApplicationContext(), "Feminino", Toast.LENGTH_SHORT).show();
                else if (masc)
                    Toast.makeText(getApplicationContext(), "Masculino", Toast.LENGTH_SHORT).show();
            }
        });

        //Evento ao marcar o checkbox Windows Phone
        chkWp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "Windows Phone checked: " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        //Evento ao trocar switch de notificacoes
        swNotificacoes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "Notificacoes: " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        //Evento ao clicar no botao valores para mostrar os valores selecionados
        btnValores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), String.format("Sexo: %s \n\nSOs marcados: %s \n\nNotificações ligadas?: %s", getRadioSelected(), getChksSelected(), getStatusNotificacoes()),Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getStatusNotificacoes() {
        if (swNotificacoes.isChecked())
            return swNotificacoes.getTextOn().toString();
        else
            return swNotificacoes.getTextOff().toString();


    }

    private String getChksSelected() {
        String retorno = "";
        if (chkIos.isChecked())
            retorno+=chkIos.getText().toString();
        if (chkAndroid.isChecked())
            retorno+=", "+ chkAndroid.getText().toString();
        if (chkWp.isChecked())
            retorno+=", "+ chkWp.getText().toString();

        return retorno;
    }

    private String getRadioSelected() {
        int selectedId = rdSexGroup.getCheckedRadioButtonId();
        if (selectedId==-1) return "";
        RadioButton radioSexButton = (RadioButton) findViewById(selectedId);

        return radioSexButton.getText().toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_componentes, menu);
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
