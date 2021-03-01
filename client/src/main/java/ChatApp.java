import javafx.application.Application;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Сущность чата.
 *
 * @author Kristina Retivykh
 */
public class ChatApp extends Application {
    static Stage primaryStage;

    void setChat(Stage stage, Network network) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("chat.fxml"));
        stage.setScene(new Scene(root));

        stage.setTitle("login");
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(request -> {
            try {
                network.writeMessage(new MessageDTO("", "/quit"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent chat = FXMLLoader.load(getClass().getResource("chat.fxml"));
        Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
        MainController mainController = new MainController(primaryStage);
        mainController.add("login", login);
        mainController.add("chat", chat);
        mainController.activate("login");

    }
}
