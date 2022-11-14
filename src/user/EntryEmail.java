package user;

public class EntryEmail extends Entry {
    private static final int ID = IDs.ENTRYEMAIL.getID();
    private String email;

    public EntryEmail(String username, String password, String email) {
        super(username, password);
        this.email = email;
    }

    public EntryEmail(String username, String email) {
        super(username);
        this.email = email;
    }

    public EntryEmail(String username) {
        super(username);
    }

    public EntryEmail() {
        super();
    }

    public static int getID() {
        return ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return  ID + " " + username + " " + password + " " + email;
    }
}
