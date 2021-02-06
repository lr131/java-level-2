package ru.geekbrains.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Cервер.
 *
 * @author Kristina Retivykh
 */
public class Server {
    private final int PORT = 8189;
    private boolean running;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Server() {
        running = true;
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (running) {
                System.out.println("Server is ready.");
                Socket socket = server.accept(); //wait client
                Scanner scanner = new Scanner(System.in);
                outputStream = new DataOutputStream(socket.getOutputStream());
                Thread threadGet = new Thread(() -> {
                    try {
                        getMessages(socket);
                    } catch (SocketException e) {
                        System.out.println("Client connection was broke.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                threadGet.start();
                while (true) {
                    String message = scanner.next();
                    outputStream.writeUTF(message);
                    outputStream.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getMessages(Socket socket) throws IOException {
        while (true) {
            inputStream = new DataInputStream(socket.getInputStream());
            String message = inputStream.readUTF();
            System.out.println(message);
            if (message.equals("exit")) {
                outputStream.writeUTF("You have dropped the connection.");
                outputStream.flush();
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
