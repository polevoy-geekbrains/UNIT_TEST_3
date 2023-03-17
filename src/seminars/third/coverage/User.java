package seminars.third.coverage;

import java.util.Objects;

public class User {
    public String name;
    public String pass;
    private boolean authenticate = false;
    private boolean admin = false;
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
    public boolean auth (String login, String pass){
        return Objects.equals(login, this.name) && Objects.equals(pass, this.pass);
    }
}
