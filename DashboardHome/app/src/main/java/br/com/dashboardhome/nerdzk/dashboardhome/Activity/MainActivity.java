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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.dashboardhome.nerdzk.dashboardhome.R;
import br.com.dashboardhome.nerdzk.dashboardhome.model.*;
import br.com.dashboardhome.nerdzk.dashboardhome.model.dao.UserDao;


public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button botaoSignin;
    private Button botaoRegister;



    User user = new User();
    Connection connection = new Connection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.emailId);
        password = findViewById(R.id.passwordId);
        botaoSignin = findViewById(R.id.botaoSigninId);
        botaoRegister = findViewById(R.id.botaoRegisterId);

        botaoSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailDigitado = email.getText().toString();
                String senhaDigitada = password.getText().toString();

                if( emailDigitado.isEmpty() ){
                    Toast.makeText( MainActivity.this, "Campo Email vazio!", Toast.LENGTH_LONG).show();
                }
                else if( senhaDigitada.isEmpty() ){
                    Toast.makeText( MainActivity.this, "Campo Password vazio!", Toast.LENGTH_LONG).show();
                }
                else{
                    user.setUsername( emailDigitado );
                    user.setPassword( senhaDigitada );

                    UserDao userDao = UserDao.getInstance();
                    userDao.autenticarUsuario( user, MainActivity.this);
                }

            }
        });

        botaoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( MainActivity.this, UserRegisterActivity.class)); // navegando para a activity UserRegister
            }
        });

    }
}

/*
    connection.setServer("192.168.0.1");
    connection.setPort("1883");
    connection.setClientId("Nerdzk");

    userReference.child("001").setValue(user);
    connectionReference.child("001").setValue(connection);
*/


/*
    //funções para recuperar dados do usuário
    userReference.addValueEventListener( new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.i("USERS: ", dataSnapshot.getValue().toString() );
        }
    @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //funções para recuperar dados do conexão
        connectionReference.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("FIREBASE:", dataSnapshot.getValue().toString() );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */