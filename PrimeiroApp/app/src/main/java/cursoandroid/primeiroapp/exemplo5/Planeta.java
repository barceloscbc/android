package cursoandroid.primeiroapp.exemplo5;

import java.util.ArrayList;
import java.util.List;

import cursoandroid.primeiroapp.R;

/**
 * Created by Mateus Dias on 19/07/2015.
 */
public class Planeta {
    private String nome;
    private int imagem;

    public Planeta(String nome, int imagem){
        this.nome = nome;
        this.imagem = imagem;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public static List<Planeta> getPlanetas() {
        // Planetas
        List<Planeta> planetas = new ArrayList<Planeta>();
        planetas.add(new Planeta("Mercúrio", R.drawable.planeta_01_mercurio));
        planetas.add(new Planeta("Vênus", R.drawable.planeta_02_venus));
        planetas.add(new Planeta("Terra", R.drawable.planeta_03_terra));
        planetas.add(new Planeta("Marte", R.drawable.planeta_04_marte));
        planetas.add(new Planeta("Júpiter", R.drawable.planeta_05_jupiter));
        planetas.add(new Planeta("Saturno", R.drawable.planeta_06_saturno));
        planetas.add(new Planeta("Urano", R.drawable.planeta_07_urano));
        planetas.add(new Planeta("Netuno", R.drawable.planeta_08_neptuno));
        planetas.add(new Planeta("Plutão", R.drawable.planeta_09_plutao));
        return planetas;
    }


}
