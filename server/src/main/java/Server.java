import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Класс сервера
 */
public class Server {
    private static final int DEFAULT_PORT = 8189;
    
    private ConcurrentLinkedDeque<ClientHandler> clients;

    public Server(int port) {
        clients = new ConcurrentLinkedDeque<>();
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("[DEBUG] Server started on port " + port);
          while (true) {
              Socket socket = server.accept(); //get connection
              System.out.println("[DEBUG] Client accepted.");
              ClientHandler handler = new ClientHandler(socket, this);
              addClient(handler);
              new Thread(handler).start();
              
          }
        } catch (Exception e) {
            System.err.println("Server was broke");
        }
    }

    public void addClient(ClientHandler clientHandler) {
        clients.add(clientHandler);
        System.out.println("[DEBUG] Client added to broadcast queue.");
    }
    
    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        System.out.println("[DEBUG] Client removed from broadcast queue.");
    }

    public void broadCastMessage(Message message) throws IOException {
        for (ClientHandler client: clients) {
            client.sendMessage(message);
        }
    }

    public void sendPrivateMessage(Message message) throws IOException {
        for (ClientHandler client: clients) {
            if (message.getRecipient().equals(client.getUser().getNick())
                || message.getAuthor().getNick().equals(client.getUser().getNick())) {
                client.sendMessage(message);
            }
        }
        System.out.println("[DEBUG] Client "
            + message.getAuthor().getNick()
            + " send private message to "
            + message.getRecipient()
        );
    }

    public static void main(String[] args) {
        int port = -1;
        if (args != null && args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        if (port == -1) {
            port = DEFAULT_PORT;
        }
        new Server(port);
    }
}
