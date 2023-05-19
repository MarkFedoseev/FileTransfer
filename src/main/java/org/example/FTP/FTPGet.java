package org.example.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTP;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FTPGet {
    public static void main(String serverAddress, int port,  String username, String password, String remoteFile, File localFile) {

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(serverAddress, port);
            ftpClient.login(username, password);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            OutputStream outputStream = new FileOutputStream(localFile);
            InputStream inputStream = ftpClient.retrieveFileStream(remoteFile);

            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream.write(bytesArray, 0, bytesRead);
            }

            boolean success = ftpClient.completePendingCommand();
            if (success) {
                System.out.println("Success download");
            }

            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
