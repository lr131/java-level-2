import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;


public class ChatController
extends MainController
implements Initializable {

    private Network network;
    public TextField messageField;
    public ListView<String> listView;
    private Date currentDate;
    private DateFormat dateFormatter = new SimpleDateFormat(
            "dd MMM yyyy");
    private DateFormat timeFormatter = new SimpleDateFormat(
            "HH:mm");

    public void inputMessage(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            send();
        }
    }

    public void sendMessage(ActionEvent actionEvent) throws IOException {
        send();
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        network = Network.getInstance();
        new Thread(() -> {
            try {
                while (true) {
                    MessageDTO message = network.readMessage();
                    if (message.getMsg().equals("/quit")) {
                        network.close();
                        break;
                    }
                    if (listView.getItems().size() == 0 || isNewDay(new Date())) {
                        //если переписка только началась, то помечаем начало переписки
                        // или если в процессе переписки наступили новые сутки
                        listView.getItems().add(dateFormatter.format(new Date()));
                        setCurrentDate(new Date());
                    }
                    String privateLabel = message.getNickTo().isEmpty()
                            ? ""
                            :" PRIVATE";
                    Platform.runLater(() -> listView.getItems().add(
                            String.format("(%s) [%s]%s: %s",
                                    timeFormatter.format(new Date()),
                                    message.getNickFrom(),
                                    privateLabel,
                                    message.getMsg()
                            )
                        )
                    );
                }
            } catch (IOException e) {
                System.err.println("Server was broke");
                Platform.runLater(() -> listView.getItems().add("Server was broke"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Метод отправляет сообщение.
     * Под отправкой понимается перемещение сообщения в общее текстовое поле.
     */
    private void send() throws IOException {
        Date date = new Date();
        ObservableList<String> historyItems = listView.getItems();
        String address = "";
        String msgBody = messageField.getText();
        if (msgBody.startsWith("/w ")) {
            address = msgBody.split(" ")[1];
            int indexMsg = msgBody.indexOf(address) + address.length() + 1;
            msgBody = msgBody.substring(indexMsg);
        }

        network.writeMessage(new MessageDTO(
                "client nick",
                address,
                msgBody)
        );
        messageField.clear();
    }

    /**
     * Устанавливает, наступили ли новые сутки или ещё нет.
     * @param date
     *        дата отправки сообщения.
     * @return {@code true} новые сутки наступили.
     *         {@code false} новые сутки не наступили.
     */
    private boolean isNewDay(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        //TODO потом поправить
        if (currentDate == null) {
            currentDate = new Date();
        }
        String currentDateStr = formatter.format(currentDate);
        String newDateStr = formatter.format(date);

        Date current = null;
        Date newDate = null;

        try {
            current = formatter.parse(currentDateStr);
            newDate = formatter.parse(newDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Количество дней между датами в миллисекундах
        long diff = newDate.getTime() - current.getTime();
        // Перевод количества дней между датами из миллисекунд в дни
        int days =  (int)(diff / (24 * 60 * 60 * 1000));

        return days > 0;
    }
}
