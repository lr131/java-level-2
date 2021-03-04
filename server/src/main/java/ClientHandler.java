import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Класс, отвечающий за обмен сообщениями между клиентами и сервером.
 */
public class ClientHandler implements Runnable {
    
    private final Socket socket;
    private final Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean running;
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        running = true;
    }

    boolean auth(User user) {
        List<User> usersMockData = new ArrayList<>();
        usersMockData.add(new User("user1", "1234"));
        usersMockData.add(new User("user2", "1234"));
        usersMockData.add(new User("user3", "1234"));
        usersMockData.add(new User("user4", "1234"));
        usersMockData.add(new User("user5", "1234"));
        usersMockData.add(new User("user6", "1234"));
        usersMockData.add(new User("user7", "1234"));
//        return usersMockData.contains(user); //TODO
        for (User mock: usersMockData) {
            if (user.equals(mock)) return true;
        }
        return false;
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("[DEBUG] client start processing");
            while (running) {
                Message message = (Message) in.readObject();
                if (message.getMsg() == null) {
                    sendMessage(new Message(message.getAuthor(),
                            auth(message.getAuthor())));
                } else {
                    setUser(message.getAuthor());
                    if (message.getMsg().equals("/quit")) {
                        out.writeObject(message);
                    } else if (!message.getNickTo().isEmpty()) {
                        server.sendPrivateMessage(message);
                    } else {
                        server.broadCastMessage(message);
                    }
                    System.out.println("[DEBUG] message from client: " + message.getAuthor().getNick());
                }
            }
        } catch (Exception e) {
            System.err.println("Handled connection was broken");
            server.removeClient(this);
        }
    }
    
    public void sendMessage(Message message) throws IOException {
        out.writeObject(message);
        out.flush();
    }
}
