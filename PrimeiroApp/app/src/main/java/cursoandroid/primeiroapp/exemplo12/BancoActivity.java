package cursoandroid.primeiroapp.exemplo12;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import cursoandroid.primeiroapp.R;

public class BancoActivity extends ActionBarActivity {
    private EditText edtUsuario;
    private EditText edtSenha;
    private EditText edtNomeUsuario;
    private Button btnInserir;
    private Button btnExcluir;
    private Button btnAtualizar;
    private ListView listaUsuarios;
    private ArrayAdapter<String> usuariosAdapter;
    private Banco bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_banco);

        edtNomeUsuario = (EditText) findViewById(R.id.edtNome);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        btnInserir = (Button) findViewById(R.id.btnInserir);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);
        listaUsuarios = (ListView) findViewById(R.id.listViewUsuarios);
        bd = new Banco(this);

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = bd.inserirUsuarioBD(edtUsuario.getText().toString(), edtSenha.getText().toString(), edtNomeUsuario.getText().toString());
                if (ok)
                    Toast.makeText(getApplicationContext(), "Inserido com suceso", Toast.LENGTH_SHORT).show();
                showLista();
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = bd.excluirUsuarioBD(edtUsuario.getText().toString());
                if (ok)
                    Toast.makeText(getApplicationContext(), "Excluido com suceso", Toast.LENGTH_SHORT).show();
                showLista();
            }
        });

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ok = bd.atualizarUsuarioBD(edtUsuario.getText().toString(), edtSenha.getText().toString(), edtNomeUsuario.getText().toString());
                if (ok)
                    Toast.makeText(getApplicationContext(), "Atualizado com suceso", Toast.LENGTH_SHORT).show();
                showLista();
            }
        });


        showLista();
    }

    private void showLista(){
        usuariosAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bd.getListaUsuariosBD());
        listaUsuarios.setAdapter(usuariosAdapter);
    }


}
