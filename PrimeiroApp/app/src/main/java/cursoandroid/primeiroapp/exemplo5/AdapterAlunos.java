package cursoandroid.primeiroapp.exemplo5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cursoandroid.primeiroapp.R;

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
    public View getView(int position, View convertView, ViewGroup parent) {

        // Infla a view da linha/item
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item_planeta, parent, false);

        // findViewById das views que precisa atualizar
        TextView txtNomePlaneta = (TextView) view.findViewById(R.id.txtNomePlaneta);
        ImageView imgPlaneta = (ImageView) view.findViewById(R.id.imgPlaneta);

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
}
