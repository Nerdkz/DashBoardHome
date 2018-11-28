package br.com.dashboardhome.nerdzk.dashboardhome.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.dashboardhome.nerdzk.dashboardhome.R;
import br.com.dashboardhome.nerdzk.dashboardhome.model.User;
import br.com.dashboardhome.nerdzk.dashboardhome.model.dao.UserDao;

public class UserUpdateActivity extends AppCompatActivity {

    private EditText newEmail;
    private EditText newPassword;
    private Button botaoUpdate;
    private Button botaoGoSetUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);

        newEmail = findViewById(R.id.newEmailId);
        newPassword = findViewById(R.id.newPasswordId);
        botaoUpdate = findViewById(R.id.botaoUpdateUserId);
        botaoGoSetUp = findViewById(R.id.botaoSetUpId);

        botaoUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmailDigitado = newEmail.getText().toString();
                String newpasswordDigitado = newPassword.getText().toString();

                if( newEmailDigitado.isEmpty() ){
                    Toast.makeText( UserUpdateActivity.this, "Campo Email vazio!", Toast.LENGTH_LONG).show();
                }
                else if( newEmailDigitado.isEmpty()){
                    Toast.makeText( UserUpdateActivity.this, "Campo (NEW)Email vazio!", Toast.LENGTH_LONG).show();
                }
                else if( newpasswordDigitado.isEmpty() ){
                    Toast.makeText( UserUpdateActivity.this, "Campo Password vazio!", Toast.LENGTH_LONG).show();
                }
                else if( newpasswordDigitado.length() < 6){
                    Toast.makeText( UserUpdateActivity.this, "Digite uma senha com no mínimo 6 caracteres!", Toast.LENGTH_LONG).show();

                }
                else{
                    UserDao.getInstance().excluirUsuarioAutenticado(UserUpdateActivity.this);
                    User user = new User();
                    user.setUsername( newEmailDigitado );
                    user.setPassword( newpasswordDigitado );
                    UserDao.getInstance().atualizarUsuario( user, UserUpdateActivity.this );
                }
            }
        });

        botaoGoSetUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( UserUpdateActivity.this, LoginActivity.class));
            }
        });

    }
}
