package User;

public class EntryInternet extends Entry{
    private String URL;
    private static final int ID = 2;
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
}
