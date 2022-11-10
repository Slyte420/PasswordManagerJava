package User;

public enum IDs {
    ENTRY("Entry",0, new String[]{"Username", "Password"}),
    ENTRYINTERNET("Internet",1,new String[]{"Username", "Password","URL"}),
    ENTRYEMAIL("Email",2,new String[]{"Username", "Password","Email"}),
    ENTRYGENERAL("General",3,new String[]{"Username", "Password","Notes"});

    private final String name;
    private final int ID;
    private final String[] columns;
    IDs(String name, int ID,String[] columns) {
        this.name = name;
        this.ID = ID;
        this.columns = columns;
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
