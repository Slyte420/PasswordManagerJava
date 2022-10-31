package Launcher;

public enum FormsID {
    MAINMENU(0),
    CREATE(1),
    LOGIN(2),
    PASSWORDMENU(3);

    private final int ID;

    FormsID(int ID){
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}
