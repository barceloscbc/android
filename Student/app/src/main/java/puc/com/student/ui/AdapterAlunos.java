package puc.com.student.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.ResponseBody;
import puc.com.student.MainActivity;
import puc.com.student.R;
import puc.com.student.model.Aluno;
import puc.com.student.model.AlunoInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Igor on 28/09/2016.
 */

public class AdapterAlunos extends BaseAdapter {
    private final Context context;
    private final List<Aluno> alunos;

    public AdapterAlunos(Context context, List<Aluno> alunos) {

        this.context = context;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos != null ? alunos.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Infla a view da linha/item
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item_planeta, parent, false);

        // findViewById das views que precisa atualizar
        TextView txtNomePlaneta = (TextView) view.findViewById(R.id.txtNomePlaneta);
        ImageView imgPlaneta = (ImageView) view.findViewById(R.id.imgPlaneta);
        ImageButton btMapa = (ImageButton) view.findViewById(R.id.btn_mapa);
        ImageButton btExcluir = (ImageButton) view.findViewById(R.id.btn_excluir);

        btMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String endereco = alunos.get(position).getEndereco();
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + endereco);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);
            }
        });


        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirme(v, position);
            }
        });

        // Atualiza os valores das views
        Aluno aluno = alunos.get(position);
        txtNomePlaneta.setText(aluno.getNome());
        // imgPlaneta.setImageResource(R.drawable.planeta_05_jupiter);

        String url = aluno.getFotoUrl();
        //Picasso.with(this).load(url).into(imgDownPicasso);

        Picasso.with(this.context)
                .load(url)
                .into(imgPlaneta, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
        return view;
    }

    private void showConfirme(View v, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Você deseja excluir o aluno")
                .setPositiveButton("sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("Error", "sim");

                        Aluno aluno = alunos.get(position);
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(AlunoInterface.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        AlunoInterface service = retrofit.create(AlunoInterface.class);
                        service.deleteAluno(aluno.getObjectId());
                        Call<ResponseBody> response = service.deleteAluno(aluno.getObjectId());
                        response.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                alunos.remove(position);
                                final AdapterAlunos adapterCustomizado = new AdapterAlunos(context, alunos);
                                ((MainActivity) context).getListviewAlunos().setAdapter(adapterCustomizado);
                                Toast.makeText(context, "Aluno Excluido com Sucesso!!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.d("Error", t.getMessage());
                                Toast.makeText(context, "Ocorreu algum problem ao  excluir o aluno.", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                })
                .setNegativeButton("não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("Error", "Não");
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


