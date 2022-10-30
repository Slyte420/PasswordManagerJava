package User;

public enum IDs {
    ENTRY("Entry",0),
    ENTRYINTRENET("Internet",1),
    ENTRYEMAIL("Email",2),
    ENTRYGENERAL("General",3);

    private final String name;
    private final int ID;
    IDs(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public int getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
}
