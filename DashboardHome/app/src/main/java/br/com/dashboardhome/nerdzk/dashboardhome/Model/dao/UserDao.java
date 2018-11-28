package br.com.dashboardhome.nerdzk.dashboardhome.model.dao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import br.com.dashboardhome.nerdzk.dashboardhome.activity.LoginActivity;
import br.com.dashboardhome.nerdzk.dashboardhome.activity.MainActivity;
import br.com.dashboardhome.nerdzk.dashboardhome.activity.PanelButtonActivity;
import br.com.dashboardhome.nerdzk.dashboardhome.activity.UserRegisterActivity;
import br.com.dashboardhome.nerdzk.dashboardhome.activity.UserUpdateActivity;
import br.com.dashboardhome.nerdzk.dashboardhome.model.User;

public class UserDao {

    private static UserDao userDao = null;
    public static FirebaseAuth firebaseAuth;
    public static DatabaseReference firebaseReference;
    public static DatabaseReference userReference;
    public static ArrayList<User> lista_de_usuarios;
    //private DatabaseReference connectionReference = firebaseReference.child("Conections");

    public UserDao() {
        firebaseReference = FirebaseDatabase.getInstance().getReference();
        userReference = firebaseReference.child("Users");
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public static synchronized UserDao getInstance()
    {
        if(userDao == null)
        {
            userDao = new UserDao();
        }
        return userDao;
    }
    public void autenticarUsuario(User usuario , final Context context){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword( usuario.getUsername(), usuario.getPassword() ).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ){
                    Toast.makeText(context,"Autenticação feita com sucesso!",Toast.LENGTH_LONG).show();
                    context.startActivity( new Intent( context, UserUpdateActivity.class));
                    ((Activity) context).finish();
                }
                else{
                    Toast.makeText(context,"Erro na autenticação!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void cadastrarUsuario(User user, final Context context){


        //Cadastro usuario
        firebaseAuth.createUserWithEmailAndPassword( user.getUsername(), user.getPassword()).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if( task.isSuccessful() ){ // Sucesso
                    Toast.makeText( context, "Cadastro feito com Sucesso!", Toast.LENGTH_LONG).show();
                    context.startActivity( new Intent( context, MainActivity.class)); // navegando para a activity main novamente
                }

                else{ // erro
                    Toast.makeText( context, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void excluirUsuarioAutenticado(final Context context) {

        /*
            OBS: Criar Botão de Excluir!!!!!!

        */

        // Pegando o usuário que fez o "Sign In"
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // Usando o método delete para deleta-lo da aunteticação
        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "Usuário não excluido!!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Usuário excluido com sucesso!!", Toast.LENGTH_LONG).show();
                }


            }


        });
    }

    public static String buscaUmUsuarioEspecificoERetornaASuaChaveDoFireBase(String email) {
        // percorrendo cada usuário da lista de usuários
        for (User user : lista_de_usuarios) {
            // se o email do usuário da posição X, for igual ao email que foi passado para a função
            if (user.getUsername().equalsIgnoreCase(email)) {
                // retorno a chave dele
                return user.getUsername();
            }
        }

        // se não encontrou anda, retorna null
        return null;
    }

    public void atualizarUsuario( User user, final Context context ){

        //atualização usuario
        firebaseAuth.createUserWithEmailAndPassword( user.getUsername(), user.getPassword()).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if( task.isSuccessful() ){ // Sucesso
                    Toast.makeText( context, "Atualização feita com Sucesso!", Toast.LENGTH_LONG).show();
                    context.startActivity( new Intent( context, MainActivity.class)); // navegando para a activity main novamente
                }

                else{ // erro
                    Toast.makeText( context, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
