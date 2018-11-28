package br.com.dashboardhome.nerdzk.dashboardhome.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import br.com.dashboardhome.nerdzk.dashboardhome.model.Connection;
import br.com.dashboardhome.nerdzk.dashboardhome.R;
import br.com.dashboardhome.nerdzk.dashboardhome.model.dao.BotaoDao;
import br.com.dashboardhome.nerdzk.dashboardhome.model.dao.ConnectionDao;
import br.com.dashboardhome.nerdzk.dashboardhome.model.dao.UserDao;

public class LoginActivity extends AppCompatActivity {

    private EditText server;
    private EditText port;
    private EditText hostName;
    private Button botaoSave;
    private Button botaoDeleteUser;
    private Button botaoDeleteConnection;

    Connection connection = new Connection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        server = findViewById(R.id.serverId);
        port = findViewById(R.id.portId);
        hostName = findViewById(R.id.hostnameId);
        botaoSave = findViewById(R.id.botaoSaveId);
        botaoDeleteUser = findViewById(R.id.botaoDeleteUserId);
        botaoDeleteConnection = findViewById(R.id.botaoDeleteConnectionId);

        String clientId = MqttClient.generateClientId();
        MqttAndroidClient client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com:1883",
                        clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText( LoginActivity.this, "Conected!", Toast.LENGTH_LONG ).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText( LoginActivity.this, "Connection failed!", Toast.LENGTH_LONG ).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        botaoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                connection.setServer( server.getText().toString() );
                connection.setPort( port.getText().toString() );
                connection.setClientId( hostName.getText().toString() );

                ConnectionDao.getInstance().cadastrarConnection( connection );
                Toast.makeText( LoginActivity.this, "Connection cadastrada com sucesso!", Toast.LENGTH_LONG).show();
            }
        });

        botaoDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDao.getInstance().excluirUsuarioAutenticado(LoginActivity.this);
                startActivity( new Intent( LoginActivity.this, MainActivity.class));
            }
        });

        botaoDeleteConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionDao.getInstance().deletarConnection( connection );
                Toast.makeText(LoginActivity.this, "Connection deletada com sucesso!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
