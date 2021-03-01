import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.plaf.synth.SynthTabbedPaneUI;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginController
extends MainController
implements Initializable {

    public TextField userNameField;
    public PasswordField passwordField;
    public Label lblStateConn;
    public AnchorPane logWin;

    public void login(ActionEvent actionEvent) throws IOException {
        HashMap<String, String> data = new HashMap<>();
        data.put("user1", "1234");
        data.put("user2", "1234");
        data.put("user3", "1234");
        data.put("user4", "1234");
        if ( data.containsKey(userNameField.getText())
                && data.getOrDefault(userNameField.getText(), "").equals(
                    passwordField.getText())) {
            lblStateConn.setText("Login success");
            activate("chat");
        } else {
            lblStateConn.setText("Login failed");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
