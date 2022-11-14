package launcher;

public enum FormsID {
    MAINMENU(0),
    CREATE(1),
    LOGIN(2),
    PASSWORDMENU(3);

    private final int ID;

    private static int IDCounter = 0;

    FormsID(int ID) {
        this.ID = getIDCounter();
        incrementIDCounter();
    }


    public static int getIDCounter() {
        return IDCounter;
    }

    private static void incrementIDCounter() {
        IDCounter++;
    }

    public int getID() {
        return ID;
    }
}
