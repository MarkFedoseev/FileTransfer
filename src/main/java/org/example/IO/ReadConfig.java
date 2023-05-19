package org.example.IO;

import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadConfig {
    public static JSONObject GetConfig(String configpath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(configpath)));
            JSONObject config = new JSONObject(content);
            /*
            System.out.println("Host: " + config.getString("host"));
            System.out.println("Port: " + config.getInt("port"));
            System.out.println("User: " + config.getString("user"));
            System.out.println("Pass: " + config.getString("password"));
             */

            return config;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}