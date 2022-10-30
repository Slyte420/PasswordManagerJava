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

    public PasswordManagerModel(){
        entries = new ArrayList<Entry>();
        fileHandler = new FileHandler();
        instanceEnc = DES.getInstance();
    }
    public PasswordManagerModel(ArrayList<Entry> entries,FileHandler fileHandler, Encryption instanceEnc){
        this.entries = entries;
        this.fileHandler = fileHandler;
        this.instanceEnc = instanceEnc;
    }
    public PasswordManagerModel(FileHandler fileHandler){
        entries = new ArrayList<Entry>();
        fileHandler = fileHandler;
        instanceEnc = DES.getInstance();
    }
    public PasswordManagerModel(ArrayList<Entry> entries){
        this.entries = entries;
        fileHandler = new FileHandler();
        instanceEnc = DES.getInstance();
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
