package ru.geekbrains.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private boolean running;
    private DataInputStream is;
    private DataOutputStream os;

    public EchoServer() {
        running = true;
        try (ServerSocket server = new ServerSocket(8189)) {
            while (running) {
                Socket socket = server.accept(); //wait client
                try {
                    handleConnection(socket);
                } catch (IOException ioe) {
                    System.out.println("Client connection was broke.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleConnection(Socket socket) throws IOException {
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
        while (true) {
            String message = is.readUTF();
            if (message.equals("exit")) {
                os.writeUTF("Goodbuy");
                os.flush();
                break;
            }
            os.writeUTF(message);
            os.flush();
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public static void main(String[] args) {
        new EchoServer();
    }
}
