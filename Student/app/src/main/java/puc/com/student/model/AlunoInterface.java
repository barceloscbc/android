package puc.com.student.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Igor on 28/09/2016.
 */

public interface AlunoInterface {
    String BASE_URL = "https://parseapi.back4app.com/classes/";

    @Headers({
            "X-Parse-Application-Id: FWmmldOSRF8GE7jR8424Ex9Tu2ZHLTrggQHLJvjY",
            "X-Parse-REST-API-Key: RegHHKDEd3qf260q0mGUM7Z7GMsWry79eKsv3Jic"
    })
    @GET("Aluno")
    Call<ResponseAlunos> listAlunos();

    @Headers({
            "X-Parse-Application-Id: FWmmldOSRF8GE7jR8424Ex9Tu2ZHLTrggQHLJvjY",
            "X-Parse-REST-API-Key: RegHHKDEd3qf260q0mGUM7Z7GMsWry79eKsv3Jic"
    })
    @DELETE("Aluno/{id}")
    Call<ResponseBody> deleteAluno(@Path("id") String id);
}
