package user;

public class EntryInternet extends Entry{
    private String URL;
    private static final int ID = IDs.ENTRYINTERNET.getID();
    public EntryInternet(String username, String password,String URL){
        super(username, password);
        this.URL = URL;
    }
    public EntryInternet(String username, String URL){
        super(username);
        this.URL = URL;
    }
    public EntryInternet(String username){
        super(username);
    }
    public EntryInternet(){
        super();
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public static int getID() {
        return ID;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return ID + " " + username + " " + password + " " + URL;
    }
}
