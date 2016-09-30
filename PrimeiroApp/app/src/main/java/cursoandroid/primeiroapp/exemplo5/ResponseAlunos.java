package cursoandroid.primeiroapp.exemplo5;

import java.util.List;

/**
 * Created by Igor on 28/09/2016.
 */

public class ResponseAlunos {
    private List<Aluno> results;

    public List<Aluno> getResults ()
    {
        return results;
    }

    public void setResults (List<Aluno> results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+"]";
    }
}
