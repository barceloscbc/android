package cursoandroid.primeiroapp.exemplo5;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import cursoandroid.primeiroapp.R;
import cursoandroid.primeiroapp.exemplo13.APIService;
import cursoandroid.primeiroapp.exemplo13.Empregado;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mateus Dias on 19/07/2015.
 */
public class AdapterPlanetas extends BaseAdapter {
    private final Context context;
    private final List<Planeta> planetas;

    public AdapterPlanetas(Context context, List<Planeta> planetas) {

        this.context = context;
        this.planetas = planetas;
    }

    @Override
    public int getCount() {

        return planetas != null ? planetas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return planetas.get(position);
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
        Planeta planeta = planetas.get(position);
        txtNomePlaneta.setText(planeta.getNome());
        imgPlaneta.setImageResource(planeta.getImagem());

        // Retorna a view deste planeta
        return view;
    }
}
