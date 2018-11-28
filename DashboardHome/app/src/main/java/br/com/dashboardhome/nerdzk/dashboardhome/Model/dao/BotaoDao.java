package br.com.dashboardhome.nerdzk.dashboardhome.model.dao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.dashboardhome.nerdzk.dashboardhome.activity.MainActivity;
import br.com.dashboardhome.nerdzk.dashboardhome.model.Botao;
import br.com.dashboardhome.nerdzk.dashboardhome.model.User;

public class BotaoDao {

    private static BotaoDao botaoDao = null;
    public static DatabaseReference firebaseReference;
    public static DatabaseReference botaoReference;


    public BotaoDao() {
        firebaseReference = FirebaseDatabase.getInstance().getReference();
        botaoReference = firebaseReference.child("Buttons");
    }

    public static synchronized BotaoDao getInstance()
    {
        if(botaoDao == null)
        {
            botaoDao = new BotaoDao();
        }
        return botaoDao;
    }

    public void cadastrarBotao(Botao botao){
        botaoReference.push().setValue( botao );
    }


}
