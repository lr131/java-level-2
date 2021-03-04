import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private final String login;
    private final String pass;

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getNick() {
        return login;
    }

    public boolean equals(User o) {
        if (o == null) {
            return false;
        }
        return this.login.equals(o.login)
                && this.pass.equals(this.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, pass);
    }
}
