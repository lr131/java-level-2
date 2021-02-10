import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;


public class ChatController implements Initializable {
    
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
        //TODO всё, что здесь, перенести в отдельный метод типа "коннект", привязать к кнопке
        network = Network.getInstance();
        new Thread(() -> {
            try {
                while (true) {
                    String message = network.readMessage();
                    if (message.equals("/quit")) {
                        network.close();
                        break;
                    }
                    Platform.runLater(() -> listView.getItems().add(message));
                }
            } catch (IOException e) {
                System.err.println("Server was broke");
                Platform.runLater(() -> listView.getItems().add("Server was broke"));
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
        //TODO
        if (historyItems.size() == 0 || isNewDay(date)) {
            //если переписка только началась, то помечаем начало переписки
            // или если в процессе переписки наступили новые сутки
            network.writeMessage(dateFormatter.format(date));
            setCurrentDate(date);
        }
        String prefixMsg = "(" + timeFormatter.format(date) + "): ";
        network.writeMessage(prefixMsg + messageField.getText());
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
