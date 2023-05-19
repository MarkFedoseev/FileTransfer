package org.example.FTP;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FTPSend {
    public static void main(String serverAddress, int port,  String username, String password, String remoteFile, File localFile) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(serverAddress, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();

            InputStream inputStream = new FileInputStream(localFile);

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.storeFile(remoteFile, inputStream);

            System.out.println("Success upload");

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
