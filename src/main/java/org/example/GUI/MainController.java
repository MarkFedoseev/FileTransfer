package org.example.GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import java.io.File;

import org.example.FTP.FTPGet;
import org.example.FTP.FTPSend;
import org.example.FTP.Server;
import org.example.IO.FTPConfig;

import org.example.P2P.Server.HolePunchingServer;

public class MainController extends Application {

    File fileToSend;
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/MainForm.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("File Transfer");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
        @FXML
        private TextField ftpUploadAddress;
        @FXML
        private TextField ftpUploadPort;
        @FXML
        private TextField ftpUploadUsername;
        @FXML
        private PasswordField ftpUploadPassword;
        @FXML
        private TextField ftpUploadFilename;

        @FXML
        private TextField ftpDownloadAddress;
        @FXML
        private TextField ftpDownloadFilename;
        @FXML
        private TextField ftpDownloadPort;
        @FXML
        private TextField ftpDownloadUsername;
        @FXML
        private PasswordField ftpDownloadPassword;

        @FXML
        private TextField p2pServerAddress;
        @FXML
        private TextField p2pServerPort;

        @FXML
        private Label statusBar;

        @FXML
        private TextField ftpServerAddress;
        @FXML
        private TextField ftpServerPort;
        @FXML
        private TextField ftpServerUser;
        @FXML
        private PasswordField ftpServerPassword;
        @FXML
        private TextField ftpHomeDirectory;

        @FXML
        private TextField p2pHostAddress;
        @FXML
        private TextField p2pHostPort;


        @FXML
        private void Exit() {
            System.exit(0);
        }

        @FXML
        private void browseFileUpload(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                fileToSend = file;
            }
        }

        @FXML
        private void uploadFileToFtp(ActionEvent event) {

            FTPConfig ftpConfig = new FTPConfig(
                    ftpUploadAddress.getText(),
                    Integer.parseInt(ftpUploadPort.getText()),
                    ftpUploadUsername.getText(),
                    ftpUploadPassword.getText()
            );
            FTPSend ftpSend = new FTPSend();
            ftpSend.main(FTPConfig.server, FTPConfig.port, ftpConfig.user, ftpConfig.password,
                    ftpUploadFilename.getText(),
                    fileToSend);
            /*
            ftpSend.main(FTPConfig.server, FTPConfig.port, ftpConfig.user, ftpConfig.password,
                    fileToSend.getAbsolutePath().substring(fileToSend.getAbsolutePath().lastIndexOf("\\")+1),
                    fileToSend);

             */
        }

        @FXML
        private void browseFileDownload(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                fileToSend = file;
            }
        }

        @FXML
        private void downloadFileFromFtp(ActionEvent event) {
            FTPConfig ftpConfig = new FTPConfig(
                    ftpDownloadAddress.getText(),
                    Integer.parseInt(ftpDownloadPort.getText()),
                    ftpDownloadUsername.getText(),
                    ftpDownloadPassword.getText()
            );
            FTPGet ftpGet = new FTPGet();
            ftpGet.main(FTPConfig.server, FTPConfig.port, ftpConfig.user, ftpConfig.password,
                    ftpDownloadFilename.getText(),
                    fileToSend);
        }

        @FXML
        private void browseFileP2P(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                fileToSend = file;
            }
        }

        @FXML
        private void sendFileP2P(ActionEvent event) {
            //TODO
        }

        Server ftpServer = new Server();
        @FXML
        private void StartFTPServer(ActionEvent event) {
            Server.start(
                    ftpServerAddress.getText(),
                    Integer.parseInt(ftpServerPort.getText()),
                    ftpServerUser.getText(),
                    ftpServerPassword.getText(),
                    ftpHomeDirectory.getText()
                    );
        }

        @FXML
        private void StopFTPServer(ActionEvent event) {
            ftpServer.stop();
        }

        HolePunchingServer p2pServer = new HolePunchingServer();
        @FXML
        private void StartP2PServer(ActionEvent event) throws Exception {
            p2pServer.main(p2pHostAddress.getText(), Integer.parseInt(p2pHostPort.getText()));
        }

        @FXML
        private void StopP2PServer(ActionEvent event) {
            p2pServer.stop();
        }

    public static void main(String[] args) {
        launch(args);
    }
}