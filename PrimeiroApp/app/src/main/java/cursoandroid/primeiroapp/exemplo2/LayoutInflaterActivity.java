package cursoandroid.primeiroapp.exemplo2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cursoandroid.primeiroapp.R;

public class LayoutInflaterActivity extends AppCompatActivity {
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_inflater);

        RelativeLayout relativeLayout = (RelativeLayout)
                findViewById(R.id.relativeLayoutPrincipal);

        LayoutInflater inflater = LayoutInflater.from(this);

        LinearLayout linearLayoutInflate = (LinearLayout)
                inflater.inflate(R.layout.layout_teste_inflate, relativeLayout, false);

        relativeLayout.addView(linearLayoutInflate);

        Button btnPlay = (Button) linearLayoutInflate.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Botão Play Click", Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_layout_inflater, menu);
        return true;
    }

}
