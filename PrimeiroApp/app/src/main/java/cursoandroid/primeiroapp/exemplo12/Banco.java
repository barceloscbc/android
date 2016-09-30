package cursoandroid.primeiroapp.exemplo12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateus Dias on 13/07/2015.
 */
public class Banco extends SQLiteOpenHelper {
    public static String NOME_BD = "bd_usuarios";
    public static int VERSAO = 1;
    public Banco(Context context) {
        super(context, NOME_BD, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS usuarios_tbl (" + "id_usuarios INTEGER PRIMARY KEY autoincrement," +
                " usuario varchar(45) NOT NULL ," + " senha varchar(45) NOT NULL," + "" +
                " nome_completo varchar(45) NOT NULL" + ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean inserirUsuarioBD(String usuario, String senha, String nome) {
        SQLiteDatabase sqlDB = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("usuario", usuario);
        valores.put("senha", senha);
        valores.put("nome_completo", nome);
        long result = sqlDB.insert("usuarios_tbl", null, valores);
        sqlDB.close();
        if (result!=-1) {
           return true;
        }
        return false;

    }

    public boolean excluirUsuarioBD(String usuario){
        SQLiteDatabase sqlDB = getWritableDatabase();
        int result = sqlDB.delete("usuarios_tbl", "usuario=?", new String[]{usuario});
        sqlDB.close();
        if (result > 0) {
            return true;
        }
        return false;


    }

    public boolean atualizarUsuarioBD(String usuario, String senha, String nome){
        SQLiteDatabase sqlDB = getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("senha", senha);
        valores.put("nome_completo", nome);

        int result = sqlDB.update("usuarios_tbl", valores, "usuario=?", new String[]{usuario});
        sqlDB.close();
        if (result>0) {
           return true;
        }
        return false;

    }

    public List<String> getListaUsuariosBD() {
        SQLiteDatabase sqlDB = getReadableDatabase();
        List<String> usuarios = new ArrayList<String>();
        Cursor cursor = sqlDB.query("usuarios_tbl", new String[]{"usuario", "senha", "nome_completo"}, null, null, null, null, null);
        //Cursor cursorExemplo2 = sqlDB.rawQuery("select * from usuarios_tbl",null);

        while (cursor.moveToNext()) {
            usuarios.add(cursor.getString(0) + " - " + cursor.getString(1) + " - " + cursor.getString(2));
        }
        //Lembre-se de sempre fechar o cursor
        cursor.close();
        sqlDB.close();

        return usuarios;
    }
}
