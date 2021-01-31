package ru.geekbrains.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("localhost", 8189);
        DataInputStream is = new DataInputStream(socket.getInputStream());
        DataOutputStream os = new DataOutputStream((socket.getOutputStream()));
        while (true) {
            String message = sc.next();
            os.writeUTF(message);
            os.flush();
            String msg = is.readUTF();
            System.out.println("From server: " + msg);
        }
    }
}
