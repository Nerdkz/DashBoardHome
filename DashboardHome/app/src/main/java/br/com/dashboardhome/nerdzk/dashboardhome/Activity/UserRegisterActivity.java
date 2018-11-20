package br.com.dashboardhome.nerdzk.dashboardhome.Activity;

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

public class UserRegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button botaoSignup;

    private FirebaseAuth cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        email = findViewById(R.id.emailId);
        password = findViewById(R.id.passwordId);
        botaoSignup = findViewById(R.id.botaoSignupId);

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
                else{


                    cadastro = FirebaseAuth.getInstance();

                    //Cadastro usuario
                    cadastro.createUserWithEmailAndPassword( emailDigitado, passwordDigitado).addOnCompleteListener(UserRegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if( task.isSuccessful() ){ // Sucesso
                                Toast.makeText( UserRegisterActivity.this, "Cadastro feito com Sucesso!", Toast.LENGTH_LONG).show();
                                cadastro.signOut();
                                startActivity( new Intent( UserRegisterActivity.this, MainActivity.class)); // navegando para a activity login
                            }

                            else{ // erro
                                Toast.makeText( UserRegisterActivity.this, "erro ao Cadastrar!", Toast.LENGTH_LONG).show();
                                Toast.makeText( UserRegisterActivity.this, "Email já cadastrado!", Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }
            }
        });
    }
}
