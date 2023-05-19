package org.example.IO;

import org.json.JSONObject;

public class FTPConfig {
    public FTPConfig(String serverAddress, int serverPort, String username, String pass) {
        server = serverAddress;
        port = serverPort;
        user = username;
        password = pass;
    }
    static public Boolean loaded = false;
    static public String server;
    static public int port;
    static public String user;
    static public String password;
    static public void load() {
        if (loaded)
            return;
        ReadConfig readConfig = new ReadConfig();
        JSONObject config = readConfig.GetConfig("FTPConfig.cfg");
        server = config.getString("host");
        port = config.getInt("port");
        user = config.getString("user");
        password = config.getString("password");
        loaded = true;
    }
}