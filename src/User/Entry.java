package User;

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
        //TODO: Random password
    }

    public Entry(){
        //TODO: Random password
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
            return (this.username == o.password && this.password == o.password);
        }
        return false;
    }

    @Override
    public String toString() {
        return username + " " + password;
    }
}
