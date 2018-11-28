package br.com.dashboardhome.nerdzk.dashboardhome.model.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.dashboardhome.nerdzk.dashboardhome.model.Botao;
import br.com.dashboardhome.nerdzk.dashboardhome.model.Connection;

public class ConnectionDao {

    private static ConnectionDao connectionDao = null;
    public static DatabaseReference firebaseReference;
    public static DatabaseReference connectionReference;
    public ArrayList<Connection> connectionArray = new ArrayList<Connection>();

    public ConnectionDao() {
        this.firebaseReference = FirebaseDatabase.getInstance().getReference();
        this.connectionReference = firebaseReference.child("Connections");
    }

    public static synchronized ConnectionDao getInstance()
    {
        if(connectionDao == null)
        {
            connectionDao = new ConnectionDao();
        }
        return connectionDao;
    }

    public void cadastrarConnection(Connection connection){
        connectionReference.push().setValue( connection );
        getConnections();
    }

    public void deletarConnection(Connection connection){
        connectionReference.push().setValue( null );
    }

    public void getConnections(){
        connectionReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot connectionSnap: dataSnapshot.getChildren()){

                    Connection connection = connectionSnap.getValue(Connection.class);
                    connection.setKey(connectionSnap.getKey());
                    connectionArray.add(connection);

                    Log.i("TESTE",connection.toString());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("Cancel","Cancel");
            }
        });
    }
}
