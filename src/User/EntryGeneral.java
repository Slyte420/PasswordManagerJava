package User;

public class EntryGeneral extends Entry {
    private String notes;

    private final static int ID = 1;


    public EntryGeneral(String username, String password, String notes) {
        super(username, password);
        this.notes = notes;
    }

    public EntryGeneral(String username ,String notes) {
        super(username);
        this.notes = notes;
    }

    public EntryGeneral(String username) {
        super(username);
    }

    public EntryGeneral(){
        super();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public static int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return super.toString() + " " + notes;
    }
}
