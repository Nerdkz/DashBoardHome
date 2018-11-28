package br.com.dashboardhome.nerdzk.dashboardhome.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.dashboardhome.nerdzk.dashboardhome.R;
import br.com.dashboardhome.nerdzk.dashboardhome.model.User;
import br.com.dashboardhome.nerdzk.dashboardhome.model.dao.UserDao;

public class UserRegisterActivity extends AppCompatActivity {

    private  EditText newEmail;
    private EditText email;
    private EditText password;
    private Button botaoSignup;
    private Button botaoUpdate;

    private FirebaseAuth cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        email = findViewById(R.id.emailId);
        password = findViewById(R.id.passwordId);
        botaoSignup = findViewById(R.id.botaoSignupId);
        botaoUpdate = findViewById(R.id.botaoUpdateUserId);
        newEmail = findViewById(R.id.newEmailId);

        botaoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailDigitado = email.getText().toString();
                String passwordDigitado = password.getText().toString();

                if( emailDigitado.isEmpty() ){
                    Toast.makeText( UserRegisterActivity.this, "Campo Email vazio!", Toast.LENGTH_LONG).show();
                }
                else if( passwordDigitado.isEmpty() ){
                    Toast.makeText( UserRegisterActivity.this, "Campo Password vazio!", Toast.LENGTH_LONG).show();
                }
                else if( passwordDigitado.length() < 6){
                    Toast.makeText( UserRegisterActivity.this, "Digite uma senha com no mínimo 6 caracteres!", Toast.LENGTH_LONG).show();

                }
                else{
                    User user = new User();
                    user.setUsername( emailDigitado );
                    user.setPassword( passwordDigitado );
                    UserDao.getInstance().cadastrarUsuario( user, UserRegisterActivity.this );

                }
            }
        });


    }
}
