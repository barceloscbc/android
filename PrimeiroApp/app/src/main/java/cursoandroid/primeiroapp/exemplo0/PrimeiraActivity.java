package cursoandroid.primeiroapp.exemplo0;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cursoandroid.primeiroapp.R;


public class PrimeiraActivity extends DebugActivity {
    final Context context = this;
    Button btnSegundaTela;
    EditText edtIda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira);

        btnSegundaTela = (Button) findViewById(R.id.btn_segunda_tela);
        edtIda = (EditText) findViewById(R.id.edt_param_ida);

        btnSegundaTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, SegundaActivity.class);
                it.putExtra("PARAM_IDA", edtIda.getText().toString());
                startActivity(it);
            }
        });
    }



}
