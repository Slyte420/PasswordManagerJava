package User;

import RandomPasswordGenerator.PassGen;

public class Entry {
    protected String username;
    protected String password;
    private static final int ID = 0;

    public static int getID() {
        return ID;
    }

    public Entry(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public Entry(String username) {
        this.username = username;
        this.password = new String(new PassGen().generatePassword(24));
    }

    public Entry() {
        this.password = new String(new PassGen().generatePassword(24));
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Entry && obj != null) {
            Entry o = (Entry) obj;
            boolean user = false;
            boolean password = false;
            if (o.username == null && this.username == null) {
                user = true;
            } else {
                if (o.username != null && this.username != null) {
                    user = o.username.equals(this.username);
                }
            }
            if (o.password == null && this.password == null) {
                password = true;
            } else {
                if (o.password != null && this.password != null) {
                    password = o.password.equals(this.password);
                }
            }
            return (user && password);
        }
        return false;
    }

    @Override
    public String toString() {
        return ID + " " + username + " " + password;
    }
}
