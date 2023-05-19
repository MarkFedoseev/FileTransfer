package org.example.IO;

import org.json.JSONObject;

public class HPConfig {
    static public Boolean loaded = false;
    static public String myServerAddress;
    static public int myServerPort;
    static public String remoteServerAddress;
    static public int remoteServerPort;

    static public void load() {
        if (loaded)
            return;
        ReadConfig readConfig = new ReadConfig();
        JSONObject config = readConfig.GetConfig("HPConfig.cfg");
        myServerAddress = config.getString("myServerAddress");
        myServerPort = config.getInt("myServerPort");
        remoteServerAddress = config.getString("remoteServerAddress");
        remoteServerPort = config.getInt("remoteServerPort");
        loaded = true;
    }
}