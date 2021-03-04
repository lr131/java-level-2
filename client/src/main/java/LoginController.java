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
import java.util.ResourceBundle;

public class LoginController
extends MainController
implements Initializable {
    private Network network;

    public TextField userNameField;
    public PasswordField passwordField;
    public Label lblStateConn;
    public AnchorPane logWin;

    public void login() {
        //TODO пока заглушка,  вообще отправить Message(User)
        Message respMessageMock = new Message(
                new User(userNameField.getText(),
                        passwordField.getText()),
                true
        );
        setUser(new User(userNameField.getText(),
                passwordField.getText()));
        Message messageAuth = new Message(getUser());
        if (Boolean.TRUE.equals(respMessageMock.isState())) {
            lblStateConn.setText("Login success");
            setUser(new User(userNameField.getText(),
                    passwordField.getText()));
            activate("chat", ("Чат " + getUser().getNick()));
        } else {
            lblStateConn.setText("Login failed");
        }
//        try {
////            network.writeMessage(messageAuth);
////            Message messageState = network.readMessage();
//            if (Boolean.TRUE.equals(respMessageMock.isState())) {
//                lblStateConn.setText("Login success");
//                setUser(new User(userNameField.getText(),
//                        passwordField.getText()));
//                activate("chat", ("Чат " + getUser().getNick()));
//            } else {
//                lblStateConn.setText("Login failed");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.err.println("Не получилось отправить сообщение");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.err.println("Не получилось прочитать сообщение");
//        }
    }
    public void loginButton(ActionEvent actionEvent) throws IOException {
        login();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        network = Network.getInstance();
    }

    public void signIn(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            login();
        }
    }
}
