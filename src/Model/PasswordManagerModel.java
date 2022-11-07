package Model;

import Encryption.DES;
import Encryption.Encryption;
import InputOutputHandling.FileHandler;
import User.Entry;
import User.EntryEmail;
import User.EntryInternet;

import java.util.ArrayList;

public class PasswordManagerModel {
    private ArrayList<Entry> entries;
    private FileHandler fileHandler;
    private Encryption instanceEnc;

    private static PasswordManagerModel instance;

    private PasswordManagerModel(){
        entries = new ArrayList<Entry>();
        fileHandler = new FileHandler();
        instanceEnc = DES.getInstance();
    }
    public static PasswordManagerModel getInstance(){
        if(instance == null){
            instance = new PasswordManagerModel();
        }
        return instance;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public Encryption getInstanceEnc() {
        return instanceEnc;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void setInstanceEnc(Encryption instanceEnc) {
        this.instanceEnc = instanceEnc;
    }

    public void addEntryGeneral(String username, char[] password){
        entries.add(new Entry(username,new String(password)));
    }
    public void addEntryGeneral(String username, String password){
        entries.add(new Entry(username,password));
    }
    public void addEntryEmail(String username, char[] password,String email){
        entries.add(new EntryEmail(username,new String(password),email));
    }
    public void addEntryEmail(String username, String password,String email){
        entries.add(new EntryEmail(username,password,email));
    }
    public void addEntryInternet(String username, char[] password,String URL){
        entries.add(new EntryInternet(username,new String(password),URL));
    }
    public void addEntryInternet(String username, String password,String URL){
        entries.add(new EntryInternet(username,password,URL));
    }
    
}
