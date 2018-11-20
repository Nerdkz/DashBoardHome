package br.com.dashboardhome.nerdzk.dashboardhome.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.dashboardhome.nerdzk.dashboardhome.R;

public class PanelButtonActivity extends AppCompatActivity {

    private ListView listaBotoes;
    private Button botaoAdicionar;

    private SQLiteDatabase bancoDados;

    private ArrayAdapter<Button> botoesAdaptador;
    private ArrayList<Button> botoes;
    private ArrayList<Integer> ids;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_button);

        //Recuperar componentes
        botaoAdicionar = findViewById(R.id.botaoAdicionarId);

        //Lista
        listaBotoes = findViewById(R.id.listViewId);

        //Banco de Dados
        bancoDados = openOrCreateDatabase("dashboardhome", MODE_PRIVATE, null);

        //Tabela Botoes
        bancoDados.execSQL( "CREATE TABLE IF NOT EXISTS botoes( id INTEGER PRIMARY KEY AUTOINCREMENT, topic VARCHAR, value VARCHAR, nome VARCHAR)");

        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( PanelButtonActivity.this, CreateButtonActivity.class ) );
            }
        });




    }

}
