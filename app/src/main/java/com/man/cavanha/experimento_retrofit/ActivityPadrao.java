/*
Esta Activity demonstra a utilização de um ListView extendendo a classe Activity.
Desta forma o id da ListView pode ser qualquer id que você quiser nomear.
No arquivo padrao.xml temos a definição do id assim:

<ListView
        android:id="@+id/listview_listadealunos"

 */
package com.man.cavanha.experimento_retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.man.cavanha.experimento_retrofit.model.Aluno;
import com.man.cavanha.experimento_retrofit.services.InterfaceDeServicos;
import com.man.cavanha.experimento_retrofit.services.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPadrao extends Activity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.padrao);
        lista = (ListView) findViewById(R.id.listview_listadealunos);
        imprimeLista();
    }

    private void imprimeLista() {

        InterfaceDeServicos services = RetrofitService.getServico();
        Call<List<Aluno>> call = services.webserviceNotasDeAlunos();

        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                List<Aluno> listaAlunosNotas = response.body();
                lista.setAdapter(new ListaAdapter(ActivityPadrao.this, listaAlunosNotas));
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {
                Log.i("debug", t.getMessage());
            }
        });
    }
}
