import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainController {
    private static HashMap<String, Parent> controllers;
    private static Network network;
    private static Stage primaryStage;

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

    public void activate(String name)
    {
        Parent parent = controllers.get(name);
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle(name);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(request -> {
            try {
                network.writeMessage(new MessageDTO("", "/quit"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
