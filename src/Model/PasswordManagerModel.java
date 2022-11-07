package Model;

import Encryption.DES;
import Encryption.Encryption;
import InputOutputHandling.FileHandler;
import User.Entry;

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
}
