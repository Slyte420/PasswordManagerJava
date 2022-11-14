package user;

import randompasswordgenerator.PassGen;
import randompasswordgenerator.PassGenException;

public class Entry {
    protected String username;
    protected String password;
    private static final int ID = IDs.ENTRY.getID();

    public static int getID() {
        return ID;
    }

    public Entry(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Entry(String username) {
        this.username = username;
        try {
            this.password = new String(new PassGen().generatePassword(24));
        } catch (PassGenException e) {
            throw new RuntimeException(e);
        }
    }

    public Entry() {
        try {
            this.password = new String(new PassGen().generatePassword(24));
        } catch (PassGenException e) {
            throw new RuntimeException(e);
        }
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

    public void generatePassword() {
        try {
            this.password = new String(new PassGen().generatePassword(24));
        } catch (PassGenException e) {
            throw new RuntimeException(e);
        }
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
