package org.example.FTP;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;

import java.io.File;

public class Server {

    private static FtpServer server;
    public static void start(String address, int port, String username, String password, String homeDirectory) {
        FtpServerFactory serverFactory = new FtpServerFactory();
        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(port);
        serverFactory.addListener("default", listenerFactory.createListener());
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        //userManagerFactory.setFile(new File("users.properties"));
        BaseUser user = new BaseUser();
        user.setName(username);
        user.setPassword(password);
        user.setHomeDirectory(homeDirectory);

        try {
            userManagerFactory.createUserManager().save(user);
        } catch (FtpException e) {
            e.printStackTrace();
        }
        serverFactory.setUserManager(userManagerFactory.createUserManager());
        server = serverFactory.createServer();
        try {
            server.start();
        } catch (FtpException e) {
            e.printStackTrace();
        }
    }
    public static void stop() {
        server.stop();
    }
}