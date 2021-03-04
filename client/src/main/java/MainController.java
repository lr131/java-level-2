import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class MainController {
    private static HashMap<String, Parent> controllers;
    private static Network network;
    private static Stage primaryStage;
    static private String nickName;

    public static String getNickName() {
        return nickName;
    }

    public static Network getNetwork() {
        return network;
    }

    public static void setNickName(String nickName) {
        MainController.nickName = nickName;
    }

    public MainController() {
    }

    public MainController(Stage primaryStage) {
        this.controllers = new HashMap<>();
        this.primaryStage = primaryStage;
        network = Network.getInstance();
    }

    public void add(String name, Parent parent)
    {
        controllers.put(name, parent);
    }

    public Parent get(String name) {
        return controllers.getOrDefault(name, null);
    }

    public void activate(String name, String title)
    {
        Parent parent = controllers.get(name);
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle(title);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(request -> {
            try {
                network.writeMessage(new MessageDTO(getNickName(), "/quit"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}