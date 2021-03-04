import java.io.Serializable;

public class Message implements Serializable {
    private final User author;
    private final String nickTo;
    private final String msg;
    private Boolean state = null;

    public Boolean isState() {
        return state;
    }

    public User getAuthor() {
        return this.author;
    }

    public String getNickTo() {
        return nickTo;
    }

    public String getMsg() {
        return msg;
    }

    public Message(User author, String nickTo, String msg) {
        this.author = author;
        this.nickTo = nickTo;
        this.msg = msg;
    }

    public Message(User author, String msg) {
        this.author = author;
        this.msg = msg;
        this.nickTo = null;
    }

    public Message(User user) {
        this.author = user;
        this.nickTo = null;
        this.msg = null;
    }

    public Message(User user, boolean state) {
        this.author = user;
        this.nickTo = null;
        this.msg = null;
        this.state = state;
    }

}
