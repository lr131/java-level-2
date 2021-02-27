import java.io.Serializable;

public class MessageDTO implements Serializable {
    private final String nickFrom;
    private final String nickTo;
    private final String msg;

    public String getNickFrom() {
        return nickFrom;
    }

    public String getNickTo() {
        return nickTo;
    }

    public String getMsg() {
        return msg;
    }

    public MessageDTO(String nickFrom, String nickTo, String msg) {
        this.nickFrom = nickFrom;
        this.nickTo = nickTo;
        this.msg = msg;
    }

    public MessageDTO(String nickFrom, String msg) {
        this.nickFrom = nickFrom;
        this.msg = msg;
        this.nickTo = "";
    }
}
