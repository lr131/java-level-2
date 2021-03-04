import java.io.*;
import java.net.Socket;

/**
 * Класс, отвечающий за обмен сообщениями между клиентами и сервером.
 */
public class ClientHandler implements Runnable {
    
    private Socket socket;
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean running;
    private String nickName;


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        running = true;
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("[DEBUG] client start processing");
            while (running) {
                MessageDTO message = (MessageDTO) in.readObject();
                setNickName(message.getNickFrom());
                if (message.getMsg().equals("/quit")) {
                    out.writeObject(message);
                } else if (!message.getNickTo().isEmpty()) {
                    server.sendPrivateMessage(message);
                } else {
                    server.broadCastMessage(message);
                }
                System.out.println("[DEBUG] message from client: " + message);
            }
        } catch (Exception e) {
            System.err.println("Handled connection was broken");
            server.removeClient(this);
        }
    }
    
    public void sendMessage(MessageDTO message) throws IOException {
        out.writeObject(message);
        out.flush();
    }
}
