import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Класс, отвечающий за обмен сообщениями между клиентами и сервером.
 */
public class ClientHandler implements Runnable {
    
    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean running;
    private String nickName;
    private static int cnt = 0;
    
    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        running = true;
        cnt++;
        nickName = "user" + cnt;
    }

    @Override
    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            System.out.println("[DEBUG] client start processing");
            while (running) {
                String message = in.readUTF();
                if (message.equals("/quit")) {
                    out.writeUTF(message);
                } else {
                    server.broadCastMessage("[" + nickName + "]: " + message);
                }
                System.out.println("[DEBUG] message from client: " + message);
            }
        } catch (Exception e) {
            System.err.println("Handled connection was broken");
            server.removeClient(this);
        }
    }
    
    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }
}
