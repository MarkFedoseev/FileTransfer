package org.example.P2P;
import java.net.*;

public class HolePunching_old {
    public static String main(String serverAddress, int serverPort,
                            String localIp, int localPort,
                            String remoteIp, int remotePort) throws Exception {
        DatagramSocket localSocket = new DatagramSocket(localPort);
        DatagramSocket remoteSocket = new DatagramSocket();
        localSocket.setReuseAddress(true);
        localSocket.bind(new InetSocketAddress(localIp, localPort));
        remoteSocket.connect(new InetSocketAddress(remoteIp, remotePort));
        String publicIp = localSocket.getLocalAddress().getHostAddress();
        int publicPort = localSocket.getLocalPort();
        String message = publicIp + ":" + publicPort;
        Socket serverSocket = new Socket(serverAddress, serverPort);
        serverSocket.getOutputStream().write(message.getBytes());
        byte[] buffer = new byte[1024];
        int count = serverSocket.getInputStream().read(buffer);
        String data = new String(buffer, 0, count);
        String[] parts = data.split(":");
        String remotePublicIp = parts[0];
        int remotePublicPort = Integer.parseInt(parts[1]);
        message = "test";
        byte[] bytes = message.getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(remotePublicIp), remotePublicPort);
        remoteSocket.send(packet);

        // Wait for response
        packet = new DatagramPacket(new byte[1024], 1024);
        localSocket.receive(packet);
        if (packet.getAddress().getHostAddress().equals(remotePublicIp) && packet.getPort() == remotePublicPort) {
            System.out.println("HP done");
        } else {
            System.out.println("HP failed");
        }
        return null;
    }
}
