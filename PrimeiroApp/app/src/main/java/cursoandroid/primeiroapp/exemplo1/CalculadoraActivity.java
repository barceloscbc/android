package cursoandroid.primeiroapp.exemplo1;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import cursoandroid.primeiroapp.R;


public class CalculadoraActivity extends ActionBarActivity {
    Button btnSomar;
    EditText edtNum1;
    EditText edtNum2;
    LinearLayout linearLayoutResultado;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        btnSomar = (Button) findViewById(R.id.btn_somar);
        edtNum1 = (EditText) findViewById(R.id.edt_num_1);
        edtNum2 = (EditText) findViewById(R.id.edt_num_2);
        LinearLayout linearLayoutPrincipal = (LinearLayout)
                findViewById(R.id.linearLayoutPrincipal) ;

        ////INTERFACE COM JAVA

        //Criando um linearLayout para o resultado
        linearLayoutResultado = new LinearLayout(this);
        linearLayoutResultado.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutResultado.setVisibility(View.GONE);
        linearLayoutResultado.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

        //Criando dois textviews para exibir o label resultado e o valor do resultado
        TextView txtLabelResultado = new TextView(this);
        txtLabelResultado.setText("Resultado JAVA: ");
        txtResult = new TextView(this);
        txtResult.setTypeface(txtResult.getTypeface(), Typeface.BOLD);

        //Add o linearLayout com os dois textviews no linearlayout criado
        linearLayoutResultado.addView(txtLabelResultado);
        linearLayoutResultado.addView(txtResult);

        //Add o linearLayout criado no linearlayout principal
        linearLayoutPrincipal.addView(linearLayoutResultado);

        btnSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                efetuarCalculo();

            }
        });


    }

    private void efetuarCalculo() {
        String soma = getSoma();

        if (!soma.isEmpty()){
            //Exibe layout feito em JAVA
            linearLayoutResultado.setVisibility(View.VISIBLE);
            txtResult.setText(soma);

            ////INTERFACE COM XML
            //Exibe Layout feito em XML
            LinearLayout linearLayoutXML = (LinearLayout)
                    findViewById(R.id.linearLayoutResultadoXML);
            linearLayoutXML.setVisibility(View.VISIBLE);
            TextView txtResultXML = (TextView)
                    findViewById(R.id.txtResultXML);
            txtResultXML.setText(soma);
        }
    }

    private String getSoma(){
        String valor1 = edtNum1.getText().toString();
        String valor2 = edtNum2.getText().toString();

        if (!valor1.isEmpty() && !valor2.isEmpty()){
            int num1 = Integer.parseInt(valor1);
            int num2 = Integer.parseInt(valor2);
            return String.valueOf(num1+num2);
        }else
            return "";

    }


}
