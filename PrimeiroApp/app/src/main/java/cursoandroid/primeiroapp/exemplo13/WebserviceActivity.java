package cursoandroid.primeiroapp.exemplo13;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import cursoandroid.primeiroapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebserviceActivity extends AppCompatActivity {
    static final String TAG = "http";

    private Button btnJsonHttp;
    private Button btnJsonRetrofit;
    private Button btnDownAsync;
    private Button btnDownPicasso;
    private ProgressDialog pDialog;
    private TextView txtJsonHttp;
    private TextView txtJsonRetrofit;
    private ImageView imgDownAsync;
    private ImageView imgDownPicasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webservice);
        btnJsonHttp = (Button) findViewById(R.id.btnJsonHttp);
        btnJsonRetrofit = (Button) findViewById(R.id.btnJsonRetrofit);
        btnDownAsync= (Button) findViewById(R.id.btnDownAsync);
        btnDownPicasso = (Button) findViewById(R.id.btnDownAq);

        txtJsonHttp = (TextView) findViewById(R.id.txtJsonHttp);
        txtJsonRetrofit = (TextView) findViewById(R.id.txtJsonRetrofit);

        imgDownAsync = (ImageView) findViewById(R.id.imgDownAsync);
        imgDownPicasso = (ImageView) findViewById(R.id.imgDownPicasso);

        //ProgressDialog para loading enquanto baixa os dados
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Buscando dados...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);

        btnJsonHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSONTask().execute();
            }
        });

        btnDownAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask().execute();
            }
        });

        btnJsonRetrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONRetrofit();
            }
        });

        btnDownPicasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downPicasso();
            }
        });


    }

    //////////Download image com Picasso
    private void downPicasso() {
        imgDownPicasso = (ImageView) findViewById(R.id.imgDownPicasso);
        String url = "http://postcron.com/pt/blog/wp-content/uploads/2014/10/passaro-postcron.jpg";
        //Picasso.with(this).load(url).into(imgDownPicasso);

        //Com callback
        pDialog.show();

        Picasso.with(this)
                .load(url)
                .into(imgDownPicasso, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                        pDialog.dismiss();
                    }

                    @Override
                    public void onError() {
                        pDialog.dismiss();
                    }
                });

    }

    //////////JSON Android query
    private void JSONRetrofit() {
        pDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);



        Call<List<Empregado>> call = service.listEmpregados();
        call.enqueue(new Callback<List<Empregado>>() {
            @Override
            public void onResponse(Call<List<Empregado>> call, Response<List<Empregado>> response) {
                if (response.isSuccessful()) {
                    List<Empregado> empregados = response.body();
                    Empregado empregado = empregados.get(0);
                    txtJsonRetrofit.setText(empregado.getNome() + "\n" + empregado.getIdade() + "\n" + empregado.getCargo());
                } else {
                    txtJsonRetrofit.setText("erro: " + response.message());
                    Log.e(TAG, response.message());
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Empregado>> call, Throwable t) {
                txtJsonRetrofit.setText("falha: " + t.getMessage());
                Log.e(TAG, t.getMessage());
                pDialog.dismiss();
            }
        });


    }


    //////////////TASK para baixar o JSON
    private class JSONTask extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.show();
        }

        @Override
        protected JSONArray doInBackground(Void... params) {

            JSONArray jObj=null;
            try {
                URL u = new URL("http://androidti.com/php/Empregados.php");

                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");

                conn.connect();
                InputStream is = conn.getInputStream();

                // Read the stream
                byte[] b = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                while (is.read(b) != -1)
                    baos.write(b);

                String JSONResp = new String(baos.toByteArray());

                // try parse the string to a JSON object
                try {
                    jObj = new JSONArray(JSONResp);
                } catch (JSONException e) {
                    Log.e("JSON Parser", "Error parsing data " + e.toString());
                }

                return jObj;
            } catch (Throwable t) {
                t.printStackTrace();
            }
            return null;

        }
        @Override
        protected void onPostExecute(JSONArray json) {
            Log.d(TAG, json.toString());
            pDialog.dismiss();
            try {
                JSONObject jsonObj = (JSONObject) json.get(1);
                String nome_idade = jsonObj.getString("nome") + "\n"+ jsonObj.getString("idade")+"\n"+ jsonObj.getString("cargo");
                txtJsonHttp.setText(nome_idade);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    ///////TASK para baixar a imagem
    private class DownloadTask extends AsyncTask<Void, Void, Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog.show();

        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            Bitmap bitmap=null;
            InputStream iStream = null;
            try{
                URL url = new URL("http://wptrafficanalyzer.in/blog/demo/img6.jpg");
                /** Creating an http connection to communcate with url */
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                /** Connecting to url */
                urlConnection.connect();

                /** Reading data from url */
                iStream = urlConnection.getInputStream();

                /** Creating a bitmap from the stream returned from the url */
                bitmap = BitmapFactory.decodeStream(iStream);

            }catch(Exception e){
                Log.d(TAG, "Exception while downloading url "  + e.toString());
            }finally{
                try {
                    iStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bitmap;

        }

        @Override
        protected void onPostExecute(Bitmap result) {
            pDialog.dismiss();
            /** Displaying the downloaded image */
            imgDownAsync.setImageBitmap(result);

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_webservice, menu);
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
