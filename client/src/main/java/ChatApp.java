import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Сущность чата.
 *
 * @author Kristina Retivykh
 */
public class ChatApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent chat = FXMLLoader.load(getClass().getResource("chat.fxml"));
        Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
        MainController mainController = new MainController(primaryStage);
        mainController.add("login", login);
        mainController.add("chat", chat);
        mainController.activate("login", "Войти");

    }
}
