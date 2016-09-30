package puc.com.student.model;

import java.util.Date;

/**
 * Created by Igor on 28/09/2016.
 */

public class Aluno {

    private String updatedAt;

    private String fotoUrl;

    private String idade;

    private String createdAt;

    private String objectId;

    private String telefone;

    private String nome;

    private String endereco;

    public String getUpdatedAt ()
    {
        return updatedAt;
    }

    public void setUpdatedAt (String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getFotoUrl ()
    {
        return fotoUrl;
    }

    public void setFotoUrl (String fotoUrl)
    {
        this.fotoUrl = fotoUrl;
    }

    public String getIdade ()
    {
        return idade;
    }

    public void setIdade (String idade)
    {
        this.idade = idade;
    }

    public String getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getObjectId ()
    {
        return objectId;
    }

    public void setObjectId (String objectId)
    {
        this.objectId = objectId;
    }

    public String getTelefone ()
    {
        return telefone;
    }

    public void setTelefone (String telefone)
    {
        this.telefone = telefone;
    }

    public String getNome ()
    {
        return nome;
    }

    public void setNome (String nome)
    {
        this.nome = nome;
    }

    public String getEndereco ()
    {
        return endereco;
    }

    public void setEndereco (String endereco)
    {
        this.endereco = endereco;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [updatedAt = "+updatedAt+", fotoUrl = "+fotoUrl+", idade = "+idade+", createdAt = "+createdAt+", objectId = "+objectId+", telefone = "+telefone+", nome = "+nome+", endereco = "+endereco+"]";
    }
}
