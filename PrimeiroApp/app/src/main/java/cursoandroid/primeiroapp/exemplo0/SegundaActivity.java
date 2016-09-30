package cursoandroid.primeiroapp.exemplo0;

import android.os.Bundle;
import android.widget.TextView;

import cursoandroid.primeiroapp.R;


public class SegundaActivity extends DebugActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        TextView txtParamRec = (TextView) findViewById(R.id.txt_param_rec);

        Bundle extras = getIntent().getExtras();
        txtParamRec.setText(extras.getString("PARAM_IDA"));
    }



}
