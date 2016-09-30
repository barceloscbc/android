package cursoandroid.primeiroapp.exemplo13;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mateus Dias on 17/04/2016.
 */
public interface APIService {
    String BASE_URL  = "http://www.androidti.com/php/";

    @GET("Empregados.php")
    Call<List<Empregado>> listEmpregados();


}
