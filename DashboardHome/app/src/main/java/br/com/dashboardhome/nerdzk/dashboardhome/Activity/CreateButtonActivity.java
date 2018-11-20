package br.com.dashboardhome.nerdzk.dashboardhome.Activity;

import android.content.Intent;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.dashboardhome.nerdzk.dashboardhome.Model.Botao;
import br.com.dashboardhome.nerdzk.dashboardhome.R;



public class CreateButtonActivity extends AppCompatActivity {

    private Button botaoCreateButton;
    private EditText topicButton;
    private EditText valueButton;
    private EditText nameButton;


    Intent intent = new Intent(CreateButtonActivity.this, PanelButtonActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_button);

        botaoCreateButton = findViewById(R.id.botaoCreateButtonId);
        topicButton = findViewById(R.id.topicButtonId);
        valueButton = findViewById(R.id.valueButtonId);
        nameButton = findViewById(R.id.nameButtonId);




        botaoCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();

                Botao botao = new Botao();
                botao.setTopic(topicButton.getText().toString());
                botao.setValue(valueButton.getText().toString());
                botao.setName(nameButton.getText().toString());

                bundle.putSerializable("topic", topicButton.getText().toString());
                bundle.putSerializable("value", valueButton.getText().toString());
                bundle.putSerializable("name", nameButton.getText().toString());
                intent.putExtras(bundle);

                startActivity( intent );
            }
        });



    }
}
