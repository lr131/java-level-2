package ru.geekbrains.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Клиент.
 *
 * @author Kristina Retivykh
 */
public class Client {
    public Client() {
        try (Socket socket = new Socket("localhost", 8189)) {
            Thread threadGet = new Thread(() -> {
                try {
                    getMessages(socket);
                } catch (IOException e) {
                    System.out.println("Server connection was broke.");
                    e.printStackTrace();
                }
            });
            threadGet.start();
            Scanner sc = new Scanner(System.in);
            DataOutputStream os = new DataOutputStream((socket.getOutputStream()));
            while (true) {
                String message = sc.next();
                os.writeUTF(message);
                os.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMessages(Socket socket) throws IOException {
        while (true) {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            String message = inputStream.readUTF();
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
