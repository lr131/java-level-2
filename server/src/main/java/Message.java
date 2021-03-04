import java.io.Serializable;

public class Message implements Serializable {
    private final User author;
    private final String recipient;
    private final String msg;
    private Boolean state = null;

    public Boolean isState() {
        return state;
    }

    public User getAuthor() {
        return this.author;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMsg() {
        return msg;
    }

    public Message(User author, String recipient, String msg) {
        this.author = author;
        this.recipient = recipient;
        this.msg = msg;
    }

    public Message(User author, String msg) {
        this.author = author;
        this.msg = msg;
        this.recipient = null;
    }

    public Message(User user) {
        this.author = user;
        this.recipient = null;
        this.msg = null;
    }

    public Message(User user, boolean state) {
        this.author = user;
        this.recipient = null;
        this.msg = null;
        this.state = state;
    }

}
