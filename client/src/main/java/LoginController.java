import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

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

    public void login() {
        HashMap<String, String> data = new HashMap<>();
        data.put("user1", "1234");
        data.put("user2", "1234");
        data.put("user3", "1234");
        data.put("user4", "1234");
        if ( data.containsKey(userNameField.getText())
                && data.getOrDefault(userNameField.getText(), "").equals(
                passwordField.getText())) {
            lblStateConn.setText("Login success");
            setNickName(userNameField.getText());
            activate("chat", ("Чат " + getNickName()));
        } else {
            lblStateConn.setText("Login failed");
        }
    }
    public void loginButton(ActionEvent actionEvent) throws IOException {
        login();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void signIn(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            login();
        }
    }
}
