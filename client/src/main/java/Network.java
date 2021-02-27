import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import javafx.application.Platform;

import java.io.*;
import java.net.Socket;

public class Network {

    private static final int PORT = 8189;
    
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private static Network instance;

    public static Network getInstance() {
        if (instance == null) {
            instance = new Network();
        }
        return instance;
    }
    
    private Network() {
        try {
            socket = new Socket("localhost", PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            System.err.println("Problem with server on port " + PORT);
        }
    }
    
    public MessageDTO readMessage() throws IOException, ClassNotFoundException {
        return (MessageDTO) in.readObject();
    }
    
    public void writeMessage(MessageDTO message) throws IOException {
        out.writeObject(message);
        out.flush();
    }
    
    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }
}
