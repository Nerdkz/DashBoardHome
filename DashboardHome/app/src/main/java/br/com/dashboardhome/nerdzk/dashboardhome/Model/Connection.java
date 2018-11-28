package br.com.dashboardhome.nerdzk.dashboardhome.model;

public class Connection {

    private String key;
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

    public String getKey(){return key;}
    public void setKey(String key){ this.key = key;}

    @Override
    public String toString(){
        return this.key+ " / " + this.server+" / "+this.port+" / "+this.clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
