package user;

public enum IDs {
    ENTRY("Entry", new String[]{"Username", "Password"}),
    ENTRYINTERNET("Internet",new String[]{"Username", "Password","URL"}),
    ENTRYEMAIL("Email",new String[]{"Username", "Password","Email"}),
    ENTRYGENERAL("General",new String[]{"Username", "Password","Notes"});

    private final String name;
    private final int ID;
    private static int IDCounter = 0;
    private final String[] columns;
    IDs(String name, String[] columns) {
        this.name = name;
        this.ID = getIDCounter();
        this.columns = columns;
        IDCounterIncrement();
    }

    private static int getIDCounter(){
        return IDCounter;
    }
    private static void IDCounterIncrement(){
        IDCounter++;
    }
    public int getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public String[] getColumns() {
        return columns;
    }

}
