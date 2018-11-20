package br.com.dashboardhome.nerdzk.dashboardhome.Model;

public class Connection {

    private String server;
    private String port;
    private String clientId;

    public Connection() {
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
