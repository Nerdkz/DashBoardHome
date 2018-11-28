package br.com.dashboardhome.nerdzk.dashboardhome.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.dashboardhome.nerdzk.dashboardhome.R;
import br.com.dashboardhome.nerdzk.dashboardhome.model.Botao;
import br.com.dashboardhome.nerdzk.dashboardhome.model.dao.BotaoDao;

public class PanelButtonActivity extends AppCompatActivity {

    private ListView listaBotoes;
    private Button botaoAdicionar;
    private EditText topic;
    private EditText value;
    private EditText name;



    private ArrayAdapter<String> botoesAdaptador;
    private ArrayList<String> botoes;
    private ArrayList<Integer> ids;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_button);

        //Recuperar componentes
        botaoAdicionar = findViewById(R.id.botaoAdicionarId);
        topic = findViewById(R.id.topicButtonId2);
        value = findViewById(R.id.valueButtonId2);
        name = findViewById(R.id.nameButtonId);

        //Lista
        listaBotoes = findViewById(R.id.listViewId);


       botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String topico = topic.getText().toString();
                String valor = value.getText().toString();
                String nome = name.getText().toString();

                if( topico.isEmpty() ){
                    Toast.makeText( PanelButtonActivity.this, "Topic Vazio!", Toast.LENGTH_LONG).show();
                }

                else if( valor.isEmpty() ){
                    Toast.makeText( PanelButtonActivity.this, "Value Vazio!", Toast.LENGTH_LONG).show();
                }
                else if( nome.isEmpty() ){
                    Toast.makeText( PanelButtonActivity.this, "Name Vazio!", Toast.LENGTH_LONG).show();
                }
                else{
                    salvarBotao( topico, valor, nome);
                   // recuperarBotoes();
                }

                listaBotoes.setLongClickable(true);
                listaBotoes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                        //removerBotao( ids.get( position ));
                        Toast.makeText( PanelButtonActivity.this, "Salvo!", Toast.LENGTH_LONG).show();
                        return true;
                    }
                });

                //Listar Botões
                //recuperarBotoes();

            }
        });
    }

    private void salvarBotao( String topico, String valor, String nome){

        //Toast.makeText(PanelButtonActivity.this, "Tarefa salva com sucesso!", Toast.LENGTH_SHORT).show();
        try {

            Toast.makeText(PanelButtonActivity.this, "Tarefa salva com sucesso!", Toast.LENGTH_SHORT).show();
            Botao botao = new Botao();
            botao.setName( nome );
            botao.setTopic( topico );
            botao.setValue( valor );
            BotaoDao.getInstance().cadastrarBotao( botao );

            topic.setText("");
            value.setText("");
            name.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /*private void recuperarBotoes(){
        try {


            //Criar Adaptador
            botoes = new ArrayList<String>();
            ids = new ArrayList<Integer>();

            botoesAdaptador = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_expandable_list_item_1,
                    android.R.id.text2, botoes);
            listaBotoes.setAdapter(botoesAdaptador);

            //listar os Botões
            boolean position =  cursor.moveToFirst();

            while(position){

                Log.i("Resultado - ", "Id tarefa: " + cursor.getString(indiceColunaId) +
                        " Tarefa: " +cursor.getString(indiceColunaNome));
                botoes.add(cursor.getString(indiceColunaNome));
                ids.add(Integer.parseInt(cursor.getString( indiceColunaId )));

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void removerBotao(Integer id){
        try{

            bancoDados.execSQL("DELETE FROM botoes WHERE id="+id );
            recuperarBotoes();
            Toast.makeText(PanelButtonActivity.this, "Botão Removido!", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

}
