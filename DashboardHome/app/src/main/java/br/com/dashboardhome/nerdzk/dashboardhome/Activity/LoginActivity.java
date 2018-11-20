package br.com.dashboardhome.nerdzk.dashboardhome.Activity;

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

import br.com.dashboardhome.nerdzk.dashboardhome.Model.Connection;
import br.com.dashboardhome.nerdzk.dashboardhome.R;

public class LoginActivity extends AppCompatActivity {

    private EditText server;
    private EditText port;
    private EditText hostName;
    private Button botaoSave;

    Connection connection = new Connection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        server = findViewById(R.id.serverId);
        port = findViewById(R.id.portId);
        hostName = findViewById(R.id.hostnameId);
        botaoSave = findViewById(R.id.botaoSaveId);


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

            }
        });

    }
}
